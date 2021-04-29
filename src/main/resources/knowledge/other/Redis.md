# Redis

---
## 参考网站
1. [Redis入门](https://www.imooc.com/learn/839)
2. [Redis 教程 | 菜鸟教程](https://www.runoob.com/redis/redis-tutorial.html)
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
## Redis 支持类型
- 字符串类型
- 列表类型
- 有序集合类型
- 散列类型
- 集合类型
---
## Redis 应用场景
- String：缓存、限流、计数器、分布式锁、分布式 session
- Hash：用户信息、用户主页访问量、组合查询
- List：微博未关注人时间轴列表、简单队列
- Set：赞、踩、标签、好友关系
- Zset：排行榜
---
## [客户端操作](https://www.cnblogs.com/javastack/p/9854489.html)
- 连接远程 redis：redis-cli -h host -p port -a password
- 命令
  - Key
  ```
  DEL key                                           删除
  EXISTS key                                        判断是否存在
  TYPE key                                          返回类型
  EXPIRE key seconds                                设置过期时间，秒
  EXPIREAT key timestamp                            设置过期时间点，时间戳
  TTL key                                           返回生存时间，秒
  PERSIST key                                       持久保持
  MOVE key db                                       移动
  KEYS pattern                                      查看
  ```
  - String
  ```
  SET key value [command]                           设置
  SETNX key value                                   不存在时设置
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
  HSETNX key field                                  不存在时设置
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
  LSET key index ele                                索引添加
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
  ```
  - Sorted SET
  ```
  ZADD key [command] score member [score member...] 添加
  ZRANGE key start pop [command]                    获取
    ZRANGE key 0 -1                                 获取全部
  ZSCORE key member                                 获取分数
  ZRANK key mebmer                                  获取排名
  ZREM key member [member...]                       删除
  ZINCRBY key increment member                      增加分数
  ZCARD key                                         长度
  ```
  - 连接
  ```
  SELECT index                                      切换数据库
  ```
  - 服务器
  ```
  FLUSHALL                                          删除所有数据库 key
  FLUSHDB                                           删除当前库所有 key
  DBSIZE                                            当前库 key 数量
  ```
---
  