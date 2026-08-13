package org.ljh.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Customer — 客户实体。
 * <p>集中演示序号 1/2/5 三类「纯 JPA 规范」特性：</p>
 * <ul>
 *     <li><b>序号 1（主键生成策略）</b>：{@code @GeneratedValue(strategy = TABLE)} +
 *         {@code @TableGenerator}，主键值来自独立的一张表（{@code id_gen_table}），
 *         通过 {@code pkColumnName}/{@code valueColumnName}/{@code pkColumnValue}/{@code allocationSize}
 *         指定表结构与步长；与 {@link Order} 的 SEQUENCE、{@link Customer2} 的 AUTO 形成对照。</li>
 *     <li><b>序号 2（字段映射注解）</b>：{@code @Basic}（是否可选/懒加载）、
 *         {@code @Enumerated}（{@code STRING} 存枚举名 vs {@code ORDINAL} 存下标）、
 *         {@code @Temporal}（{@code DATE} 仅日期 vs {@code TIMESTAMP} 含时间）。</li>
 *     <li><b>序号 5（关联删除行为）</b>：本实体不声明一对多集合（保持纯被引用端），
 *         {@link Order} 通过多对一引用本实体且外键列 {@code NOT NULL}，删除被订单引用的本实体
 *         即触发外键约束异常。</li>
 * </ul>
 *
 * @author ljh
 * @since 2026/8/12
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键：TABLE 生成策略，值存于 {@code id_gen_table} 表（见 {@code demo-schema.sql}） */
    @Id
    @TableGenerator(
            name = "customer_id_gen",
            table = "id_gen_table",
            pkColumnName = "id_gen_name",
            valueColumnName = "id_gen_value",
            pkColumnValue = "customer_id",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customer_id_gen")
    @Column(name = "id")
    private Long id;

    /** @Basic 显式声明：默认 EAGER + 非可选（可写可不写，此处显式以作演示） */
    @Basic
    @Column(name = "name", length = 50)
    private String name;

    /** {@code @Enumerated(STRING)}：库中存枚举名（如 ACTIVE），可读性好、列更宽 */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private CustomerStatus status;

    /** {@code @Enumerated(ORDINAL)}：库中存枚举下标（如 1），紧凑但改枚举顺序会错乱 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_ordinal")
    private CustomerStatus statusOrdinal;

    /** {@code @Temporal(DATE)}：仅存日期部分（yyyy-MM-dd） */
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    /** {@code @Temporal(TIMESTAMP)}：日期 + 时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    /** {@code @Basic(fetch = LAZY)}：声明懒加载的普通字段，访问时才取（需字节码增强才真正生效） */
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "note")
    private String note;
}
