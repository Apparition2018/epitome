# Interview

---
## Redis
- @see Redis.md
  https://mp.weixin.qq.com/s/vXBFscXqDcXS_VaIERplMQ
  https://mp.weixin.qq.com/s/LkIcGS9kFTXNLFlxASPYUA
### Redis 为什么快
- 官方：QPS 10w
1. 基于内存
2. 数据结构经过专门设计
3. 单线程
4. 多路 I/O 复用模型，非阻塞 IO 
>- [参考网站1](https://blog.csdn.net/weixin_39852953/article/details/111114534)
>- [参考网站2](https://mp.weixin.qq.com/s/LkIcGS9kFTXNLFlxASPYUA)
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
