package org.ljh.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Order2 — 订单子项实体（单向一对多的「多」方）。
 * <p>序号 4 演示：该实体<strong>不声明</strong>任何指向 {@link Customer2} 的反向映射，
 * 外键列 {@code customer_id} 存在于 {@code order2} 表，但完全由「一」方
 * {@link Customer2#getOrderItems()} 上的 {@code @JoinColumn} 维护——体现单向一对多
 * 「外键在 n 方、由 1 方维护，会产生多余 UPDATE」的特点。</p>
 *
 * @author ljh
 * @since 2026/8/12
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order2")
public class Order2 implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 子项名称 */
    @Column(name = "name", length = 50)
    private String name;
}
