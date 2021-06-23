package epitomeTest;

import l.demo.Demo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import springboot.Application;
import springboot.domain.Resource;
import springboot.util.SpringContextUtils;

import javax.servlet.ServletContext;
import java.util.Objects;

/**
 * JUnit 5和JUnit 4比较：https://blog.csdn.net/u010675669/article/details/86574956
 *
 * @author ljh
 * created on 2020/1/9 19:28
 */
@SpringBootTest(classes = Application.class)
public class SpringBootTestDemo extends Demo {

    private final ServletContext servletContext;

    @Autowired
    public SpringBootTestDemo(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Test
    public void testSpringContextUtils() {
        p(SpringContextUtils.getApplicationContext());

        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(Objects.requireNonNull(servletContext));
        Assertions.assertSame(SpringContextUtils.getBean(Resource.class), webApplicationContext.getBean(Resource.class));
    }
}
