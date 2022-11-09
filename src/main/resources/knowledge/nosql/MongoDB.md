# MongoDB
- 由 C++ 语言编写的
- 基于分布式文件存储的开源数据库系统
---
## Reference
1. [MongoDB Documentation](https://www.mongodb.com/docs/)
2. [MongoDB Manual](https://www.mongodb.com/docs/manual/)
3. [尚硅谷MongoDB入门基础教程](https://www.bilibili.com/video/BV18s411E78K/)
4. [MongoDB 教程 | 菜鸟教程](https://www.runoob.com/mongodb/mongodb-tutorial.html)
---
## 数据库对比
| 术语/概念 | RDBMS       | MongoDB         | ElasticSearch |
|-------|-------------|-----------------|---------------|
| 数据库   | database    | database        | es库           |
| 表/集合  | table       | collection      | index         |
| 行/文档  | row         | document (BSON) | document      |
| 列/字段  | column      | field           | field         |
| 主键    | primary key | _id             | _id           |
| 索引    | index       | index           |               |
| 表连接   | join        | 嵌入文档            |               |
---
## [Install](https://www.mongodb.com/docs/v6.0/administration/install-community/)
- [yum](https://www.mongodb.com/docs/v6.0/tutorial/install-mongodb-on-red-hat/)
- [.tgz](https://www.mongodb.com/docs/v6.0/tutorial/install-mongodb-on-red-hat-tarball/)
---
## [mongod.conf](https://www.mongodb.com/docs/manual/reference/configuration-options/)
```
systemLog:
    # 安静模式（过滤不重要的日志），调试时可设置为 false
    quiet: true
    path: "/usr/local/mongodb/logs/mongod.log"
    # 追加日志模式
    logAppend: true
    destination: file
storage:
    dbPath: "/usr/local/mongodb/db/"
    journal:
        # 启用日志持久化
        enabled: true
processManagement:
    # 后台运行（守护进程）
    fork: true
net:
    # mongod 监听客户端连接的 主机名/IP 地址/完整的 Unix 域套接字路径，多个用逗号隔开，默认 localhost
    # 提供外网访问，不对 IP 进行绑定
    bindIp: 0.0.0.0
    port: 27017
    # 最大并发连接，默认 65536
    maxIncomingConnections: 65536
security:
    # 启用基于角色的访问控制
    authorization: enabled
setParameter:
    enableLocalhostAuthBypass: false
```
---
## [BSON](https://www.mongodb.com/docs/v6.0/reference/bson-types/)

---
