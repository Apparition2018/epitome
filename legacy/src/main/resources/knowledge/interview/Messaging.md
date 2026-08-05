# Messaging
- 使用场景：异步、削峰、解耦
- 使用缺点：系统复杂性、数据一致性、可用性
---
## Reference
1. [为什么使用消息队列？](https://github.com/doocs/advanced-java/blob/main/docs/high-concurrency/why-mq.md)
2. [消息队列是什么？](https://www.zhihu.com/question/54152397/answer/923992679)
3. [Messaging | Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#messaging)
---
## 使用场景
1. 异步：把非关键操作后置、提升响应速度
    1. 注册/下单后发 推送短信、邮件、推送
    2. 订单支付后 加积分、发优惠券
    3. 日志、埋点、监控数据上报
2. 削峰：平滑流量，保护后端系统不被瞬时高并发冲垮
    1. 秒杀/大促下单请求排队
    2. 点赞、签到、抽奖等高并发操作
    3. 定时任务：日终批量积分系统重算、日终批量生成账单
3. 解耦：降低系统耦合，发布和扩展互不影响
    1. 下单与下游操作（积分、优惠券、推荐、物流）
    2. 数据库 Binlog，异构系统同步（ES、Redis、审计平台）（顺序模型：Insert → Update → Delete）
    3. 配置中心变更，各节点或系统同步更新（广播模型）
---
## 保证消息不丢失
1. 生产者 → Broker：同步发送 + Broker ACK
2. Broker：写入磁盘（同步刷盘）
3. Broker → 消费者：手动 ACK，处理完才确认
---
## 幂等
- 原则：多次操作或请求，不会导致业务数据的错乱或重复
- 推荐方案：状态机幂等
    - 大多数场景：数据库唯一约束
    - 高并发场景：Redis SETNX（快速防重）
    - 金融/核心业务：幂等表 + 事务
---
## [RocketMQ](https://rocketmq.apache.org/zh/docs/)
- @see OrderFlowIntegrationTest.java
### 🔺Message Model（消息模型）
1. CLUSTERING：集群（默认），只会被同一消费者组内的一个实例消费
2. BROADCASTING：广播，会被同一消费者组内的所有实例消费
    - 不支持重试，对顺序模式也存在问题。所以官方建议通过多组单消费者集群实现类似效果
### 🔺Consume Mode（消费模式）
1. CONCURRENTLY：并发
2. ORDERLY：顺序
    - 顺序保证：同一 id 发送到同一个 Queue
---
## 事务消息
1. 一阶段：Prepare，生产者发送一条“半消息”（Half Message）给 Broker，存到一个内部 Topic，消费者不可见
2. 二阶段：生产者执行本地事务
    1. Commit：发送给 Broker Commit，Broker 将消息移到业务 Topic，消费者可见
    2. Rollback：发送给 Broker Rollback，丢弃该“半消息”
    3. Unknown：异常
3. 事务状态回查：异常时，Broker 定期回查生产者 ……
---
## 重试
- 集群并发模式：重试不影响其它消息
    - 默认最大重试次数：16
    - 重试间隔：不能修改，10s/30s/1m/2m/3m/4m/5m/6m/7m/8m/9m/10m/20m/30/1h/2h
    - 超过最大次数进入死信队列（%DLQ% + 消息组名）
        1. 归档+告警
        2. 业务补偿：如积分发放失败进入死信，死信消费者收到消息调用回滚订单状态接口
        3. 有限重试：如下游接口偶发超时
- 集群顺序模式：重试阻塞当前队列
    - 默认最大重试次数：∞
    - 重试间隔：默认 1s
---
## 消息堆积
1. 开启批量消费
    1. `implements RocketMQListener<List<MessageExt>>`
    2. `rocketmq.consumer.consume-message-batch-max-size: 32`
2. 水平扩容 Consumer 容器实例，实例数 ≤ Topic 总队列数
---
