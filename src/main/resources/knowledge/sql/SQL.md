# SQL  

---
## 参考网站
1. [TechOnTheNet](https://www.techonthenet.com/)
2. [SQL Fiddle](http://sqlfiddle.com/)
3. [建表规范](https://www.cnblogs.com/xphdbky/p/7154434.html)
4. [SQL 语法速成手册](https://mp.weixin.qq.com/s?__biz=MzU2MTI4MjI0MQ==&mid=2247491550&idx=2&sn=cfe8ed6eea2e61646e5cc3d0b5e96b68)
## 课程
1. [数据库设计那些事](https://www.imooc.com/learn/117)
2. [性能优化之MySQL优化](https://www.imooc.com/learn/194)
3. [MySQL开发技巧（一）](https://www.imooc.com/learn/398)
4. [MySQL开发技巧（二）](https://www.imooc.com/learn/427)
5. [MySQL开发技巧（三）](https://www.imooc.com/learn/449)
---
## 问题
1. [MySQL, SQL Server, Oracle 的区别](https://www.cnblogs.com/cherxu/p/6856262.html)
2. [distinct 和 group by 的去重逻辑浅析](https://www.cnblogs.com/dancesir/p/7505730.html)
3. [select 和 in 的区别](https://www.cnblogs.com/emilyyoucan/p/7833769.html)
4. [varchar 与 nvarchar](https://www.cnblogs.com/Jashinck/p/8384388.html)
5. [当要给字符串创建前缀索引时，如何确定我应该使用多长的前缀呢？](https://blog.csdn.net/qq_38670588/article/details/108499966)
---
## 阿里规约
>### 建表规约
>```
>1. 表达是与否概念的字段，必须使用 is_xxx 的方式命名，数据类型是 unsigned tinyint（1 表示是，0 表示否）
>2. 表名不使用复数名词
>3. 主键索引名为 pk_字段名；唯一索引名为 uk_字段名；普通索引名则为 idx_字段名
>4. 小数类型为 decimal，禁止使用 float 和 double
>5. 如果存储的字符串长度几乎相等，使用 char 定长字符串类型
>6. varchar 是可变长字符串，不预先分配存储空间，长度不要超过 5000，如果存储长度大于此值，定义字段类型为 text，独立出来一张表，用主键来对应，避免影响其它字段索引效率
>7. 表的命名最好是遵循“业务名称_表的作用”；库名与应用名称尽量一致
>8. 单表行数超过 500 万行或者单表容量超过 2GB，才推荐进行分库分表
>```
>### 索引规约
>```
>1. 业务上具有唯一特性的字段，即使是组合字段，也必须建成唯一索引
>2. 超过三个表禁止 join。需要 join 的字段，数据类型保持绝对一致；多表关联查询时，保证被关联的字段需要有索引
>3. 在 varchar 字段上建立索引时，必须指定索引长度，没必要对全字段建立索引，根据实际文本区分度决定索引长度
>4. 页面搜索严禁左模糊或者全模糊，如果需要请走搜索引擎来解决
>5. 如果有 order by 的场景，请注意利用索引的有序性，如：where a=? and b=? order by c; 索引：a_b_c
>6. 利用覆盖索引来进行查询操作，避免回表：用 explain 的结果，extra 列会出现：using index
>7. 利用延迟关联或者子查询优化超多分页场景：SELECT t1.* FROM 表 1 as t1, (select id from 表 1 where 条件 LIMIT 100000,20 ) as t2 where t1.id=t2.id
>8. SQL 性能优化的目标：至少要达到 range 级别，要求是 ref 级别，如果可以是 consts 最好
>9. 建组合索引的时候，区分度最高的在最左边
>```
>### SQL 语句
>```
>1. 不要使用 count(列名) 或 count(常量) 来替代 count(*)，count(*) 是 SQL92 定义的标准统计行数的语法，跟数据库无关，跟 NULL 和非 NULL 无关
>2. count(distinct col) 计算该列除 NULL 之外的不重复行数，注意 count(distinct col1, col2) 如果其中一列全为 NULL，那么即使另一列有不同的值，也返回为 0
>3. 当某一列的值全是 NULL 时，count(col) 的返回结果为 0，但 sum(col) 的返回结果为 NULL，因此使用 sum() 时需注意 NPE 问题，避免方法：SELECT IFNULL(SUM(column), 0) FROM table
>4. 不得使用外键与级联，一切外键概念必须在应用层解决
>5. 禁止使用存储过程，存储过程难以调试和扩展，更没有移植性
>6. 数据订正（特别是删除或修改记录操作）时，要先 select，避免出现误删除，确认无误才能执行更新语句
>7. in 操作能避免则避免，若实在避免不了，需要仔细评估 in 后边的集合元素数量，控制在 1000 个之内
>```
>### ORM 映射
>```
>1. POJO 类的布尔属性不能加 is，而数据库字段必须加 is_，要求在 resultMap 中进行字段与属性之间的映射
>2. 不要用 resultClass 当返回参数，即使所有类属性名与数据库字段一一对应，也需要定义<resultMap>；反过来，每一个表也必然有一个<resultMap>与之对应
>3. sql.xml 配置参数使用：#{}，#param# 不要使用 ${} 此种方式容易出现 SQL 注入
>4. iBATIS 自带的 queryForList(String statementName,int start,int size) 不推荐使用
>5. 不允许直接拿 HashMap 与 Hashtable 作为查询结果集的输出
>6. 不要写一个大而全的数据更新接口。传入为 POJO 类，不管是不是自己的目标更新字段，都进行 update table set c1=value1, c2=value2, c3=value3; 不要更新无改动的字段，一是易出错；二是效率低；三是增加 binlog 存储
>7. 使用事务的地方需要考虑各方面的回滚方案，包括缓存回滚、搜索引擎回滚、消息补偿、统计修正等
>```
---
## [数据库名词](https://www.cnblogs.com/dmeck/p/10507936.html)
- 关系：表
- 属性：表中的一列即为一个属性
- 域：属性的取值范围
- 元组：表中的一行即为一个元组
- 分量：元组中的一个属性值
- 码：关系中某个属性或属性组，能唯一标识每个元组
- 超码：关系中的一个码移去某个属性，仍然是这个关系的码，则这样的码为该关系的超码
- 候选码：关系中的一个码移去某个属性后，就不是这个关系的码，则这样的码为该关系的候选码；即关系的最小超码
- 主属性：关系中的任一个候选码中的属性
---
## 数据操作异常
1. 插入异常：如果某实体随着另一个实体的存在而存在，即缺少某个实体时无法表示这个实体，那么这个表就存在插入异常
2. 更新异常：如果更新表所对应的某个实体实例的单独属性时，需要将多行更新，那么就说这个表存在更新异常
3. 删除异常：如果删除表的某一行来反映某实体实例失效时导致另一个不同实体实例信息丢失，那么这个表中就存在删除异常
---
## 索引维护
1. 出现在 WHERE，GROUP BY，ORDER BY 中的列
2. 可选择性高的列要放到索引的前面
3. 索引中不要包括太长的数据类型
4. 索引并不是越多越好，过多的索引不但会降低写效率而且会降低读的效率
5. 定期维护索引碎片
6. SQL 语句中不要使用强制索引关键字
---
## 设计范式
- 数据库范式分为 1NF，2NF，3NF，BCNF，4NF，5NF，一般考虑到 BCNF 就可以了。符合高一级范式的设计，必定符合低一级范式。
- 解决了数据冗余过大，插入异常，修改异常，删除异常问题
1. 1NF：每个属性不可再分
2. 2NF：在 1NF 的基础上，消除非主属性对于码的部分函数依赖  
   2.1 找出数据表中所有的码  
   2.2 根据第一步所得到的码，找出所有的主属性  
   2.3 除去所有的主属性，剩下的就都是非主属性  
   2.4 查看是否存在非主属性对码的部分函数依赖
3. 3NF：在 2NF 的基础上，消除非主属性对于码的传递函数依赖
4. BCNF：在 3NF 的基础上，消除主属性对于码的部分与传递函数依赖
>### 函数依赖
>1. 函数依赖：
>     - 学号 → 姓名，姓名函数依赖于学号
>2. 完全函数依赖：
>     - 学号 F→ 姓名，（学号，课名）F→ 分数 
>3. 部分函数依赖：存在着组合关键字中的某一关键字决定非关键字的情况
>     - （学号，课名）P→ 姓名 
>4. 传递函数依赖：
>     - 学号 → 系名，系名 → 系主任，则 学号 T→ 系主任 
>#### 参考
>1. [什么是第一，第二，第三范式](https://blog.csdn.net/xidianliuy/article/details/51566576)
>2. [如何理解关系型数据库的常见设计范式？](https://www.zhihu.com/question/24696366/answer/29189700)
---
## 数据类型选择
1. 数字类型 > 日期类型|二进制类型 > 字符类型
2. char > varchar，小于 50byte 建议使用 char
3. 精确数据使用 decimal，非精确数据使用 float
## 数据类型速查表
|数据类型|MySQL|SQL Server|Oracle|
|:------|:---:|:--------:|:----:|
|boolean| |bit||
|integer|int|int|number|
|float|decimal|decimal / numeric|number|
|date|date / datetime|date / datetime|date / timestamp|
|string (fixed)|char|char|char|
|string (variable)|varchar / nvarchar|varchar / nvarchar|varchar2 / nvarchar2|
|binary object|blob / text|binary (fixed up to 8k) <br> varbinary (<8k) <br> image (<2GB)|long / raw|
---
## SQL 优化
1. 避免全表扫描：对 无索引的表进行的查询 或 放弃索引进行的查询 称为全表扫描
   1. 在 where，order by，group by，多表关联涉及的列上建立索引
   2. 避免使用 is null 和 is not null，建表时尽量所有字段设置 NOT NULL
   3. 避免使用 != 和 <>
   4. 避免使用 or 来连接条件，可以使用 union 或 union all 代替
   5. 避免使用 前置% like，如 like "%xyz" 和 like "%xyz%"；可使用 locate('x', 'field') > 0 代替，或数据库提供的全文检索功能和专门的全文搜索引擎
   6. 避免使用 参数，如 where num=@num
   7. 避免对字段进行表达式和函数操作，num/2=100 可以改为 num=100*2，包括数据类型不匹配，如字符串和整数进行比较
   8. 谨慎使用 in 和 not in，考虑是否能用 between，exists，not exists 代替
   9. 复合索引，必须使用该索引中的第一个字段作为条件
2. 索引相关
   - 索引越小越好
   - [选择性高的列放在索引的前面](https://blog.csdn.net/li_canhui/article/details/86487693)
   - 索引不要包含太长的字段，可考虑前缀索引：ALTER TABLE <table_name> ADD INDEX idx_content(content(6))
   - 索引不是越多越好
      - 索引也占用空间，还需要定期维护索引
      - DML 操作需要重建索引
      - DQL 操作优化器选择使用哪一个索引需要时间
3. [避免使用 select *](https://www.cnblogs.com/MrYuChen-Blog/p/13936680.html)
   1. 增加网络开销
   2. 大字段(长度超过728字节)，会先把超出的数据序列化到另外一个地方，等于多增加一次 IO 操作
   3. 失去了覆盖索引的可能性
4. 小表驱动大表
   - JOIN 小表连大表
   - IN 小表内大表外
   - EXISTS 小表外大表内
5. [避免使用子查询和 join](https://blog.csdn.net/weixin_38676357/article/details/81510079)
6. [使用 explain 查看执行计划](https://tonydong.blog.csdn.net/article/details/103579177)
7. 不要使用 OFFSET 实现分页
8. 垂直分表
   - 不常用的字段
   - 大字段
   - 经常一起使用的字段
9. 水平分表：mod(id, 5)；查询用分表，统计用总表
10. [表分区](https://www.cnblogs.com/zhouguowei/p/9360136.html)
>#### 参考
>1. [SQL 性能优化梳理](https://juejin.cn/post/6844903494504185870)
>2. [SQL 语句性能优化](https://www.cnblogs.com/SimpleWu/p/9929043.html) 39~
>3. [SQL 语句性能优化](https://www.cnblogs.com/zhangtan/p/7440960.html)
>4. [mysql建立索引的几大原则](https://zhuanlan.zhihu.com/p/88963084)
>5. [MySQL 之覆盖索引](https://zhuanlan.zhihu.com/p/145119015)
>6. [联合索引的最左前缀匹配原则](https://blog.csdn.net/i_silence/article/details/106499430)
>7. [MySQL 之全文索引](https://zhuanlan.zhihu.com/p/35675553)
>8. [记一次关于 Mysql 中 text 类型和索引问题引起的慢查询的定位及优化](https://blog.csdn.net/zdplife/article/details/94607896)
>9. [MySql千万级limit优化方案](https://www.jianshu.com/p/f46b0f3d296b)
>10. [索引失效分析、in与exists使用场合](https://www.cnblogs.com/zjxiang/p/9160810.html)
>11. [复合索引的优点和注意事项](https://www.cnblogs.com/zjdxr-up/p/8319881.html)
---
## 各层命名规约（阿里编程规约）
1. Service/DAO 层方法命名规约
   1. 获取单个对象的方法用 get 做前缀。
   2. 获取多个对象的方法用 list 做前缀，复数结尾，如：listObjects。 
   3. 获取统计值的方法用 count 做前缀。 
   4. 插入的方法用 save/insert 做前缀。
   5. 删除的方法用 remove/delete 做前缀。
   6. 修改的方法用 update 做前缀。
2. 领域模型命名规约
   1. 数据对象：xxxDO，xxx 即为数据表名。
   2. 数据传输对象：xxxDTO，xxx 为业务领域相关的名称。
   3. 展示对象：xxxVO，xxx 一般为网页名称。
   4. POJO 是 DO/DTO/BO/VO 的统称，禁止命名成 xxxPOJO。
---
## 分层领域模型规约【参考】
1. DO（Data Object）：此对象与数据库表结构一一对应，通过 DAO 层向上传输数据源对象。
2. DTO（Data Transfer Object）：数据传输对象，Service 或 Manager 向外传输的对象。
3. BO（Business Object）：业务对象，可以由 Service 层输出的封装业务逻辑的对象。
4. Query：数据查询对象，各层接收上层的查询请求。注意超过 2 个参数的查询封装，禁止使用 Map 类来传输。
5. VO（View Object）：显示层对象，通常是 Web 向模板渲染引擎层传输的对象。
---