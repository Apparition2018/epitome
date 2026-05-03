# 并发

---
## 参考网站
1. [透彻理解 Java 并发编程](https://segmentfault.com/blog/ressmix_multithread)
---
## 阿里编程规约
1. 线程资源必须通过线程池提供，不允许在应用中自行显式创建线程
2. 线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式
    ```
    FixedThreadPool/SingleThreadExecutor    queueCapacity                ∞ → OOM
    CachedThreadPool                        maxPoolSize                  ∞ → OOM
    ScheduledThreadPool                     maxPoolSize/queueCapacity    ∞ → OOM
    ```
3. 高并发时，同步调用应该去考量锁的性能损耗
    - 无锁数据结构 → 锁区块 → 对象锁 → 类锁
    - 避免在锁代码块中调用 RPC 方法
4. 对多个资源、数据库表、对象同时加锁时，需要保持一致的加锁顺序，否则可能会造成死锁
5. 并发修改同一记录时，避免更新丢失，需要加锁。
    - 应用层加锁 / 缓存加锁 / 数据库层乐观锁 (version / updated_at) / 数据库层 CAS
    - 冲突概率小于 20% 使用乐观锁，否则使用悲观锁
    - 乐观锁的重试次数不得小于 3 次，可以使用 @Retryable
6. 资金相关的金融敏感信息，使用悲观锁策略；悲观锁遵循一锁、二判、三更新、四释放的原则
7. Abstract Queued Synchronizer
    ```
    CountDownLatch      主线程等子线程         异步转同步（主线程等并行查询结果）
    CyclicBarrier       子线程互相等齐         并发测试（50个线程同时冲）
    Semaphore           控制访问线程数         流量控制（限流/资源池）
    ```
8. 在高并发场景中，避免使用“等于”判断作为中断或退出的条件
    - 如果并发控制没有处理好，容易产生等值判断被“击穿”的情况，使用大于或小于的区间判断条件来代替
---
## 抗住高并发
- 分层削峰，减少流量直接打到数据库
- 客户端 → CDN → Nginx → 应用集群 → Redis → 消息队列 → 数据库
### 前端层
1. CDN：Content Delivery Network，静态资源（图片、CSS/JS、字体）、页面静态化（秒杀固定信息）
2. 合并请求：字体图标、SVG Sprite、接口合并（GraphQL）
3. 减少请求：节流（throttle）、防抖（debounce）
### 网关层
1. 负载均衡
    1. DNS 轮询：多机房入口分流（一个机房对应一个公网 IP）
    2. Nginx：单机房内[负载均衡](../../../resources/knowledge/nginx/nginx_3_load_balancer.conf)
2. 限流
    1. 单机：Guava RateLimiter、Semaphore
    2. [Nginx](../../../resources/knowledge/nginx/nginx_4_limit.conf)
    3. 分布式
        1. Spring Cloud Gateway 内置 RequestRateLimiter。仅限网关层，无法在业务方法上使用
        2. 自定义：Redis + Lua 实现令牌桶（Token Bucket）和滑动窗口（Sliding Window）
        3. Redisson 的 RRateLimiter
---
## 线程池
1. JDK [`ThreadPoolExecutor` 参数 和 内置拒绝策略](pool/ThreadPoolExecutorDemo.java)
2. Spring `ThreadPoolTaskExecutor` 参数 [AsyncConfig#createBaseExecutor](../../springboot/config/AsyncConfig.java)
3. [线程池对待任务的策略](pool/ThreadPoolExecutorDemo.java)
4. 何时创建：
    1. 项目启动时创建自定义线程池 [AsyncConfig#ioBoundTaskExecutor#cpuBoundTaskExecutor](../../springboot/config/AsyncConfig.java)
    2. Spring Boot 兜底创建 task- 前缀 ThreadPoolTaskExecutor（必须在配置文件自定义属性）
5. 使用场景：[异步方法/异步请求](../../springboot/controller/AsyncController.java)
6. 异步方法调用可靠性要求和补偿方案：[AsyncConfig#ioBoundTaskExecutor](../../springboot/config/AsyncConfig.java)
---
## 分布式锁
1. [数据库](https://honeypps.com/architect/distribute-lock-based-on-database/)
    1. 表记录：创建一张锁表，获取锁就增加一条记录，释放锁就删除这条记录
        ```sql
        CREATE TABLE `lock` (
            `id` BIGINT NOT NULL AUTO_INCREMENT,
            `resource` int NOT NULL COMMENT '锁定的资源',
            `desc` varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
            PRIMARY KEY (`id`),
            UNIQUE KEY `uiq_idx_resource` (`resource`)
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据库分布式锁表';
        ```
    2. 乐观锁：
        ```sql
        CREATE TABLE `good` (
            `id` BIGINT NOT NULL AUTO_INCREMENT,
            `quantity` int NOT NULL COMMENT '数量',
            `version` int NOT NULL COMMENT '版本号',
            `created_at` datetime COMMENT '创建时间',
            `updated_at` datetime COMMENT '更新时间',
            PRIMARY KEY (`id`)
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据库分布式锁表';
        ```
        1. 版本号 (version)
            ```sql
            SELECT quantity, version FROM good WHERE id = 1;
            UPDATE good SET quantity = newQuantity, version = version + 1 WHERE id = 1 AND version = oldVersion;
            ```
        2. 修改时间 (updated_at)
            ```sql
            SELECT quantity, updated_at FROM good WHERE id = 1;
            UPDATE good SET quantity = newQuantity, updated_at = newUpdatedAt WHERE id = 1 AND updated_at = oldUpdatedAt;
            ```
    3. 悲观锁：`SELECT … FOR UPDATE`
2. Redis：@see [JedisDistributedLocks](../../jar/jedis/JedisDistributedLocks.java)
    ```
    互斥性             只有一个客户端持有锁                                                      SETNX
    不会死锁           即使一个客户端持有锁的期间由于崩溃而没有主动释放锁，其他客户端后续也能获取锁   获取锁时设置过期时间，EXPIRE
    解铃还须系铃人      获取锁和释放锁必须是同一个客户端                                           获取锁和释放锁时传一个相同的唯一标识值
    容错性             大部分 Redis 节点正常运行，客户端即可获取锁和释放锁
    ```
3. Zookeeper：@see [CuratorDistributedLock](../../jar/apache/curator/CuratorDistributedLock.java)
    ```
    lock.acquire(time, timeUnit)
    lock.release()
    ```
---
## 秒杀活动
### [seckill-1]
1. 秒杀地址接口 - Redis 缓存秒杀相关信息
2. 秒杀操作接口
    1. 减少事务时间：先 insert 购买行为（无竞争不加锁），再 update 扣减库存（加行锁）
    2. 减少事务时间：存储过程（insert 购买行为 + update 扣减库存）
    3. 原子操作扣减库存（Redis+Lua）→ 记录购买行为消息（分布式 MQ）→ 消费消息落地（DB）
### [seckill-2]
1. 扣减库存
    ```xml
    <update>
        UPDATE item_stock SET stock = stock - #{amount}
        WHERE item_id = #{itemId} AND stock >= #{amount}
    </update>
    ```
2. 订单号生成：订单号有16位
     1. 前8位：时间信息，年月日
     2. 中间8位：自增序列，从序列表(sequence_info)获取
     3. 后2位：分库分表位，如：userId % 100
     - 生成代码段事务传播机制设置为 `@Transactional(propagation = Propagation.REQUIRES_NEW)`
---
