package jar.fasterxml.jackson.annotataion;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;
import l.demo.Demo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * &#064;JsonIdentityInfo：解决循环依赖序列化和反序列化问题
 *
 * @author ljh
 * @since 2021/7/24 17:04
 */
public class JsonIdentityInfoDemo extends Demo {

    @Test
    public void testJsonIdentityInfo() throws JsonProcessingException {
        Employee e1 = new Employee().setName("mary");
        Employee e2 = new Employee().setName("lucy");

        Boss boss = new Boss().setName(MY_NAME).setEmployeeList(Lists.list(e1, e2));

        e1.setBoss(boss);
        e2.setBoss(boss);

        p(jsonMapper.writeValueAsString(boss));
        // {"name":"ljh","employeeList":[{"name":"mary","boss":"ljh"},{"name":"lucy","boss":"ljh"}]}
    }

    @Data
    @Accessors(chain = true)
    @JsonIdentityInfo(property = "name", generator = ObjectIdGenerators.PropertyGenerator.class)
    static class Boss {
        private String name;
        private List<Employee> employeeList;
    }

    @Data
    @Accessors(chain = true)
    @JsonIdentityInfo(property = "name", generator = ObjectIdGenerators.PropertyGenerator.class)
    static class Employee {
        private String name;
        private Boss boss;
    }
}
