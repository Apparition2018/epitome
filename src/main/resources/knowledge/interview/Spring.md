# Spring
- 非侵入式的轻量级框架
---
## Reference
1. [Spring 4 | 尚硅谷](https://www.bilibili.com/video/BV1KW411u7An)
2. [你对 Spring 的理解？](https://www.zhihu.com/question/48427693)
3. [15个 Spring 面试常见问题](https://mp.weixin.qq.com/s/uVoeXRLNEMK8c00u3tm9KA)
4. [为什么使用 IOC 和 AOP](https://www.cnblogs.com/RudeCrab/p/14365296.html)
5. [IOC 和 AOP 详解](https://zhuanlan.zhihu.com/p/144241957)
---
## [Features](https://spring.io/projects/spring-framework#features)
1. 核心技术：DI（依赖注入）、events（事件）, resources（资源）, i18n, validation, data binding（数据绑定）, type conversion（类型转换）, SpEL, AOP
2. 测试：mock objects, TestContext framework, Spring MVC Test, `WebTestClient`
3. 数据访问：transactions, DAO support, JDBC, ORM, Marshalling XML
4. web 框架：Spring MVC, Spring WebFlux
5. 集成：remoting（远程调用）, JMS, JCA, JMX, email, tasks, scheduling, cache
6. 语言：Kotlin, Groovy, dynamic languages
---
## [Why Spring ?](https://spring.io/why-spring)

---
## Bean 生命周期
- @see ClassPathXmlApplicationContextDemo#testLifecycle
1. [BeanDefinition](https://my.oschina.net/u/4600853/blog/4556323) ：通过 BeanDefinitionReader 从 XML/注解/JavaConfig/Groovy DSL 读取 Bean 的配置信息，生成 BeanDefinition
2. BeanFactoryPostProcessor：对 BeanFactory 相关信息的修改或扩展
3. BeanFactory
    1. 实现了各种 XxxAware: 普通属性填充之后调用
    ```
    BeanNameAware's setBeanName
    BeanClassLoaderAware's setBeanClassLoader
    BeanFactoryAware's setBeanFactory
    EnvironmentAware's setEnvironment
    EmbeddedValueResolverAware's setEmbeddedValueResolver
    ResourceLoaderAware's setResourceLoader
    ApplicationEventPublisherAware's setApplicationEventPublisher
    MessageSourceAware's setMessageSource
    ApplicationContextAware's setApplicationContext
    ServletContextAware's setServletContext
    ```
    2. BeanPostProcessor's postProcessBeforeInitialization：对 Bean 相关信息的修改或扩展
    3. InitializingBean's afterPropertiesSet
    4. RootBeanDefinition's getInitMethodName
    5. BeanPostProcessor's postProcessAfterInitialization：对 Bean 相关信息的修改或扩展
    6. DestructionAwareBeanPostProcessor's postProcessBeforeDestruction
    7. DisposableBean's destroy
    8. RootBeanDefinition's getDestroyMethodName
>- [Spring Bean 的生命周期](https://www.cnblogs.com/zrtqsk/p/3735273.html)
---
## 实例化 Bean
1. 无参构造方法：`<bean id="gregorianCalendar" class="java.util.GregorianCalendar"/>`
    - class 属性为 Bean 的全类名，通过反射创建 Bean，所以要求 Bean 必须有无参构造函数
2. 工厂方法
    1. 静态工厂方法：`<bean id="calendar" class="java.util.Calendar" factory-method="getInstance"/>`
    2. 实例工厂方法
    ```
    <bean id="gregorianCalendar" class="java.util.GregorianCalendar"/>
    <bean id="time" factory-bean="gregorianCalendar" factory-method="getTime"/>
    ```
3. [FactoryBean](https://blog.csdn.net/m0_45406092/article/details/114805287)
---
## IOC & DI
1. Inversion of Control 控制反转，是一种设计思想
    - 控制：管理对象（生命周期，依赖关系）
    - 反转：对象的控制权交给 IOC 容器
    - 演变：接口及其实现 → 工厂设计模式 → 控制反转
2. Dependency Injection 依赖注入，是 IOC 的一种实现方式
- 好处：解耦
>- [阐述你对 IOC 的理解](https://www.zhihu.com/question/313785621)
### [IOC 容器](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-beanfactory)
1. BeanFactory：读取 bean 配置文档，负责 bean 的加载，实例化，维护 bean 之间的依赖关系，负责 bean 的声明周期
2. ApplicationContext：BeanFactory 的子孙接口，提供了比 BeanFactory 更完善的功能
    1. 继承 MessageSource 接口，因此提供国际化支持
    2. 资源访问
    3. 事件机制
    4. 载入多个配置文件
    5. 以声明式的方式启动，并创建 Spring 容器
3. ClassPathXmlApplicationContext：ApplicationContext 的子孙类，基于类路径加载配置文件
    - `ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");`
4. FileSystemXmlApplicationContext：ApplicationContext 的子孙类，基于文件系统加载配置文件
### [依赖注入方式](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-collaborators)
1. 构造器注入：`<constructor-arg/>`
    - c 命名空间注入：快捷的构造方法注入
    - [推荐使用构造器注入](https://reflectoring.io/constructor-injection/) ：创建不可变对象，防止 NPE，线程安全，使代码更健壮
2. setter 注入：属性注入，`<property/>`
    - p 命名空间注入：快捷的 setter 注入
### [依赖配置细节](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-properties-detailed)
1. 引用其它 Bean：在 `<constructor-arg/>`/`<property/>` 使用 ref 属性 或 `<ref/>` 子元素
2. 内部 Bean：在 `<constructor-arg/>`/`<property/>` 使用 `<bean/>` 子元素 
3. 集合：`<list/>`, `<set/>`, `<map/>`, `<props/>`
4. null：`<property name="age">`, `<null/>`, `</property>`
5. 复合属性名
```xml
<bean id="something" class="this.ThingOne">
    <property name="fred.bob.sammy" value="123"/>
</bean>
```
### [自动装配](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-autowire)
1. no：默认值，禁用自动装配
2. byName：属性名
3. byType：属性类型
4. constructor：构造器
### [Bean 作用域](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes)
1. Singleton：单例，Spring 容器创建时就会初始化 Bean
2. prototype：原型，Spring 容器创建时不会初始化 Bean，而在每次使用时创建一个新的实例
3. Web-aware
    1. request：HTTP 请求
    2. session：HTTP 会话
    3. application：ServletContext 生命周期
    4. websocket：WebSocket 生命周期
4. [自定义作用域](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes-custom)
### [容器扩展点](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-extension)
1. 使用 BeanPostProcessor 自定义 Beans
2. 使用 BeanFactoryPostProcessor 自定义配置元数据：`<context:property-placeholder location/>`
    - 可使用 `${property-name}` 占位符获取属性值
3. 使用 FactoryBean 自定义实例化逻辑
### [依赖注入注解](https://www.zhihu.com/question/39356740/answer/1907479772)
| 注解   | @Autowired                           | @Resource                        |
|:-----|:-------------------------------------|:---------------------------------|
| 定义   | Spring                               | JSR-250                          |
| 装配方式 | 默认按类型装配；<br/>配合使用 @Qualifier，可以按名称装配 | 指定 name 按名称装配；<br/>指定 type 按类型匹配 |
| 使用范围 | 成员变量、构造器、方法、参数、注解                    | 类、成员变量、方法                        |
- 装配找到多个类型相同的类：
    1. 使用按名称装配
    2. 在某个相同类型上添加 @Primary 注解
- 多个类型相同的类可以装配到集合中，如 List、Set、Map
### 循环依赖
| 场景                                        | 是否报错           | 解决方法                                                   |
|:------------------------------------------|:---------------|:-------------------------------------------------------|
| 单例 setter 注入                              | 否 (Spring 解决了) | 三级缓存                                                   |
| 多例 setter 注入                              | 被单例引用时报错       ||
| 构造器注入                                     | 是              ||
| DependsOn                                 | 是              ||
| 单例代理对象 setter 注入 (先)<br/>单例 setter 注入 (后) | 是              | 1. @Lazy<br/>2. @DependsOn<br/>3. 修改文件名，从而调转循环依赖类的加载顺序 |
| 构造器注入 (先)<br/>单例 setter 注入 (后)            | 是              | 同上                                                     |
- 三级缓存

| 缓存                    | 级别   |                                |                   | Map               |
|:----------------------|:-----|:-------------------------------|:------------------|:------------------|
| singletonObjects      | 一级缓存 | Bean 成品 (初始化未完成)               | 对外暴露的对象           | ConcurrentHashMap |
| earlySingletonObjects | 二级缓存 | Bean 半成品或半成品的代理 (实例化完成，初始化未完成) | 处理循环依赖的对象创建问题     | HashMap           |
| singletonFactories    | 三级缓存 | Bean 工厂 (实例化 Bean)             | 存在 AOP 时能提前创建代理对象 | HashMap           |
- 注：非抽象、单例、非懒加载的类才会被提前初始化
>- [Spring 解决循环 | Java基基](https://mp.weixin.qq.com/s/Ra6Peuq7ZDSNe7OruAWZtw)
>- [Spring 解决循环](https://www.zhihu.com/question/438247718/answer/1730527725)
>- [@Transactional、@Async 和循环依赖](https://www.cnblogs.com/liuzhihang/p/spring-trans-async.html)
---
## AOP (Aspect-Oriented Programming)
- 解决的问题：共同非业务代码的抽取
- 优点：减少重复代码，提高系统可拓展性
- 使用场景：日志，事务，缓存，权限(安全)
- 实现原理：动态代理
    1. JDK：基于反射；目标对象实现了接口，则 spring 默认使用
    2. Cglib：基于 ASM 类库在运行时对字节码进行修改和生成
- [核心概念](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-introduction-defn) ：
  
| 概念         | 中文  | 说明                                          |
|:-----------|:----|:--------------------------------------------|
| Aspect     | 切面  | Pointcut + Advice；Spring AOP 中 @Aspect 注释的类 |
| Join Point | 连接点 | 使用 Advice 的地方；Spring AOP 中连接点只能是方法          |
| Advice     | 通知  | 切面的具体行动                                     |
| Pointcut   | 切入点 | 匹配 Join Point                               |
- 对比 AspectJ：
    - Spring AOP 是运行时织入，AspectJ 是编译时织入
    - Spring AOP 使用了 AspectJ 的注解 和 切入点表达式语言
>- [Spring AOP 和 AspectJ 的区别？](https://segmentfault.com/a/1190000022019122)
---
## 事务
1. 事务隔离级别：@see SQL.md#事务隔离级别
2. [事务传播机制](https://segmentfault.com/a/1190000013341344)

| 事务传播          | 外层无事务 | 外层有事务 | 外层有事务备注 |
|:--------------|:------|:------|:--------|
| REQUIRED      | 新建事务  | 加入事务  | 同一事务    |
| REQUIRES_NEW  | 新建事务  | 新建事务  | 不同事务    |
| NESTED        | 新建事务  | 嵌套事务  | 内不影响外   |
| SUPPORTS      | 无事务   | 加入事务  | 同一事务    |
| NOT_SUPPORTED | 无事务   | 无事务   ||
| NEVER         | 无事务   | 抛出异常  ||
| MANDATORY     | 抛出异常  | 加入事务  | 同一事务    |
3. @Transactional 失效场景：
    1. 未被 Spring 管理
    2. 数据库不支持
    3. 非 public 方法，final 修饰
    4. 自调用
    5. RuntimeException 和 Error 才生效
    6. 多线程，多数据源，分布式
    7. 主动：propagation 设置未非事务，try catch 处理了异常
---
## [SpringMVC](https://mp.weixin.qq.com/s/yGP_34nilJp3QKyM3RaO2w)
- MVC：
    - Model：封装应用程序的数据结构和业务逻辑 (service, dao, entity)
    - View：Model 的外在表现，提供界面
    - Controller：协调 Model 和 View
- @see SSM.md#Spring MVC
---
## [SpEL](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions)
- 一种强大的表达式语言，支持在运行时查询和操作对象图
- 支持功能
    - 访问属性、arrays、lists、maps：`<property name="pwd" value="#{staff.pwd}"/>`
    - 方法调用：`<property name="pwd" value="#{staff.getPwd()}"/>`
    - 各种运算符：`<property name="pwd" value="#{staff.pwd ?: 123456}"/>`
    - 正则表达式：`<property name="mobile" value="#{1[3456789]\d{9}}"/>`
    - Types：`<property name="PI" value="#{T(java.lang.Math).PI}"/>`
    - bean 引用：`<property name="mobile" value="#{mobile}"/>`
    - ...
---
## Spring 用到的设计模式
| 模式    | 用例                                                                              |
|:------|:--------------------------------------------------------------------------------|
| 工厂方法  | FactoryBean, AbstractFactoryBean                                                |
| 抽象工厂  | BeanFactory                                                                     |
| 建造者   | BeanDefinitionBuilder, MockMvcWebClientBuilder                                  |
| 单例    | AbstractBeanFactory                                                             |
| 原型    | AbstractBeanFactory                                                             |
| 适配器   | Spring AOP (AdvisorAdapter)<br/>Spring MVC (HandlerAdapter)                     |
| 代理    | Spring AOP (JdkDynamicAopProxy, CglibAopProxy)                                  |
| 装饰器   | XxxDecorator，XxxWrapper                                                         |
| 组合    | WebMvcConfigurerComposite                                                       |
| 外观    | JdbcUtils                                                                       |
| 模板方法  | JdbcTemplate，RedisTemplate，RabbitTemplate, TransactionTemplate                  |
| 命令    | JdbcTemplate#execute(StatementCallback)                                         |
| 策略    | 资源访问 (Resource)                                                                 |
| 状态    | 状态机 (statemachine)                                                              |
| 观察者   | Spring Event (ApplicationEventPublisher, ApplicationListener, ApplicationEvent) |
| 中介者   | Spring MVC (DispatcherServlet)                                                  |
| 责任链模式 | Spring MVC (HandlerExecutionChain)                                              |
| 访问者   | BeanDefinitionVisitor                                                           |
| 备忘录   | Spring Web Flow (StateManageableMessageContext)                                 |
| 解释器   | SpEL (ExpressionParser)                                                         |
## 其它
1. 单例 Bean 线程安全问题：多个线程对同一对象的非静态成员变量进行写操作时存在线程安全问题
    - 解决：ThreadLocal
2. @Component 和 @Bean 的区别：
    - @Component 作用于类
    - @Bean 作用于方法；第三方库的类只能用 @Bean 装配到 Spring IOC 容器
3. userService 一定是 UserServiceImpl 的实例吗?
    - 不一定：如果 UserServiceImpl 加了 @Transactional，则是代理对象
---
