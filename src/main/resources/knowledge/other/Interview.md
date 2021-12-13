# Interview

---
## Spring
1. [你对Spring的理解？](https://www.zhihu.com/question/48427693)
1. [为什么使用 IOC 和 AOP](https://zhuanlan.zhihu.com/p/349386138)
2. [IOC 和 AOP 详解](https://zhuanlan.zhihu.com/p/144241957)
>### IOC
>- IOC：Inversion of Control 控制反转，是一种设计思想；控制-对象的管理(生命周期，依赖关系)，反转-控制权交给 IOC 容器
>- DI：Dependency Injection 依赖注入，是 IOC 的一种实现方式
>- 好处：解耦，避免重复造轮子
>- [循环依赖](https://www.zhihu.com/question/438247718)
>> #### 参考网站
>>1. [阐述你对 IOC 的理解](https://www.zhihu.com/question/313785621)
>>2. [带你玩转 Spring IoC](https://mp.weixin.qq.com/s/WpRSitDqtgOuU9GnI1-HDw)
>### Bean 生命周期
>1. [BeanDefinition](https://my.oschina.net/u/4600853/blog/4556323) ：通过 BeanDefinitionReader 从 XML/注解/JavaConfig/Groovy DSL 读取 Bean 的配置信息生成 
>2. BeanFactoryPostProcessor：对 BeanFactory 相关信息的修改或扩展
>3. BeanFactory
>   1. 实现了各种 XxxAware: 普通属性填充之后调用
>   ```
>   BeanNameAware's setBeanName
>   BeanClassLoaderAware's setBeanClassLoader
>   BeanFactoryAware's setBeanFactory
>   EnvironmentAware's setEnvironment
>   EmbeddedValueResolverAware's setEmbeddedValueResolver
>   ResourceLoaderAware's setResourceLoader
>   ApplicationEventPublisherAware's setApplicationEventPublisher
>   MessageSourceAware's setMessageSource
>   ApplicationContextAware's setApplicationContext
>   ServletContextAware's setServletContext
>   ```
>   2. BeanPostProcessor's postProcessBeforeInitialization：对 Bean 相关信息的修改或扩展    
>   3. InitializingBean's afterPropertiesSet
>   4. RootBeanDefinition's getInitMethodName
>   5. BeanPostProcessor's postProcessAfterInitialization：对 Bean 相关信息的修改或扩展
>   6. DestructionAwareBeanPostProcessor's postProcessBeforeDestruction
>   7. DisposableBean's destroy
>   8. RootBeanDefinition's getDestroyMethodName
>> #### 参考网站
>> 1. [SpringIOC 面试题](https://mp.weixin.qq.com/s/SH4laewpIsio66MUJFLTyg)
>### AOP (Aspect-Oriented Programming)
>1. [Spring AOP 和 AspectJ 的区别？](https://segmentfault.com/a/1190000022019122)
>- 解决的问题：共同非业务代码的抽取
>- 使用场景：日志，事务，权限，缓存
>- 对比 AspectJ：Spring AOP 是运行时织入，AspectJ 是编译时织入；Spring AOP 用到了 AspectJ 的注解
>### 事务
>1. 事务隔离级别：SQL.md
>2. 事务传播：SSM.md
>### 用到的设计模式
>1. 简单工厂：BeanFactory，ApplicationContext
>2. 工厂方法：FactoryBean
>3. 单例模式：Bean 默认单例
>4. 策略模式：实例化对象 (InstantiationStrategy)
>5. 代理模式：Spring AOP (JdkDynamicAopProxy，CglibAopProxy)
>6. 适配器：Spring AOP 的通知 (AdvisorAdapter)；Controller (HandlerAdapter)
>7. 装饰器：XxxDecorator，XxxWrapper
>8. 观察者：Spring 事件驱动 (ApplicationEvent，ApplicationListener)
>9. 模板方法：JdbcTemplate，RedisTemplate
---

1. spring 和 springboot 区别？Bean 加载
2. 学习最多的项目