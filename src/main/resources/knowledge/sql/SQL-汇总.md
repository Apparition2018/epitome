# SQL  

---
## 参考网站
1. [SQL Fiddle](http://sqlfiddle.com/)
2. [SQL Tutorial](https://www.techonthenet.com/sql/)
3. [建表规范](https://www.cnblogs.com/xphdbky/p/7154434.html)
4. [SQL Server 常用数据类型](https://www.cnblogs.com/mcgrady/p/3840812.html)
## 问题
1. [MySQL, SQL Server, Oracle 的区别](https://www.cnblogs.com/cherxu/p/6856262.html)
2. [distinct 和 group by 的去重逻辑浅析](https://www.cnblogs.com/dancesir/p/7505730.html)
3. [select 和 in 的区别](https://www.cnblogs.com/emilyyoucan/p/7833769.html)
4. [varchar 与 nvarchar](https://www.cnblogs.com/Jashinck/p/8384388.html)
>### 优化
>1. [SQL语句性能优化](https://www.cnblogs.com/SimpleWu/p/9929043.html)
>2. [SQL语句性能优化](https://www.cnblogs.com/zhangtan/p/7440960.html)
>### MySQL
>1. [使用 mysql 应该注意的细节](https://www.cnblogs.com/zhangyiqinga/p/9753484.html)
>2. [delimiter 的用法和作用](https://blog.csdn.net/langkeziju/article/details/14446671)
>3. [MySQL 中实现 rank 排名查询](https://blog.csdn.net/justry_deng/article/details/80597916)
---
## 数据库术语
- 元组：表中的一行即为一个元组
- 分量：元组中的一个属性值，称为分量
- 码：关系中某个属性或属性组，能唯一标识每个元组
- 超码：关系中的一个码移去某个属性，仍然是这个关系的码，则这样的码为该关系的超码
- 候选码：关系中的一个码移去某个属性后，就不是这个关系的码，则这样的码为该关系的候选码；即关系的最小超码
- 主属性：关系中的任一个候选码中的属性
>#### 参考：[关系数据库基本术语](https://www.cnblogs.com/dmeck/p/10507936.html)
---
## 函数依赖
1. 函数依赖：学号 → 姓名，姓名函数依赖于学号
2. 完全函数依赖：学号 F→ 姓名，（学号，课名）F→ 分数
3. 部分函数依赖：（学号，课名）P→ 姓名
4. 传递函数依赖：学号 → 系名，系名 → 系主任，则 学号 T→ 系主任
---
## 设计范式
- 数据库范式分为 1NF，2NF，3NF，BCNF，4NF，5NF，一般考虑到 BCNF 就可以了。符合高一级范式的设计，必定符合低一级范式。
- 解决了数据冗余过大，插入异常，修改异常，删除异常问题
1. 1NF：符合 1NF 的关系中的每个属性都不可再分
2. 2NF：在 1NF 的基础上，消除非主属性对于码的部分函数依赖
    1. 找出数据表中所有的码
    2. 根据第一步所得到的码，找出所有的主属性
    3. 除去所有的主属性，剩下的就都是非主属性
    4. 查看是否存在非主属性对码的部分函数依赖
3. 3NF：在 2NF 的基础上，消除非主属性对于码的传递函数依赖
4. BCNF：在 3NF 的基础上，消除主属性对于码的部分与传递函数依赖
>#### 参考
>   1. [什么是第一，第二，第三范式](https://blog.csdn.net/xidianliuy/article/details/51566576)
>   2. [如何理解关系型数据库的常见设计范式？](https://www.zhihu.com/question/24696366/answer/29189700)
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
## 各层命名规约【参考】
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