# Jakarta Bean Validation 校验

基于 `jakarta.validation` + Hibernate Validator，演示 Bean Validation 的常见用法。

> 规范演进：Bean Validation 1.0（JSR 303）→ 1.1（JSR 349）→
> 2.0（JSR 380，新增 `@NotBlank`/`@NotEmpty`/`@Email`/`@Positive` 等）→
> Jakarta Bean Validation 3.x （包名由 `javax.validation` 迁移为 `jakarta.validation`）。
> 本模块基于 3.x，其注解与 机制基本从 JSR 303 / JSR 380 延续而来。

## 1. 依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

> 注意：`spring-boot-starter-web` 2.3+ 不再自动包含校验能力，需要显式引入
> `spring-boot-starter-validation`（内含 `jakarta.validation-api` + `hibernate-validator`）。

## 2. 常用校验注解速查表

| 注解                              | 作用               | 适用类型                     |
|---------------------------------|------------------|--------------------------|
| `@NotNull` / `@Null`            | 值非空 / 必须为空       | 任意引用类型                   |
| `@NotEmpty`                     | 不能为空（集合/字符串非空）   | String、Collection、Map、数组 |
| `@NotBlank`                     | 不能为空且不能全为空白      | String                   |
| `@Size`                         | 长度/大小范围          | String、Collection、Map、数组 |
| `@Min` / `@Max`                 | 数值上下限（含端点）       | 数值类型                     |
| `@Positive` / `@PositiveOrZero` | 必须为正（含 0）        | 数值类型                     |
| `@Digits(integer, fraction)`    | 整数/小数位数上限        | BigDecimal、BigInteger    |
| `@Email`                        | 邮箱格式             | String                   |
| `@Pattern(regexp)`              | 正则匹配             | String                   |
| `@Past` / `@Future`             | 必须是过去 / 未来时间     | 时间类型                     |
| `@AssertTrue` / `@AssertFalse`  | 必须为 true / false | boolean、Boolean          |

## 3. 三种触发方式

### 3.1 `@RequestBody`（DTO 字段校验）
```java
@PostMapping
public Object create(@Validated(Create.class) @RequestBody UserCreateRequest request) { ... }
```
失败抛出 `MethodArgumentNotValidException`。

### 3.2 路径 / 查询参数校验
在需要校验的方法参数上加 `@Validated`，并叠加约束注解：
```java
@RestController
public class UserController {
    @GetMapping("/{id}")
    public Object get(@Validated @PathVariable @Min(1) Long id) { ... }
}
```
失败抛出 `HandlerMethodValidationException`（Spring 6.1+）。

> 注意：参数校验不应在类上标注 `@Validated`。若在类上启用，
> Spring 会改用 `MethodValidationPostProcessor` 的方法级 AOP 校验，
> 届时参数校验抛出的是 `ConstraintViolationException`，且可能干扰 `@RequestBody` 的分组校验。

### 3.3 Service 方法级校验（AOP）
Service 类上标注 `@Validated`（需注册 `MethodValidationPostProcessor`）：
```java
@Service
@Validated
public class UserService {
    public String get(@Min(1) Long id) { ... }   // 失败抛 ConstraintViolationException
}
```
**方法返回值也能标注约束**，同样由 `MethodValidationPostProcessor` 拦截、失败抛 `ConstraintViolationException`：
```java
@NotBlank(message = "方法返回值不能为空白")
public String getUsername(@NotBlank(message = "参数 name 不能为空") String name) { ... }
```

## 4. 分组校验

定义接口作为分组标记，可继承 `Default` 表示"该校验同时包含默认分组约束"：
```java
public interface Create extends Default {}   // 校验 Create 时同时校验 Default 分组
public interface Update {}
```
字段上区分约束所属分组：
```java
@Null(groups = Create.class, message = "新增时 id 必须为空")
@NotNull(groups = Update.class, message = "更新时 id 不能为空")
Long id;
```
触发时指定分组：`@Validated(Create.class)` / `@Validated(Update.class)`。

## 5. `@Valid` 级联校验：根对象与嵌套对象

Bean Validation 默认**只校验当前对象的直接字段，不会自动深入嵌套对象**。`@Valid` 的核心作用是触发
**级联校验（cascade validation）**——让校验器从入口对象出发，对其内部（以及内部的内部）逐层递归校验。

### 5.1 方法参数上的 `@Valid`：根对象（入口）

```java
@PostMapping
public Object create(@Valid UserCreateRequest request) { ... }
```

- `UserCreateRequest` 是本次校验的**根对象**，作为整棵对象图校验的入口，不属于"嵌套对象"。
- 由 Spring MVC 驱动，失败抛 `MethodArgumentNotValidException` / `BindException`。

### 5.2 属性上的 `@Valid`：嵌套对象

```java
public record UserCreateRequest(@Valid Address address) { }
```

- `Address` 才是真正的**嵌套对象**；`@Valid` 在此作为**级联跳板**，让校验深入一层。
- **不加 `@Valid` 会静默失效**：`Address` 内部字段上的 `@NotBlank` 等约束不会被执行。

| 标注位置                        | 对象角色    | 语义               |
|-----------------------------|---------|------------------|
| 方法参数 `@Valid User user`     | 根对象（入口） | 声明整个对象图从此处开始递归校验 |
| 属性 `@Valid Address address` | 嵌套对象    | 声明校验沿此属性继续深入一层   |

## 6. 集合元素校验

对集合内的每个元素单独校验，约束注解写在**类型实参**上：

```java
List<@NotBlank @Size(max = 10) String> hobbies  // 每个元素：非空且长度 ≤ 10
List<@Valid User> users                         // 每个元素做级联校验
```

- 简单类型元素：约束注解直接标注在类型参数上，如上例。
- 对象类型元素：需要级联校验内部字段时加 `@Valid`。

## 7. 自定义约束注解（三步法）

1. 定义注解，用 `@Constraint(validatedBy = ...)` 关联校验器，含 `message/groups/payload`。
2. 实现 `ConstraintValidator<A, T>`，覆写 `isValid` 完成逻辑。
3. 在 DTO 字段上使用该注解。

```java
// 1. 注解
@Constraint(validatedBy = EnumValueValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface EnumValue {
    Class<? extends Enum<?>> enumClass();
    String message() default "取值不在允许的枚举范围内";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

// 2. 校验器
public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {
    @Override public void initialize(EnumValue annotation) {
        this.enumClass = annotation.enumClass();   // 读取注解属性，只调用一次
        this.allowNull = annotation.allowNull();
    }
    @Override public boolean isValid(Object v, ConstraintValidatorContext ctx) {
        // 比对 enumClass.getEnumConstants() 的 name()
    }
}
```

> `ConstraintValidator` 生命周期：
> - `initialize()`：校验器实例化后**只调用一次**，用于读取注解属性
>   （如 `@EnumValue(enumClass = ...)` 中的 `enumClass`/`allowNull`）。
> - `isValid()`：**每次校验调用一次**；同一实例可能被并发调用，实现须**线程安全**（不持有可变状态）。
> - 返回 `false` 时可用 `ConstraintValidatorContext.buildConstraintViolationWithTemplate(...)` 覆盖默认错误消息。
>
> 注：Bean Validation 规定，除 `@NotNull` 等少数注解外，**大多数约束对 `null` 值视为合法**
> （"null is valid"）。例如 `@RequestParam(required = false) @Email String email` 传 `null` 时
> `@Email` 直接通过；自定义校验器也常据此自行决定 `null` 的处理（见上文 `allowNull`）。

## 8. 快速失败

Hibernate Validator 默认收集全部错误；可配置为遇到第一个错误立即返回：
```java
LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
factory.getValidationPropertyMap().put(
        BaseHibernateValidatorConfiguration.FAIL_FAST, "true");
```

## 9. 统一异常处理

用 `@RestControllerAdvice` 集中捕获三类异常，返回结构化 JSON（见
`handler/GlobalExceptionHandler.java`）。

## 10. 两种校验触发链路

本模块中的校验可通过**两种链路**触发，对应两类测试：

- **编程式校验链路**：直接调用 Bean Validation 的 `Validator.validate()` 手动触发，不依赖 Spring 容器，
  由 `ValidationTest` 演示。
- **Spring MVC 自动触发链路**：请求到达 Controller 时由 Spring MVC 自动触发校验，由 `UserControllerTest`
  演示（配合 `@WebMvcTest` + `MockMvc`）。

| 维度           | 编程式校验（`Validator.validate()`）             | Spring MVC 自动触发                                                          |
|--------------|-------------------------------------------|--------------------------------------------------------------------------|
| 触发方式         | 手动调用 `Validator.validate(object, groups)` | 请求处理时自动触发                                                                |
| 校验对象         | 任意 `@Valid`-able 对象（DTO 等）                | `@RequestBody` / `@PathVariable` / `@RequestParam`                       |
| 依赖 Spring 容器 | 否（纯 Bean Validation API）                  | 是（Spring MVC、Servlet 环境）                                                 |
| 分组支持         | 是（`validate(obj, Create.class)`）          | 是（`@Validated(Create.class)`）                                            |
| 异常类型         | `Set<ConstraintViolation<T>>`（返回值，不抛异常）   | `MethodArgumentNotValidException` / `HandlerMethodValidationException` 等 |
| 错误处理         | 遍历返回值自行收集                                 | `GlobalExceptionHandler` 统一返回结构化 JSON                                    |
| 对应测试         | `src/test/.../ValidationTest.java`        | `src/test/.../controller/UserControllerTest.java`                        |

```java
// 编程式：手动触发，返回错误集合，不依赖容器
Set<ConstraintViolation<UserCreateRequest>> violations = validator.validate(request);

// MVC 自动：请求到达 Controller 时由 Spring MVC 触发，失败抛异常
@PostMapping
public Object create(@Validated(Create.class) @RequestBody UserCreateRequest request) { ... }
```

> 两者可互为补充：编程式适合快速验证 DTO 上的注解与分组逻辑；MVC 自动触发覆盖"请求边界"，
> 验证 Controller 的入参是否被正确拦截，以及异常是否被 `GlobalExceptionHandler` 统一处理。
