# Spring

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
>### 依赖注入
>- [推荐使用构造器注入](https://reflectoring.io/constructor-injection/) ：创建不可变对象，防止 NPE，线程安全，使代码更健壮
>>### [依赖注入注解](https://www.zhihu.com/question/39356740/answer/1907479772)
>>|注解|@Autowired|@Resource|
>>|:---|:---|:---|
>>|定义|Spring|JSR-250|
>>|装配方式|默认按类型装配；<br/>配合使用 @Qualifier，可以按名称装配|指定 name 按名称装配；<br/>指定 type 按类型匹配|
>>|使用范围|成员变量、构造器、方法、参数、注解|类、成员变量、方法|
>>- 装配找到多个类型相同的类：
>>  1. 使用按名称装配
>>  2. 在某个相同类型上添加 @Primary 注解
>>- 多个类型相同的类可以装配到集合中，如 List、Set、Map
>>### 循环依赖
>>- 场景
>>
>>  |场景|是否报错|解决方法|
>>  |:---|:---|:---|
>>  |单例 setter 注入|否 (Spring 解决了)|singletonObjects<br/>earlySingletonObjects<br/>singletonFactories：AOP 扩展|
>>  |多例 setter 注入|被单例引用时报错||
>>  |构造器注入|是||
>>  |DependsOn|是||
>>  |单例代理对象 setter 注入 (先)<br/>单例 setter 注入 (后)|是|1. @Lazy<br/>2. @DependsOn<br/>3. 修改文件名，从而调转循环依赖类的加载顺序|
>>  |构造器注入 (先)<br/>单例 setter 注入 (后)|是|同上|
>>- 注：非抽象、单例、非懒加载的类才会被提前初始化
>>>#### 参考网站
>>>1. [Spring 解决循环](https://www.zhihu.com/question/438247718/answer/1730527725)
>>>2. [@Transactional、@Async 和循环依赖](https://www.cnblogs.com/liuzhihang/p/spring-trans-async.html)
>### AOP (Aspect-Oriented Programming)
>- 解决的问题：共同非业务代码的抽取
>- 优点：减少重复代码，可拓展性
>- 使用场景：日志，事务，缓存，权限(安全)
>- 原理：基于动态代理
>   1. JDK (代理对象实现了某个接口)
>   2. Cglib
>- 核心概念：
>   1. 切面：Aspect，
>   2. 连接点：Join Point，
>   3. 切入点：Pointcut，
>   4. 通知：Advice，
>- 对比 AspectJ：Spring AOP 是运行时织入，AspectJ 是编译时织入；Spring AOP 用到了 AspectJ 的注解
>>#### 参考网站
>>1. [Spring AOP 和 AspectJ 的区别？](https://segmentfault.com/a/1190000022019122)
>### 事务
>1. 事务隔离级别：@See SQL.md#事务隔离级别
>2. 事务传播机制：@See SSM.md#事务传播机制
>### [Spring MVC](https://mp.weixin.qq.com/s/yGP_34nilJp3QKyM3RaO2w)
>- MVC：
>   - Model：封装应用程序的数据结构和业务逻辑 (service, dao, entity)
>   - View：Model 的外在表现，提供界面
>   - Controller：协调 Model 和 View
>- @See SSM.md#Spring MVC
>### 用到的设计模式
>|模式|用例|
>|:---|:---|
>|简单工厂|BeanFactory, ApplicationContext|
>|工厂方法|FactoryBean|
>|单例|Bean 创建默认单例|
>|策略|实例化策略 (InstantiationStrategy)|
>|代理|Spring AOP (JdkDynamicAopProxy, CglibAopProxy)|
>|适配器|Spring AOP (AdvisorAdapter)<br/>Spring MVC (HandlerAdapter)|
>|装饰器|XxxDecorator，XxxWrapper|
>|观察者|Spring Event (ApplicationEventPublisher, ApplicationListener, ApplicationEvent)|
>|模板方法|JdbcTemplate，RedisTemplate|
>|责任链模式|Spring AOP (ExposeInvocationInterceptor) ???|
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
---


Filter → Interceptor → Aspect

1. spring 和 springboot 区别？Bean 加载
2. 学习最多的项目