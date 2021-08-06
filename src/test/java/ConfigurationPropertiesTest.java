import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.Application;
import springboot.domain.Student;

/**
 * @author ljh
 * created on 2021/8/6 17:49
 */
@SpringBootTest(classes = Application.class)
public class ConfigurationPropertiesTest {

    @Autowired
    private Student student;

    @Test
    public void test() {
        System.out.println(student);
    }
}
