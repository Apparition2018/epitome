package org.ljh.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    private Continent continent;

    private String region;

    private BigDecimal surfaceArea;

    private Short indepYear;

    private Integer population;

    private BigDecimal lifeExpectancy;

    private BigDecimal GNP;

    private BigDecimal GNPOld;

    private String localName;

    private String governmentForm;

    private String headOfState;

    private Integer capital;

    private String code2;
}
