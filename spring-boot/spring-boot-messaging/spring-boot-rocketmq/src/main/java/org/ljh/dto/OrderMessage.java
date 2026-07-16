package org.ljh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 消息体 DTO
 *
 * @author ljh
 * @since 2026/5/5 1:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long orderId;
    private String orderNo;
    private Long userId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createTime;
}
