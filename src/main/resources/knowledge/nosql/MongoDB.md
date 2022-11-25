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
## [BSON (Binary JSON)](https://www.mongodb.com/docs/v6.0/reference/bson-types/)

---
## mongo Shell
### [mongo Shell Quick Reference](https://www.mongodb.com/docs/v4.4/reference/mongo-shell/) 
```
# Command Helpers
help                                    显式帮助
show dbs                                打印服务器上所有的数据库的列表
show databases                          打印所有可用数据库的列表
use <db>                                将当前数据库切换到 <db>
show collections                        打印当前数据库的所有集合的列表

# Basic Shell JavaScript Operations
db.collection.drop()                    完全删除或删除集合

# Administrative Command Helpers
db.dropDatabase()                       删除当前数据库
```
### [MongoDB CRUD Operations](https://www.mongodb.com/docs/v4.4/crud/) 
```
db                                      引用当前数据库的变量

# Insert
db.collection.insert()                  将单个文档或多个文档插入集合中
db.collection.insertOne()               向集合中插入一个新文档
db.collection.insertMany()              向集合中插入多个新文档

# Query
db.collection.find(<query>)             在集合中查找匹配<query>条件的文档
    { <field1>: { <operator1>: <value1> }, ... }
db.collection.findOne(<query>)          查找并返回一个文档
db.collection.find(<query>).count()     返回与查询匹配的文档的总数
db.collection.find(<query>).sort(<sort order>)
                                        在指定的<排序顺序>中返回匹配<查询>条件的文档
db.collection.find( ... ).skip( <n> )   跳过<n>结果
db.collection.find( ... ).limit( <n> )  将结果限制为<n>行

# Update
db.collection.update()                  更新或替换匹配指定筛选器的单个文档，或者更新匹配指定筛选器的所有文档
    {
      <update operator>: { <field1>: <value1>, ... },
      <update operator>: { <field2>: <value2>, ... },
      ...
    }
db.collection.updateOne()               更新集合中单个现有文档
db.collection.updateMany()              更新集合中的多个现有文档
db.collection.replaceOne()              即使可能有多个文档匹配指定的筛选器，也至多替换一个匹配指定筛选器的文档

# Delete
db.collection.remove()                  删除单个文档或匹配指定筛选器的所有文档
db.collection.deleteOne()               即使多个文档可能与指定筛选器匹配，也只能删除一个与指定筛选器匹配的文档
db.collection.deleteMany()              删除符合指定筛选器的所有文档
```
---
## [Operators](https://www.mongodb.com/docs/v4.4/reference/operator/)
- [Query and Projection Operators](https://www.mongodb.com/docs/v4.4/reference/operator/query/)
- [Update Operators](https://www.mongodb.com/docs/v4.4/reference/operator/update/)
```
$set                                    设置文档中字段的值
$unset                                  从文档中删除指定的字段
$inc                                    将字段的值增加指定的数量
$push                                   向数组中添加项
$addToSet                               仅当元素在集合中不存在时，才向数组中添加元素
```
---
