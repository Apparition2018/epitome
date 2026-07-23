package org.ljh.mybatis.entity.normal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CountryLanguage extends CountryLanguageKey implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String isOfficial;

    private BigDecimal percentage;
}
