# MySQL

---
## 参考网站
1. [MySQL :: MySQL 8.0 Reference Manual](https://dev.mysql.com/doc/refman/8.0/en/)
2. [MySQL :: Sakila Sample Database](https://dev.mysql.com/doc/sakila/en/)
3. [MySQL 规范](https://blog.csdn.net/shenjian58/article/details/89850405)
4. [MySQL 总结](https://mp.weixin.qq.com/s/pWHCieOwAdCrz8cauduWlQ)
---
## [安装](https://blog.csdn.net/weixin_42109012/article/details/94443391)
- mysql 5.6 不需要执行 mysqld --initialize
- [my.ini](https://www.cnblogs.com/missmeng/p/13404228.html)
```
[mysqld]
# 设置3306端口
port=3306
# 自定义设置mysql的安装目录，即解压mysql压缩包的目录
basedir=/usr/local/mysql-5.7
# 自定义设置mysql数据库的数据存放目录
datadir=/usr/local/mysql-5.7/data
# 允许最大连接数
max_connections=200
# 允许连接失败的次数，这是为了防止有人从该主机试图攻击数据库系统
max_connect_errors=10
# 服务端使用的字符集默认为UTF8
character-set-client-handshake=FALSE
character-set-server=utf8mb4
collation-server = utf8mb4_bin
init_connect='SET NAMES utf8mb4'
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
# 默认使用mysql_native_password插件认证
default_authentication_plugin=mysql_native_password
# 表名大小写是否敏感
# 0：Unix 和 Linux 默认，大小写敏感
# 1：Windows 默认，存储转为小写，大小写不敏感
# 2：OS X 默认，大小写不敏感
lower_case_table_names=1
##
performance_schema=off
table_definition_cache=400
table_open_cache=64
innodb_buffer_pool_chunk_size=64M
innodb_buffer_pool_size=64M
max_allowed_packet=16M

[mysql]
# 设置mysql客户端默认字符集
default-character-set=utf8mb4

[client]
# 设置mysql客户端连接服务端时默认使用的端口和默认字符集
port=3306
default-character-set=utf8mb4
```
---
## [MySQL 术语](https://dev.mysql.com/doc/refman/8.0/en/glossary.html)
- dirty page：脏页，Buffer Pool 中更改过，且未 written/flushed 到数据文件的页
- extent：tablespace 的一组页，1页默认16KB，1 extent 包含64页
- read-ahead：预读，预先读取一组页面到 Buffer Pool
    1. linear read-ahead：线性预读
    2. random read-ahead：随机预读
---
## MySQL8 新特性
- [window functions](https://www.jb51.net/article/129447.htm)
---
## [InnoDB 存储引擎](https://dev.mysql.com/doc/refman/8.0/en/innodb-storage-engine.html)
|                  |     |                                                               |
|------------------|-----|---------------------------------------------------------------|
| Row              | 行   | 由一组列定义的逻辑数据结构，每一页面可以包含一行或多行                                   |
| Page             | 页   | 任意时刻在磁盘(数据文件)和内存(缓冲池)之间传输多少数据的单位；<br/>一页可以包含一行或多行；默认页面大小为16KB |
| Extent           | 区   | 表空间内的一组页。对于默认页面大小为KB的页，一区包含64个页                               |
| page compression | 页压缩 | 允许对位于表文件表空间中的InnoDB表进行页级压缩                                    |
### InnoDB vs MyISAM
| 对比              | InnoDB             | MyISAM          |
|:----------------|:-------------------|:----------------|
| 如何选择            |                    | 低并发查询为主的应用      |
| 事务              | 支持                 | 不支持             |
| 锁               | 行锁、表锁              | 表锁              |
| 主键              | 没有会自动生成<br/>推荐自增主键 | 可以没有            |
| 聚簇索引            | 是                  | 否               |
| 缓存              | 数据和索引              | 索引              |
| 全文索引            | 5.6后支持             | 支持              |
| hash 索引         | 不能主动创建             | 不支持             |
| 外键              | 支持                 | 不支持             |
| select count(*) | 较慢，索引扫描            | 很快，有一个变量保存了表的行数 |
| 压缩              | 不支持                | 支持              |
| 存储文件            | .frm .idb          | .frm .myd .myi  |
| 记录存储顺序          | 主键大小               | 插入顺序            |
>- [MyISAM 为什么查询比 InnoDB 快](https://mp.weixin.qq.com/s/Nisohn7GyuvUX6xdp8YaXw)
### [InnoDB 多版本](https://dev.mysql.com/doc/refman/8.0/en/innodb-multi-versioning.html)
| 字段          | 说明                  |
|:------------|:--------------------|
| DB_TRX_ID   | 记录插入或更新行得事务的事务ID    |
| DB_ROLL_PTR | 回滚指针，指向 undo log 记录 |
| DB_ROW_ID   | 行ID                 |
>- [MySQL MVCC](https://blog.csdn.net/Waves___/article/details/105295060)
### [InnoDB 内存结构](https://dev.mysql.com/doc/refman/8.0/en/innodb-in-memory-structures.html)
1. [Buffer Pool](https://mp.weixin.qq.com/s/nA6UHBh87U774vu4VvGhyw) ：缓冲池
    - 缓存数据和索引
    - 变种 LRU 算法
2. [Change Buffer](https://mp.weixin.qq.com/s/PF21mUtpM8-pcEhDN4dOIw) ：写缓冲
    - 5.5 之前叫 Insert Buffer，只对 INSERT 做了优化，现在对 UPDATE 和 DELETE 也有效
    - 缓存对二级索引页的更改，前提是这些页不在 Buffer Pool。
      当这些页因为其它读操作加载到 Buffer Pool时，与之合并
3. [Adaptive Hash Index](https://mp.weixin.qq.com/s/vQsilZmyMaaVkYGkn-WXew) ：自适应哈希索引
    - InnoDB 无法主动创建 Hash Index
    - InnoDB 自行判断是否创建 Hash Index
4. [Log Buffer](https://mp.weixin.qq.com/s/-Hx2KKYMEQCcTC-ADEuwVA) ：日志缓冲
    - 缓冲要写入磁盘日志文件的数据
### [InnoDB 磁盘结构](https://dev.mysql.com/doc/refman/8.0/en/innodb-on-disk-structures.html)
1. Tablespaces：表空间，保存一个或多个 InnoDB 表和相关联索引的数据文件
2. [Doublewrite Buffer](https://mp.weixin.qq.com/s/bkoQ9g4cIcFFZBnpVh8ERQ) ：双写缓冲
    - 解决的问题：Dirty Page(16KB) 写入磁盘(1页4KB)失败，可从中找到副本恢复错误
    - 两部分：①内存；②双写文件
    - 步骤：Dirty Page → 内存双写缓冲 → ①双写文件双写缓冲；②InnoDB 数据文件
3. Redo Log：重做日志
### [Innodb 索引类型]( https://dev.mysql.com/doc/refman/8.0/en/innodb-index-types.html)
1. 聚簇索引：Clustered Indexes
    - 索引树的叶子节点存储：①索引；②行记录（索引与行记录一起存储，所以称之为聚簇）
    - 如何选择聚簇索引：①主键；②第一个 NOT NULL UNIQUE 列；③自动生成隐藏 GEN_CLUST_INDEX
2. 二级索引：Secondary Indexes
    - 索引树的叶子节点存储：①索引；②对应聚簇索引值
>- [索引的数据结构模型及维护 | 妲个己吧](https://mp.weixin.qq.com/s/hmolnmi7WU0ZhQ9X5IR1JA)
>- [小林coding](https://mp.weixin.qq.com/s/LTX67XxkWcAeYUyLh_5b4g)
>- [架构师之路](https://mp.weixin.qq.com/s/woz5lkQwyJZNmoiiJZy7NA)
---
## MySQL 调优
1. [Optimization](https://dev.mysql.com/doc/refman/8.0/en/optimization.html)
2. [SQL 性能优化梳理](https://juejin.cn/post/6844903494504185870)
3. [字符串索引前缀长度](https://blog.csdn.net/qq_38670588/article/details/108499966)
### 优化建议
- 索引相关
    - [索引方法](https://dev.mysql.com/doc/refman/8.0/en/index-btree-hash.html)
        1. B+Tree：[最左前缀原则 (Leftmost Prefix Principle)](https://www.cnblogs.com/-mrl/p/13230006.html)
            1. 从左到右匹配知道遇到范围查询 (>, <, between, like) 停止匹配，建议范围查询放最后
            2. in 和 = 可以乱序
        2. Hash：所有索引列；不支持排序；只支持等值查询：=，<=>
    - 表数据太少不需要添加索引
    - 索引的数据类型越小效率越高
    - 索引不要包括太长的数据类型，使用前缀索引代替：ALTER TABLE <table_name> ADD INDEX idx_content(content(6))
    - 索引不是越多越好
        - 索引也占用空间，还需要定期维护索引
        - DML 操作需要重建索引
        - DQL 操作优化器选择使用哪一个索引需要时间
    - [索引选择性高的列放在索引的前面](https://www.cnblogs.com/liyasong/p/mysql_xuanzexing_index.html)
    - [组合索引 (Composite Indexes)](https://www.cnblogs.com/zjdxr-up/p/8319881.html)
    - [索引下推 (Index Condition Pushdown)](https://dev.mysql.com/doc/refman/8.0/en/index-condition-pushdown-optimization.html)
        - [索引下推](https://blog.csdn.net/LBWNB_Java/article/details/120348886)
    - [覆盖索引 (Covering Indexes)](https://mp.weixin.qq.com/s/y0pjtNUZhOW2ZBOy4m-xsA)
        - 如何实现：将被查询的字段、条件字段、排序字段等，建立到联合索引里去
        - explain extra 显示 Using index
    - [降序索引 (Descending Indexes)](https://dev.mysql.com/doc/refman/8.0/en/descending-indexes.html)
    - [MySQL8 三大索引](https://www.mdnice.com/writing/ca72a1892384484aa67bc37398dea3b8) 
    - 查找重复索引及冗余索引
        1. 语句查询
        ```mysql
        use information_schema;
    
        select s1.table_schema, s1.table_name, s1.index_name as index1, s2.index_name as index2, s1.column_name as dup_col 
        from statistics s1
        join statistics s2 on s1.table_schema = s2.table_schema and s1.table_name = s2.table_name and s1.seq_in_index = s2.seq_in_index and s1.column_name = s2.column_name
        where s1.seq_in_index = 1 and s1.index_name <> s2.index_name;
        ```
        2. pt-duplicate-key-checker
    - 删除不用的索引
        - pt-index-usage
- 避免全表扫描：①表无索引；②放弃使用索引：成本是否够小，比如数据量小的表，!= 和 is null 等也可能使用索引
    1. 在 ①where ②order by ③group by ④多表关联涉及的列 上建立索引
    2. 避免使用 is null 和 is not null，建表时尽量设置 not null
    3. 避免使用 != 和 <>
    4. 避免使用 左% like，如 field like "%x" 和 field like "%x%"；代替：①locate('x', field) > 0，②[FULLTEXT](https://juejin.cn/post/6969887148036063239) ，③全文搜索引擎
    5. 避免对字段进行操作，①表达式 ②函数 ③隐式转换 ④手动转换
    6. [避免 join 表字符编码不同](https://mp.weixin.qq.com/s/1Sowt2TcjMGDv55OQOe2rQ)
    7. 避免使用[变量](https://www.cnblogs.com/Brambling/p/9259375.html) ，如 where field = @num;
    8. 谨慎使用 in 和 or，使用 union/union all 代替
        - 当返回数据过多导致应用堆内存溢出时，in 和 or 不走索引：@see SQL 语句-9
        - [or vs in](https://www.cnblogs.com/chengho/p/16149979.html)：当字段没有索引，随着查询条件的增多，in 的性能明显优于 or
- [避免使用 select *](https://www.cnblogs.com/MrYuChen-Blog/p/13936680.html)
    1. 增加网络开销，
    2. 大字段(长度超过728字节)，会先把超出的数据序列化到另外一个地方，等于多增加一次 IO 操作
    3. 失去了覆盖索引的可能性
- [避免使用子查询和 join](https://blog.csdn.net/weixin_38676357/article/details/81510079)
    - ①避免使用子查询，用 join 代替；②OLTP 场景也避免使用 join
    - [联表查询 和 单表查询+业务层处理 比较](https://www.zhihu.com/question/68258877)
- 避免使用 having：查询语句执行顺序是 where → 聚合操作 → having，尽量在 where 过滤数据
- [小表驱动大表](https://blog.csdn.net/qq_20891495/article/details/93744495)
    - [根据驱动表优化查询](https://www.cnblogs.com/zhengyun_ustc/p/slowquery1.html)
    - [in 小表内大表外，exists 小表外大表内](https://www.cnblogs.com/zjxiang/p/9160810.html)
- 适当的反范式：适当增加冗余数据
- 汇总表/缓存表：定时生成数据，用于用户耗时时间长的操作
- [offset 和 limit 分页优化](https://juejin.cn/post/6844903939247177741)：@see 索引规约-7
- 垂直分表：①不常用的字段 ②大字段 ③经常一起使用的字段
- 水平分表：mod(id, 5)；查询用分表，统计用总表
- [表分区](https://www.cnblogs.com/zhouguowei/p/9360136.html)
### [数据类型选择](https://mp.weixin.qq.com/s/IEyaazf_Z1fTObsmCizelQ)
1. 优先选择小的数据类型：数值 > 日期时间 > 字符串`
2. 用整型保存 IP：`INET_ATON('192.168.0.1')`，`INET_NTOA(3232235521)`
3. 实数
```
bigint          效率快，空间小
decimal         精确定点数
double          双精度浮点数
float           单精度浮点数
```
4. [日期时间](https://blog.csdn.net/assember/article/details/90941451)
    - [日期时间类型怎么选择](https://mp.weixin.qq.com/s/G27sFUYdC7PgJnx5IBuRwQ)
```
datetime        8字节             1000-01-01 00:00:00.000000  9999-12-31 23:59:59.999999
timestamp       4字节     UTC     1970-01-01 00:00:01.000000  2038-01-19 03:14:07.999999
```
5. 字符串
```
char            较短的字符串      频繁更新
varchar         较长的字符串      很少更新
text            大于
```
6. 枚举：enum 或 set
>- [MySQL 数据类型选择](https://blog.csdn.net/weixin_39583222/article/details/113140641)
### explain
- [type](https://blog.csdn.net/lilongsy/article/details/95184594) ：连接类型

| type   | 说明                                                                |
|:-------|:------------------------------------------------------------------|
| system | MyISAM 且只有一行记录，const 的特例                                          |
| const  | pk 或 unique not null 索引的等值查询，只有一行命中                               |
| eq_ref | pk 或 unique not null 索引的等值关联查询，对于前表的每一行，后表只有一行命中                  |
| ref    | 非 unique 索引的等值查询，多行命中                                             |
| range  | 索引上的范围扫描；如：=, <>, >, >=, <, <=, IS NULL, <=>, BETWEEN, LIKE, IN() |
| index  | ①覆盖索引，Using index；②索引树的全表扫描                                       |
| ALL    | 全表扫描                                                              |
- possible_keys：可选择使用的索引
- key：实际选择使用的索引
- [key_len](https://www.cnblogs.com/lukexwang/articles/7060950.html) ：使用索引的长度
- ref：索引中的使用了的列
- rows：估计需要查询的行数
- extra：附加信息
    
| extra                    | 说明                                                              | 优化              |
|:-------------------------|:----------------------------------------------------------------|:----------------|
| Using index              | 在一棵索引树上获取所有所需数据，无需额外查询来读取实际行                                    ||
| Using index condition    | 在一棵索引树上获取不到所有所需数据，需要额外查询来读取实际行                                  ||
| Using where; Using index | 同 Using index，但 where 条件不是索引的前导列                                ||
| Using filesort           | 需对结果集进行额外文件排序操作<br/>原因：①order by 没有索引；②结果集大小超过 sort_buffer_size | order by 字段添加索引 |
| Using temporary          | 需创建临时表暂存中间结果                                                    ||
| Using join buffer        | 需创建连接缓冲区暂存中间结果                                                  | 关联字段添加索引        |
>- [EXPLAIN Output Format](https://dev.mysql.com/doc/refman/8.0/en/explain-output.html)
>- [EXPLAIN 百科](https://mp.weixin.qq.com/s/QCJq1o-CWbNNwnuzJmVEPg)
>- [Using index vs Using where](https://www.cnblogs.com/wy123/p/7366486.html)
## [慢查询](https://dev.mysql.com/doc/refman/8.0/en/slow-query-log.html)
- 开启慢查询日志
```mysql
-- 是否开启慢查询
set global slow_query_log = on;
-- 记录查询时间超过多少秒的慢查询，最小0秒，默认10秒
set global long_query_time = 1;
-- 是否记录没使用索引或全索引扫描的查询
set global log_queries_not_using_indexes = on;
-- 最少检查行限制
set global min_examined_row_limit = 0;
-- 慢查询日志文件
set global slow_query_log_file = '';
```
- 慢查询日志存储格式
```
# Time: 2021-09-13T04:00:44.885048Z
# User@Host: root[root] @  [172.17.0.1]  Id:    12
# Query_time: 0.000205  Lock_time: 0.000099 Rows_sent: 2  Rows_examined: 2
SET timestamp=1631505644;
select * from store limit 10;
```
- 慢查询日志分析工具
    1. [mysqldumpslow](https://dev.mysql.com/doc/refman/8.0/en/mysqldumpslow.html)
    ```
    # 查看帮助
    mysqldumpslow -h
    mysqldumpslow [options] [log_file ...]
    ```
    2. pt-query-digest
    ```
    # 查看帮助
    pt-query-digest -h
    # 输出到文件
    pt-query-digest slow.log > slow_log.report
    # 输出到数据库表
    pt-query-digest slow.log -review h=127.0.0.1,D=test,p=root,P=3306,u=root,t=query_view \
    --create-reviewtable --review-history t=hostname_slow
    ```
    3. 关注哪些慢查询？
    ```
    1. 次数多且占用时间长的 SQL
    2. IO 大的 SQL                    pt-query-digest 的 Rows examine
    3. 未命中索引的 SQL                pt-query-digest 的 Rows examine 和 Rows Send 的对比
    ```
- 慢查询优化：①正确加索引；②去除查询不需要的列；③数据量太大考虑分表
### 配置
1. 系统配置
    - 网络方面，修改 /etc/sysctl.conf
    ```
    # 增加 tcp 支持的队列数
    net.ipv4.tcp_max_syn_backlog = 65535
    # 减少断开连接时，资源回收
    net.ipv4.tcp_max_tw_buckets = 8000
    net.ipv4.tcp_tw_reuse = 1
    net.ipv4.tcp_tw_recycle = 1
    net.ipv4.tcp_fin_timeout = 10
    ```
    - 打开文件数限制：ulimit -a 查看目录的各位限制，可以修改 /etc/security/limits.conf
    ```
    soft nofile 65535
    hard nofile 65535
    ```
    - 关闭防火墙 iptables, selinux
2. MySQL 配置
- 查找配置文件的顺序：/usr/sbin/mysqld --verbose --help | grep -A 1 'Default options'
```
innodb_buffer_pool_size            # 缓冲池大小；如果只有 innodb 表，推荐配置为总内存的75%
innodb_buffer_pool_instances       # 缓冲池的个数，默认1个
innodb_flush_log_at_trx_commit     # 0：每秒 flush | 1：每次 flush | 2：每次刷到缓冲区每秒 flush；默认1，推荐2
innodb_log_buffer_size             # 能存在一秒日志即可
innodb_read_io_threads             # 推荐 CPU 核心数和读写情况决定
innodb_write_io_threads
innodb_file_per_table              # 每个表使用独立的表空间，默认 OFF，推荐 ON
innodb_stats_on_metadata           # 什么情况下刷新 innodb 表的统计信息
```
3. 第三方配置工具：[Percona Configuration Wizard](https://tools.percona.com/wizard)
---
## [InnoDB 锁和事务模型](https://dev.mysql.com/doc/refman/8.0/en/innodb-locking-transaction-model.html)
### [InnoDB 锁](https://dev.mysql.com/doc/refman/8.0/en/innodb-locking.html)
1. 共享/排它锁 (Shared and Exclusive Locks)
    1. 共享锁 (Shared Locks, S)：
        - 对符合条件的行加S锁，其它事务可以对这些记录添加IS锁和S锁，即其它事务可以读取这些数据但无法修改
    2. 排它锁 (Exclusive Locks, X)：
        - 对符合条件的行加X锁，其它事务无法对这些记录加任何IS锁和IX锁，即其它事务无法对这些记录进行读取和修改
        - 不会阻止非锁定读
2. 意向锁 (Intention Locks)：表级锁，指示事务稍后需要对表中的行使用哪种类型的锁(共享/排它)
    1. 意向共享锁 (Intention Shared Locks, IS)：`SELECT … FOR SHARE`
    2. 意向排它锁 (Intention Exclusive Locks, IX)：[`SELECT … FOR UPDATE`](https://www.cnblogs.com/xiao-lei/p/12598552.html)
        
|     | X   | IX  | S   | IS  |
|:----|:----|:----|:----|:----|
| X   | 冲突  | 冲突  | 冲突  | 冲突  |
| IX  | 冲突  | 兼容  | 冲突  | 兼容  |
| S   | 冲突  | 冲突  | 兼容  | 兼容  |
| IS  | 冲突  | 兼容  | 兼容  | 兼容  |
3. 记录锁 (Record Locks)：`SELECT c1 FROM t WHERE c1 = 10 FOR UPDATE`
    - 总是锁定索引记录，阻止其它事务插入、更新或删除  
4. [间隙锁](https://www.jianshu.com/p/32904ee07e56) (Gap Locks)：`SELECT c1 FROM t WHERE c1 BETWEEN 10 and 20 FOR UPDATE`  
    - 对索引记录之间的间隙的锁，或是对第一个索引之前或最后一个索引记录之后的间隙的锁，防止其他事物插入数据
    - 不同事务允许持有冲突的间隙锁，容易导致死锁
    - 隔离级别为 REPEATABLE READ 或 SERIALIZABLE，间隙锁生效
5. 临键锁 (Next-key Locks)
    - 记录锁 + 索引记录之前的间隙锁，左开右闭
    - 隔离级别为 REPEATABLE READ 或 SERIALIZABLE，临键锁生效
6. 插入意向锁 (Insert Intention Locks)
7. 自增锁 (AUTO-INC Locks)
>- [MySQL select 加锁分析](https://www.cnblogs.com/rjzheng/p/9950951.html)
>- [InnoDB 的七种锁](https://mp.weixin.qq.com/s/f4_o-6-JEbIzPCH09mxHJg)
>- [InnoDB 锁到底锁的是什么](https://mp.weixin.qq.com/s/fmSHG0SejfD0IdnpIYHT9w)
### RR 下的加锁规则
- 原则：
    - 加锁基本单位 Next-key Locks
    - 查找过程中访问到的对象会加锁
- 索引上等值查询优化：
    - 给唯一索引加锁时，Next-key Locks → Record Locks
    - 向右遍历且最后一个值不满足等值条件时，Next-key Locks → Gap Locks
    - 对于覆盖索引查询，不对聚簇索引加锁
- 唯一索引上的范围查询会访问到不满足条件的第一个值为止
### 非锁定读 vs 锁定读
| Read  | [Consistent Nonlocking Reads](https://dev.mysql.com/doc/refman/8.0/en/innodb-consistent-read.html) | [Locking Reads](https://dev.mysql.com/doc/refman/8.0/en/innodb-locking-reads.html) |
|:------|:---------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------|
| 中文    | 一致性非锁定读，快照读                                                                                        | 锁定读                                                                                |
| 语句    | select                                                                                             | select … for share<br/>select … for update                                         |
| 说明    | 只能读当前事务之前更改                                                                                        | 能读最新的更改                                                                            |
| 是否会幻读 | 不可能                                                                                                | 可能                                                                                 |
| 隔离级别  | RC、RR                                                                                              |                                                                                    |
---
## [复制](https://dev.mysql.com/doc/refman/8.0/en/replication.html)
1. 创建 bin_log/ 和 relay_log/ 文件夹，并更改其所属用户组和用户为 mysql
2. master my.cnf
```
# https://dev.mysql.com/doc/refman/8.0/en/replication-options-binary-log.html
# 开启binlog，binlog文件名称
log-bin=/usr/local/mysql-5.7/bin_log/log-bin
# binlog记录格式：MIXED、STATEMENT、ROW
binlog_format=ROW
# format为ROW时生效，信息日志时间写入binlog
binlog_rows_query_log_events=ON
# binlon自动删除天数
expire_logs_days=30
# binlong单个日志文件最大大小，最大和默认为1G
max_binlog_size=1024M

# https://dev.mysql.com/doc/refman/8.0/en/replication-options-replica.html
server-id=1
# relay log文件名称
relay_log=/usr/local/mysql-5.7/relay_log/relay-bin
# 忽略不需要同步的数据库
replicate-ignore-db=information_schema
replicate-ignore-db=mysql
replicate-ignore-db=performance_schema
replicate-ignore-db=sys

# https://dev.mysql.com/doc/refman/8.0/en/replication-options-gtids.html
# 开启gtid
enforce_gtid_consistency=ON
gtid_mode=ON
```
3. slave my.cnf
```
log-bin=/usr/local/mysql-5.7/bin_log/log-bin2
service-id=2
relay_log=/usr/local/mysql-5.7/relay_log/relay-bin2
```
4. 连接 master
```mysql
-- 创建 repl 账号，允许从 192.168.0.141 访问，密码为 repl
grant replication slave on *.* to repl@192.168.0.141 identified by 'repl';
flush privileges;
-- 重置 master，初次配置时可以使用
reset master;
-- 查看 master 信息
show master status;
```
5. 连接 slave
```mysql
-- 停止 slave
stop slave;
-- 将 192.168.0.141:3306 设为 master
-- master_user, master_password，使用 master 创建的 repl 账号
-- master_log_file, master_log_pos 是在 master 使用 show master status 语句查看到的 File 和 Position
change master to master_host='192.168.0.141', master_port=3306, master_user='repl',
    master_password='repl', master_log_file='log-bin.000001', master_log_pos=154;
-- 开启 slave
start slave;
-- 查看 slave 状态，Slave_IO_Running 和 Slave_SQL_Running 都为 Yes 时，表示成功
-- 如果不成功，可查看 data 目录下 *。err 日志文件
show slave status;
-- 重置 slave，slave 异常时可以使用
reset slave all;
```
>- [MySQL 双主配置](https://www.jb51.net/article/252651.htm)
---
## 其它
1. [delimiter](https://www.jb51.net/article/146693.htm)：分隔符
2. [为什么 varchar 默认是 255](https://www.jianshu.com/p/83bdcf9bd5a8)
    - varchar 有一个长度前缀表示字节数
        1. varchar <= 255个字节时，长度前缀为1个字节
        2. varchar >  255个字节时，长度前缀为2个字节
3. 变更大表表结构
    1. gh-ost：5.5 | 5.6 | 5.7（主从修改表结构的操作） | 8.0（主从不支持 INSTANT 的操作）
    2. DDL
        1. INPLACE：5.7 | 8.0（不支持 INSTANT 的操作）
        2. INSTANT：8.0
>- [dbaplus社群 | 王文安](https://mp.weixin.qq.com/s/2k948wvPQio5KU1FzqfbUw)
>- [敖丙](https://mp.weixin.qq.com/s/pinOFeF09orQCnIp4L6XyA)
---
## 阿里 MySQL 数据库
### 建表规约
```
1. 表名、字段名必须使用小写字母或数字
    - MySQL 在 Windows 下不区分大小写，但在 Linux 下默认是区分大小写
2. 表名不使用复数名词
3. 禁用保留字，如 desc、range、match、delayed 等
    - https://dev.mysql.com/doc/refman/8.0/en/keywords.html
4. 主键索引名为 pk_字段名；唯一索引名为 uk_字段名；普通索引名则为 idx_字段名
5. 小数类型为 decimal，禁止使用 float 和 double
6. 如果存储的字符串长度几乎相等，使用 char 定长字符串类型
7. varchar 是可变长字符串，不预先分配存储空间，长度不要超过 5000，如果存储长度大于此值，定义字段类型为 text，独立出来一张表，用主键来对应，避免影响其它字段索引率
8. 表必备三字段：id，create_time，update_time
    - id 必为主键，类型为 bigint unsigned、单表时自增、步长为 1
    - create_time，update_time 的类型均为 datetime 类型，如果要记录时区信息，那么类型设置为 timestamp
9. 在数据库中不能使用物理删除操作，要使用逻辑删除
    - 会使得一些情况下的唯一主键变得不唯一，需要根据情况来酌情解决
10. 表的命名最好是遵循“业务名称_表的作用”：alipay_task / force_project / trade_config / tes_question
11. 字段允许适当冗余，以提高查询性能，但必须考虑数据一致。冗余字段应遵循
    1. 不是频繁修改的字段
    2. 不是唯一索引的字段
    3. 不是 varchar 超长字段，更不能是 text 字段
    - 各业务线经常冗余存储商品名称，避免查询时需要调用 IC 服务获取
12. 单表行数超过 500 万行或者单表容量超过 2GB，才推荐进行分库分表
13. 合适的字符存储长度，不但节约数据库表空间、节约索引存储，更重要的是提升检索速度
    - 无符号值可以避免误存负数，且扩大了表示范围
        1. tinyint unsigned         1       0 到 255
        2. smallint unsigned        2       0 到 65535
        3. int unsigned             4       0 到约 43 亿
        4. bigint unsigned          8       0 到约 10 的 19 次方
```
### 索引规约
```
1. 业务上具有唯一特性的字段，即使是组合字段，也必须建成唯一索引
2. 超过三个表禁止 join。需要 join 的字段，数据类型保持绝对一致；多表关联查询时，保证被关联的字段需要有索引
    - https://www.zhihu.com/question/56236190
3. 在 varchar 字段上建立索引时，必须指定索引长度，没必要对全字段建立索引，根据实际文本区分度决定索引长度
    - 可以使用 count(distinct left(列名，索引长度)) / count(*) 的区分度来确定
4. 页面搜索严禁左模糊或者全模糊，如果需要请走搜索引擎来解决
5. 如果有 order by 的场景，请注意利用索引的有序性
    - where a = ? and b = ? order by c；索引：a_b_c
6. 利用覆盖索引来进行查询操作，避免回表
    - 覆盖索引只是一种查询的一种效果，用 explain 的结果，extra 列会出现：using index
7. 利用延迟关联或者子查询优化超多分页场景 ???
    - MySQL 并不是跳过 offset 行，而是取 offset+N 行，然后返回放弃前 offset 行，返回 N 行
    - 先快速定位需要获取的 id 段，然后再关联
    - SELECT t1.* FROM 表 1 as t1 , (select id from 表 1 where 条件 LIMIT 100000 , 20) as t2 where t1.id = t2.id
    - https://mp.weixin.qq.com/s/3Pc88OtKr3_KJXItX2IJ3Q
8. SQL 性能优化的目标：至少要达到 range 级别，要求是 ref 级别，如果可以是 const 最好
9. 建组合索引的时候，区分度最高的在最左边
    - 如果 where a = ? and b = ?，a 列的几乎接近于唯一值，那么只需要单建 idx_a 索引即可
    - 存在非等号和等号混合判断条件时，在建索引时，请把等号条件的列前置
        - 如：where c > ? and d = ? 那么即使 c 的区分度更高，也必须把 d 放在索引的最前列，即建立组合索引 idx_d_c
```
### SQL 语句
```
1. 不要使用 count(列名) 或 count(常量) 来替代 count(*)，count(*) 是 SQL92 定义的标准统计行数的语法，跟数据库无关，跟 NULL 和非 NULL 无关
    - count(*) 会统计值为 NULL 的行，而 count(列名) 不会统计此列为 NULL 值的行
    - count(*) 详解和优化：https://mp.weixin.qq.com/s/foPwJPS8Ek7YmR3ZgV8xMw
2. count(distinct col) 计算该列除 NULL 之外的不重复行数，注意 count(distinct col1 , col2) 如果其中一列全为 NULL，那么即使另一列有不同的值，也返回为 0
3. 当某一列的值全是 NULL 时，count(col) 的返回结果为 0；但 sum(col) 的返回结果为 NULL，因此使用 sum() 时需注意 NPE 问题
    - SELECT IFNULL(SUM(column) , 0) FROM table
4. 使用 ISNULL() 来判断是否为 NULL 值
    - NULL 与任何值的直接比较都为 NULL
5. 不得使用外键与级联，一切外键概念必须在应用层解决
6. 禁止使用存储过程，存储过程难以调试和扩展，更没有移植性
7. 数据订正（特别是删除或修改记录操作）时，要先 select，避免出现误删除的情况，确认无误才能执行更新语句
8. 对于数据库中表记录的查询和变更，只要涉及多个表，都需要在列名前加表的别名（或表名）进行限定
    - 区分同名字段
9. in 操作能避免则避免，若实在避免不了，需要仔细评估 in 后边的集合元素数量，控制在 1000 个之内
10. 因国际化需要，所有的字符存储与表示，均采用 utf8mb4 字符集，字符计数方法需要注意
    1. SELECT LENGTH("轻松工作")；--返回为 12
    2. SELECT CHARACTER_LENGTH("轻松工作")；--返回为 4
    - 表情需要用 utf8mb4 来进行存储，注意它与 utf8 编码的区别
11. TRUNCATE TABLE 比 DELETE 速度快，且使用的系统和事务日志资源少，但 TRUNCATE 无事务且不触发 trigger，有可能造成事故，故不建议在开发代码中使用此语句
```
### ORM 映射
```
1. POJO 类的布尔属性不能加 is，而数据库字段必须加 is_，要求在 resultMap 中进行字段与属性之间的映射
2. 不要用 resultClass 当返回参数，即使所有类属性名与数据库字段一一对应，也需要定义<resultMap>；反过来，每一个表也必然有一个<resultMap>与之对应
3. sql.xml 配置参数使用：#{}，#param# 不要使用 ${} 此种方式容易出现 SQL 注入
4. iBATIS 自带的 queryForList(String statementName，int start，int size) 不推荐使用
    - 其实现方式是在数据库取到 statementName 对应的 SQL 语句的所有记录，再通过 subList 取 start
5. 不允许直接拿 HashMap 与 Hashtable 作为查询结果集的输出
6. 不要写一个大而全的数据更新接口。传入为 POJO 类，不管是不是自己的目标更新字段，都进行 update table set c1 = value1 , c2 = value2 , c3 = value3
    - 一是易出错；二是效率低；三是增加 binlog 存储
7. @Transactional 事务不要滥用。事务会影响数据库的 QPS，另外使用事务的地方需要考虑各方面的回滚方案，包括缓存回滚、搜索引擎回滚、消息补偿、统计修正等
8. 使用事务的地方需要考虑各方面的回滚方案，包括缓存回滚、搜索引擎回滚、消息补偿、统计修正等
9. <isEqual>中的 compareValue 是与属性值对比的常量，一般是数字，表示相等时带上此条件；<isNotEmpty>表示不为空且不为 null 时执行；<isNotNull>表示不为 null 值时执行
>```
---
