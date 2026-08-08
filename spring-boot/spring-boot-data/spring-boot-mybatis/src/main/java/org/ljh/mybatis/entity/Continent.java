package org.ljh.mybatis.entity;

import lombok.Getter;

/**
 * 大洲枚举
 *
 * @author ljh
 * @since 2026/7/20 16:15
 */
@Getter
public enum Continent {

    /** 亚洲 */
    ASIA("Asia"),
    /** 欧洲 */
    EUROPE("Europe"),
    /** 非洲 */
    AFRICA("Africa"),
    /** 北美洲 */
    NORTH_AMERICA("North America"),
    /** 南美洲 */
    SOUTH_AMERICA("South America"),
    /** 大洋洲 */
    OCEANIA("Oceania"),
    /** 南极洲 */
    ANTARCTICA("Antarctica");

    private final String value;

    Continent(String value) {
        this.value = value;
    }

    /** 由数据库字符串值反查枚举 */
    public static Continent fromValue(String value) {
        for (Continent c : values()) {
            if (c.value.equals(value)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown continent: " + value);
    }
}
