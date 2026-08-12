package org.ljh.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.ljh.jpa.entity.Continent;
import org.ljh.jpa.entity.Country;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * CountryDao — 纯 JPA 数据访问（只使用标准 {@code EntityManager}，不用 Spring Data Repository）。
 * <p>集中演示 EntityManager 生命周期方法与查询方式：</p>
 * <ul>
 *     <li>持久化生命周期：{@code persist}/{@code find}/{@code getReference}/{@code merge}/
 *         {@code remove}/{@code flush}/{@code refresh}/{@code detach}/{@code contains}/{@code clear}</li>
 *     <li>查询：JPQL（{@code createQuery}）、命名查询（{@code createNamedQuery}）、
 *         原生 SQL（{@code createNativeQuery}）、分页（{@code setFirstResult}/{@code setMaxResults}）</li>
 *     <li>Criteria API：{@code CriteriaBuilder}/{@code CriteriaQuery}/{@code Root}/{@code Predicate}</li>
 * </ul>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Repository
public class CountryDao {

    /** 注入当前事务绑定的 EntityManager（JPA 标准 {@code @PersistenceContext}） */
    @PersistenceContext
    private EntityManager em;

    // ==================== 持久化生命周期 ====================

    /** 新增（persist）：新实体进入受管状态，事务提交时 flush 到库 */
    public void save(Country country) {
        em.persist(country);
    }

    /** 按主键查询（find）：若已在一级缓存则直接返回，否则查库 */
    public Country findById(String code) {
        return em.find(Country.class, code);
    }

    /**
     * 按主键获取引用（getReference）：仅生成代理，不立即查库，
     * 访问其属性时才触发 SQL（延迟初始化）。
     */
    public Country getReference(String code) {
        return em.getReference(Country.class, code);
    }

    /**
     * 更新/合并（merge）：把脱管（detached）实体的改动合并回受管状态。
     * 若数据库无此记录则当作新增插入。
     */
    public Country update(Country country) {
        return em.merge(country);
    }

    /** 删除（remove）：实体须处于受管状态 */
    public void remove(Country country) {
        em.remove(country);
    }

    /**
     * 刷新（refresh）：用数据库最新行覆盖实体当前内存状态，
     * 常用于丢弃本地未提交修改或重新读取被外部修改的数据。
     */
    public void refresh(Country country) {
        em.refresh(country);
    }

    /** 判断实体是否处于受管状态（在同一持久化上下文/一级缓存中） */
    public boolean contains(Country country) {
        return em.contains(country);
    }

    /** 脱管（detach）：将实体从当前持久化上下文移除，后续修改不再自动同步 */
    public void detach(Country country) {
        em.detach(country);
    }

    // ==================== JPQL ====================

    /** JPQL：命名参数查询 */
    public List<Country> findByContinent(Continent continent) {
        TypedQuery<Country> query = em.createQuery(
                "select c from Country c where c.continent = :continent", Country.class);
        query.setParameter("continent", continent);
        return query.getResultList();
    }

    /** JPQL：命名参数 + 分页 */
    public List<Country> pageByContinent(Continent continent, int first, int max) {
        TypedQuery<Country> query = em.createQuery(
                "select c from Country c where c.continent = :continent order by c.population desc", Country.class);
        query.setParameter("continent", continent);
        query.setFirstResult(first);
        query.setMaxResults(max);
        return query.getResultList();
    }

    // ==================== 命名查询（@NamedQuery） ====================

    /** 使用实体上预定义的命名查询 */
    public List<Country> byContinentNamed(Continent continent) {
        TypedQuery<Country> query = em.createNamedQuery("Country.byContinent", Country.class);
        query.setParameter("continent", continent);
        return query.getResultList();
    }

    /** 聚合：统计国家总数 */
    public long countAll() {
        return em.createNamedQuery("Country.count", Long.class).getSingleResult();
    }

    // ==================== 原生 SQL ====================

    /** 原生 SQL 查询，按列名映射到实体 */
    public List<Country> nativeFindAll() {
        Query query = em.createNativeQuery(
                "select * from country where Continent = ?1 order by Population desc", Country.class);
        query.setParameter(1, "Asia");
        return query.getResultList();
    }

    // ==================== 批量更新（JPQL update） ====================

    /** JPQL 批量更新：返回受影响行数 */
    public int updateContinent(String code, Continent continent) {
        return em.createQuery("update Country c set c.continent = :continent where c.code = :code")
                .setParameter("continent", continent)
                .setParameter("code", code)
                .executeUpdate();
    }

    // ==================== Criteria API ====================

    /**
     * Criteria API：动态拼装查询（Java 类型安全，避免字符串拼接）。
     * 示例：按国家码精确 + 按大洲动态过滤 + 按人口排序。
     */
    public List<Country> searchByCriteria(String codePrefix, Continent continent, int max) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        Root<Country> root = cq.from(Country.class);

        List<Predicate> predicates = new ArrayList<>();
        if (codePrefix != null && !codePrefix.isBlank()) {
            predicates.add(cb.like(root.get("code"), codePrefix + "%"));
        }
        if (continent != null) {
            predicates.add(cb.equal(root.get("continent"), continent));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("population")));

        return em.createQuery(cq).setMaxResults(max).getResultList();
    }
}
