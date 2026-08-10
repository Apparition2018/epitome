package org.ljh.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * Manager — 经理子类（继承映射 SINGLE_TABLE）
 * <p>在 {@link Person} 共享的 {@code person} 表中追加经理专属列 {@code team_size}，
 * 辨别者列 {@code DISC_TYPE} 存 {@code MANAGER}。</p>
 *
 * @author ljh
 * @since 2026/8/10
 */
@Data
@Entity
@DiscriminatorValue("MANAGER")
@EqualsAndHashCode(callSuper = true)
public class Manager extends Person {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 团队规模 */
    @Column(name = "team_size")
    private Integer teamSize;
}
