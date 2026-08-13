package org.ljh.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer2 — 客户实体（单向一对多演示）。
 * <p>序号 4 与序号 1 的对照：</p>
 * <ul>
 *     <li><b>序号 1（主键生成策略对照）</b>：{@code @GeneratedValue(strategy = AUTO)}，
 *         Hibernate 依据方言自动选择生成方式（H2 下走序列），与 {@link Customer} 的 TABLE、
 *         {@link Order} 的 SEQUENCE 三策略对照。</li>
 *     <li><b>序号 4（单向一对多）</b>：{@code @OneToMany} + {@code @JoinColumn}，外键列
 *         {@code customer_id} 建在「多」方表（{@code order2}），却由「一」方（本类）维护，
 *         因此 {@code persist} 后会额外发出 {@code UPDATE order2 SET customer_id = ?}
 *         来维护外键（这就是单向一对多「多余 UPDATE」现象的由来）。</li>
 * </ul>
 *
 * @author ljh
 * @since 2026/8/12
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer2")
@ToString(exclude = "orderItems")
public class Customer2 implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键：AUTO 生成策略（H2 方言下由 Hibernate 选择序列实现） */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /** 客户名 */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * 单向一对多：{@code @JoinColumn} 声明在「一」方，外键列 {@code customer_id} 落在
     * {@code order2} 表，但由本类维护；n 方实体 {@link Order2} 上不声明任何反向映射。
     * <p>不设 {@code mappedBy}，故无法用「n 方自动回填外键」，只能由 1 方 set 集合后
     * 额外 UPDATE 维护外键列。</p>
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private List<Order2> orderItems = new ArrayList<>();

    /** 添加子项（单向：只需加入本方集合，n 方不感知反向引用） */
    public Customer2 addOrderItem(Order2 item) {
        orderItems.add(item);
        return this;
    }
}
