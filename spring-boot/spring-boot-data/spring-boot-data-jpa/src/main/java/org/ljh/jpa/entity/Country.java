package org.ljh.jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Country — 国家实体
 * <p>演示：主键、一对多关联（城市/语言）、{@code AttributeConverter} 枚举映射、级联操作与双向引用维护。</p>
 *
 * @author ljh
 * @since 2026/8/8
 */
@Data
@Entity
@Table(name = "country")
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

    /** 上一年国民生产总值 */
    @Column(name = "GNPOld", precision = 10, scale = 2)
    private BigDecimal gnpOld;

    /** 本地名 */
    @Column(name = "LocalName", length = 45)
    private String localName;

    /** 政体 */
    @Column(name = "GovernmentForm", length = 45)
    private String governmentForm;

    /** 国家元首 */
    @Column(name = "HeadOfState", length = 60)
    private String headOfState;

    /** 首都对应的 city.ID */
    @Column(name = "Capital")
    private Integer capital;

    /** 双字母国家码 */
    @Column(name = "Code2", length = 2)
    private String code2;

    /**
     * 一对多：一个国家包含多个城市。
     * <p>{@code mappedBy} 表示由 {@link City#country} 维护外键；
     * 级联持久化与删除；延迟加载；集合按人口倒序。</p>
     */
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("population DESC")
    @ToString.Exclude
    private List<City> cities = new ArrayList<>();

    /**
     * 一对多：一个国家使用多种官方/非官方语言。
     * <p>目标表用 {@code @IdClass} 原生复合主键。</p>
     */
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<CountryLanguage> languages = new ArrayList<>();

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

    /** 添加语言，同时维护反向引用与复合主键中的国家码 */
    public Country addLanguage(CountryLanguage language) {
        languages.add(language);
        language.setCountry(this);
        language.setCountryCode(code);
        return this;
    }
}
