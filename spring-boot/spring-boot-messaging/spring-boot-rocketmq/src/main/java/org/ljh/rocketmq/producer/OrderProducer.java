package org.ljh.rocketmq.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.ljh.rocketmq.dto.OrderMessage;
import org.ljh.rocketmq.listener.OrderTransactionListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 生产者服务
 *
 * @author ljh
 * @since 2026/5/5 1:34
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderProducer {

    private final RocketMQTemplate rocketMQTemplate;

    /** 主题 */
    private static final String TOPIC = "order-topic";

    /** 同步发送：关键业务用同步，确保可靠性 */
    public SendResult sendOrderCreated(OrderMessage order) {
        Message<OrderMessage> msg = MessageBuilder.withPayload(order).setHeader("orderId", order.getOrderId()).build();
        // syncSendOrderly：相同 hashKey 的消息进同一队列，保证顺序
        SendResult result = rocketMQTemplate.syncSendOrderly(TOPIC + ":order-created", msg, String.valueOf(order.getOrderId()), 10000);
        log.info("同步发送成功, orderNo={}, msgId={}", order.getOrderNo(), result.getMsgId());
        return result;
    }

    /**
     * 发送延迟消息：支付超时取消订单
     * 延迟等级：1s/5s/10s/30s/1m/2m/3m/4m/5m/6m/7m/8m/9m/10m/20m/30m/1h/2h
     * 等级 3 = 10s
     */
    public SendResult sendDelayCancel(OrderMessage order) {
        Message<OrderMessage> msg = MessageBuilder.withPayload(order).build();
        return rocketMQTemplate.syncSend(TOPIC + ":order-cancel", msg, 5000, 3);
    }

    /** 异步发送——允许丢少量消息的场景使用 */
    public void sendOrderPaid(OrderMessage order) {
        Message<OrderMessage> msg = MessageBuilder.withPayload(order).setHeader("userId", order.getUserId()).build();
        rocketMQTemplate.asyncSend(TOPIC + ":order-paid", msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("异步发送成功, orderNo={}, msgId={}", order.getOrderNo(), sendResult.getMsgId());
            }

            @Override
            public void onException(Throwable e) {
                log.error("异步发送失败, orderNo={}", order.getOrderNo(), e);
                // 生产环境：写入本地失败表，定时任务补偿
                handleSendFailure(order, e);
            }
        });
    }

    /** 发送单向消息——不关心结果，例如日志 */
    public void sendOrderLog(OrderMessage order) {
        rocketMQTemplate.sendOneWay(TOPIC + ":order-log", MessageBuilder.withPayload(order).build());
    }


    private void handleSendFailure(OrderMessage order, Throwable e) {
        // 生产环境：写入本地异常表，定时重扫补偿
        log.warn("消息发送失败已记录, 待补偿 orderNo={}", order.getOrderNo());
    }

    /**
     * 发送事务消息：确保本地事务与消息发送的一致性
     * <p>流程：
     * <ol>
     *   <li>发送半消息（Half Message）到 RocketMQ，此时消息对 Consumer 不可见</li>
     *   <li>RocketMQ 回调 {@link OrderTransactionListener#executeLocalTransaction} 执行本地事务</li>
     *   <li>根据本地事务结果，提交或回滚消息</li>
     *   <li>若超时未确认，RocketMQ 会回调 {@link OrderTransactionListener#checkLocalTransaction} 回查事务状态</li>
     * </ol>
     *
     * @param order 订单消息
     */
    public void sendOrderCreatedInTransaction(OrderMessage order) {
        Message<OrderMessage> msg = MessageBuilder.withPayload(order).setHeader("orderId", order.getOrderId()).build();
        // arg 参数会传递给 TransactionListener.executeLocalTransaction 的 arg 参数
        rocketMQTemplate.sendMessageInTransaction(TOPIC + ":order-created", msg, null);
        log.info("事务消息已发送（半消息），等待本地事务执行，orderNo={}", order.getOrderNo());
    }
}
