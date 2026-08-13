package org.ljh.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * Passport — 护照实体（一对一关联的反向端）。
 * <p>演示 {@code @OneToOne mappedBy="passport"}：外键维护在 {@link Author} 一侧的
 * {@code author.passport_id} 列，本端仅做反向映射，不重复建列。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "passport")
public class Passport implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 护照号（唯一） */
    @Column(name = "number", length = 30, nullable = false, unique = true)
    private String number;

    /** 反向端：该护照归属的作者 */
    @OneToOne(mappedBy = "passport")
    @ToString.Exclude
    private Author author;
}
