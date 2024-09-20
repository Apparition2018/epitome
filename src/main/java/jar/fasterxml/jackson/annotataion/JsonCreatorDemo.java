package jar.fasterxml.jackson.annotataion;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import l.demo.Demo;
import lombok.Data;

/**
 * {@code @JsonCreator}<br/>
 * Jackson 反序列化时，会默认使用无参构造函数实例化对象；<br/>
 * 当没有无参构造函数时可使用 {@code @JsonCreator} 来指定反序列化时候的构造函数，需要配合 {@code @JsonProperty} 使用
 *
 * @author ljh
 * @since 2021/7/24 17:00
 */
public class JsonCreatorDemo extends Demo {

    public static void main(String[] args) throws JsonProcessingException {
        Person person = jsonMapper.readValue("{\"name\":\"ljh\",\"age\":31}", Person.class);
        p(person); // JsonCreatorDemo.Person(name=super ljh, age=31)
    }

    @Data
    private static class Person {
        private String name;
        private Integer age;

        @JsonCreator
        public Person(@JsonProperty("name") String name) {
            if (MY_NAME.equals(name)) name = "super" + name;
            this.name = name;
        }
    }
}
