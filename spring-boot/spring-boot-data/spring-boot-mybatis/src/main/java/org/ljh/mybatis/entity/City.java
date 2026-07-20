package org.ljh.mybatis.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * City
 *
 * @author ljh
 * @since 2026/7/20 16:16
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class City {

    private Integer id;
    private String name;
    private String countryCode;
    private String district;
    private Integer population;
}
