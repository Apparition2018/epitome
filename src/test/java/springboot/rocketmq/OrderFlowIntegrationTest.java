package springboot.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import springboot.messaging.rocketmq.dto.OrderMessage;
import springboot.messaging.rocketmq.producer.OrderProducer;
import springboot.messaging.rocketmq.service.OrderService;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RocketMQ 集成测试
 *
 * @author ljh
 * @since 2026/5/5 1:55
 */
@Slf4j
@SpringBootTest
// PER_METHOD：每个测试方法一个新实例；实例变量每个方法独立；
//      @BeforeAll / @AfterAll 必须是 static；纯单元测试
// PER_CLASS：所有测试方法共享一个实例；实例变量方法间共享；
//      @BeforeAll / @AfterAll 可以是实例方法；集成测试，有状态依赖，需要共享资源
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("rocketmq")
public class OrderFlowIntegrationTest {

    private static final EmbeddedRocketMQServer embeddedRocketMQ;

    static {
        try {
            embeddedRocketMQ = new EmbeddedRocketMQServer();
        } catch (Exception e) {
            throw new RuntimeException("启动 EmbeddedRocketMQServer 失败", e);
        }
    }

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProducer orderProducer;

    /** 用于测试验证的阻塞队列 */
    private final BlockingQueue<OrderMessage> receivedMessages = new LinkedBlockingQueue<>();
    private DefaultMQPushConsumer testConsumer;

    /** 向 Environment 动态加入属性 */
    @DynamicPropertySource
    static void rocketmqProperties(DynamicPropertyRegistry registry) {
        registry.add("rocketmq.name-server", embeddedRocketMQ::getNameServerAddr);
    }

    @BeforeAll
    void startTestConsumer() throws Exception {
        String namesrvAddr = embeddedRocketMQ.getNameServerAddr();

        /* 启动一个测试消费者，订阅 order-topic，将收到的消息反序列化为 OrderMessage 并存入共享队列供供后续验证 */
        testConsumer = new DefaultMQPushConsumer("test-verify-consumer");
        testConsumer.setNamesrvAddr(namesrvAddr);
        testConsumer.subscribe("order-topic", "*");
        testConsumer.registerMessageListener((MessageListenerConcurrently) (msgList, context) -> {
            for (MessageExt msg : msgList) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    OrderMessage order = objectMapper.readValue(msg.getBody(), OrderMessage.class);
                    log.info("[TEST] 收到消息 tag={}, orderNo={}, msgId={}, 重试次数={}", msg.getTags(), order.getOrderNo(), msg.getMsgId(), msg.getReconsumeTimes());
                    receivedMessages.offer(order);
                } catch (Exception e) {
                    log.error("[TEST] 处理消息失败: {}", e.getMessage());
                }
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        testConsumer.start();
        log.info("[TEST] testConsumer 已启动，等待消费者注册完成...");
        // 等待消费者向 Broker 注册并完成重平衡
        TimeUnit.SECONDS.sleep(1);
    }

    @AfterAll
    void stopRocketMQ() throws Exception {
        if (testConsumer != null) testConsumer.shutdown();
        embeddedRocketMQ.shutdown();
    }

    @Test
    void testCreateMessage() throws Exception {
        OrderMessage msg = new OrderMessage(1L, "ORD-TEST-001", 1001L, new BigDecimal("199.00"), "CREATED", null);

        var result = orderProducer.sendOrderCreated(msg);
        assertNotNull(result);
        assertFalse(result.getMsgId().isEmpty());

        OrderMessage received = receivedMessages.poll(3, TimeUnit.SECONDS);
        assertNotNull(received);
        assertEquals("ORD-TEST-001", received.getOrderNo());
    }

    @Test
    void testDelayMessage() throws Exception {
        OrderMessage msg = new OrderMessage(2L, "ORD-DELAY-001", 1002L, new BigDecimal("50.00"), "CREATED", null);

        orderProducer.sendDelayCancel(msg);

        OrderMessage fast = receivedMessages.poll(2, TimeUnit.SECONDS);
        assertNull(fast, "延迟消息在 2s 内不应被消费");

        OrderMessage delayed = receivedMessages.poll(12, TimeUnit.SECONDS);
        assertNotNull(delayed, "延迟消息应在 10s 左右被消费");
        assertEquals("ORD-DELAY-001", delayed.getOrderNo());
    }

    @Test
    void testConsumerRetryOnFailure() throws Exception {
        OrderMessage badOrder = new OrderMessage(3L, "ORD-BAD-001", 1003L, BigDecimal.ZERO, "CREATED", null);

        orderProducer.sendOrderCreated(badOrder);

        OrderMessage received = receivedMessages.poll(10, TimeUnit.SECONDS);
        assertNotNull(received, "即使消费失败，流程不应中断");
    }

    @Test
    void testInTransaction() throws Exception {
        OrderMessage order = orderService.createOrder(1004L, new BigDecimal("99.99"));

        OrderMessage received = receivedMessages.poll(3, TimeUnit.SECONDS);
        assertNotNull(received);
        assertEquals(order.getOrderNo(), received.getOrderNo());
        assertEquals("CREATED", received.getStatus());
    }
}
