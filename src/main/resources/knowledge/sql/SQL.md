# SQL  

---
## 参考网站
1. [SQL Fiddle](http://sqlfiddle.com/)
2. [SQL Tutorial](https://www.techonthenet.com/sql/)
3. [建表规范](https://www.cnblogs.com/xphdbky/p/7154434.html)
4. [SQL 语法速成手册](https://mp.weixin.qq.com/s?__biz=MzU2MTI4MjI0MQ==&mid=2247491550&idx=2&sn=cfe8ed6eea2e61646e5cc3d0b5e96b68)
## 课程
1. [数据库设计那些事](https://www.imooc.com/learn/117)
---
## 问题
1. [MySQL, SQL Server, Oracle 的区别](https://www.cnblogs.com/cherxu/p/6856262.html)
2. [distinct 和 group by 的去重逻辑浅析](https://www.cnblogs.com/dancesir/p/7505730.html)
3. [select 和 in 的区别](https://www.cnblogs.com/emilyyoucan/p/7833769.html)
4. [varchar 与 nvarchar](https://www.cnblogs.com/Jashinck/p/8384388.html)
5. [当要给字符串创建前缀索引时，如何确定我应该使用多长的前缀呢？](https://blog.csdn.net/qq_38670588/article/details/108499966)
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
   1. 考虑在 where，order by，group by，多表关联涉及的列上建立索引
   2. 避免使用 is null 和 is not null，建议建表时设置默认值
   3. 避免使用 != 和 <>
   4. 避免使用 or 来连接条件，可以使用 union 合并查询
   5. 避免使用 in 和 not in，考虑是否能用 between，exists，not exists 代替
   6. 避免使用 前置% like，如 like "%xyz" 和 like "%xyz%"；可使用 locate('x', 'field') > 0 代替，或数据库提供的全文检索功能和专门的全文搜索引擎
   7. 避免使用 参数，如 num=@num
   8. 避免对索引进行表达式和函数操作，num/2=100 可以改为 num=100*2，包括数据类型不匹配，如字符串和整数进行比较
   9. 使用 limit
   10. 复合索引，必须使用该索引中的第一个字段作为条件
2. 索引相关
   - [选择性高的列放在索引的前面](https://www.cnblogs.com/lty-fly/p/10693849.html)
   - 索引不要包含太长的字段，可考虑前缀索引
   - 索引过多不但会降低写效率，可能还会降低读的效率
   - 定期维护索引碎片
   - MYSQL 不要使用强制索引关键字
3. [避免使用 select *](https://www.cnblogs.com/MrYuChen-Blog/p/13936680.html)
   1. 增加网络开销
   2. 大字段(长度超过728字节)，会先把超出的数据序列化到另外一个地方，等于多增加一次 IO 操作
   3. 失去了覆盖索引的可能性
4. [使用 explain 查看执行计划](https://tonydong.blog.csdn.net/article/details/103579177)
5. [表分区](https://www.cnblogs.com/zhouguowei/p/9360136.html)
6. [hint](https://www.cnblogs.com/jpfss/p/11490765.html)
>#### 参考
>1. [SQL 性能优化梳理](https://zhuanlan.zhihu.com/p/85724757)
>2. [SQL性能优化的最佳21条经验](https://zhuanlan.zhihu.com/p/21956773)
>3. [SQL语句性能优化](https://www.cnblogs.com/SimpleWu/p/9929043.html)
>4. [SQL语句性能优化](https://www.cnblogs.com/zhangtan/p/7440960.html)
>5. [索引：SQL 性能优化](https://zhuanlan.zhihu.com/p/145119015)
>6. [SQL 优化极简法则](https://zhuanlan.zhihu.com/p/269434753)
>7. [MySQL 之全文索引](https://zhuanlan.zhihu.com/p/35675553)
>8. [记一次关于 Mysql 中 text 类型和索引问题引起的慢查询的定位及优化](https://blog.csdn.net/zdplife/article/details/94607896)
---
## 各层命名规约（阿里编程规约）
1. Service/DAO层方法命名规约
    1. 获取单个对象的方法用get做前缀。
    2. 获取多个对象的方法用list做前缀，复数形式结尾如：listObjects。
    3. 获取统计值的方法用count做前缀。
    4. 插入的方法用save/insert(batch)做前缀。
    5. 删除的方法用remove/delete做前缀。
    6. 修改的方法用update做前缀。
2. 领域模型命名规约
    1. 数据对象：xxxDO，xxx即为数据表名。
    2. 数据传输对象：xxxDTO，xxx为业务领域相关的名称。
    3. 展示对象：xxxVO，xxx一般为网页名称。
    4. POJO是DO/DTO/BO/VO的统称，禁止命名成xxxPOJO。
---
## 分层领域模型规约【参考】
- DO（Data Object）：此对象与数据库表结构一一对应，通过DAO层向上传输数据源对象。
- DTO（Data Transfer Object）：数据传输对象，Service或Manager向外传输的对象。
- BO（Business Object）：业务对象，由Service层输出的封装业务逻辑的对象。
- AO（Application Object）：应用对象，在Web层与Service层之间抽象的复用对象模型，极为贴近展示层，复用度不高。
- VO（View Object）：显示层对象，通常是Web向模板渲染引擎层传输的对象。
- Query：数据查询对象，各层接收上层的查询请求。注意超过2个参数的查询封装，禁止使用Map类来传输。
---