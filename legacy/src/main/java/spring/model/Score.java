package spring.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * Score
 *
 * @author ljh
 * @since 2020/11/27 1:59
 */
@Data
@Accessors(chain = true)
public class Score implements Serializable {
    @Serial
    private static final long serialVersionUID = 500668519675954582L;
    private Integer id;
    private String name;
    private String course;
    private Integer score;
}
