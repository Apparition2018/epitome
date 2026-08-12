package org.ljh.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Person — 继承映射的父类（{@code SINGLE_TABLE} 单表继承）。
 * <p>纯 JPA 标准注解演示：
 * <ul>
 *     <li>{@code @Inheritance(SINGLE_TABLE)}：父类与所有子类映射到同一张表
 *         {@code person}，用 {@code @DiscriminatorColumn} 的 {@code DISC_TYPE} 列区分具体子类；</li>
 *     <li>{@code @Version}：乐观锁版本字段，并发更新时由 Provider 自动递增并做冲突校验。</li>
 * </ul></p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISC_TYPE", discriminatorType = DiscriminatorType.STRING, length = 20)
public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 姓名 */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /** 乐观锁版本 */
    @Version
    @Column(name = "version")
    private Long version;
}
