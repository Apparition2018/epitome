# Redis
![Redis 思维导图](https://img2018.cnblogs.com/blog/967517/201904/967517-20190417162215588-320188667.png)
- 基于内存的 key-value store 非关系型数据库
---
## 参考网站
1. [Redis](https://redis.io/)
2. [Redis入门视频教程-慕课网](https://www.imooc.com/learn/839)
3. [Redis 教程 | 菜鸟教程](https://www.runoob.com/redis/redis-tutorial.html)
4. [Redis 教程 | 冯佳兴](https://blog.csdn.net/fjxcsdn/category_8935622.html)
5. [Windows Redis](https://github.com/microsoftarchive/redis/releases)
6. [windows 下安装 redis 并设置自启动](https://www.cnblogs.com/yunqing/p/10605934.html)   
7. [Redis Desktop Manager](https://www.jianshu.com/p/ccc3ebe29f7b)
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
## [客户端操作](https://www.redis.io/commands)
- [Try Redis](https://try.redis.io/)
- 连接远程 redis：redis-cli -h host -p port -a password
  - Key
  ```
  KEYS pattern[?|*]                                 查看
  DEL key [key ...]                                 删除
  EXISTS key [key ...]                              判断是否存在
  RENAME key newkey                                 重命名
  TYPE key                                          返回类型
  EEXPIRE key seconds [NX|XX|GT|LT]                 设置过期时间，秒
  EXPIREAT key unix-time-seconds [NX|XX|GT|LT]      设置过期时间点，时间戳
  PEXPIRE key milliseconds [NX|XX|GT|LT]            设置过期时间，毫秒
  PEXPIREAT key unix-time-milliseconds [NX|XX|GT|LT]设置过期时间点，时间戳
  TTL key                                           返回生存时间，秒
  PERSIST key                                       持久保持
  MOVE key db                                       移动
  ```
  - String
  ```
  SET key value [command]                           设置
  SETNX key value                                   不存在才设置
  SETRANGE key offset value                         替换
  SETBIT key offset value                           设置 bit
  GETSET key value                                  设置，并获取旧值
  MSET key value [key value ...]                    批量设置
  GET key                                           获取
  GETRANGE key start end                            截取
  GETBIT key offset                                 获取 bit
  MGET key [key ...]                                批量获取
  APPEND key value                                  追加
  INCR key                                          增一
  DECR key                                          减一
  INCRBY key increment                              增加
  DECRBY key increment                              减少
  STRLEN key                                        长度
  ```
  - Hash
  ```
  HSET key field value [field value ...]            设置
  HMSET key field value [field value ...]           批量设置
  HSETNX key field                                  不存在才设置
  HGET key field                                    获取
  HMGET key field [field ...]                       批量获取
  HGETALL key                                       获取所有键值
  HKEYS key                                         获取所有键
  HVALS key                                         获取所有值
  HDEL key field [field ...]                        删除
  HEXISTS key field                                 判断是否存在
  HINCRBY key field increment                       增加
  HLEN key                                          长度
  ```
  - List
  ```
  RPUSH key element [element ...]                   添加
  LPUSH key element [element ...]                   头部添加
  RPUSHX key element [element ...]                  存在才添加
  LPUSHX key element [element ...]                  存在才头部添加
  LINSERT key BEFORE|AFTER pivot element            在元素前或后添加
  LSET key index element                            索引设置
  RPOPLPUSH source destination                      移除最后并添加到另外最后
  LRANGE key start stop                             获取
    LRANGE key 0 -1                                 获取全部
  LINDEX key index                                  索引获取
  RPOP key [count]                                  移除
  LPOP key [count]                                  头部移除
  BRPOP key [key ...] timeout                       阻塞移除
  BLPOP key [key ...] timeout                       阻塞头部移除
  LREM key count element                            删除
  LTRIM key start stop                              范围删除
  LLEN key                                          长度
  ```
  - Set
  ```
  SADD key member [member ...]                      添加
  SMEMBERS key                                      获取
  SRANDMEMBER key [count]                           随机获取
  SPOP key [count]                                  随机移除
  SREM key member [member ...]                      删除
  SISMEMBER key member                              判断是否存在
  SCARD key                                         长度
  SUNION key [key ...]                              并集
  SUNIONSTORE destination key [key ...]             并集并另外存储
  SINTER key [key ...]                              交集
  SINTERSTORE destination key [key ...]             交集并另外存储
  SDIFF key [key ...]                               差异
  SDIFFSTORE destination key [key ...]              差异并另外存储
  ```
  - Sorted SET
  ```
  ZADD key [command] score member [score member ...]添加
  ZRANGE key min max [command]                      获取
    ZRANGE key 0 -1                                 获取全部
  ZRANGEBYSCORE key min max [command]               获取按分数排名
  ZREVRANGE key start stop [WITHSCORES]             倒叙获取
  ZSCORE key member                                 获取分数
  ZRANK key mebmer                                  获取排名
  ZUNIONSTORE destination numkeys key [key ...]     并集并另外存储
  ZREM key member [member ...]                      删除
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
  