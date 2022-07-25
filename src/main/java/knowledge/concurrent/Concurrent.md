# 并发编程

---
## 参考网站
1. [透彻理解 Java 并发编程](https://segmentfault.com/blog/ressmix_multithread)
---
## 阿里编程规约：
1. 获取单例对象需要保证线程安全，其中的方法也要保证线程安全
2. 高并发时，同步调用应该去考量锁的性能损耗
    1. 能用无锁数据结构，就不要用锁
        - [Java 下实现锁无关数据结构](https://blog.csdn.net/netliving/article/details/83676228)
    2. 能锁区块，就不要锁整个方法体
    3. 能用对象锁，就不要用类锁
    - 注：避免在锁代码块中调用 RPC 方法
3. 并发修改同一记录时，避免更新丢失，需要加锁。要么在应用层加锁，要么在缓存加锁，要么在数据库层使用乐观锁，使用 version 作为更新依据
    - 如果每次访问冲突概率小于 20%，推荐使用乐观锁，否则使用悲观锁。乐观锁的重试次数不得小于 3 次
    - [乐观锁和悲观锁](https://www.jianshu.com/p/d2ac26ca6525)
4. 资金相关的金融敏感信息，使用悲观锁策略；悲观锁遵循一锁、二判、三更新、四释放的原则
5. 在高并发场景中，避免使用“等于”判断作为中断或退出的条件
    - 如果并发控制没有处理好，容易产生等值判断被“击穿”的情况，使用大于或小于的区间判断条件来代替
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
    3. 悲观锁：`SELECT ... FOR UPDATE`  
2. Redis：@see JedisDistributedLocks
    ```
    互斥性             只有一个客户端持有锁                                                      SETNX
    不会死锁           即使一个客户端持有锁的期间由于崩溃而没有主动释放锁，其他客户端后续也能获取锁   获取锁时设置过期时间，EXPIRE
    解铃还须系铃人      获取锁和释放锁必须是同一个客户端                                           获取锁和释放锁时传一个相同的唯一标识值
    容错性             大部分 Redis 节点正常运行，客户端即可获取锁和释放锁
    ```
3. Zookeeper：@see CuratorDistributedLock
    ```
    lock.acquire(time, timeUnit)
    lock.release()
    ```
---
## 秒杀活动
### [seckill-1]
1. 详情页 - [CDN 缓存](https://zhuanlan.zhihu.com/p/190935418) ：Content Delivery Network，建议存放静态资源和经常访问的页面
2. 秒杀地址接口 - 服务器缓存：Redis 等
3. 秒杀操作接口 - 库存处理 | 热点商品
    - 原子计数器记录库存（NoSQL）→ 记录行为消息（分布式 MQ） → 消费消息落地（DB）
    - 事务竞争优化（减事务锁时间）：存储过程，包含 insert（购买行为）和 update（减库存）
### [seckill-2]
1. 商品表分为商品表(item)和库存表(item_stock)：加减库存锁库存表(item_stock)的时候不影响商品表(item)的使用
    - 减库存方式：①下单成功减库存；②付款成功减库存
    ```xml
    <update>
        UPDATE item_stock SET stock = stock - #{amount}
        WHERE item_id = #{itemId} AND stock >= #{amount}
    </update>
    ```
2. 订单号生成：订单号有16为
     1. 前8位：时间信息，年月日
     2. 中间8位：自增序列，从序列表(sequence_info)获取
     3. 后2位：分库分表位，如：userId % 100
     - 生成代码段事务传播机制设置为 `@Transactional(propagation = Propagation.REQUIRES_NEW)`
3. 秒杀活动表 (promo)
```sql
CREATE TABLE `promo` (
    `id` int NOT NULL AUTO_INCREMENT,
    `promo_name` varchar(255) NOT NULL DEFAULT '' COMMENT '秒杀活动名称',
    `start_date` datetime NOT NULL COMMENT '秒杀活动的开始时间',
    `end_date` datetime NOT NULL COMMENT '秒杀活动的结束时间',
    `item_id` int NOT NULL DEFAULT '0' COMMENT '秒杀活动的适用商品',
    `promo_item_price` decimal(10,2) NOT NULL DEFAULT '0' COMMENT '秒杀活动的商品价格',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
```
---
