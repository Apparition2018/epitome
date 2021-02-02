# MySQL 优化

---
## 参考网站

---
## 安装
1. [mysql完全卸载教程](https://blog.csdn.net/qq_41140741/article/details/81489531)
2. [同时安装MySQL5和MySQL8](https://blog.csdn.net/qq_32793985/article/details/105807328)
3. [MySql5.6版修改用户登录密码](https://www.cnblogs.com/zhouerba/p/7380504.html)
- mysql 5.6 不需要执行 mysqld --initialize
---
## [my.ini](https://www.cnblogs.com/missmeng/p/13404228.html)
    [mysqld]
    # 设置3306端口
    port=3306
    # 自定义设置mysql的安装目录，即解压mysql压缩包的目录
    basedir=F:\mysql-8.0.16-winx64
    # 自定义设置mysql数据库的数据存放目录
    datadir=F:\mysql-8.0.16-winx64\data
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
    # 默认使用“mysql_native_password”插件认证
    default_authentication_plugin=mysql_native_password
    ###
    performance_schema=off
    table_definition_cache=400
    table_open_cache=64
    innodb_buffer_pool_chunk_size=64M
    innodb_buffer_pool_size=64M
    [mysql]
    # 设置mysql客户端默认字符集
    default-character-set=utf8mb4
    [client]
    # 设置mysql客户端连接服务端时默认使用的端口和默认字符集
    port=3306
    default-character-set=utf8mb4
---