package org.ljh.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Country — 国家实体
 * <p>JPA 标准映射演示：{@code @Entity}/{@code @Id}/{@code @Column}/{@code @OneToMany}、
 * {@code @NamedQuery}（预定义 JPQL）、{@code @Convert}（枚举转换）。所有注解均来自
 * {@code jakarta.persistence}，仅依赖 JPA 规范 API。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "country")
@NamedQuery(name = "Country.byContinent",
        query = "select c from Country c where c.continent = :continent order by c.population desc")
@NamedQuery(name = "Country.count", query = "select count(c) from Country c")
public class Country implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键：ISO 3166-1 三位国家码，如 CHN */
    @Id
    @Column(name = "Code", length = 3)
    private String code;

    /** 国家名 */
    @Column(name = "Name", length = 52, nullable = false)
    private String name;

    /** 所属大洲：通过 {@link ContinentConverter} 与数据库字符串互转 */
    @Convert(converter = ContinentConverter.class)
    @Column(name = "Continent", length = 30, nullable = false)
    private Continent continent;

    /** 地区，如 Eastern Asia */
    @Column(name = "Region", length = 26)
    private String region;

    /** 国土面积 */
    @Column(name = "SurfaceArea", precision = 10, scale = 2)
    private BigDecimal surfaceArea;

    /** 人口 */
    @Column(name = "Population")
    private Integer population;

    /**
     * 一对多：一个国家包含多个城市。
     * <p>{@code mappedBy} 表示由 {@link City#getCountry()} 维护外键；级联持久化与删除；
     * 延迟加载；集合按人口倒序。纯 JPA 标准注解。</p>
     */
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("population DESC")
    @ToString.Exclude
    private List<City> cities = new ArrayList<>();

    /** 添加城市并维护反向引用 */
    public Country addCity(City city) {
        cities.add(city);
        city.setCountry(this);
        return this;
    }

    /** 移除城市并解除反向引用 */
    public Country removeCity(City city) {
        cities.remove(city);
        city.setCountry(null);
        return this;
    }
}
