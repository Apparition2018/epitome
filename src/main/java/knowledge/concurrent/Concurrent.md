# 并发编程
  
---
## 参考网站
1. [透彻理解 Java 并发编程](https://segmentfault.com/blog/ressmix_multithread)
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
2. Redis：@see RedisDistributedLocks
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
1. 秒杀业务的核心：库存的处理
    - ”减库存“和“记录购买明细“组成事务
    - 购买行为：①谁购买了；②成功的时间/有效期；③付款/发货信息
2. 秒杀的难点：高效的处理“竞争”
    - MySQL：事务+行级锁
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