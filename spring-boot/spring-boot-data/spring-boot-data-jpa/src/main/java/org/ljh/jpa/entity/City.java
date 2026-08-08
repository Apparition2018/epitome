package org.ljh.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * City — 城市实体
 * <p>演示：自增主键、多对一外键关联、普通字段映射。</p>
 *
 * @author ljh
 * @since 2026/8/8
 */
@Data
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
     * {@code @ToString.Exclude} 防止 {@code Country.cities} 造成递归打印。</p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CountryCode")
    @ToString.Exclude
    private Country country;
}
