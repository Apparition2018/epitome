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

    ASIA("Asia"),
    EUROPE("Europe"),
    NORTH_AMERICA("North America"),
    AFRICA("Africa"),
    OCEANIA("Oceania"),
    ANTARCTICA("Antarctica"),
    SOUTH_AMERICA("South America");

    private final String value;

    Continent(String value) {
        this.value = value;
    }

    public static Continent fromValue(String value) {
        for (Continent c : values()) {
            if (c.value.equals(value)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown continent: " + value);
    }
}
