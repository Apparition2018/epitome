# [MQTT](https://www.bilibili.com/video/BV1HADnYFEXn/)
- Message Queuing Telemetry Transport（消息队列遥测传输）
- [EMQX 文档](https://docs.emqx.com/zh/)
- [博客 | EMQ](https://www.emqx.com/zh/blog)
    - [MQTT 教程 2026：从入门到精通 | EMQ](https://www.emqx.com/zh/mqtt-guide)
---
## [特点](https://www.emqx.com/zh/blog/the-easiest-guide-to-getting-started-with-mqtt#%E4%B8%BA%E4%BB%80%E4%B9%88-mqtt-%E6%98%AF%E9%80%82%E7%94%A8%E4%BA%8E%E7%89%A9%E8%81%94%E7%BD%91%E7%9A%84%E6%9C%80%E4%BD%B3%E5%8D%8F%E8%AE%AE)
| 特性         | 说明                                            |
|:-----------|:----------------------------------------------|
| 轻量级        | 物联网设备资源受限，MQTT 报文小、开销低，有限能力下高效通信              |
| 可靠         | 支持多级 QoS、会话感知和持久连接，高延迟或不稳定网络中保证消息可靠传递         |
| 安全通信       | 提供 TLS/SSL 加密，用户名/密码或证书认证，保障数据传输安全和访问控制       |
| 双向通信       | 发布-订阅模式解耦设备间通信，设备可发布可订阅，易于扩展和集成               |
| 连续、有状态的会话  | 支持持久会话，断线后 Broker 保留订阅和未送达消息，重连后自动恢复，降低数据丢失风险 |
| 大规模物联网设备支持 | 轻量、低带宽，发布-订阅解耦配合多级 QoS，高效支撑海量设备部署             |
| 语言支持       | 覆盖主流编程语言，与多种平台和技术集成，实现物联网生态无缝互通               |
---
## 概念
| 组件/概念       | 说明                                           |
|:------------|:---------------------------------------------|
| MQTT 客户端    | 任何运行 MQTT 库的应用或设备（如传感器、即时通讯应用），负责发布或订阅消息     |
| MQTT Broker | 核心服务器，处理连接、订阅管理，并负责消息的路由和转发                  |
| 发布-订阅模式     | 发布者与订阅者通过 Broker 解耦，无需直接建立连接，由 Broker 负责消息分发 |
| 主题          | 通过 / 分层，支持 +（单层）和 #（多层）通配符，用于消息分类和过滤         |
| QoS 等级      | 0（最多一次）、1（至少一次）、2（仅一次），控制消息传递的可靠性            |
---
## EMQX
- [通过 Docker 运行 EMQX](https://docs.emqx.com/zh/emqx/latest/deploy/install-docker.html)
    - docker: `emqx-enterprise`
---
## MQTTX
1. [线上客户端](https://mqttx.app/web-client/)
    - docker: `emqx/mqttx-web`
2. [桌面客户端](https://mqttx.app/zh)
3. [命令行客户端](https://mqttx.app/zh/cli)
    - `mqttx sub -t 'hello' -h '192.168.119.128' -p 1883 -v`
    - `mqttx pub -t 'hello' -h '192.168.119.128' -p 1883 -q 0 -m 'from MQTTX CLI`
---
## [Control Packets（控制报文)](https://www.emqx.com/zh/blog/introduction-to-mqtt-control-packets)
- 报文类型

| 报文类别 | 报文名称        | 报文说明                                |
|:-----|:------------|:------------------------------------|
| 连接   | CONNECT     | 客户端向服务器发起连接                         |
| 连接   | CONNACK     | 响应返回连接的结果                           |
| 连接   | DISCONNECT  | 客户端想要结束通信，或遇到必须终止连接的错误时，发送此报文关闭网络连接 |
| 连接   | AUTH        | MQTT 5.0 引入，用于增强认证，提供更安全的身份验证       |
| 连接   | PINGREQ     | 连接保持和探活，客户端定期发出以表示自己仍活跃             |
| 连接   | PINGRESP    | 服务器对 PINGREQ 的响应，用于判断服务器是否活跃        |
| 发布   | PUBLISH     | 发布消息                                |
| 发布   | PUBACK      | QoS 1 消息的确认流程                       |
| 发布   | PUBREC      | QoS 2 消息的确认流程（第一步）                  |
| 发布   | PUBREL      | QoS 2 消息的确认流程（第二步）                  |
| 发布   | PUBCOMP     | QoS 2 消息的确认流程（第三步）                  |
| 订阅   | SUBSCRIBE   | 客户端向服务器发送订阅请求                       |
| 订阅   | UNSUBSCRIBE | 客户端向服务器发送取消订阅请求                     |
| 订阅   | SUBACK      | 服务器返回订阅结果                           |
| 订阅   | UNSUBACK    | 服务器返回取消订阅结果                         |

- 报文格式

| 组成部分                 | 说明                                 |
|:---------------------|:-----------------------------------|
| 固定报头 Fixed Header    | 存在于所有控制报文中，由报文类型、标识位和剩余长度三个字段组成。   |
| 可变报头 Variable Header | 存在于部分报文中，内容取决于报文类型，字段顺序必须严格遵循协议规范。 |
| 有效载荷 Payload         | 存在于部分报文中，用于实现报文的核心目的，如承载应用消息或订阅主题。 |
---
## [服务质量等级 (QoS)](https://www.emqx.com/zh/blog/introduction-to-mqtt-qos)
- QoS（Quality of Service）

| 特性     | QoS 0                | QoS 1                | QoS 2               |
|:-------|:---------------------|:---------------------|:--------------------|
| 服务质量名称 | 最多交付一次               | 至少交付一次               | 只交付一次               |
| 保证机制   | 即发即弃，无需确认            | 确认与重传机制              | 四次握手流程              |
| 消息去重   | 不需要                  | 协议层面无法避免，需在业务层自行去重   | 协议层面保证不会重复          |
| 适用场景   | 高频、非关键的传感器数据，可接受少量丢失 | 重要指令或状态更新，业务需能处理消息重复 | 金融、航空等对数据准确性要求极高的场景 |
---
## [主题 (Topics)](https://www.emqx.com/zh/blog/advanced-features-of-mqtt-topics)
- 定义：UTF-8 编码的字符串，用于消息路由
- 结构：类似文件系统的分层结构，如 `sensor/10/temperature`
- 自动创建：客户端发布或订阅时即可自动生成，也无需手动删除
- 长度：两个字节，因此最多可包含 65,535 个字符
### 建议
- 名称长度和层级越少越好
- 不建议以 / 开头或结尾
- 同一主题层级：①my_home ②my-home ③myHome
- 当使用通配符时，将唯一值的主题层（例如设备号）越靠近第一层越好
    - `device/00000001/command/#` > `device/command/00000001/#`
### Wildcards（通配符）
| 规则项  | `+`                    | `#`                                                   |
|:-----|:-----------------------|:------------------------------------------------------|
| 名称   | 单层通配符                  | 多层通配符                                                 |
| 匹配规则 | 匹配且仅匹配一个主题层级           | 匹配父级及之后的任意数量子层级                                       |
| 使用限制 | 必须独立占据整个层级；只能用于订阅      | 必须独立占据整个层级，且必须是主题的最后一个字符；只能用于订阅                       |
| 示例   | `sensor/+/temperature` | `sensor/#` 可匹配 `sensor`、`sensor/temp`、`sensor/1/temp` |
### [系统主题](https://docs.emqx.com/zh/emqx/latest/observability/mqtt-system-topics.html)
- 获取 MQTT 服务器自身运行状态、消息统计、客户端上下线事件等数据
---
## [会话 (Session)](https://www.emqx.com/zh/blog/mqtt5-new-feature-clean-start-and-session-expiry-interval)
- 服务端：
    1. 会话是否存在。
    2. 客户端的订阅列表。
    3. 已发送给客户端，但是还没有完成确认的 QoS 1 和 QoS 2 消息。
    4. 等待传输给客户端的 QoS 0 消息（可选），QoS 1 和 QoS 2 消息。
    5. 从客户端收到的，但是还没有完成确认的 QoS 2 消息。
    6. 遗嘱消息与遗嘱过期间隔。
    7. 会话过期时间。
- 客户端：
    1. 已发送给服务端，但是还没有完成确认的 QoS 1 和 QoS 2 消息。
    2. 从服务端收到的，但是还没有完成确认的 QoS 2 消息。
- Clean Start：位于 CONNECT 报文的 可变报头
    - 值为 0：如果服务端存在与客户端连接时指定的 Client ID 关联的会话，使用这个会话来恢复通信；否者创建一个全新的会话
    - 值为 1：开始一个全新会话
- Session Expiry Interval：位于 CONNECT 报文的 可变报头，指定会话在网络断开后能够在服务端保留的最长时间

    | 值          | 说明             |
    |:-----------|:---------------|
    | 未指定 或 0    | 网络连接断开时立即结束    |
    | 大于 0       | 网络连接断开的多少秒之后过期 |
    | 0xFFFFFFFF | 永不过期           |
---
## [保留消息 (Retained Messages)](https://www.emqx.com/zh/blog/mqtt5-features-retain-message)
- 机制：MQTT 服务器为每个主题存储最新一条保留消息，以便消息发布后才上线的客户端在订阅主题后立即接收到该消息
- 标识：发布者发布消息时，如果 Retained 标记被设置为 true
- 注意：在保留消息发布前订阅主题，将不会收到保留消息（即使收到，该消息也不是保留消息）。需要重新订阅该主题，才会收到保留消息
- 存储方式：①内存（重启后丢失） ②磁盘（重启后仍存在）
    - 配置途径：
        1. EMQX Dashboard：管理 → MQTT 配置 → 保留消息 → 存储方式
        2. 配置文件
        3. HTTP API
- 删除方式：
    1. 客户端往某个主题发送一个 Payload 为空的保留消息，服务端就会删除这个主题下的保留消息
    2. 在 MQTT 服务器上删除：EMQX Dashboard → 监控 → 保留消息 → 删除
    3. MQTT 5.0 消息过期间隔属性
- vs 会话：存储在服务端中，但不属于会话的一部分
---
## [遗嘱消息 (Will Messages)](https://www.emqx.com/zh/blog/use-of-mqtt-will-message)
- 机制：客户端在连接时在服务端中注册一个遗嘱消息，当该客户端意外断开连接，服务端就会向其他订阅了相应主题的客户端发送此遗嘱消息
- 标识：客户端连接服务器时，如果 Will Flag 标记被设置为 1
- 触发条件：
    1. 服务端检测到了一个 I/O 错误或者网络故障
    2. 客户端在 Keep Alive 时间内未能通讯
    3. 客户端在没有发送 Reason Code 为 0x00（正常关闭）的 DISCONNECT 报文的情况下关闭了网络连接
    4. 服务端在没有收到 Reason Code 为 0x00（正常关闭）的 DISCONNECT 报文的情况下关闭了网络连接
- Will Delay Interval：服务端将在网络连接关闭后延迟多久发布遗嘱消息
- vs 会话：是服务端会话状态的一部分，会话结束也会发布遗嘱消息
- 使用技巧：状态监控
    1. 客户端连接时设置 offline 保留遗嘱消息
    2. 连接成功后，发布 online 保留消息
---
## [延迟发布](https://docs.emqx.com/zh/emqx/latest/messaging/mqtt-delayed-publish.html)
- `$delayed/{DelayInterval}/{TopicName}`
    - `$delayed/15/x/y`：15 秒后将消息发布到主题 `x/y`
- 设置：EMQX Dashboard → 监控 → 延迟发布 → 设置
---
## [用户属性 (User Properties)](https://www.emqx.com/zh/mqtt-guide)
- 一种自定义属性，允许用户向 MQTT 消息添加自己的元数据（UTF-8 键值对）
---
## [订阅选项 (Subscription Options)](https://www.emqx.com/zh/blog/an-introduction-to-subscription-options-in-mqtt)
- 订阅 = 主题过滤器 + 订阅选项
- 订阅选项
    1. Qos
    2. No Local：服务端是否能将消息转发给发布这个消息的客户端（0-允许转发；1-禁止转发）
    3. Retain As Published：服务端在向此订阅转发应用消息时是否保持消息中的 Retain 标识不变（0-清除标识；1-保持标识）
    4. Retain Handling：向服务端指示当订阅建立时，是否需要发送保留消息（0-总是发送；1-仅全新订阅时发送；2-从不发送）
        - 比如 Clean Start 就是全新订阅的一种
---
## [共享订阅 (Shared Subscriptions)](https://www.emqx.com/zh/blog/introduction-to-mqtt5-protocol-shared-subscription)
- MQTT 服务端可以在使用特定订阅的客户端之间均衡地分配消息负载
- 两种方案：
    1. 带订阅组：`$share/{Share Name}/{Topic Filter}`
    2. 不带订阅组：`$queue/{Topic File}`
- 负载均衡策略：EMQX Dashboard → 管理 → MQTT 配置 → 通用 → 共享订阅策略

   | 策略名称 | 策略名称        | 说明                                                |
   |:-----|:------------|:--------------------------------------------------|
   | 随机   | Random      | 在共享订阅组内随机选择一个会话发送消息                               |
   | 轮询   | Round Robin | 在共享订阅组内按顺序选择一个会话发送消息，循环往复                         |
   | 哈希   | Hash        | 基于某个字段的哈希结果来分配                                    |
   | 粘性   | Sticky      | 在共享订阅组内随机选择一个会话发送消息，此后保持这一选择，直到该会话结束再重复这一过程       |
   | 本地优先 | Local       | 随机选择，但优先选择与消息的发布者处于同一节点的会话，如果不存在这样的会话，则退化为普通的随机策略 |
---
## [排它订阅](https://docs.emqx.com/zh/emqx/latest/messaging/mqtt-exclusive-subscription.html)
- 一个主题同一时刻仅被允许存在一个订阅者，在当前订阅者未取消订阅前，其他订阅者都将无法订阅对应主题
- `$exclusive/t/1`
- 配置：
    - EMQX Dashboard：管理 → MQTT 配置 → 通用 → 允许排它订阅
    - 配置文件：`mqtt.exclusive_subscription.enable = true`
---
## [自动订阅](https://docs.emqx.com/zh/emqx/latest/messaging/mqtt-auto-subscription.html)
- 给 EMQX 设置多个规则，在设备成功连接后按照规则为其订阅指定主题，不需要额外发起订阅
- EMQX Dashboard → 管理 → MQTT 配置 → 自动订阅
---
## [访问控制](https://docs.emqx.com/zh/emqx/latest/dashboard/acloverview.html)
- EMQX Dashboard → 访问控制
    1. 客户端认证
        - 认证链：①按配置先后依次尝试 ②任一认证通过则认证通过 ③所有认证失败则认证失败
    2. 客户端授权
        - 授权链：①按配置先后依次尝试 ②任一检查匹配则按权限操作 ③所有检查不匹配则按 no_match 配置操作
    3. 黑名单
        - 可使用的匹配规则：①正则 ②无类别域间路由 (CIDR)：192.168.1.0/16
    4. 连接抖动：自动封禁被检测到短时间频繁登录的客户端（只封禁客户端 ID）
        - 配置文件：`flapping_detect.enable`
---
## [数据集成](https://docs.emqx.com/zh/emqx/latest/data-integration/data-bridges.html)
- EMQX Dashboard → 集成
- 使用 Sink 与 Source 组件与外部数据系统连接

    | 组件     | 功能           | 外部数据系统示例              |
    |:-------|:-------------|:----------------------|
    | Sink   | 将消息发送到外部数据系统 | MySQL、Kafka、HTTP 服务   |
    | Source | 从外部数据系统接收消息  | MQTT、Kafka、GCP PubSub |
### 连接器 (Connector)
- 数据集成的关键概念，它作为 Sink/Source 的底层连接通道，用于连接到外部数据系统
- ![EMQX 连接器概念](https://docs.emqx.com/assets/connector-sink.DCYKv6UB.jpg)
### [规则引擎](https://docs.emqx.com/zh/emqx/latest/data-integration/rules.html)
- 基于 SQL 的数据处理组件，搭配数据集成无需编写代码即可实现一站式的 IoT 数据提取、过滤、转换、存储与处理
- 组成：
    1. 数据来源：消息、事件、外部的数据系统 (Source)；FROM 子句指定数据的来源
    2. 数据处理过程：WHERE 子句用于过滤数据，SELECT 子句以及 SQL 函数用于提取和转换数据
    3. 处理结果去向：
        1. 消息重发布：将结果发布到指定 MQTT 主题
        2. 控制台输出：将结果输出到控制台或日志中
        3. 发送到各类 Sink：将结果发送到外部数据系统中
---
