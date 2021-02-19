# 编程

---
## 收藏
1. [程序员你为什么这么累？(编程规范)](https://zhuanlan.zhihu.com/p/28705206)
2. [反向代理为何叫反向代理？](https://www.zhihu.com/question/24723688)
3. [session与token的区别](https://www.cnblogs.com/shijianchuzhenzhi/p/12317439.html)
4. [Kubernetes和Docker到底有啥关系？](https://zhuanlan.zhihu.com/p/87186261)
---
## [环境变量](http://www.xitongcheng.com/jiaocheng/dnrj_article_44449.html)
1. WIN+R 输入 sysdm.cpl → 高级 → 环境变量
2. 在系统变量新建 MAVEN_HOME：D:\tts9\apache-maven-3.3.9
3. 在系统变量找到 Path，增加 %MAVEN_HOME%\bin;
4. 在 cmd 输入 mvn -v
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
1. [谁能用通俗的语言解释一下什么是 RPC 框架？](https://www.zhihu.com/question/25536695)
2. [RPC是什么，与WebService有什么异同？](https://zhuanlan.zhihu.com/p/97640202)
3. [HTTP，TCP， socket，RPC 与gRPC都是啥？](https://www.jianshu.com/p/959030de7f1c)
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
>### GraphQL
>1. [GraphQL | 一种为你的 API 而生的查询语言](https://graphql.cn)
>2. [使用 GraphQL 的 6 个月](https://zhuanlan.zhihu.com/p/139226118)
>3. [GraphQL 为何没有火起来?](https://www.zhihu.com/question/38596306/answer/79714979)
>### Google API Design Guide
>1. [Google API Design Guide](https://www.bookstack.cn/read/API-design-guide/API-design-guide-README.md)
---
## [端口被占用](https://jingyan.baidu.com/article/3c48dd34491d47e10be358b8.html)
1. 打开 cmd.exe
2. netstat -ano
3. netstat -ano|findstr 8080，根据指定端口字符串查找，记下 PID
4. tasklist|findstr 9776，根据指定 PID 字符串查找任务，记下任务名
5. taskkill /f /t /im WXWork.exe，或 taskkill /f /t /pid 333412
---
## JWT
1. [五分钟带你了解啥是JWT](https://zhuanlan.zhihu.com/p/86937325)
2. [JWT 超详细分析](https://learnku.com/articles/17883)
3. [jwt与token+redis，哪种方案更好用？](https://www.zhihu.com/question/274566992)
---
## Token 登录
1. [RESTful登录设计（基于Spring及Redis的Token鉴权）](www.scienjus.com/restful-token-authorization/)
---
## Redis
1. [Releases/redis](https://github.com/microsoftarchive/redis/releases) 下载最新发布版本
2. 点击 Redis-x64-x.y.z.zip 下载
3. 启动：
    - 双击 Redis 目录下 redis-server.exe
    - 启动 cmd cd 到 Redis 目录，输入命令 redis-server.exe redis.window.conf
4. [Redis Desktop Manager](https://www.jianshu.com/p/ccc3ebe29f7b)
>#### 参考网站
>1. [windows下安装redis并设置自启动](https://www.cnblogs.com/yunqing/p/10605934.html)
>2. [Redis 密码设置和登录](https://www.cnblogs.com/xiaozong/p/5652563.html)
>3. [Redis 教程](https://www.cnblogs.com/yiwangzhibujian/category/1020818.html)
---
## 微信小程序
>### 登录设计
>1. 
---