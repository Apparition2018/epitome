package org.ljh.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * CountryLanguageId — 复合主键（配合 {@code @IdClass} 使用）
 * <p>{@code countrylanguage} 表的主键 {@code (CountryCode, Language)}。
 * {@code @IdClass} 方式要求该类：可序列化、有无参构造、实现 {@code equals}/{@code hashCode}。</p>
 *
 * @author ljh
 * @since 2026/8/9
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryLanguageId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 国家码 */
    private String countryCode;

    /** 语言名 */
    private String language;
}
