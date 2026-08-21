package springboot.util.converter;

import com.ljh.commons.BaseEnum;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串转枚举 Converter
 *
 * @author ljh
 * @since 2021/6/17 16:39
 */
public class StringToEnumConverter<T extends BaseEnum> implements Converter<String, T> {

    private final Map<String, T> ENUM_MAP = new HashMap<>();

    public StringToEnumConverter(Class<T> enumType) {
        T[] enumConstants = enumType.getEnumConstants();
        for (T enumConstant : enumConstants) {
            ENUM_MAP.put(enumConstant.getCode().toString(), enumConstant);
        }
    }

    @Override
    public T convert(@NonNull String source) {
        return ENUM_MAP.get(source);
    }
}
