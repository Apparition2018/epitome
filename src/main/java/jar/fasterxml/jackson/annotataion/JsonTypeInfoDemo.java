package jar.fasterxml.jackson.annotataion;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class JsonTypeInfoDemo {

    @Test
    public void testJsonTypeInfo() throws JsonProcessingException {
        Circle circle = new Circle("12");
        Rectangle rectangle = new Rectangle("12", "13");
        View view = new View(Lists.newArrayList(circle, rectangle));

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(view);
        System.out.println(json);
        // {"sharps":[{"radius":"12"},{"length":"12","width":"13"}]}

        view = objectMapper.readValue(json, View.class);
        System.out.println(view);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class View {
        private List<Sharp> sharps;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "name")
    @JsonSubTypes(value = {@JsonSubTypes.Type(value = Circle.class), @JsonSubTypes.Type(value = Rectangle.class)})
    abstract static class Sharp {

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonTypeName("circleType")
    static class Circle extends Sharp {
        private String radius;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonTypeName("rectangleType")
    static class Rectangle extends Sharp {
        private String length;
        private String width;
    }
}
