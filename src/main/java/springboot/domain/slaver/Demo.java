package springboot.domain.slaver;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Demo implements Serializable {
    private static final long serialVersionUID = -388873145067363215L;
    private Integer id;
    private String name;
}