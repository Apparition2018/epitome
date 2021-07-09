# 编程

---
## 收藏
1. [程序员你为什么这么累？(编程规范)](https://zhuanlan.zhihu.com/p/28705206)
2. [反向代理为何叫反向代理？](https://www.zhihu.com/question/24723688)
3. [Kubernetes和Docker到底有啥关系？](https://zhuanlan.zhihu.com/p/87186261)
4. [Java 内存分配全面浅析](https://www.cnblogs.com/lixiaolun/p/4311775.html)
---
## 开源镜像站
1. [阿里巴巴开源镜像站](https://developer.aliyun.com/mirror/)
2. [腾讯软件源](https://mirrors.cloud.tencent.com)
3. [网易开源镜像站](https://mirrors.163.com/)
---
## Tomcat
>### Tomcat 启动中文乱码
>1. 打开 D:\tts9\apache-tomcat-9.0.40\conf\logging.properties
>2. 修改 java.util.logging.ConsoleHandler.encoding = GBK
>### [Tomcat 热部署](https://blog.csdn.net/w15321271041/article/details/80597962)
>1. On 'Update' action: Update classes and resources
>2. Debug 启动项目 (修改 Java 文件时可以立刻生效)
---
## RPC
0. [EasyRPC](https://github.com/yeecode/EasyRPC)
1. [谁能用通俗的语言解释一下什么是 RPC 框架？](https://www.zhihu.com/question/25536695)
2. [RPC是什么，与WebService有什么异同？](https://zhuanlan.zhihu.com/p/97640202)
3. [WebService是什么？他究竟和WebSocket有什么关系？](https://cloud.tencent.com/developer/article/1405501)
4. [HTTP，TCP， socket，RPC 与gRPC都是啥？](https://www.jianshu.com/p/959030de7f1c)
5. [阿里P8架构师谈：Restful、SOAP、RPC、SOA、微服务之间的区别](https://youzhixueyuan.com/the-difference-between-restful-soap-rpc-soa-and-micro-service.html)
6. [既然有 HTTP 请求，为什么还要用 RPC 调用？](https://www.zhihu.com/question/41609070)
>### Web Services
>1. [Web Services——重整山河待后生](https://zhuanlan.zhihu.com/p/26252433)
---
## API 设计
>### RESTful API
>1. [RESTful API 设计指南](https://www.ruanyifeng.com/blog/2014/05/restful_api.html)
>2. [RESTful API 最佳实践](https://www.ruanyifeng.com/blog/2018/10/restful-api-best-practices.html)
>3. [RESTful API 登录设计](https://www.v2ex.com/t/118049)
>4. [RESTful API 设计最佳实践](https://www.oschina.net/translate/best-practices-for-a-pragmatic-restful-api)
>5. [RESTful API 的十个最佳实践](https://www.cnblogs.com/xiaoyaojian/p/4612503.html)
>6. [HiddenHttpMethodFilter进行请求过滤，实现Rest风格的url](https://www.cnblogs.com/Coder-Pig/p/7340694.html)
>7. [为什么很多后端写接口都不按照restful规范？](https://www.zhihu.com/question/438825740/answer/1692268189)
>8. [WEB开发中，使用JSON-RPC好，还是RESTful API好？](https://www.zhihu.com/question/28570307/answer/541465581)
>9. [REST API一对多,多对多调用设计](https://www.iteye.com/blog/wwwcomy-2422101)
>### GraphQL
>1. [GraphQL | 一种为你的 API 而生的查询语言](https://graphql.cn)
>2. [使用 GraphQL 的 6 个月](https://zhuanlan.zhihu.com/p/139226118)
>3. [GraphQL 为何没有火起来?](https://www.zhihu.com/question/38596306/answer/79714979)
>### Google API Design Guide
>1. [Google API Design Guide](https://www.bookstack.cn/read/API-design-guide/API-design-guide-README.md)
---
## [端口被占用](https://jingyan.baidu.com/article/3c48dd34491d47e10be358b8.html)
1. 打开 cmd.exe
2. netstat -ano|findstr 8080，根据指定端口字符串查找，记下 PID
3. tasklist|findstr 9776，根据指定 PID 字符串查找任务，记下任务名
4. taskkill /f /t /im WXWork.exe，或 taskkill /f /t /pid 333412
---
## Web
1. [什么是跨域？为什么要禁止跨域？怎样跨域？](https://blog.csdn.net/qq_28773159/article/details/104834167)
2. [浅谈CSRF攻击方式](https://www.cnblogs.com/hyddd/archive/2009/04/09/1432744.html)
---
## SSO
- 步骤：存储票据，查验票据
1. [什么是单点登录(原理与实现简介) ](https://www.cnblogs.com/wcwnina/p/9946623.html)
2. [java sso单点登录实战_哔哩哔哩](https://www.bilibili.com/video/BV1hT4y157QF?p=2&spm_id_from=pageDriver)
---
## JWT
1. [五分钟带你了解啥是JWT](https://zhuanlan.zhihu.com/p/86937325)
2. [JWT 超详细分析](https://learnku.com/articles/17883)
3. [jwt与token+redis，哪种方案更好用？](https://www.zhihu.com/question/274566992)
---
## Token
1. [RESTful登录设计（基于Spring及Redis的Token鉴权）](www.scienjus.com/restful-token-authorization/)
2. [session与token的区别](https://www.cnblogs.com/shijianchuzhenzhi/p/12317439.html)
---
## 注释
```
阿里编程规约：
    1. 对于暂时被注释掉，后续可能恢复使用的代码片断，在注释代码上方，统一规定使用三个斜杠(///)来说明注释掉代码的理由
```
---
## 前后端配合
```
阿里编程规约：
    1. 前后端交互的 API，需要明确协议、域名、路径、请求方法、请求内容、状态码、响应体
    2. 前后端数据列表相关的接口返回，如果为空，则返回空数组[]或空集合{}
    3. 服务端发生错误时，返回给前端的响应信息必须包含 HTTP 状态码，errorCode、errorMessage、用户提示信息四个部分   
    4. 对于需要使用超大整数的场景，服务端一律使用 String 字符串类型返回，禁止使用 Long 类型；Java 服务端如果直接返回 Long 整型数据给前端，JS 会自动转换为 Number 类型（注：此类型表示原理与取值范围等同于 Java 中的 Double）。
    5. HTTP 请求通过 URL 传递参数时，不能超过 2048 字节
    6. HTTP 请求通过 body 传递内容时，必须控制长度，超出最大长度后，后端解析会出错；nginx 默认限制是 1MB，tomcat 默认限制为 2MB
    7. 在翻页场景中，用户输入参数的小于 1，则前端返回第一页参数给后端；后端发现用户输入的参数大于总页数，直接返回最后一页
    8. 服务器内部重定向必须使用 forward；外部重定向地址必须使用 URL 统一代理模块生成，否则会因线上采用 HTTPS 协议而导致浏览器提示“不安全”，并且还会带来 URL 维护不一致的问题
    9. 服务器返回信息必须被标记是否可以缓存，如果缓存，客户端可能会重用之前的请求结果；http 1.1 中，s-maxage 告诉服务器进行缓存，时间单位为秒，用法如下，response.setHeader("Cache-Control", "s-maxage=" + cacheSeconds)；
    10. 在接口路径中不要加入版本号，版本控制在 HTTP 头信息中体现，有利于向前兼容
```
---