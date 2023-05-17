# Redis
![Redis 思维导图](https://img2018.cnblogs.com/blog/967517/201904/967517-20190417162215588-320188667.png)
- 基于内存的 key-value store 非关系型数据库
---
## Reference
1. [Redis](https://redis.io/)
2. [GitHub](https://github.com/redis/redis)
3. [菜鸟教程](https://www.runoob.com/redis/redis-tutorial.html)
4. [Redis Desktop Manager](https://www.jianshu.com/p/ccc3ebe29f7b)
5. [windows 下安装 redis 并设置自启动](https://www.cnblogs.com/yunqing/p/10605934.html)
---
## Author
1. [JavaGuide](https://github.com/Snailclimb/JavaGuide/tree/main/docs/database/redis)
2. [敖丙](https://mp.weixin.qq.com/s/vXBFscXqDcXS_VaIERplMQ)
3. [小林coding](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzUxODAzNDg4NQ==&action=getalbum&album_id=1790401816640225283)
4. [码哥字节](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzkzMDI1NjcyOQ==&action=getalbum&album_id=1918295695426404359)
5. [我没有三颗心脏](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=Mzg5MzU2NDgyNw==&action=getalbum&album_id=1710550712134189060)
---
## 规范
1. [Redis开发运维实战 | 付磊](https://mp.weixin.qq.com/s/BO3wrKjvO52XqyQIBT-n2g)
2. [付磊](https://developer.aliyun.com/article/531067)
3. [码哥字节](https://mp.weixin.qq.com/s/zI5jJapggUURWcpPVB8IVg)
4. [掘金开发者社区 | 小姐姐味道](https://mp.weixin.qq.com/s/-nZ08zGTYNS1b1ep-dDwRQ)
---
## [Redis 多快，为什么快](https://mmbiz.qpic.cn/mmbiz_png/g6hBZ0jzZb0Zb0XiaaR6bGaN80wicXIIP735YhoW1fic47MuJOx0HheBX4ficULcmdHhdGQnqGcfCgvunMmxpb8LnA/640)
- 官方：Redis 的瓶颈通常是内存或网络，而不是 CPU；查询 QPS 达 10w/s
1. 基于内存：①内存读写比磁盘快；②省去将数据从磁盘读到内存的时间
2. 高效的数据结构

| 中文      | 英文                     | 数据类型                   |
|:--------|:-----------------------|:-----------------------|
| 简单动态字符串 | Simple Dynamic Strings | String                 |
| 双端链表    | LinkedList             | List                   |
| 压缩链表    | ZipList                | List, Hash, Sorted Set |
| 字典      | HashTable              | Hash, Set              |
| 跳跃表     | SkipList               | Sorted Set             |
3. 合理的数据编码

| 数据类型       | 编码方式                                                |
|:-----------|:----------------------------------------------------|
| String     | 数字用 Int 编码，<=39字节的字符串用 EmbStr 编码，>39字节用 Raw 编码      |
| List       | 元素小于512个，且每个元素小于64字节，用 ZipList 编码，否则用 LinkedList 编码 |
| Hash       | 元素小于512个，且每个元素小于64字节，用 ZipList 编码，否则用 HashTable 编码  |
| Set        | 元素小于512个，且都是整数，用 IntSet 编码，否则用 HashTable 编码         |
| Sorted Set | 元素小于128个，且每个元素小于64字节，用 ZipList 编码，否则用 SkipList 编码   |
4. 合理的线程模型
    - 文件事件处理器：基于 Reactor 模式开发的一套事件处理机制，是单线程运行的
    ![文件事件处理器](https://mp.weixin.qq.com/s/apScwfXHWlh8xUS1RhMSIA)
        - IO 多路复用：让单个线程高效处理多个请求，减少网络 IO 时间消耗
    - 为什么设计成单线程：因为 Redis 键值对读写是基于内存的，所以不需要多线程来提高 CPU 的利用率
        - 多核 CPU 可启动多个 Redis 发挥多核性能
    - [多线程带来的问题](https://blog.csdn.net/dfsdwes/article/details/2515941) ：线程开销，上下文切换，同步等问题
    - 引入多线程
        - 2.6：①BIO_CLOSE_FILE（关闭文件）；②BIO_AOF_FSYNC（AOF 刷盘）
        - 4.0：BIO_LAZY_FREE（异步释放内存）
            - `UNLINK key [key ...]`：`DEL key [key ...]` 的异步版本
            - `FLUSHDB ASYNC`
            - `FLUSHALL ASYNC`
        - 6.0：多个 IO 线程，默认禁用
            - redis.conf：`io-threads-do-reads yes`，`io-threads 8`
5. [VM 机制](https://mp.weixin.qq.com/s/N1NR-LgiCvAOMxgbdICziQ)
>- [IT界农民工 | 莱乌](https://mp.weixin.qq.com/s/b_yzbLeQh57oYjqlIgPiYQ)
>- [捡田螺的小男孩](https://mp.weixin.qq.com/s/wf08G3PHpfbJKiU7ZVO9Lw)
>- [小林coding](https://mp.weixin.qq.com/s/apScwfXHWlh8xUS1RhMSIA)
---
## [数据类型](https://redis.io/docs/manual/data-types/)
| 数据类型        | 使用场景                                                        |
|:------------|:------------------------------------------------------------|
| String      | 缓存、计数器（数量统计（阅读量）、数量控制（限流））、时效信息（验证码）、全局 ID、分布式 session、分布式锁 |
| List        | 简单队列、定时排行榜、最近/最新                                            |
| Set         | 唯一数据（标签、关注、点赞）、集合操作（共同好友、商品筛选）、抽奖（SPOP）                     |
| Sorted Set  | 排序（实时排行榜）                                                   |
| Hash        | 对象缓存（购物车）、条件查询（Lua）                                         |
| Bitmap      | 二值状态统计（活跃用户，在线用户）                                           |
| HyperLogLog | 基数统计（UV（Unique Visitor） 统计）                                 |
| Stream      | 消息队列                                                        |
| Geo         | 地理位置                                                        |
>- [Redis 数据结构详解](https://zhuanlan.zhihu.com/p/100460843)
>- [Redis 常见使用场景](https://mp.weixin.qq.com/s/W5T_a_EQhxHOI0lfgO3r0g)
>- [HyperLogLog | 我没有三颗心脏](https://mp.weixin.qq.com/s/NPT5ONV1soYy8oFh4sGLsg)
---
## [配置](https://redis.io/docs/manual/config/)
```
# 设置客户端密码
# requirepass password
# bind 监听主机的网卡 IP
# bind 127.0.0.1 -::1，监听回送 IPv4 和 IPv6 地址
# bind 0.0.0.0，监听所有 IPv4 地址
bind 127.0.0.1 -::1
# 是否开启保护模式；如果没有设置 requirepass，只接受回送地址的连接
protected-mode yes
# 是否守护线程运行
# docker 下设置为 no，因为 docker run -d 已经是后台启动 
daemonize yes
# 持久化文件存储目录
dir ./
# 包含其它配置文件
# include /path/to/other.conf
```
- 通过命令行传递参数：使用 `--` 前缀，如：`./redis-server --port 6380 --replicaof 127.0.0.1 6379`
- 运行时修改配置：`CONFIG SET`，`CONFIG REWRITE`
---
## [键驱逐](https://redis.io/docs/manual/eviction/)
- 过期删除策略

| 策略  | 说明                                                  | Redis 使用 |
|:----|:----------------------------------------------------|:---------|
| 定时  | 设置过期时间的同时，创建一个定时器，当 KEY 到期时立即删除                     ||
| 惰性  | 查询 KEY 时，判断是否过期，过期则删除                               | √        |
| 定期  | 每隔一段时间（默认100ms）删除到期 KEY<br/>检查多少个数据库和删除多少 KEY，由算法决定 | √        |
- redis.conf
    - `maxmemory <bytes>` 
    - `maxmemory-policy <policy>`
- [驱逐策略](https://redis.io/docs/manual/eviction/)
    - lru：Least Recently Used
    - lfu：Least Frequently Used
    - ttl：Time to Live

| 策略              | 说明                             |
|:----------------|:-------------------------------|
| volatile-lru    | 在已设置过期时间的 KEY 中，删除最近最少使用的 KEY  |
| volatile-lfu    | 在已设置过期时间的 KEY 中，删除最不常用的 KEY    |
| volatile-random | 在已设置过期时间的 KEY 中，随机删除 KEY       |
| volatile-ttl    | 在已设置过期时间的 KEY 中，删除剩余过期时间最短 KEY |
| allkeys-lru     | 在所有 KEY 中，删除最近最少使用的 KEY        |
| allkeys-lfu     | 在所有 KEY 中，删除最不常用的 KEY          |
| allkeys-random  | 在所有 KEY 中，随机删除 KEY             |
| noeviction      | 不删除任何 KEY，在写操作时返回错误            |
---
## [持久化](https://redis.io/topics/persistence)
1. RDB：Redis Database，指定时间间隔执行的数据集快照
    - 优点
        1. 压缩的二进制单文件，按时间点备份，适合灾难恢复
        2. 父进程 fork() 一个子进程完成持久化工作
        3. 对比 AOF 更快的大数据集重启速度
        4. 在 Redis 复制，RDB 支持重启和故障转移后的部分重新同步
    - redis.conf
        ```
        # RDB 持久化文件名
        dbfilename dump.rdb
        # seconds 秒后至少有 changes 个键改变时，进行快照保存
        # save "" 表示完全禁用快照
        save <seconds> <changes>
        ```
    - commands：`SAVE`, `BGSAVE [SCHEDULE]`
2. AOF：Append Only File，记录每个写入操作
    - 优点
        1. fsync 策略：`everysec`（每秒）、`always`（每个命令），所以使用 AOF 最多丢失一秒的写入 
        2. append-only 日志，即使由于某些原因（磁盘已满）日志以写一半的命令结束，可使用 redis-check-aof 工具修复
        3. AOF 变得太大时，后台将自动重写 AOF
        4. 能通过删除误操作命令恢复数据
    - redis.conf
        ```
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
3. RDB + AOF
    - redis.conf：`aof-use-rdb-preamble yes`
    - 发生于重写 AOF，重写后 AOF 文件前面是 RDB 格式的全量数据，后面是 AOF 格式的增量数据
---
## [复制](https://redis.io/docs/manual/replication/)
- redis.conf
```
# 5.0 弃用 slaveof
replicaof <masterip> <masterport>
# master 密码
masterauth <master-password>
# 是否使用 socket 策略
# 两种复制同步策略：disk | socket
# disk：master → RDB → disk → replicas
# socket：maseter → RDB → replicas socket，慢磁盘高带宽推荐使用
repl-diskless-sync yes
# socket 同步延迟秒数
repl-diskless-sync-delay <seconds>
# 副本是否只读
replica-read-only yes
# 延迟小于等于 M 秒的 replicas 小于 N 个，master 停止接受写操作
min-replicas-to-write N
min-replicas-max-lag M
# 副本优先级，越小优先级越高，0表示永不会提升为master
replica-priority 100
```
- 三种主要机制
    1. 连接良好时：master 向 replica 发送命令流
    2. 连接中断时：replica 会重新连接并尝试进行部分重新同步，仅获取在断开连接期间错过的命令流
    3. 无法进行部分重新同步时：将请求完全重新同步，master 创建所有数据的快照发送到 replica，然后继续发送命令流
- [工作原理](https://redis.io/docs/manual/replication/#how-redis-replication-works)
    - [replication ID](https://redis.io/docs/manual/replication/#replication-id-explained)：伪随机字符串，标记数据集的给定历史
    - replication offset：随复制流的每个字节而增加，即使没有连接 replica，也会增加
    - master 发送命令流：replicas 使用 `PSYNC` 命令向 master 发送旧 replication ID 和 offset
    - 完全重新同步：master buffers 没有足够的 backlog，或 replica 引用旧的 replication ID
        1. master 后台生成 RDB 文件，同时缓冲新的写命令
        2. master 传输 RDB 文件到 replica，replica 将其保存在磁盘上，然后加载到内存
        3. master 发送缓冲命令到 replica，
- [keys 过期](https://redis.io/docs/manual/replication/#how-redis-replication-deals-with-expires-on-keys)
    1. replicas key 不会过期，master key 过期或被驱逐时，会传输 `DEL` 到 replicas
    2. 当 replicas 存在已经过期的 key 时，replica 会使用逻辑时钟来报告 key 不存在
    3. Lua 脚本执行期间，不会执行 key 过期
- 相关命令：`INFO [section [section ...]]`，`ROLE`
>- [Java知音 | JingQ](https://mp.weixin.qq.com/s/9DzAv6jRtNyKi7Q8ShQBhA)
>- [民工哥技术之路 | 二叉树](https://mp.weixin.qq.com/s/IvrODQ2PYsNp-RQr6XkK8g)
---
## [哨兵](https://redis.io/docs/manual/sentinel/)
- sentinel.conf
```
# sentinel monitor
# 当<quorum>个 Sentinel 同时同意 master 不可访问时，其中一个会尝试启动故障转移 
sentinel monitor <master-group-name> <ip> <port> <quorum>
# sentinel <option_name> <master_name> <option_value>
sentinel down-after-milliseconds master 5000
sentinel failover-timeout master 60000
sentinel parallel-syncs master 1
#
sentinel announce-ip <ip>
sentinel announce-port <port>
```
- 功能
    1. 监控：Monitoring
    2. 通知：Notification
    3. 自动故障转移：Automatic failover
    4. 配置提供者：Configuration provider
- 选主
    1. 过滤掉已经离线的节点
    2. 过滤掉历史网络连接状态不好的节点
    3. 剩下的节点进行3轮考察
        1. 优先级：`replica-priority` 哪个最小
        2. 复制进度：slave_repl_offset 哪个最接近 master_repl_offset
        3. ID：ID 哪个最小
- 选举执行故障转移的 sentinel
    - 注：哨兵数应该是奇数，quorum 值建议为 (哨兵数/2)+1
    1. 得票数 > 哨兵数的一半
    2. 得票数 >= quorum
- [命令](https://redis.io/docs/manual/sentinel/#sentinel-commands)
    - [运行时重新配置 Sentinel](https://redis.io/docs/manual/sentinel/#reconfiguring-sentinel-at-runtime)
        - `SENTINEL SET`：修改 Master-specific 配置参数
        - `SENTINEL CONFIG SET`：修改全局配置参数
    - 查询 master 状态
        - `SENTINEL masters`：显示受监控的 masters 及其状态
        - `SENTINEL master <master_name>`：显示指定 master 的状态和信息
        - `SENTINEL replicas <master_name>`：显示指定 master 的 replicas 及其状态
        - `SENTINEL sentinels <master_name>`：显示指定 master 的 sentinels 及其状态
>- [小林coding](https://mp.weixin.qq.com/s/HJHNq3MOSJD_C0cPAFz_Iw)
>- [小林coding](https://mp.weixin.qq.com/s/DzVY4iA-_my0JUSqOOniOw)
---
## [集群](https://redis.io/docs/manual/scaling/)

---
## [事务](https://redis.io/docs/manual/transactions/)
- 命令
    - `MULTI` 开启事务，`EXEC` 执行所有事务块内的命令，`DISCARD` 取消事务
    - `WATCH` 监视，`UNWATCH` 取消监视
- Redis 不支持事务回滚，因为支持回滚会对 Redis 的简单性和性能产生重大影响
---
## [可编程性](https://redis.io/docs/manual/programmability/)
1. &gt;= 7：使用 Redis Functions 来管理和运行脚本
2. <= 6.2：使用 Lua 脚本和 EVAL 命令对服务器进行编程
---
## 慢查询日志
- redis.conf
```
# 命令执行时间超过多少毫秒时记录 slowlog
slowlog-log slower-than <microseconds>
# slowlog 记录最大条数
slowlog-max-length <length>
```
- 命令
    - `SLOWLOG GET [count]`：返回指定条数的慢查询，默认返回所有
    - `SLOWLOG LEN`：返回慢查询日志条数
    - `SLOWLOG RESET`：清除慢查询日志
---
## 缓存雪崩、击穿、穿透
1. 穿透 (Penetration)：访问一个缓存和数据库都不存在的数据
    - 接口校验：用户鉴权、参数校验
    - 缓存空值或默认值：设置较短过期时间
    - 布隆过滤器 @see BloomFilterDemo
2. 击穿 (Breakdown)：某个缓存在过期的瞬间有大量请求
    - 互斥锁或队列
    - 不设置过期时间：定时更新，或更新操作时更新
    - 多级缓存
3. 雪崩 (Avalanche)
    1. 多个缓存在同一时刻过期且有大量请求
        - 同击穿
        - 打散过期时间
    2. Redis 故障宕机
        - 熔断、降级、限流：Hystrix
        - 高可用：主从，哨兵，集群
>- [程序员囧辉](https://mp.weixin.qq.com/s/QX8UviH7iaxXTz-Io_7uZg)
>- [小林coding](https://mp.weixin.qq.com/s/_StOUX9Nu-Bo8UpX7ThZmg)
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
>- [水滴与银弹 | Magic Kaito](https://mp.weixin.qq.com/s/4W7vmICGx6a_WX701zxgPQ)
>- [小林coding](https://mp.weixin.qq.com/s/sh-pEcDd9l5xFHIEN87sDA)
---
## bigkey
- 什么是 bigkey
    1. String：size > 10KB
    2. 集合：元素个数 > 5000
- 危害
    1. 内存空间不均匀：不利于集群对内存的统一管理，存在丢失数据的隐患
    2. 请求倾斜：bigkey 使请求集中落在集群的某一个节点上
    3. 超时阻塞：Redis 单线程，且 bigkey 的操作通常比较耗时，会引起阻塞。如：过期删除
    4. 网络拥塞：每次获取 bigkey 产生的网络流量较大
    5. 迁移困难
- 发现：下列均建议在从节点本机执行
    1. `SCAN` + `MEMORY USAGE key [SAMPLES count]`：可代替 `SCAN` + `DEBUG object key`
    2. [`redis-cli --bigkeys`](https://redis.io/docs/manual/cli/#scanning-for-big-keys)：返回每种数据类型的 top 1 bigkey
    3. `info clients`：观察客户端 输入缓冲区的字节数 和 输出缓冲区的队列长度
    4. 修改客户端代码收集
    5. [redis-rdb-tools](https://github.com/sripathikrishnan/redis-rdb-tools)
    6. [阿里 Python 脚本扫描](https://developer.aliyun.com/article/117042)
- 优化
    1. 可删除
        1. < 4.0：`SCAN`，渐进式删除，
        2. &gt;= 4.0：`UNLINK`，惰性删除
    2. 不可删除：
        1. value 只存有用信息
        2. 压缩：大 str 压缩
        3. 拆分：大 str 拆分成小 str，`MGET` 获取；大集合拆分成小集合；大 hash 取模二次 hash；按日期拆分等
>- [Redis开发运维实战 | 付磊](https://mp.weixin.qq.com/s/LqyjZ0ZinI_JmPlSt4oa6Q)
>- [小林coding | 大白斯基](https://mp.weixin.qq.com/s/l3l9d9sLiWoUM381E9o-3Q)
>- [BiggerBoy | ITwalking](https://mp.weixin.qq.com/s/ruMfDiloAm9qev4C49bGYg)
---
## [CLI](https://redis.io/docs/manual/cli/)
- [Try Redis](https://try.redis.io/)
- 连接服务：redis-cli -h host -p port -a password
### [Commands](https://redis.io/commands/)
- [Generic](https://redis.io/commands/?group=generic)
```
KEYS pattern[?|*]                                   查看
DEL key [key ...]                                   删除
SCAN cursor [MATCH pattern] [COUNT count] [TYPE type]   迭代元素集合
EXISTS key [key ...]                                判断是否存在
RENAME key newkey                                   重命名
TYPE key                                            返回类型
EXPIRE key seconds [NX|XX|GT|LT]                    设置过期时间，秒
EXPIREAT key unix-time-seconds [NX|XX|GT|LT]        设置过期时间点，时间戳
PEXPIRE key milliseconds [NX|XX|GT|LT]              设置过期时间，毫秒
PEXPIREAT key unix-time-milliseconds [NX|XX|GT|LT]  设置过期时间点，时间戳
TTL key                                             返回生存时间，秒
PERSIST key                                         持久保持
MOVE key db                                         移动
```
- [String](https://redis.io/commands/?group=string)
```
SET key value [command]                             设置
SETNX key value                                     不存在才设置
SETRANGE key offset value                           替换
GETSET key value                                    设置，并获取旧值
MSET key value [key value ...]                      批量设置
GET key                                             获取
GETRANGE key start end                              截取
MGET key [key ...]                                  批量获取
APPEND key value                                    追加
INCR key                                            增一
DECR key                                            减一
INCRBY key increment                                增加
DECRBY key increment                                减少
STRLEN key                                          长度
```
- [Hash](https://redis.io/commands/?group=hash)
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
HSCAN key cursor [MATCH pattern] [COUNT count]      迭代
HEXISTS key field                                   判断是否存在
HINCRBY key field increment                         增加
HLEN key                                            长度
```
- [List](https://redis.io/commands/?group=list)
```
RPUSH key element [element ...]                     添加
LPUSH key element [element ...]                     头部添加
RPUSHX key element [element ...]                    存在才添加
LPUSHX key element [element ...]                    存在才头部添加
LINSERT key BEFORE|AFTER pivot element              在元素前或后添加
LSET key index element                              索引设置
LRANGE key start stop                               获取
  LRANGE key 0 -1                                   获取全部
LINDEX key index                                    索引获取
RPOP key [count]                                    移除
LPOP key [count]                                    头部移除
BRPOP key [key ...] timeout                         阻塞移除
BLPOP key [key ...] timeout                         阻塞头部移除
RPOPLPUSH source destination                        移除最后并添加到另一列表开头
LMOVE source destination LEFT | RIGHT LEFT | RIGHT  移除并添加到另一列表
LREM key count element                              删除
LTRIM key start stop                                范围删除
LLEN key                                            长度
```
- [Set](https://redis.io/commands/?group=set)
```
SADD key member [member ...]                        添加
SMEMBERS key                                        获取
SRANDMEMBER key [count]                             随机获取
SPOP key [count]                                    随机移除
SREM key member [member ...]                        删除
SSCAN key cursor [MATCH pattern] [COUNT count]      迭代
SISMEMBER key member                                判断是否存在
SCARD key                                           长度
SUNION key [key ...]                                并集
SUNIONSTORE destination key [key ...]               并集并另外存储
SINTER key [key ...]                                交集
SINTERSTORE destination key [key ...]               交集并另外存储
SDIFF key [key ...]                                 差异
SDIFFSTORE destination key [key ...]                差异并另外存储
```
- [Sorted Set](https://redis.io/commands/?group=sorted-set)
```
ZADD key [command] score member [score member ...]  添加
ZRANGE key min max [command]                        获取
  ZRANGE key 0 -1                                   获取全部
ZRANGEBYSCORE key min max [command]                 获取按分数排名
ZREVRANGE key start stop [WITHSCORES]               倒序获取
ZSCORE key member                                   获取分数
ZRANK key mebmer                                    获取排名
ZREVRANK key member                                 获取倒序排名
ZUNIONSTORE destination numkeys key [key ...]       并集并另外存储
ZREM key member [member ...]                        删除
ZSCAN key cursor [MATCH pattern] [COUNT count]      迭代
ZREMRANGEBYRANK key start stop                      删除排名区间成员
ZREMRANGEBYSCORE key min max                        删除分数区间成员
ZINCRBY key increment member                        增加分数
ZCARD key                                           长度
ZCOUNT key min max                                  计算区间成员个数
```
- [Stream](https://redis.io/commands/?group=stream)
```
XADD key field value [field value ...]              添加
XREAD id                                            读取，大于指定 ID
XRANGE key start end [COUNT count]                  获取，匹配指定 ID 范围
XDEL key id [id ...]                                删除
XLEN key                                            长度
XGROUP CREATE key groupname <id | $>                创建消费者组
XGROUP DESTROY key groupname                        销毁消费者组
XREADGROUP GROUP group consumer STREAMS key [key ...] id [id ...]
```
- [Geospatial](https://redis.io/commands/?group=geo)
```
GEOADD key longitude latitude member                添加
GEOPOS key member [member ...]                      获取
GEODIST key member1 member2 [M | KM | FT | MI]      距离
GEOHASH key member [member ...]                     GeoHash 字符串
GEORADIUS key longitude latitude radius             经纬度半径内
GEORADIUSBYMEMBER key member radius                 成员半径内
GEOSEARCH key                                       经纬度/成语 半径或边框内
GEOSEARCHSTORE destination source                   同上并存储到目标键
```
- [HyperLogLog](https://redis.io/commands/?group=hyperloglog)
```
PFADD key [element [element ...]]                   添加
PFCOUNT key [key ...]                               基数估计值
PFMERGE destkey sourcekey [sourcekey ...]           合并
```
- [Bitmap](https://redis.io/commands/?group=bitmap)
```
SETBIT key offset value                             设置
GETBIT key offset                                   获取
BITCOUNT key [ start end [ BYTE | BIT]]             计算 set bits 个数
BITOP operation destkey key [key ...]               按位运算：AND、OR、XOR、NOT
```
- [Pub/Sub](https://redis.io/commands/?group=pubsub)
```
SUBSCRIBE channel [channel ...]                     订阅通道
PSUBSCRIBE pattern [ pattern ...]                   订阅样式通道
UNSUBSCRIBE [channel [channel ...]]                 取消订阅通道
PUNSUBSCRIBE [pattern [pattern ...]]                取消订阅样式通道
PUBLISH channel message                             向通道发布消息
SPUBLISH shardchannel message                       向分片通道发布消息
PUBSUB CHANNELS [pattern]                           列出当前活动的通道
PUBSUB NUMPAT                                       unique patterns 数量
PUBSUB NUMSUB [channel [channel ...]]               订阅通道的数量，不包括订阅样式通道
```
- [服务器管理](https://redis.io/commands/?group=server)
```
INFO [section [section ...]]                        返回服务器的信息和统计信息
SAVE                                                同步保存到 .rdb 文件
BGSAVE [SCHEDULE]                                   异步保存到 .rdb 文件
DBSIZE                                              当前库 key 数量
FLUSHALL [ASYNC|SYNC]                               冲洗所有数据库 key
FLUSHDB [ASYNC|SYNC]                                冲洗当前库所有 key
CONFIG SET parameter value [ parameter value ...]   运行时配置参数
CONFIG GET parameter [ parameter ...]               获取配置参数
CONFIG REWRITE                                      重写 redis.conf
```
- [连接管理](https://redis.io/commands/?group=connection)
```
AUTH [usename] password                             验权
SELECT index                                        切换数据库
```
- [事务](https://redis.io/commands/?group=transactions)
```
MULTI                                               开启事务
EXEC                                                执行所有事务块内的命令
DISCARD                                             取消事务
WATCH key [key ...]                                 监视 key，在事务执行之前 key 被其他命令改动，则事务中断
UNWATCH                                             取消监视
```
---
## [计数器并发精准数量控制](https://www.imooc.com/learn/1067)
![redis 数量控制并发问题](https://img1.mukewang.com/6092cf44000167a319201080-500-284.jpg)
![redis 数量控制并发优化](https://img4.mukewang.com/6092d0560001c11019201080-500-284.jpg)
---
