package org.ljh.hibernate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serial;
import java.io.Serializable;

/**
 * City — 城市实体
 * <p>演示：自增主键、多对一外键关联、普通字段映射；{@code @Cache} 开启实体级二级缓存，
 * 配合 {@link Country#getCities()} 上的集合缓存，实现「国家→城市列表」整体缓存。</p>
 *
 * @author ljh
 * @since 2026/8/9
 */
@Data
@Entity
@Table(name = "city")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class City implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    /** 城市名 */
    @Column(name = "Name", length = 35, nullable = false)
    private String name;

    /** 行政区 / 省份，如 Peking */
    @Column(name = "District", length = 20)
    private String district;

    /** 人口 */
    @Column(name = "Population")
    private Integer population;

    /**
     * 多对一：多个城市属于一个国家。
     * <p>{@code @JoinColumn} 指定外键列 {@code CountryCode}；默认延迟加载；
     * {@code @ToString.Exclude} 防止 {@code Country.cities} 造成递归打印。</p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CountryCode")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Country country;
}
