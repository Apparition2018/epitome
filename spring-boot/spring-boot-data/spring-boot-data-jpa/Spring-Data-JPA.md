# Spring Data JPA 速查文档

> Spring Data JPA 是 Spring Data 家族的一员，基于 JPA 规范（默认实现 Hibernate），让你用「定义接口方法」的方式即可获得完整的数据访问能力，无需手写 SQL 与实现类。

本文档为通用参考，示例取自项目 `spring-boot-jpa` 模块，可对照学习。

## 1. 核心概念

- **JPA**：Java 持久化规范（Jakarta Persistence），定义实体映射与 ORM 的标准。
- **Hibernate**：JPA 的主流实现，负责生成 SQL、管理实体生命周期。
- **Spring Data JPA**：在 JPA 之上提供 Repository 抽象，自动生成接口实现，大幅减少样板代码。
- **三层关系**：你写的是 **Spring Data JPA** → 底层由 **Hibernate** 实现 → 最终通过 **JDBC** 执行 SQL。

### 基本组成

```java
// 1. 实体：对应数据库表
@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "Code", length = 3)
    private String code;
    // 其他字段...
}

// 2. Repository 接口：无需实现，Spring 运行时自动生成实现类
public interface CountryRepository extends JpaRepository<Country, String> {
    // 方法名即查询
    List<Country> findByPopulationGreaterThan(Integer population);
}

// 3. 使用：直接注入接口
@Autowired
private CountryRepository countryRepository;
```

## 2. 实体映射

### 2.1 基本注解

直接在实体上说明：

```java
@Entity                            // 标记为实体
@Table(name = "country")           // 指定表名（省略则用类名）
public class Country {

    @Id                                                       // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)       // 自增主键
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Name", length = 35, nullable = false)     // 列映射：长度、非空
    private String name;

    @Column(name = "SurfaceArea", precision = 10, scale = 2)  // DECIMAL(10,2)
    private BigDecimal surfaceArea;
}
```

### 2.2 枚举映射

枚举映射的简单方式是 `@Enumerated(EnumType.STRING)`（库值 = 枚举名，如库存 `ASIA`、枚举名 `ASIA`）。但本模块 world 库的 `Continent` 列存的是 `"North America"`，与枚举名 `NORTH_AMERICA` 不一致，`@Enumerated` 对不上，故用 `AttributeConverter` 做 Java 枚举与库值的双向转换：

```java
@Converter(autoApply = true)
public class ContinentConverter implements AttributeConverter<Continent, String> {
    public String convertToDatabaseColumn(Continent c) { return c == null ? null : c.getValue(); }
    public Continent convertToEntityAttribute(String v) { return Continent.fromValue(v); }
}
```

实体字段直接声明 `Continent` 类型即可，无需 `@Enumerated`，转换由 `@Converter` 自动完成。

## 3. Repository 查询能力

### 3.1 声明仓储与子接口体系

**Repository 是一个空标记接口**，仅用于标识「这是一个数据仓储」。定义持久层接口继承 `Repository`，即可让其纳入 Spring 容器。常用子接口体系：

| 子接口                                 | 继承自                          | 提供能力                               |
|-------------------------------------|------------------------------|------------------------------------|
| `CrudRepository<T, ID>`             | `Repository`                 | 基础 CRUD                            |
| `PagingAndSortingRepository<T, ID>` | `CrudRepository`             | 分页 + 排序                            |
| `JpaRepository<T, ID>`              | `PagingAndSortingRepository` | JPA 特有方法（如 `flush`、`saveAndFlush`） |

> 在 Spring Boot 场景下，只要仓库接口放在启动类所在包（或其子包）下，`@EnableJpaRepositories` 会自动生效，无需手写。

**不继承接口，仅用注解声明仓储**：`@RepositoryDefinition(domainClass = ..., idClass = ...)` 可替代继承，方法名依旧支持派生查询：

```java
@RepositoryDefinition(domainClass = City.class, idClass = Integer.class)
public interface CityDefinitionRepository {
    Optional<City> findByName(String name);  // 派生查询
    long count();                            // 内置方法
}
```

### 3.2 继承 JpaRepository 自带 CRUD

继承 `JpaRepository<T, ID>` 即自动获得，无需写任何方法：

- `save` / `saveAndFlush` / `saveAll`
- `findById` / `findAll` / `findAllById`
- `count` / `existsById`
- `deleteById` / `delete` / `deleteAll`
- `findAll(Sort)` / `findAll(Pageable)`

### 3.3 派生查询（Derived Query）

**方法名即查询语句**，Spring Data 自动解析方法名生成 SQL，无需写实现：

```java
List<Country> findByContinent(Continent continent);                   // 等值（枚举）
Optional<Country> findFirstByName(String name);                       // 首个匹配
List<Country> findByPopulationGreaterThanOrderByPopulationDesc(Integer p); // 大于+倒序
List<Country> findByPopulationBetween(Integer min, Integer max);      // 区间
List<Country> findByNameContainingIgnoreCase(String keyword);         // 模糊+忽略大小写
List<Country> findByContinentAndPopulationGreaterThanEqual(...);      // 组合 And
List<Country> findByRegionIn(List<String> regions);                   // IN
long countByContinent(Continent c);                                   // count
boolean existsByCode(String code);                                    // exists
void deleteByName(String name);                                       // 删除（派生删除，无需 @Modifying，框架自动处理）
List<Country> findTop3ByOrderByPopulationDesc();                      // 限制条数：人口最多的前 3 个国家
```

### 3.4 JPQL 查询（@Query）

JPQL 操作对象是**实体及其属性**（非表/列），数据库无关、可移植：

```java
// 分组聚合，返回 [大洲, 数量] 数组
@Query("select c.continent, count(c) from Country c group by c.continent")
List<Object[]> countGroupByContinent();

// 命名参数 :name（配合 @Param）
@Query("select c from Country c where lower(c.name) like concat('%', lower(:name), '%')")
List<Country> findByNameLike(@Param("name") String name);

// 位置参数：?1 ?2 与方法形参一一对应，字符串内可直接拼 %
@Query("select c from Country c where c.name like %?1 or c.region like ?2")
List<Country> findByNameLikeAndRegionLike(String name, String region);

// JOIN FETCH：一次取回关联集合，避免 N+1
@Query("select distinct c from Country c left join fetch c.cities where c.code = :code")
Optional<Country> findWithCitiesByCode(@Param("code") String code);
```

### 3.5 Native SQL（@Query + nativeQuery=true）

直接写数据库 SQL，与数据库强绑定，适合复杂/性能敏感场景：

```java
@Query(value = "select * from country where continent = :continent", nativeQuery = true)
List<Country> findNativeByContinent(@Param("continent") String continent);
```

## 4. 派生查询关键字速查

| 关键词                                                         | 说明                                                                        | 示例                                             |
|-------------------------------------------------------------|---------------------------------------------------------------------------|------------------------------------------------|
| 前缀                                                          | `find…By` / `read…By` / `get…By` / `count…By` / `exists…By` / `delete…By` | `countByContinent`                             |
| `And` / `Or`                                                | 条件组合                                                                      | `findByContinentAndPopulationGreaterThanEqual` |
| `Between` / `LessThan` / `GreaterThan` / `GreaterThanEqual` | 比较                                                                        | `findByPopulationBetween`                      |
| `In` / `NotIn`                                              | 集合                                                                        | `findByRegionIn`                               |
| `Containing` / `StartingWith` / `EndingWith`                | 模糊（LIKE）                                                                  | `findByNameContaining`                         |
| `IgnoreCase`                                                | 忽略大小写                                                                     | `findByNameContainingIgnoreCase`               |
| `IsNull` / `NotNull`                                        | 空判断                                                                       | `findByEmailIsNull`                            |
| `OrderBy…Desc` / `Asc`                                      | 排序                                                                        | `OrderByPopulationDesc`                        |
| `First` / `Top`                                             | 限制条数（`TopN` 取前 N 条）                                                       | `findTop3ByOrderByPopulationDesc`              |
| 关联导航                                                        | 用 `_` 或属性路径穿透关联                                                           | `findByCountryCode`（穿透 `Country.country.code`） |

## 5. 修改操作 @Modifying

JPQL 的 `update` / `delete` **必须**加 `@Modifying`，否则抛异常；需在事务中执行（方法上加 `@Transactional`），返回 `int` 表示受影响行数：

```java
@Modifying
@Transactional
@Query("update Country c set c.population = :population where c.code = :code")
int updatePopulationByCode(@Param("code") String code, @Param("population") Integer population);

@Modifying
@Transactional
@Query("delete from Country c where c.population < :threshold")
int deleteByPopulationLessThan(@Param("threshold") Integer threshold);
```

> 注意：`@Modifying` 修改后，同一事务内 `findById` 读到的是修改后的值（一级缓存 + 同步）。

## 6. 分页与排序

把 `Pageable` / `Slice` / `Sort` 作为方法参数，Spring Data 自动追加 `LIMIT` / `ORDER BY`。

### 6.1 Page 分页（含总数）

返回 `Page`：除当前页数据外，还额外执行一次 `count` 查询得到总数，可渲染传统分页栏：

```java
Page<Country> page = countryRepository.findByContinent(
        Continent.ASIA, PageRequest.of(0, 5, Sort.by("population").descending()));

page.getTotalElements();   // 总条数
page.getTotalPages();      // 总页数
page.getContent();         // 当前页数据
```

### 6.2 Slice 分页（不查总数）

返回 `Slice`：**不执行 count 查询**，只保证当前页 + `hasNext()` 判断，拿不到 `getTotalElements()` / `getTotalPages()`，比 `Page` 更轻量，适合下拉加载更多 / 无限滚动：

```java
Slice<City> slice = cityRepository.findByPopulationGreaterThanEqual(
        0, PageRequest.of(0, 50));
slice.getContent();     // 当前页数据（50 条）
slice.hasNext();        // 是否有下一页
// slice.getTotalElements();  // ❌ 不存在，编译报错
```

### 6.3 独立 Sort（仅排序，不分页）

`Sort` 单独作为参数，只追加 `ORDER BY`，不限制条数：

```java
List<City> cities = cityRepository.findByPopulationGreaterThanEqual(
        0, Sort.by(Sort.Direction.DESC, "population"));
```

- 页码从 0 开始。
- 原生 SQL 分页需额外提供 `countQuery` 才能正确统计总数。

## 7. 关联关系

### 7.1 多对一（@ManyToOne）

外键由「多」方维护，`@JoinColumn` 指定外键列：

```java
@ManyToOne(fetch = FetchType.LAZY)   // 默认多对一为 EAGER，建议显式 LAZY
@JoinColumn(name = "CountryCode")
private Country country;
```

### 7.2 一对多（@OneToMany）

反方用 `mappedBy`（指向「多」方维护外键的字段名）：

```java
@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
@OrderBy("population DESC")
private List<City> cities = new ArrayList<>();
```

- `mappedBy`：本侧不建外键，由对方字段维护。
- `cascade = CascadeType.ALL`：级联持久化/删除/合并。
- `orphanRemoval = true`：从集合移除即删除对应行（孤儿删除）。
- 双向引用建议提供工具方法（`addCity`/`removeCity`），保证两端一致。

## 8. 复合主键

两种方式：`@IdClass` 或 `@EmbeddedId`。示例用 `@IdClass`：

```java
@Entity
@Table(name = "countrylanguage")
@IdClass(CountryLanguageId.class)
public class CountryLanguage {
    @Id @Column(name = "CountryCode", length = 3) private String countryCode;
    @Id @Column(name = "Language", length = 30)   private String language;

    // 主键字段同时作外键：insertable/updatable=false 表示列由主键管理
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CountryCode", insertable = false, updatable = false)
    private Country country;
}
```

主键类要求：可序列化、无参构造、实现 `equals/hashCode`（`@Data` 自动生成）。查询：`findById(new CountryLanguageId("CHN", "Chinese"))`。

## 9. 投影 Projection

只查所需字段，减少整行加载与数据传输。

### 9.1 DTO 构造器投影

JPQL `new 全限定类名(...)`，构造器参数顺序与 select 列表一致：

```java
@Query("select new org.ljh.jpa.projection.CountryNamePopulation(c.code, c.name, c.population) "
     + "from Country c order by c.population desc")
List<CountryNamePopulation> findNamePopulationProjection();
```

### 9.2 单字段 / 单值投影

```java
@Query("select c.name from Country c where c.code = :code")
Optional<String> findNameByCode(@Param("code") String code);

@Query("select c.surfaceArea from Country c where c.code = :code")
BigDecimal findSurfaceAreaByCode(@Param("code") String code);
```

## 10. 实体图 @EntityGraph 与 N+1

### 问题：N+1

延迟加载的集合，遍历 N 条父记录会触发 N 次额外查询（N+1 问题）。

### 解决方式一：JOIN FETCH（JPQL，见 3.4）

在 JPQL 里用 `left join fetch` 一次性取回集合，简单直接，但写死在查询里。

### 解决方式二：@EntityGraph（声明式，复用性好，不污染 JPQL）

```java
@EntityGraph(attributePaths = "cities")          // 一次取回 cities
@Query("select c from Country c where c.code = :code")
Optional<Country> findWithCitiesByCodeUsingEntityGraph(@Param("code") String code);

@EntityGraph(attributePaths = {"cities", "languages"})  // 一次取多个集合
```

## 11. 动态查询 Specification

适合查询条件不固定的场景（可选筛选项）。Repository 继承 `JpaSpecificationExecutor<T>` 后可用 `findAll(Specification)`：

```java
public interface CountryRepository extends JpaRepository<Country, String>,
        JpaSpecificationExecutor<Country> { }

// 动态拼接条件
Specification<Country> spec = (root, query, cb) -> cb.and(
        cb.equal(root.get("continent"), Continent.ASIA),
        cb.greaterThan(root.get("population"), 130_000_000));
List<Country> result = countryRepository.findAll(spec);

// 复杂组合：OR
Specification<Country> spec2 = (root, query, cb) -> cb.or(
        cb.equal(root.get("continent"), Continent.EUROPE),
        cb.like(root.get("name"), "J%"));
```

- `root.get("属性名")`：引用实体字段。
- `cb`（CriteriaBuilder）：`equal` / `greaterThan` / `like` / `and` / `or` 等条件构造器。
- 适合与 `null` 判断配合做「可选条件」动态拼装。

## 12. Query by Example（QBE）

用**一个实体实例作为查询模板**，Spring Data 自动把非空字段作为等值条件，无需写 JPQL。Repository 继承 `JpaRepository` 即内置 `findAll(Example)`：

```java
// 模板：name 非空 → 作为等值条件（name = "China"）
Country probe = new Country();
probe.setName("China");
Example<Country> example = Example.of(probe);
List<Country> result = countryRepository.findAll(example);
```

配合 `ExampleMatcher` 可定制匹配规则（忽略大小写、模糊等）：

```java
Country probe = new Country();
probe.setName("fra");
Example<Country> example = Example.of(probe,
        ExampleMatcher.matching()
                .withIgnoreCase()                        // 忽略大小写
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)); // 包含匹配
List<Country> matched = countryRepository.findAll(example);   // 命中 FRA
```

> 对比：`Specification` 适合条件完全动态、可任意 `and/or` 组合；`QBE` 适合「以某个已有对象为模板」的简单查询，语义上接近 `find by example`。

## 13. 并发控制

### 13.1 乐观锁 @Version

防止并发覆盖。实体加 `@Version` 字段，每次 update 自动 `+1`；提交时若版本号已变（被他人改过）则抛 `OptimisticLockingFailureException`：

```java
@Version
@Column(name = "version")
private Long version;
```

原理：两个客户端读取同一 `version=0` → 第一个提交成功（0→1）→ 第二个仍基于 `version=0` 提交，`UPDATE ... WHERE version=0` 影响 0 行，抛冲突异常。

| 场景      | 结果                                        |
|---------|-------------------------------------------|
| 乐观锁正常路径 | 每次 update 版本递增 0→1→2…                     |
| 并发冲突    | 后提交者抛 `OptimisticLockingFailureException` |

### 13.2 悲观锁 @Lock

用 `@Lock(LockModeType.PESSIMISTIC_WRITE)` 让查询直接对行加数据库写锁（`SELECT ... FOR UPDATE`），事务提交/回滚后释放。靠数据库行锁，适合并发写冲突高的场景：

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)     // 生成 SELECT ... FOR UPDATE
@Query("select p from Product p where p.id = :id")
Optional<Product> findByIdForUpdate(@Param("id") Long id);
```

**调用方必须处于事务中**（方法加 `@Transactional`），否则会因无活动事务抛异常：

```java
@Transactional
void demo() {
    Product locked = productRepository.findByIdForUpdate(1L).orElseThrow();
    // 已加写锁，其他事务对该行的写操作会阻塞等待，直到本事务提交/回滚
}
```

> 对比：乐观锁靠版本号，无行锁、性能好，适合读多写少；悲观锁靠数据库行锁、立刻生效，适合写冲突高的场景。

## 14. 审计 Auditing

自动填充创建人/时间、修改人/时间。

### 14.1 开启审计 + 提供当前操作人

```java
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("system");  // 真实场景从 SecurityContext / 请求上下文获取
    }
}
```

### 14.2 实体声明审计字段

```java
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @CreatedBy        @Column(name = "created_by")  private String createdBy;
    @CreatedDate      @Column(name = "created_at", updatable = false) private LocalDateTime createdAt;
    @LastModifiedBy   @Column(name = "updated_by")  private String updatedBy;
    @LastModifiedDate @Column(name = "updated_at")  private LocalDateTime updatedAt;
}
```

新增时自动填 `createdBy/createdAt/updatedAt`，更新时填 `updatedBy/updatedAt`。

## 15. 与事务的关系

- Spring Data JPA 自动在 `save` / `delete` / `findById` 等内置方法上应用事务，开箱即用。
- **自定义修改方法（`@Modifying`）必须显式加 `@Transactional`**，否则报错。
- 事务本身是 Spring 通用能力（`@Transactional`），Spring Data JPA 在此之上做了自动化，两者配合使用。

## 16. 常见坑点

| 坑                             | 说明 / 解决                                                           |
|-------------------------------|-------------------------------------------------------------------|
| `@Enumerated` 与库值不符           | 库值 `"North America"` ≠ 枚举名 `NORTH_AMERICA`，用 `AttributeConverter` |
| N+1 查询                        | 用 `JOIN FETCH` 或 `@EntityGraph`                                   |
| `@Modifying` 未加事务             | 必须配合 `@Transactional`，否则报错                                        |
| 复合主键忘记 equals/hashCode        | 主键类必须实现，否则 `findById` 异常                                          |
| 级联保存子实体主键没填                   | 复合主键子实体需手动 `setCountryCode`，或用 `@MapsId` 让主键字段自动复用外键关联的实体主键       |
| `findById().get()` 直接调用       | 用 `orElseThrow()` 显式处理空值，避免 NPE                                   |
| `Slice` 没有 `getTotalElements` | Slice 只保证当前页 + `hasNext()`，需总数时改用 `Page`                          |
| `@Lock` 必须在事务内调用              | 悲观锁需 `@Transactional` 包裹，否则因无活动事务抛异常                              |

## 关联阅读

- 本项目完整示例与测试：见 `spring-boot-jpa` 模块 `src/test/java/.../JpaRepositoryTests.java`。
- 官方文档：<https://docs.spring.io/spring-data/jpa/reference/>
