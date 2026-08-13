package org.ljh.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Order — 订单实体。
 * <p>序号 1/5 演示：</p>
 * <ul>
 *     <li><b>序号 1（主键生成策略对照）</b>：{@code @GeneratedValue(strategy = SEQUENCE)} +
 *         {@code @SequenceGenerator}，主键值来自数据库序列 {@code order_seq}（H2 中
 *         {@code CREATE SEQUENCE} 创建），与 {@link Customer} 的 TABLE、{@link Customer2} 的 AUTO 对照。</li>
 *     <li><b>序号 5（关联删除行为）</b>：多对一 {@code @ManyToOne} 持有外键列 {@code customer_id}，
 *         删除被引用的 {@link Customer} 时，若仍有订单引用则触发外键约束异常；删除一对多的 1 端
 *         （{@link Customer}）时，Hibernate 先 {@code UPDATE orders SET customer_id = NULL}
 *         再 DELETE 客户。</li>
 * </ul>
 *
 * @author ljh
 * @since 2026/8/12
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
@ToString(exclude = "customer")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键：SEQUENCE 生成策略，序列名 {@code order_seq}（见 {@code demo-schema.sql}） */
    @Id
    @SequenceGenerator(name = "order_seq_gen", sequenceName = "order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq_gen")
    @Column(name = "id")
    private Long id;

    /** 订单金额 */
    @Column(name = "total")
    private BigDecimal total;

    /** 多对一：外键列 customer_id 在本表（orders），由「多」方维护 */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
