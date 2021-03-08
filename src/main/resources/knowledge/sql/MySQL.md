# MySQL

---
## 参考网站

---
## 问题
1. [使用 mysql 应该注意的细节](https://www.cnblogs.com/zhangyiqinga/p/9753484.html)
2. [delimiter 的用法和作用](https://blog.csdn.net/langkeziju/article/details/14446671)
3. [MySQL 中实现 rank 排名查询](https://blog.csdn.net/justry_deng/article/details/80597916)
4. [Mysql 为什么默认定义varchar(255) 而不是varchar(256)](https://juejin.cn/post/6844903894703685646)
5. [Mysql 8 新特性 window functions 的作用](https://www.jb51.net/article/129447.htm)
6. [mysql中的回表查询与索引覆盖](https://www.cnblogs.com/yanggb/p/11252966.html)
---
## 安装
1. [mysql完全卸载教程](https://blog.csdn.net/qq_41140741/article/details/81489531)
2. [MySQL 8.0 压缩包版安装方法](https://www.cnblogs.com/xuqp/p/9172254.html)
3. [同时安装MySQL5和MySQL8](https://blog.csdn.net/qq_32793985/article/details/105807328)
4. [MySql5.6版修改用户登录密码](https://www.cnblogs.com/zhouerba/p/7380504.html)
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