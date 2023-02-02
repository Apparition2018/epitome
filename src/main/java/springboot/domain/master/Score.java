package springboot.domain.master;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Score implements Serializable {
    @Serial
    private static final long serialVersionUID = -5129497473222426950L;
    private Integer id;
    private String name;
    private String course;
    private Integer score;
}
