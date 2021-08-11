package springboot.converter;

import com.google.common.collect.Maps;
import l.demo.BaseEnum;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Map;

/**
 * 整数转枚举 ConverterFactory
 *
 * @author ljh
 * created on 2021/6/17 17:00
 */
public class IntegerToEnumConverterFactory implements ConverterFactory<Integer, BaseEnum> {

    private static final Map<Class<?>, Converter> CONVERTER_MAP = Maps.newHashMap();

    @Override
    public <T extends BaseEnum> @NonNull Converter<Integer, T> getConverter(@NonNull Class<T> targetType) {
        Converter<Integer, T> converter = CONVERTER_MAP.get(targetType);
        if (converter == null) {
            converter = new IntegerToEnumConverter<>(targetType);
            CONVERTER_MAP.put(targetType, converter);
        }
        return converter;
    }
}
