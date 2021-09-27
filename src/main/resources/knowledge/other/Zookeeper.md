# Zookeeper
- 开源的分布式协调服务框架
- Google 的 Chubby 的开源实现
---
## Zookeeper 核心功能
1. 文件系统  
    ![Zookeeper 文件系统](https://img2.mukewang.com/60c24f0d000148fb03860338.jpg)
    - 每个目录都是一个 znode 节点
    - znode 节点可直接存储数据
    - 类型：持久化、持久化顺序、临时、临时顺序
2. 通知机制
    - 客户端监听关心的 znode 节点
    - znode 节点有变化（数据改变/删除/子目录添加删除）时，通知客户端
---
## zoo.cfg
```
# Zookeeper 服务器之间或客户端与服务器之间维持心跳的时间间隔（毫秒），即心跳
# Zookeeper 里最小的 session 过期时间就是 tickTime 的两倍
tickTime=2000
# LF 初始通信时限
# 集群中的 follower 服务器(F) 与 leader 服务器(L) 之间初始连接时能容忍的最多心跳数
initLimit=5
# LF 同步通信时限
# 集群中的 follower 服务器(F) 与 leader 服务器(L) 之间请求和应答时能容忍的最多心跳数
syncLimit=2
# 客户端端口
clientPort=2181
# 自动清理历史快照，保留 n 个
autopurge.snapRetainCount
# 自动清理历史快照时间间隔（小时）
autopurge.purgeInterval
#
server.1=localhost:2888:3888;2181
```
## [命令](https://www.cnblogs.com/senlinyang/p/7833669.html)
```
%ZOOKEEPER_HOME%/bin/zkServer.sh [--config <conf-dir>] {start|start-foreground|stop|version|restart|status|print-cmd}

%ZOOKEEPER_HOME%/bin/zkCli.sh [-server localhost:2181]
help
ls [-s] [-w] [-R] path
get [-s] [-w] path                                       获取节点数据
set [-s] [-v version] path data                          设置节点数据
create [-s] [-e] [-c] [-t ttl] path [data] [acl]         创建节点
delete [-v version] path                                 删除节点
```