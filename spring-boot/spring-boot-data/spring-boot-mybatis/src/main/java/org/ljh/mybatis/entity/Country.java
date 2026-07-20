package org.ljh.mybatis.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Country
 *
 * @author ljh
 * @since 2026/7/20 16:18
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Country {

    private String code;
    private String name;
    private Continent continent;
    private String region;
    private Double surfaceArea;
    private Integer indepYear;
    private Integer population;
    private Double lifeExpectancy;
    private Double gnp;
    private Double gnpOld;
    private String localName;
    private String governmentForm;
    private String headOfState;
    /** 对应 city.ID（H2 不支持该 FK 约束，仅保留字段） */
    private Integer capital;
    private String code2;
}
