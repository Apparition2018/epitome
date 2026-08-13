package org.ljh.hibernate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Country — 国家实体
 * <p>在标准 JPA 映射之上，演示 Hibernate 特有注解：</p>
 * <ul>
 *     <li>{@code @NaturalId}：把业务唯一键（国家码 Code）声明为自然 ID，可用
 *         {@code session.byNaturalId()} 直接按自然键查询，无需自增主键。</li>
 *     <li>{@code @NaturalIdCache(region = "countryNaturalId")}：为自然 ID 建立二级缓存映射
 *         （配合 {@code @Cache} 二级缓存）。显式指定 region 是为避开默认的
 *         {@code 实体##NaturalId} 区域名——Caffeine jcache 会把缓存名当作 HOCON 路径解析，
 *         {@code ##} 是注释起始符会触发 BadPath 启动失败（该缺陷在 caffeine 3.2.4 仍存在，
 *         且无更新版本可升级，故必须绕开默认区域名）。</li>
 *     <li>{@code @Cache(usage = READ_WRITE)}：开启二级缓存，跨 Session 共享实体实例。</li>
 *     <li>{@code @NamedQuery}：预定义 HQL 查询，按名字复用，避免字符串散落在代码各处。</li>
 * </ul>
 *
 * @author ljh
 * @since 2026/8/9
 */
@Data
@Entity
@Table(name = "country")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NaturalIdCache(region = "countryNaturalId")
@NamedQuery(name = "Country.byContinent", query = "select c from Country c where c.continent = :continent order by c.population desc")
@NamedQuery(name = "Country.count", query = "select count(c) from Country c")
public class Country implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键：ISO 3166-1 三位国家码，如 CHN */
    @Id
    @Column(name = "Code", length = 3)
    private String code;

    /** 自然 ID：国家名，业务唯一，可作 {@code @NaturalId} 查询依据 */
    @NaturalId
    @Column(name = "Name", length = 52, nullable = false)
    private String name;

    /** 所属大洲：通过 {@link ContinentConverter} 与数据库字符串互转 */
    @Column(name = "Continent", length = 30, nullable = false)
    private Continent continent;

    /** 地区，如 Eastern Asia */
    @Column(name = "Region", length = 26)
    private String region;

    /** 国土面积 */
    @Column(name = "SurfaceArea", precision = 10, scale = 2)
    private BigDecimal surfaceArea;

    /** 独立年份 */
    @Column(name = "IndepYear")
    private Short indepYear;

    /** 人口 */
    @Column(name = "Population")
    private Integer population;

    /** 平均寿命 */
    @Column(name = "LifeExpectancy", precision = 3, scale = 1)
    private BigDecimal lifeExpectancy;

    /** 国民生产总值 */
    @Column(name = "GNP", precision = 10, scale = 2)
    private BigDecimal gnp;

    /** 本地名 */
    @Column(name = "LocalName", length = 45)
    private String localName;

    /** 政体 */
    @Column(name = "GovernmentForm", length = 45)
    private String governmentForm;

    /** 双字母国家码 */
    @Column(name = "Code2", length = 2)
    private String code2;

    /**
     * 一对多：一个国家包含多个城市。
     * <p>{@code mappedBy} 表示由 {@link City#country} 维护外键；
     * 级联持久化与删除；延迟加载；集合按人口倒序；
     * {@code @Cache(READ_WRITE)} 把集合内元素的主键列表也放进二级缓存（需配合 {@code City} 的 {@code @Cache}）。</p>
     */
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OrderBy("population DESC")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<City> cities = new ArrayList<>();

    // ==================== 双向关联维护工具方法 ====================

    /** 添加城市，同时维护反向引用 */
    public Country addCity(City city) {
        cities.add(city);
        city.setCountry(this);
        return this;
    }

    /** 移除城市，同时解除反向引用 */
    public Country removeCity(City city) {
        cities.remove(city);
        city.setCountry(null);
        return this;
    }
}
