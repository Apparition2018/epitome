package org.ljh.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Staff — 员工（{@code Person} 的子类）。
 * <p>演示继承映射子类 + {@code @Embedded} 嵌入组件（{@link Address}）。
 * 子类专属字段 {@code department} 与可嵌入地址列都会落入 {@code person} 表。
 * {@code @DiscriminatorValue} 指定 {@code DISC_TYPE} 列的取值。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("STAFF")
@ToString(callSuper = true)
public class Staff extends Person {

    /** 所属部门 */
    @Column(name = "department", length = 50)
    private String department;

    /** 可嵌入地址 */
    @Embedded
    private Address address = new Address();
}
