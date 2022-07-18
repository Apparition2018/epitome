# Redis
![Redis 思维导图](https://img2018.cnblogs.com/blog/967517/201904/967517-20190417162215588-320188667.png)
- 基于内存的 key-value store 非关系型数据库
---
## Reference
1. [Redis](https://redis.io/)
2. [Redis | Github](https://github.com/redis/redis)
3. [Redis入门视频教程-慕课网](https://www.imooc.com/learn/839)
4. [Redis 教程 | 菜鸟教程](https://www.runoob.com/redis/redis-tutorial.html)
5. [Redis 教程 | 冯佳兴](https://blog.csdn.net/fjxcsdn/category_8935622.html)
6. [Redis Desktop Manager](https://www.jianshu.com/p/ccc3ebe29f7b)
7. [windows 下安装 redis 并设置自启动](https://www.cnblogs.com/yunqing/p/10605934.html)
---
## Interview
1. [JavaGuide](https://github.com/Snailclimb/JavaGuide/blob/main/docs/database/redis/redis-questions-01.md)
2. [Aobing](https://mp.weixin.qq.com/s/vXBFscXqDcXS_VaIERplMQ)
---
## [Redis 多快，为什么快](https://mmbiz.qpic.cn/mmbiz_png/g6hBZ0jzZb0Zb0XiaaR6bGaN80wicXIIP735YhoW1fic47MuJOx0HheBX4ficULcmdHhdGQnqGcfCgvunMmxpb8LnA/640)
- 官方：Redis 的瓶颈通常是内存或网络，而不是 CPU；查询 QPS 达 10w/s
1. 基于内存：①内存读写比磁盘快；②省去将数据从磁盘读到内存的时间
2. 高效的数据结构
    - 简单动态字符串(SDS)：String
    - 双端链表：List
    - 压缩链表：List, Hash, Sorted Set
    - 字典：Hash, Set
    - 跳跃表：Sorted Set
3. 单线程：执行命令单线程
    - [多线程的问题](https://blog.csdn.net/dfsdwes/article/details/25159417) ：线程开销，上下文切换，锁问题等
    - 多核 CPU 可启动多个 Redis 发挥多核性能
    - 多线程：
        - 4.0：异步删除，如 `UNLINK key [key ...]`，`FLUSHDB ASYNC`，`FLUSHALL ASYNC` 等
        - 6.0：增强对 IO 读写的并发能力，默认禁用
4. IO 多路复用：让单个线程高效处理多个请求，减少网络 IO 时间消耗（多路指多个 socket，复用指同一个线程）
>- [IT界农民工](https://mp.weixin.qq.com/s/b_yzbLeQh57oYjqlIgPiYQ)
>- [捡田螺的小男孩](https://mp.weixin.qq.com/s/wf08G3PHpfbJKiU7ZVO9Lw)
>- [Hollis](https://mp.weixin.qq.com/s/mscKInWNAuhCbg183Um9_g)
---
## [数据类型及其使用场景](https://redis.io/topics/data-types-intro)
- 通用场景：缓存

| 数据类型        | 使用场景                                |
|:------------|:------------------------------------|
| String      | 计数器（数量统计、数量控制）、时效信息（验证码）、自增 ID、分布式锁 |
| List        | 简单队列、定时排行榜、最近/最新                    |
| Set         | 不重复数据、交集/并集                         |
| Sorted Set  | 实时排行榜                               |
| Hash        | 对象缓存、条件查询（Lua）                      |
| Bitmap      | 保存状态并需进一步分析（活跃用户，在线用户）              |
| HyperLogLog | 基数统计                                |
| Stream      | 消息队列                                |
| Geo         | 地理位置                                |
>- [参考网站1](https://zhuanlan.zhihu.com/p/100460843)
>- [参考网站2](https://zhuanlan.zhihu.com/p/263390414)
---
## 文件事件处理器
![Redis 文件事件处理器过程](https://img2020.cnblogs.com/blog/1323607/202006/1323607-20200613165540238-1561612908.png)

| 部分        | 说明  |
|:----------|:----|
| 套接字       ||
| IO 多路复用程序 ||
| 文件事件分派器   ||
| 事件处理器     ||
---
## 删除过期 KEY 的策略
| 策略  | 说明                                                 | Redis 使用 |
|:----|:---------------------------------------------------|:---------|
| 定期  | 每隔一段时间（默认100ms）删除到期 KEY<br/>检查多少数据库和删除多少 KEY，由算法决定 | √        |
| 惰性  | 查询 KEY 时，判断是否过期，过期则删除                              | √        |
| 定时  | 设置过期时间的同时，创建一个定时器，当 KEY 到期时立即删除                    ||
---
## [内存淘汰策略](https://redis.io/topics/lru-cache)
- redis.conf：`maxmemory-policy noeviction`
- lru：Least Recently Used
- lfu：Least Frequently Used
- ttl：Time to Live

| 策略              | 说明                             |
|:----------------|:-------------------------------|
| volatile-lru    | 在已设置过期时间的 KEY 中，删除最近最少使用的 KEY  |
| volatile-lfu    | 在已设置过期时间的 KEY 中，删除最不常用的 KEY    |
| volatile-random | 在已设置过期时间的 KEY 中，随机删除 KEY       |
| volatitle-ttl   | 在已设置过期时间的 KEY 中，删除剩余过期时间最短 KEY |
| allkeys-lru     | 在所有 KEY 中，删除最近最少使用的 KEY        |
| allkeys-lfu     | 在所有 KEY 中，删除最不常用的 KEY          |
| allkeys-random  | 在所有 KEY 中，随机删除 KEY             |
| noeviction      | 不删除任何 KEY，在写操作时返回错误            |
---
## [持久化机制](https://redis.io/topics/persistence)

| 机制  | RDB                                                  | AOF                                                      |
|:----|:-----------------------------------------------------|:---------------------------------------------------------|
| 全称  | Redis Database                                       | Append Only File                                         |
| 说明  | 数据集快照                                                | 记录写操作                                                    |
| 优点  | ①文件小且只有一个，适合备份、灾难恢复<br/>②fork() 子进程进行保存工作<br/>③启动速度快 | ①更可靠，最多丢失一秒钟的数据<br/>②redis-check-aof</br>③能通过删除误操作命令恢复数据 |
- redis.conf：
```
# RDB 持久化文件名
dbfilename dump.rdb
# seconds 秒后至少有 changes 个键改变时，进行快照保存
# save "" 表示完全禁用快照
save <seconds> <changes>

# 是否开启 AOF 持久化
appendonly yes
# AOF 持久化文件名
appendfilename "appendonly.aof"
# 同步策略
appendfsync always | everysec | no
# 自动重写 AOF 文件
auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb
```
- 混合持久化：RDB + AOF
    - 配置：`aof-use-rdb-preamble yes`
    - 只发生于 AOF rewrite，重写后 AOF 文件前面是 RDB 格式的全量数据，后面是 AOF 格式的增量数据
---
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
## 数据库和缓存一致性问题
1. 更新缓存 or 删除缓存？：推荐删除缓存
    - 写写并发：无论是先更新缓存还是后更新缓存，都会出现数据不一致的问题，而删除缓存则不会
    - 读写并发：更新缓存和删除缓存都存在数据不一致的问题
2. 先删除缓存 or 后删除缓存？：推荐后删除缓存
    - a写b读：
        - 先删除缓存：
            1. a删除x缓存
            2. b读取x缓存不存在，读取x数据库值1
            3. b更新x缓存值1
            4. a更新x数据库值2
            5. 无论3和4谁先执行，数据库都是新值，缓存都是旧值，b读到旧值
            - 注：可以使用延迟双删解决
        - 后删除缓存：
            1. a更新x数据库值2
            2. b读取x缓存值1
            3. a删除x缓存
            4. 数据库是新值，缓存无值，b读到旧值
            - 注：加锁，保证a更新数据库和删除缓存串行执行
    - a读b写：
        1. a读取x缓存不存在，读取x数据库值1
        2. b更新x数据库值2 (缓存不存在，所以不考虑先删除还是后删除缓存)
        3. a更新x缓存值1
        4. 数据库是新值，缓存是旧值，a读到旧值
        - 注：出现概率不高，因为缓存操作+读数据库通常要快于写数据库
3. 删除缓存失败怎么办？：①异步重试；②MySQL binlog (阿里 canal)
4. [常用的缓存模式](https://blog.csdn.net/z69183787/article/details/112308815)
    1. Cache Aside：旁路缓存模式
        - 读：命中缓存则直接返回数据；未命中缓存则查询数据库数据，并将数据更新至缓存，然后返回数据
        - 写：先更新数据库，再删除缓存
        - ![Cache Aside](https://mmbiz.qpic.cn/mmbiz_jpg/j3gficicyOvaulG1WsztCXujT0qDxALxwEy8knbFcMB7NoiajEYjxo04ww5kmKIOyicbnpbMD0kz3N57EzT4H46xkQ/640) 
    2. Read Through
        - 读：访问控制层（同 Cache Aside）
        - ![Read Through](https://mmbiz.qpic.cn/mmbiz_jpg/j3gficicyOvaulG1WsztCXujT0qDxALxwEMibwZEPeCZE1icibze89CLf9h8aPkgcJ0NUWlvJjd0IcMa6Yib4ziamibic1w/640) 
    3. Write Through
        - 写：访问控制层（先更新数据库，再更新缓存，加锁)
        - ![Write Through](https://mmbiz.qpic.cn/mmbiz_jpg/j3gficicyOvaulG1WsztCXujT0qDxALxwEbcBG5HIc4pohTLg9sMgRNDlAR3xPmYHS2HlMbz3Gv57bTeRJsmicKAA/640) 
    4. Write Behind
        - 写：访问控制层（先更新缓存，后异步更新数据库）
        - ![Write Behind](https://mmbiz.qpic.cn/mmbiz_jpg/j3gficicyOvaulG1WsztCXujT0qDxALxwEfl7FaXmIwmgNPoUOwib2tSWykFoZicIpiaxbHPIpLC51VKyFazLkKEaeQ/640) 
>- [腾讯技术工程](https://mp.weixin.qq.com/s/Y9S89MT0uAobzRKgYVrI9Q)
>- [苏三说技术](https://mp.weixin.qq.com/s/4hP-T0h8QPyjcpH8m0cbsA)
>- [水滴与银弹](https://mp.weixin.qq.com/s/4W7vmICGx6a_WX701zxgPQ)
>- [小林coding](https://mp.weixin.qq.com/s/sh-pEcDd9l5xFHIEN87sDA)
---
## [Redis 计数器并发精准数量控制](https://www.imooc.com/learn/1067)
![redis 数量控制并发问题](https://img1.mukewang.com/6092cf44000167a319201080-500-284.jpg)
![redis 数量控制并发优化](https://img4.mukewang.com/6092d0560001c11019201080-500-284.jpg)
---
## [配置](https://github.com/redis/redis/blob/unstable/redis.conf)
```
# 设置客户端密码
requirepass password
# 监听 loopback IPv4 和 IPv6
# docker 下注释掉
bind 127.0.0.1 -::1
# 是否开启保护模式
# docker 下设置为 no
protected-mode yes
# 是否守护线程运行
# docker 下设置为 no，因为 docker run -d 已经是后台启动 
daemonize no
# 持久化文件存储目录
dir ./
# 包含其它配置文件
include other.conf
```
---
## [命令](https://www.redis.io/commands)
- [Try Redis](https://try.redis.io/)
- 连接远程 redis：redis-cli -h host -p port -a password
- Key
```
KEYS pattern[?|*]                                   查看
DEL key [key ...]                                   删除
EXISTS key [key ...]                                判断是否存在
RENAME key newkey                                   重命名
TYPE key                                            返回类型
EEXPIRE key seconds [NX|XX|GT|LT]                   设置过期时间，秒
EXPIREAT key unix-time-seconds [NX|XX|GT|LT]        设置过期时间点，时间戳
PEXPIRE key milliseconds [NX|XX|GT|LT]              设置过期时间，毫秒
PEXPIREAT key unix-time-milliseconds [NX|XX|GT|LT]  设置过期时间点，时间戳
TTL key                                             返回生存时间，秒
PERSIST key                                         持久保持
MOVE key db                                         移动
```
- String
```
SET key value [command]                             设置
SETNX key value                                     不存在才设置
SETRANGE key offset value                           替换
SETBIT key offset value                             设置 bit
GETSET key value                                    设置，并获取旧值
MSET key value [key value ...]                      批量设置
GET key                                             获取
GETRANGE key start end                              截取
GETBIT key offset                                   获取 bit
MGET key [key ...]                                  批量获取
APPEND key value                                    追加
INCR key                                            增一
DECR key                                            减一
INCRBY key increment                                增加
DECRBY key increment                                减少
STRLEN key                                          长度
```
- Hash
```
HSET key field value [field value ...]              设置
HMSET key field value [field value ...]             批量设置
HSETNX key field                                    不存在才设置
HGET key field                                      获取
HMGET key field [field ...]                         批量获取
HGETALL key                                         获取所有键值
HKEYS key                                           获取所有键
HVALS key                                           获取所有值
HDEL key field [field ...]                          删除
HEXISTS key field                                   判断是否存在
HINCRBY key field increment                         增加
HLEN key                                            长度
```
- List
```
RPUSH key element [element ...]                     添加
LPUSH key element [element ...]                     头部添加
RPUSHX key element [element ...]                    存在才添加
LPUSHX key element [element ...]                    存在才头部添加
LINSERT key BEFORE|AFTER pivot element              在元素前或后添加
LSET key index element                              索引设置
RPOPLPUSH source destination                        移除最后并添加到另外最后
LRANGE key start stop                               获取
  LRANGE key 0 -1                                   获取全部
LINDEX key index                                    索引获取
RPOP key [count]                                    移除
LPOP key [count]                                    头部移除
BRPOP key [key ...] timeout                         阻塞移除
BLPOP key [key ...] timeout                         阻塞头部移除
LREM key count element                              删除
LTRIM key start stop                                范围删除
LLEN key                                            长度
```
- Set
```
SADD key member [member ...]                        添加
SMEMBERS key                                        获取
SRANDMEMBER key [count]                             随机获取
SPOP key [count]                                    随机移除
SREM key member [member ...]                        删除
SISMEMBER key member                                判断是否存在
SCARD key                                           长度
SUNION key [key ...]                                并集
SUNIONSTORE destination key [key ...]               并集并另外存储
SINTER key [key ...]                                交集
SINTERSTORE destination key [key ...]               交集并另外存储
SDIFF key [key ...]                                 差异
SDIFFSTORE destination key [key ...]                差异并另外存储
```
- Sorted SET
```
ZADD key [command] score member [score member ...]  添加
ZRANGE key min max [command]                        获取
  ZRANGE key 0 -1                                   获取全部
ZRANGEBYSCORE key min max [command]                 获取按分数排名
ZREVRANGE key start stop [WITHSCORES]               倒叙获取
ZSCORE key member                                   获取分数
ZRANK key mebmer                                    获取排名
ZUNIONSTORE destination numkeys key [key ...]       并集并另外存储
ZREM key member [member ...]                        删除
ZREMRANGEBYRANK key start stop                      删除排名区间成员
ZREMRANGEBYSCORE key min max                        删除分数区间成员
ZINCRBY key increment member                        增加分数
ZCARD key                                           长度
ZCOUNT key min max                                  计算区间成员个数
```
- 连接
```
AUTH [usename] password                             验权
SELECT index                                        切换数据库
```
- 事务
```
MULTI                                               开启事务                                             
EXEC                                                执行所有事务块内的命令
DISCARD                                             取消事务
```
- 服务器
```
INFO [section]                                      返回关于 Redis 服务器的各种信息和统计数值
SAVE                                                同步保存到 .rdb 文件
BGSAVE [SCHEDULE]                                   异步保存到 .rdb 文件
DBSIZE                                              当前库 key 数量
FLUSHALL [ASYNC|SYNC]                               冲洗所有数据库 key
FLUSHDB [ASYNC|SYNC]                                冲洗当前库所有 key
```
---
