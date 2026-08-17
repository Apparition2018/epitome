package org.ljh.jpa;

import com.ljh.commons.Continent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.Test;
import org.ljh.jpa.dao.*;
import org.ljh.jpa.entity.*;
import org.ljh.jpa.projection.CustomerProjection;
import org.ljh.jpa.service.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 纯 JPA（EntityManager）演示测试
 * <p>不使用 Spring Data 的 Repository 抽象，数据访问完全基于标准 {@code EntityManager} /
 * {@code EntityManagerFactory}（{@code jakarta.persistence.*}）。按主题组织，涵盖以下主题：</p>
 * <ol>
 *     <li><b>实体映射与生命周期</b>：EntityManager 生命周期、懒代理、主键生成策略（TABLE / SEQUENCE / AUTO）、
 *         字段映射注解（{@code @Basic} / {@code @Enumerated} / {@code @Temporal}）</li>
 *     <li><b>查询方式</b>：JPQL（分页 / 命名查询 / 聚合）、原生 SQL、Criteria、构造器投影 / {@code Object[]} 投影</li>
 *     <li><b>关联映射</b>：复合主键（{@code @IdClass}）、一对多（双向 / 单向）、多对多、一对一、
 *         继承 + 可嵌入、关联删除行为</li>
 *     <li><b>缓存体系</b>：实体二级缓存（规范层 {@code @Cacheable} + {@code shared-cache-mode}）</li>
 *     <li><b>并发控制</b>：乐观锁 {@code @Version} / 悲观锁</li>
 *     <li><b>事务与异常翻译</b>：JPA 异常翻译为 Spring {@code DataAccessException}</li>
 * </ol>
 *
 * @author ljh
 * @since 2026/8/11
 */
@SpringBootTest(classes = JpaApplication.class)
class JpaTests {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AdvancedDao advancedDao;

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private CountryLanguageDao countryLanguageDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private RelationshipDao relationshipDao;

    @Autowired
    private JpaService demoService;

    /** 手动管理事务，用于演示乐观锁并发冲突 */
    @Autowired
    private PlatformTransactionManager txManager;

    // ==================== 一、实体映射与生命周期 ====================

    // --- 1.1 EntityManager 注入与实体生命周期 ---

    /** 容器注入 EntityManager / EntityManagerFactory，验证 JPA 标准 SPI 可用 */
    @Test
    void contextLoads() {
        assertThat(entityManagerFactory).isNotNull();
        assertThat(entityManager).isNotNull();
    }

    /** find / getReference / persist / contains / detach / merge 生命周期 */
    @Test
    @Transactional
    void entityLifecycle() {
        // persist：新增进入受管状态
        Country c = new Country();
        c.setCode("XX1");
        c.setName("TestLand");
        c.setContinent(Continent.ASIA);
        c.setRegion("TestRegion");
        c.setPopulation(1);
        c.setSurfaceArea(new BigDecimal("100.00"));
        countryDao.save(c);

        // find：查回同一受管实例
        Country found = countryDao.findById("XX1");
        assertThat(found).isNotNull();
        assertThat(countryDao.contains(found)).isTrue();

        // detach：脱管后不再被跟踪
        countryDao.detach(found);
        assertThat(countryDao.contains(found)).isFalse();

        // merge：用一个全新实例合并更新（模拟来自别处的脱管实体）
        Country fresh = new Country();
        fresh.setCode("XX1");
        fresh.setName("TestLand");
        fresh.setContinent(Continent.ASIA);
        fresh.setRegion("NewRegion");
        fresh.setPopulation(1);
        fresh.setSurfaceArea(new BigDecimal("100.00"));
        Country merged = countryDao.update(fresh);
        assertThat(merged).isNotNull();
        assertThat(merged.getRegion()).isEqualTo("NewRegion");
        assertThat(countryDao.contains(merged)).isTrue();

        // remove：删除受管实体，clear 后再查验证主键已不存在
        countryDao.remove(merged);
        entityManager.clear();
        assertThat(countryDao.findById("XX1")).isNull();
    }

    /** getReference：仅返回代理不触发 SQL，访问属性时才初始化 */
    @Test
    @Transactional
    void getReferenceLazyProxy() {
        Country ref = countryDao.getReference("CHN");
        assertThat(ref.getCode()).isEqualTo("CHN");
    }

    // --- 1.2 主键生成策略（TABLE / SEQUENCE / AUTO 对照） ---

    /**
     * Customer 用 TABLE 策略：主键值来自 {@code id_gen_table}，自增值 = 种子值 + allocationSize。
     * <p>@TableGenerator 访问 {@code id_gen_table} 时会对其加 {@code FOR UPDATE} 行锁，
     * 故用独立的 EntityManager 分两步提交：先写入种子，再 persist，避免同事务内自锁。</p>
     */
    @Test
    void tableGenerator() {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            // 第一步：写入种子（ON DUPLICATE KEY UPDATE 幂等，可多次运行）
            // 注：H2 配了 MODE=MySQL，MySQL 模式不识别 H2 原生 MERGE 语法，
            // 需用 MySQL 兼容的 INSERT ... ON DUPLICATE KEY UPDATE
            em.getTransaction().begin();
            em.createNativeQuery(
                            "insert into id_gen_table(id_gen_name, id_gen_value) "
                                    + "values ('customer_id', 1000) "
                                    + "on duplicate key update id_gen_value = 1000")
                    .executeUpdate();
            em.getTransaction().commit();

            // 第二步：persist，TABLE 策略从 id_gen_table 取号（1000 + 1 = 1001）
            em.getTransaction().begin();
            Customer c = new Customer();
            c.setName("TableGen");
            em.persist(c);
            em.getTransaction().commit();

            // allocationSize=1 → 主键 = 种子值 + 1
            assertThat(c.getId()).isNotNull();
            assertThat(c.getId()).isEqualTo(1001L);
        }
    }

    /** Order 用 SEQUENCE 策略：主键来自序列 order_seq */
    @Test
    @Transactional
    void sequenceGenerator() {
        Order o = new Order();
        o.setTotal(new BigDecimal("99.90"));
        advancedDao.saveOrder(o);
        assertThat(o.getId()).isNotNull();

        Order o2 = new Order();
        o2.setTotal(new BigDecimal("9.90"));
        advancedDao.saveOrder(o2);
        assertThat(o2.getId()).isGreaterThan(o.getId()); // 序列递增
    }

    /** Customer2 用 AUTO 策略：H2 方言下由 Hibernate 自动选择实现（序列） */
    @Test
    @Transactional
    void autoGenerator() {
        Customer2 c = new Customer2();
        c.setName("AutoGen");
        advancedDao.saveCustomer2(c);
        assertThat(c.getId()).isNotNull();
    }

    // --- 1.3 字段映射注解（@Basic / @Enumerated / @Temporal） ---

    /** {@code @Enumerated(STRING)} 存枚举名、{@code @Enumerated(ORDINAL)} 存下标；{@code @Temporal(DATE/TIMESTAMP)} 精度各异 */
    @Test
    @Transactional
    void fieldMapping() {
        Customer c = new Customer();
        c.setName("Mapping");
        c.setStatus(CustomerStatus.ACTIVE);
        c.setStatusOrdinal(CustomerStatus.INACTIVE);
        c.setBirthDate(new Date());
        c.setCreatedAt(new Date());
        advancedDao.saveCustomer(c);
        // flush + clear 强制从库重新读取，验证各映射还原
        entityManager.flush();
        entityManager.clear();

        Customer found = advancedDao.findCustomer(c.getId());
        assertThat(found).isNotNull();
        // STRING：库值即枚举名
        assertThat(found.getStatus()).isEqualTo(CustomerStatus.ACTIVE);
        // ORDINAL：库值为下标，读回仍映射为对应枚举
        assertThat(found.getStatusOrdinal()).isEqualTo(CustomerStatus.INACTIVE);
        // @Temporal 还原为日期/时间
        assertThat(found.getBirthDate()).isNotNull();
        assertThat(found.getCreatedAt()).isNotNull();
    }

    // ==================== 二、查询方式 ====================

    // --- 2.1 JPQL / 原生 SQL / Criteria ---

    /** JPQL + 分页 + 命名查询 + 聚合 */
    @Test
    void jpqlQueries() {
        // JPQL 命名参数
        List<Country> asia = countryDao.findByContinent(Continent.ASIA);
        assertThat(asia).isNotEmpty();

        // 分页
        List<Country> page = countryDao.pageByContinent(Continent.ASIA, 0, 3);
        assertThat(page).hasSizeLessThanOrEqualTo(3);

        // 命名查询
        assertThat(countryDao.byContinentNamed(Continent.ASIA)).hasSameSizeAs(asia);

        // 聚合
        assertThat(countryDao.countAll()).isGreaterThan(0);
    }

    /** 原生 SQL 映射 */
    @Test
    void nativeSql() {
        List<Country> list = countryDao.nativeFindAll();
        assertThat(list).isNotEmpty();
        assertThat(list).allMatch(c -> c.getContinent() == Continent.ASIA);
    }

    /** Criteria API 动态查询 */
    @Test
    void criteriaQuery() {
        List<Country> list = countryDao.searchByCriteria("C", Continent.ASIA, 5);
        assertThat(list).isNotEmpty();
        assertThat(list).allMatch(c -> c.getCode().startsWith("C"));
    }

    // --- 2.2 结果投影（构造器 / Object[]） ---

    /** 构造器投影：SELECT new ...CustomerProjection(id, name)，返回强类型 DTO */
    @Test
    @Transactional
    void constructorProjection() {
        Customer c = new Customer();
        c.setName("Proj");
        advancedDao.saveCustomer(c);

        List<CustomerProjection> list = advancedDao.projectConstructor();
        assertThat(list).isNotEmpty();
        assertThat(list).allSatisfy(p -> {
            assertThat(p.getId()).isNotNull();
            assertThat(p.getName()).isNotBlank();
        });
    }

    /** Object[] 投影：SELECT c.id, c.name 返回 Object[] */
    @Test
    @Transactional
    void objectArrayProjection() {
        Customer c = new Customer();
        c.setName("ArrProj");
        advancedDao.saveCustomer(c);

        List<Object[]> rows = advancedDao.projectObjectArray();
        assertThat(rows).isNotEmpty();
        assertThat(rows.get(0)).hasSize(2); // [id, name]
    }

    // ==================== 三、关联映射 ====================

    // --- 3.1 复合主键（@IdClass） ---

    @Test
    @Transactional
    void compositeKey() {
        CountryLanguage cl = new CountryLanguage();
        cl.setCountryCode("XX2");
        cl.setLanguage("Klingon");
        cl.setIsOfficial("T");
        cl.setPercentage(new BigDecimal("50.0"));
        countryLanguageDao.save(cl);

        CountryLanguage found = countryLanguageDao.findById("XX2", "Klingon");
        assertThat(found).isNotNull();
        assertThat(found.getPercentage()).isEqualByComparingTo("50.0");

        countryLanguageDao.remove(countryLanguageDao.findById("XX2", "Klingon"));
        assertThat(countryLanguageDao.findById("XX2", "Klingon")).isNull();
    }

    // --- 3.2 一对多（双向级联 / 单向） ---

    /** 双向一对多 + JOIN FETCH 预抓取集合（消除懒加载异常） */
    @Test
    @Transactional
    void oneToManyCascadeAndLazy() {
        Country c = new Country();
        c.setCode("XX3");
        c.setName("Chain");
        c.setContinent(Continent.EUROPE);
        c.setRegion("Europe");
        c.setSurfaceArea(new BigDecimal("200.00"));
        c.setPopulation(1000000);

        City city = new City();
        city.setName("Metro");
        city.setDistrict("District1");
        c.addCity(city);                 // 维护反向引用

        demoService.saveCountryWithCities(c);

        // JOIN FETCH：事务内一次性取出城市集合
        Country loaded = demoService.findCountryWithCities("XX3");
        assertThat(loaded.getCities()).hasSize(1);
        assertThat(loaded.getCities().get(0).getName()).isEqualTo("Metro");
    }

    /** 单向一对多：persist 客户 + 子项，外键由 1 方维护，可正常查询到子项 */
    @Test
    @Transactional
    void oneToManyPersist() {
        Customer2 c = new Customer2();
        c.setName("UniCustomer");

        Order2 item1 = new Order2();
        item1.setName("Item-A");
        Order2 item2 = new Order2();
        item2.setName("Item-B");
        c.addOrderItem(item1);
        c.addOrderItem(item2);

        advancedDao.saveCustomer2WithItems(c);
        // 单向一对多：@JoinColumn 由 1 方维护，flush 时会发出维护外键的 UPDATE
        entityManager.flush();
        entityManager.clear();

        Customer2 loaded = advancedDao.findCustomer2WithItems(c.getId());
        assertThat(loaded).isNotNull();
        assertThat(loaded.getOrderItems()).hasSize(2);
    }

    // --- 3.3 多对多 / 一对一 ---

    @Test
    @Transactional
    void manyToMany() {
        Student s1 = new Student();
        s1.setName("Stu1");
        Student s2 = new Student();
        s2.setName("Stu2");

        Teacher t = new Teacher();
        t.setName("T1");
        t.getStudents().add(s1);
        t.getStudents().add(s2);
        demoService.saveTeacherWithStudents(t);
        // 显式 flush：级联持久化的学生需要 flush 才回填 ID、写入中间表
        entityManager.flush();
        entityManager.clear();

        // 维护端：JOIN FETCH 一次取出学生集合
        Teacher loaded = relationshipDao.findTeacherWithStudents(t.getId());
        assertThat(loaded.getStudents()).hasSize(2);

        // 反向端：学生反查其教师
        Student back = relationshipDao.findStudentWithTeachers(s1.getId());
        assertThat(back.getTeachers()).anyMatch(x -> x.getId().equals(t.getId()));
    }

    @Test
    void oneToOne() {
        Author author = new Author();
        author.setName("Luo");
        Passport passport = new Passport();
        passport.setNumber("G888888");
        author.setPassport(passport);
        demoService.saveAuthorWithPassport(author);

        Author loaded = relationshipDao.findAuthorWithPassport(author.getId());
        assertThat(loaded.getPassport().getNumber()).isEqualTo("G888888");
    }

    // --- 3.4 继承 + 可嵌入 ---

    @Test
    @Transactional
    void inheritanceAndEmbeddable() {
        Long staffId = demoService.addStaff("Dave", "HR",
                new Address("Rd.9", "GZ", "510000"));
        Long managerId = demoService.addManager("Eve", 5);

        Staff staff = entityManager.find(Staff.class, staffId);
        assertThat(staff).isNotNull();
        assertThat(staff.getAddress().getCity()).isEqualTo("GZ");

        Manager manager = entityManager.find(Manager.class, managerId);
        assertThat(manager.getTeamSize()).isEqualTo(5);

        // 多态查询：父类查询命中所有子类
        List<Person> all = personDao.findAll();
        assertThat(all).anyMatch(p -> p.getId().equals(staffId));
        assertThat(all).anyMatch(p -> p.getId().equals(managerId));

        // 只查具体子类（TYPE 表达式）
        assertThat(personDao.findManagers())
                .anyMatch(m -> m.getId().equals(managerId));

        // 子类专属字段更新
        personDao.updateDepartment(staffId, "Finance");
        entityManager.flush();
        assertThat(entityManager.find(Staff.class, staffId).getDepartment()).isEqualTo("Finance");
    }

    // --- 3.5 关联删除行为 ---

    /**
     * 删除被引用端（Customer 被 Order 多对一引用，外键列 NOT NULL）：触发外键约束异常。
     * 演示 {@code @ManyToOne} 下删除被引用实体的行为。
     */
    @Test
    void deleteReferencedEntityViolatesFk() {
        // 用独立 EntityManager + 显式 flush 精确控制事务边界，便于观察约束异常
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            Customer c = new Customer();
            c.setName("FkViolate");
            em.persist(c);
            em.flush(); // INSERT customer
            assertThat(c.getId()).isNotNull();

            // 订单多对一引用客户，外键列 customer_id NOT NULL
            Order o = new Order();
            o.setTotal(new BigDecimal("1.00"));
            o.setCustomer(c);
            em.persist(o);
            em.flush(); // INSERT orders
            em.getTransaction().commit();
            em.clear(); // 清空持久化上下文，避免删除时 Hibernate 检测到 Order 的 transient 引用

            // 删除被订单引用的客户：orders.customer_id 仍有引用且 NOT NULL，flush 时触发外键约束异常
            em.getTransaction().begin();
            Customer managed = em.find(Customer.class, c.getId());
            assertThat(managed).isNotNull();
            em.remove(managed);
            assertThatThrownBy(em::flush).isInstanceOf(PersistenceException.class);
            em.getTransaction().rollback();
        }
    }

    /**
     * 删除单向一对多的「1」端（Customer2）：外键列可空且未配 orphanRemoval，
     * Hibernate 先 {@code UPDATE order2 SET customer_id = NULL} 再删除客户。
     */
    @Test
    @Transactional
    void deleteOneSideNullsForeignKey() {
        Customer2 c = new Customer2();
        c.setName("NullFk");
        Order2 item = new Order2();
        item.setName("Item-Null");
        c.addOrderItem(item);
        advancedDao.saveCustomer2WithItems(c);
        entityManager.flush();
        entityManager.clear();

        // 删除一对多的「1」端：先置 null 子表外键再 DELETE 客户
        Customer2 managed = advancedDao.findCustomer2WithItems(c.getId());
        assertThat(managed).isNotNull();
        Long itemId = managed.getOrderItems().get(0).getId();
        advancedDao.removeCustomer2(managed);
        entityManager.flush();

        // 客户已删除，子项仍在（外键已被置为 null）
        assertThat(advancedDao.findCustomer2WithItems(c.getId())).isNull();
        assertThat(advancedDao.findOrder2(itemId)).isNotNull();
    }

    // ==================== 四、缓存体系 ====================

    // --- 4.1 实体二级缓存（规范层 @Cacheable + shared-cache-mode） ---

    /**
     * 二级缓存：两个独立事务（独立 EntityManager）先后 find 同一 Customer3，
     * 第二次应命中二级缓存而不再查库（用 Hibernate Statistics 的命中计数验证）。
     */
    @Test
    void secondLevelCache() {
        // 先造一个 Customer3 并提交
        Customer3 seed = new Customer3();
        seed.setName("CacheSeed");
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(seed);
            em.getTransaction().commit();
        }
        Long id = seed.getId();

        // 第一个事务加载（写入二级缓存）
        try (EntityManager em1 = entityManagerFactory.createEntityManager()) {
            em1.getTransaction().begin();
            Customer3 c1 = em1.find(Customer3.class, id);
            assertThat(c1.getName()).isEqualTo("CacheSeed");
            em1.getTransaction().commit();
        }

        Statistics stats = entityManagerFactory.unwrap(SessionFactory.class).getStatistics();
        long hitsBefore = stats.getSecondLevelCacheHitCount();

        // 第二个事务再加载：应命中二级缓存（命中计数 +1），而非重新查库
        try (EntityManager em2 = entityManagerFactory.createEntityManager()) {
            em2.getTransaction().begin();
            Customer3 c2 = em2.find(Customer3.class, id);
            assertThat(c2.getName()).isEqualTo("CacheSeed");
            em2.getTransaction().commit();
        }
        assertThat(stats.getSecondLevelCacheHitCount()).isGreaterThan(hitsBefore);
    }

    // ==================== 五、并发控制 ====================

    // --- 5.1 乐观锁 @Version ---

    /** 乐观锁正常更新：@Version 版本号自动递增 */
    @Test
    @Transactional
    void optimisticLockNormal() {
        Long id = demoService.addStaff("Sam", "IT", new Address("R1", "C1", "000000"));
        Person before = entityManager.find(Person.class, id);
        Long beforeVersion = before.getVersion();

        demoService.updatePersonName(id, "Sam v2");
        entityManager.flush();
        entityManager.clear();

        Person after = entityManager.find(Person.class, id);
        assertThat(after.getName()).isEqualTo("Sam v2");
        assertThat(after.getVersion()).isGreaterThan(beforeVersion);
    }

    /**
     * 乐观锁冲突：两个独立事务先后修改同一实体，后提交者因版本过期抛
     * {@code OptimisticLockException}。
     * <p>用 {@code TransactionTemplate} 手动控制三个事务：
     * TX1 创建并取回脱管副本；TX2 修改同名员工使版本号递增；TX3 把过期副本
     * merge 回去，flush 时版本校验失败。</p>
     */
    @Test
    void optimisticLockConflict() {
        TransactionTemplate tx = new TransactionTemplate(txManager);

        // TX1：创建一个员工，返回其脱管副本（携带当时的 version）
        Staff stale = tx.execute(status -> {
            Staff s = new Staff();
            s.setName("Old");
            s.setDepartment("IT");
            s.setAddress(new Address("R1", "C1", "000000"));
            personDao.saveStaff(s);
            entityManager.flush();
            // 脱管后不再与当前持久化上下文关联，作为过期副本返回
            Staff det = entityManager.find(Staff.class, s.getId());
            entityManager.detach(det);
            return det;
        });
        assertThat(stale).isNotNull();

        // TX2：另一事务修改同一员工 → 版本号被递增
        tx.executeWithoutResult(status -> {
            Staff fresh = entityManager.find(Staff.class, stale.getId());
            fresh.setDepartment("R&D");
        });

        // TX3：用 TX1 的过期副本做 merge → 版本校验失败
        // 底层抛 JPA 的 OptimisticLockException，经 PersistenceExceptionTranslationPostProcessor
        // 翻译为 Spring 的 ObjectOptimisticLockingFailureException（体现异常翻译机制生效）
        assertThatThrownBy(() -> tx.executeWithoutResult(status -> personDao.updateStale(stale)))
                .isInstanceOf(ObjectOptimisticLockingFailureException.class);
    }

    // --- 5.2 悲观锁 ---

    /** 悲观锁：PESSIMISTIC_WRITE 查询（基于 person 表） */
    @Test
    @Transactional
    void pessimisticLock() {
        Long id = demoService.addStaff("Frank", "Ops", new Address("R2", "C2", "100000"));
        Person locked = personDao.findByIdForUpdate(id);
        assertThat(locked).isNotNull();
        assertThat(locked.getName()).isEqualTo("Frank");
    }

    // ==================== 六、事务与异常翻译 ====================

    /** JPA 异常经 PersistenceExceptionTranslationPostProcessor 翻译为 Spring 异常 */
    @Test
    void exceptionTranslation() {
        // 对一个非受管实体 remove 会触发 IllegalStateException 翻译
        Country detached = new Country();
        detached.setCode("NONE");
        assertThatThrownBy(() -> countryDao.remove(detached))
                .isInstanceOf(InvalidDataAccessApiUsageException.class);
    }
}
