package springboot.domain.master.draw;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Prize
 *
 * @author ljh
 * @since 2022/7/4 0:23
 */
@Data
@Accessors(chain = true)
public class Prize {
    private Integer id;
    /** 抽奖活动 ID */
    private Integer drawId;
    /** 概率（1表示万分之一） */
    private Integer probability;
    /** 总数 */
    private Integer totalQty;
    /** 中奖数 */
    private Integer winQty;
}
