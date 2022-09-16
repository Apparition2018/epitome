# SSM
Spring + SpringMVC + Mybatis  

---
## 系统分层
- 上一层调用接口调用下一层的服务，这样做的好处是，当下一层的实现发生改变，不影响上一层
1. 表示层：UI层，数据展现和操作界面，另外还要负责请求分发
2. 业务层：服务层，封装业务处理逻辑
3. 持久层：数据访问层，封装数据访问逻辑
---
## MVC
<img alt="mvc" src="../notes/img/mvc.png" width="550px"><br/>
- 软件开发过程中的设计思想，是表示层的一种架构模式
- 结构
    1. Model：封装应用程序的数据结构和业务逻辑
    2. View：Model 的外在表现，提供界面 (JSP)
    3. Controller：协调 Model 和 View (Servlet)
- 优点
    1. 方便测试，业务逻辑写在java可直接测试
    2. 方便维护，修改视图、模型，互不影响
    3. 方便分工协作
---
## Spring
### Spring MVC
- 用来简化基于 MVC 架构的 WEB 应用程序开发的框架，是 Spring 框架的一部分

<img alt="SpringMVC 运行流程" src="https://upload-images.jianshu.io/upload_images/4322526-873dc442cdc93555.png" width="880px"><br/>
#### 基于XML配置 MVC
1. 搭建环境：
    1. 创建 web 工程，导包 spring-webmvc
    2. 添加 xml 配置文件
    3. 在 web.xml 配置 DispatcherServlet 前端控制器
    ```
    <servlet>
        <servlet-name>spring</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/spring-web.xml</param-value>
        </init-param>
    </servlet>
    ```
2. HandlerMapping：通过此组件，Dispatcher 可将客户 HTTP 请求映射到 Controller 上
    1. SimpleUrlHandlerMapping
    ```
    <bean class="org.springframe.web.servlet.handler.SimpleUrlHandlerMapping">
        <property name="mappings">
            <props>
    　           <prop key="/hello.do">hc</prop>
            </props>
    　   </property>
    </bean>
    ```    
    2. BeanNameUrlHandlerMapping
    3. RequestMappingHandlerMapping
3. Controller：负责执行具体的业务处理，实现 Controller 接口及约定方法 handleRequest(req, resp)
    ```
    <bean id="hc" class="controller.HelloController"/>
    ```
4. ModelAndView：handlerRequest() 返回一个 ModelAndView 对象，该对象可封装模型数据和视图名相应信息
    - ModelAndView(String ViewName); 或 ModelAndView(String viewName, Map model);
5. ViewResolver：
    1. UrlBasedViewResolver
    2. InternalResourceViewResolver
    ```
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
    ```           
    3. XmlViewResolver
    4. FreeMarkerViewResolver
#### 基于注解配置的 MVC
1. 添加注解驱动：`<mvc:annotation-driven />`
    1. 自动注册：HandlerMapping，HandlerAdapter，HandlerExceptionResolver
    2. 其它扩展功能
2. 添加包扫描：`<context:component-scan base-package="spring"/>`
3. @Controller：用于组件扫描，Controller 不用实现 Controller 接口了
4. @RequestMapping：相当于 HandlerMapping
5. ViewResolver：和上面的基于 XML 配置相同
### Spring 获取请求参数
1. 通过 HttpServletRequest 对象：
    - request.getParameter(x), request.getParameterMap();
2. 通过 @RequestParam：参数类型自动转换，但可能出现类型转换异常
    - public String bmi(@RequestParam("height") String h, String weight);
3. 通过 JavaBean：封装请求参数，属性名与请求参数名要一致，类型要一致
### Spring 向页面传值
1. 通过 HttpServletRequest 对象：
    - request.setAttribute(String name, Object obj);
2. 通过 HttpSession 对象：
    - session.setAttribute(String name, Object obj);
3. 通过 ModelAndView 对象：
```
Map<String, Object> data = new HashMap<String, Object>();
data.put("status", status);
return ModelAndView(String viewName, Map data);
                    or
ModelAndView modelAndView = new ModelAndView();
modelAndView.addObject("status", status);
modelAndView.setViewName("view");
return modelAndView;
```
4. 通过 ModelMap 对象：
```
public String bmi(BmiParam bp, ModelMap modelMap) {
    ...
    modelMap.put("status", status);
    return viewName;
}
```
5. 通过 [@ModelAttribute](https://www.4spaces.org/spring-mvc-and-the-modelattribute-annotation/)
### Spring 重定向
- SpringMVC 默认采用转发方式定位视图，，如果要重定向可采用以下方法
    1. ModelAndView：RedirectView view = new RedirectView("hello.do"); return new ModelAndView(view);
    2. String：return "redirect:hello.do";
### Spring 字符编码过滤器
- 只在容器初始化时调用一次，依赖于 Servlet 容器
```xml
<web-app>
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    <filter>
        <filter-name>FirstFilter</filter-name>
        <filter-class>springboot.filter.FirstFilter</filter-class>
    </filter>
</web-app>
```
### Spring 拦截器       
- DispatchServlet → interceptor.preHandle() → Controller → interceptor.postHandle() → interceptor.afterCompletion()
- Interceptor 属于 Spring 框架，Filter 属于 Servlet 规范
- 步骤：
    1. 创建拦截器类 实现 HandlerInterceptor 或 继承 HandlerInterceptorAdapter
    2. 再拦截器方法中，实现拦截处理逻辑
    3. 配置拦截器
    ```
    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <mvc:exclude-mapping path="/login.do"/> <!-- 排除地址 -->
            <bean class="xxx.MyInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>
    ```
### Spring 异常处理
1. XML 配置：
    - DefaultHandlerExceptionResolver
    - ExceptionHandlerExceptionResolver
    - ResponseStatusExceptionResolver
    - SimpleMappingExceptionResolver
    ```
    <bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
        <property name="exceptionMappings">
            <props>
                <prop key="java.lang.NumberFormatException">erorViewName</prop>
            </pops>
        </propety>	
    </bean>
    ```
2. 自定义：创建异常处理类 实现 HandlerExceptionResolver 接口
3. [注解配置](https://www.cnblogs.com/xd502djj/p/9873172.html)：
    - @ControllerAdvice：类注解，声明一些全局性的东西
    - @ExceptionHandler：方法注解，统一处理方法抛出的异常
    ```
    @ControllerAdvice
    public class GlobalExceptionHandler {
    	@ExceptionHandler(RuntimeException.class)
    	@ResponseBody
    	public JsonResult handleRuntimeException(Exception e) {
    		return new JsonResult(e);
    	}
    }
    ```
### Spring JDBC
- 导包：spring-jdbc，commons-dbcp2（数据库连接池）， mysql-connector-java（数据库）
- XML 配置：
```xml
<beans>       
    <context:property-placeholder location="classpath:jdbc.properties"/>
    <bean id="dbcpDataSource" class="org.apache.commons.dbcp2.BasicDataSource" destroy-method="close">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
        ...
    </bean>
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dbcpDataSource"/>
    </bean>
</beans>
```
- 写一个 Dao 注入 JdbcTemplate，调用 JdbcTemplate 提供的方法来访问数据库
---
## [Mybatis](https://mybatis.org/mybatis-3/zh/index.html)
<img alt="Mybatis 工作原理" src="https://img-blog.csdn.net/20180624002302854" width="650px"><br/>
### SqlSession
- SqlSession 提供了在数据库执行 SQL 命令所需的所有方法
1. 获取 SqlSession
```
1. Reader is = Resources.getResourceAsReader("mybatis.xml");
            Or
   InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis.xml");
2. SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
3. try (SqlSession session = sqlSessionFactory.openSession()) {}
```
2. SqlSession 常用 API：
```
int             insert(String var1, Object var2);
<E> List<E>     selectList(String var1);
<T> T           selectOne(String var1, Object var2);
int             update(String var1, Object var2);
int             delete(String var1, Object var2);
void            commit();
void            close();
```
### Mapper 映射器
- 一个符合 XML 映射文件要求的借口
- 要求
    1. 方法名要与 XML 映射文件当中的 SQL 语句的 id 一致
    2. 参数类型要与 XML 映射文件当中的 parameterType 一致
    3. 返回类型要与 XML 映射文件当中的 resultType 一致
- 获取：MyBatis 在底层会依据接口的要求生成符合要求的对象
    - `XxxMapper mapper = sqlSession.getMapper(XxxDao.class);   `
### XML 映射文件
- 包含了 SQL 代码和映射定义信息
- 顶级元素
    - cache – 该命名空间的缓存配置
    - cache-ref – 引用其它命名空间的缓存配置
    - sql – 可被其它语句引用的可重用语句块
    - insert, update delete, select
    - resultMap – 描述如何从数据库结果集中加载对象，是最复杂也是最强大的元素
    ```xml
    <mapper>
        <resultMap type="entity.Emp" id="empResultMap">
            <result property="empNo" column="id"/>
            <result property="ename" column="name"/>
        </resultMap>
        <select id="findById" parameterType="int" resultMap="empResultMap">
        	SELECT * FROM t_emp WHERE id = #{id}
        </select>
    </mapper>
    ```
- 动态 SQL：if，choose(when, otherwise)，trim(where, set)，foreach
- ["#" 与 "$" 的区别](https://mybatis.org/mybatis-3/zh/sqlmap-xml.html#Parameters)
    - \#：使用 #{} 参数语法时，MyBatis 会创建 PreparedStatement 参数占位符，并通过占位符安全地设置参数（就像使用 ? 一样，可防止 SQL 注入）。 这样做更安全，更迅速，通常也是首选做法
    - $：在 SQL 语句中直接插入一个不转义的字符串，一般用于传入数据库对象
        - 使用场景一：ORDER BY 子句，`ORDER BY ${columnName}`
        - 使用场景二：select 一个表任意一列的数据
        ```
        @Select("select * from user where ${column} = #{value}")
        User findByColumn(@Param("column") String column, @Param("value") String value);
        ```
### Mybatis 标签
```
<isEqual>
<isNotEmpty>
<isNotNull>
```
### Spring 整合 Mybatis
1. 导包：spring-webmvc，spring-jdbc，mybatis-spring，commons-dbcp2， mysql-connector-java
2. XML 配置 SqlSessionFactoryBean
```xml
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="druidDataSource"/>
    <property name="mapperLocations" value="classpath:mapper/*/*.xml"/>
    <property name="typeAliasesPackage" value="spring.bean"/>
    <!-- MyBatis 全局文件：mybatis-config.xml，后续属性会覆盖配置文件对应的属性 -->
    <property name="configLocation" value="classpath:mybatis-config.xml"/>
</bean>
```
3. 实体类，XML 映射文件，Mapper 映射器
4. XML 配置 MapperScannerConfigurer：扫描指定包及其子包下面的 Mapper 映射器，然后调用 SqlSession 的 getMapper() 返回符合 Mapper 映射器要求的对象，并且将这些对象添加到 Spring 容器里
```xml
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
    <property name="basePackage" value="spring.dao"/>
</bean>
```  
### [mybatis-config.xml](https://mybatis.org/mybatis-3/zh/configuration.html)
```xml
<configuration>
    <settings>
        <!-- 是否允许在嵌套语句中使用分页(RowBounds)，如果允许使用则设置为 false -->
        <setting name="safeRowBoundsEnabled" value="true"/>
        <!-- 全局性地开启或关闭所有映射器配置文件中已配置的任何缓存 -->
        <setting name="cacheEnabled" value="false"/>
        <!-- 允许 JDBC 支持自动生成主键，需要数据库驱动支持 -->
        <setting name="useGeneratedKeys" value="true"/>
        <!-- 使用列标签代替列名 -->
        <setting name="useColumnLabel" value="true"/>
        <!-- 是否开启驼峰命名自动映射 -->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
</configuration>
```
---
### 基于注解的 Spring 声明式事务管理
- Spring 声明式事务管理是通过 Spring AOP 实现的。其本质是对方法前后进行拦截，然后在目标方法开始之前创建或者加入一个事务，在执行目标方法之后根据执行情况提交或回滚事务。
    1. 只有 public 方法起作用
    2. 只有来自外部的方法调用才会被 AOP 代理捕获，类内部方法调用本类内部的其它方法不会引起事务行为
- 步骤：
    1. XML 配置  
    ```xml
    <beans>
        <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
            <!-- 注入数据库连接池 -->
            <property name="dataSource" ref="druidDataSource"/>
        </bean>
        <tx:annotation-driven transaction-manager="transactionManager"/>
    </beans>
    ```
    2. 在类或方法前添加 @Transactional 注解
- 注解属性：
    - value：别名 transactionManager，当配置了多个 TransactionManager，可以用该属性指定选择哪个事务管理器
    - propagation：事务传播机制
    - isolation：事务隔离级别
    - readOnly：设置当前事务是否为只读事务
    - timeout：设置事务的超时秒数，默认值为-1，表示永不超时
    - rollbackFo：设置需要进行事务回滚的异常类数组
    - noRollbackFor：设置不需要进行事务回滚的异常类数组
---
