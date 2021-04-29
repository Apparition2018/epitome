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
- 缓存
- 任务队列
- 数据过期处理
- 分布式集群架构中的 session 分离
---
## 客户端操作
- 连接远程 redis：redis-cli -h host -p port -a password
- 命令
```redis
SET key value
GET key
DEL key
keys *
```