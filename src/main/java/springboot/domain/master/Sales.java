package springboot.domain.master;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Sales implements Serializable {
    @Serial
    private static final long serialVersionUID = -4336095523865817282L;
    private Integer yearId;
    private Integer monthId;
    private Integer dayId;
    private BigDecimal salesValue;
}
