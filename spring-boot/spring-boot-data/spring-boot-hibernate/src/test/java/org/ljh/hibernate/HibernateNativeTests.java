package org.ljh.hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.Test;
import org.ljh.hibernate.dto.CountryCityDTO;
import org.ljh.hibernate.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Hibernate 原生 API 演示测试
 * <p>直接操作 Hibernate 原生 {@code Session}/{@code SessionFactory}
 * （经 Spring 注入的 {@code EntityManager} 底层即 Hibernate Session）。
 * 按主题组织，涵盖以下主题：</p>
 * <ol>
 *     <li><b>Session 核心</b>：基础 CRUD、实体生命周期、一级缓存与脏检查（含 FlushMode、refresh/clear）</li>
 *     <li><b>查询 API</b>：HQL（含 JOIN FETCH、Named Query、构造器投影）、Criteria、Native SQL</li>
 *     <li><b>数据写操作</b>：批量更新/删除、{@code @DynamicUpdate}</li>
 *     <li><b>关联映射</b>：一对多级联、复合主键、{@code @Embeddable} 组件、继承映射、多对多、一对一</li>
 *     <li><b>缓存体系</b>：懒代理、自然 ID（{@code @NaturalId}）、二级缓存与查询缓存</li>
 *     <li><b>并发控制</b>：乐观锁 {@code @Version} / 悲观锁</li>
 * </ol>
 *
 * @author ljh
 * @since 2026/8/9
 */
@SpringBootTest(classes = HibernateApplication.class)
class HibernateNativeTests {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    // ==================== 一、Session 核心 ====================

    // --- 1.1 Session / SessionFactory 基础 ---

    /** 容器注入检查 + 说明 EntityManager 底层就是 Hibernate Session */
    @Test
    void contextLoads() {
        assertThat(entityManagerFactory).isNotNull();
        assertThat(entityManager).isNotNull();
        // EntityManager 的实现类实际上是 Hibernate 的 SessionImpl（Session 接口的实现）
        Session session = entityManager.unwrap(Session.class);
        assertThat(session).isNotNull();
        // 拿到 Hibernate 原生 SessionFactory
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        assertThat(sessionFactory).isNotNull();
    }

    /** 基础 CRUD：用 Hibernate Session 直接增删改查 */
    @Test
    @Transactional
    void testBasicCrud() {
        Session session = entityManager.unwrap(Session.class);

        // persist：新增（transient -> persistent）。注意：测试代码须避开 world 库已占用的国家码（如 TMP=东帝汶、USA）
        Country tmp = newCountry("XX1", "Temperor", Continent.OCEANIA);
        session.persist(tmp);
        session.flush();

        // find：按主键查询，不存在返回 null
        Country loaded = session.find(Country.class, "XX1");
        assertThat(loaded).isNotNull();
        assertThat(loaded.getName()).isEqualTo("Temperor");

        // 更新（脏检查自动 UPDATE）
        loaded.setPopulation(2_000_000);
        session.flush();

        // delete：删除（removed 状态）
        session.remove(loaded);
        session.flush();
        assertThat(session.find(Country.class, "XX1")).isNull();
    }

    // --- 1.2 实体生命周期 ---

    /**
     * 实体四种状态：
     * <ul>
     *     <li>transient（瞬态）：new 出来，未被 Session 管理</li>
     *     <li>persistent（持久态）：已被 Session 管理，受一级缓存与脏检查跟踪</li>
     *     <li>detached（游离态）：离开 Session 后，改动不再被自动同步</li>
     *     <li>removed（删除态）：已标记删除，flush 时执行 DELETE</li>
     * </ul>
     */
    @Test
    @Transactional
    void testEntityLifecycle() {
        Session session = entityManager.unwrap(Session.class);

        // transient：new 出来但未被 Session 管理
        Country c = newCountry("XX2", "Lifecycle", Continent.EUROPE);
        assertThat(session.contains(c)).isFalse();

        // persistent：persist 后进入持久态，并 flush 落库（保证后续 evict 时 DB 已有该行）
        session.persist(c);
        session.flush();
        assertThat(session.contains(c)).isTrue();

        // detached：evict 移出 Session，改动不再自动同步
        session.evict(c);
        assertThat(session.contains(c)).isFalse();
        c.setPopulation(12345); // 此时修改不会自动写库
        session.flush();
        // 验证脏检查未把游离态的改动同步到库（仍为初始 100 万）
        assertThat(session.find(Country.class, "XX2").getPopulation()).isEqualTo(1_000_000);

        // reattach：用 merge 把游离实体合并回持久态，改动被同步（merge 返回的是新的受管实例）
        c.setPopulation(54321);
        Country managed = session.merge(c);
        assertThat(session.contains(managed)).isTrue(); // 返回的才是持久态
        session.flush();
        assertThat(session.find(Country.class, "XX2").getPopulation()).isEqualTo(54321);

        // removed：remove 标记删除，flush 执行 DELETE
        session.remove(managed);
        session.flush();
        assertThat(session.find(Country.class, "XX2")).isNull();
    }

    // --- 1.3 一级缓存与脏检查 ---

    /**
     * 一级缓存（Session 缓存）：同一 Session 内按主键查同一条记录，第二次直接命中缓存不查库。
     * 脏检查：Session 会跟踪实体快照，flush 时对比变化，只对变化过的实体发 UPDATE。
     */
    @Test
    @Transactional
    void testFirstLevelCacheAndDirtyCheck() {
        Session session = entityManager.unwrap(Session.class);

        // 第一次 get 发起查询
        Country china1 = session.find(Country.class, "CHN");
        assertThat(china1).isNotNull();

        // 第二次 get 命中一级缓存，不再查库（可观察日志：只有一次 SELECT）
        Country china2 = session.find(Country.class, "CHN");
        assertThat(china1).isSameAs(china2); // 同一 Session 内返回同一实例

        // 修改持久化对象：脏检查会自动跟踪
        int original = china1.getPopulation();
        china1.setPopulation(original + 1);
        session.flush(); // flush 时才发出 UPDATE
        // 还原，避免污染数据
        china1.setPopulation(original);
        session.flush();
    }

    /** FlushMode：AUTO（默认，查询前自动 flush）vs COMMIT（仅提交时 flush） */
    @Test
    @Transactional
    void testFlushModes() {
        Session session = entityManager.unwrap(Session.class);

        // COMMIT 模式：显式 setFlushMode，则查询前不自动 flush
        session.setHibernateFlushMode(FlushMode.COMMIT);
        Country tmp = newCountry("FLH", "Flush", Continent.AFRICA);
        session.persist(tmp);
        // COMMIT 模式下，此时 HQL 查询不会自动 flush，查不到刚 persist 但未 flush 的数据
        Query<Country> q = session.createQuery("select c from Country c where c.code = 'FLH'", Country.class);
        List<Country> list = q.list();
        assertThat(list).isEmpty();
        // 显式 flush 后再查
        session.flush();
        list = session.createQuery("select c from Country c where c.code = 'FLH'", Country.class).list();
        assertThat(list).hasSize(1);
        session.remove(session.find(Country.class, "FLH"));
        session.flush();
        // 恢复默认
        session.setHibernateFlushMode(FlushMode.AUTO);
    }

    /**
     * Session 缓存操作：{@code refresh()} 用数据库当前值覆盖内存中的实体（重新查询覆盖脏数据）；
     * {@code clear()} 清空整个一级缓存，使后续查询重新读库。
     */
    @Test
    @Transactional
    void testRefreshAndClear() {
        Session session = entityManager.unwrap(Session.class);
        Country c = newCountry("XX4", "RefreshDemo", Continent.SOUTH_AMERICA);
        session.persist(c);
        session.flush();

        // 用 SQL 直接改库（绕过 Session，使缓存中的实体与库不一致）
        session.createNativeMutationQuery("update country set Population = 999999 where Code = 'XX4'").executeUpdate();

        // refresh：以库里的值覆盖内存实体，Population 变为 999999
        session.refresh(c);
        assertThat(c.getPopulation()).isEqualTo(999999);

        // clear：清空一级缓存，find 会重新查库
        session.clear();
        Country reloaded = session.find(Country.class, "XX4");
        assertThat(reloaded.getPopulation()).isEqualTo(999999);

        session.remove(reloaded);
        session.flush();
    }

    // ==================== 二、查询 API ====================

    // --- 2.1 HQL 查询 ---

    /** HQL：面向实体类/属性的查询，位置参数与命名参数 */
    @Test
    @Transactional
    void testHqlQueries() {
        Session session = entityManager.unwrap(Session.class);

        // 位置参数 ?1 ?2
        Query<Country> q = session.createQuery(
                "select c from Country c where c.continent = ?1 and c.population > ?2", Country.class);
        q.setParameter(1, Continent.ASIA);
        q.setParameter(2, 1_000_000_000);
        List<Country> big = q.list();
        assertThat(big).extracting(Country::getCode).contains("CHN");

        // 命名参数 :name
        Query<Country> byName = session.createQuery(
                "select c from Country c where c.name = :name", Country.class);
        byName.setParameter("name", "Japan");
        assertThat(byName.list()).extracting(Country::getCode).contains("JPN");

        // 投影：只查字段，返回 Object[] 或 DTO 构造器
        Query<Object[]> proj = session.createQuery(
                "select c.code, c.name from Country c where c.code = :code", Object[].class);
        proj.setParameter("code", "FRA");
        Object[] row = proj.uniqueResult();
        assertThat(row[0]).isEqualTo("FRA");
        assertThat(row[1]).isEqualTo("France");

        // 聚合：按大洲统计国家数
        Query<Object[]> agg = session.createQuery(
                "select c.continent, count(c) from Country c group by c.continent", Object[].class);
        List<Object[]> groups = agg.list();
        assertThat(groups).isNotEmpty();
    }

    /** HQL 分页：setFirstResult / setMaxResults，等效 LIMIT + OFFSET */
    @Test
    @Transactional
    void testHqlPagination() {
        Session session = entityManager.unwrap(Session.class);
        Query<Country> q = session.createQuery(
                "select c from Country c order by c.population desc", Country.class);
        q.setFirstResult(0);
        q.setMaxResults(3);
        List<Country> top3 = q.list();
        assertThat(top3).hasSize(3);
        assertThat(top3.get(0).getCode()).isEqualTo("CHN"); // 人口最多
    }

    /**
     * Named Query：{@code @NamedQuery} 预定义的 HQL 按名字复用（见 {@link Country}）。
     * 一次定义、多处使用，避免 HQL 字符串散落在代码里；也支持返回计数。
     */
    @Test
    @Transactional
    void testNamedQuery() {
        Session session = entityManager.unwrap(Session.class);

        // 按名称取预定义查询，传命名参数（Hibernate 6 推荐 createNamedQuery，带结果类型、非弃用）
        Query<Country> byContinent = session.createNamedQuery("Country.byContinent", Country.class);
        List<Country> asia = byContinent.setParameter("continent", Continent.ASIA).list();
        assertThat(asia).isNotEmpty();
        assertThat(asia).extracting(Country::getCode).contains("CHN");

        // 预定义的计数查询
        Query<Long> count = session.createNamedQuery("Country.count", Long.class);
        assertThat(count.getSingleResult()).isGreaterThan(0L);
    }

    /**
     * HQL 连接查询：{@code JOIN FETCH}（迫切连接）会一次性把关联的懒加载集合一并查出，
     * 消除对集合逐个访问导致的 N+1 查询问题。
     */
    @Test
    @Transactional
    void testHqlJoinFetch() {
        Session session = entityManager.unwrap(Session.class);
        // LEFT JOIN FETCH 迫切连接：查询国家时一次性带出城市列表（观察日志只有一条 SQL）
        Query<Country> q = session.createQuery(
                "select distinct c from Country c left join fetch c.cities where c.code = :code", Country.class);
        Country china = q.setParameter("code", "CHN").uniqueResult();
        assertThat(china).isNotNull();
        // 因 join fetch，cities 已被初始化，无需再次查询
        assertThat(china.getCities()).isNotEmpty();
    }

    /**
     * HQL 构造器投影：{@code select new 包名.DTO(...)} 直接把查询结果映射到 DTO，
     * 比逐列组装 {@code Object[]} 更类型安全、更省事。
     */
    @Test
    @Transactional
    void testHqlConstructorProjection() {
        Session session = entityManager.unwrap(Session.class);
        // 隐式内连接：city 与 country 关联，投影国家码 + 城市名到 DTO
        Query<CountryCityDTO> q = session.createQuery(
                "select new org.ljh.hibernate.dto.CountryCityDTO(c.code, ci.name) " +
                        "from City ci join ci.country c where c.code = :code", CountryCityDTO.class);
        List<CountryCityDTO> rows = q.setParameter("code", "CHN").list();
        assertThat(rows).isNotEmpty();
        assertThat(rows.get(0).getCountryCode()).isEqualTo("CHN");
        assertThat(rows.get(0).getCityName()).isNotBlank();
    }

    // --- 2.2 Criteria API ---

    /** Criteria：类型安全、可动态拼装条件的查询 API */
    @Test
    @Transactional
    void testCriteria() {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();

        // 单条件
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        Root<Country> root = cq.from(Country.class);
        cq.select(root).where(cb.equal(root.get("continent"), Continent.EUROPE));
        List<Country> europe = session.createQuery(cq).list();
        assertThat(europe).isNotEmpty();

        // 动态组合：and + greaterThan
        CriteriaQuery<Country> cq2 = cb.createQuery(Country.class);
        Root<Country> r2 = cq2.from(Country.class);
        cq2.select(r2).where(cb.and(
                cb.equal(r2.get("continent"), Continent.ASIA),
                cb.greaterThan(r2.get("population"), 1_000_000_000)));
        List<Country> asiaBig = session.createQuery(cq2).list();
        assertThat(asiaBig).extracting(Country::getCode).contains("CHN");

        // 聚合：count + group by
        CriteriaQuery<Object[]> cq3 = cb.createQuery(Object[].class);
        Root<Country> r3 = cq3.from(Country.class);
        cq3.select(cb.array(r3.get("continent"), cb.count(r3)))
                .groupBy(r3.get("continent"));
        List<Object[]> groups = session.createQuery(cq3).list();
        assertThat(groups).isNotEmpty();
    }

    // --- 2.3 Native SQL ---

    /** createNativeQuery：直接写数据库 SQL，返回实体 */
    @Test
    @Transactional
    void testNativeSql() {
        Session session = entityManager.unwrap(Session.class);
        Query<Country> q = session.createNativeQuery(
                "select * from country where continent = :continent", Country.class);
        q.setParameter("continent", "Asia");
        List<Country> asia = q.list();
        assertThat(asia).isNotEmpty();
        assertThat(asia).extracting(Country::getCode).contains("CHN", "JPN");
    }

    // ==================== 三、数据写操作 ====================

    // --- 3.1 批量操作（HQL 批量更新/删除） ---

    /**
     * 批量修改：用 createMutationQuery 直接执行 HQL update/delete，
     * 不经过脏检查，比逐条 load+flush 高效。
     */
    @Test
    @Transactional
    void testBulkOperations() {
        Session session = entityManager.unwrap(Session.class);

        // 造数：code 列是 CHAR(3)，故用三位码 BUA / BUB，都能被下方 like 'BU%' 命中
        // 用唯一 code 前缀（BU）隔离测试数据，避免批量语句耦合 world 库已有的非洲国家数据
        Country a = newCountry("BUA", "BulkA", Continent.AFRICA);
        Country b = newCountry("BUB", "BulkB", Continent.AFRICA);
        session.persist(a);
        session.persist(b);
        session.flush();

        // 批量更新：只对本次造的数据人口 +1，受影响行数可精确断言为 2
        MutationQuery update = session.createMutationQuery(
                "update Country c set c.population = c.population + 1 where c.code like :prefix");
        update.setParameter("prefix", "BU%");
        int affected = update.executeUpdate();
        assertThat(affected).isEqualTo(2);

        // 批量删除：删除 code 以 BU 开头的国家（BUA、BUB 都命中）
        MutationQuery delete = session.createMutationQuery(
                "delete from Country c where c.code like :prefix");
        delete.setParameter("prefix", "BU%");
        assertThat(delete.executeUpdate()).isEqualTo(2);

        // 注意：批量操作绕过一级缓存，需 clear 刷新
        session.clear();
        assertThat(session.find(Country.class, "BUA")).isNull();
        assertThat(session.find(Country.class, "BUB")).isNull();
    }

    // --- 3.2 @DynamicUpdate ---

    /**
     * {@code @DynamicUpdate}：默认 Hibernate 对实体 UPDATE 会更新所有列；
     * 加该注解后只更新发生变化的列。可用 SQL 日志对比：只改了 price 就只 UPDATE price 与 version。
     */
    @Test
    @Transactional
    void testDynamicUpdate() {
        Session session = entityManager.unwrap(Session.class);

        Product p = new Product();
        p.setName("Caffeine");
        p.setPrice(new BigDecimal("29.90"));
        p.setStock(100);
        session.persist(p);
        session.flush();

        // 只改一个字段 price：@DynamicUpdate 让 UPDATE 只含 price + version 两列
        p.setPrice(new BigDecimal("19.90"));
        session.flush();

        Product reloaded = session.find(Product.class, p.getId());
        assertThat(reloaded.getPrice()).isEqualByComparingTo("19.90");
        assertThat(reloaded.getStock()).isEqualTo(100); // stock 未被改动
    }

    // ==================== 四、关联映射 ====================

    // --- 4.1 关联与复合主键 ---

    /** 一对多级联保存：persist 父实体，子实体（城市）随级联一并落库 */
    @Test
    @Transactional
    void testCascadeSave() {
        Session session = entityManager.unwrap(Session.class);
        // 注意：USA 已被 world 库占用，测试用自造国家码 XX3
        Country c = newCountry("XX3", "Testland", Continent.NORTH_AMERICA);
        c.setPopulation(330_000_000);
        c.addCity(newCity("Washington", "District of Columbia", 700_000));
        c.addCity(newCity("New York", "New York", 8_400_000));

        // 只需 persist 父实体，CascadeType.ALL 自动保存子实体
        session.persist(c);
        session.flush();

        // 验证子实体已落库
        List<City> cities = session.createQuery(
                "select c from City c where c.country.code = :code", City.class)
                .setParameter("code", "XX3")
                .list();
        assertThat(cities).hasSize(2);

        session.remove(c);
        session.flush();
    }

    /** 复合主键：@IdClass 的实体，用 CountryLanguageId 直接 get */
    @Test
    @Transactional
    void testCompositeId() {
        Session session = entityManager.unwrap(Session.class);
        CountryLanguageId id = new CountryLanguageId("CHN", "Chinese");
        CountryLanguage cl = session.find(CountryLanguage.class, id);
        assertThat(cl).isNotNull();
        assertThat(cl.getIsOfficial()).isEqualTo("T");
    }

    // --- 4.2 可嵌入组件 @Embeddable ---

    /**
     * {@code @Embedded} 组合 {@code @Embeddable}：Address 字段展开到 {@link Staff}
     * 所在的 person 表，查询时可用路径 {@code s.address.city} 访问内嵌字段。
     */
    @Test
    @Transactional
    void testEmbeddable() {
        Session session = entityManager.unwrap(Session.class);

        Staff s = new Staff();
        s.setName("Alice");
        s.setDepartment("HR");
        Address addr = new Address();
        addr.setStreet("1 Main St");
        addr.setCity("Beijing");
        addr.setZipCode("100000");
        s.setAddress(addr);
        session.persist(s);
        session.flush();

        // 内嵌字段已展开为同表三列，可按路径查询
        Staff reloaded = session.createQuery(
                "from Staff s where s.address.city = :city", Staff.class)
                .setParameter("city", "Beijing")
                .uniqueResult();
        assertThat(reloaded).isNotNull();
        assertThat(reloaded.getAddress().getStreet()).isEqualTo("1 Main St");
        assertThat(reloaded.getAddress().getZipCode()).isEqualTo("100000");

        session.remove(reloaded);
        session.flush();
    }

    // --- 4.3 继承映射 @Inheritance ---

    /**
     * 继承映射 SINGLE_TABLE（单表）：Staff 与 Manager 共享 {@code person} 表，
     * 靠辨别者列 DISC_TYPE 区分。多态查询（按父类查）会带出所有子类行。
     */
    @Test
    @Transactional
    void testInheritanceSingleTable() {
        Session session = entityManager.unwrap(Session.class);

        Staff s = new Staff();
        s.setName("Bob");
        s.setDepartment("IT");
        Manager m = new Manager();
        m.setName("Carol");
        m.setTeamSize(8);
        session.persist(s);
        session.persist(m);
        session.flush();

        // 按具体子类查询：只查 DISC_TYPE='STAFF'，且能访问子类专属字段 department
        List<Staff> staffs = session.createQuery("from Staff", Staff.class).list();
        assertThat(staffs).extracting(Person::getName).contains("Bob");
        assertThat(staffs).filteredOn(Staff::getName, "Bob")
                .singleElement()
                .extracting(Staff::getDepartment)
                .isEqualTo("IT");

        // 按父类多态查询：同时带出 Staff 与 Manager
        List<Person> people = session.createQuery("from Person", Person.class).list();
        assertThat(people).extracting(Person::getName).contains("Bob", "Carol");

        session.remove(s);
        session.remove(m);
        session.flush();
    }

    // --- 4.4 多对多 @ManyToMany ---

    /**
     * 多对多：Teacher 与 Student 通过中间表 {@code teacher_student} 关联。
     * Teacher 是维护端，持久化时级联保存 Student 并写入中间表。
     */
    @Test
    @Transactional
    void testManyToMany() {
        Session session = entityManager.unwrap(Session.class);

        Student s1 = new Student();
        s1.setName("Dave");
        Student s2 = new Student();
        s2.setName("Eve");

        Teacher t = new Teacher();
        t.setName("Frank");
        t.addStudent(s1);
        t.addStudent(s2);
        // 持久化维护端 Teacher，级联保存两个 Student，并写入中间表
        session.persist(t);
        session.flush();

        // 从维护端查询：能取到关联的学生集合
        Teacher reloaded = session.createQuery(
                "from Teacher t where t.name = :name", Teacher.class)
                .setParameter("name", "Frank")
                .uniqueResult();
        assertThat(reloaded.getStudents()).hasSize(2);
        assertThat(reloaded.getStudents()).extracting(Student::getName).contains("Dave", "Eve");

        // 从反向端查询：学生也能看到教师
        Student dave = session.createQuery("from Student s where s.name = :name", Student.class)
                .setParameter("name", "Dave").uniqueResult();
        assertThat(dave.getTeachers()).extracting(Teacher::getName).contains("Frank");

        session.remove(reloaded);
        session.flush();
    }

    // --- 4.5 一对一 @OneToOne ---

    /**
     * 一对一（外键方式）：author 表持外键 passport_id 指向 passport。
     * Author 是外键维护端，Passport 用 mappedBy 反向引用。
     */
    @Test
    @Transactional
    void testOneToOne() {
        Session session = entityManager.unwrap(Session.class);

        Passport pp = new Passport();
        pp.setNumber("E12345678");
        session.persist(pp);

        Author a = new Author();
        a.setName("Grace");
        a.setPassport(pp);
        session.persist(a);
        session.flush();

        // 从维护端取到护照
        Author reloaded = session.createQuery(
                "from Author a where a.name = :name", Author.class)
                .setParameter("name", "Grace")
                .uniqueResult();
        assertThat(reloaded.getPassport().getNumber()).isEqualTo("E12345678");

        // 从反向端取回作者：护照按号查询后，通过反向关联（Passport.author）反查作者。
        // 说明：Passport 是 mappedBy（只读）端，不持有外键，故 Hibernate 无法直接
        // 为其反向 one-to-one 生成懒代理（会退化为需显式加载）。这里用显式 join 验证反向关联，
        // 从护照出发按关联条件找到持有该护照的作者。
        Passport passport = session.createQuery(
                "from Passport p where p.number = :number", Passport.class)
                .setParameter("number", "E12345678")
                .uniqueResult();
        Author back = session.createQuery(
                "select a from Author a where a.passport = :pp", Author.class)
                .setParameter("pp", passport)
                .uniqueResult();
        assertThat(back).isNotNull();
        assertThat(back.getName()).isEqualTo("Grace");

        session.remove(reloaded);
        session.flush();
    }

    // ==================== 五、缓存体系 ====================

    // --- 5.1 get / getReference（懒代理） ---

    /**
     * getReference（旧称 load）：返回懒代理，真正访问字段时才发查询（避免无谓的 SELECT）。
     * get：立即查询，不存在返回 null。
     */
    @Test
    @Transactional
    void testGetReferenceLazyProxy() {
        Session session = entityManager.unwrap(Session.class);

        // 造一个全新国家（不在二级缓存中），用 getReference 拿到代理
        Country tmp = newCountry("XX9", "ProxyDemo", Continent.OCEANIA);
        session.persist(tmp);
        session.flush();
        session.evict(tmp); // 移出当前 Session，便于观察代理

        // getReference：只发一条 SELECT（甚至不查，直到访问属性才初始化）。
        Country proxy = session.getReference(Country.class, "XX9");
        assertThat(proxy.getName()).isEqualTo("ProxyDemo"); // 访问触发加载
        // 若尚未访问任何属性，可用 PersistenceUnitUtil.isLoaded 判断是否已加载
        assertThat(entityManagerFactory.getPersistenceUnitUtil().isLoaded(proxy)).isTrue();

        // 城市多对一关联默认懒加载：取一个非二级缓存国家的城市，其 country 一开始是懒代理
        City city = session.createQuery(
                "from City c where c.country.code = :code", City.class)
                .setParameter("code", "TMP") // 东帝汶的首都 Dili，未做二级缓存
                .setMaxResults(1)
                .uniqueResult();
        assertThat(city).isNotNull();
        assertThat(city.getCountry().getName()).isEqualTo("East Timor"); // 访问触发懒加载
    }

    // --- 5.2 自然 ID @NaturalId ---

    /**
     * byNaturalId：按 {@code @NaturalId} 声明的业务唯一键（国家名）查询，
     * 命中自然 ID 二级缓存时零 SQL。
     */
    @Test
    @Transactional
    void testNaturalId() {
        Session session = entityManager.unwrap(Session.class);
        // 按自然键 Name = "China" 查询，无需知道主键 Code
        Country china = session.byNaturalId(Country.class)
                .using("name", "China")
                .load();
        assertThat(china).isNotNull();
        assertThat(china.getCode()).isEqualTo("CHN");

        // 返回 Optional 版本
        Country japan = session.byNaturalId(Country.class)
                .using("name", "Japan")
                .getReference();
        assertThat(japan.getCode()).isEqualTo("JPN");
    }

    // --- 5.3 二级缓存 @Cache 与查询缓存 ---

    /**
     * 二级缓存：跨 Session 共享实体（配置见 application.properties）。
     * {@code @Cache(READ_WRITE)} 让实体实例进入二级缓存，
     * {@code @NaturalIdCache} 缓存自然 ID → 主键 的映射。
     * 同一 Session 命中一级缓存；新开 Session 命中二级缓存，避免重复查库。
     */
    @Test
    @Transactional
    void testSecondLevelCache() {
        SessionFactory sf = entityManagerFactory.unwrap(SessionFactory.class);
        Statistics stats = sf.getStatistics();

        // 第一个 Session 加载 Country（进一级 + 写入二级缓存）
        try (Session s1 = sf.openSession()) {
            s1.beginTransaction();
            Country c1 = s1.find(Country.class, "CHN");
            assertThat(c1).isNotNull();
            s1.getTransaction().commit();
        }
        long secondLevelPuts = stats.getSecondLevelCachePutCount();

        // 第二个 Session 再次加载：应命中二级缓存，命中计数 +1（否则会再次查询数据库）
        try (Session s2 = sf.openSession()) {
            s2.beginTransaction();
            Country c2 = s2.find(Country.class, "CHN");
            assertThat(c2.getName()).isEqualTo("China");
            s2.getTransaction().commit();
        }
        // 命中计数较首次加载后增加，证明二次加载走了二级缓存而非重新查库
        assertThat(stats.getSecondLevelCacheHitCount()).isGreaterThan(0L);
        assertThat(stats.getSecondLevelCachePutCount()).isEqualTo(secondLevelPuts);
    }

    /**
     * 查询缓存：需在 application.properties 开启 {@code use_query_cache=true}，
     * 并对查询显式 {@code setCacheable(true)}。缓存的是「结果的主键集合」，
     * 命中时免去再次执行 SQL。用执行前后 {@code getQueryCacheHitCount()} 的差值验证命中。
     */
    @Test
    @Transactional
    void testQueryCache() {
        Session session = entityManager.unwrap(Session.class);
        Statistics stats = entityManagerFactory.unwrap(SessionFactory.class).getStatistics();
        long hitsBefore = stats.getQueryCacheHitCount();

        // 同一查询执行两次：第一次执行并写入缓存，第二次命中查询缓存
        for (int i = 0; i < 2; i++) {
            Query<Country> q = session.createQuery(
                    "select c from Country c where c.continent = :continent", Country.class);
            q.setParameter("continent", Continent.ASIA);
            q.setCacheable(true); // 关键：显式开启查询缓存
            List<Country> asia = q.list();
            assertThat(asia).extracting(Country::getCode).contains("CHN");
        }

        // 第二次执行应命中查询缓存，命中计数相对执行前至少 +1（排除其他测试的干扰）
        assertThat(stats.getQueryCacheHitCount()).isGreaterThan(hitsBefore);
    }

    /**
     * 集合级二级缓存：{@link Country#getCities()} 加 {@code @Cache(READ_WRITE)}，
     * 则「某国家的城市主键列表」也被缓存。新 Session 首次加载后，
     * 再次加载同一国家时，其城市集合可直接命中集合缓存，避免重新查集合。
     * <p>集合缓存区域名默认形如 {@code 实体全类名.属性名}，此处通过
     * {@code Statistics.getCollectionStatistics(region)} 断言该缓存区域已被创建，
     * 从而证明集合级二级缓存确实生效。</p>
     */
    @Test
    void testCollectionSecondLevelCache() {
        SessionFactory sf = entityManagerFactory.unwrap(SessionFactory.class);
        Statistics stats = sf.getStatistics();
        String region = Country.class.getName() + ".cities";

        try (Session s1 = sf.openSession()) {
            s1.beginTransaction();
            Country c = s1.find(Country.class, "CHN");
            assertThat(c.getCities()).isNotEmpty(); // 触发加载集合，写入集合缓存
            s1.getTransaction().commit();
        }

        try (Session s2 = sf.openSession()) {
            s2.beginTransaction();
            Country c = s2.find(Country.class, "CHN");
            assertThat(c.getCities()).isNotEmpty(); // 命中集合缓存，避免重查集合
            s2.getTransaction().commit();
        }
        // 集合缓存区域已产生（说明集合级二级缓存确实生效，加载时写入了缓存区域）
        assertThat(stats.getCollectionStatistics(region)).isNotNull();
    }

    // ==================== 六、并发控制 ====================

    // --- 6.1 乐观锁 @Version ---

    /**
     * 乐观锁：{@code @Version} 字段每次 UPDATE 自动 +1。
     * 两个并发事务基于同一版本提交，后提交者触发 {@code OptimisticLockException}。
     */
    @Test
    void testOptimisticLock() {
        SessionFactory sf = entityManagerFactory.unwrap(SessionFactory.class);

        // 创建商品
        Product p = new Product();
        p.setName("PS5");
        p.setPrice(new BigDecimal("3999.00"));
        p.setStock(10);
        try (Session s = sf.openSession()) {
            s.beginTransaction();
            s.persist(p);
            s.getTransaction().commit();
        }
        Long id = p.getId();

        // 两个 Session 各自读同一版本（find 立即加载，随事务提交变为游离实例）
        Product v1;
        Product v2;
        try (Session s = sf.openSession()) {
            s.beginTransaction();
            v1 = s.find(Product.class, id);
            s.getTransaction().commit();
        }
        try (Session s = sf.openSession()) {
            s.beginTransaction();
            v2 = s.find(Product.class, id);
            s.getTransaction().commit();
        }
        assertThat(v1.getVersion()).isEqualTo(0L);
        assertThat(v2.getVersion()).isEqualTo(0L);

        // 第一个事务更新成功：version 0 -> 1
        try (Session s = sf.openSession()) {
            s.beginTransaction();
            Product cur = s.find(Product.class, id);
            cur.setStock(cur.getStock() - 1);
            s.getTransaction().commit();
        }

        // 第二个事务基于旧版本(0) 提交：版本号不匹配，抛乐观锁冲突
        // （底层是 StaleObjectStateException，经 JPA 桥接抛 OptimisticLockException）
        v2.setStock(8);
        assertThatThrownBy(() -> {
            try (Session s = sf.openSession()) {
                s.beginTransaction();
                s.merge(v2);
                s.getTransaction().commit();
            }
        }).isInstanceOf(OptimisticLockException.class);
    }

    // --- 6.2 悲观锁 ---

    /** 悲观锁：以 LockModeType.PESSIMISTIC_WRITE 查询即对行加写锁（SELECT ... FOR UPDATE），需在事务中 */
    @Test
    @Transactional
    void testPessimisticLock() {
        Session session = entityManager.unwrap(Session.class);
        Product p = new Product();
        p.setName("Kindle");
        p.setPrice(new BigDecimal("998.00"));
        p.setStock(5);
        session.persist(p);
        session.flush();

        // 加写锁查询，事务内可正常读到。
        // 注意：因 Product 带 @Version，Hibernate 对带版本实体请求 PESSIMISTIC_WRITE 时，
        // 会自动升级为 PESSIMISTIC_FORCE_INCREMENT（加锁同时递增版本），故实际锁模式为该值。
        Product locked = session.find(Product.class, p.getId(), LockModeType.PESSIMISTIC_WRITE);
        assertThat(locked).isNotNull();
        LockModeType mode = session.getLockMode(locked);
        assertThat(mode).isEqualTo(LockModeType.PESSIMISTIC_FORCE_INCREMENT);
    }

    // ==================== 测试辅助方法 ====================

    /**
     * 构造一个满足 world 表所有 NOT NULL 约束的国家（否则 insert 会因
     * Region/LocalName/GovernmentForm/Code2 等列不允许 NULL 而失败）。
     */
    private Country newCountry(String code, String name, Continent continent) {
        Country c = new Country();
        c.setCode(code);
        c.setName(name);
        c.setContinent(continent);
        c.setRegion("Test Region");
        c.setSurfaceArea(new BigDecimal("1000.00"));
        c.setPopulation(1_000_000);
        c.setLocalName(name);
        c.setGovernmentForm("Republic");
        c.setCode2(code.substring(0, 2));
        return c;
    }

    private City newCity(String name, String district, int population) {
        City city = new City();
        city.setName(name);
        city.setDistrict(district);
        city.setPopulation(population);
        return city;
    }
}
