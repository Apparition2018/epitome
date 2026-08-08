package org.ljh.jpa.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * CountryNamePopulation — DTO 构造器投影
 * <p>用于 {@code select new ...} JPQL 投影，只返回所需字段，避免整行加载。</p>
 *
 * @author ljh
 * @since 2026/8/8
 */
@Data
@AllArgsConstructor
public class CountryNamePopulation {

    /** 国家码 */
    private String code;

    /** 国家名 */
    private String name;

    /** 人口 */
    private Integer population;
}
