package spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import springboot.EpitomeApplication;
import springboot.domain.School;
import springboot.util.SpringContextUtils;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author ljh
 * created on 2020/1/9 19:28
 */
@SpringBootTest(classes = EpitomeApplication.class)
public class SpringTest {

    @Autowired
    private ServletContext servletContext;

    /**
     * Spring WebApplicationContextUtils 工具类：https://www.cnblogs.com/jpfss/p/9447915.html
     */
    @Test
    public void testSpringContextUtils() {
        System.out.println(SpringContextUtils.getApplicationContext());
        System.out.println(Arrays.toString(SpringContextUtils.getActiveProfiles()));

        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(Objects.requireNonNull(servletContext));
        Assertions.assertSame(SpringContextUtils.getBean(School.class), webApplicationContext.getBean(School.class));
    }
}
