# 编程

---
## 收藏
1. [程序员你为什么这么累？(编程规范)](https://zhuanlan.zhihu.com/p/28705206)
2. [反向代理为何叫反向代理？](https://www.zhihu.com/question/24723688)
3. [Kubernetes和Docker到底有啥关系？](https://zhuanlan.zhihu.com/p/87186261)
4. [Java 内存分配全面浅析](https://www.cnblogs.com/lixiaolun/p/4311775.html)
5. [程序员网站](https://www.zhihu.com/question/494519458/answer/2191335701)
---
## 开源镜像站
1. [阿里巴巴开源镜像站](https://developer.aliyun.com/mirror/)
2. [腾讯软件源](https://mirrors.cloud.tencent.com)
3. [网易开源镜像站](https://mirrors.163.com/)
4. [华为开源镜像站](https://mirrors.huaweicloud.com/home)
5. [清华大学开源软件镜像站](https://mirrors.tuna.tsinghua.edu.cn)
6. [科技大学开源软件镜像站](https://mirrors.ustc.edu.cn/)
---
## [端口被占用](https://jingyan.baidu.com/article/3c48dd34491d47e10be358b8.html)
1. 打开 cmd.exe
2. netstat -ano|findstr 8080，根据指定端口字符串查找，记下 PID
3. tasklist|findstr 9776，根据指定 PID 字符串查找任务，记下任务名
4. taskkill /f /t /im WXWork.exe，或 taskkill /f /t /pid 333412
---
## 日志
1. [一个著名的日志系统是怎么设计出来的？](https://mp.weixin.qq.com/s/XiCky-Z8-n4vqItJVHjDIg)
2. [Java日志体系居然这么复杂？——架构篇](https://zhuanlan.zhihu.com/p/101104008)
3. [五年Java经验，面试还是说不出日志该怎么写更好？——日志规范与最佳实践篇](https://zhuanlan.zhihu.com/p/101597639)
### 日志门面
1. slf4j: Simple Logging Facade for Java
2. jboss-logging
3. commons-logging
### 日志实现
1. log4j: Log for Java
2. JUL: java.util.logging
3. log4j2
4. logback
### 阿里日志规约：
1. 应用中不可直接使用日志系统（Log4j、Logback）中的 API，而应依赖使用日志框架（SLF4J、JCL—Jakarta Commons Logging）中的 API，使用门面模式的日志框架，有利于维护和各个类的日志处理方式统一
```
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

private static final Logger logger = LoggerFactory.getLogger(Test.class);
logger.info("Hello World!")
```
2. 日志文件至少保存 15 天，因为有些异常具备以“周”为频次发生的特点。对于当天日志，以 “应用名.log”来保存，保存在/{统一目录}/{应用名}/logs/目录下，过往日志格式为：{logname}.log.{保存日期}，日期格式：yyyy-MM-dd
3. 根据国家法律，网络运行状态、网络安全事件、个人敏感信息操作等相关记录，留存的日志不少于六个月，并且进行网络多机备份
4. 应用中的扩展日志（如打点、临时监控、访问日志等）命名方式：appName_logType_logName.log。logType：日志类型，如 stats / monitor / access 等； logName：日志描述
5. 在日志输出时，字符串变量之间的拼接使用占位符的方式
    - 因为 String 字符串的拼接会使用 StringBuilder 的 append() 方式，有一定的性能损耗。使用占位符仅是替换动作，可以有效提升性能
6. 对于 trace / debug / info 级别的日志输出，必须进行日志级别的开关判断
```
if (logger.isDebugEnabled()) {
   logger.debug("Current ID is: {} and name is: {}", id, getName());
}
```
7. 避免重复打印日志，浪费磁盘空间，务必在日志配置文件中设置 additivity=false
    - `<logger name="com.taobao.dubbo.config" additivity="false">`
8. 异常信息应该包括两类信息：案发现场信息和异常堆栈信息。如果不处理，那么通过关键字 throws 往上抛出
    - `logger.error("inputParams: {} and errorMessage: {}", 各类参数或者对象 toString(), e.getMessage(), e);`
9. 日志打印时禁止直接用 JSON 工具将对象转换成 String
10. 谨慎地记录日志。生产环境禁止输出 debug 日志；有选择地输出 info 日志；如果使用 warn 来记录刚上线时的业务行为信息，一定要注意日志输出量的问题，并记得及时删除这些观察日志
11. 可以使用 warn 日志级别来记录用户输入参数错误的情况，避免用户投诉时，无所适从。如非必要，请不要在此场景打出 error 级别，避免频繁报警
---
## 领域驱动设计 (Domain Drive Design)
- [DDD](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=Mzg2MTY3OTg2Ng==&action=getalbum&album_id=2044483473867931649)
---
## 缓存
1. [如何优雅的设计和使用缓存？](https://juejin.cn/post/6844903665845665805)
2. [Spring Cache VS Caffeine](https://www.cnblogs.com/Sinte-Beuve/p/12009885.html)
3. [高性能缓存 Caffeine](https://www.cnblogs.com/oopsguy/p/7731659.html)
4. [Spring Cache 实现两级缓存 (Redis + Caffeine)](https://my.oschina.net/dengfuwei/blog/1616221)
---
## RPC
1. [既然有 HTTP 请求，为什么还要用 RPC 调用？](https://www.zhihu.com/question/41609070/answer/1030913797)
2. [谁能用通俗的语言解释一下什么是 RPC 框架？](https://www.zhihu.com/question/25536695)
3. [RPC是什么，与WebService有什么异同？](https://zhuanlan.zhihu.com/p/97640202)
4. [WebService是什么？他究竟和WebSocket有什么关系？](https://cloud.tencent.com/developer/article/1405501)
5. [HTTP，TCP， socket，RPC 与gRPC都是啥？](https://www.jianshu.com/p/959030de7f1c)
6. [阿里P8架构师谈：Restful、SOAP、RPC、SOA、微服务之间的区别](https://youzhixueyuan.com/the-difference-between-restful-soap-rpc-soa-and-micro-service.html)
### Web Services
1. [Web Services——重整山河待后生](https://zhuanlan.zhihu.com/p/26252433)
---
## API 设计
### GraphQL
1. [GraphQL | 一种为你的 API 而生的查询语言](https://graphql.cn)
2. [使用 GraphQL 的 6 个月](https://zhuanlan.zhihu.com/p/139226118)
3. [GraphQL 为何没有火起来?](https://www.zhihu.com/question/38596306/answer/79714979)
### Google API Design Guide
1. [Google API Design Guide](https://www.bookstack.cn/read/API-design-guide/API-design-guide-README.md)
---
## Web
1. [什么是跨域？为什么要禁止跨域？怎样跨域？](https://blog.csdn.net/qq_28773159/article/details/104834167)
---
## Token
1. [RESTful登录设计（基于Spring及Redis的Token鉴权）](www.scienjus.com/restful-token-authorization/)
2. [session与token的区别](https://www.cnblogs.com/shijianchuzhenzhi/p/12317439.html)
---
## [OLTP vs OLAP](https://blog.csdn.net/weixin_44087159/article/details/124477313)
|      |             OLTP              |             OLAP             |
|------|:-----------------------------:|:----------------------------:|
| 全称   | Online Transaction Processing | Online Analytical Processing |
| 操作对象 |              数据库              |             数据仓库             |
| 数据量  |             数据量较小             |             数据量大             |
| 数据模型 |             实体-关系             |            星型或雪花型            |
| 数据时效 |             当前数据              |           当前及历史数据            |
| 数据操作 |          支持 DML、DDL           |          一般不支持更新和删除          |
| 操作粒度 |              记录级              |             涉及多表             |
| 性能要求 |            高吞吐、低延迟            |           性能要求相对较低           |
| 操作目的 |            查询或改变现状            |          分析规律、预测趋势           |
| 业务类型 |           账户查询，转账等            |          统计报告，多维度分析          |
---
## 测试
### JMeter
1. [JMeter 接口性能压测](https://blog.csdn.net/qq_30654043/article/details/124550052)
2. [JMeter 多用户 token 使用](https://blog.csdn.net/lluozh2015/article/details/122552565)
- 解决 HTTP 请求响应参数中文乱码：修改 jmeter.properties 文件，`sampleresult.default.encoding=UTF-8`
---
## [阿里 Java 开发手册](https://github.com/alibaba/p3c)
### 编程规约
1. 命名风格
```
1. 抽象类命名使用 Abstract 或 Base 开头；异常类命名使用 Exception 结尾，测试类命名以它要测试的类的名称开始，以 Test 结尾
2. 避免在子父类的成员变量之间、或者不同代码块的局部变量之间采用完全相同的命名，使可理解性降低
3. 如果模块、接口、类、方法使用了设计模式，在命名时需体现出具体模式
4. 接口和实现类的命名有两套规则
    1. 对于 Service 和 DAO 类，基于 SOA 的理念，暴露出来的服务一定是接口，内部的实现类用 Impl 的后缀与接口区别
    2. 如果是形容能力的接口名称，取对应的形容词为接口名（通常是 –able 结尾的形容词）：AbstractTranslator 实现 Translatable
5. 枚举类名带上 Enum 后缀，枚举成员名称需要全大写，单词间用下划线隔开
6. 各层命名规约：
    1. Service / DAO 层方法命名规约：
        1. 获取单个对象的方法用 get 做前缀
        2. 获取多个对象的方法用 list 做前缀，复数结尾，如：listObjects
        3. 获取统计值的方法用 count 做前缀
        4. 插入的方法用 save / insert 做前缀
        5. 删除的方法用 remove / delete 做前缀
        6. 修改的方法用 update 做前缀
    2. 领域模型命名规约：
        1. 数据对象：xxxDO，xxx 即为数据表名
        2. 数据传输对象：xxxDTO，xxx 为业务领域相关的名称
        3. 展示对象：xxxVO，xxx 一般为网页名称
        4. POJO 是 DO / DTO / BO / VO 的统称，禁止命名成 xxxPOJO
```
2. 注释规约
```
1. 类、类属性、类方法的注释必须使用 Javadoc 规范，使用 /** 内容 */ 格式，不得使用 // xxx 方式
2. 所有的抽象方法（包括接口中的方法）必须要用 Javadoc 注释、除了返回值、参数异常说明外，还必须指出该方法做什么事情，实现什么功能
    - 对子类的实现要求，或者调用注意事项，请一并说明
3. 对于暂时被注释掉，后续可能恢复使用的代码片断，在注释代码上方，统一规定使用三个斜杠(///)来说明注释掉代码的理由
```
3. 前后端规约
```
1. 前后端交互的 API，需要明确协议、域名、路径、请求方法、请求内容、状态码、响应体
2. 前后端数据列表相关的接口返回，如果为空，则返回空数组[]或空集合{}
3. 服务端发生错误时，返回给前端的响应信息必须包含 HTTP 状态码，errorCode、errorMessage、用户提示信息四个部分   
4. errorMessage 是前后端错误追踪机制的体现，可以在前端输出到 type="hidden" 文字类控件中，或者用户端的日志中，帮助我们快速地定位出问题
5. 对于需要使用超大整数的场景，服务端一律使用 String 字符串类型返回，禁止使用 Long 类型
    - Java 服务端如果直接返回 Long 整型数据给前端，JS 会自动转换为 Number 类型（注：此类型表示原理与取值范围等同于 Java 中的 Double）
    - Long 类型能表示的最大值是 2^63-1，在取值范围之内，超过 2^53（9007199254740992）的数值转化为 Javascript 的 Number 时，有些数值会产生精度损失
6. HTTP 请求通过 URL 传递参数时，不能超过 2048 字节
7. HTTP 请求通过 body 传递内容时，必须控制长度，超出最大长度后，后端解析会出错
    - nginx 默认限制是 1MB，tomcat 默认限制为 2MB
8. 在翻页场景中，用户输入参数的小于 1，则前端返回第一页参数给后端；后端发现用户输入的参数大于总页数，直接返回最后一页
9. 服务器内部重定向必须使用 forward；外部重定向地址必须使用 URL 统一代理模块生成，否则会因线上采用 HTTPS 协议而导致浏览器提示“不安全”，并且还会带来 URL 维护不一致的问题
10. 服务器返回信息必须被标记是否可以缓存，如果缓存，客户端可能会重用之前的请求结果
    - http 1.1 中，s-maxage 告诉服务器进行缓存，时间单位为秒，用法如：response.setHeader("Cache-Control", "s-maxage=" + cacheSeconds)
11. 前后端的时间格式统一为"yyyy-MM-dd HH:mm:ss"，统一为 GMT
12. 在接口路径中不要加入版本号，版本控制在 HTTP 头信息中体现，有利于向前兼容
```
### 单元测试
### 安全规约
### 工程结构
1. 应用分层
- ![分层结构](https://i.postimg.cc/LXzZZjw9/image.png)
```
1. 分层结构
    1. 开放 API 层：可直接封装 Service 接口暴露成 RPC 接口；通过 Web 封装成 http 接口；网关控制层等
    2. 终端显示层：各个端的模板渲染并执行显示的层。当前主要是 velocity 渲染，JS 渲染，JSP 渲染，移动端展示等
    3. Web 层：主要是对访问控制进行转发，各类基本参数校验，或者不复用的业务简单处理等
    4. Service 层：相对具体的业务逻辑服务层
    5. Manager 层：通用业务处理层，它有如下特征
        1. 对第三方平台封装的层，预处理返回结果及转化异常信息，适配上层接口
        2. 对 Service 层通用能力的下沉，如缓存方案、中间件通用处理
        3. 与 DAO 层交互，对多个 DAO 的组合复用
    6. DAO 层：数据访问层，与底层 MySQL、Oracle、Hbase、OceanBase等进行数据交互
    7. 第三方服务：包括其它部门 RPC 服务接口，基础平台，其它公司的 HTTP 接口，如淘宝开放平台、支付宝付款服务、高德地图服务等
    8. 外部数据接口：外部（应用）数据存储服务提供的接口，多见于数据迁移场景中
2. 分层异常处理规约：
    1. DAO 层：产生的异常类型有很多，无法用细粒度的异常进行 catch，使用 catch(Exception e) 方式，并 throw new DAOException(e)，不需要打印日志
    2. Service 层：出现异常时，必须记录出错日志到磁盘，尽可能带上参数和上下文信息，相当于保护案发现场
    3. Manager 层：与 Service 同机部署，日志方式与 DAO 层处理一致，如果是单独部署，则采用与 Service 一致的处理方式
    4. Web 层：绝不应该继续往上抛异常，因为已经处于顶层，如果意识到这个异常将导致页面无法正常渲染，那么就应该直接跳转到友好错误页面，尽量加上友好的错误提示信息
    5. 开放接口层：要将异常处理成错误码和错误信息方式返回
3. 分层领域模型规约：
    - https://zhuanlan.zhihu.com/p/350964880
    1. DO（Data Object）：此对象与数据库表结构一一对应，通过 DAO 层向上传输数据源对象
    2. DTO（Data Transfer Object）：数据传输对象，Service 或 Manager 向外传输的对象
    3. BO（Business Object）：业务对象，可以由 Service 层输出的封装业务逻辑的对象
    4. Query：数据查询对象，各层接收上层的查询请求。注意超过 2 个参数的查询封装，禁止使用 Map 类来传输
    5. VO（View Object）：显示层对象，通常是 Web 向模板渲染引擎层传输的对象
```
2. 二方库依赖
```
1. 定义 GAV 遵从以下规则：
    1. groupId：com.{公司/BU}.业务线.[子业务线]，最多 4 级
        - com.taobao.jstorm 或 com.alibaba.dubbo.register
    2. artifactId：产品线名-模块名。语义不重复不遗漏，先到中央仓库去查证一下
        - dubbo-client / fastjson-api / jstorm-tool
    3. version：主版本号.次版本号.修订号
2. 二方库的新增或升级，保持除功能点之外的其它 jar 包仲裁结果不变。如果有改变，必须明确评估和验证
    - 在升级时，进行 dependency:resolve 前后信息比对，如果仲裁结果完全不一致，那么通过 dependency:tree 命 令，找出差异点，进行<exclude>排除 jar 包
    - https://www.cnblogs.com/light-train-union/p/12260467.html
3. 二方库里可以定义枚举类型，参数可以使用枚举类型，但是接口返回值不允许使用枚举类型或者包含枚举类型的 POJO 对象
    - https://www.modb.pro/db/141682
4. 二方库定制包的命名方式，在规定的版本号之后加“-英文说明[序号]”，英文说明可以是部门简称、业务名称，序号直接紧跟在英文说明之后，表示此定制包的顺序号
    - fastjson 给 SCM 定制的版本号：1.0.0-SCM1
5. 禁止在子项目的 pom 依赖中出现相同的 GroupId，相同的 ArtifactId，但是不同的 Version ???
    - 在本地调试时会使用各子项目指定的版本号，但是合并成一个 war，只能有一个版本号出现在最后的 lib 目录中
6. 所有 pom 文件中的依赖声明放在<dependencies>语句块中，所有版本仲裁放在<dependencyManagement>语句块中
7. 二方库不要有配置项，最低限度不要再增加配置项 ???
```
3. 服务器
```
1. 调用远程操作必须有超时设置
2. 客户端设置远程接口方法的具体超时时间（单位 ms），超时设置生效顺序一般为：
    2.1 客户端 Special Method
    2.2 客户端接口级别
    2.3 服务端 Special Method
    2.4 服务端接口级别
3. 高并发服务器建议调小 TCP 协议的 time_wait 超时时间
    - 在 linux 服务器上请通过变更/etc/sysctl.conf 文件去修改该缺省值（秒）：net.ipv4.tcp_fin_timeout=30
4. 调大服务器所支持的最大文件句柄数（File Descriptor，简写为 fd）
5. 给 JVM 环境参数设置-XX：+HeapDumpOnOutOfMemoryError 参数，让 JVM 碰到 OOM 场景时输出 dump 信息
6. 在线上生产环境，JVM 的 Xms 和 Xmx 设置一样大小的内存容量，避免在 GC 后调整堆大小带来的压力
7. 了解每个服务大致的平均耗时，可以通过独立配置线程池，将较慢的服务与主线程池隔离开，免得不同服务的线程同归于尽
8. 服务器内部重定向必须使用 forward；外部部重定向地址必须使用 URL Broker 生成，否则因线上采用 HTTPS 协议而导致浏览器提示“不安全”。此外，还会带来 URL 维护不一致的问题
```
### 设计规约

---
