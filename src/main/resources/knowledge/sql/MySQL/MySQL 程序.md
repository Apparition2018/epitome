# [MySQL 程序](https://dev.mysql.com/doc/refman/8.2/en/programs.html)

---
## [Server and Server-Startup Programs](https://dev.mysql.com/doc/refman/8.2/en/programs-server.html)
1. [mysqld](https://dev.mysql.com/doc/refman/8.2/en/mysqld.html)：MySQL Server，完成 MySQL 安装中的大部分工作
    - `mysqld --verbose --help`：查看启动时可以指定的选项
    - [The MySQL Server](https://dev.mysql.com/doc/refman/8.2/en/mysqld-server.html)：MySQL Server 命令选项、系统变量、状态变量
    - [Installing and Upgrading MySQL](https://dev.mysql.com/doc/refman/8.2/en/installing.html)：安装 MySQL 和设置初始配置信息
2. [mysqld_safe](https://dev.mysql.com/doc/refman/8.2/en/mysqld-safe.html)：在 Unix上 启动 mysqld server 的推荐方法
3. [mysql.server](https://dev.mysql.com/doc/refman/8.2/en/mysql-server.html)：一个脚本，该脚本使用 mysqld_safe 启动 MySQL server
---
## [Client Programs](https://dev.mysql.com/doc/refman/8.2/en/programs-client.html)
1. [mysql](https://dev.mysql.com/doc/refman/8.2/en/mysql.html)：MySQL 命令行客户端
2. [mysqladmin](https://dev.mysql.com/doc/refman/8.2/en/mysqladmin.html)：MySQL Server 管理程序，执行管理操作
3. [mysqlcheck](https://dev.mysql.com/doc/refman/8.2/en/mysqlcheck.html)：表维护程序（检查、修复、优化和分析表）
4. [mysqldump](https://dev.mysql.com/doc/refman/8.2/en/mysqldump.html)：数据库备份程序
    - 备份所有数据库：`mysqldump -uroot -pCesc123! --all-databases > backup.sql`
    - 备份特定数据库：`mysqldump -uroot -pCesc123! --databases epitome ry > backup.sql`
    - 备份某个数据库中的特定表：`mysqldump -uroot -pCesc123! epitome dept emp > backup.sql`
    - [常用选项](https://dev.mysql.com/doc/refman/8.2/en/mysqldump.html#idm45156586500944)
        ```
        --routines              转储过程和函数
        --triggers              转储触发器
        --events                转储事件
        --single-transaction    转储数据之前发出 BEGIN SQL 语句，保证了主从服务器数据库的一致性
                                与 --lock-tables 互斥，因为 LOCK TABLES 会导致隐式提交任何挂起的事务
        --master-data           将二进制日志文件名和位置写入输出
                                自动关闭 --lock-tables。打开 --lock-all-tables，除非还指定了 --single-transaction，在这种情况下，全局读锁仅在转储开始时获得一小段时间
            --master-data=1     记录主库的 binlog 和 pos 点，并在文件中不注释这一行，即恢复时会执行
            --master-data=2     记录主库的 binlog 和 pos 点，并在文件中注释掉这一行
        --lock-tables           对于每个转储的数据库，在转储之前锁定所有要转储的表
                                对于事务表(such as InnoDB)，-single-transaction 是更好的选择，因为不需要锁定表
        --lock-all-tables       锁定所有数据库中的所有表。自动关闭 --single-transaction 和 --lock-tables
        --source-data           同 --master-data，8.0.26 开始使用
        --flush-logs            在开始转储之前刷新 MySQL 服务器日志文件
        --add-drop-database     在每个 CREATE DATABASE 语句之前添加 DROP DATABASE 语句
        ```
    - 重载 SQL 备份文件：`mysql < backup.sql` 或 登录 `mysql -uroot -pCesc123!` → `source backup.sql;`
    - 如果没有使用 --all-databases 和 --databases 选项备份数据，备份输出不包含 CREATE DATABASE 和 USE 语句。同时注意以下两点
        1. 重载 SQL 备份文件时，必须创建（如果需要）和指定数据库
            ```
            mysqladmin create epitome
            mysql epitome < backup.sql
            # or
            mysql -uroot -pCesc123!
            CREATE DATABASE IF NOT EXISTS db1;
            USE db1;
            source backup.sql;
            ```
        2. --add-drop-database 选项无效（其作用：在 CREATE DATABASE 语句之前写入 DROP DATABASE 语句）
5. [mysqlimport](https://dev.mysql.com/doc/refman/8.2/en/mysqlimport.html)：数据导入程序
6. [mysqlshow](https://dev.mysql.com/doc/refman/8.2/en/mysqlshow.html)：显示数据库、表和列信息
7. [mysqlslap](https://dev.mysql.com/doc/refman/8.2/en/mysqlslap.html)：压力测试工具
---
## [Administrative and Utility Programs](https://dev.mysql.com/doc/refman/8.2/en/programs-admin-utils.html)
1. [mysql_config_editor](https://dev.mysql.com/doc/refman/8.2/en/mysql-config-editor.html)：MySQL 配置实用程序，能够将身份验证凭据存储在名为 .mylogin.cnf 的模糊登录路径文件中。MySQL 客户端程序稍后可以读取该文件，以获得连接 MySQL 服务器的身份验证凭据
    - `mysql_config_editor set --login-path=test_local --user=root --host=localhost --port=3306 --password`
    - `mysql_config_editor print --all`
    - `mysql_config_editor remove --login-path=test_local`
2. [mysqlbinlog](https://dev.mysql.com/doc/refman/8.2/en/mysqlbinlog.html)：处理二进制日志文件的实用程序
3. [mysqldumpslow](https://dev.mysql.com/doc/refman/8.2/en/mysqldumpslow.html)：汇总慢查询日志文件
---