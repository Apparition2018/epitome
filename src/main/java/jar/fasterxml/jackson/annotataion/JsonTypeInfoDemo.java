package jar.fasterxml.jackson.annotataion;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import l.demo.Demo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * `@JsonTypeInfo：处理多态的序列化和反序列化
 * https://www.jianshu.com/p/4003b9756952
 *
 * @author ljh
 * created on 2021/7/24 17:09
 */
public class JsonTypeInfoDemo extends Demo {

    @Test
    public void testJsonTypeInfo() throws JsonProcessingException {
        Circle circle = new Circle().setRadius("12");
        Rectangle rectangle = new Rectangle().setLength("12").setWidth("13");
        View view = new View().setSharps(Lists.newArrayList(circle, rectangle));

        String json = jsonMapper.writeValueAsString(view);
        p(json);
        // {"sharps":[{"radius":"12"},{"length":"12","width":"13"}]}

        view = jsonMapper.readValue(json, View.class);
        p(view);
    }

    @Data
    @Accessors(chain = true)
    static class View {
        private List<Sharp> sharps;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "name")
    @JsonSubTypes(value = {@JsonSubTypes.Type(value = Circle.class), @JsonSubTypes.Type(value = Rectangle.class)})
    static abstract class Sharp {
    }

    @Data
    @Accessors(chain = true)
    @JsonTypeName("circleType")
    static class Circle extends Sharp {
        private String radius;
    }

    @Data
    @Accessors(chain = true)
    @JsonTypeName("rectangleType")
    static class Rectangle extends Sharp {
        private String length;
        private String width;
    }
}
