package springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.support.WebApplicationContextUtils;
import springboot.util.SpringUtils;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.Objects;

/**
 * Spring
 *
 * @author ljh
 * @since 2020/1/9 19:28
 */
@SpringBootTest(classes = EpitomeApplication.class)
public class SpringTest {

    @Autowired
    private ServletContext servletContext;

    /**
     * @see <a href="https://www.cnblogs.com/jpfss/p/9447915.html">Spring WebApplicationContextUtils 工具类</a>
     */
    @Test
    public void testSpringContextUtils() {
        System.out.println(Arrays.toString(SpringUtils.getActiveProfiles()));

        Assertions.assertSame(SpringUtils.getApplicationContext(), WebApplicationContextUtils.getRequiredWebApplicationContext(Objects.requireNonNull(servletContext)));
    }
}
