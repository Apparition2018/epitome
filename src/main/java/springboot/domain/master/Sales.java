package springboot.domain.master;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Sales implements Serializable {
    private Integer yearId;

    private Integer monthId;

    private Integer dayId;

    private BigDecimal salesValue;

    private static final long serialVersionUID = 1L;
}