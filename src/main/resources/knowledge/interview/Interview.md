# Interview

---
## Redis
- @see Redis.md
>- [参考网站1](https://github.com/Snailclimb/JavaGuide/blob/main/docs/database/redis/redis-questions-01.md)
>- [参考网站2](https://mp.weixin.qq.com/s/LkIcGS9kFTXNLFlxASPYUA)
>- [参考网站3]()
  https://mp.weixin.qq.com/s/vXBFscXqDcXS_VaIERplMQ
  https://mp.weixin.qq.com/s/LkIcGS9kFTXNLFlxASPYUA
### Redis 多快，为什么快
- Redis 的瓶颈通常是内存或网络，而不是 CPU；查询 QPS 达 10w/s
1. 基于内存：省去 CPU 将数据从磁盘读到内存的时间
2. 专门设计的数据结构：
3. 单线程：
    - 省去创建、切换、销毁线程上下文的时间
    - 4.0
4. 多路 IO 复用模型 
>- [参考网站1](https://cloud.tencent.com/developer/article/1600940)
>- [参考网站2](https://blog.csdn.net/weixin_39852953/article/details/111114534)
>- [参考网站3]()
### 缓存雪崩、击穿、穿透
1. 雪崩：
    1. 大量缓存同一时间过期
        - 打散过期时间：随机数
        - 加互斥锁：分布式锁、JVM 锁均可 (Key 维度枷锁?)
        - 热点数据不过期
    2. Redis 故障
        - 服务熔断
        - 请求限流
        - 高可用
2. 击穿：某个缓存在过期的瞬间有大量请求 (热点数据)
    - 加互斥锁
    - 热点数据不过期
3. 穿透：访问一个缓存和数据库都不存在的数据
    - 接口校验：用户鉴权、参数校验
    - 缓存空值或默认值：设置较短过期时间
    - 布隆过滤器
>- [参考网站1](https://zhuanlan.zhihu.com/p/359118610)
>- [参考网站2](https://mp.weixin.qq.com/s/_StOUX9Nu-Bo8UpX7ThZmg)
>- [参考网站3](https://mp.weixin.qq.com/s/knz-j-m8bTg5GnKc7oeZLg)
---
## [Zookeeper](https://www.cnblogs.com/ultranms/p/9602474.html)
- @see Zookeeper.md
