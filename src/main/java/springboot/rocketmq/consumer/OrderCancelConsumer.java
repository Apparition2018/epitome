package springboot.rocketmq.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import springboot.rocketmq.dto.OrderMessage;
import tools.jackson.databind.ObjectMapper;

/**
 * 订单取消消费者（带手动 ACK + 死信队列处理）
 *
 * @author ljh
 * @since 2026/5/5 1:50
 */

@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(
    consumerGroup = "order-cancel-consumer-group",
    topic = "order-topic",
    maxReconsumeTimes = 3,
    selectorType = SelectorType.TAG,
    selectorExpression = "order-cancel",
    // 顺序消费
    consumeMode = ConsumeMode.ORDERLY)
public class OrderCancelConsumer implements RocketMQListener<MessageExt> {

    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(MessageExt msg) {
        try {
            String body = new String(msg.getBody());
            OrderMessage order = objectMapper.readValue(body, OrderMessage.class);

            // 判断是否是死信消息（死信队列 topic 以 %DLQ% 开头）
            if (msg.getTopic().startsWith("%DLQ%")) {
                log.warn("收到死信消息, orderNo={}, 需人工介入或走降级方案", order.getOrderNo());
                // 生产环境：发告警 + 写人工处理表
                // 直接消费掉，不让它继续重试
                return;
            }

            log.info("接收到订单取消消息, orderNo={}, msgId={}, 重试次数={}", order.getOrderNo(), msg.getMsgId(), msg.getReconsumeTimes());
            // 正常取消业务：恢复库存、退款等
        } catch (Exception e) {
            log.error("订单取消消费失败, msgId={}", msg.getMsgId(), e);
            // 抛出异常 → RocketMQ 会重试；超过 maxReconsumeTimes 自动进入死信队列
            throw new RuntimeException("订单取消消费失败，触发重试", e);
        }
    }
}
