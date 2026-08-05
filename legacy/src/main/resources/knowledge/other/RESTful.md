# RESTful (Resource **Re**presentational **S**tate **T**ransfer)

---
## Reference
1. [RESTful API 设计指南](https://www.ruanyifeng.com/blog/2014/05/restful_api.html)
2. [RESTful API 最佳实践](https://www.ruanyifeng.com/blog/2018/10/restful-api-best-practices.html)
3. [RESTful API 登录设计](https://www.v2ex.com/t/118049)
## 课程
1. [SpringMVC数据绑定入门-慕课网](https://www.imooc.com/learn/558) `第8章 知识点小扩展RESTful`
2. [GitHub REST API](https://docs.github.com/en/rest)
## 问题
1. [WEB开发中，使用JSON-RPC好，还是RESTful API好？](https://www.zhihu.com/question/28570307/answer/541465581)
2. [为什么很多后端写接口都不按照restful规范？](https://www.zhihu.com/question/438825740/answer/1692268189)
---
## 字面分解
1. Resource (资源）：文本，图片，音频，服务 ...
2. Representational (表现层)
    - HTTP 协议的 content-type 和 accept
    ```
    文本          txt、html、xml、json、二进制
    图片          jpg、png
    ```
3. State Transfer (状态转化)
    - 幂等性：每次 HTTP 请求相同的参数，相同的 URI，产生的结果是相同的
    ```
    GET             http://wwww.book.com/book/001       获取资源
    POST            http://wwww.book.com/book           创建资源-不具有幂等性
    PUT             http://wwww.book.com/book/001       创建/更新资源
    DELETE          http://wwww.book.com/book/001
    ``` 
---
## [成熟度模型](https://www.cnblogs.com/fengyc/p/12035660.html)
![REST Maturity](https://sinnema313.files.wordpress.com/2014/05/rest-maturity21.png)
1. 使用 HTTP 传输方式
2. 面向资源
3. HTTP METHOD
4. HATEOAS
---
## 示例
    movie.douban.com/show/subject/11620560/             避免动词
    movie.douban.com/subject/11620560/
    
    POST /accounts/1/transfer/500/to/2                  避免动词
    POST /transaction
        from=1&to=2&amount=500.00
    
    www.example.com/app/1.0/                            版本
    www.example.com/app/
        HEADER verson=1.0    
---
## 总结
    1. 每一个 URI 代表一种资源
    2. 客户端和服务器之间，传递这种资源的某种表现层
    3. 客户端通过 HTTP 动词，对服务器端资源进行操作，实现"表现层状态转化"
---
