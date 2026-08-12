package org.ljh.jpa.projection;

import lombok.Getter;

/**
 * CustomerProjection — 构造器投影 DTO。
 * <p>序号 3 演示 JPQL 的 {@code SELECT new 全限定类名(...)} 构造器投影：查询只取部分字段，
 * 直接映射到该类的构造器。类不必标注 {@code @Entity}，只要是可被 JPQL 引用、且提供与
 * {@code select} 列表参数顺序一致的公开构造器即可。相比返回 {@code Object[]}，构造器投影
 * 得到强类型的 DTO，便于上层直接使用。</p>
 *
 * @author ljh
 * @since 2026/8/12
 */
@Getter
public class CustomerProjection {

    /** 客户主键 */
    private final Long id;

    /** 客户名 */
    private final String name;

    /** 构造器参数顺序与 JPQL {@code select new ... (id, name)} 一致 */
    public CustomerProjection(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
