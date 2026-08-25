package springboot.domain.draw;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 抽奖用户
 *
 * @author ljh
 * @since 2026/8/24
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "draw_user")
public class DrawUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 积分 */
    @Column(name = "score")
    private Integer score;
}
