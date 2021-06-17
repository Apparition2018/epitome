package springboot.converter;

import com.google.common.collect.Maps;
import l.demo.BaseEnum;
import org.springframework.core.convert.converter.Converter;

import java.util.Map;

/**
 * 字符串转枚举 Converter
 *
 * @author ljh
 * created on 2021/6/17 16:39
 */
public class StringToEnumConverter<T extends BaseEnum> implements Converter<String, T> {

    private final Map<String, T> ENUM_MAP = Maps.newHashMap();

    public StringToEnumConverter(Class<T> enumType) {
        T[] enumConstants = enumType.getEnumConstants();
        for (T enumConstant : enumConstants) {
            ENUM_MAP.put(enumConstant.getCode().toString(), enumConstant);
        }
    }

    @Override
    public T convert(String source) {
        return ENUM_MAP.get(source);
    }
}
