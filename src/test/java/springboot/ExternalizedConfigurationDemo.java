package springboot;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static l.demo.Demo.pe;

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
@SpringBootTest(classes = EpitomeApplication.class)
public class ExternalizedConfigurationDemo {

    @Autowired
    private School.Principal principal;
    @Autowired
    private Environment environment;

    @Test
    public void testEnvironment() {
        String schoolName = environment.getProperty("school.name");
        pe(new String(Objects.requireNonNull(schoolName).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
    }

    @Test
    public void testBinder() {
        Binder binder = Binder.get(environment);

        String schoolName = binder.bind("school.name", Bindable.of(String.class)).get();
        pe(new String(schoolName.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

        List<School.Student> studentList = binder.bind("school.student", Bindable.listOf(School.Student.class)).get();
        studentList.forEach(System.err::println);
    }

    @Test
    public void testConfigurationProperties() {
        pe(principal);
    }

    @Data
    private static class School {
        private String name;

        @Data
        @Component
        // Using @PropertySource：https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-using-propertysource
        // @PropertySource with YAML Files in Spring Boot：https://www.baeldung.com/spring-yaml-propertysource
        @PropertySource(value = ResourceUtils.CLASSPATH_URL_PREFIX + "school.properties")
        @ConfigurationProperties(prefix = "school.principal")
        public static class Principal {
            private String name;
            private int age;
        }

        @Data
        public static class Student {
            private String name;
            private int age;
            private int score;
        }
    }
}
