package spring.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.bean.Bean;
import spring.config.DemoConfig;

/**
 * spring @Configuration
 *
 * @author Arsenal
 * created on 2020/11/29 16:45
 */
public class SpringConfigDemo {
    AnnotationConfigApplicationContext ac;

    @Before
    public void init() {
        ac = new AnnotationConfigApplicationContext();
        ac.register(DemoConfig.class);
    }

    /**
     * 生命周期
     */
    @Test
    public void testLifecycle() {
        Bean bean = ac.getBean("bean", Bean.class);
        bean.service();
    }

    @After
    public void destroy() {
        ac.close();
    }
}
