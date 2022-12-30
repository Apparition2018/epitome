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
import java.util.Objects;

/**
 * <p><a href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.external-config">Externalized Configuration</a> 外部化配置
 * <pre>
 * 1 @Value
 * 2 <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-environment">Environment</a>
 * 3 <a href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.external-config.typesafe-configuration-properties">@ConfigurationProperties</a>
 * </pre>
 *
 * @author ljh
 * @since 2021/8/6 17:49
 */
@SpringBootTest
public class ExternalizedConfigurationTest {

    @Autowired
    private Principal principal;
    @Autowired
    private Environment environment;

    @Test
    public void testEnvironment() {
        String schoolName = environment.getProperty("school.name");
        System.err.println(new String(Objects.requireNonNull(schoolName).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
    }

    @Test
    public void testBinder() {
        Binder binder = Binder.get(environment);

        String schoolName = binder.bind("school.name", Bindable.of(String.class)).get();
        System.err.println(new String(schoolName.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

        List<Student> studentList = binder.bind("school.student", Bindable.listOf(Student.class)).get();
        studentList.forEach(System.err::println);
    }

    @Test
    public void testConfigurationProperties() {
        System.err.println(principal);
    }
}
