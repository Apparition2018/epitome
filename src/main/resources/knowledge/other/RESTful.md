# RESTful (Resource **Re**presentational **S**tate **T**ransfer)

---
## 参考网站
1. [SpringMVC数据绑定入门](https://www.imooc.com/learn/558) `第8章 知识点小扩展RESTful`
2. [RESTful API 设计指南](https://www.ruanyifeng.com/blog/2014/05/restful_api.html)
3. [RESTful API 最佳实践](https://www.ruanyifeng.com/blog/2018/10/restful-api-best-practices.html)
4. [GitHub REST API](https://docs.github.com/en/rest)
5. [RESTful API 设计最佳实践](https://www.oschina.net/translate/best-practices-for-a-pragmatic-restful-api)
6. [RESTful API 登录设计](https://www.v2ex.com/t/118049)
7. [WEB开发中，使用JSON-RPC好，还是RESTful API好？](https://www.zhihu.com/question/28570307/answer/541465581)
8. [为什么很多后端写接口都不按照restful规范？](https://www.zhihu.com/question/438825740/answer/1692268189)
---
## Resource (资源)
    1. 文本
    2. 图片
    3. 服务
    4. 音频
    5. ......
---
## Representational (表现层)
#### HTTP 协议的 content-type 和 accept
    文本：txt、html、xml、json、二进制
    图片：jpg、png
---
## State Transfer (状态转化)
#### 幂等性：每次 HTTP 请求相同的参数，相同的 URI，产生的结果是相同的
    1. GET     获取资源                    http://wwww.book.com/book/001
    2. POST    创建资源-不具有幂等性        http://wwww.book.com/book
    3. PUT     创建(更新)资源              http://wwww.book.com/book/001
    4. DELETE                             http://wwww.book.com/book/001
---
## RESTful 示例
    movie.douban.com/show/subject/11620560/             避免动词
    movie.douban.com/subject/11620560/
    
    POST /accounts/1/transfer/500/to/2                  避免动词
    POST /transaction
        from=1&to=2&amount=500.00
    
    www.example.com/app/1.0/                            版本
    www.example.com/app/
        HEADER verson=1.0    
---
## RESTful 总结
    1. 每一个 URI 代表一种资源
    2. 客户端和服务器之间，传递这种资源的某种表现层
    3. 客户端通过 HTTP 动词，对服务器端资源进行操作，实现"表现层状态转化"
---