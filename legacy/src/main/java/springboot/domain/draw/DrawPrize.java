package springboot.domain.draw;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 抽奖奖品
 *
 * @author ljh
 * @since 2026/8/24
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "draw_prize")
public class DrawPrize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 抽奖活动 ID */
    @Column(name = "draw_id")
    private Long drawId;
    /** 概率（1表示万分之一） */
    @Column(name = "probability")
    private Integer probability;
    /** 总数 */
    @Column(name = "total_qty")
    private Integer totalQty;
    /** 中奖数 */
    @Column(name = "win_qty")
    private Integer winQty;
}
