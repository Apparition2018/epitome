# [MySQL 程序](https://dev.mysql.com/doc/refman/8.0/en/programs.html)

---
## [Server and Server-Startup Programs](https://dev.mysql.com/doc/refman/8.0/en/programs-server.html)
1. [mysqld](https://dev.mysql.com/doc/refman/8.0/en/mysqld.html)：MySQL Server，完成 MySQL 安装中的大部分工作
    - `mysqld --verbose --help`：查看启动时可以指定的选项
    - [The MySQL Server](https://dev.mysql.com/doc/refman/8.0/en/mysqld-server.html)：MySQL Server 命令选项、系统变量、状态变量
    - [Installing and Upgrading MySQL](https://dev.mysql.com/doc/refman/8.0/en/installing.html)：安装 MySQL 和设置初始配置信息
2. [mysqld_safe](https://dev.mysql.com/doc/refman/8.0/en/mysqld-safe.html)：在 Unix上 启动 mysqld server 的推荐方法
3. [mysql.server](https://dev.mysql.com/doc/refman/8.0/en/mysql-server.html)：一个脚本，该脚本使用 mysqld_safe 启动 MySQL server
---
## [Client Programs](https://dev.mysql.com/doc/refman/8.0/en/programs-client.html)
1. [mysql](https://dev.mysql.com/doc/refman/8.0/en/mysql.html)：MySQL 命令行客户端
2. [mysqladmin](https://dev.mysql.com/doc/refman/8.0/en/mysqladmin.html)：MySQL Server 管理程序，执行管理操作
3. [mysqlcheck](https://dev.mysql.com/doc/refman/8.0/en/mysqlcheck.html)：表维护程序（检查、修复、优化和分析表）
4. [mysqldump](https://dev.mysql.com/doc/refman/8.0/en/mysqldump.html)：数据库备份程序
5. [mysqlimport](https://dev.mysql.com/doc/refman/8.0/en/mysqlimport.html)：数据导入程序
6. [mysqlshow](https://dev.mysql.com/doc/refman/8.0/en/mysqlshow.html)：显示数据库、表和列信息
7. [mysqlslap](https://dev.mysql.com/doc/refman/8.0/en/mysqlslap.html)：压力测试工具
---
## [Administrative and Utility Programs](https://dev.mysql.com/doc/refman/8.0/en/programs-admin-utils.html)
1. [mysql_config_editor](https://dev.mysql.com/doc/refman/8.0/en/mysql-config-editor.html)：MySQL 配置实用程序，能够将身份验证凭据存储在名为 .mylogin.cnf 的模糊登录路径文件中。MySQL 客户端程序稍后可以读取该文件，以获得连接 MySQL 服务器的身份验证凭据
    - `mysql_config_editor set --login-path=test_local --user=root --host=localhost --port=3306`
2. [mysqlbinlog](https://dev.mysql.com/doc/refman/8.0/en/mysqlbinlog.html)：处理二进制日志文件的实用程序
3. [mysqldumpslow](https://dev.mysql.com/doc/refman/8.0/en/mysqldumpslow.html)：汇总慢查询日志文件
---