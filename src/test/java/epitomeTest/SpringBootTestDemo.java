package epitomeTest;

import l.demo.Demo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.Application;
import springboot.util.SpringContextUtils;

/**
 * JUnit 5和JUnit 4比较：https://blog.csdn.net/u010675669/article/details/86574956
 *
 * @author ljh
 * created on 2020/1/9 19:28
 */
@SpringBootTest(classes = Application.class)
public class SpringBootTestDemo extends Demo {

    @Test
    public void testSpringContextUtils() {
        p(SpringContextUtils.getApplicationContext());
        p(SpringContextUtils.getActiveProfiles());
    }
}
