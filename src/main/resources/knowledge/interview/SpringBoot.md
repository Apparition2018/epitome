# SpringBoot

---
## ⚪[Features](https://spring.io/projects/spring-boot#overview)
- 直接嵌入 Tomcat、Jetty 或 Undertow（无需部署 WAR 文件）
- 提供预设的 'starter' 依赖项以简化构建配置
- 尽可能自动配置 Spring 及第三方库
- 提供 production-ready 功能，如指标(metrics)、健康检查、外部化配置
- 完全无需代码生成，也无需 XML 配置
---
## application.yml vs bootstrap.yml
|        | application.yml |         bootstrap.yml         |
|:------:|:---------------:|:-----------------------------:|
|  加载时机  |    应用上下文创建后     |           应用上下文创建前            |
|   用途   |      业务配置       | 拉取远程配置（如 Nacos、Config Server） |
| 是否可被覆盖 |        是        |           否（优先级最高）            |
---
