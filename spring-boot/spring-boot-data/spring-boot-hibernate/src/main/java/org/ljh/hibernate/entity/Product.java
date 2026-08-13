package org.ljh.hibernate.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Product — 商品实体（独立演示表 {@code product}）
 * <p>集中演示两个 Hibernate 特有 / 常用特性：</p>
 * <ul>
 *     <li>{@code @DynamicUpdate}：默认 Hibernate 会整行 UPDATE 所有列；加该注解后
 *         UPDATE 只包含真正变化的列，减少网络与锁开销（适合大宽表）。</li>
 *     <li>{@code @Version} 乐观锁：每次 UPDATE 自动 +1，并发冲突抛
 *         {@code OptimisticLockException}。</li>
 * </ul>
 *
 * @author ljh
 * @since 2026/8/9
 */
@Data
@Entity
@Table(name = "product")
@DynamicUpdate
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 商品名 */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /** 价格 */
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    /** 库存 */
    @Column(name = "stock")
    private Integer stock;

    /** 乐观锁版本号：每次 update 自动 +1，并发冲突时抛出 OptimisticLockException */
    @Version
    @Column(name = "version")
    private Long version;
}
