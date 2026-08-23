# FreeMarker 模板引擎

## 概述

[FreeMarker](https://freemarker.apache.org/) 是一个基于模板生成文本输出的 **Java 模板引擎**，是 Spring Boot 官方推荐的模板方案之一（与 Thymeleaf、Mustache 并列），常用于服务端 HTML 渲染。核心思路：**模板（`.ftlh`）+ 数据模型（Model）→ 输出 HTML**，实现视图与业务逻辑分离。

本模块 `spring-boot-freemarker` 演示 Spring Boot + FreeMarker 的完整集成：配置、核心语法、共享变量、异常处理与测试。

---

## 1. 依赖

`spring-boot-starter-freemarker` 由根 pom 的 `org.ljh:commons` 通过 Maven 传递依赖提供，**本模块无需重复声明**：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
```

## 2. 核心概念

| 概念              | 说明                                                             |
|-----------------|----------------------------------------------------------------|
| **模板**（`.ftlh`） | 含 FreeMarker 指令与 `${...}` 占位符的文本文件，存放于 `classpath:/templates/` |
| **数据模型**（Model） | Controller 通过 `model.addAttribute(...)` 注入模板的变量                |
| **输出**          | 模板 + 数据模型合并后的 HTML 结果                                          |

> `.ftlh` 后缀表示 **HTML 安全模式**（对 `<` `>` `&` 自动转义），比传统 `.ftl` 更安全，是 Spring Boot 推荐的 HTML 模板后缀。

## 3. 配置（application.yml）

FreeMarker 配置见 [`application.yml`](src/main/resources/application.yml)，完整配置及注释以此文件为准。

## 4. 模板语法

### 4.1 输出表达式

```ftl
${message}                          <#-- 普通输出 -->
${message + " · " + appName}        <#-- 字符串拼接 -->
```

### 4.2 内置函数（Built-ins）

```ftl
${message?upper_case}               <#-- 转大写 -->
${message?length}                   <#-- 长度 -->
${users?size}                       <#-- 集合大小 -->
${users?map(u -> u.name)?join("、")} <#-- map + join -->
${users?map(u -> u.name)?seq_contains('李四')?then('是','否')} <#-- 链式判断 -->
```

### 4.3 指令（Directives）

```ftl
<#-- 遍历 + 序号 -->
<#list users as u>
    ${u?index + 1} ${u.name}
</#list>

<#-- 空列表兜底 -->
<#list emptyList as e>
    ${e}
<#else>
    （列表为空）
</#list>

<#-- 多分支：<#elseif> + 比较运算符（文本形式 lt/gt，避免裸写 < >） -->
<#if emp.age lt 18>
    ${emp.name} 未成年
<#elseif emp.age gt 60>
    ${emp.name} 已退休
<#else>
    ${emp.name} 在职
</#if>
```

> **比较运算符**推荐使用文本形式：`lt`（`<`）、`lte`（`<=`）、`gt`（`>`）、`gte`（`>=`）。
> 在 XML/HTML 模板中裸写 `<` `>` 可能与标签解析冲突（`<` 会被当作指令开始），文本形式更安全；`>` 也可写成 `&gt;`。`<#elseif>` 可连续使用，实现多分支判断。

### 4.4 null 安全

```ftl
${user.name!}            <#-- 值为 null 时输出空 -->
${unknown!'默认值'}       <#-- null/缺失时输出默认值 -->
${unknown?default('xx')} <#-- 等价于 ! -->
<#if user??>...</#if>     <#-- 判空 -->
```

### 4.5 格式化

```ftl
${user.balance?string("0.00")}   <#-- 数字格式化为两位小数 -->
${user.createdAt?date}           <#-- 日期按 date_format 格式化 -->
```

> ⚠️ **`?date` 要求 `java.util.Date`**：FreeMarker 默认 `ObjectWrapper` 不识别 `LocalDate`，会把它当普通对象导致解析报错。本模块在 Controller 传入模型前把 `LocalDate` 转为 `java.util.Date`（见 `DemoController.toDate()`）。

### 4.6 条件输出 `?then`

```ftl
${(count > 100)?then('大于100', '不大于100')}
```

> ⚠️ **优先级陷阱**：`?then` 优先级**高于**比较运算符，必须写 `(count > 100)?then(...)`，否则被解析为 `count > (100?then(...))`，左侧变成数字而报 `NonBooleanException`。

### 4.7 HTML 转义（防 XSS）

```ftl
<#-- 模板级转义：区域内所有 ${} 自动转义 -->
<#escape x as x?html>
    ${keyword}              <#-- <script> → &lt;script&gt; -->
</#escape>
```

- 使用 `.ftlh` 后缀本身就是 HTML 安全模式。
- **不要**用 `?no_esc` 输出原始用户输入，否则绕过转义导致 XSS 漏洞。

### 4.8 宏与函数

```ftl
<#-- 定义宏（macros.ftlh） -->
<#macro nav active="">
    <a href="/" class="${(active == 'index')?then('active', '')}">首页</a>
</#macro>

<#-- 定义函数 -->
<#function roleLabel roles>
    <#if roles?seq_contains('admin')><#return '管理员'>
    <#elseif roles?seq_contains('user')><#return '普通用户'>
    <#else><#return '访客'></#if>
</#function>

<#-- 导入并调用 -->
<#import "/macros.ftlh" as m>
<@m.nav active="index"/>
${m.roleLabel(user.roles)}
```

### 4.9 注释

```ftl
<#-- 这是 FreeMarker 注释，不会输出到结果 -->
```

## 5. 模板文件说明

| 模板                                                        | 演示知识点                                                              |
|-----------------------------------------------------------|--------------------------------------------------------------------|
| [`index.ftlh`](src/main/resources/templates/index.ftlh)   | 表达式、内置函数（`?upper_case`/`?length`）、`?then`、共享变量                     |
| [`users.ftlh`](src/main/resources/templates/users.ftlh)   | `<#list>` 遍历 + 序号、空列表兜底、`?size`/`?map`/`?join`/`?seq_contains`、格式化 |
| [`detail.ftlh`](src/main/resources/templates/detail.ftlh) | `<#if>` 判空、`!` 默认值、`?default`                                      |
| [`form.ftlh`](src/main/resources/templates/form.ftlh)     | `<#escape>` HTML 转义防 XSS、`?has_content`                            |
| [`error.ftlh`](src/main/resources/templates/error.ftlh)   | 状态码/错误信息渲染，`.now?datetime`                                         |
| [`macros.ftlh`](src/main/resources/templates/macros.ftlh) | `<#macro>` 导航、`<#function>` 角色标签                                   |

## 6. 共享变量

在 [`FreeMarkerConfig.java`](src/main/java/org/ljh/freemarker/config/FreeMarkerConfig.java) 中通过 `FreeMarkerConfigurer#setFreemarkerVariables` 注入所有模板可用的静态变量：

```java
@Bean
public FreeMarkerConfigurer freeMarkerConfigurer() {
    FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
    configurer.setTemplateLoaderPath("classpath:/templates/");
    configurer.setDefaultEncoding("UTF-8");
    configurer.setFreemarkerVariables(Map.of("appName", "Spring Boot + FreeMarker"));
    return configurer;
}
```

> ⚠️ 自定义 `FreeMarkerConfigurer` 会**取代** Spring Boot 自动配置，因此 `application.yml` 里的 `spring.freemarker.settings`（如 `date_format`）需手动应用，否则用 FreeMarker 默认值。

## 7. 控制器与路由

| 路由                | 方法           | 渲染模板                | 说明             |
|-------------------|--------------|---------------------|----------------|
| `GET /`           | `index`      | `index.ftlh`        | 首页             |
| `GET /users`      | `users`      | `users.ftlh`        | 用户列表           |
| `GET /users/{id}` | `userDetail` | `detail.ftlh`       | 用户详情，未找到为 null |
| `GET /form`       | `form`       | `form.ftlh`         | 表单回显（转义演示）     |
| `GET /error-demo` | `errorDemo`  | `error.ftlh`（经全局处理） | 抛异常演示          |

> **数据模型约定**：`DemoController` 把 `User`（record）通过 `toMap()`/`toMapList()` **转换为 `Map`** 再放入 Model，
> 并把 `LocalDate createdAt` 转换为原生 `java.util.Date`。这样模板可用 `${u.id}`（Map 取值）与 `${u.createdAt?date}`，
> 避免依赖 FreeMarker 对 record / `LocalDate` 的扩展 ObjectWrapper 配置（后者在当前 Spring 7 / Boot 4 环境下会引入编译兼容问题）。

## 8. 异常处理

[`GlobalExceptionHandler.java`](src/main/java/org/ljh/freemarker/controller/GlobalExceptionHandler.java) 用 `@ControllerAdvice` 捕获异常并渲染到 `error.ftlh`：

```java
@ExceptionHandler(NoHandlerFoundException.class)  // 404
@ExceptionHandler(Exception.class)                 // 500
```

## 9. 测试

[`FreemarkerTests.java`](src/test/java/org/ljh/freemarker/FreemarkerTests.java) 直接注入 `FreeMarkerConfigurer`，调用 `Template.process()` 渲染并断言输出：

| 测试           | 验证点                  |
|--------------|----------------------|
| `index`      | 共享变量与表达式渲染           |
| `users`      | 遍历输出、角色标签、数字格式化      |
| `escapeHtml` | HTML 特殊字符被转义、原始脚本不出现 |

## 10. 常见问题

1. **`?then` 报 `NonBooleanException`**：比较表达式要加括号 `(count > 100)?then(...)`。
2. **`${u.id}` 报 "evaluated to a method+sequence"**：数据模型是 Java record 时，FreeMarker 默认 `ObjectWrapper` 会把访问器 `id()` 当方法而非属性。**解决**：在 Controller 把 record 转为 `Map` 再传入模板。
3. **`?date` 解析失败（`Unparseable date`）**：数据模型里的日期必须是 `java.util.Date`；`LocalDate` 默认不被 FreeMarker 识别，需传入前转换为 `Date`。
4. **不要用 `freemarker.ext.java8.Java8TimeModelFactory` / `DefaultObjectWrapper` 等扩展配置**：在 Spring 7 / Boot 4 下，`FreeMarkerConfigurer` 已移除 `setObjectWrapper`/`setDateModelFactory`，且 `Java8TimeModelFactory` 可能解析不到，会引发编译错误。改用「record → Map」方案最稳妥。
5. **`date_format` 不生效**：自定义了 `FreeMarkerConfigurer` 时需手动应用 `application.yml` 的 `settings`。
6. **XSS 风险**：使用 `.ftlh` + `<#escape>`，禁用 `?no_esc` 输出用户输入。
