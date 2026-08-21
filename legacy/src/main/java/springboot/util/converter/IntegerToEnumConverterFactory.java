package springboot.util.converter;

import com.ljh.commons.BaseEnum;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 整数转枚举 ConverterFactory
 *
 * @author ljh
 * @since 2021/6/17 17:00
 */
public class IntegerToEnumConverterFactory implements ConverterFactory<Integer, BaseEnum> {

    @SuppressWarnings("rawtypes")
    private static final Map<Class<?>, Converter> CONVERTER_MAP = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BaseEnum> @NonNull Converter<Integer, T> getConverter(@NonNull Class<T> targetType) {
        Converter<Integer, T> converter = CONVERTER_MAP.get(targetType);
        if (converter == null) {
            converter = new IntegerToEnumConverter<>(targetType);
            CONVERTER_MAP.put(targetType, converter);
        }
        return converter;
    }
}
