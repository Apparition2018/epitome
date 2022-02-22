# MySQL

---
## 参考网站
1. [MySQL 8.0 Reference Manual](https://dev.mysql.com/doc/refman/8.0/en/)
---
## 问题
1. [MySQL delimiter 的定义及作用](https://www.jb51.net/article/146693.htm)
2. [MySQL 为什么默认定义varchar(255) 而不是varchar(256)](https://juejin.cn/post/6844903894703685646)
3. [MySQL8 新特性 window functions 的作用](https://www.jb51.net/article/129447.htm)
---
## 安装
1. [MySQL 完全卸载教程](https://blog.csdn.net/qq_41140741/article/details/81489531)
2. [MySQL8 压缩包版安装方法](https://www.cnblogs.com/xuqp/p/9172254.html)
3. [同时安装 MySQL5 和 MySQL8](https://blog.csdn.net/qq_32793985/article/details/105807328)
- mysql 5.6 不需要执行 mysqld --initialize
>### [my.ini](https://www.cnblogs.com/missmeng/p/13404228.html)
>```
>[mysqld]
># 设置3306端口
>port=3306
># 自定义设置mysql的安装目录，即解压mysql压缩包的目录
>basedir=F:\mysql-8.0.16-winx64
># 自定义设置mysql数据库的数据存放目录
>datadir=F:\mysql-8.0.16-winx64\data
># 允许最大连接数
>max_connections=200
># 允许连接失败的次数，这是为了防止有人从该主机试图攻击数据库系统
>max_connect_errors=10
># 服务端使用的字符集默认为UTF8
>character-set-client-handshake=FALSE
>character-set-server=utf8mb4
>collation-server = utf8mb4_bin
>init_connect='SET NAMES utf8mb4'
># 创建新表时将使用的默认存储引擎
>default-storage-engine=INNODB
># 默认使用“mysql_native_password”插件认证
>default_authentication_plugin=mysql_native_password
>###
>performance_schema=off
>table_definition_cache=400
>table_open_cache=64
>innodb_buffer_pool_chunk_size=64M
>innodb_buffer_pool_size=64M
>[mysql]
># 设置mysql客户端默认字符集
>default-character-set=utf8mb4
>[client]
># 设置mysql客户端连接服务端时默认使用的端口和默认字符集
>port=3306
>default-character-set=utf8mb4
>```
---
## 优化
1. [SQL 性能优化梳理](https://juejin.cn/post/6844903494504185870)
2. [字符串索引前缀长度](https://blog.csdn.net/qq_38670588/article/details/108499966)
>## 优化建议
>1. 索引相关
>   - 索引方法
>       1. BTREE
>           - 适用于：全键值，范围查找，前缀索引，排序
>           - 限制：[最左前缀原则 (Leftmost Prefix Principle)](https://www.cnblogs.com/-mrl/p/13230006.html)
>       2. HASH
>           - 限制：匹配索引所有列；不适用于排序；不支持部分匹配；只支持等值，如：=，in
>   - 表数据太少不需要添加索引
>   - 索引的数据类型越小效率越高
>   - 索引不要包括太长的数据类型，使用前缀索引代替：ALTER TABLE <table_name> ADD INDEX idx_content(content(6))
>   - 索引不是越多越好
>       - 索引也占用空间，还需要定期维护索引
>       - DML 操作需要重建索引
>       - DQL 操作优化器选择使用哪一个索引需要时间
>   - [索引选择性高的列放在索引的前面](https://www.cnblogs.com/liyasong/p/mysql_xuanzexing_index.html)
>   - [组合索引 (composite indexes)](https://www.cnblogs.com/zjdxr-up/p/8319881.html)
>   - [覆盖索引 (Covering indexes)](https://www.cnblogs.com/myseries/p/11265849.html) ：查询字段和条件字段都包含在一个联合索引中，不需要回表
>   - [MySQL8 三大索引](https://www.mdnice.com/writing/ca72a1892384484aa67bc37398dea3b8) 
>   - 查找重复索引及冗余索引
>       1. 语句查询
>       ```sql
>       use information_schema;
>
>       select s1.table_schema, s1.table_name, s1.index_name as index1, s2.index_name as index2, s1.column_name as dup_col 
>       from statistics s1
>       join statistics s2 on s1.table_schema = s2.table_schema and s1.table_name = s2.table_name and s1.seq_in_index = s2.seq_in_index and s1.column_name = s2.column_name
>       where s1.seq_in_index = 1 and s1.index_name <> s2.index_name;
>       ```
>       2. pt-duplicate-key-checker
>   - 删除不用的索引
>       - pt-index-usage
>2. 避免全表扫描：①对无索引的表进行的查询；②放弃索引进行的查询
>   1. 在 ①where ②order by ③group by ④多表关联涉及的列 上建立索引
>   2. 避免使用 is null 和 is not null，建表时尽量设置 not null
>   3. 避免使用 != 和 <>
>   4. 避免使用 or 来连接条件，可以使用 union 或 union all 代替
>   5. 避免使用 左% like，如 field like "%x" 和 field like "%x%"；代替：①locate('x', field) > 0，②[FULLTEXT](https://juejin.cn/post/6969887148036063239) ，③全文搜索引擎
>   6. 避免对字段进行操作，①表达式 ②函数 ③自动转换 ④手动转换
>   7. 避免使用[变量](https://www.cnblogs.com/Brambling/p/9259375.html)? ，如 where field = @num;
>3. [避免使用 select *](https://www.cnblogs.com/MrYuChen-Blog/p/13936680.html)
>   1. 增加网络开销，
>   2. 大字段(长度超过728字节)，会先把超出的数据序列化到另外一个地方，等于多增加一次 IO 操作
>   3. 失去了覆盖索引的可能性
>4. [避免使用子查询和 join](https://blog.csdn.net/weixin_38676357/article/details/81510079)
>   - [联表查询 和 单表查询+业务层处理 比较](https://www.zhihu.com/question/68258877)
>5. [小表驱动大表](https://blog.csdn.net/qq_20891495/article/details/93744495)
>   - [根据驱动表优化查询](https://www.cnblogs.com/zhengyun_ustc/p/slowquery1.html)
>   - [in 小表内大表外，exists 小表外大表内](https://www.cnblogs.com/zjxiang/p/9160810.html)
>6. 反范式：适当增加冗余数据
>7. 汇总表/缓存表，定时生成数据，用于用户数据耗时操作
>8. [offset 和 limit 分页优化](https://juejin.cn/post/6844903939247177741)
>9. 垂直分表：①不常用的字段 ②大字段 ③经常一起使用的字段
>10. 水平分表：mod(id, 5)；查询用分表，统计用总表
>11. [表分区](https://www.cnblogs.com/zhouguowei/p/9360136.html)
>## 数据类型选择
>1. 数字类型 > 日期类型|二进制类型 > 字符类型
>2. char > varchar，小于 50byte 建议使用 char
>3. 精确数据使用 decimal，非精确数据使用 float
>4. 整型保存 IP，INET_ATON('192.168.0.1')
>### [explain](https://www.cnblogs.com/qlqwjy/p/7767479.html)
>- [type](https://blog.csdn.net/lilongsy/article/details/95184594)
>   - system：①表只有一行数据 ②引擎是 MyISAM
>   - const：①条件列为 pk 或 unique ②条件值可被优化器视为常量
>   - eq_ref：①等值关联 ②被驱动表的关联列为 pk 或 unique not null
>   - ref：条件列只使用最左前缀，或不是 pk 或 unique
>   - range：范围扫描，<>, <, <=, >, >=, between, like, in, not in
>   - index：全索引扫描
>   - ALL：全表扫描
>- possible_keys：可能用到的索引
>- key：实际使用的索引
>- [key_len](https://www.cnblogs.com/lukexwang/articles/7060950.html) ：使用索引的长度
>- ref：显示索引的哪一列被使用
>- rows：返回结果需要查询的行数
>- extra：[优化 Using temporary 和 Using filesort](https://www.dazhuanlan.com/mrlonely1988/topics/1146538)
>### 慢查询
>1. 开启慢查询
>```sql
>show variables like '%slow%';                           -- 查看慢查询日志文件位置
>set global log_queries_not_using_indexes = on;
>set global long_query_time = 1;
>set global slow_query_log = on;
>```
>2. 慢查询日志存储格式
>```
># Time: 2021-09-13T04:00:44.885048Z
># User@Host: root[root] @  [172.17.0.1]  Id:    12
># Query_time: 0.000205  Lock_time: 0.000099 Rows_sent: 2  Rows_examined: 2
>SET timestamp=1631505644;
>select * from store limit 10;
>```
>3. 慢查询日志分析工具
>   1. mysqldumpslow
>   ```
>       # 查看帮助
>       mysqldumpslow -h
>       mysqldumpslow mysqldumpslow [ OPTS... ] [ LOGS... ]
>   ```
>   2. pt-query-digest
>   ```
>       # 查看帮助
>       pt-query-digest -h
>       # 输出到文件
>       pt-query-digest slow.log > slow_log.report
>       # 输出到数据库表
>       pt-query-digest slow.log -review h=127.0.0.1,D=test,p=root,P=3306,u=root,t=query_view \
>       --create-reviewtable --review-history t=hostname_slow
>   ```
>   3. 关注哪些慢查询？
>   ```
>       1. 次数多且占用时间长的 SQL
>       2. IO 大的 SQL                    pt-query-digest 的 Rows examine
>       3. 未命中索引的 SQL                pt-query-digest 的 Rows examine 和 Rows Send 的对比
>   ```
>### 配置
>1. 系统配置
>   - 网络方面，修改 /etc/sysctl.conf
>   ```
>       # 增加 tcp 支持的队列数
>       net.ipv4.tcp_max_syn_backlog = 65535
>       # 减少断开连接时，资源回收
>       net.ipv4.tcp_max_tw_buckets = 8000
>       net.ipv4.tcp_tw_reuse = 1
>       net.ipv4.tcp_tw_recycle = 1
>       net.ipv4.tcp_fin_timeout = 10
>   ```
>   - 打开文件数限制：ulimit -a 查看目录的各位限制，可以修改 /etc/security/limits.conf
>   ```
>       soft nofile 65535
>       hard nofile 65535
>   ```
>   - 关闭防火墙 iptables, selinux
>2. MySQL 配置
>- 查找配置文件的顺序：/usr/sbin/mysqld --verbose --help | grep -A 1 'Default options'
>```
>innodb_buffer_pool_size            # 缓冲池大小；如果只有 innodb 表，推荐配置为总内存的75%
>innodb_buffer_pool_instances       # 缓冲池的个数，默认1个
>innodb_flush_log_at_trx_commit     # 0：每秒 flush | 1：每次 flush | 2：每次刷到缓冲区每秒 flush；默认1，推荐2
>innodb_log_buffer_size             # 能存在一秒日志即可
>innodb_read_io_threads             # 推荐 CPU 核心数和读写情况决定
>innodb_write_io_threads
>innodb_file_per_table              # 每个表使用独立的表空间，默认 OFF，推荐 ON
>innodb_stats_on_metadata           # 什么情况下刷新 innodb 表的统计信息
>```
>3. 第三方配置工具：[Percona Configuration Wizard](https://tools.percona.com/wizard)
---
## 锁
- [MySQL 8.0 Reference Manual :: InnoDB Locking](https://dev.mysql.com/doc/refman/8.0/en/innodb-locking.html)
- [MySQL 锁机制详解](https://www.cnblogs.com/volcano-liu/p/9890832.html)
- [MySQL 加锁分析](https://www.cnblogs.com/rjzheng/p/9950951.html)
- [MySQL 多版本并发控制与锁机制](https://blog.csdn.net/litianxiang_kaola/article/details/83003190)
- [MySQL 的 MVCC(多版本并发控制)](https://blog.csdn.net/Waves___/article/details/105295060)
>### 锁的类型
>1. 意向锁 (Intention Locks)：表级锁，指示事务稍后需要对表中的行使用哪种类型的锁（共享/排它）
>    1. 意向共享锁 (Intention Shared Locks, IS)：`SELECT ... FOR SHARE`
>    2. 意向排它锁 (Intention Exclusive Locks, IX)：[SELECT ... FOR UPDATE](https://www.cnblogs.com/xiao-lei/p/12598552.html)
>
>        | |X|IX|S|IS|
>        |:---|:---|:---|:---|:---|
>        |X|冲突|冲突|冲突|冲突|
>        |IX|冲突|兼容|冲突|兼容|
>        |S|冲突|冲突|兼容|兼容|
>        |IS|冲突|兼容|兼容|兼容|
>2. 共享/排它锁 (Shared and Exclusive Locks)
>    1. 共享锁 (Shared Locks, S)：
>        - 对符合条件的行加S锁，其它事务可以对这些记录添加IS锁和S锁，即其它事务可以读取这些数据但无法修改
>    2. 排它锁 (Exclusive Locks, X)：
>        - 对符合条件的行加X锁，其它事务无法对这些记录加任何IS锁和IX锁，即其它事务无法对这些记录进行读取和修改 
>        - 不会阻止非锁定读（普通 select 就是非锁定读，即快照读）
>3. 记录锁 (Record Locks)：`SELECT c1 FROM t WHERE c1 = 10 FOR UPDATE`
>    - 总是锁定索引记录，阻止其它事务插入、更新或删除  
>4. [间隙锁](https://www.jianshu.com/p/32904ee07e56) (Gap Locks)：`SELECT c1 FROM t WHERE c1 BETWEEN 10 and 20 FOR UPDATE`  
>    - 对索引记录之间的间隙的锁，或是对第一个索引之前或最后一个索引记录之后的间隙的锁，防止其他事物插入数据
>    - 不同事务允许持有冲突的间隙锁，容易导致死锁
>    - 隔离级别为 REPEATABLE READ 或 SERIALIZABLE，间隙锁生效
>5. 临键锁 (Next-key Locks)
>    - 记录锁 + 索引记录之前的间隙锁，左开右闭
>    - 隔离级别为 REPEATABLE READ 或 SERIALIZABLE，临键锁生效
>6. 插入意向锁 (Insert Intention Locks)
>7. 自增锁 (AUTO-INC Locks)
>### REPEATABLE READ 下的加锁规则
>1. 原则：
>   - 加锁基本单位 Next-key Locks
>   - 查找过程中访问到的对象会加锁
>2. 索引上等值查询优化：
>   - 给唯一索引加锁时，Next-key Locks → Record Locks
>   - 向右遍历且最后一个值不满足等值条件时，Next-key Locks → Gap Locks
>   - 对于覆盖索引查询，不对聚簇索引加锁 ?
>3. 唯一索引上的范围查询会访问到不满足条件的第一个值为止
---