package org.ljh.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Customer3 — 客户实体（实体二级缓存·规范层演示）。
 * <p>序号 6 演示 JPA 规范层的实体二级缓存：</p>
 * <ul>
 *     <li>{@code @Cacheable}（{@code jakarta.persistence.Cacheable}）：声明本实体「可被二级缓存」。
 *         这是 <strong>JPA 规范注解</strong>，区别于 Hibernate 专有的 {@code @Cache}。</li>
 *     <li>开关由持久化单元的 <code>shared-cache-mode</code> 控制；本模块为 Spring Boot 无
 *         {@code persistence.xml}，故在 {@code application.properties} 用
 *         {@code spring.jpa.properties.jakarta.persistence.sharedCache.mode=ENABLE_SELECTIVE}
 *         指定「仅缓存显式标了 {@code @Cacheable} 的实体」。</li>
 *     <li>真正的缓存存储仍由实现层（Hibernate）承载，故还需开启
 *         {@code hibernate.cache.use_second_level_cache} 并指定
 *         {@code hibernate.cache.region.factory_class}（本模块用 JCache + Caffeine）。</li>
 * </ul>
 *
 * @author ljh
 * @since 2026/8/12
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Cacheable
@Table(name = "customer3")
public class Customer3 implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /** 客户名 */
    @Column(name = "name", length = 50)
    private String name;
}
