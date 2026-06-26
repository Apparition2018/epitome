# MySQL

---
## 参考网站
1. [MySQL :: MySQL Reference Manual](https://dev.mysql.com/doc/refman/8.4/en/)
2. [MySQL :: Sakila Sample Database](https://dev.mysql.com/doc/sakila/en/)
    - [Other MySQL Documentation](https://dev.mysql.com/doc/index-other.html)
3. [MySQL 规范](https://blog.csdn.net/shenjian58/article/details/89850405)
4. [MySQL 总结](https://mp.weixin.qq.com/s/pWHCieOwAdCrz8cauduWlQ)
---
## B-Tree vs InnoDB B+ Tree
🔺![B+ Tree](https://i.postimg.cc/prpW6Qzh/btree-vs-bplus-tree.png)
1. 节点存储
    1. 主键索引（聚簇索引）
        1. 非叶子节点存[键+指针]
        2. 叶子节点存[键（有序排序）+整行数据]
    2. 二级索引（非主键索引）
        1. 非叶子节点存[索引列值+指针]
        2. 叶子节点存[索引列值（有序排序）+主键值]
2. 每个节点对应一页16KB，因为非叶子节点不存数据，所以单个节点能放更多索引项，比 B-Tree 扇出更大，树更矮 → IO 更少 → 查询更快
    - 同理，主键/索引选择更短的数据类型，查询更快
3. 叶子节点用双向链表相连，便于范围查询
4. 非叶子节点的键会在叶子节点重复出现
5. 回表：使用二级索引查询时，需要根据主键去主键索引中查找行数据
---
## [MySQL 术语](https://dev.mysql.com/doc/refman/8.4/en/glossary.html)
1. ⚪cardinality：基数，索引列中不同值的预估数量
    - 查看索引的 Cardinality 统计值：`SHOW INDEX FROM table_name`
2. 🔺selectivity：选择性/区分度，= Cardinality / TABLE_ROWS，值越高说明索引过滤性越好
3. ⚪index hint：索引提示，覆盖优化器推荐的索引。适用于 cardinality 分布非常不均匀时
    1. FORCE INDEX：强制使用
    2. USE INDEX：建议使用
    3. IGNORE INDEX：忽略使用
4. 🔺[Leftmost Prefix](https://dev.mysql.com/doc/refman/8.4/en/multiple-column-indexes.html)：最左前缀原则，按索引顺序从左到右连续使用，索引才生效
    - 如：(col1,col2,col3) 索引，那么这些条件组合 (coll1)、(col1,col2) 和 (col1,col2,col3) 索引都生效
    - 遇到范围条件 (>, <, >=, <=, !=, <>, BETWEEN, LIKE, OR)，不再考虑后续部分
    - 综上和条件重排，一般情况下 WHERE 条件中包含索引最左的列，索引即生效（范围查询也有可能生效）
5. 🔺covering index：覆盖索引，一个索引包含查询所需的所有列，而无需回表读取数据行。extra 显示 Using index
6. ⚪[index condition pushdown](https://dev.mysql.com/doc/refman/8.4/en/index-condition-pushdown-optimization.html)：索引条件下推，将 WHERE 条件中能用索引列判断的部分，下推到存储引擎层
    - 减少回表次数和 MySQL 服务器访问存储引擎的次数
    - 如：索引 (a,b,c) `WHERE a = 1 AND b > 2 AND c = 3` ❓
7. 🔺column prefix / [Index Prefixes](https://dev.mysql.com/doc/refman/8.4/en/column-indexes.html#column-indexes-prefix)：列前缀索引，字符串列索引使用 `col_name(N)` 语法，创建仅使用列的前 N 个字符的索引
    - 减少索引大小并提高查询性能
    - 当索引 BLOB 或 TEXT 列时，必须指定前缀长度
8. ⚪FULLTEXT index：全文索引，利用倒排索引加速文本搜索
    - 中文场景要用 ngram 分词器 `FULLTEXT INDEX ft_idx_xxx (goods_name, detail_text) WITH PARSER ngram`
9. ⚪descending index：降序索引，允许索引列按降序 DESC 存储
    - 使索引排序方向与 order by 方向一致，从而消除 filesort
10. ⚪[Functional indexes](https://dev.mysql.com/doc/refman/8.4/en/create-index.html#create-index-functional-key-parts)：函数索引，允许对表达式建立索引
11. [Invisible Indexes](https://dev.mysql.com/doc/refman/8.4/en/invisible-indexes.html)：隐藏索引，使得索引对优化器不可见，有助于索引的灰度测试和发布
12. dirty page：脏页，Buffer Pool 中更改过，且未 written/flushed 到数据文件的页
13. extent：tablespace 的一组页，1页默认16KB，1 extent 包含64页
14. read-ahead：预读，预先读取一组页面到 Buffer Pool
    1. linear read-ahead：线性预读
    2. random read-ahead：随机预读
---
## 阿里 MySQL 数据库
### 建表规约
1. 表达是与否概念的字段，必须使用 is_xxx 的方式命名，数据类型是 unsigned tinyint（1 表示是，0 表示否）
    - POJO 类中的任何布尔类型的变量，都不要加 is 前缀，所以需要在<resultMap>设置从 is_xxx 到 Xxx 的映射关系
2. 表名、字段名必须使用小写字母或数字，禁止出现数字开头禁止两个下划线中间只出现数字
    - MySQL 在 Windows 下不区分大小写，但在 Linux 下默认是区分大小写
3. 表名不使用复数名词
4. 禁用保留字，如 desc、range、match、delayed 等
    - https://dev.mysql.com/doc/refman/8.4/en/keywords.html
5. 主键索引名为 pk_字段名；唯一索引名为 uk_字段名；普通索引名则为 idx_字段名
6. 小数类型为 decimal，禁止使用 float 和 double
7. 如果存储的字符串长度几乎相等，使用 char 定长字符串类型
    - ⚪对于现代数据库，只有长度完全固定的业务字段（如国家代码、UUID、MD5）才用 CHAR
8. varchar 是可变长字符串，不预先分配存储空间，长度不要超过 5000，如果存储长度大于此值，定义字段类型为 text，独立出来一张表，用主键来对应，避免影响其它字段索引率
    - ⚪①字段长度500~8192字节且不常与主表一起查询 ②多个中等长度字段累加超过8KB
9. 表必备三字段：id，create_time，update_time
    - id 必为主键，类型为 bigint unsigned、单表时自增、步长为 1
    - create_time，update_time 的类型均为 datetime 类型，如果要记录时区信息，那么类型设置为 timestamp
    - datetime 存储 UTC + 应用层转换
10. 在数据库中不能使用物理删除操作，要使用逻辑删除
    - 会使得一些情况下的唯一主键变得不唯一，需要根据情况来酌情解决
11. 表的命名最好是遵循“业务名称_表的作用”：alipay_task / force_project / trade_config / tes_question
12. ⚪字段允许适当冗余，以提高查询性能，但必须考虑数据一致。冗余字段应遵循
    1. 不是频繁修改的字段
    2. 不是唯一索引的字段
    3. 不是 varchar 超长字段，更不能是 text 字段
    - 各业务线经常冗余存储商品名称，避免查询时需要调用 Item Center（商品中心）服务获取
13. 单表行数超过 500 万行或者单表容量超过 2GB，才推荐进行分库分表
    - ⚪先优化索引、调整字段类型、重写慢 SQL、归档历史数据、垂直拆分、升级硬件
14. 合适的字符存储长度，不但节约数据库表空间、节约索引存储，更重要的是提升检索速度
    - 无符号值可以避免误存负数，且扩大了表示范围
        1. tinyint unsigned         1       0 到 255
        2. smallint unsigned        2       0 到 65535
        3. int unsigned             4       0 到约 43 亿
        4. bigint unsigned          8       0 到约 10 的 19 次方
    - 🔺[数据类型选择](#数据类型选择)
### 索引规约
1. 业务上具有唯一特性的字段，即使是组合字段，也必须建成唯一索引
2. 超过三个表禁止 join。⚪需要 join 的字段，数据类型和编码保持绝对一致；多表关联查询时，保证被关联的字段需要有索引
    - https://www.zhihu.com/question/56236190
3. ⚪在 varchar 字段上建立索引时，必须指定索引长度（前缀索引），没必要对全字段建立索引，根据实际文本区分度决定索引长度
    - 可以使用 count(distinct left(列名，索引长度)) / count(*) ≥ 90% 的区分度来确定
4. ⚪页面搜索严禁左模糊或者全模糊
    - 索引文件具有 B-Tree 的最左前缀匹配特性，如果左边的值未确定，那么无法使用此索引
    - 解决方案：①全文索引 ②冗余“反向字段” ③限定范围（加筛选条件）+覆盖索引 ④前缀索引+关键词分词 ⑤搜索引擎
5. ⚪如果有 order by 的场景，请注意利用索引的有序性。order by 最后的字段是组合索引的一部 分，并且放在索引组合顺序的最后，避免出现 filesort 的情况
    - where a = ? and b = ? order by c；索引：a_b_c
6. ⚪利用延迟关联或者子查询优化超多分页场景
    - MySQL 并不是跳过 offset 行，而是取 offset+N 行，然后返回放弃前 offset 行，返回 N 行
    - 先快速定位需要获取的 id 段，然后再关联
    - SELECT t1.* FROM 表 1 as t1 , (select id from 表 1 where 条件 LIMIT 100000 , 20) as t2 where t1.id = t2.id
    - https://mp.weixin.qq.com/s/3Pc88OtKr3_KJXItX2IJ3Q
    -
    | 对比维度     | 延迟关联                   | 游标分页                                                       |
    |:---------|:-----------------------|:-----------------------------------------------------------|
    | SQL      |                        | SELECT * FROM table WHERE id > 100000 ORDER BY id LIMIT 20 |
    | SQL 核心语法 | JOIN 覆盖索引查 ID，再回表查完整数据 | WHERE 游标字段 > 上一页最后值 LIMIT n                                |
    | 扫描行数     | N+20 行 (仅扫索引，较快)       | 恒定 20 行 (极快)                                               |
    | 性能随深度    | 页码越深越慢 (但远优于传统分页)      | 性能恒定 (与页码深度无关)                                             |
    | 是否支持跳页   | 支持 (可跳转任意页码)           | 不支持 (仅限上下页连续翻)                                             |
    | 复杂排序支持   | 容易 (直接 ORDER BY 任意字段)  | 较难 (需组合游标处理同值边界)                                           |
    | 适用场景     | 后台管理、PC端页码列表           | App 瀑布流、下拉无限加载                                             |
7. ⚪SQL 性能优化的目标：至少要达到 range 级别，要求是 ref 级别，如果可以是 const 最好
8. 建组合索引的时候，区分度最高的在最左边
    - 如果 where a = ? and b = ?，a 列的几乎接近于唯一值，那么只需要单建 idx_a 索引即可
    - 存在非等号和等号混合判断条件时，在建索引时，请把等号条件的列前置
        - 如：where c > ? and d = ? 那么即使 c 的区分度更高，也必须把 d 放在索引的最前列，即建立组合索引 idx_d_c
9. 创建索引时避免有如下极端误解
    - ⚪索引宁滥勿缺。认为一个查询就需要建一个索引
        - 如：①WHERE order_status = ? 不建，区分度低 ②表数据少
        - 索引也占用空间，还需要定期维护索引
        - DML 操作需要重建索引
        - DQL 操作优化器选择使用哪一个索引需要时间
    - 吝啬索引的创建。认为索引会消耗空间、严重拖慢记录的更新以及行的新增速度
    - 抵制唯一索引。认为唯一索引一律需要在应用层通过“先查后插”方式解决
### SQL 语句
1. ⚪不要使用 count(列名) 或 count(常量) 来替代 count(*)，count(*) 是 SQL92 定义的标准统计行数的语法，跟数据库无关，跟 NULL 和非 NULL 无关
    - count(*) 会统计值为 NULL 的行，而 count(列名) 不会统计此列为 NULL 值的行
    - count(*) 详解和优化：https://mp.weixin.qq.com/s/foPwJPS8Ek7YmR3ZgV8xMw
2. count(distinct col) 计算该列除 NULL 之外的不重复行数，注意 count(distinct col1 , col2) 如果其中一列全为 NULL，那么即使另一列有不同的值，也返回为 0
3. 当某一列的值全是 NULL 时，count(col) 的返回结果为 0；但 sum(col) 的返回结果为 NULL，因此使用 sum() 时需注意 NPE 问题
    - SELECT IFNULL(SUM(column) , 0) FROM table
4. 使用 ISNULL() 来判断是否为 NULL 值
    - NULL 与任何值的直接比较都为 NULL
5. ⚪代码中写分页查询逻辑时，若 count 为 0 应直接返回，避免执行后面的分页语句
6. 不得使用外键与级联，一切外键概念必须在应用层解决
7. 禁止使用存储过程，存储过程难以调试和扩展，更没有移植性
8. 数据订正（特别是删除或修改记录操作）时，要先 select，避免出现误删除的情况，确认无误才能执行更新语句
9. 🔺in 操作能避免则避免，若实在避免不了，需要仔细评估 in 后边的集合元素数量，控制在 1000 个之内
    1. 表值构造器：`JOIN (VALUES ROW(1), ROW(2), ROW(3)) AS tmp(id)`
    2. 临时表 + join：临时表主键索引
    3. 应用层分批查询
    4. 子查询代替：优化器将子查询优化为 Semi Join
10. 因国际化需要，所有的字符存储与表示，均采用 utf8mb4 字符集，字符计数方法需要注意
    1. SELECT LENGTH("轻松工作")；--返回为 12
    2. SELECT CHARACTER_LENGTH("轻松工作")；--返回为 4
    - 表情需要用 utf8mb4 来进行存储，注意它与 utf8 编码的区别
11. TRUNCATE TABLE 比 DELETE 速度快，且使用的系统和事务日志资源少，但 TRUNCATE 无事务且不触发 trigger，有可能造成事故，故不建议在开发代码中使用此语句
### ORM 映射
1. ⚪在表查询中，一律不要使用 * 作为查询的字段列表，需要哪些字段必须明确写明
    - 失去覆盖索引的可能性
    - 无用大字段增加网络/磁盘消耗
2. ⚪POJO 类的布尔属性不能加 is，而数据库字段必须加 is_，要求在 resultMap 中进行字段与属性之间的映射
3. POJO 类的布尔属性不能加 is，而数据库字段必须加 is_，要求在 resultMap 中进行字段与属性之间的映射
4. 不要用 resultClass 当返回参数，即使所有类属性名与数据库字段一一对应，也需要定义<resultMap>；反过来，每一个表也必然有一个<resultMap>与之对应
5. ⚪sql.xml 配置参数使用：#{}，#param# 不要使用 ${} 此种方式容易出现 SQL 注入
    - ${} 适用于动态表名、列名、ORDER BY 等
6. iBATIS 自带的 queryForList(String statementName，int start，int size) 不推荐使用
    - 其实现方式是在数据库取到 statementName 对应的 SQL 语句的所有记录，再通过 subList 取 start
7. 不允许直接拿 HashMap 与 Hashtable 作为查询结果集的输出
8. 更新数据表记录时，必须同时更新记录对应的 update_time 字段值为当前时间
9. 不要写一个大而全的数据更新接口。传入为 POJO 类，不管是不是自己的目标更新字段，都进行 update table set c1 = value1 , c2 = value2 , c3 = value3
    - 一是易出错；二是效率低；三是增加 binlog 存储
10. @Transactional 事务不要滥用。事务会影响数据库的 QPS，另外使用事务的地方需要考虑各方面的回滚方案，包括缓存回滚、搜索引擎回滚、消息补偿、统计修正等
11. <isEqual>中的 compareValue 是与属性值对比的常量，一般是数字，表示相等时带上此条件；<isNotEmpty>表示不为空且不为 null 时执行；<isNotNull>表示不为 null 值时执行
---
## MySQL 调优
### ⚪索引失效场景
1. 违反[最左前缀原则](#mysql-术语) -4.
2. 使用 !=、<>、NOT IN、OR
    - 使用 UNION (ALL) 代替 OR
3. [隐式类型/编码转换](#索引规约) -2.
4. [LIKE 以 % 开头](#索引规约) -4.
5. [对索引列做函数/运算操作](#mysql-术语) -10.
6. [数据区分度低](#索引规约) -9.
### [explain](https://dev.mysql.com/doc/refman/8.4/en/explain-output.html)
- select_type：查询类型
- table：当前访问的表
- partitions：匹配的分区
- 🔺[type](https://dev.mysql.com/doc/refman/8.4/en/explain-output.html#explain-join-types) ：连接类型

| type            | 说明                            |
|:----------------|:------------------------------|
| system          | 表只有一行，是 const 的特例             |
| const           | pk / unique 索引等值匹配，最多一行       |
| eq_ref          | join 时被驱动表 pk / unique 索引等值匹配 |
| ref             | 非 unique 索引等值查询               |
| fulltext        | 全文索引                          |
| ref_or_null     | 非 unique 索引等值查询 or is null    |
| index_merge     | 多个单列索引合并使用                    |
| unique_subquery | IN 子查询返回唯一值                   |
| index_subquery  | IN 子查询返回非一值                   |
| range           | 范围检索                          |
| index           | 全索引扫描                         |
| ALL             | 全表扫描                          |
- possible_keys：可能使用的索引
- 🔺key：实际使用的索引
- ⚪key_len：使用索引的字节数，越大说明用到的索引越多
- ref：与索引比较的列或常量
- rows：预估需要扫描的行数，越小越好
- filtered：经过 WHERE 条件过滤后，剩余行数的百分比，越小越好
- 🔺[extra](https://dev.mysql.com/doc/refman/8.4/en/explain-output.html#explain-extra-information)：额外信息

| extra                 | 说明             | 优化                  |
|:----------------------|:---------------|:--------------------|
| Using index           | 覆盖索引，无需回表      |                     |
| Using index condition | 索引条件下推         |                     |
| Using where           | 服务层过滤          |                     |
| Using filesort        | 需额外文件排序        | order by 列加索引       |
| Using temporary       | 需创建临时表暂存中间结果   | group by / distinct |
| Using join buffer     | 需创建连接缓冲区暂存中间结果 | join 列加索引           |
### 🔺[慢查询](https://dev.mysql.com/doc/refman/8.4/en/slow-query-log.html)
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
set global slow_query_log_file = '/var/log/mysql/slow-query.log';
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
    1. [mysqldumpslow](https://dev.mysql.com/doc/refman/8.4/en/mysqldumpslow.html)
        ```
        # 按查询时间降序，显示前5条
        mysqldumpslow -s t -t 5 /var/lib/mysql/slow-query.log
        # 按平均查询时间降序，显示前5条
        mysqldumpslow -s at -t 5 /var/lib/mysql/slow-query.log
        # 按锁定时间降序，显示前5条
        mysqldumpslow -s l -t 5 /var/log/mysql/slow.log
        ```
    2. Percona Toolkit：pt-query-digest
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
- 🔺慢查询优化：
    1. [索引失效场景](#索引失效场景)
    2. [避免 select *](#orm-映射) -1.
    3. [字段适当冗余](#建表规约) -12.
    4. 改写 SQL
        - [大分页优化：延迟关联](#索引规约) -6.
    5. 读写分离 / [分库分表](#建表规约) -13.
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
    - 配置文件的查找顺序：`/usr/sbin/mysqld --verbose --help | grep -A 1 'Default options'`）
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
### [数据类型选择](https://mp.weixin.qq.com/s/IEyaazf_Z1fTObsmCizelQ)
1. 优先选择小的数据类型：数值 > 日期时间 > 字符串

|    数据    |              推荐类型              |                          理由                          |
|:--------:|:------------------------------:|:----------------------------------------------------:|
|    IP    |            UNSIGNED            | `INET_ATON('192.168.0.1')` / `INET_NTOA(3232235521)` |
| 枚举/状态/类型 |        TINYINT UNSIGNED        |                                                      |
|   长度固定   |              CHAR              |      UUID CHAR(36) / 国家代码 CHAR(2)/ MD5 CHAR(32)      |
|    ID    | INT UNSIGNED / BIGINT UNSIGNED |                                                      |
|   日期时间   |            DATETIME            |          存储 UTC + 应用层转换，TIMESTAMP 有 2038 问题          |
|    金额    |            CHAR(36)            |                        精确定点数                         |
### 其它建议
- 🔺查找重复索引及冗余索引
    1.
        ```mysql
        use information_schema;

        select s1.table_schema, s1.table_name, s1.index_name as index1, s2.index_name as index2, s1.column_name as dup_col
        from statistics s1
        join statistics s2 on s1.table_schema = s2.table_schema and s1.table_name = s2.table_name and s1.seq_in_index = s2.seq_in_index and s1.column_name = s2.column_name
        where s1.seq_in_index = 1 and s1.index_name <> s2.index_name;
        ```
    2. [Percona Toolkit](https://tools.percona.com/wizard)：pt-duplicate-key-checker
- 汇总表/缓存表：定时生成数据，用于用户耗时时间长的操作
---
## [MySQL 字符集](https://khiav223577.github.io/blog/2019/06/30/MySQL-%E7%B7%A8%E7%A2%BC%E6%8C%91%E9%81%B8%E8%88%87%E5%B7%AE%E7%95%B0%E6%AF%94%E8%BC%83/)
- [字符集配置](https://dev.mysql.com/doc/refman/8.4/en/charset-configuration.html)
    ```
    [mysqld]
    character-set-server=utf8mb4
    collation-server=utf8mb4_unicode_ci
    [client]
    default-character-set=utf8mb4
    ```
1. ci vs cs vs bin
    1. ci: case-insensitive，不区分大小写
    2. cs: case-sensitive，区分大小写
    3. bin: 使用 binary value 比对
2. ai vs as
    1. ai: accent-insensitive，不区分音调
    2. as: accent-sensitive，区分音调
3. [general vs unicode vs 0900](https://stackoverflow.com/questions/766809/whats-the-difference-between-utf8-general-ci-and-utf8-unicode-ci)
    1. general: 排序速度较快，但准确性稍差
    2. unicode: unicode 4.0 规范，适用于多种语言准确排序
    3. unicode_520: unicode 5.2 规范
    4. 0900: unicode 9.0 规范
---
## [InnoDB 存储引擎](https://dev.mysql.com/doc/refman/8.4/en/innodb-storage-engine.html)
|                  |     |                                                               |
|------------------|-----|---------------------------------------------------------------|
| Row              | 行   | 由一组列定义的逻辑数据结构，每一页面可以包含一行或多行                                   |
| Page             | 页   | 任意时刻在磁盘(数据文件)和内存(缓冲池)之间传输多少数据的单位；<br/>一页可以包含一行或多行；默认页面大小为16KB |
| Extent           | 区   | 表空间内的一组页。对于默认页面大小为KB的页，一区包含64个页                               |
| page compression | 页压缩 | 允许对位于表文件表空间中的InnoDB表进行页级压缩                                    |
### InnoDB vs MyISAM
| 对比              |       InnoDB       |     MyISAM      |
|:----------------|:------------------:|:---------------:|
| 如何选择            |       适合 DML       |   适合 DQL、低并发    |
| 事务              |         支持         |       不支持       |
| 锁               |       行锁、表锁        |       表锁        |
| 主键              | 没有会自动生成<br/>推荐自增主键 |      可以没有       |
| 聚簇索引            |         是          |        否        |
| 缓存              |       数据和索引        |       索引        |
| 全文索引            |       5.6后支持       |       支持        |
| hash 索引         |       不能主动创建       |       不支持       |
| 外键              |         支持         |       不支持       |
| select count(*) |      较慢，索引扫描       | 很快，有一个变量保存了表的行数 |
| 压缩              |        不支持         |       支持        |
| 存储文件            |     .frm .idb      | .frm .myd .myi  |
| 记录存储顺序          |        主键大小        |      插入顺序       |
>- [MyISAM 为什么查询比 InnoDB 快](https://mp.weixin.qq.com/s/Nisohn7GyuvUX6xdp8YaXw)
### [InnoDB 多版本](https://dev.mysql.com/doc/refman/8.4/en/innodb-multi-versioning.html)
| 字段          | 说明                  |
|:------------|:--------------------|
| DB_TRX_ID   | 记录插入或更新行得事务的事务ID    |
| DB_ROLL_PTR | 回滚指针，指向 undo log 记录 |
| DB_ROW_ID   | 行ID                 |
>- [MySQL MVCC](https://blog.csdn.net/Waves___/article/details/105295060)
### [InnoDB 内存结构](https://dev.mysql.com/doc/refman/8.4/en/innodb-in-memory-structures.html)
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
### [InnoDB 磁盘结构](https://dev.mysql.com/doc/refman/8.4/en/innodb-on-disk-structures.html)
1. Tablespaces：表空间，保存一个或多个 InnoDB 表和相关联索引的数据文件
2. [Doublewrite Buffer](https://mp.weixin.qq.com/s/bkoQ9g4cIcFFZBnpVh8ERQ) ：双写缓冲
    - 解决的问题：Dirty Page(16KB) 写入磁盘(1页4KB)失败，可从中找到副本恢复错误
    - 两部分：①内存；②双写文件
    - 步骤：Dirty Page → 内存双写缓冲 → ①双写文件双写缓冲；②InnoDB 数据文件
3. Redo Log：重做日志
### [Innodb 索引类型]( https://dev.mysql.com/doc/refman/8.4/en/innodb-index-types.html)
1. 聚簇索引：Clustered Indexes
    - 索引树的叶子节点存储：①索引；②行记录（索引与行记录一起存储，所以称之为聚簇）
    - 如何选择聚簇索引：①主键；②第一个 NOT NULL UNIQUE 列；③自动生成隐藏 GEN_CLUST_INDEX
2. 二级索引：Secondary Indexes
    - 索引树的叶子节点存储：①索引；②对应聚簇索引值
>- [索引的数据结构模型及维护 | 妲个己吧](https://mp.weixin.qq.com/s/hmolnmi7WU0ZhQ9X5IR1JA)
>- [小林coding](https://mp.weixin.qq.com/s/LTX67XxkWcAeYUyLh_5b4g)
>- [架构师之路](https://mp.weixin.qq.com/s/woz5lkQwyJZNmoiiJZy7NA)
---
## [InnoDB 锁和事务模型](https://dev.mysql.com/doc/refman/8.4/en/innodb-locking-transaction-model.html)
### [InnoDB 锁](https://dev.mysql.com/doc/refman/8.4/en/innodb-locking.html)
1. 共享/排它锁 (Shared and Exclusive Lock)
    1. 共享锁 (Shared Lock, S)：
        - 对符合条件的行加S锁，其它事务可以对这些记录添加IS锁和S锁，即其它事务可以读取这些数据但无法修改
    2. 排它锁 (Exclusive Lock, X)：
        - 对符合条件的行加X锁，其它事务无法对这些记录加任何IS锁和IX锁，即其它事务无法对这些记录进行读取和修改
        - 不会阻止非锁定读
2. 意向锁 (Intention Lock)：表级锁，指示事务稍后需要对表中的行使用哪种类型的锁(共享/排它)
    1. 意向共享锁 (Intention Shared Lock, IS)：`SELECT … FOR SHARE`
    2. 意向排它锁 (Intention Exclusive Lock, IX)：[`SELECT … FOR UPDATE`](https://www.cnblogs.com/xiao-lei/p/12598552.html)

    |     | X   | IX  | S   | IS  |
    |:----|:----|:----|:----|:----|
    | X   | 冲突  | 冲突  | 冲突  | 冲突  |
    | IX  | 冲突  | 兼容  | 冲突  | 兼容  |
    | S   | 冲突  | 冲突  | 兼容  | 兼容  |
    | IS  | 冲突  | 兼容  | 兼容  | 兼容  |
3. 记录锁 (Record Lock)：`SELECT c1 FROM t WHERE c1 = 10 FOR UPDATE`
    - 总是锁定索引记录，阻止其它事务插入、更新或删除
4. [间隙锁](https://www.jianshu.com/p/32904ee07e56) (Gap Lock)：`SELECT c1 FROM t WHERE c1 BETWEEN 10 and 20 FOR UPDATE`
    - 对索引记录之间的间隙的锁，或是对第一个索引之前或最后一个索引记录之后的间隙的锁，防止其他事物插入数据
    - 不同事务允许持有冲突的间隙锁，容易导致死锁
    - 隔离级别为 REPEATABLE READ 或 SERIALIZABLE，间隙锁生效
5. 临键锁 (Next-key Lock)
    - 记录锁 + 索引记录之前的间隙锁，左开右闭
    - 隔离级别为 REPEATABLE READ 或 SERIALIZABLE，临键锁生效
6. 插入意向锁 (Insert Intention Lock)
7. 自增锁 (AUTO-INC Lock)
>- [MySQL select 加锁分析](https://www.cnblogs.com/rjzheng/p/9950951.html)
>- [InnoDB 的七种锁](https://mp.weixin.qq.com/s/f4_o-6-JEbIzPCH09mxHJg)
>- [InnoDB 锁到底锁的是什么](https://mp.weixin.qq.com/s/fmSHG0SejfD0IdnpIYHT9w)
### RR 下的加锁规则
- 原则：
    - 加锁基本单位 Next-key Lock
    - 查找过程中访问到的对象会加锁
- 索引上等值查询优化：
    - 给唯一索引加锁时，Next-key Lock → Record Lock
    - 向右遍历且最后一个值不满足等值条件时，Next-key Lock → Gap Lock
    - 对于覆盖索引查询，不对聚簇索引加锁
- 唯一索引上的范围查询会访问到不满足条件的第一个值为止
### 非锁定读 vs 锁定读
| Read  | [Consistent Nonlocking Reads](https://dev.mysql.com/doc/refman/8.4/en/innodb-consistent-read.html) | [Locking Reads](https://dev.mysql.com/doc/refman/8.4/en/innodb-locking-reads.html) |
|:------|:---------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------|
| 中文    | 一致性非锁定读，快照读                                                                                        | 锁定读                                                                                |
| 语句    | select                                                                                             | select … for share<br/>select … for update                                         |
| 说明    | 只能读当前事务之前更改                                                                                        | 能读最新的更改                                                                            |
| 是否会幻读 | 不可能                                                                                                | 可能                                                                                 |
| 隔离级别  | RC、RR                                                                                              |                                                                                    |
---
## [复制](https://dev.mysql.com/doc/refman/8.4/en/replication.html)
- 配置
```
# https://dev.mysql.com/doc/refman/8.4/en/replication-options.html
server_id=1

# https://dev.mysql.com/doc/refman/8.4/en/replication-options-binary-log.html
# 开启binlog，binlog文件名称
log-bin=/usr/local/mysql-a/bin_log/log-bin
# binlog记录格式：MIXED、STATEMENT、ROW（默认）
binlog_format=ROW
# 记录格式为ROW时生效，信息性日志事件（如行查询日志事件）写入binlog，默认OFF
binlog_rows_query_log_events=ON
# binlon过期时间，单位秒，默认2592000（30天），范围[0,4294967295]；expire_logs_days已被弃用
binlog_expire_logs_seconds=2592000
# binlog单个日志文件最大大小，默认1GB，范围[4096B,1GB]
max_binlog_size=1024M
# 事务期间保存binlog更改的内存缓冲大小，默认32KB，范围[4096B,100GB]
binlog_cache_size=1M
# 不记录指定数据库的binlog（设置在主库上）
binlog-ignore-db=mysql
binlog-ignore-db=sys
binlog-ignore-db=information_schema
binlog-ignore-db=performance_schema
# replice server从source server接收的更新是否记录到replice的binlong中
# 8.0.26之前使用log_slave_updates
log_replica_updates=ON

# https://dev.mysql.com/doc/refman/8.4/en/replication-options-replica.html
# 中继日志文件名称
relay_log=/usr/local/mysql-a/relay_log/relay-bin
# 不复制指定数据库（设置在从库上）
# replicate-ignore-db=epitome
# 跳过错误，避免复制中断
# slave_skip_errors=1062

# https://dev.mysql.com/doc/refman/8.4/en/replication-options-gtids.html
# 开启gtid
enforce_gtid_consistency=ON
gtid_mode=ON
```
---
## 其它
1. [delimiter](https://www.jb51.net/article/146693.htm)：分隔符
2. [为什么 varchar 默认是 255](https://www.jianshu.com/p/83bdcf9bd5a8)
    - varchar 有一个长度前缀表示字节数
        1. varchar ≤ 255个字节时，长度前缀为1个字节
        2. varchar > 255个字节时，长度前缀为2个字节
3. 变更大表表结构
    1. gh-ost：5.5 | 5.6 | 5.7（主从修改表结构的操作） | 8.0（主从不支持 INSTANT 的操作）
    2. DDL
        1. INPLACE：5.7 | 8.0（不支持 INSTANT 的操作）
        2. INSTANT：8.0
>- [dbaplus社群 | 王文安](https://mp.weixin.qq.com/s/2k948wvPQio5KU1FzqfbUw)
>- [敖丙](https://mp.weixin.qq.com/s/pinOFeF09orQCnIp4L6XyA)
---
## [MySQL 5 安装](https://blog.csdn.net/weixin_42109012/article/details/94443391)
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
collation-server=utf8mb4_general_ci
init_connect='SET collation_connection=utf8mb4_general_ci'
init_connect='SET NAMES utf8mb4'
skip-character-set-client-handshake
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
