# Interview

---
## Spring
1. [你对 Spring 的理解？](https://www.zhihu.com/question/48427693)
2. [15个 Spring 面试常见问题](https://mp.weixin.qq.com/s/OMlwHHnGcN7iZ8lerUvW7w)
3. [为什么使用 IOC 和 AOP](https://zhuanlan.zhihu.com/p/349386138)
4. [IOC 和 AOP 详解](https://zhuanlan.zhihu.com/p/144241957)
>### IOC
>- IOC：Inversion of Control 控制反转，是一种设计思想；控制-对象的管理(生命周期，依赖关系)，反转-控制权交给 IOC 容器
>- DI：Dependency Injection 依赖注入，是 IOC 的一种实现方式
>- 好处：解耦，避免重复造轮子
>>#### 参考网站
>>1. [阐述你对 IOC 的理解](https://www.zhihu.com/question/313785621)
>>2. [带你玩转 Spring IoC](https://mp.weixin.qq.com/s/WpRSitDqtgOuU9GnI1-HDw)
>### Bean 生命周期
>- @see ClassPathXmlAcDemo.java#testLifecycle
>1. [BeanDefinition](https://my.oschina.net/u/4600853/blog/4556323) ：通过 BeanDefinitionReader 从 XML/注解/JavaConfig/Groovy DSL 读取 Bean 的配置信息，生成 BeanDefinition
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
>>#### 参考网站
>>1. [SpringIOC 面试题](https://mp.weixin.qq.com/s/SH4laewpIsio66MUJFLTyg)
>>2. [Spring Bean 的生命周期](https://www.cnblogs.com/zrtqsk/p/3735273.html)
>### [循环依赖](https://www.zhihu.com/question/438247718/answer/1730527725)
>- 场景
>   1. 
>### AOP (Aspect-Oriented Programming)
>1. [Spring AOP 和 AspectJ 的区别？](https://segmentfault.com/a/1190000022019122)
>- 解决的问题：共同非业务代码的抽取
>- 优点：减少重复代码，可拓展性
>- 使用场景：日志，事务，权限，缓存
>- 原理：基于动态代理
>   1. JDK (代理对象实现了某个接口)
>   2. Cglib
>- 对比 AspectJ：Spring AOP 是运行时织入，AspectJ 是编译时织入；Spring AOP 用到了 AspectJ 的注解
>### 事务
>1. 事务隔离级别：@See SQL.md#事务隔离级别
>2. 事务传播：@See SSM.md#事务传播机制
>### Spring MVC
>- MVC：Model(service, dao, entity), View, Controller
>- @See SSM.md#Spring MVC
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
>### 其它
>1. 单例 Bean 线程安全问题：多个线程对同一对象的非静态成员变量进行写操作存时在线程安全问题
>   - 解决：ThreadLocal
>2. @Component 和 @Bean 的区别：
>   - @Component 作用于类
>   - @Bean 作用于方法；第三方库的类只能用 @Bean 装配到 Spring IOC 容器
>3. userService 一定是 UserServiceImpl 的实例吗?
>   - 不一定：如果 UserServiceImpl 加了 @Transactional，则是代理对象
>   ```
>   @Controller
>   public class UserController {
>       @Resource
>       private UserService userService; 
>   }
>   ```
```
---

1. spring 和 springboot 区别？Bean 加载
2. 学习最多的项目