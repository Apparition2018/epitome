package springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * EpitomeApplication
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
// 相当于 @Configuration, @EnableAutoConfiguration, @ComponentScan
@SpringBootApplication
/* Listener     @WebListener
 * Filter       @WebFilter
 * Servlet      @WebServlet
 */
@ServletComponentScan
// Quick Guide to the Spring @Enable Annotations：https://www.baeldung.com/spring-enable-annotations
@EnableCaching
@EnableScheduling
@EnableResilientMethods
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan(basePackages = "springboot.mqtt.mapper")
public class EpitomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpitomeApplication.class, args);
    }
}
