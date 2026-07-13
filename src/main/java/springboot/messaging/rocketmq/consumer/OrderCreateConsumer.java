package springboot.messaging.rocketmq.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import springboot.messaging.rocketmq.dto.OrderMessage;
import tools.jackson.databind.ObjectMapper;

/**
 * 订单创建消费者
 *
 * @author ljh
 * @since 2026/5/5 1:37
 */
@Slf4j
@Component
@Profile("rocketmq")
@RequiredArgsConstructor
@RocketMQMessageListener(
    consumerGroup = "order-created-consumer-group",
    topic = "order-topic",
    maxReconsumeTimes = 3,
    selectorType = SelectorType.TAG,
    selectorExpression = "order-created",
    // 并发消费
    consumeMode = ConsumeMode.CONCURRENTLY,
    // 集群模式（每个消息只被消费一次）
    messageModel = MessageModel.CLUSTERING)
public class OrderCreateConsumer  implements RocketMQListener<MessageExt> {

    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(MessageExt msg) {
        try {
            String body = new String(msg.getBody());
            OrderMessage order = objectMapper.readValue(body, OrderMessage.class);
            log.info("接收到订单创建消息, orderNo={}, msgId={}, 重试次数={}", order.getOrderNo(), msg.getMsgId(), msg.getReconsumeTimes());

            // 模拟业务处理
            processOrder(order);

            // 消费成功（无异常即视为 ACK）
        } catch (Exception e) {
            log.error("订单创建消费失败, msgId={}", msg.getMsgId(), e);
            throw new RuntimeException("订单创建消费失败，触发重试", e);
        }
    }

    private void processOrder(OrderMessage order) {
        // 生产环境：写 DB、发短信、扣库存等
        if (order.getAmount() == null || order.getAmount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("订单金额异常");
        }
    }
}
