package org.ljh.jpa.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * CountryLanguageId — 复合主键（配合 {@code @IdClass} 使用）。
 * <p>JPA 规范要求：主键类必须 {@code public}、无参构造、实现 {@code Serializable}，
 * 且须正确重写 {@code equals}/{@code hashCode}。本类对应 world 库
 * {@code countrylanguage} 表的主键（CountryCode, Language）两列。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
public class CountryLanguageId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String countryCode;

    private String language;

    public CountryLanguageId() {
    }

    public CountryLanguageId(String countryCode, String language) {
        this.countryCode = countryCode;
        this.language = language;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CountryLanguageId that)) {
            return false;
        }
        return Objects.equals(countryCode, that.countryCode)
                && Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, language);
    }
}
