package org.ljh.jpa.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * ContinentConverter — 大洲枚举 ↔ 数据库字符串 转换器。
 * <p>纯 JPA 规范实现（{@code AttributeConverter}），把 {@code Continent} 枚举映射为
 * world 库 {@code country.Continent} 列存的小写字符串；反之从字符串还原枚举。
 * {@code autoApply = false} 表示不全局生效，仅对显式标注 {@code @Convert} 的字段生效，
 * 便于演示 {@code @Convert} 注解的显式用法。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Converter(autoApply = false)
public class ContinentConverter implements AttributeConverter<Continent, String> {

    @Override
    public String convertToDatabaseColumn(Continent attribute) {
        return attribute == null ? null : attribute.getCode();
    }

    @Override
    public Continent convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (Continent continent : Continent.values()) {
            if (continent.getCode().equalsIgnoreCase(dbData)) {
                return continent;
            }
        }
        throw new IllegalArgumentException("未知大洲：" + dbData);
    }
}
