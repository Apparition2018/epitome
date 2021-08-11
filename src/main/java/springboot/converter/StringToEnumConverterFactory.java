package springboot.converter;

import com.google.common.collect.Maps;
import l.demo.BaseEnum;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Map;

/**
 * 字符串转枚举 ConverterFactory
 *
 * @author ljh
 * created on 2021/6/17 16:31
 */
public class StringToEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    
    private static final Map<Class<?>, Converter> CONVERTER_MAP = Maps.newHashMap();

    @Override
    public <T extends BaseEnum> @NonNull Converter<String, T> getConverter(@NonNull Class<T> targetType) {
        Converter<String, T> converter = CONVERTER_MAP.get(targetType);
        if (converter == null) {
            converter = new StringToEnumConverter<>(targetType);
            CONVERTER_MAP.put(targetType, converter);
        }
        return converter;
    }
}
