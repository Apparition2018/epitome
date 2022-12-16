package springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import springboot.domain.School.Principal;
import springboot.domain.School.Student;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author ljh
 * @since 2021/8/6 17:49
 */
@SpringBootTest
public class PropertiesBindTest {

    @Autowired
    private Principal principal;
    @Autowired
    private Environment environment;

    @Test
    public void testConfigurationProperties() {
        System.out.println(principal);
    }

    @Test
    public void testBinder() {
        Binder binder = Binder.get(environment);

        String schoolName = binder.bind("school.name", Bindable.of(String.class)).get();
        System.out.println(new String(schoolName.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

        List<Student> studentList = binder.bind("school.student", Bindable.listOf(Student.class)).get();
        studentList.forEach(System.out::println);
    }

}
