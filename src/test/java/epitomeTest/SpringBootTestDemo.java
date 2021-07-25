package epitomeTest;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import springboot.Application;
import springboot.dao.slaver.DemoMapper;
import springboot.domain.Resource;
import springboot.domain.slaver.Demo;
import springboot.util.SpringContextUtils;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * JUnit 5和JUnit 4比较：https://blog.csdn.net/u010675669/article/details/86574956
 *
 * @author ljh
 * created on 2020/1/9 19:28
 */
@SpringBootTest(classes = Application.class)
public class SpringBootTestDemo {

    private final ServletContext servletContext;
    private final DemoMapper demoMapper;

    @Autowired
    public SpringBootTestDemo(ServletContext servletContext, DemoMapper demoMapper) {
        this.servletContext = servletContext;
        this.demoMapper = demoMapper;
    }

    @Test
    public void testSpringContextUtils() {
        System.out.println(SpringContextUtils.getApplicationContext());

        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(Objects.requireNonNull(servletContext));
        Assertions.assertSame(SpringContextUtils.getBean(Resource.class), webApplicationContext.getBean(Resource.class));
    }

    @Test
    public void testDemoInsertBatch() {
        List<Demo> list = new ArrayList<>();
        list.add(new Demo().setName("ljh"));
        list.add(new Demo().setName("mary"));
        demoMapper.insertBatch(list);
    }
}
