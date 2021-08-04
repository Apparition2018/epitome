package springboot.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Data
@Component
// 加载指定的属性文件：https://blog.csdn.net/swpu_ocean/article/details/79243591
@PropertySource(value = ResourceUtils.CLASSPATH_URL_PREFIX + "resource.properties")
// 指定属性前缀：https://www.cnblogs.com/duanxz/p/4520571.html
@ConfigurationProperties(prefix = "resource")
public class Resource {

    private String name;
    private int age;
    private int score;
}
