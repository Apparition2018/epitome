# Interview

---
# Redis
- @see Redis.md
>- [参考网站1](https://github.com/Snailclimb/JavaGuide/blob/main/docs/database/redis/redis-questions-01.md)
>- [参考网站2](https://mp.weixin.qq.com/s/vXBFscXqDcXS_VaIERplMQ)
## Redis 多快，为什么快
- 官方：Redis 的瓶颈通常是内存或网络，而不是 CPU；查询 QPS 达 10w/s
1. 完全基于内存：内存操作快，省去将数据从磁盘读到内存的时间
2. 专门设计的数据结构：简单动态字符串
3. 单线程：执行命令单线程
    - [多线程的问题](https://blog.csdn.net/dfsdwes/article/details/25159417) ：线程开销，上下文切换，锁问题等
    - 多核 CPU 可启动多个 Redis 发挥多核性能
    - 多线程：
        - 4.0：异步删除，如 `UNLINK key [key ...]`，`FLUSHDB ASYNC`，`FLUSHALL ASYNC` 等
        - 6.0：增强对 IO 读写的并发能力，默认禁用
4. IO 多路复用：让单个线程高效处理多个请求，减少网络 IO 时间消耗（多路指多个 socket，复用指同一个线程）
>- [参考网站1](https://www.cnblogs.com/caohongchang/p/13285948.html)
>- [参考网站2](https://mp.weixin.qq.com/s/mscKInWNAuhCbg183Um9_g)
## 数据类型及其使用场景
- 通用场景：缓存

|数据类型|使用场景|
|:---|:---|
|String|计数器（数量统计、数量控制）、时效信息（验证码）、自增 ID、分布式锁|
|Hash|对象缓存、条件查询（Lua）|
|List|简单队列、定时排行榜、最近/最新|
|Set|不重复数据、交集/并集|
|Sorted Set|实时排行榜|
|HyperLogLog|基数统计|
|Geo|地理位置|
|Bitmap|保存状态并需进一步分析（活跃用户，在线用户）|
|Stream|消息队列|
>- [参考网站1](https://zhuanlan.zhihu.com/p/100460843)
>- [参考网站2](https://zhuanlan.zhihu.com/p/263390414)
## 文件事件处理器
![Redis 文件事件处理器过程](https://img2020.cnblogs.com/blog/1323607/202006/1323607-20200613165540238-1561612908.png)

|部分|说明|
|:---|:---|
|套接字||
|IO 多路复用程序||
|文件事件分派器||
|事件处理器||
## 删除过期 KEY 的策略
|策略|说明|Redis 使用|
|:---|:---|:---|
|定期|每隔一段时间（默认100ms）删除到期 KEY<br/>检查多少数据库和删除多少 KEY，由算法决定|√|
|惰性|查询 KEY 时，判断是否过期，过期则删除|√|
|定时|设置过期时间的同时，创建一个定时器，当 KEY 到期时立即删除||
## 内存淘汰策略
- redis.conf：`maxmemory-policy noeviction`
- lru：Least Recently Used
- lfu：Least Frequently Used
- ttl：Time to Live

|策略|说明|
|:---|:---|
|volatile-lru|在已设置过期时间的 KEY 中，删除最近最少使用的 KEY|
|volatile-lfu|在已设置过期时间的 KEY 中，删除最不常用的 KEY|
|volatile-random|在已设置过期时间的 KEY 中，随机删除 KEY|
|volatitle-ttl|在已设置过期时间的 KEY 中，删除剩余过期时间最短 KEY|
|allkeys-lru|在所有 KEY 中，删除最近最少使用的 KEY|
|allkeys-lfu|在所有 KEY 中，删除最不常用的 KEY|
|allkeys-random|在所有 KEY 中，随机删除 KEY|
|noeviction|不删除任何 KEY，在写操作时返回错误|
## [持久化机制](https://segmentfault.com/a/1190000016021217)
|机制|RBD|AOF|混合持久化|
|:---|:---|:---|:---|
|说明|快照。|||
|配置||||
|优点||||
|缺点||||
## 缓存雪崩、击穿、穿透
1. 雪崩：
    1. 大量缓存同一时间过期
        - 打散过期时间：随机数
        - 加互斥锁：分布式锁、JVM 锁均可（Key 维度枷锁?）
        - 热点数据不过期
    2. Redis 故障
        - 服务熔断
        - 请求限流
        - 高可用
2. 击穿：某个缓存在过期的瞬间有大量请求（热点数据）
    - 加互斥锁
    - 热点数据不过期
3. 穿透：访问一个缓存和数据库都不存在的数据
    - 接口校验：用户鉴权、参数校验
    - 缓存空值或默认值：设置较短过期时间
    - 布隆过滤器
>- [参考网站1](https://zhuanlan.zhihu.com/p/359118610)
>- [参考网站2](https://mp.weixin.qq.com/s/_StOUX9Nu-Bo8UpX7ThZmg)
>- [参考网站3](https://mp.weixin.qq.com/s/knz-j-m8bTg5GnKc7oeZLg)
---
## [Zookeeper](https://www.cnblogs.com/ultranms/p/9602474.html)
- @see Zookeeper.md
