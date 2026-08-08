package org.ljh.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Product — 商品实体（独立演示表 {@code product}）
 * <p>集中演示两个 world 表无法承载的 JPA 特性：</p>
 * <ul>
 *     <li>{@code @Version} 乐观锁：更新时校验版本号，防止并发覆盖</li>
 *     <li>审计字段：{@code @CreatedBy}/{@code @CreatedDate}/{@code @LastModifiedBy}/{@code @LastModifiedDate}
 *         ，由 {@code AuditingEntityListener} 自动填充</li>
 * </ul>
 *
 * @author ljh
 * @since 2026/8/8
 */
@Data
@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
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

    /** 乐观锁版本号：每次 update 自动 +1，并发冲突时抛出 OptimisticLockException */
    @Version
    @Column(name = "version")
    private Long version;

    /** 创建人 */
    @CreatedBy
    @Column(name = "created_by", length = 50)
    private String createdBy;

    /** 创建时间 */
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /** 最后修改人 */
    @LastModifiedBy
    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    /** 最后修改时间 */
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
