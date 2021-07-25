package springboot.domain.master;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Score implements Serializable {
    private static final long serialVersionUID = -2370593631437965516L;
    private Integer id;
    private String name;
    private String course;
    private Integer score;
}