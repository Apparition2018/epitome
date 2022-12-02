# MongoDB
- 由 C++ 语言编写的
- 基于分布式文件存储的开源数据库系统
---
## Reference
1. [MongoDB Documentation](https://www.mongodb.com/docs/)
2. [MongoDB Manual](https://www.mongodb.com/docs/manual/)
3. [尚硅谷MongoDB入门基础教程](https://www.bilibili.com/video/BV18s411E78K/)
4. [黑马程序员MongoDB基础入门到高级进阶](https://www.bilibili.com/video/BV1bJ411x7mq/)
5. [2022B站最新的MongoDB视频教程](https://www.bilibili.com/video/BV1CS4y1e7Pb/)
6. [MongoDB 教程 | 菜鸟教程](https://www.runoob.com/mongodb/mongodb-tutorial.html)
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
1. 高性能
    - 支持嵌入式文档，减少了数据库系统上的I/O活动
    - 支持多种索引
2. 高可用
    - 单节点：Journal 机制
    - 集群：副本集(replica set)，维护相同的数据集，提供冗余并增加数据可用性
3. 高扩展性：分片
4. BSON 文档模型：适合敏捷开发
5. 丰富的查询支持：数据集合、文本搜索、地理空间查询等
---
## MongoDB 劣势
1. 事务 ?
2. 占用空间大
3. 集群分片数据分布不均匀
4. 单机可靠性差
5. 大数据量持续插入，写入性能有较大波动
---
## 应用场景
- 高并发、高可用、高可扩展、海量数据、低事务要求
1. 游戏：存储用户信息、用户装备、积分
2. 物流：存储订单信息
3. 社交：存储用户信息、用户发表的朋友圈信息、通过地理位置索引实现附近的人、地点
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
db.collection.estimatedDocumentCount()  包装计数以返回集合或视图中文档的大约计数，忽略查询条件

# Index
db.collection.createIndex()             在集合上构建索引
db.collection.createIndexes()           在集合上构建一个或多个索引
db.collection.getIndexes()              返回描述集合上现有索引的文档数组
db.collection.totalIndexSize()          报告集合上索引使用的总大小
db.collection.hideIndex()               从查询计划器中隐藏索引
db.collection.unhideIndex()             从查询计划器中取消索引
db.collection.dropIndex()               移除集合上的指定索引
db.collection.dropIndexes()             移除集合上的所有索引
```
### [Cursor Methods](https://www.mongodb.com/docs/v4.4/reference/method/js-cursor/)
```
cursor.pretty()                         配置游标以易于阅读的格式显示结果
cursor.explain()                        报告游标的查询执行计划
cursor.hint()                           强制MongoDB对查询使用特定的索引
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
- [Aggregation Pipeline Stages](https://www.mongodb.com/docs/v4.4/reference/operator/aggregation-pipeline/)
```
$match                                  过滤文档流，只允许匹配的文档未经修改地传递到下一个管道阶段
$project                                重新塑造流中的每个文档，例如添加新字段或删除现有字段
$lookup                                 对同一数据库中的另一个集合执行左外连接，以从“已连接”集合中筛选待处理的文档
$sort                                   按指定的排序键对文档流重新排序
$group                                  按指定的标识符表达式对输入文档进行分组，并对每个组应用累加器表达式(如果指定了)
$skip                                   跳过前n个文档(其中n是指定的跳过号)，并将剩余的文档未经修改地传递给管道
$limit                                  将前n个未修改的文档传递到n为指定限制的管道
$unwind                                 分解输入文档中的数组字段，为每个元素输出文档
$graphLookup                            在集合上执行递归搜索
$facet                                  在同一组输入文档的单一阶段中处理多个聚合管道
$bucket                                 根据指定的表达式和桶边界将传入的文档分类到称为桶的组中
```
---
## 聚合操作
### 聚合实战1
1. data.js
```javascript
// book
var tags = ["nosql", "mongodb", "document", "developer", "popluar"];
var types = ["technology", "sociality", "travel", "novel", "literature"];
var books = [];
for (var i = 0; i < 50; i++) {
    var typeIdx = Math.floor(Math.random() * types.length);
    var tagIdx = Math.floor(Math.random() * tags.length);
    var tagIdx2 = Math.floor(Math.random() * tags.length);
    var favCount = Math.floor(Math.random() * 100);
    var username = "xx00" + Math.floor(Math.random() * 10);
    var age = 20 + Math.floor(Math.random() * 15);
    var book = {
        title: "book-" + i,
        type: types[typeIdx],
        tag: [tags[tagIdx], tags[tagIdx2]],
        favCount: favCount,
        author: { name: username, age: age }
    };
    books.push(book);
}
db.book.insertMany(books);
db.book.insert([
    {
        "title": "book-51",
        "type": "technology",
        "favCount": 11,
        "tag": [],
        "author": {
            "name": "fox",
            "age": 28
        }
    },
    {
        "title": "book-52",
        "type": "technology",
        "favCount": 15,
        "author": {
            "name": "fox",
            "age": 28
        }
    },
    {
        "title": "book-53",
        "type": "technology",
        "favCount": 20,
        "tag": [
            "nosql",
            "document"
        ],
        "author": {
            "name": "fox",
            "age": 28
        }
    }
]);

db.customer.insert({ customerCode: 1, name: "customer1", phone: "13112345678", address: "address1" });
db.customer.insert({ customerCode: 2, name: "customer2", phone: "13112345679", address: "address2" });

db.order.insert({ orderId: 1, orderCode: "order001", customerCode: 1, price: 200 });
db.order.insert({ orderId: 2, orderCode: "order002", customerCode: 2, price: 400 });

db.orderItem.insert({ itemId: 1, productName: "apples", qutity: 2, orderId: 1 });
db.orderItem.insert({ itemId: 2, productName: "oranges", qutity: 2, orderId: 1 });
db.orderItem.insert({ itemId: 3, productName: "mangoes", qutity: 2, orderId: 1 });
db.orderItem.insert({ itemId: 4, productName: "apples", qutity: 2, orderId: 2 });
db.orderItem.insert({ itemId: 5, productName: "oranges", qutity: 2, orderId: 2 });
db.orderItem.insert({ itemId: 6, productName: "mangoes", qutity: 2, orderId: 2 });
```
2. 复制 data.js 到 mongodb：`docker cp C:/Users/HP/Desktop/data.js mongo:/data/db/js/`
3. 执行 `load("data.js")`
    - `load()` 接受相对路径和绝对路径，可以使用 `pwd()` 查看 mongo shell 的当前工作目录
4. 聚合操作
    1. 标签热度排行：按其关联 book 的收藏数计算
    ```javascript
    db.book.aggregate([
        { $match: { favCount: { $gt: 0 } } },
        { $unwind: "$tag" },
        { $group: { _id: "$tag", total: { $sum: "$favCount" } } },
        { $sort: { total: -1 } }
    ])
    ```
    2. 统计 book 收藏数 [0,10), [10,60), [60,80), [80,100), [100,+∞)
    ```javascript
    db.book.aggregate([{
        $bucket: {
            groupBy: "$favCount",
            boundaries: [0, 10, 60, 80, 100],
            default: "other",
            output: { "count": { $sum: 1 } }
        }
    }])
    ```
    3. customer c left join order o on c.customerCode = o.customerCode
    ```javascript
    db.customer.aggregate([{
        $lookup: {
            from: "order",
            localField: "customerCode",
            foreignField: "customerCode",
            as: "customerOrder"
        }
    }]).pretty()
    ```
    4. order o left join customer c on o.customerCode = c.customerCode
       left join orderItem oi on o.orderId = oi.orderId
    ```javascript
    db.order.aggregate([
        {
            $lookup: {
                from: "customer",
                localField: "customerCode",
                foreignField: "customerCode",
                as: "customer"
            }
        },
        {
            $lookup: {
                from: "orderItem",
                localField: "orderId",
                foreignField: "orderId",
                as: "orderItem"
            }
        }
    ]).pretty()
    ```
### 聚合实战2
1. [zips.json](https://media.mongodb.org/zips.json)
2. [MongoDB Command Line Database Tools](https://www.mongodb.com/try/download/database-tools)
3. `.\mongoimport.exe -h localhost -p 27017 -u fox -p fox --authenticationDatabase=admin -d test -c zip --file C:/Users/HP/Desktop/zips.json`
4. 聚合操作
    1. 返回人口超过1000万的州
    ```javascript
    db.zip.aggregate([
        { $group: { _id: "$state", totalPop: { $sum: "$pop" } } },
        { $match: { totalPop: { $gt: 1000 * 10000 } } }
    ])
    ```
    2. 返回各州平均城市人口
    ```javascript
    db.zip.aggregate([
        { $group: { _id: { state: "$state", city: "$city" }, cityPop: { $sum: "$pop" } } },
        { $group: { _id: "$_id.state", avgCityPop: { $avg: "$cityPop" } } }
    ])
    ```
    3. 按州返回人口最大和最小的城市
    ```javascript
    db.zip.aggregate([
        { $group: { _id: { state: "$state", city: "$city" }, cityPop: { $sum: "$pop" } } },
        { $sort: { cityPop: 1 } },
        {
            $group: {
                _id: "$_id.state",
                biggestCity: { $last: "$_id.city" },
                biggestPop: { $last: "$cityPop" },
                smallestCity: { $first: "$_id.city" },
                smallestPop: { $first: "$cityPop" }
            }
        },
        {
            $project: {
                _id: 0,
                state: "$_id",
                biggestCity: { name: "$biggestCity", pop: "$biggestPop" },
                smallestCity: { name: "$smallestCity", pop: "$smallestPop" }
            }
        }
    ])
    ```
---
## [索引](https://www.mongodb.com/docs/manual/indexes/)
- B+Tree 数据结构
    1. 官网：MongoDB indexes use a B-tree data structure
    2. [WiredTiger](https://source.wiredtiger.com/3.0.0/tune_page_size_and_comp.html)：WiredTiger maintains a table's data in memory using a data structure called a B-Tree ( B+ Tree to be specific), referring to the nodes of a B-Tree as pages
    3. [数据结构可视化](https://www.cs.usfca.edu/~galles/visualization/Algorithms.html)
- 索引类型

| 类型     | 英文                   | 说明                                                    |
|--------|----------------------|-------------------------------------------------------|
| 单字段索引  | Single Field Indexes |                                                       |
| 复合索引   | Compound Indexes     |                                                       |
| 多键索引   | Multikey Indexes     | 对存储在数组中的内容进行索引                                        |
| 地理空间索引 | Geospatial Indexes   | 2d Indexes：使用平面几何返回结果<br/>2dsphere Indexes：使用球面几何返回结果 |
| 文本索引   | Text Indexes         | 支持在集合中搜索字符内容                                          |
| 散列所有   | Hashed Indexes       | 对字段值的哈希值进行索引，支持基于哈希的分片                                |
| 聚簇索引   | Clustered Indexes    |                                                       |
| 通配符索引  | Wildcard Indexes     | 支持在一个或一组字段上创建索引来支持查询                                  |
- 索引属性

| 属性              | 说明                    |
|-----------------|-----------------------|
| Unique Indexes  | 拒绝索引字段的重复值            |
| Partial Indexes | 只对集合中满足指定筛选表达式的文档进行索引 |
| Sparse Indexes  | 确保索引只包含具有索引字段的文档的条目   |
| TTL Indexes     | 在一定时间后自动从集合中删除文档      |
| Hidden Indexes  | 对查询计划器是不可见的，不能用于支持查询  |
### 索引实战
1. data.js
```javascript
db.restaurant.insert({
    restaurantId: 0,
    restaurantName: "兰州牛肉面",
    location: {
        type: "Point",
        coordinates: [-73.97, 40.77]
    }
})

db.store.insert([
    { _id: 1, name: "Java Hut", description: "Coffee and cakes" },
    { _id: 2, name: "Burger Buns", description: "Gourmet hamburgers" },
    { _id: 3, name: "Coffee Shop", description: "Just Coffee" },
    { _id: 4, name: "Clothes Clothes Clothes", description: "Discount clothing" },
    { _id: 5, name: "Java Shoping", description: "Indonesian goods" }
])
```
2. 复制 data.js 到 mongodb：`docker cp C:/Users/HP/Desktop/data.js mongo:/data/db/js/`
3. 地理空间索引
    1. 创建 2dsphere 索引：`db.restaurant.createIndex({ location: "2dsphere" })`
    2. 查询附件10000米商家信息
    ```javascript
    db.restaurant.find({
        location: {
            $near: {
                $geometry: {
                    type: "Point",
                    coordinates: [-73.88, 40.78]
                },
                $maxDistance: 10000
            }
        }
    })
    ```
4. 文本索引
    1. 创建文本索引：`db.store.createIndex({ name: "text", description: "text" })`
    2. 查找包含 coffee, shop, java 的商店：`db.store.find({ $text: { $search: "java coffee shop" } })` 
---
## [Explain Results](https://www.mongodb.com/docs/v6.0/reference/explain-results/)

---
## [视图](https://www.mongodb.com/docs/v6.0/core/views/)
1. data.js
```javascript
var orders = [];
var shipping = [];
var addresses = ["广西省玉林市", "湖南省岳阳市", "湖北省荆州市", "甘肃省兰州市", "吉林省松原市", "江西省景德镇", "辽宁省沈阳市", "福建省厦门市", "广东省广州市", "北京市朝阳区"];
for (var i = 10000; i < 20000; i++) {
    var orderNo = i + Math.random().toString().substring(2, 5);
    orders[i] = {
        orderNo: orderNo,
        userId: i,
        price: Math.round(Math.random() * 10000) / 100,
        qty: Math.floor(Math.random() * 10) + 1,
        orderTime: new Date(new Date().setSeconds(Math.floor(Math.random() * 10000)))
    }
    var address = addresses[Math.floor(Math.random() * 10)];
    shipping[i] = {
        orderNo: orderNo,
        address: address,
        recipient: "wilson",
        province: address.substring(0, 3),
        city: address.substring(3, 3)
    }
}
db.order2.insert(orders);
db.shipping.insert(shipping);
```
2. 复制 data.js 到 mongodb：`docker cp C:/Users/HP/Desktop/data.js mongo:/data/db/js/`
3. 执行 `load("data.js")`
4. 创建视图：当天最高的10笔订单
```javascript
db.createView(
    "orderInfo",    // 视图名称
    "order2",        // 数据源
        [
            { $match: { "orderTime": { $gte: new Date(new Date().toLocaleDateString()) } } },
            { $sort: { "price": -1 } },
            { $limit: 10 },
            { $project: { _id: 0, orderNo: 1, price: 1, orderTime: 1 } }
        ]
)
```
5. 修改视图
```javascript
db.runCommand({
    collMod: "orderInfo",
    viewOn: "order2",
    pipeline: [
        { $match: { "orderTime": { $gte: new Date(new Date().toLocaleDateString()) } } },
        { $sort: { "price": -1 } },
        { $limit: 10 },
        { $project: { _id: 0, orderNo: 1, price: 1, qty: 1, orderTime: 1 } }
    ]
})
```
6. 删除视图：`db.orderInfo.drop()`
---
