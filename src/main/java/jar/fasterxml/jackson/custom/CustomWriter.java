package jar.fasterxml.jackson.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import com.fasterxml.jackson.databind.util.Annotations;
import lombok.NoArgsConstructor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * CustomWriter
 *
 * @author ljh
 * created on 2021/7/24 17:54
 */
@NoArgsConstructor
public class CustomWriter extends VirtualBeanPropertyWriter {

    public CustomWriter(BeanPropertyDefinition propDef, Annotations contextAnnotations, JavaType declaredType) {
        super(propDef, contextAnnotations, declaredType);
    }

    @Override
    protected Object value(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Field nameFiled = ReflectionUtils.findField(o.getClass(), "name");
        ReflectionUtils.makeAccessible(Objects.requireNonNull(nameFiled));
        return nameFiled.get(o).toString().length() > 2 ? "1.2" : "1.0";
    }

    @Override
    public VirtualBeanPropertyWriter withConfig(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, BeanPropertyDefinition beanPropertyDefinition, JavaType javaType) {
        return new CustomWriter(beanPropertyDefinition, annotatedClass.getAnnotations(), javaType);
    }
}
