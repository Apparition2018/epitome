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
## 各层命名规约（阿里编程规约）
1. Service/DAO 层方法命名规约
   1. 获取单个对象的方法用 get 做前缀。
   2. 获取多个对象的方法用 list 做前缀，复数结尾，如：listObjects。 
   3. 获取统计值的方法用 count 做前缀。 
   4. 插入的方法用 save / insert 做前缀。
   5. 删除的方法用 remove / delete 做前缀。
   6. 修改的方法用 update 做前缀。
2. 领域模型命名规约
   1. 数据对象：xxxDO，xxx 即为数据表名。
   2. 数据传输对象：xxxDTO，xxx 为业务领域相关的名称。
   3. 展示对象：xxxVO，xxx 一般为网页名称。
   4. POJO 是 DO / DTO / BO / VO 的统称，禁止命名成 xxxPOJO。
---
## [分层领域模型规约](https://zhuanlan.zhihu.com/p/350964880) （阿里编程规约）
1. DO（Data Object）：此对象与数据库表结构一一对应，通过 DAO 层向上传输数据源对象。
2. DTO（Data Transfer Object）：数据传输对象，Service 或 Manager 向外传输的对象。
3. BO（Business Object）：业务对象，可以由 Service 层输出的封装业务逻辑的对象。
4. Query：数据查询对象，各层接收上层的查询请求。注意超过 2 个参数的查询封装，禁止使用 Map 类来传输。
5. VO（View Object）：显示层对象，通常是 Web 向模板渲染引擎层传输的对象。
---
## 阿里编程规约
### 建表规约
```
1. 表达是与否概念的字段，必须使用 is_xxx 的方式命名，数据类型是 unsigned tinyint（1 表示是，0 表示否）
2. 表名不使用复数名词
3. 主键索引名为 pk_字段名；唯一索引名为 uk_字段名；普通索引名则为 idx_字段名
4. 小数类型为 decimal，禁止使用 float 和 double
5. 如果存储的字符串长度几乎相等，使用 char 定长字符串类型
6. varchar 是可变长字符串，不预先分配存储空间，长度不要超过 5000，如果存储长度大于此值，定义字段类型为 text，独立出来一张表，用主键来对应，避免影响其它字段索引效率
7. 表的命名最好是遵循“业务名称_表的作用”；库名与应用名称尽量一致
8. 单表行数超过 500 万行或者单表容量超过 2GB，才推荐进行分库分表
```
### 索引规约
- [阿里为什么禁止超过三张表 join？](https://www.zhihu.com/question/56236190?sort=created)
```
1. 业务上具有唯一特性的字段，即使是组合字段，也必须建成唯一索引
2. 超过三个表禁止 join。需要 join 的字段，数据类型保持绝对一致；多表关联查询时，保证被关联的字段需要有索引
3. 在 varchar 字段上建立索引时，必须指定索引长度，没必要对全字段建立索引，根据实际文本区分度决定索引长度
4. 页面搜索严禁左模糊或者全模糊，如果需要请走搜索引擎来解决
5. 如果有 order by 的场景，请注意利用索引的有序性，如：where a=? and b=? order by c; 索引：a_b_c
6. 利用覆盖索引来进行查询操作，避免回表：用 explain 的结果，extra 列会出现：using index
7. 利用延迟关联或者子查询优化超多分页场景：SELECT t1.* FROM 表 1 as t1, (select id from 表 1 where 条件 LIMIT 100000,20 ) as t2 where t1.id=t2.id
8. SQL 性能优化的目标：至少要达到 range 级别，要求是 ref 级别，如果可以是 consts 最好
9. 建组合索引的时候，区分度最高的在最左边
```
### SQL 语句
```
1. 不要使用 count(列名) 或 count(常量) 来替代 count(*)，count(*) 是 SQL92 定义的标准统计行数的语法，跟数据库无关，跟 NULL 和非 NULL 无关
2. count(distinct col) 计算该列除 NULL 之外的不重复行数，注意 count(distinct col1, col2) 如果其中一列全为 NULL，那么即使另一列有不同的值，也返回为 0
3. 当某一列的值全是 NULL 时，count(col) 的返回结果为 0，但 sum(col) 的返回结果为 NULL，因此使用 sum() 时需注意 NPE 问题，避免方法：SELECT IFNULL(SUM(column), 0) FROM table
4. 不得使用外键与级联，一切外键概念必须在应用层解决
5. 禁止使用存储过程，存储过程难以调试和扩展，更没有移植性
6. 数据订正（特别是删除或修改记录操作）时，要先 select，避免出现误删除，确认无误才能执行更新语句
7. in 操作能避免则避免，若实在避免不了，需要仔细评估 in 后边的集合元素数量，控制在 1000 个之内
```
### ORM 映射
```
1. POJO 类的布尔属性不能加 is，而数据库字段必须加 is_，要求在 resultMap 中进行字段与属性之间的映射
2. 不要用 resultClass 当返回参数，即使所有类属性名与数据库字段一一对应，也需要定义<resultMap>；反过来，每一个表也必然有一个<resultMap>与之对应
3. sql.xml 配置参数使用：#{}，#param# 不要使用 ${} 此种方式容易出现 SQL 注入
4. iBATIS 自带的 queryForList(String statementName,int start,int size) 不推荐使用
5. 不允许直接拿 HashMap 与 Hashtable 作为查询结果集的输出
6. 不要写一个大而全的数据更新接口。传入为 POJO 类，不管是不是自己的目标更新字段，都进行 update table set c1=value1, c2=value2, c3=value3; 不要更新无改动的字段，一是易出错；二是效率低；三是增加 binlog 存储
7. 使用事务的地方需要考虑各方面的回滚方案，包括缓存回滚、搜索引擎回滚、消息补偿、统计修正等
>```
---
