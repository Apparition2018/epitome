package springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "resource")
@PropertySource(value = "classpath:resource.properties")
@Getter
@Setter
@ToString
public class Resource {

    private String name;
    private int age;
    private int score;

}
