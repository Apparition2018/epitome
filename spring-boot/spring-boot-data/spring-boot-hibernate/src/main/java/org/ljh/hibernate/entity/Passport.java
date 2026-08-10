package org.ljh.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * Passport — 护照实体，演示一对一（{@code @OneToOne}）的反向端
 * <p>{@code mappedBy = "passport"} 表示外键由 {@link Author} 维护，
 * 本端是只读（inverse）端，仅提供 {@code author} 反向引用。</p>
 *
 * @author ljh
 * @since 2026/8/10
 */
@Data
@Entity
@Table(name = "passport")
public class Passport implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 护照号码 */
    @Column(name = "number", length = 30, nullable = false, unique = true)
    private String number;

    /** 反向引用作者（不维护外键） */
    @OneToOne(mappedBy = "passport", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Author author;
}
