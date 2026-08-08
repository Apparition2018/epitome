package org.ljh.jpa.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * CountryArea — DTO 构造器投影
 *
 * @author ljh
 * @since 2026/8/8
 */
@Data
@AllArgsConstructor
public class CountryArea {

    /** 国家码 */
    private String code;

    /** 国家名 */
    private String name;

    /** 面积 */
    private BigDecimal surfaceArea;
}
