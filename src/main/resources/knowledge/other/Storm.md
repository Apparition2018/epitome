# Storm
- Twitter 开源的一个类似于 Hadoop 的实时数据处理框  
![Storm 的诞生](http://img2.mukewang.com/60c249b6000126d205000310.jpg)
---
## Storm 应用场景
1. 推荐系统：实时推荐，根据下单或加入购物车推荐相关商品
2. 网站统计：实时销量、流量统计
3. 监控预警系统
4. 金融系统
---
## Storm 特性
1. 适用场景广泛：实时处理或更新，持续并行化查询，满足大量场景
2. 可伸缩性高：扩展计算任务，只需要加机器并提高并行度
3. 保证数据无丢失：保证每条消息都会被处理
4. 异常健壮：集群易管理，可轮流重启节点
5. 容错性好：消息处理过程出现异常，会进行重试
6. 语言无关性：topology 可以用多种语言编写
---
## Storm 原理
>### 大数据框架架构类型
>1. 主从架构：简单，高效，但主节点存在单点问题
>2. 对称架构：复杂，效率较低，但无单点问题，更加可靠
>### Storm 主从架构
>![Storm 主从架构](http://img.mukewang.com/60c24bb50001447a05000303.jpg)
>1. Nimbus: 
>   1. 接受客户端 topo 代码，拆分成多个 task，将 task 信息存入 zk
>   2. 将 task 分配给 Supervisor，将映射关系存入 zk
>   3. 故障监测
>2. Supervisor:
>   1. 从 Nimbus 目录读取代码，从 zk 上读取 Nimbus 分配的 task
>   2. 启动工作进程 Worker 执行任务
>   3. 监测运行的工作进程 Worker
>3. Worker:
>   1. 从 zk 上读取分配的 task，并计算出 task 需要给哪些 task 发信息
>   2. 启动一个或多个 Executor 线程执行 task
>4. Zookeeper:
>   1. Nimbus 于 Supervisor 进行通信（分配任务和心跳）
>   2. Supervisor 于 Worker 进行通信（分配任务和心跳）
>   3. Nimbus 高可用（HA 机制）
---