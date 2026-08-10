package org.ljh.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Address — 地址（可嵌入组件，对应 hibernate_ 的 {@code <component>}）
 * <p>{@code @Embeddable} 标注的类本身不是实体、没有自己的表，它的字段会被"展开"
 * 到宿主实体（{@link Staff}）的同一张表中，作为该表的一组列。用 {@code @Embedded}
 * 组合复用一组字段，避免重复定义。</p>
 *
 * @author ljh
 * @since 2026/8/10
 */
@Data
@Embeddable
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 街道 */
    @Column(name = "street")
    private String street;

    /** 城市 */
    @Column(name = "address_city")
    private String city;

    /** 邮编 */
    @Column(name = "zip_code", length = 20)
    private String zipCode;
}
