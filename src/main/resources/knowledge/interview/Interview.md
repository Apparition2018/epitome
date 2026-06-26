# Interview

1. Spring 如何解决循环依赖
2. Mybatis-plus 是如何实现的
3. Hibernate/Mybatis

1. 使用哪些中间件（ES ……）
2. 工中难忘的问题
---
## 工作中难忘的问题
### 预约场次的人数和预约记录条数不一致
    1 乐观锁，测试
    2 原子：容量不能超卖、锁场不能预约、自增原子性
    同一人重复就预约
---
## JDK8 新特性
| 特性                        | 描述                                                                           |
|:--------------------------|:-----------------------------------------------------------------------------|
| Lambda 和 Method Reference | 简化匿名内部类的编写，支持函数式编程                                                           |
| Stream API                | 提供对集合的高阶操作（过滤、映射、归约等），支持串行和并行流                                               |
| Optional                  | 优雅地处理空指针异常                                                                   |
| java.time                 | 全新日期时间 API（LocalDate, LocalTime, LocalDateTime）                              |
| 接口默认/静态方法                 | 支持在接口中使用 default 定义具体方法                                                      |
| CompletableFuture         | 支持异步链式调用与函数式组合的异步编程工具                                                        |
| HashMap / Map 优化          | HashMap 底层优化，Map 增加 forEach / compute / merge 等方法                            |
| Base64                    |
| @Repeatable               | 支持在同一位置重复使用同一个注解                                                             |
| 工具类增强                     | Objects.isNull/nonNull/requireNonNull(), String.join(), Arrays.parallelXxx() |

---
## [Spring](./Spring.md)
- IOC
- AOP
- @Transactional 失效场景
---
## [JPA](../../../java/springboot/controller/JpaController.java)

---
## [Messaging](./Messaging.md)

---
## [Concurrent](../../../java/knowledge/concurrent/concurrent.md)

---
## [设计模式](../../../java/knowledge/design/pattern/DesignPattern.md)

---
