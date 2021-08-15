package jar.fasterxml.jackson.annotataion;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;

/**
 * `@JsonValue：序列化只返回这个字段的值
 *
 * @author ljh
 * created on 2021/7/24 17:02
 */
public class JsonValueDemo {

    @Test
    public void testJsonValue() throws JsonProcessingException {
        Person person = new Person().setName("ljh").setAge(31);
        System.out.println(new ObjectMapper().writeValueAsString(person)); // "ljh"
    }

    @Data
    @Accessors(chain = true)
    static class Person {
        @JsonValue
        private String name;
        private Integer age;

    }
}
