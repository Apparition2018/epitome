package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
@SpringBootApplication
@EnableTransactionManagement
// Servlet, Filter, Listener 直接通过 @WebServlet, @WebFilter, @WebListener 注解自动注册
@ServletComponentScan
@EnableAsync
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
