package spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.support.WebApplicationContextUtils;
import springboot.EpitomeApplication;
import springboot.util.SpringUtils;

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
        System.out.println(Arrays.toString(SpringUtils.getActiveProfiles()));

        Assertions.assertSame(SpringUtils.getApplicationContext(), WebApplicationContextUtils.getRequiredWebApplicationContext(Objects.requireNonNull(servletContext)));
    }
}
