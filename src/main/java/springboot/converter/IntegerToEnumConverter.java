package springboot.converter;

import com.google.common.collect.Maps;
import l.demo.BaseEnum;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;

import java.util.Map;

/**
 * 整数转枚举 Converter
 *
 * @author ljh
 * @since 2021/6/17 16:58
 */
public class IntegerToEnumConverter<T extends BaseEnum> implements Converter<Integer, T> {

    private final Map<Integer, T> ENUM_MAP = Maps.newHashMap();

    public IntegerToEnumConverter(Class<T> enumType) {
        T[] enumConstants = enumType.getEnumConstants();
        for (T enumConstant : enumConstants) {
            ENUM_MAP.put(enumConstant.getCode(), enumConstant);
        }
    }

    @Override
    public T convert(@NonNull Integer source) {
        return ENUM_MAP.get(source);
    }
}
