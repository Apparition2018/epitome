package jar.jackson.annotataion;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import l.demo.Demo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * <a href="https://www.jianshu.com/p/4003b9756952">@JsonTypeInfo</a>
 * <p>处理多态的序列化和反序列化
 *
 * @author ljh
 * @since 2021/7/24 17:09
 */
public class JsonTypeInfoDemo extends Demo {

    public static void main(String[] args) throws JsonProcessingException {
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
    private static class View {
        private List<Sharp> sharps;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "name")
    @JsonSubTypes(value = {@JsonSubTypes.Type(value = Circle.class), @JsonSubTypes.Type(value = Rectangle.class)})
    private static abstract class Sharp {
    }

    @Data
    @Accessors(chain = true)
    @JsonTypeName("circleType")
    private static class Circle extends Sharp {
        private String radius;
    }

    @Data
    @Accessors(chain = true)
    @JsonTypeName("rectangleType")
    private static class Rectangle extends Sharp {
        private String length;
        private String width;
    }
}
