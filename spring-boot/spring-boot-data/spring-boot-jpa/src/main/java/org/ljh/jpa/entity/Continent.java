package org.ljh.jpa.entity;

/**
 * Continent — 大洲枚举（值类型，不映射表）。
 * <p>配合 {@link ContinentConverter} 在 Java 枚举与数据库字符串之间做转换，
 * 全部使用 JPA 规范 {@code @Converter}（{@code jakarta.persistence.Converter}）。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
public enum Continent {
    ASIA("Asia"),
    EUROPE("Europe"),
    NORTH_AMERICA("North America"),
    AFRICA("Africa"),
    OCEANIA("Oceania"),
    ANTARCTICA("Antarctica"),
    SOUTH_AMERICA("South America");

    /** 与 world 库 {@code country.Continent} 列一致的存储值 */
    private final String code;

    Continent(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
