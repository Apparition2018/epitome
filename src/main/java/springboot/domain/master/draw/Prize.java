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
    private Integer drawId;
    private Integer probability;
    private Integer totalQty;
    private Integer winQty;
}
