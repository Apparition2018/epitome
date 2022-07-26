# SQL  

---
## Reference
1. [TechOnTheNet](https://www.techonthenet.com/)
2. [建表规范](https://www.cnblogs.com/xphdbky/p/7154434.html)
3. [SQL 语法速成手册](https://juejin.cn/post/6844903790571700231)
## 在线 SQL
1. [SQL Fiddle](http://sqlfiddle.com/)
2. [db<>fiddle](https://dbfiddle.uk)
3. [Oracle Live SQL](https://livesql.oracle.com/apex/f?p=590:1000)
---
## 慕课
1. [数据库设计那些事](https://www.imooc.com/learn/117)
2. [性能优化之MySQL优化](https://www.imooc.com/learn/194)
3. [MySQL开发技巧（一）](https://www.imooc.com/learn/398)
4. [MySQL开发技巧（二）](https://www.imooc.com/learn/427)
5. [MySQL开发技巧（三）](https://www.imooc.com/learn/449)
---
## 问题
1. [MySQL, SQL Server, Oracle 的区别](https://www.cnblogs.com/cherxu/p/6856262.html)
2. [distinct 和 group by 的去重逻辑浅析](https://www.cnblogs.com/dancesir/p/7505730.html)
---
## [数据库名词](https://www.cnblogs.com/dmeck/p/10507936.html)
| 名词      | 英文                   | 说明                       |
|:--------|:---------------------|:-------------------------|
| 关系      | Relation             | 基本表、查询表、视图表              |
| 列/属性    | Column/Attribute     ||
| 行/记录/元组 | Row/Record/Tuple     ||
| 码/键     | Key                  | 某个属性或属性组，能唯一标识每个元组       |
| 超码/超键   | Super Key            | 关系中的一个码移去某个属性，仍然是这个关系的码  |
| 候选码/候选键 | Candidate Key        | 关系中的一个码移去某个属性后，就不是这个关系的码 |
| 非主属性    | NonPrimary Attribute | 不是任何一个候选码中的属性            |
| 主码/主键   | Primary Key          | 关系中被指定用来唯一标识元组的候选码       |
| 主属性     | Primary Attribute    | 任何一个候选码中的属性              |
---
## 关系的完整性约束
| 完整性      | 是否必选 | 说明                     |
|:---------|:-----|:-----------------------|
| 实体完整性    | 必选   | 主属性不能为空                |
| 参照完整性    | 必选   | 外键值要么为空，要么为被参照关系的某个主码值 |
| 用户定义的完整性 | 可选   | 唯一性、是否不能为空等            |
---
## [设计范式](https://www.zhihu.com/question/24696366/answer/29189700)
- 数据库范式分为 1NF，2NF，3NF，BCNF，4NF，5NF，一般考虑到 BCNF 就可以了
- 符合高一级范式的设计，必定符合低一级范式
- 解决了数据冗余过大，插入异常，修改异常，删除异常问题

| 范式   | 说明                      |
|:-----|:------------------------|
| 1NF  | 属性不可再分                  |
| 2NF  | 消除非主属性对候选码的部分函数依赖       |
| 3NF  | 消除非主属性对候选码的传递函数依赖       |
| BCNF | 消除主属性对候选码的部分函数依赖和传递函数依赖 |
---
## 函数依赖
| 依赖     | 写作     | 说明                                   | 例子                                |
|:-------|:-------|:-------------------------------------|:----------------------------------|
| 函数依赖   | X → Y  | X 可以确定 Y (Y 函数依赖于 X)                 | 学号 → 姓名                           |
| 完全函数依赖 | X F→ Y | 如 X → Y，且对于 X 的任何一个真子集 X'，X' → Y 不成立 | （学号，课名）F→ 分数                      |
| 部分函数依赖 | X P→ Y | 如 X → Y，且存在 X 的一个真子集 X'，X' → Y 成立    | （学号，课名）P→ 姓名                      |
| 传递函数依赖 | X T→ Z | 如 X → Y，且 Y → Z，则 X → Z              | 学号 → 系名，系名 → 系主任，<br/>则 学号 T→ 系主任 |
---
## 数据操作异常
1. 插入异常：某实体根据另一个实体存在而存在，即缺少某个实体时无法表示这个实体
2. 删除异常：如果删除表的某一行来反映某实体实例失效时导致另一个不同实体实例信息丢失
3. 修改异常：如果更新表所对应的某个实体实例的单独属性时，需要将多行更新
---
## 数据类型速查表
| 数据类型              |       MySQL        |                           SQL Server                           |        Oracle        |
|:------------------|:------------------:|:--------------------------------------------------------------:|:--------------------:|
| boolean           |                    |                              bit                               ||
| integer           |        int         |                              int                               |        number        |
| float             |      decimal       |                       decimal / numeric                        |        number        |
| date              |  date / datetime   |                        date / datetime                         |   date / timestamp   |
| string (fixed)    |        char        |                              char                              |         char         |
| string (variable) | varchar / nvarchar |                       varchar / nvarchar                       | varchar2 / nvarchar2 |
| binary object     |    blob / text     | binary (fixed up to 8k) <br> varbinary (<8k) <br> image (<2GB) |      long / raw      |
---
## [事务的特性 (ACID)](https://en.wikipedia.org/wiki/ACID)
1. Atomicity：原子性，事务通常由多个语句组成。原子性保证每个事务都被视为一个单独的'单元'，要么完全成功，要么完全失败。
2. [Consistency](https://www.zhihu.com/question/31346392) ：一致性，一致性确保事务只能将数据库从一种有效状态带到另一种有效状态，写入数据库的任何数据必须根据所有定义的规则有效，包括约束、级联、触发器及其任意组合。
3. Isolation：隔离性，事务通常是并发执行的。隔离性确保事务的并发执行使数据库处于与顺序执行事务时获得的相同状态。
4. Durability：持久性，持久性保证一旦事务被提交，即使在系统故障的情况下，它也将保持提交后的修改。
---
## 并发事务读取现象
| 现象                | 中文    | 说明                                 |
|:------------------|:------|:-----------------------------------|
| Dirty Read        | 脏读    | 一个事务读到另一个事务尚未提交的                   |
| Unrepeatable Read | 不可重复读 | 一个事务两次读取的结果不一致，数据被 update 或 delete |
| Phantom Read      | 幻读    | 一个事务两次读取的结果不一致，有新的数据 insert        |
---
## 事务隔离级别
| 隔离级别             | 默认                 | 脏读  | 不可重复读 | 幻读  |
|:-----------------|:-------------------|:----|:------|:----|
| Read Uncommitted |                    | 可能  | 可能    | 可能  |
| Read Committed   | Oracle, SQL Server | 不可能 | 可能    | 可能  |
| Repeatable Read  | MySQL              | 不可能 | 不可能   | 可能  |
| Serializable     |                    | 不可能 | 不可能   | 不可能 |
- [MySQL RR 如何解决幻读](https://www.zhihu.com/question/372905832)
---
