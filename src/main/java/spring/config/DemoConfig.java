package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;

/**
 * DemoConfig
 * <p>
 * Spring 3.1 @EnableXXX 注解：https://www.cnblogs.com/duanxz/p/4875156.html
 * Spring 4.0 @Configuration 的使用：https://www.cnblogs.com/duanxz/p/7493276.html
 * Spring 4.0 @PropertySource：https://www.cnblogs.com/duanxz/archive/2012/11/06/2756362.html
 * 获取 properties 属性的几种方式：https://blog.csdn.net/lettuce_/article/details/101518075
 *
 * @author Arsenal
 * created on 2020/11/29 15:55
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

    @Bean(name = "bean", initMethod = "init", destroyMethod = "destroy")
    public spring.bean.Bean bean() {
        return new spring.bean.Bean();
    }

    @Bean(name = "game")
    public Game a() {
        return i -> {
            System.out.println("> 0");
        };
    }

//    @Bean(name = "game")
//    public Game b() {
//        return i -> {
//            System.out.println("< 0");
//        };
//    }
//
//    @Bean(name = "game")
//    public Game c() {
//        return i -> {
//            System.out.println("< 0");
//        };
//    }

    interface Game {
        void play(int i);
    }
}
