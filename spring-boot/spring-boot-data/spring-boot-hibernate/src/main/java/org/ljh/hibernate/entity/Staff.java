package org.ljh.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * Staff — 员工子类（继承映射 SINGLE_TABLE）
 * <p>在 {@link Person} 共享的 {@code person} 表中追加员工专属列 {@code department}
 * 及可嵌入地址 {@link Address}（展开为 street / address_city / zip_code 三列），
 * 辨别者列 {@code DISC_TYPE} 存 {@code STAFF}。</p>
 * <p>这里同时演示两种特性：继承映射 + {@code @Embedded} 组件，路径查询可用
 * {@code s.address.city} 访问内嵌字段。</p>
 *
 * @author ljh
 * @since 2026/8/10
 */
@Data
@Entity
@DiscriminatorValue("STAFF")
@EqualsAndHashCode(callSuper = true)
public class Staff extends Person {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 所属部门 */
    @Column(name = "department", length = 50)
    private String department;

    /** 可嵌入地址（展开为 street / address_city / zip_code 三列） */
    @Embedded
    private Address address;
}
