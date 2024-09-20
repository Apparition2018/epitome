package jar.fasterxml.jackson.annotataion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import l.demo.Demo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * {@code @JsonManagedReference} 和 {@code @JsonBackReference} 解决循环依赖序列化和反序列化问题（父/子）
 *
 * @author ljh
 * @since 2021/7/24 17:03
 */
public class JsonReferenceDemo extends Demo {

    public static void main(String[] args) throws JsonProcessingException {
        Employee e1 = new Employee().setName("mary");
        Employee e2 = new Employee().setName("lucy");

        Boss boss = new Boss().setName(MY_NAME).setEmployeeList(Lists.list(e1, e2));

        e1.setBoss(boss);
        e2.setBoss(boss);

        p(jsonMapper.writeValueAsString(boss));
        // {"name":"ljh","employeeList":[{"name":"mary"},{"name":"lucy"}]}
    }

    @Data
    @Accessors(chain = true)
    private static class Boss {
        private String name;
        @JsonManagedReference
        private List<Employee> employeeList;
    }

    @Data
    @Accessors(chain = true)
    private static class Employee {
        private String name;
        @JsonBackReference
        private Boss boss;
    }
}
