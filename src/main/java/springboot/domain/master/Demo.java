package springboot.domain.master;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Demo
 *
 * @author ljh
 * created on 2022/2/18 18:03
 */
@Data
@Accessors(chain = true)
public class Demo {
    private Integer id;
    private String name;
}
