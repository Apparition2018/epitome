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
 * <pre>
 * <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-java">Java-based Container Configuration</a>
 * <a href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using.configuration-classes">Configuration Classes</a>
 * <a href="https://www.cnblogs.com/duanxz/p/7493276.html">@Configuration 的使用</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/11/29 15:55
 */
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
