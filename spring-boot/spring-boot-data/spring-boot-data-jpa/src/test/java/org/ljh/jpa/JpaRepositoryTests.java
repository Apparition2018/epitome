package org.ljh.jpa;

import org.junit.jupiter.api.Test;
import org.ljh.jpa.entity.*;
import org.ljh.jpa.projection.CountryArea;
import org.ljh.jpa.projection.CountryNamePopulation;
import org.ljh.jpa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Spring Data JPA 演示测试
 * <p>按主题组织，覆盖：仓储声明、四种查询方式（派生/JPQL/Native/修改）、分页排序、
 * 动态查询、实体图、投影、关联与复合主键、并发控制（乐观锁/悲观锁）、审计。</p>
 *
 * @author ljh
 * @since 2026/8/8
 */
@SpringBootTest(classes = JpaApplication.class)
class JpaRepositoryTests {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryLanguageRepository languageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CityDefinitionRepository cityDefinitionRepository;

    // ==================== 一、仓储声明 ====================

    /** 容器注入检查：所有仓储代理均成功创建 */
    @Test
    void contextLoads() {
        assertThat(countryRepository).isNotNull();
        assertThat(cityRepository).isNotNull();
        assertThat(languageRepository).isNotNull();
        assertThat(productRepository).isNotNull();
        assertThat(cityDefinitionRepository).isNotNull();
    }

    /**
     * 继承 JpaRepository 自带 CRUD：findById / existsById / findAll / count / save / delete。
     */
    @Test
    void testCrudViaJpaRepository() {
        // findById
        Optional<Country> china = countryRepository.findById("CHN");
        assertThat(china).isPresent();
        assertThat(china.get().getName()).isEqualTo("China");

        // existsById
        assertThat(countryRepository.existsById("JPN")).isTrue();
        assertThat(countryRepository.existsById("XXX")).isFalse();

        // findAll + count（world 库有大量数据）
        assertThat(countryRepository.findAll()).isNotEmpty();
        assertThat(countryRepository.count()).isGreaterThan(100);

        // save + delete（新增一个临时国家再删除）
        Country tmp = newCountry("TMP", "Temporary", Continent.OCEANIA);
        countryRepository.save(tmp);
        assertThat(countryRepository.existsById("TMP")).isTrue();
        countryRepository.deleteById("TMP");
        assertThat(countryRepository.existsById("TMP")).isFalse();
    }

    /**
     * {@code @RepositoryDefinition}：不继承任何 Repository 子接口，仅靠注解声明领域类与主键，
     * 方法名依旧支持派生查询。
     */
    @Test
    void testRepositoryDefinition() {
        assertThat(cityDefinitionRepository.findByName("Shanghai")).isPresent();
        assertThat(cityDefinitionRepository.count()).isGreaterThan(0);
    }

    // ==================== 二、查询方式 ====================

    /** 派生查询：方法名即查询条件，无需写 SQL */
    @Test
    void testDerivedQueries() {
        // 按枚举查询（经 ContinentConverter 转换）
        List<Country> asia = countryRepository.findByContinent(Continent.ASIA);
        assertThat(asia).isNotEmpty();
        assertThat(asia).extracting(Country::getCode).contains("CHN", "JPN");

        // First：仅返回第一个匹配
        Optional<Country> japan = countryRepository.findFirstByName("Japan");
        assertThat(japan).isPresent();
        assertThat(japan.get().getCode()).isEqualTo("JPN");

        // 大于 + 排序：人口 > 1 亿的国家
        List<Country> big = countryRepository.findByPopulationGreaterThanOrderByPopulationDesc(100_000_000);
        assertThat(big).isNotEmpty();
        assertThat(big.get(0).getCode()).isEqualTo("CHN"); // 中国人口最多

        // 区间查询
        List<Country> mid = countryRepository.findByPopulationBetween(50_000_000, 200_000_000);
        assertThat(mid).extracting(Country::getCode).contains("JPN", "FRA");

        // 模糊 + 忽略大小写
        List<Country> like = countryRepository.findByNameContainingIgnoreCase("fra");
        assertThat(like).extracting(Country::getCode).contains("FRA");

        // 组合条件：亚洲人口 > 10 亿（可能含 CHN、IND、IDN、PAK 等大国）
        List<Country> asiaBig = countryRepository.findByContinentAndPopulationGreaterThanEqual(Continent.ASIA, 1_000_000_000);
        assertThat(asiaBig).extracting(Country::getCode).contains("CHN");

        // in 条件
        List<Country> regions = countryRepository.findByRegionIn(List.of("Eastern Asia"));
        assertThat(regions).isNotEmpty();

        // count / exists
        assertThat(countryRepository.countByContinent(Continent.ASIA)).isGreaterThan(10);
        assertThat(countryRepository.existsByCode("FRA")).isTrue();
    }

    /** Top：方法名 TopN 限制返回条数，配合排序取前 N 名 */
    @Test
    void testTopLimit() {
        List<Country> top3 = countryRepository.findTop3ByOrderByPopulationDesc();
        assertThat(top3).hasSize(3);
        // 人口最多的国家：中国、印度……
        assertThat(top3.get(0).getCode()).isEqualTo("CHN");
    }

    /** JPQL 查询（{@code @Query}）：分组聚合、命名参数 */
    @Test
    void testJpqlQueries() {
        // 分组聚合：按大洲统计国家数
        List<Object[]> groups = countryRepository.countGroupByContinent();
        assertThat(groups).isNotEmpty();
        groups.forEach(g ->
                System.out.printf("  %s: %s 个%n", g[0], g[1]));

        // 命名参数 + 模糊
        List<Country> like = countryRepository.findByNameLike("fra");
        assertThat(like).extracting(Country::getCode).contains("FRA");

        // count
        assertThat(countryRepository.countAll()).isGreaterThan(100);
    }

    /** {@code @Query} 中 SpEL 表达式：{@code #{#entityName}} 在运行时替换为实体名，JPQL 不写死实体名 */
    @Test
    void testSpelQuery() {
        Optional<Country> china = countryRepository.findByCodeUsingSpel("CHN");
        assertThat(china).isPresent();
        assertThat(china.get().getName()).isEqualTo("China");
    }

    /** Native SQL 查询（{@code @Query} + nativeQuery） */
    @Test
    void testNativeQueries() {
        List<Country> asia = countryRepository.findNativeByContinent("Asia");
        assertThat(asia).isNotEmpty();

        // 原生 SQL + 分页
        Page<Country> page = countryRepository.findNativeByPopulationGreaterThan(0, PageRequest.of(0, 5));
        assertThat(page.getContent()).hasSize(5);
        assertThat(page.getTotalElements()).isGreaterThan(100);
    }

    /** 修改操作（{@code @Modifying} + 事务） */
    @Test
    @Transactional
    void testModifyingQueries() {
        // 更新单个字段
        int updated = countryRepository.updatePopulationByCode("CHN", 1410000000);
        assertThat(updated).isEqualTo(1);
        Country chn = countryRepository.findById("CHN").orElseThrow();
        assertThat(chn.getPopulation()).isEqualTo(1410000000);

        // 按大洲批量增减（影响所有欧洲国家）
        int affected = countryRepository.addPopulationByContinent(Continent.EUROPE, 1);
        assertThat(affected).isGreaterThan(10);

        // 批量删除人口为负的国家（world 数据无负值，故为 0，避免误删真实数据）
        int deleted = countryRepository.deleteByPopulationLessThan(0);
        assertThat(deleted).isEqualTo(0);
    }

    // ==================== 三、分页与排序 ====================

    /** Page 分页与排序：内容 + 总记录数 + 总页数（额外执行 count 查询） */
    @Test
    void testPagingAndSorting() {
        // 分页：第 1 页（0-based），每页 5 条，按人口倒序
        Page<Country> page1 = countryRepository.findByContinent(
                Continent.ASIA, PageRequest.of(0, 5, Sort.by("population").descending()));
        assertThat(page1.getTotalElements()).isGreaterThan(10);
        assertThat(page1.getContent()).hasSize(5);
        assertThat(page1.getContent().get(0).getCode()).isEqualTo("CHN");

        // 城市分页：中国的城市，每页 50 条
        Page<City> cityPage = cityRepository.findByCountryCode("CHN", PageRequest.of(0, 50));
        assertThat(cityPage.getTotalElements()).isGreaterThan(300);
        assertThat(cityPage.getContent()).hasSize(50);
    }

    /**
     * Slice 分页：只返回当前页 + 是否有下一页（hasNext()），不执行 count 查询，
     * 比 Page 更轻量，适合下拉加载更多/无限滚动。
     */
    @Test
    void testSlicePaging() {
        Slice<City> slice = cityRepository.findByPopulationGreaterThanEqual(
                0, PageRequest.of(0, 50));
        assertThat(slice).isNotNull();
        assertThat(slice.getContent()).hasSize(50);
        // Slice 只保证 hasNext()，不提供 getTotalElements()
        assertThat(slice.hasNext()).isTrue();
    }

    /** 独立 Sort：无分页，仅排序 */
    @Test
    void testIndependentSort() {
        List<City> cities = cityRepository.findByPopulationGreaterThanEqual(
                0, Sort.by(Sort.Direction.DESC, "population"));
        assertThat(cities).isNotEmpty();
        // 人口最多的城市应排在首位
        assertThat(cities.get(0).getPopulation())
                .isGreaterThanOrEqualTo(cities.get(1).getPopulation());
    }

    // ==================== 四、动态 / 模板查询 ====================

    /** Specification：用 Criteria 动态拼接查询条件 */
    @Test
    void testSpecification() {
        // 动态拼接：大洲 = ASIA 且 人口 > 1.3 亿（含印度、印尼、巴基斯坦等大国）
        Specification<Country> spec = (root, query, cb) -> cb.and(
                cb.equal(root.get("continent"), Continent.ASIA),
                cb.greaterThan(root.get("population"), 130_000_000));
        List<Country> result = countryRepository.findAll(spec);
        assertThat(result).extracting(Country::getCode).contains("CHN");

        // 复杂组合：大洲 = EUROPE 或 名称以 J 开头
        Specification<Country> spec2 = (root, query, cb) -> cb.or(
                cb.equal(root.get("continent"), Continent.EUROPE),
                cb.like(root.get("name"), "J%"));
        List<Country> result2 = countryRepository.findAll(spec2);
        assertThat(result2).extracting(Country::getCode).contains("FRA", "JPN");
    }

    /**
     * Query by Example：用实体实例作查询模板，由 Spring Data 根据非空字段自动生成条件，
     * 配合 ExampleMatcher 可配置模糊/忽略大小写等匹配规则。
     */
    @Test
    void testQueryByExample() {
        // 以 China 为模板：非空字段 name 作为等值条件
        Country probe = new Country();
        probe.setName("China");
        Example<Country> example = Example.of(probe);
        List<Country> result = countryRepository.findAll(example);
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCode()).isEqualTo("CHN");

        // ExampleMatcher：字符串模糊匹配 + 忽略大小写
        Country matcherProbe = new Country();
        matcherProbe.setName("fra");
        Example<Country> matcherExample = Example.of(matcherProbe,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        List<Country> matched = countryRepository.findAll(matcherExample);
        assertThat(matched).extracting(Country::getCode).contains("FRA");
    }

    // ==================== 五、性能优化：@EntityGraph 解决 N+1 ====================

    @Test
    @Transactional
    void testEntityGraph() {
        // 一次查询同时取出国家及其城市
        Optional<Country> china = countryRepository.findWithCitiesByCodeUsingEntityGraph("CHN");
        assertThat(china).isPresent();
        assertThat(china.get().getCities()).isNotEmpty();

        // 语言集合同样一次加载
        Optional<Country> japan = countryRepository.findWithLanguagesByCodeUsingEntityGraph("JPN");
        assertThat(japan).isPresent();
        assertThat(japan.get().getLanguages()).isNotEmpty();
    }

    /** JOIN FETCH：与 {@code @EntityGraph} 效果等价，直接在 JPQL 中声明急加载 */
    @Test
    @Transactional
    void testJoinFetch() {
        Optional<Country> china = countryRepository.findWithCitiesByCode("CHN");
        assertThat(china).isPresent();
        assertThat(china.get().getCities()).isNotEmpty();
    }

    // ==================== 六、结果映射：投影（Projection） ====================

    /** 投影：仅查询指定字段，减少数据传输（DTO 构造器 / 单字段 / 单值） */
    @Test
    void testProjection() {
        // DTO 构造器投影
        List<CountryNamePopulation> list = countryRepository.findNamePopulationProjection();
        assertThat(list).isNotEmpty();
        list.stream().limit(5).forEach(dto ->
                System.out.printf("  %s %s: %d%n", dto.getCode(), dto.getName(), dto.getPopulation()));

        // 单字段投影
        Optional<String> name = countryRepository.findNameByCode("FRA");
        assertThat(name).contains("France");

        // 面积投影
        List<CountryArea> areas = countryRepository.findAreaProjection();
        assertThat(areas).isNotEmpty();

        // 单值查询
        assertThat(countryRepository.findSurfaceAreaByCode("CHN")).isNotNull();
    }

    // ==================== 七、关联与复合主键 ====================

    @Test
    @Transactional
    void testAssociations() {
        // 通过国家码查询城市，按人口倒序
        List<City> cities = cityRepository.findByCountryCodeOrderByPopulationDesc("CHN");
        assertThat(cities).isNotEmpty();
        assertThat(cities.get(0).getName()).isEqualTo("Shanghai");

        // 反向：通过国家实体访问城市集合
        Country china = countryRepository.findById("CHN").orElseThrow();
        assertThat(china.getCities()).isNotEmpty();

        // @IdClass 复合主键：按国家码查询语言
        List<CountryLanguage> cnLanguages = languageRepository.findByCountryCode("CHN");
        assertThat(cnLanguages).isNotEmpty();

        // 官方语言
        List<CountryLanguage> official = languageRepository.findByIsOfficial("T");
        assertThat(official).isNotEmpty();

        // 通过关联国家名称过滤语言
        List<CountryLanguage> byCountryName = languageRepository.findByCountryName("China");
        assertThat(byCountryName).isNotEmpty();
    }

    /** 级联保存：父实体 save 一次，子实体（城市/语言）随 CascadeType.ALL 一并落库 */
    @Test
    @Transactional
    void testCascadeSave() {
        // 新建一个国家及其城市、语言（复合主键）
        Country usa = newCountry("USA", "United States", Continent.NORTH_AMERICA);
        usa.setPopulation(330000000);
        usa.setSurfaceArea(new BigDecimal("9833520.00"));

        usa.addCity(newCity("Washington", "District of Columbia", 700000));
        usa.addCity(newCity("New York", "New York", 8400000));
        usa.addLanguage(newLanguage("English", "T", new BigDecimal("82.1")));
        usa.addLanguage(newLanguage("Spanish", "F", new BigDecimal("12.9")));

        // 只需保存父实体，级联自动保存全部子实体
        countryRepository.save(usa);

        // 验证：城市、语言均已落库
        assertThat(cityRepository.findByCountryCode("USA")).hasSize(2);
        List<CountryLanguage> usaLanguages = languageRepository.findByCountryCode("USA");
        assertThat(usaLanguages).hasSize(2);

        // 验证复合主键正确填充（@MapsId/手动 setCountryCode）
        assertThat(usaLanguages.get(0).getId().getCountryCode()).isEqualTo("USA");
    }

    /** 复合主键对象查询：以 CountryLanguageId 直接查找 */
    @Test
    void testFindByCompositeId() {
        CountryLanguageId id = new CountryLanguageId("CHN", "Chinese");
        Optional<CountryLanguage> found = languageRepository.findById(id);
        assertThat(found).isPresent();
        assertThat(found.get().getIsOfficial()).isEqualTo("T");
    }

    /** 更新子实体：修改城市人口后保存，验证孤儿/级联更新 */
    @Test
    @Transactional
    void testUpdateChildEntity() {
        Country usa = newCountry("USC", "United States", Continent.NORTH_AMERICA);
        usa.addCity(newCity("LA", "California", 3900000));
        countryRepository.save(usa);

        // 修改子实体并通过父实体保存（级联更新）
        usa.getCities().get(0).setPopulation(4000000);
        countryRepository.save(usa);

        assertThat(cityRepository.findByName("LA").get(0).getPopulation()).isEqualTo(4000000);
    }

    // ==================== 八、并发控制（product 表） ====================

    /**
     * 乐观锁 {@code @Version}：两个客户端各自读取（独立事务）后再更新。
     * 第一个提交成功 version 0→1；第二个基于旧 version(0) 提交时冲突抛异常。
     */
    @Test
    void testOptimisticLockConflict() {
        Product p = new Product();
        p.setName("iPhone");
        p.setPrice(new BigDecimal("5999.00"));
        Product saved = productRepository.saveAndFlush(p); // insert，version=0

        // 模拟两个并发客户端，各自独立事务读取同一版本
        Product p1 = productRepository.findById(saved.getId()).orElseThrow();
        Product p2 = productRepository.findById(saved.getId()).orElseThrow();

        // 第一个客户端更新成功：version 0 -> 1
        p1.setPrice(new BigDecimal("4999.00"));
        Product updated1 = productRepository.saveAndFlush(p1);
        assertThat(updated1.getVersion()).isEqualTo(1);

        // 第二个客户端仍基于旧 version(0) 提交，触发乐观锁冲突
        p2.setPrice(new BigDecimal("3999.00"));
        assertThatThrownBy(() -> productRepository.saveAndFlush(p2))
                .isInstanceOf(OptimisticLockingFailureException.class);
    }

    /** 乐观锁正常路径：insert 后 version=0，每次 update 递增 */
    @Test
    void testOptimisticLockNormal() {
        Product p = new Product();
        p.setName("MacBook");
        p.setPrice(new BigDecimal("9999.00"));
        Product saved = productRepository.saveAndFlush(p); // insert，version=0

        // 第一次更新：0 -> 1
        Product loaded = productRepository.findById(saved.getId()).orElseThrow();
        loaded.setPrice(new BigDecimal("8999.00"));
        Product u1 = productRepository.saveAndFlush(loaded);
        assertThat(u1.getVersion()).isEqualTo(1);

        // 第二次更新：1 -> 2
        Product loaded2 = productRepository.findById(saved.getId()).orElseThrow();
        loaded2.setPrice(new BigDecimal("7999.00"));
        Product u2 = productRepository.saveAndFlush(loaded2);
        assertThat(u2.getVersion()).isEqualTo(2);
    }

    /**
     * 悲观锁 {@code @Lock}：查询即对行加写锁（{@code SELECT ... FOR UPDATE}）。
     * 需在事务中执行；这里演示加锁读取能正常返回。
     */
    @Test
    @Transactional
    void testPessimisticLock() {
        Product p = new Product();
        p.setName("PS5");
        p.setPrice(new BigDecimal("3999.00"));
        productRepository.saveAndFlush(p);

        // 在事务内以悲观写锁查询，能正常读到
        Optional<Product> locked = productRepository.findByIdForUpdate(p.getId());
        assertThat(locked).isPresent();
        assertThat(locked.get().getName()).isEqualTo("PS5");
    }

    // ==================== 九、审计（product 表） ====================

    /** 审计：新增时填充 createdBy/createdAt；更新时填充 updatedBy/updatedAt */
    @Test
    @Transactional
    void testAuditing() {
        Product p = new Product();
        p.setName("Kindle");
        p.setPrice(new BigDecimal("998.00"));
        productRepository.save(p);

        // 新增：创建人/时间已自动填充
        assertThat(p.getCreatedBy()).isEqualTo("system");
        assertThat(p.getCreatedAt()).isNotNull();
        assertThat(p.getUpdatedAt()).isNotNull();

        // 更新：修改时间随之更新
        p.setPrice(new BigDecimal("888.00"));
        productRepository.save(p);
        assertThat(p.getUpdatedBy()).isEqualTo("system");
        assertThat(p.getUpdatedAt()).isNotNull();
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
        c.setPopulation(1000000);
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

    private CountryLanguage newLanguage(String language, String official, BigDecimal percentage) {
        CountryLanguage cl = new CountryLanguage();
        cl.setId(new CountryLanguageId(null, language));
        cl.setIsOfficial(official);
        cl.setPercentage(percentage);
        return cl;
    }
}
