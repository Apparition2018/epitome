package org.ljh.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * CountryLanguage
 *
 * @author ljh
 * @since 2026/7/20 16:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CountryLanguage implements Serializable {

    private String countryCode;
    private String language;
    /** 'T' = 官方语言, 'F' = 非官方 */
    private String isOfficial;
    private Double percentage;
}
