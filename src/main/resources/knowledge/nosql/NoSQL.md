# NoSQL
- 非关系型数据库 Not Only SQL
---
## 参考网站
1. [Redis和MongoDB的区别以及应用场景](https://www.cnblogs.com/ht22ht22/p/12567172.html)
2. [mongodb,redis,hbase 三者都是nosql数据库，他们的最大区别和不同定位是什么？](https://www.zhihu.com/question/30219620)
3. [MongoDB、ElasticSearch、Redis、HBase这四种热门数据库的优缺点及应用场景](https://zhuanlan.zhihu.com/p/37964096)
---
## 简介
- 为什么使用 NoSL
    - High performance 高并发读写
    - Huge Storage 海量数据的高效率存储和访问
    - High Scalability && High Availability 高可扩展性和高可用性
- 分类
    - 键值(k-v)存储：Redis，Memcached
    - 列存储：Cassandra，HBase，RiaK
    - 文档数据库：MongoDB，CouchDB
    - 图形数据库：Neo4J
- 特点
    - 易扩展
    - 灵活的数据模型
    - 大数据量，高性能
    - 高可用
---
## 各 NoSQL 应用场景
1. Redis: 速度快，缓存
2. HBase: 数据量大，大数据分析
3. MongoDB: 复杂查询，高性能数据库
---
## CAP 定理
<img alt="CAP Theorem" src="https://www.runoob.com/wp-content/uploads/2013/10/cap-theoram-image.png" width="400"/>

- 对于一个分布式计算系统来说，不可能同时满足以下三点
    ```
    1. 一致性：Consistency，所有节点在同一时间具有相同的数据
    2. 可用性：Availability，保证每个请求不管成功或者失败都有响应
    3. 分隔容忍：Partition tolerance，系统中任意信息的丢失或失败不会影响系统的继续运作
    ```
- 根据 CAP 原理将 NoSQL 数据库分成了满足 CA 原则、满足 CP 原则和满足 AP 原则三 大类
    ```
    1. CA：单点集群，满足一致性，可用性的系统，通常在可扩展性上不太强大
    2. CP：满足一致性，分区容忍性的系统，通常性能不是特别高
    3. AP：满足可用性，分区容忍性的系统，通常可能对一致性要求低一些
    ```
---
## BASE
- NoSQL 数据库通常对可用性及一致性的弱要求原则
    ```
    1. Basically Available: 基本可用
    2. Soft-state: 软状态/柔性事务。Soft state 可以理解为"无连接"的, 而 Hard state 是"面向连接"的
    3. Eventually Consistency: 最终一致性，也是 ACID 的最终目的
    ```
---