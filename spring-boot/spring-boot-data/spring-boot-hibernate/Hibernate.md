# Spring Boot + Hibernate 原生 API 速查

> 本模块直接使用 Hibernate 原生 `Session` / `SessionFactory` 完成持久化，不经过任何上层抽象，用于理解 Hibernate 的底层机制。

Spring 注入的 `EntityManager` 底层实现就是 Hibernate 的 `Session`，可用 `entityManager.unwrap(Session.class)` 拿到原生 Session，从而调用 Hibernate 特有的能力。

```java
@Autowired
private EntityManager entityManager;

Session session = entityManager.unwrap(Session.class);          // 原生 Session
SessionFactory sf = entityManagerFactory.unwrap(SessionFactory.class); // 原生工厂
```

按主题组织为六个大方向，与 `HibernateTests.java` 的六个测试区块一一对应。

## 一、Session 核心

### 1.1 Session 基础 CRUD

| 方法                        | 说明                   |
|---------------------------|----------------------|
| `session.persist(e)`      | 新增，进入持久态             |
| `session.find(Class, id)` | 按主键立即查询，不存在返回 `null` |
| `session.remove(e)`       | 删除，flush 时执行 DELETE  |
| `session.evict(e)`        | 把实体移出 Session（变游离态）  |
| `session.merge(e)`        | 把游离实体合并回持久态（重新关联）    |
| `session.flush()`         | 把缓存中的 SQL 立即同步到数据库   |

```java
session.persist(tmp);                    // 新增
Country c = session.find(Country.class, "CHN"); // 查询
c.setPopulation(2_000_000);              // 修改（脏检查自动 UPDATE）
session.remove(c);                       // 删除
```

### 1.2 实体生命周期（四种状态）

| 状态         | 说明                                 | 判别 API                          |
|------------|------------------------------------|---------------------------------|
| transient  | new 出来，未被 Session 管理，改动不落库         | `session.contains(e) == false`  |
| persistent | 已被 Session 管理，受一级缓存 + 脏检查跟踪，改动自动同步 | `session.contains(e) == true`   |
| detached   | 离开 Session（evict / 事务提交），改动不再自动同步  | `contains == false`，可用 merge 召回 |
| removed    | 标记删除，flush 时 DELETE                | —                               |

状态流转：`new` →(persist)→ `persistent` →(evict/提交)→ `detached` →(merge)→ `persistent` →(remove)→ `removed`。

### 1.3 一级缓存与脏检查

- **一级缓存（Session 缓存）**：同一 Session 内按主键查同一条记录，第二次直接返回缓存实例（`==` 相同），不再查库。
- **脏检查（Dirty Checking）**：Session 保存实体的「快照」，`flush` 时对比变化，只为「变过的实体」发 UPDATE。
- **FlushMode**：`AUTO`（默认，查询前自动 flush）vs `COMMIT`（仅提交时 flush）；可用 `session.setHibernateFlushMode(...)` 调整。

```java
Country c1 = session.find(Country.class, "CHN");
Country c2 = session.find(Country.class, "CHN");
assertThat(c1).isSameAs(c2);   // 命中一级缓存
```

#### refresh / clear

- **`refresh(e)`**：用数据库当前值覆盖内存中的实体（重新查询，覆盖可能的脏数据）。
- **`clear()`**：清空整个一级缓存，使后续查询重新读库。

```java
session.createNativeMutationQuery("update country set Population = 999999 where Code = 'XX4'").executeUpdate();
session.refresh(c);          // 用库里的值覆盖内存实体
session.clear();             // 清空一级缓存，find 会重新查库
```

## 二、查询 API

### 2.1 HQL（Hibernate Query Language）

操作对象是**实体类与属性**（非表/列），数据库无关。

| 特性          | 示例                                                                                |
|-------------|-----------------------------------------------------------------------------------|
| 位置参数        | `where c.continent = ?1 and c.population > ?2`（`setParameter(1, ...)`）            |
| 命名参数        | `where c.name = :name`（`setParameter("name", ...)`）                               |
| 投影          | `select c.code, c.name from Country c` → `Object[]`                               |
| 聚合          | `select c.continent, count(c) from Country c group by c.continent`                |
| 分页          | `q.setFirstResult(0); q.setMaxResults(3);`（等效 LIMIT + OFFSET）                     |
| 迫切连接        | `select distinct c from Country c left join fetch c.cities`（一次性带出集合，消除 N+1）       |
| 构造器投影       | `select new org.ljh.hibernate.dto.CountryCityDTO(c.code, ci.name) ...`（直接映射到 DTO） |
| Named Query | 实体上用 `@NamedQuery` 预定义，测试中 `session.getNamedQuery("Country.byContinent")` 复用      |

```java
// JOIN FETCH：查询国家时一次带出城市集合
Query<Country> q = session.createQuery(
        "select distinct c from Country c left join fetch c.cities where c.code = :code", Country.class);

// 构造器投影：结果直接进 DTO
Query<CountryCityDTO> dto = session.createQuery(
        "select new org.ljh.hibernate.dto.CountryCityDTO(c.code, ci.name) " +
        "from City ci join ci.country c where c.code = :code", CountryCityDTO.class);
```

### 2.2 Criteria API

类型安全、可**动态拼装**条件：

```java
CriteriaBuilder cb = session.getCriteriaBuilder();
CriteriaQuery<Country> cq = cb.createQuery(Country.class);
Root<Country> root = cq.from(Country.class);
cq.select(root).where(cb.and(
        cb.equal(root.get("continent"), Continent.ASIA),
        cb.greaterThan(root.get("population"), 1_000_000_000)));
List<Country> list = session.createQuery(cq).list();
```

### 2.3 Native SQL

直接写数据库 SQL，强绑定数据库：

```java
Query<Country> q = session.createNativeQuery(
        "select * from country where continent = :continent", Country.class);
q.setParameter("continent", "Asia");
List<Country> asia = q.list();
```

## 三、数据写操作

### 3.1 批量操作

用 `createMutationQuery` 直接执行 HQL 的 `update` / `delete`，**绕过脏检查**，比逐条 load + flush 高效：

```java
MutationQuery update = session.createMutationQuery(
        "update Country c set c.population = c.population + 1 where c.code like :prefix");
update.setParameter("prefix", "BU%");
int affected = update.executeUpdate();   // 返回受影响行数
```

> 注意：批量操作会绕过一级缓存，操作后需 `session.clear()` 刷新，否则读到旧值。

### 3.2 @DynamicUpdate

默认 Hibernate 对实体 UPDATE 会更新**所有列**；加 `@DynamicUpdate` 后只更新**发生变化的列**，减少网络与锁开销，适合大宽表：

```java
@Entity
@DynamicUpdate
public class Product { /* ... */ }
```

## 四、关联映射

### 4.1 关联与复合主键

- **一对多级联**：`@OneToMany(cascade = ALL, orphanRemoval = true)`，`persist` 父实体即级联保存子实体。
- **复合主键**：`@IdClass(CountryLanguageId.class)`，用主键类 `session.find(CountryLanguage.class, new CountryLanguageId("CHN", "Chinese"))` 直接查询。

### 4.2 可嵌入组件 @Embeddable

`@Embeddable` 类不是实体、没有自己的表，其字段会「展开」到宿主实体的同一张表中；宿主用 `@Embedded` 组合复用一组字段，查询时用路径 `s.address.city` 访问。本项目把 `Address` 嵌入继承体系里的 `Staff` 子类：

```java
@Data @Embeddable
public class Address { /* street / city / zipCode */ }

@Entity @DiscriminatorValue("STAFF")
public class Staff extends Person {
    private String department;
    @Embedded private Address address;   // 展开为 person 表的 street/address_city/zip_code 三列
}
```

### 4.3 继承映射 @Inheritance

默认用 `SINGLE_TABLE`（单表）：所有子类共享一张表，靠辨别者列区分具体类型，查询子类只访问一张表，性能最好：

```java
@Entity @Table(name = "person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISC_TYPE", discriminatorType = DiscriminatorType.STRING, length = 20)
public class Person { @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id; }

@Entity @DiscriminatorValue("STAFF")
public class Staff extends Person { private String department; }   // person 表加 department 列

@Entity @DiscriminatorValue("MANAGER")
public class Manager extends Person { private Integer teamSize; }  // person 表加 team_size 列
```

- 按具体子类查询只带出该类行；按父类 `from Person` 多态查询会带出所有子类，并能访问各自的子类字段。
- 其他策略：`JOINED`（父表 + 子表 JOIN）、`TABLE_PER_CLASS`（每类一张表），可按需选用。

### 4.4 多对多 @ManyToMany

教师与学生对多对多，通过中间表 `teacher_student` 关联。维护端（`Teacher`）加 `@JoinTable` 并级联 `PERSIST`，反向端（`Student`）用 `mappedBy`：

```java
@Data @Entity @Table(name = "teacher")
public class Teacher {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "teacher_student",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;
}
// Student 端：@ManyToMany(mappedBy = "students")
```

持久化维护端 `Teacher` 时，级联保存 `Student` 并自动写入中间表；从任一端的集合都能取到关联对象。

### 4.5 一对一 @OneToOne

外键方式：维护端持有外键列，反向端用 `mappedBy`。`author` 表持 `passport_id` 指向 `passport`：

```java
@Data @Entity @Table(name = "author")
public class Author {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "passport_id") private Passport passport;
}
// Passport 端：@OneToOne(mappedBy = "passport")
```

> 注意：反向 one-to-one（mappedBy 端）不持有外键，Hibernate 无法为其生成懒代理，反向关联需通过显式 JPQL 关联查询（`where a.passport = :pp`）读取。

## 五、缓存体系

### 5.1 find vs getReference（懒代理）

- `getReference`（旧称 `load`）：返回**懒代理**，真正访问字段才发查询；用于「只想拿主键建立外键关联」场景，零 SQL。
- `find`：立即查询，不存在返回 `null`。

```java
Country china = session.getReference(Country.class, "CHN");
PersistenceUnitUtil util = entityManagerFactory.getPersistenceUnitUtil();
util.isLoaded(china);   // false，尚未加载（懒代理）
china.getName();        // 访问才触发 SELECT
util.isLoaded(china);   // true
```

### 5.2 自然 ID（@NaturalId）

`@NaturalId` 把**业务唯一键**声明出来（如国家名），可按自然键查询而无需知道自增主键，配合 `@NaturalIdCache` 命中缓存时零 SQL：

```java
Country china = session.byNaturalId(Country.class)
        .using("name", "China")     // 业务键
        .load();
```

### 5.3 二级缓存与查询缓存

实体加 `@Cache(usage = READ_WRITE)` 与 `@NaturalIdCache` 后，实体进入**跨 Session 共享**的二级缓存。相关配置在 `application.properties`：

```properties
spring.jpa.properties.hibernate.cache.use_second_level_cache=true
spring.jpa.properties.hibernate.cache.region.factory_class=org.hibernate.cache.jcache.internal.JCacheRegionFactory
spring.jpa.properties.hibernate.generate_statistics=true
```

依赖：`hibernate-jcache`（桥接 JCache 规范）+ `caffeine`（本地缓存实例）+ `caffeine:jcache`（提供 JCache 的 `CachingProvider` SPI，与 caffeine 同版本）。三者缺一不可，缺 `jcache` 会报 `Cache provider not started`。caffeine 与 jcache 需**同版本**发布，版本在根 POM 的 `dependencyManagement` 中统一管理（`caffeine.version`）。

> 二级缓存命中情况可在测试中通过 `sessionFactory.getStatistics()` 的 `getSecondLevelCacheHitCount()` 验证。

#### 集合级二级缓存

`@OneToMany` 集合上也加 `@Cache(READ_WRITE)`（如 `Country.cities`），则「某国家的城市主键列表」也会被缓存；需同时给子实体（`City`）加 `@Cache`。可配置查询缓存后，通过 `Statistics.getCollectionStatistics(region)` 观察集合缓存区域是否建立：

```java
@OneToMany(mappedBy = "country", cascade = ALL, orphanRemoval = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
private List<City> cities;
```

#### 查询缓存

在 `application.properties` 开启 `use_query_cache=true` 后，对查询显式 `setCacheable(true)` 即可缓存查询结果（主键集合），命中时免去重新执行 SQL：

```properties
spring.jpa.properties.hibernate.cache.use_query_cache=true
```

```java
Query<Country> q = session.createQuery("select c from Country c where c.continent = :continent", Country.class);
q.setParameter("continent", Continent.ASIA);
q.setCacheable(true);      // 关键：显式开启查询缓存
List<Country> asia = q.list();
// 命中次数：sessionFactory.getStatistics().getQueryCacheHitCount()
```

> 查询缓存底层用「时间戳缓存区域」判定结果是否失效：查询涉及的表一旦发生更新，相关缓存整体作废，保证数据一致性。

## 六、并发控制

### 6.1 乐观锁 @Version

`@Version` 字段每次 UPDATE 自动 `+1`。两个并发事务基于同一版本提交时，后提交者抛 `OptimisticLockException`（原生为 `StaleObjectStateException`）。

### 6.2 悲观锁

`session.find(Class, id, LockModeType.PESSIMISTIC_WRITE)` 生成 `SELECT ... FOR UPDATE`，**必须在事务中**调用。

> 注意：实体带 `@Version` 时，请求 `PESSIMISTIC_WRITE` 会被 Hibernate 自动升级为 `PESSIMISTIC_FORCE_INCREMENT`（加锁同时递增版本），实测 `getLockMode` 返回该值。

## 关联阅读

- 完整测试：本模块 `src/test/java/.../HibernateTests.java`
- 官方文档：<https://hibernate.org/orm/documentation/6.2/>
