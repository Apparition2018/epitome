package org.ljh.mybatis.entity.dynamic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryLanguage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String countryCode;

    private String language;

    private String isOfficial;

    private BigDecimal percentage;
}
