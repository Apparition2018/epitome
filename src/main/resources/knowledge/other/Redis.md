# Redis
- 单进程阻塞式
- 不适合缓存大尺寸对象(>100kb )
---
## 参考网站
1. [Redis入门](https://www.imooc.com/learn/839)
2. [Redis 教程 | 菜鸟教程](https://www.runoob.com/redis/redis-tutorial.html)
3. [Redis 教程](https://www.cnblogs.com/yiwangzhibujian/category/1020818.html)
4. [Redis 密码设置和登录](https://www.cnblogs.com/xiaozong/p/5652563.html)
5. [Windows Redis](https://github.com/microsoftarchive/redis/releases)
6. [windows下安装redis并设置自启动](https://www.cnblogs.com/yunqing/p/10605934.html)   
7. [Redis Desktop Manager](https://www.jianshu.com/p/ccc3ebe29f7b)
---
## NoSQL
- 非关系型数据库 Not Only SQL
- 为什么使用 NoSL
    - High performance 高并发读写
    - Huge Storage 海量数据的高效率存储和访问
    - High Scalability && High Availability 高可扩展性和高可用性
- 分类
    - 键值(k-v)存储：Redis，Memcached
    - 列存储：Cassandra，HBase，RiaK
    - 文档数据库：MongoDB
    - 图形数据库：Neo4J
- 特点
    - 易扩展
    - 灵活的数据模型
    - 大数据量，高性能
    - 高可用
---
## Redis 支持类型及应用场景
- 字符串 String
    - 二进制安全的，存入和获取的数据相同
    - 可存储 512M
    - 缓存、限流、计数器、分布式锁、分布式 session
      - 计数器应用场景：
        - 频率控制：接口防刷，密码尝试次数限制
        - 数量统计：请求量统计
        - 数量控制：商品抢购，奖励额度控制
- 散列 Hash
    - 字符串键值的 Map
    - 可存储 4294967295 个键值对
    - 用户信息、用户主页访问量、组合查询
- 列表 List
    - 微博未关注人时间轴列表、简单队列  
    ![RPOPLPUSH](https://img3.mukewang.com/608c3a3e0001e0eb13660768-500-284.jpg)
- 集合类型 Set
    - 可存储 4294967295
    - 赞、踩、标签、唯一性数据、数据对象之间的关系
- 有序集合 Sorted Set
    - 从小到大
    - 排行榜、构建索引数据
---
## Redis 数量控制
1. 并发问题  
![redis 数量控制并发问题](https://img1.mukewang.com/6092cf44000167a319201080-500-284.jpg)
2. 优化  
![redis 数量控制并发优化](https://img4.mukewang.com/6092d0560001c11019201080-500-284.jpg)
---
## Redis 特性
- 多数据库
- Redis 事务
---
## [Redis 配置](https://raw.githubusercontent.com/redis/redis/6.0/redis.conf)
>### 常规配置
>```
># 设置客户端密码
>requirepass password
># 绑定 IPv4 地址
>bind 127.0.0.1
># 是否开启保护模式
>protected-mode yes
># 是否守护线程运行
># yes 会和 docker run -d 冲突
>daemonize no
># 持久化文件存储目录
>dir ./
># 包含其它配置文件
>include other.conf
>```
>### RDB 持久化
>```
># RDB 持久化文件名
>dbfilename dump.rdb
># 当 seconds 秒内至少有 changes 个键被改动时，自动进行数据集保存操作
># save "" 并删除 dump.rdb 相当于光比 RDB 持久化
>save <seconds> <changes>
>```
>### AOF 持久化
>```
># 是否开启 AOF 持久化
>appendonly yes
># AOF 持久化文件名
>appendfilename "appendonly.aof"
># 同步策略
>appendfsync always | everysec | no
>```
---
## [Redis 持久化](https://segmentfault.com/a/1190000016021217)
- RDB 持久化
- AOF 持久化
---
## [客户端操作](https://www.cnblogs.com/javastack/p/9854489.html)
- 连接远程 redis：redis-cli -h host -p port -a password
- [命令](https://www.runoob.com/redis/redis-commands.html)
  - Key
  ```
  KEYS pattern[?|*]                                 查看
  DEL key                                           删除
  EXISTS key                                        判断是否存在
  RENAME key newkey                                 重命名
  TYPE key                                          返回类型
  EXPIRE key seconds                                设置过期时间，秒
  EXPIREAT key timestamp                            设置过期时间点，时间戳
  TTL key                                           返回生存时间，秒
  PERSIST key                                       持久保持
  MOVE key db                                       移动
  ```
  - String
  ```
  SET key value [command]                           设置
  SETNX key value                                   不存在才设置
  GETSET key value                                  设置，并获取旧值
  MSET key value [key value...]                     批量设置
  GET key                                           获取
  MGET key [key...]                                 批量获取
  APPEND key value                                  追加
  INCR key                                          增一
  DECR key                                          减一
  INCRBY key increment                              增加
  DECRBY key increment                              减少
  STRLEN key                                        长度
  ```
  - Hash
  ```
  HSET key field value [field value...]             设置
  HMSET key field value [field value...]            批量设置
  HSETNX key field                                  不存在才设置
  HGET key field                                    获取
  HMET key field [field...]                         批量获取
  HGETALL key                                       获取所有键值
  HKEYS key                                         获取所有键
  HVALS key                                         获取所有值
  HDEL key field [field...]                         删除
  HEXISTS key field                                 判断是否存在
  HINCRBY key field increment                       增加
  HLEN key                                          长度
  ```
  - List
  ```
  RPUSH key ele [ele...]                            添加
  LPUSH key ele [ele...]                            头部添加
  RPUSHX key ele [ele...]                           存在才添加
  LPUSHX key ele [ele...]                           存在才头部添加
  LINSERT key before|after pivot ele                在元素前或后添加
  LSET key index ele                                索引添加
  RPOPLPUSH source destination                      移除最后并添加到另外最后
  LRANGE key start stop                             获取
    LRANGE key 0 -1                                 获取全部
  LINDEX key index                                  索引获取
  RPOP key [count]                                  移除
  LPOP key [count]                                  头部移除
  LREM key count element                            删除
  LTRIM key start stop                              范围删除
  LLEN key                                          长度
  ```
  - Set
  ```
  SADD key member [member...]                       添加
  SMEMBERS key                                      获取
  SRANDMEMBER key [count]                           随机获取
  SPOP key [count]                                  随机移除
  SREM key member [member...]                       删除
  SISMEMBER key member                              判断是否存在
  SCARD key                                         长度
  SUNION key [key...]                               并集
  SUNIONSTORE destination key [key...]              并集并另外存储
  SINTER key [key...]                               交集
  SINTERSTORE destination key [key...]              交集并另外存储
  SDIFF key [key...]                                差异
  SDIFFSTORE destination key [key...]               差异并另外存储
  ```
  - Sorted SET
  ```
  ZADD key [command] score member [score member...] 添加
  ZRANGE key min max [command]                      获取
    ZRANGE key 0 -1                                 获取全部
  ZRANGEBYSCORE key min max [command]               获取按分数排名
  ZREVRANGE key start pop [WITHSCORES]              倒叙获取
  ZSCORE key member                                 获取分数
  ZRANK key mebmer                                  获取排名
  ZREM key member [member...]                       删除
  ZREMRANGEBYRANK key start stop                    删除排名区间成员
  ZREMRANGEBYSCORE key min max                      删除分数区间成员
  ZINCRBY key increment member                      增加分数
  ZCARD key                                         长度
  ZCOUNT key min max                                计算区间成员个数
  ```
  - 连接
  ```
  AUTH [usename] password                           验权
  SELECT index                                      切换数据库
  ```
  - 事务
  ```
  MULTI                                             开启事务                                             
  EXEC                                              执行所有事务块内的命令
  DISCARD                                           取消事务
  ```
  - 服务器
  ```
  INFO [section]                                    返回关于 Redis 服务器的各种信息和统计数值
  SAVE                                              同步保存到 .rdb 文件
  BGSAVE [SCHEDULE]                                 异步保存到 .rdb 文件
  DBSIZE                                            当前库 key 数量
  FLUSHALL [ASYNC|SYNC]                             冲洗所有数据库 key
  FLUSHDB [ASYNC|SYNC]                              冲洗当前库所有 key
  ```
---
  