package org.ljh.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Manager — 经理（{@code Person} 的子类）。
 * <p>演示继承映射子类的额外字段 {@code teamSize}（落入 {@code person} 表）。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("MANAGER")
@ToString(callSuper = true)
public class Manager extends Person {

    /** 管理团队规模 */
    @Column(name = "team_size")
    private Integer teamSize;
}
