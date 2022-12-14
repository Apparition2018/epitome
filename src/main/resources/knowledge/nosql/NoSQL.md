# NoSQL
- 非关系型数据库 Not Only SQL
---
## Reference
1. [NoSQL - Wikipedia](https://en.wikipedia.org/wiki/NoSQL#Types_and_examples)
2. [NoSQL 简介 | 菜鸟教程](https://www.runoob.com/mongodb/nosql.html)
3. [Redis vs MongoDB](https://www.cnblogs.com/ht22ht22/p/12567172.html)
4. [Redis vs MongoDB vs HBase](https://www.zhihu.com/question/30219620)
---
## 数据模型对比
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
Consistency             一致性，更新操作成功后，所有节点在同一时间的数据完全一致
Availability            可用性，访问数据时，系统是否能在正常响应时间返回预期的结果
Partition tolerance     分隔容忍，分布式系统在遇到某节点或网络分区故障时，仍能对外提供满足一致性和可用性的服务
```
---
## BASE
- BASE 是 NoSQL 数据库通常对可用性及一致性的弱要求原则
```
Basically Available     基本可用
Soft-state              软状态/柔性事务。Soft state 可以理解为"无连接"的, 而 Hard state 是"面向连接"的
Eventually Consistency  最终一致性，也是 ACID 的最终目的
```
---
