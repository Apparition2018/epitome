package org.ljh.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Address — 可嵌入值类型（{@code @Embeddable}）。
 * <p>纯 JPA 标准注解：{@code @Embeddable} 表示这是一个可嵌入组件，其字段会作为
 * 宿主实体表的普通列存储，不单独建表。被 {@link Staff} 通过 {@code @Embedded} 嵌入。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 街道 */
    @Column(name = "street", length = 100)
    private String street;

    /** 城市 */
    @Column(name = "address_city", length = 50)
    private String city;

    /** 邮编 */
    @Column(name = "zip_code", length = 20)
    private String zipCode;
}
