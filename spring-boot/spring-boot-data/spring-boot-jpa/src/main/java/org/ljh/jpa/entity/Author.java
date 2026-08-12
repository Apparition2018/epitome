package org.ljh.jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * Author — 作者实体（一对一关联的维护端）。
 * <p>演示 {@code @OneToOne} + {@code @JoinColumn(name = "passport_id")}：
 * 外键列 {@code author.passport_id} 由本端持有；级联持久化，使保存作者时同时保存护照。
 * 维护端保存时设置 {@code Passport.author} 反向引用，保证双方一致。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author implements Serializable {

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

    /** 护照（延迟加载） */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    @ToString.Exclude
    private Passport passport;
}
