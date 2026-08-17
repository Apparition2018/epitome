package org.ljh.jpa.entity;

import com.ljh.commons.Continent;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * ContinentConverter — 大洲枚举与数据库字符串互转
 * <p>{@code country.Continent} 列存的是 {@code North America} 等字符串，
 * 与枚举名（{@code NORTH_AMERICA}）不一致，故用 {@code AttributeConverter} 做双向映射。</p>
 *
 * @author ljh
 * @since 2026/8/8
 */
@Converter(autoApply = true)
public class ContinentConverter implements AttributeConverter<Continent, String> {

    @Override
    public String convertToDatabaseColumn(Continent attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public Continent convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Continent.fromValue(dbData);
    }
}
