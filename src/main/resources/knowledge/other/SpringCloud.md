# SpringCloud

---
## 核心组件
| 要解决的问题  | 组件                           | 说明                             |
|---------|------------------------------|--------------------------------|
| 服务注册与发现 | Nacos / Eureka               | 服务注册中心，Provider 注册、Consumer 发现 |
| 远程调用    | OpenFeign                    | 声明式 HTTP 调用，写接口即调用             |
| 负载均衡    | LoadBalancer（替代 Ribbon）      | 客户端负载均衡                        |
| 服务熔断/降级 | Sentinel / Resilience4j      | 服务雪崩保护                         |
| 网关      | Spring Cloud Gateway         | 统一入口、路由、鉴权、限流                  |
| 配置中心    | Nacos Config / Config Server | 集中管理配置，动态刷新                    |
| 分布式事务   | Seata                        | 跨服务事务一致性                       |
| 链路追踪    | SkyWalking / Sleuth + Zipkin | 分布式调用链追踪                       |
### Nacos vs Eureka
| 对比维度   | Nacos                               | Eureka                     |
|:-------|:------------------------------------|:---------------------------|
| CAP 模型 | AP / CP（临时实例 AP，持久化实例 CP）           | AP（高可用）                    |
| 一致性协议  | CP 模式使用 Raft 协议选举；AP 模式使用 Distro 协议 | Peer-to-Peer 异步复制，无强一致性协议  |
| 实例类型   | 临时实例 + 持久化实例                        | 仅临时实例                      |
| 健康检查机制 | 临时实例：客户端心跳；持久化实例：服务端主动探测（TCP/HTTP）  | 客户端心跳（默认 30s），超时剔除         |
| 自我保护机制 | 有（但阈值可配，且会主动标记不健康，更智能）              | 有（节点心跳比例低时触发，保留疑似宕机节点）     |
| 配置中心   | 内置（Nacos 既是注册中心也是配置中心）              | 无（需配合 Spring Cloud Config） |
| 当前维护状态 | 持续活跃迭代                              | 2.x 版本闭源停维                 |

### Spring Cloud Gateway
1. GlobalFilter：全局过滤器，对所有路由生效
2. GatewayFilter：路由过滤器，只对配置了该过滤器的路由生效（如 AddRequestHeader）
- 注：先鉴权，后限流
    - 鉴权的成本极低（通常只是解密 JWT 或查一次 Redis）
    - 消耗合法配额（黑客用无效 Token 发起多次攻击，白白消耗限流限额）
### 熔断器的断路器模型的三种状态
1. Closed：正常状态，请求全部放行，同时统计失败率。当失败率达到阈值（如 50%），断路器切换到 Open
2. Open：熔断状态，所有请求直接拒绝（快速失败），不调用下游服务，并启动一个休眠时钟
3. Half-Open：休眠期结束后进入半开状态，允许少量请求通过测试
    1. 如果这些请求成功，说明下游恢复，切换回 Closed
    2. 如果仍然失败，说明下游未恢复，切换回 Open 重新计时
---
## VS Dubbo
| 特性   | SpringCloud          | Dubbo                             |
|:-----|:---------------------|:----------------------------------|
| 通信协议 | HTTP (REST)          | RPC (自定义二进制协议)                    |
| 性能   | 相对较低 (HTTP 开销大)      | 高 (二进制序列化 + Netty)                |
| 调用方式 | OpenFeign (声明式 HTTP) | 接口直连 (像调用本地方法)                    |
| 服务治理 | 需组合多个组件              | 内置 (负载均衡、容错、路由)                   |
| 生态   | Spring 全家桶，社区庞大      | Apache 顶级项目，Java 专注               |
| 跨语言  | 支持 (HTTP 通用)         | Java 为主 (Dubbo3 已支持 Triple 协议跨语言) |
---
## 自动配置步骤
1. 读清单：启动时通过 `ImportSelector` 读取 jar 包下 META-INF/spring.factories 中预定义的配置类清单
    - `@SpringBootApplication` → `@EnableAutoConfiguration` → `@Import(AutoConfigurationImportSelector.class)`
2. 条件过滤：利用 `@Conditional` 系列注解逐一判断当前环境是否满足条件，满足条件的配置类生效
3. 注册 Bean：自动向 IOC 容器注册对应的 Bean
---
