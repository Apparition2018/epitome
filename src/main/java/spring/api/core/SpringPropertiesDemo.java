package spring.api.core;

import org.junit.jupiter.api.Test;
import org.springframework.core.SpringProperties;

/**
 * SpringProperties
 * 获取 spring.properties 文件的属性值
 *
 * @author ljh
 * created on 2021/9/9 11:45
 */
public class SpringPropertiesDemo {

    @Test
    public void testSpringProperties() {
        System.out.println(SpringProperties.getProperty("name")); // ljh
    }
}
