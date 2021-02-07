# Interview

---
## 参考网站
1. [码农翻身](https://blog.csdn.net/coderising/article/details/100021588)
2. [芋道源码](https://www.zhihu.com/people/yunaiv)

---
## 日志
1. 日志门面
    1. slf4j: Simple Logging Facade for Java
    2. jboss-logging
    3. commons-logging
2. 日志实现
    1. log4j: Log for Java
    2. JUL: java.util.logging
    3. log4j2
    4. logback
- 应用中不应直接使用日志系统 (Log4j, Logback) 中的 API，而应依赖使用日志框架 SLF4J 中的 API。因为使用过门面模式的日志框架，有利于维护各个类的日志处理方式统一。
```
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

Logger logger = LoggerFactory.getLogger(Test.class);
logger.info("Hello World!")
```
>### 参考网站
>1. [一个著名的日志系统是怎么设计出来的？](~~https://mp.weixin.qq.com/s?__biz=MzAxOTc0NzExNg==&mid=2665513967&idx=1&sn=5586ce841a7e8b39adc2569f0eb5bb45~~)
>2. [如何设计一个良好的日志格式](https://www.bilibili.com/video/BV1Kk4y1U7ep/)
>3. [Java日志体系居然这么复杂？——架构篇 - 知乎](https://zhuanlan.zhihu.com/p/101104008)
>4. [五年Java经验，面试还是说不出日志该怎么写更好？——日志规范与最佳实践篇 - 知乎](https://zhuanlan.zhihu.com/p/101597639)
---
## 缓存
>### 参考网站
>1. [后端系统的缓存使用浅谈 - 知乎](https://zhuanlan.zhihu.com/p/32434005)
>2. [服务端分布式缓存与本地缓存](https://blog.csdn.net/haoxinqing9698/article/details/102465975)
>3. [如何优雅的设计和使用缓存？](https://juejin.cn/post/6844903665845665805)
>4. [Spring Cache VS Caffeine](https://www.cnblogs.com/Sinte-Beuve/p/12009885.html)
>5. [Spring Cache抽象详解 - 《亿级流量网站架构核心技术》](https://www.iteye.com/blog/jinnianshilongnian-2001040)
>6. [本地缓存解决方案-Caffeine Cache - 知乎](https://zhuanlan.zhihu.com/p/158424114)
>7. [为什么要用Redis而不直接用Map做缓存](https://www.cnblogs.com/treasury/p/13022344.html)
>8. [Caffeine和Redis居然可以这么搭，想不到吧，爱了爱了](https://mp.weixin.qq.com/s?__biz=Mzg5MjQ5MzY2Mg==&mid=2247484393&idx=1&sn=19f40ed53f7c57805594887ccd88676e)
>9. [spring cache 实现两级缓存（redis + caffeine）](https://my.oschina.net/dengfuwei/blog/1616221?p=1)
---