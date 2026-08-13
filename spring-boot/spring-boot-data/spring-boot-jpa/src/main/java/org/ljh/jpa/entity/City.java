package org.ljh.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * City — 城市实体
 * <p>演示：自增主键（{@code @GeneratedValue IDENTITY}）、多对一外键关联
 * （{@code @ManyToOne} + {@code @JoinColumn}）、普通字段映射，
 * 以及 JPA 规范的生命周期回调注解 {@code @PrePersist}（持久化前自动补齐默认值）。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "city")
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
     * {@code @ToString.Exclude} 防止与 {@code Country.cities} 造成递归打印。</p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CountryCode")
    @ToString.Exclude
    private Country country;

    /**
     * JPA 生命周期回调：持久化前校验/补齐业务默认值（纯 JPA 规范注解）。
     * <p>注意：此方法由 Provider（Hibernate）在内部直接调用，入参无对象也能工作，
     * 它是在实体加载/持久化时被回调的。</p>
     */
    @PrePersist
    public void prePersist() {
        if (this.population == null) {
            this.population = 0;
        }
    }
}
