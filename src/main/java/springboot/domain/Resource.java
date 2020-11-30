package springboot.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Data
@Component
@ConfigurationProperties(prefix = "resource")
@PropertySource(value = "classpath:resource.properties")
public class Resource {

    private String name;
    private int age;
    private int score;

}
