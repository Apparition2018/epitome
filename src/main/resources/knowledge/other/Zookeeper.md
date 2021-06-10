# Zookeeper
- 开源的分布式协调服务框架
- Google 的 Chubby 的开源实现
---
## Zookeeper 核心功能
1. 文件系统
    ![Zookeeper 文件系统](http://img2.mukewang.com/60c24f0d000148fb03860338.jpg)
    - 每个目录都是一个 znode 节点
    - znode 节点可直接存储数据
    - 类型：持久化、持久化顺序、临时、临时顺序
2. 通知机制
    - 客户端监听关心的 znode 节点
    - znode 节点有变化（数据改变/删除/子目录添加删除）时，通知客户端
---