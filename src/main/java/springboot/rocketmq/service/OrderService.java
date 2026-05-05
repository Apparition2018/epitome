package springboot.rocketmq.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.rocketmq.dto.OrderMessage;
import springboot.rocketmq.listener.OrderTransactionListener;
import springboot.rocketmq.producer.OrderProducer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * OrderService
 *
 * <p>使用 RocketMQ 事务消息确保本地事务与消息发送的原子性
 *
 * @author ljh
 * @since 2026/5/5 1:53
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderProducer orderProducer;

    /**
     * 创建订单：通过事务消息保证一致性
     * <p>流程：
     * <ol>
     *   <li>构建订单消息（生成订单 ID / 订单号）</li>
     *   <li>发送事务消息（半消息）</li>
     *   <li>{@link OrderTransactionListener#executeLocalTransaction} 执行本地事务（写订单表）</li>
     *   <li>本地事务成功 → 提交消息，失败 → 回滚消息</li>
     * </ol>
     *
     * @param userId 用户 ID
     * @param amount 订单金额
     * @return 订单消息（此时本地事务可能尚未提交，仅供调用方参考）
     */
    public OrderMessage createOrder(Long userId, BigDecimal amount) {
        // 1. 构建订单消息（在事务消息发送前生成唯一标识）
        OrderMessage order = new OrderMessage(
            ThreadLocalRandom.current().nextLong(100000, 999999),
            "ORD" + System.currentTimeMillis(),
            userId, amount, "CREATED", LocalDateTime.now());

        // 2. 发送事务消息（本地事务在 TransactionListener 中执行）
        orderProducer.sendOrderCreatedInTransaction(order);

        return order;
    }

    /**
     * 内部方法：执行本地事务（创建订单）
     *
     * <p>由 {@link springboot.rocketmq.listener.OrderTransactionListener#executeLocalTransaction} 调用
     *
     * @param order 订单消息
     */
    @Transactional
    public void createOrderInternal(OrderMessage order) {
        // TODO: 实际应调用订单数据库 Mapper 插入订单记录
        log.info("本地事务执行：订单已创建, orderNo={}", order.getOrderNo());
    }

    /**
     * 检查订单是否存在（供事务回查使用）
     *
     * @param orderNo 订单号
     * @return 订单是否存在
     */
    public boolean checkOrderExists(String orderNo) {
        // TODO: 实际应查询数据库 select count(1) from t_order where order_no = ?
        log.info("事务回查：查询订单是否存在, orderNo={}", orderNo);
        return true;
    }

    /**
     * 支付回调
     *
     * @param orderId 订单 ID
     */
    @Transactional
    public void payOrder(Long orderId) {
        // 1. 更新订单状态为 PAID
        // 2. 异步通知发货系统
        OrderMessage paidMsg = new OrderMessage(orderId, null, null, null, "PAID", LocalDateTime.now());
        orderProducer.sendOrderPaid(paidMsg);
    }
}
