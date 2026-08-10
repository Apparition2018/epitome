package org.ljh.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * Author — 作者实体，演示一对一（{@code @OneToOne}）
 * <p>作者与 {@link Passport} 是「一对一」关系（一人一护照）。
 * 这里演示基于外键的一对一：本端 {@code @OneToOne} + {@code @JoinColumn(name = "passport_id")}，
 * 即 {@code author} 表持有外键列 {@code passport_id} 指向 {@code passport} 表主键。
 * 反向端由 {@link Passport#getAuthor()} 用 {@code mappedBy} 指定。</p>
 *
 * @author ljh
 * @since 2026/8/10
 */
@Data
@Entity
@Table(name = "author")
public class Author implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 作者姓名 */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /** 一对一外键维护端：author 表存 passport_id 外键 */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passport_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Passport passport;
}
