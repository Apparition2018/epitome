package spring.api.core;

import org.springframework.core.SpringProperties;

/**
 * SpringProperties
 * <p>获取 spring.properties 文件的属性值
 *
 * @author ljh
 * @since 2021/9/9 11:45
 */
public class SpringPropertiesDemo {

    public static void main(String[] args) {
        System.out.println(SpringProperties.getProperty("name"));
    }
}
