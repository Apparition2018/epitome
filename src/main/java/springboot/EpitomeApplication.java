package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
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
// @Enable 源码分析：https://www.cnblogs.com/duanxz/p/4875156.html
@EnableCaching
@EnableScheduling
@EnableAsync
@EnableTransactionManagement
// TK Mybatis MapperScan，不能包含通用 mapper 的路径，所以 MyMapper.java 不能放在 springboot.mapper.master.tk 里
@MapperScan(basePackages = "springboot.mapper.master.tk")
public class EpitomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpitomeApplication.class, args);
    }
}
