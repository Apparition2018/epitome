package spring.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Score
 *
 * @author Arsenal
 * created on 2020/11/27 1:59
 */
@Data
@Accessors(chain = true)
public class Score {
    private Integer id;
    private String name;
    private String course;
    private Integer score;
}
