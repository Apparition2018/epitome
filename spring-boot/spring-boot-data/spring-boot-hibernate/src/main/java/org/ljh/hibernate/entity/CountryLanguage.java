package org.ljh.hibernate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * CountryLanguage — 国家语言实体
 * <p>演示 {@code @IdClass} 复合主键 + 与 {@link Country} 的一对多关联。
 * 复合主键字段（countryCode）同时作为外键关联国家。</p>
 *
 * @author ljh
 * @since 2026/8/9
 */
@Data
@Entity
@Table(name = "countrylanguage")
@IdClass(CountryLanguageId.class)
// equals/hashCode 仅基于复合主键字段，与 @IdClass 保持一致，避免引入非主键字段造成集合误判
@EqualsAndHashCode(of = {"countryCode", "language"})
public class CountryLanguage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 复合主键一部分：国家码，同时是外键 */
    @Id
    @Column(name = "CountryCode", length = 3)
    private String countryCode;

    /** 复合主键另一部分：语言名 */
    @Id
    @Column(name = "Language", length = 30)
    private String language;

    /** 是否官方语言（T/F） */
    @Column(name = "IsOfficial", length = 1)
    private String isOfficial;

    /** 使用该语言的人口占比 */
    @Column(name = "Percentage", precision = 4, scale = 1)
    private BigDecimal percentage;

    /**
     * 关联国家。此字段是 {@code @IdClass} 的一部分（{@code CountryCode}），
     * 同时作为多对一关联的引用。
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CountryCode", insertable = false, updatable = false)
    @ToString.Exclude
    private Country country;

    /** 便捷返回复合主键对象 */
    public CountryLanguageId getId() {
        return new CountryLanguageId(countryCode, language);
    }

    /** 便捷设置复合主键对象 */
    public void setId(CountryLanguageId id) {
        this.countryCode = id.getCountryCode();
        this.language = id.getLanguage();
    }
}
