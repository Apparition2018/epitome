package springboot.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

/**
 * School
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class School {

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
