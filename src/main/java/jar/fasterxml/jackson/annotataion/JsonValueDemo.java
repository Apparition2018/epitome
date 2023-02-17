package jar.fasterxml.jackson.annotataion;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import l.demo.Demo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * &#064;JsonValue  序列化只返回这个字段的值
 *
 * @author ljh
 * @since 2021/7/24 17:02
 */
public class JsonValueDemo extends Demo {

    public static void main(String[] args) throws JsonProcessingException {
        Person person = new Person().setName(MY_NAME).setAge(31);
        p(jsonMapper.writeValueAsString(person)); // "ljh"
    }

    @Data
    @Accessors(chain = true)
    private static class Person {
        @JsonValue
        private String name;
        private Integer age;
    }
}
