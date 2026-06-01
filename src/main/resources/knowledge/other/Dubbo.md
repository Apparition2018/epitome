# Dubbo

---
## 参考网站
1. [Apache Dubbo](https://cn.dubbo.apache.org/zh-cn/docs/)
2. [Dubbo 教程](https://www.bilibili.com/video/BV1bX4y1G7SF)
---
## [概念与架构](https://cn.dubbo.apache.org/zh-cn/docs/concepts/)
![architecture](https://cn.dubbo.apache.org/imgs/architecture.png)
---
## [高级用法](https://cn.dubbo.apache.org/zh-cn/docs/advanced/)
### [集群容错](https://cn.dubbo.apache.org/zh-cn/docs/advanced/fault-tolerent-strategy/)
| 策略               | 行为                  | 适用场景        |
|:-----------------|:--------------------|:------------|
| Failover（失败重试）   | 失败自动切换其他节点重试，默认 2 次 | 读操作（默认）     |
| Failfast（快速失败）   | 失败立即报错，不重试          | 写操作（防重复）    |
| Failsafe（失败安全）   | 失败忽略，返回空            | 日志/审计等非关键操作 |
| Failback（失败自动恢复） | 失败记录，后台定时重试         | 消息推送等异步场景   |
| Forking（并行调用）    | 同时调多个节点，一个成功即返回     | 实时性要求极高的读操作 |
---
