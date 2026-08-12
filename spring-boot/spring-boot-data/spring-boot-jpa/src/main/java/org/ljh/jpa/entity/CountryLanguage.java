package org.ljh.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * CountryLanguage — 国家语言实体（映射 world 库 {@code countrylanguage} 表）。
 * <p>演示复合主键的 {@code @IdClass} 方式：主键类为 {@link CountryLanguageId}，
 * 实体上通过 {@code @IdClass} 声明，并给两个主键字段分别标 {@code @Id}。
 * JPA 据此把 {@code (CountryCode, Language)} 组合成持久化标识。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "countrylanguage")
@IdClass(CountryLanguageId.class)
public class CountryLanguage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 复合主键之一：国家码 */
    @Id
    @Column(name = "CountryCode", length = 3)
    private String countryCode;

    /** 复合主键之二：语言 */
    @Id
    @Column(name = "Language", length = 30)
    private String language;

    /** 是否官方语言：T/F */
    @Column(name = "IsOfficial", length = 1)
    private String isOfficial;

    /** 使用该语言的百分比 */
    @Column(name = "Percentage", precision = 4, scale = 1)
    private BigDecimal percentage;
}
