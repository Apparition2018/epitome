package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
// 相当于 @Configuration, @EnableAutoConfiguration, @ComponentScan
@SpringBootApplication
/* Servlet      @WebServlet
 * Filter       @WebFilter
 * Listener     @WebListener
 */
@ServletComponentScan
// Quick Guide to the Spring @Enable Annotations：https://www.baeldung.com/spring-enable-annotations
@EnableCaching
@EnableScheduling
@EnableAsync
@EnableTransactionManagement
public class EpitomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpitomeApplication.class, args);
    }

}
