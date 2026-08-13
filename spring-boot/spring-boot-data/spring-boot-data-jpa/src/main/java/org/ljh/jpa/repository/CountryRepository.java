package org.ljh.jpa.repository;

import org.ljh.jpa.entity.Continent;
import org.ljh.jpa.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * CountryRepository — 国家仓储
 * <p>集中演示 Spring Data JPA 的四种查询方式：</p>
 * <ol>
 *     <li>继承 {@code JpaRepository} 自带 CRUD + 分页方法</li>
 *     <li>根据方法名自动推导的派生查询（derived query）</li>
 *     <li>基于 {@code @Query} 的 JPQL 查询</li>
 *     <li>基于 {@code @Query} 的 Native SQL 查询</li>
 * </ol>
 * 另演示：{@code @Modifying} 更新/删除、{@code @EntityGraph} 解决 N+1、分页排序、动态查询。
 *
 * @author ljh
 * @since 2026/8/8
 */
public interface CountryRepository extends JpaRepository<Country, String>, JpaSpecificationExecutor<Country> {

    // ==================== 1. 派生查询（Derived Query） ====================
    // 方法名即查询语句，Spring Data 自动解析，无需写 SQL

    /** 按大洲查询（枚举参数） */
    List<Country> findByContinent(Continent continent);

    /** 按国家名精确查询，仅返回第一个匹配 */
    Optional<Country> findFirstByName(String name);

    /** 按人口大于给定值查询，并按人口倒序 */
    List<Country> findByPopulationGreaterThanOrderByPopulationDesc(Integer population);

    /** 按人口区间查询 */
    List<Country> findByPopulationBetween(Integer min, Integer max);

    /** 按国家名模糊查询（忽略大小写） */
    List<Country> findByNameContainingIgnoreCase(String keyword);

    /** 按大洲和人口大于等于查询 */
    List<Country> findByContinentAndPopulationGreaterThanEqual(Continent continent, Integer population);

    /** 按地区在给定集合中查询 */
    List<Country> findByRegionIn(List<String> regions);

    /** 统计某大洲国家数量 */
    long countByContinent(Continent continent);

    /** 判断是否存在指定国家码 */
    boolean existsByCode(String code);

    /** 按名称删除（派生删除：框架自动处理，无需 @Modifying 与显式事务） */
    void deleteByName(String name);

    // ==================== 2. JPQL 查询（@Query） ====================
    // JPQL 操作对象是实体及其属性，数据库无关，可移植

    /** 按大洲分组统计国家数量：返回 {@code [大洲, 数量]} 数组 */
    @Query("select c.continent, count(c) from Country c group by c.continent")
    List<Object[]> countGroupByContinent();

    /** 带命名参数（:name） */
    @Query("select c from Country c where lower(c.name) like concat('%', lower(:name), '%')")
    List<Country> findByNameLike(@Param("name") String name);

    /** 位置参数 + JPQL 字符串内直接拼 %：多个位置参数 ?1 ?2 按位置一一对应 */
    @Query("select c from Country c where c.name like %?1 or c.region like ?2")
    List<Country> findByNameLikeAndRegionLike(String name, String region);

    /** 多位置参数：?1 ?2 与方法形参顺序一一对应 */
    @Query("select c from Country c where c.population > ?1 and c.continent = ?2")
    List<Country> findByPopulationGreaterThanAndContinentParam(Integer population, Continent continent);

    /** 统计国家总数 */
    @Query("select count(c) from Country c")
    long countAll();

    /** 关联查询：一次性取回国家与其城市，避免 N+1（JOIN FETCH） */
    @Query("select distinct c from Country c left join fetch c.cities where c.code = :code")
    Optional<Country> findWithCitiesByCode(@Param("code") String code);

    // ==================== 3. Native SQL 查询（@Query + nativeQuery） ====================
    // 直接写数据库 SQL，与数据库强绑定，适合复杂/性能敏感场景

    /** 原生 SQL：按大洲查询 */
    @Query(value = "select * from country where continent = :continent", nativeQuery = true)
    List<Country> findNativeByContinent(@Param("continent") String continent);

    /** 原生 SQL + 分页：返回 Page 对象 */
    @Query(value = "select * from country where population > :population",
            countQuery = "select count(*) from country where population > :population",
            nativeQuery = true)
    Page<Country> findNativeByPopulationGreaterThan(@Param("population") Integer population, Pageable pageable);

    // ==================== 4. 修改操作（@Modifying） ====================
    // JPQL 的 update/delete 必须加 @Modifying，且需要事务

    /** JPQL 批量更新：按国家码更新人口 */
    @Modifying
    @Transactional
    @Query("update Country c set c.population = :population where c.code = :code")
    int updatePopulationByCode(@Param("code") String code, @Param("population") Integer population);

    /** JPQL 批量更新：按大洲统一提高人口（负数即减少） */
    @Modifying
    @Transactional
    @Query("update Country c set c.population = c.population + :delta where c.continent = :continent")
    int addPopulationByContinent(@Param("continent") Continent continent, @Param("delta") Integer delta);

    /** JPQL 批量删除：按人口区间 */
    @Modifying
    @Transactional
    @Query("delete from Country c where c.population < :threshold")
    int deleteByPopulationLessThan(@Param("threshold") Integer threshold);

    // ==================== 5. @EntityGraph 解决 N+1 ====================
    // 效果等同 JOIN FETCH，但可声明式复用，不污染 JPQL

    /** 一次查出国家及其城市（实体图：city），避免逐条查询 */
    @EntityGraph(attributePaths = "cities")
    @Query("select c from Country c where c.code = :code")
    Optional<Country> findWithCitiesByCodeUsingEntityGraph(@Param("code") String code);

    /** 一次查出国家及其语言 */
    @EntityGraph(attributePaths = "languages")
    @Query("select c from Country c where c.code = :code")
    Optional<Country> findWithLanguagesByCodeUsingEntityGraph(@Param("code") String code);

    // ==================== 6. 分页与排序 ====================
    // Pageable/Sort 作为方法参数，Spring Data 自动追加 LIMIT / ORDER BY

    /** 按大洲分页查询 */
    Page<Country> findByContinent(Continent continent, Pageable pageable);

    /** 投影：仅查询指定字段，减少数据传输（DTO 构造器投影） */
    @Query("select new org.ljh.jpa.projection.CountryNamePopulation(c.code, c.name, c.population) " +
            "from Country c order by c.population desc")
    List<org.ljh.jpa.projection.CountryNamePopulation> findNamePopulationProjection();

    /** 返回单个字段的投影 */
    @Query("select c.name from Country c where c.code = :code")
    Optional<String> findNameByCode(@Param("code") String code);

    /** 存在性判断 */
    boolean existsByName(String name);

    /** 返回国家码+面积的投影 */
    @Query("select new org.ljh.jpa.projection.CountryArea(c.code, c.name, c.surfaceArea) " +
            "from Country c")
    List<org.ljh.jpa.projection.CountryArea> findAreaProjection();

    /** 返回单个字段（面积） */
    @Query("select c.surfaceArea from Country c where c.code = :code")
    BigDecimal findSurfaceAreaByCode(@Param("code") String code);

    // ==================== 7. 结果限制（First / Top） ====================
    // 方法名 TopN 限制返回条数，配合排序取前 N 名

    /** 人口最多的前 3 个国家（按人口倒序取 Top 3） */
    List<Country> findTop3ByOrderByPopulationDesc();

    // ==================== 8. @Query 中 SpEL 表达式 ====================
    // #{#entityName} 在运行时替换为当前仓储管理的实体名（Country），
    // 使 JPQL 不写死实体名，增强复用性

    /** SpEL：用 #{#entityName} 引用实体名 */
    @Query("select c from #{#entityName} c where c.code = :code")
    Optional<Country> findByCodeUsingSpel(@Param("code") String code);
}
