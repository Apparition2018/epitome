package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

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
// TK Mybatis MapperScan，不能包含通用 mapper 的路径，所以 MyMapper.java 不能放在 springboot.mapper.master.tk 里
@MapperScan(basePackages = "springboot.mapper.tk.sales")
public class EpitomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpitomeApplication.class, args);
    }
}
