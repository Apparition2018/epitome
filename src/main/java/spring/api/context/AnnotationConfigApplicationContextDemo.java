package spring.api.context;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.bean.Bean;
import spring.config.DemoConfig;

/**
 * spring @Configuration
 *
 * @author ljh
 * @since 2020/11/29 16:45
 */
public class AnnotationConfigApplicationContextDemo {
    AnnotationConfigApplicationContext applicationContext;

    @BeforeEach
    public void init() {
        applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DemoConfig.class);
        applicationContext.refresh();
    }

    @Test
    public void testLifecycle() {
        Bean bean = applicationContext.getBean("bean", Bean.class);
        bean.service();
        System.err.println(bean);
    }

    @AfterEach
    public void destroy() {
        applicationContext.close();
    }
}
