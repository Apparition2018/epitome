# JPA

纯 JPA（Jakarta Persistence 规范）演示模块：**不使用 Spring Data JPA 的 Repository 抽象**，数据访问完全基于标准 `EntityManager` / `EntityManagerFactory`（`jakarta.persistence.*`）。

- 启动类：`JpaApplication`（通过 `@SpringBootApplication(exclude = DataJpaRepositoriesAutoConfiguration.class)` 显式关闭 Repository 自动装配，确保整个应用不创建任何 Repository 代理）
- 测试：`src/test/java/org/ljh/jpa/JpaTests.java`

---

## 一、依赖

`pom.xml`（父模块 `org.ljh:spring-boot-data:1.0.1-SNAPSHOT`）

| 依赖              | 坐标                                                      | 作用 / scope                         |
|-----------------|---------------------------------------------------------|------------------------------------|
| commons         | `org.ljh:commons`                                       | 内部模块，提供 world 数据库 SQL（schema + 数据） |
| Spring Data JPA | `org.springframework.boot:spring-boot-starter-data-jpa` | JPA + Hibernate 自动装配               |
| H2              | `com.h2database:h2`                                     | runtime，内存数据库                      |

版本由父模块统一管理（Spring Boot BOM）。

---

## 二、配置（application.properties）

```properties
# 数据源：H2 内存库，MySQL 兼容模式，进程存活期间不关闭
spring.datasource.url=jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1

# Schema / 数据初始化
spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:db/world-db/world-schema.sql,classpath:db/demo/demo-schema.sql
spring.sql.init.data-locations=classpath:db/world-db/world-data.sql

# JPA + Hibernate
spring.jpa.hibernate.ddl-auto=none          # 关闭自动建表，表结构由 SQL 负责
spring.jpa.show-sql=true                     # 打印 SQL
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl  # @Column 精确匹配列名
spring.jpa.open-in-view=false                # 关闭 OSIV，强制 Service 层用事务+抓取策略解决懒加载
```

关键配置说明：

- **`ddl-auto=none`**：表结构完全由 `schema-locations` 指定的 SQL 创建，Hibernate 仅做对象-关系映射。
- **`PhysicalNamingStrategyStandardImpl`**：不做驼峰转下划线，列名以 `@Column(name=...)` 精确指定。
- **`open-in-view=false`**：关闭 Open Session/EntityManager in View，避免表现层意外开启长事务，强制在 Service 层用 `@Transactional` + `JOIN FETCH` 等抓取策略解决懒加载（否则事务外访问懒加载集合会抛 `LazyInitializationException`）。

序号 6 还额外开启了**实体二级缓存（规范层）**：

```properties
# 序号 6：实体二级缓存（JPA 规范层，对应 persistence.xml 的 <shared-cache-mode>）
spring.jpa.properties.jakarta.persistence.sharedCache.mode=ENABLE_SELECTIVE
spring.jpa.properties.hibernate.cache.use_second_level_cache=true
spring.jpa.properties.hibernate.cache.region.factory_class=org.hibernate.cache.jcache.internal.JCacheRegionFactory
spring.jpa.properties.hibernate.generate_statistics=true
```

- `shared-cache-mode=ENABLE_SELECTIVE`：JPA 规范开关，仅缓存显式标注 `@Cacheable` 的实体（本项目即 `Customer3`）。
- 后两条是**实现层**（Hibernate）真正让二级缓存生效的底层配置：`use_second_level_cache` 打开缓存，`region.factory_class` 指定缓存提供方（本项目用 JCache + Caffeine，依赖 `hibernate-jcache` + `caffeine:jcache`）。
- `generate_statistics`：开启 Hibernate 统计，供测试用 `Statistics.getSecondLevelCacheHitCount()` 验证命中。

### 数据表

| 来源                         | 表                                                                            | 说明                                 |
|----------------------------|------------------------------------------------------------------------------|------------------------------------|
| commons `world-schema.sql` | `country` / `city` / `countrylanguage`                                       | 真实世界数据库（4000+ 城市、200+ 国家、1000+ 语言） |
| 本模块 `demo-schema.sql`      | `person` / `teacher` / `student` / `teacher_student` / `author` / `passport` | 承载 world 三表无法表达的映射特性               |

`demo-schema.sql` 中的独立表用于表达三类特殊映射：

1. **`person`**：继承映射 `SINGLE_TABLE` + `@Embeddable`（Address 三列）+ `@Version` 乐观锁。子类专属列 `department`（Staff）/ `team_size`（Manager）及嵌入地址列 `street` / `address_city` / `zip_code` 均落本表，`version` 列供乐观锁使用。
2. **`teacher` / `student` / `teacher_student`**：多对多，中间表存 `(teacher_id, student_id)` 复合主键。
3. **`author` / `passport`**：一对一，外键 `author.passport_id` 指向 `passport.id`。

---

## 三、数据访问层（DAO，纯 EntityManager）

所有 DAO 均注入当前事务绑定的 `EntityManager`（JPA 标准 `@PersistenceContext`），不引入任何 Spring Data Repository 代理。

### 4.1 CountryDao —— EntityManager 生命周期 + 查询方式

**持久化生命周期**（对应测试 `entityLifecycle`）：

| 方法             | API               | 说明                  |
|----------------|-------------------|---------------------|
| `save`         | `em.persist`      | 新增，进入受管状态           |
| `findById`     | `em.find`         | 按主键查，命中一级缓存则直接返回    |
| `getReference` | `em.getReference` | 仅生成代理，访问属性才触发 SQL   |
| `update`       | `em.merge`        | 合并脱管实体改动回受管状态       |
| `remove`       | `em.remove`       | 删除受管实体              |
| `refresh`      | `em.refresh`      | 用库中最新的行覆盖实体内存状态     |
| `contains`     | `em.contains`     | 判断是否处于受管状态          |
| `detach`       | `em.detach`       | 移出持久化上下文，后续改动不再自动同步 |

**查询方式**：

| 方法                 | 方式           | 要点                                                              |
|--------------------|--------------|-----------------------------------------------------------------|
| `findByContinent`  | JPQL 命名参数    | `createQuery` + `setParameter`                                  |
| `pageByContinent`  | JPQL 分页      | `setFirstResult` / `setMaxResults`                              |
| `byContinentNamed` | 命名查询         | `createNamedQuery("Country.byContinent", ...)`                  |
| `countAll`         | 命名查询聚合       | `createNamedQuery("Country.count", Long.class)`                 |
| `nativeFindAll`    | 原生 SQL       | `createNativeQuery(..., Country.class)` 按列名映射                   |
| `updateContinent`  | JPQL 批量更新    | `executeUpdate()` 返回受影响行数                                       |
| `searchByCriteria` | Criteria API | `CriteriaBuilder` / `CriteriaQuery` / `Root` / `Predicate` 动态拼装 |

### 4.2 CountryLanguageDao —— 复合主键（@IdClass）

`em.find(CountryLanguage.class, new CountryLanguageId(code, language))` 以主键类查复合主键实体。

### 4.3 PersonDao —— 继承映射 + 并发控制

- 多态查询：`select p from Person p` 返回所有子类实例；`select m from Manager m` 只查具体子类（JPA 标准 `TYPE` 表达式语义）。
- `findByIdForUpdate`：`em.find(..., LockModeType.PESSIMISTIC_WRITE)` 悲观写锁。
- `updateStale`：`merge` 脱管实体后 `flush`，用于演示乐观锁冲突——版本过期时 `update ... where version=旧值` 匹配不到行，抛 `OptimisticLockException`。

### 4.4 RelationshipDao —— 关联映射 + 抓取策略

- 多对多维护端：`saveTeacher` 级联持久化；`findTeacherWithStudents` / `findStudentWithTeachers` 用 `JOIN FETCH` 预抓取集合。
- 一对一维护端：`saveAuthor` 保存作者并维护 `Passport.author` 双向一致性；`findAuthorWithPassport` 用 `JOIN FETCH` 预抓取护照。
- 一对多：`findCountryWithCities` 用 `JOIN FETCH` 一次取出城市集合（消除 N+1 与懒加载异常）；`findCountryLazy` 仅查国家不取城市（集合仍为懒代理）。
- 连接投影：`findCityJoinCountry` 用内连接 `join c.country co` 取城市名/人口/国家名，返回 `Object[]` 并 `setMaxResults(10)`。

---

## 四、实体映射要点

### 5.1 关联映射

| 关系                 | 实体                    | 关键注解                                                                                                                                                                                                          |
|--------------------|-----------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 一对多（维护于多端）         | `Country` ↔ `City`    | `@OneToMany(mappedBy="country", cascade=ALL, orphanRemoval=true, fetch=LAZY)` + `@OrderBy("population DESC")`；`City` 端 `@ManyToOne(fetch=LAZY)` + `@JoinColumn(name="CountryCode")`                           |
| 多对多（维护端 `Teacher`） | `Teacher` ↔ `Student` | `@ManyToMany(cascade=PERSIST)` + `@JoinTable(name="teacher_student", joinColumns=@JoinColumn("teacher_id"), inverseJoinColumns=@JoinColumn("student_id"))`；反向端 `Student` 用 `@ManyToMany(mappedBy="students")` |
| 一对一（维护端 `Author`）  | `Author` ↔ `Passport` | `Author` 持外键 `@OneToOne(fetch=LAZY, cascade=ALL)` + `@JoinColumn(name="passport_id")`；`Passport` 用 `@OneToOne(mappedBy="passport")` 反向映射                                                                      |

### 5.2 复合主键（@IdClass）

- `CountryLanguage` 声明 `@IdClass(CountryLanguageId.class)`，并把 `countryCode`、`language` 两字段分别标 `@Id`。
- 主键类 `CountryLanguageId` 要求：`public` 无参构造、实现 `Serializable`、正确重写 `equals`/`hashCode`。

### 5.3 继承映射（SINGLE_TABLE 单表）

- 父类 `Person`：`@Inheritance(SINGLE_TABLE)` + `@DiscriminatorColumn(name="DISC_TYPE", discriminatorType=STRING)` + `@Version`（乐观锁版本字段）。
- 子类 `Staff`（`@DiscriminatorValue("STAFF")`）：新增 `department` 列 + `@Embedded` 嵌入 `Address`。
- 子类 `Manager`（`@DiscriminatorValue("MANAGER")`）：新增 `team_size` 列。
- 子类专属列与嵌入地址列均落入同一张 `person` 表，靠 `DISC_TYPE` 区分。

### 5.4 可嵌入（@Embeddable + @Embedded）

- `Address` 标注 `@Embeddable`，字段作为宿主实体普通列存储，不单独建表。
- `Staff` 通过 `@Embedded` 嵌入，其字段落为 `person` 表的 `street` / `address_city` / `zip_code` 三列。

### 5.5 枚举转换（@Converter）

- `Continent` 枚举映射到 world 表 `country.Continent` 列。其 `code` 存储值与 world 库列取值一致（首字母大写，如 `Asia`、`North America`）；`ContinentConverter.convertToEntityAttribute` 用 `equalsIgnoreCase` 容错还原枚举。
- `ContinentConverter` 实现 `AttributeConverter<Continent, String>`，`@Converter(autoApply=false)` 仅对显式标 `@Convert` 的字段生效（见 `Country.continent`）。

### 5.6 命名查询（@NamedQuery）

- `Country` 上预定义两个命名查询：`Country.byContinent`（按大洲查并按人口倒序）、`Country.count`（计数）。
- 通过 `em.createNamedQuery(name, Class)` 复用，避免 HQL 字符串散落。

### 5.7 生命周期回调（@PrePersist）

- `City.prePersist()`：持久化前自动补齐默认值（`population` 为空则置 0）。

### 5.8 乐观锁（@Version） / 悲观锁

- **乐观锁**：`Person.version` 标注 `@Version`，每次 UPDATE 自动 +1 并做冲突校验（`OptimisticLockException`）。经 `PersistenceExceptionTranslationPostProcessor` 翻译为 Spring 的 `ObjectOptimisticLockingFailureException`。
- **悲观锁**：`em.find(Person.class, id, LockModeType.PESSIMISTIC_WRITE)` 对行加写锁。

---

## 五、Service 层（JpaService，@Transactional）

在 DAO 之上再包一层 `@Transactional` 服务，演示 Spring 声明式事务与 JPA 的关系：

- 事务边界由 `JpaTransactionManager` 管理（starter 自动装配），同一事务内共享同一持久化上下文（一级缓存），提交时才统一 flush。
- 读方法标 `@Transactional(readOnly=true)`；写方法标 `@Transactional`。

| 方法                        | 用途                              |
|---------------------------|---------------------------------|
| `saveCountryWithCities`   | 级联保存国家及城市（依赖 `CascadeType.ALL`） |
| `listByContinent`         | 按大洲 JPQL 查询                     |
| `findCountryWithCities`   | 国家 + 城市 `JOIN FETCH` 预抓取        |
| `saveAuthorWithPassport`  | 保存作者并级联保存护照                     |
| `saveTeacherWithStudents` | 保存教师及学生关联                       |
| `addStaff` / `addManager` | 新增员工（含可嵌入地址）/ 经理                |
| `listPersons`             | 多态查询                            |
| `updatePersonName`        | 乐观锁更新姓名                         |

`JpaRunner` 实现 `ApplicationRunner`，应用启动后自动执行 7 步典型 JPA 演示并把结果打印到控制台，便于直接观察效果：

1. **JPQL + 命名查询 + 聚合**：`listByContinent(ASIA)` 与 `countAll()`。
2. **Criteria 动态查询**：`searchByCriteria("C", ASIA, 5)`。
3. **原生 SQL**：`nativeFindAll()`。
4. **一对多 + JOIN FETCH**：`findCountryWithCities("CHN")` 取城市数。
5. **继承 + 可嵌入**：`addStaff` ×2、`addManager`，打印 `findAll()` 总数。
6. **乐观锁更新**：`updatePersonName` 后打印 `version`（应自增）。
7. **一对一**：`saveAuthorWithPassport` 保存作者+护照。

---

## 六、异常翻译（config/JpaConfig）

- `JpaConfig` 注册 `PersistenceExceptionTranslationPostProcessor`，把 JPA 的 `PersistenceException` 体系翻译成 Spring 统一的 `DataAccessException` 体系，便于上层按异常类型统一捕获。
- `EntityManagerFactory` / `JpaTransactionManager` 由 starter 自动装配，无需重复声明。
- 演示：对非受管实体 `remove` 抛 `IllegalStateException` 被翻译为 `InvalidDataAccessApiUsageException`。

---

## 六·补、序号 1–6 补充特性（纯 JPA）

`AdvancedDao` + `JpaTests` 中新增的分区补充演示六类 JPA 规范特性（对应参考教程的 Customer/Order/Customer2/Customer3 示例）：

### 序号 1：主键生成策略（TABLE / SEQUENCE / AUTO 对照）

| 实体 | 策略 | 关键注解 | 说明 |
|------|------|---------|------|
| `Customer` | `TABLE` | `@GeneratedValue(strategy=TABLE)` + `@TableGenerator(table="id_gen_table", pkColumnName="id_gen_name", valueColumnName="id_gen_value", pkColumnValue="customer_id", allocationSize=1)` | 主键值存于独立表 `id_gen_table`，`@TableGenerator` 每次 `SELECT ... FOR UPDATE` 取号并自增 |
| `Order` | `SEQUENCE` | `@GeneratedValue(strategy=SEQUENCE)` + `@SequenceGenerator(sequenceName="order_seq", allocationSize=1)` | 主键来自数据库序列 `order_seq`（H2 `CREATE SEQUENCE`） |
| `Customer2` / `Customer3` | `AUTO` | `@GeneratedValue(strategy=AUTO)` | 由方言自动选择实现；H2 下走序列（默认 `allocationSize=50`，故 schema 中序列步长须为 50） |

> 注意：`GenerationType.AUTO` 未显式声明 `@SequenceGenerator` 时 Hibernate 默认 `allocationSize=50`，若数据库序列步长不符会在启动报 `MappingException: The increment size ... is set to [50]`。故 `demo-schema.sql` 中 `customer2_SEQ`/`customer3_SEQ` 用 `INCREMENT BY 50`。

### 序号 2：字段映射注解（@Basic / @Enumerated / @Temporal）

`Customer` 集中演示：

- `@Basic`：显式声明普通字段（可选/懒加载）；`@Basic(fetch=LAZY)` 声明懒加载字段（`note`）。
- `@Enumerated(EnumType.STRING)`：库中存枚举名（`status`）；`@Enumerated(EnumType.ORDINAL)`：库中存下标（`status_ordinal`）。
- `@Temporal(TemporalType.DATE)`：仅日期（`birth_date`）；`@Temporal(TemporalType.TIMESTAMP)`：日期 + 时间（`created_at`）。

### 序号 3：JPQL 构造器投影

- 构造器投影：`select new org.ljh.jpa.projection.CustomerProjection(c.id, c.name) from Customer c`，返回强类型 DTO（`CustomerProjection` 无需 `@Entity`，提供与 select 列表一致的构造器即可）。
- `Object[]` 投影：`select c.id, c.name from Customer c` 返回 `List<Object[]>`。

### 序号 4：单向一对多（@OneToMany + @JoinColumn）

`Customer2`（1 方）→ `Order2`（n 方）：

- `@OneToMany(cascade = PERSIST)` + `@JoinColumn(name = "customer_id")`：外键列建在 n 方表 `order2`，但由 1 方维护，`persist` 后会额外发出 `UPDATE order2 SET customer_id = ?`（单向一对多「多余 UPDATE」现象）。
- n 方 `Order2` 不声明反向映射，纯单向。

### 序号 5：关联删除行为

- **删除被引用端触发外键约束异常**（`deleteReferencedEntityViolatesFk`）：`Order` 多对一引用 `Customer`，外键列 `NOT NULL`；删除被订单引用的 `Customer` 时 flush 触发外键约束异常。
- **删除 1 端时外键先置 null 再删**（`deleteOneSideNullsForeignKey`）：`Customer2` 单向一对多 `Order2`，外键列可空且未配 `orphanRemoval`；删除 `Customer2` 时 Hibernate 先 `UPDATE order2 SET customer_id = NULL` 再 DELETE 客户。

### 序号 6：实体二级缓存（规范层 @Cacheable）

- `Customer3` 标注 `@Cacheable`（`jakarta.persistence.Cacheable`，JPA 规范注解）。
- 开关由 `shared-cache-mode=ENABLE_SELECTIVE` 控制（见「二、配置」），实际存储由 Hibernate 二级缓存承载。
- 测试用两个独立 `EntityManager` 先后 `find` 同一实体，配合 `Statistics.getSecondLevelCacheHitCount()` 验证第二次命中二级缓存（不再查库）。

---

## 七、测试覆盖（JpaTests）

测试类 `org.ljh.jpa.JpaTests` 按主题分为六大分区（与 `HibernateTests` / `SpringDataJpaTests` 同构）：

1. **一、实体映射与生命周期**
   - EntityManager 生命周期：`contextLoads` / `entityLifecycle`（find、persist、detach、merge、remove）/ `getReferenceLazyProxy`。
   - 主键生成策略：`tableGenerator`（TABLE + @TableGenerator）/ `sequenceGenerator`（SEQUENCE）/ `autoGenerator`（AUTO）。
   - 字段映射注解：`fieldMapping`（@Basic / @Enumerated STRING+ORDINAL / @Temporal DATE+TIMESTAMP）。
2. **二、查询方式**
   - JPQL + 分页 + 命名查询 + 聚合（`jpqlQueries`）、原生 SQL（`nativeSql`）、Criteria（`criteriaQuery`）。
   - 结果投影：`constructorProjection`（构造器投影）/ `objectArrayProjection`（Object[] 投影）。
3. **三、关联映射**
   - 复合主键：`compositeKey`（@IdClass 增删改查）。
   - 一对多：`oneToManyCascadeAndLazy`（双向级联 + JOIN FETCH）、`oneToManyPersist`（单向 @OneToMany + @JoinColumn）。
   - 多对多：`manyToMany`（级联持久化 + 显式 flush + 双向反查）。
   - 一对一：`oneToOne`（级联保存护照 + JOIN FETCH 预抓取）。
   - 继承 + 可嵌入：`inheritanceAndEmbeddable`（多态查询、TYPE 表达式、子类专属字段更新）。
   - 关联删除行为：`deleteReferencedEntityViolatesFk`（删除被引用端触发外键约束异常）/ `deleteOneSideNullsForeignKey`（删除 1 端外键先置 null 再删）。
4. **四、缓存体系**
   - 实体二级缓存：`secondLevelCache`（@Cacheable + shared-cache-mode 命中验证）。
5. **五、并发控制**
   - 乐观锁：`optimisticLockNormal`（版本自动递增）、`optimisticLockConflict`（过期副本 merge 触发冲突）；悲观锁：`pessimisticLock`。
6. **六、事务与异常翻译**
   - `exceptionTranslation`（JPA 异常翻译为 Spring 异常）。

---

## 八、运行

```bash
# 进入模块目录
cd spring-boot/spring-boot-data/spring-boot-jpa

# 运行测试（内存 H2，SQL 自动初始化 world + demo 表）
mvn test

# 启动应用（JpaRunner 自动打印演示结果）
mvn spring-boot:run
```

> 依赖说明：模块依赖内部模块 `commons`（提供 world 库 SQL），构建前需先安装父模块及 `commons` 模块（`mvn install`）或使用 Maven 多模块聚合构建。
