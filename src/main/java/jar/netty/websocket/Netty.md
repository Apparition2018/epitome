# Netty
1. 本质：JBoss 提供的基于 NIO 开发的 Java 开源框架
2. 目的：快速开发高性能、高可靠性的网络服务器和客户端程序
3. 优点：提供事件驱动的、异步的非阻塞的网络应用程序框架和工具
---
## 参考网站
1. [Netty.docs: Netty.docs: Home](https://netty.io/wiki/)
2. [Netty入门之WebSocket初体验-慕课网](https://www.imooc.com/learn/941)
3. [解锁网络编程之NIO的前世今生-慕课网](https://www.imooc.com/learn/1118)
4. [通俗地讲，Netty 能做什么？](https://www.zhihu.com/question/24322387)
---
## 使用场景
1. 高性能：游戏，大数据分布式计算
2. 多线程并发
    - Reactor 模型：
        1. 多路复用器
        2. 事件分发器
        3. 事件处理器
3. 异步通信：Future-Listener 机制
---
## [IO](https://mp.weixin.qq.com/s/5rhH4caRqHdHyGV6_jeP3g)
|BIO|伪异步 IO|NIO|AIO|
|:---|:---|:---|:---|
|同步阻塞|异步阻塞?|同步非阻塞|异步非阻塞|
|1请求-1应答|M请求-N答应|M请求-1应答|M请求-0应答|
1. NIO：同步非阻塞
    - 缓冲区 Buffer：NIO 所有数据都通过 Buffer 处理，包括写入或读出的数据
    - 通道 Channel：双向的，可用于读、写或同时读写
    - 多路复用器 Selector：可监听多个非阻塞 Channel
2. AIO：异步非阻塞
    - 连接注册读写事件和回调函数
    - 读写方法异步
    - 主动通知程序：和 NIO 区别的关键
---
## [Reactor 线程模型](https://www.zhihu.com/question/26943938)
- Reactor 是一种为处理并发服务请求，并将请求提交到一个或者多个服务处理程序的事件驱动设计模式  
<img alt="Reactor" src="http://5b0988e595225.cdn.sohucs.com/images/20181102/10d4cec7f05c4a319084fd60e8644e50.jpeg" width="550"/>
---
## WebSocket
- HTML5 开始提供的一种在单个 TCP 连接上进行全双工通讯的协议
    - 全双工：允许二台设备间同时进行双向资料传输
- 客户端向服务端发送一个 HTTP 请求，握手后创建一个用于通信的 TCP 连接
<img alt="WebSocket Connection Lifecycle" src="http://www.ruanyifeng.com/blogimg/asset/2017/bg2017051502.png" width="500"/>
1. 服务端
2. 客户端
>#### 参考网站
>1. [WebSocket 教程 - 阮一峰](http://www.ruanyifeng.com/blog/2017/05/websocket.html)
>2. [WebSocket 是什么原理？为什么可以实现持久连接？ - 知乎](https://www.zhihu.com/question/20215561)
---