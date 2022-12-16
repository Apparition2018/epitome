package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;

import java.util.Properties;

/**
 * DemoConfig
 * <p>
 * Spring 3.1 @EnableXXX：https://www.cnblogs.com/duanxz/p/4875156.html
 * Spring 4.0 @Configuration：https://www.cnblogs.com/duanxz/p/7493276.html
 * Spring 4.0 @PropertySource：https://www.cnblogs.com/duanxz/archive/2012/11/06/2756362.html
 * 获取 properties 属性的几种方式：https://blog.csdn.net/lettuce_/article/details/101518075
 *
 * @author ljh
 * @since 2020/11/29 15:55
 */
// @Configuration 相当于 xml 配置文件中的 <beans>
@Configuration
@PropertySource(ResourceUtils.CLASSPATH_URL_PREFIX + "jdbc.properties")
public class DemoConfig {

    private final Environment environment;

    @Autowired
    public DemoConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = "bean", initMethod = "initMethod", destroyMethod = "destroyMethod")
    public spring.bean.Bean bean() {
        Properties properties = new Properties();
        properties.setProperty("jdbc.username", environment.getProperty("jdbc.username"));
        return new spring.bean.Bean().setProperties(properties);
    }
}
