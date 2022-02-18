# Interview

---
## Redis
- 缓存穿透、击穿、雪崩
    1. 穿透：访问一个缓存和数据库都不存在的数据
        - 接口校验：用户鉴权、参数校验
        - 缓存空值：过期时间设短一点
        - 布隆过滤器
    2. 击穿：某个缓存在过期的瞬间有大量请求直接到达数据库
        - 加互斥锁
        - 热点数据不过期
    3. 雪崩：大量缓存同一时间过期使得瞬时有大量请求直接到达数据库
        - 打散过期时间
        - 加互斥锁
        - 热点数据不过期
    - [参考网站1](https://mp.weixin.qq.com/s/knz-j-m8bTg5GnKc7oeZLg)
    - [参考网站2](https://zhuanlan.zhihu.com/p/359118610)
    - [参考网站3](https://zhuanlan.zhihu.com/p/359659736)
---
## [Zookeeper](https://www.cnblogs.com/ultranms/p/9602474.html)
- @see Zookeeper.md
1.