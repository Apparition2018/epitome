# MongoDB
- 由 C++ 语言编写的
- 基于分布式文件存储的开源数据库系统
---
## Reference
1. [MongoDB Documentation](https://www.mongodb.com/docs/)
2. [MongoDB Manual](https://www.mongodb.com/docs/manual/)
3. [尚硅谷MongoDB入门基础教程](https://www.bilibili.com/video/BV18s411E78K/)
4. [2022B站最新的MongoDB视频教程](https://www.bilibili.com/video/BV1CS4y1e7Pb/)
5. [MongoDB 教程 | 菜鸟教程](https://www.runoob.com/mongodb/mongodb-tutorial.html)
---
## 数据库对比
| 术语/概念 |    RDBMS    |     MongoDB     | ElasticSearch |
|-------|:-----------:|:---------------:|:-------------:|
| 数据库   |  database   |    database     |      es库      |
| 表/集合  |    table    |   collection    |     index     |
| 行/文档  |     row     | document (BSON) |   document    |
| 列/字段  |   column    |      field      |     field     |
| 主键    | primary key |       _id       |      _id      |
| 表连接   |    join     |     $lookup     |               |
| 语言    |     SQL     |      类 SQL      |      DSL      |
| 分布式   |     第三方     |       分布式       |      分布式      |
| 扩容    |    分库分表     |       分片        |      分片       |
---
## MongoDB 优势
1. 敏捷开发：基于灵活的 BSON 文档模型
2. 高可用：单节点-Journal机制，集群-副本集
3. 易扩展：支持集群分片
---
## MongoDB 劣势
1. 事务 ?
2. 占用空间大
3. 集群分片数据分布不均匀
4. 单机可靠性差
5. 大数据量持续插入，写入性能有较大波动
---
## MongoDB 应用场景
1. 游戏：存储用户信息、用户装备、积分等
2. 物流：存储订单信息
3. 社交：存储用户信息、用户发表的朋友圈信息、通过地理位置索引实现附近的人、地点等
4. 物联网：存储接入设备信息、设备汇报的日志信息
5. 视频直播：存储用户信息、礼物信息
6. 大数据
---
## 考虑使用 MongoDB
| 应用特征                      | Yes/? |
|---------------------------|-------|
| 不需要复杂/长事务/join            | Yes   |
| 新应用，需求会变，数据模型无法确定，想快速迭代开发 | ?     |
| 2000-3000以上的每秒读写          | ?     |
| TB或PB级的数据存储               | ?     |
| 快速水平扩展                    | ?     |
| 要求存储数据不丢失                 | ?     |
| 99.9999%的高可用              | ?     |
| 大量的地理位置查询、文本查询            | ?     |
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
    # 存储引擎，默认 wiredTiger
    engine: wiredTiger
processManagement:
    # 后台运行（守护进程）
    fork: true
    # 要从其中加载时区数据库的完整路径
    timeZoneInfo: /usr/share/zoneinfo
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
## mongo Shell
### [mongo Shell Quick Reference](https://www.mongodb.com/docs/v4.4/reference/mongo-shell/)
```
# Command Helpers
help                                    显式帮助
db.<collection>.help()                  显示关于集合方法的帮助
show dbs                                打印服务器上所有的数据库的列表
show databases                          打印所有可用数据库的列表
use <db>                                将当前数据库切换到<db>
show collections                        打印当前数据库的所有集合的列表
show users                              打印当前数据库的用户列表
show roles                              打印当前数据库的所有角色的列表
show profile                            打印最近耗时1毫秒或更长时间的5个操作
load()                                  执行一个JavaScript文件
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
                                        在指定的<sort order>中返回匹配<query>条件的文档
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
### [Collections Methods](https://www.mongodb.com/docs/v4.4/reference/method/js-collection/)
```
db.collection.findAndModify()           原子地修改并返回单个文档
db.collection.findOneAndDelete()        找到一个文档并删除它
db.collection.stats()                   关于集合状态的报告
db.collection.drop()                    从数据库中删除指定的集合
```
### [Database Methods](https://www.mongodb.com/docs/v5.0/reference/method/js-database/)
```
db.help()                               显示常用db对象方法的描述
db.createCollection()                   创建一个新的集合或视图。通常用于创建有上限的集合
db.dropDatabase()                       删除当前数据库
db.version()                            返回mongod实例的版本
```
### [User Management Methods](https://www.mongodb.com/docs/v5.0/reference/method/js-user-management/)
```
db.auth()                               将用户验证到数据
db.createUser()                         创建一个新用户
db.dropUser()                           删除单个用户
```
### [Native Methods](https://www.mongodb.com/docs/v4.4/reference/method/js-native/)
```
pwd()                                   返回当前目录
```
---
## [BSON (Binary JSON)](https://www.mongodb.com/docs/v6.0/reference/bson-types/)

---
## [Operators](https://www.mongodb.com/docs/v4.4/reference/operator/)
- [Query and Projection Operators](https://www.mongodb.com/docs/v4.4/reference/operator/query/)
```
SQL                                     MQL

a = 1                                   {a: 1}
a <> 1                                  {a: {$ne: 1}}
a > 1                                   {a: {$gt: 1}}
a >= 1                                  {a: {$gte: 1}}
a < 1                                   {a: {$lt: 1}}
a <= 1                                  {a: {$lte: 1}}

a = 1 AND b = 1                         {$and: [{a: 1}, {b: 1}]}    {a: 1, b: 1}
a = 1 OR b = 1                          {$or: [{a: 1}, {b: 1}]}
a IS NULL                               {a: {$exists: false}}
a IN (1, 2, 3)                          {a: {$in: [1, 2, 3]}}
                                        {a: {$regex: "so"}}         {a: /so/}
```
- [Update Operators](https://www.mongodb.com/docs/v4.4/reference/operator/update/)
```
$set                                    设置文档中字段的值
$unset                                  从文档中删除指定的字段
$inc                                    将字段的值增加指定的数量
$push                                   向数组中添加项
$addToSet                               仅当元素在集合中不存在时，才向数组中添加元素
```
---
## 批量插入数据
1. books.js
```javascript
var tags = ["nosql", "mongodb", "document", "developer", "popluar"];
var types = ["technology", "sociality", "travel", "novel", "literature"];
var books = [];
for (var i = 0; i < 50; i++) {
    var typeIdx = Math.floor(Math.random() * types.length);
    var tagIdx = Math.floor(Math.random() * tags.length);
    var favCount = Math.floor(Math.random() * 100);
    var book = {
        title: "book-" + i,
        type: types[typeIdx],
        tag: tags[tagIdx],
        favCount: favCount,
        author: "xxx" + i
    };
    books.push(book);
}
db.books.insertMany(books);
```
2. 复制 books.js 到容器：`docker cp C:/Users/HP/Desktop/books.js mongo:/data/db/js/`
3. 执行 `load("books.js")`
    - `load()` 接受相对路径和绝对路径，可以使用 `pwd()` 查看 mongo shell 的当前工作目录
---
