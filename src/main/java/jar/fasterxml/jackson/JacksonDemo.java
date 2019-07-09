package jar.fasterxml.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import java.io.IOException;

/**
 * Jackson教程
 * https://www.yiibai.com/jackson
 */
public class JacksonDemo {

    @Test
    public void test() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";

        //map json to student
        try {
            Student student = mapper.readValue(jsonString, Student.class);
            System.out.println(student);

            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonString = mapper.writeValueAsString(student);
            System.out.println(jsonString);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

@Getter
@Setter
@ToString
class Student {
    private String name;
    private int age;
}
