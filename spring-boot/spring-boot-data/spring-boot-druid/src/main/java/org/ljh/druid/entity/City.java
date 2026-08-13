package org.ljh.druid.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * City — 城市实体
 * <p>映射 commons 模块 world-db 中的 {@code city} 表
 * （ID / Name / CountryCode / District / Population）。</p>
 *
 * @author ljh
 * @since 2026/8/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    private Integer id;

    private String name;

    private String countryCode;

    private String district;

    private Integer population;
}
