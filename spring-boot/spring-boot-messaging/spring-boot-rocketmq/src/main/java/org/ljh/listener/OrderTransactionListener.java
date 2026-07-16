package org.ljh.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.ljh.dto.OrderMessage;
import org.ljh.service.OrderService;
import org.springframework.messaging.Message;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * 订单事务消息监听器
 * <p>负责执行本地事务和回查本地事务状态
 * <p>事务生产者组通过 {@code rocketmq.producer.group} 配置，此处无需在注解中指定
 *
 * @author ljh
 * @since 2026/5/5
 */
@Slf4j
@RocketMQTransactionListener
@RequiredArgsConstructor
public class OrderTransactionListener implements RocketMQLocalTransactionListener {

    private final OrderService orderService;
    private final ObjectMapper objectMapper = new ObjectMapper(); // 支持 JavaTimeModule，反序列化 LocalDateTime

    /**
     * 执行本地事务
     * <p>在此方法中执行本地事务，并根据事务结果返回对应的状态
     *
     * @param msg 事务消息（payload 为 JSON 字节数组）
     * @param arg 发送消息时传递的额外参数
     * @return 本地事务状态：COMMIT / ROLLBACK / UNKNOWN
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            OrderMessage order = parsePayload(msg);
            log.info("事务消息：开始执行本地事务，orderNo={}", order.getOrderNo());
            orderService.createOrderInternal(order);
            log.info("事务消息：本地事务执行成功，orderNo={}", order.getOrderNo());
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            log.error("事务消息：本地事务执行失败", e);
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 回查本地事务状态
     * <p>当 RocketMQ 长时间未收到本地事务的最终确认时，会回调此方法查询事务状态
     *
     * @param msg 事务消息（payload 为 JSON 字节数组）
     * @return 本地事务状态：COMMIT / ROLLBACK / UNKNOWN
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        try {
            OrderMessage order = parsePayload(msg);
            log.info("事务消息：回查本地事务状态，orderNo={}", order.getOrderNo());
            boolean exists = orderService.checkOrderExists(order.getOrderNo());
            return exists ? RocketMQLocalTransactionState.COMMIT : RocketMQLocalTransactionState.ROLLBACK;
        } catch (Exception e) {
            log.error("事务消息：回查失败", e);
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }

    /** 将消息 payload（byte[]）反序列化为 OrderMessage */
    private OrderMessage parsePayload(Message<?> msg) throws IOException {
        Object payload = msg.getPayload();
        if (payload instanceof byte[]) {
            return objectMapper.readValue((byte[]) payload, OrderMessage.class);
        }
        if (payload instanceof OrderMessage) {
            return (OrderMessage) payload;
        }
        throw new IllegalArgumentException("无法解析消息 payload，类型：" + payload.getClass().getName());
    }
}
