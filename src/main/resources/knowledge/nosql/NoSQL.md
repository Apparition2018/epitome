# NoSQL
- 非关系型数据库 Not Only SQL
---
## Reference
1. [NoSQL - Wikipedia](https://en.wikipedia.org/wiki/NoSQL#Types_and_examples)
2. [NoSQL 简介 | 菜鸟教程](https://www.runoob.com/mongodb/nosql.html)
3. [Redis vs MongoDB](https://www.cnblogs.com/ht22ht22/p/12567172.html)
4. [Redis vs MongoDB vs HBase](https://www.zhihu.com/question/30219620)
---
## 对比
| Data Model          | Performance | Scalability   | Flexibility | Complexity | Notable Examples  | Use           |
|:--------------------|:------------|:--------------|:------------|:-----------|:------------------|:--------------|
| Key-Value Store     | high        | high          | high        | none       | Memcached, Redis  | 缓存 (速度快)      |
| Document Store      | high        | variable/high | high        | low        | MongoDB, CouchDB  | 高性能数据库 (复杂查询) |
| Wide Column Store   | high        | high          | moderate    | low        | Cassandra, HBase  | 大数据 (数据量大)    |
| Graph Database      | variable    | variable      | high        | high       | Neo4J, JanusGraph ||
| Relational Database | variable    | variable      | low         | moderate   | MySQL, SQL server ||
- [键值存储 vs 文档存储](https://www.likecs.com/show-204647694.html)
```
相同点：都是 k-v 结构
不同点：键值存储只能通过 key 查找整个 value；文档存储可以通过 key 查找 value 中的内容
```
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
- BASE 是 NoSQL 数据库通常对可用性及一致性的弱要求原则
    ```
    1. Basically Available: 基本可用
    2. Soft-state: 软状态/柔性事务。Soft state 可以理解为"无连接"的, 而 Hard state 是"面向连接"的
    3. Eventually Consistency: 最终一致性，也是 ACID 的最终目的
    ```
---