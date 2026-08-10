package org.ljh.hibernate.entity;

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
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Person — 人的抽象基类，演示继承映射 {@code SINGLE_TABLE}（单表策略）
 * <p>三种继承策略：</p>
 * <ul>
 *     <li>{@code SINGLE_TABLE}：所有子类共享一张表 {@code person}，用辨别者列
 *         {@code DISC_TYPE} 区分具体类型，列取所有子类字段的并集（本策略）。</li>
 *     <li>{@code JOINED}：父表 + 每张子表（通过主键外键 JOIN），表结构最规范。</li>
 *     <li>{@code TABLE_PER_CLASS}：每个具体类各建一张表，列互不共享。</li>
 * </ul>
 * <p>这里演示最常用、性能最好的 {@code SINGLE_TABLE}：查询子类时只访问一张表。</p>
 *
 * @author ljh
 * @since 2026/8/10
 */
@Data
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISC_TYPE", discriminatorType = DiscriminatorType.STRING, length = 20)
public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 姓名 */
    @Column(name = "name", length = 50, nullable = false)
    private String name;
}
