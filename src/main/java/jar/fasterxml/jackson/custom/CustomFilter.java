package jar.fasterxml.jackson.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import org.springframework.util.ReflectionUtils;

import java.util.Objects;

/**
 * CustomFilter
 *
 * @author ljh
 * @since 2021/7/24 15:01
 */
public class CustomFilter extends SimpleBeanPropertyFilter {

    @Override
    public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
        if (include(writer, pojo)) {
            writer.serializeAsField(pojo, jgen, provider);
        } else if (!jgen.canOmitFields()) {
            // since 2.3
            writer.serializeAsOmittedField(pojo, jgen, provider);
        }
    }

    protected boolean include(PropertyWriter writer, Object pojo) throws Exception {
        if ("age".equals(writer.getFullName().getSimpleName())) {
            return Integer.parseInt(Objects.requireNonNull(ReflectionUtils.findField(pojo.getClass(), "age")).get(pojo).toString()) < 18;
        }
        return super.include(writer);
    }
}
