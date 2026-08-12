package org.ljh.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.ljh.jpa.entity.Customer;
import org.ljh.jpa.entity.Customer2;
import org.ljh.jpa.entity.Customer3;
import org.ljh.jpa.entity.Order;
import org.ljh.jpa.entity.Order2;
import org.ljh.jpa.projection.CustomerProjection;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AdvancedDao — 序号 1–6「纯 JPA 规范」特性的数据访问演示。
 * <p>全部使用标准 {@code EntityManager}，不引入 Spring Data Repository。各方法对应：</p>
 * <ul>
 *     <li>序号 1：{@code saveCustomer}/{@code saveOrder}/{@code saveCustomer2} —— 主键生成策略
 *         TABLE（+@TableGenerator）/ SEQUENCE（+@SequenceGenerator）/ AUTO 对照。</li>
 *     <li>序号 2：{@code saveCustomer}/{@code findCustomer} —— @Basic / @Enumerated / @Temporal 字段映射。</li>
 *     <li>序号 3：{@code projectConstructor}/{@code projectObjectArray} —— JPQL 构造器投影 / Object[] 投影。</li>
 *     <li>序号 4：{@code saveCustomer2WithItems}/{@code findCustomer2WithItems} —— 单向一对多（多余 UPDATE）。</li>
 *     <li>序号 5：{@code removeCustomer}/{@code removeCustomer2} —— 关联删除行为（约束异常 / 外键先置 null 再删）。</li>
 *     <li>序号 6：{@code findCustomer3} —— 实体二级缓存（@Cacheable + shared-cache-mode）。</li>
 * </ul>
 *
 * @author ljh
 * @since 2026/8/12
 */
@Repository
public class AdvancedDao {

    /** 注入当前事务绑定的 EntityManager（JPA 标准 {@code @PersistenceContext}） */
    @PersistenceContext
    private EntityManager em;

    // ==================== 序号 1：主键生成策略（TABLE / SEQUENCE / AUTO 对照） ====================

    /** 持久化 Customer：主键用 {@code GenerationType.TABLE} + {@code @TableGenerator} */
    public void saveCustomer(Customer customer) {
        em.persist(customer);
    }

    /** 持久化 Order：主键用 {@code GenerationType.SEQUENCE} + {@code @SequenceGenerator} */
    public void saveOrder(Order order) {
        em.persist(order);
    }

    /** 持久化 Customer2：主键用 {@code GenerationType.AUTO} */
    public void saveCustomer2(Customer2 customer2) {
        em.persist(customer2);
    }

    // ==================== 序号 2：字段映射注解（@Basic / @Enumerated / @Temporal） ====================

    /** 按主键读取 Customer，验证字段映射（枚举 STRING/ORDINAL、日期 DATE/TIMESTAMP、@Basic） */
    public Customer findCustomer(Long id) {
        return em.find(Customer.class, id);
    }

    // ==================== 序号 3：JPQL 构造器投影 / Object[] 投影 ====================

    /** 构造器投影：{@code SELECT new ...CustomerProjection(id, name)}，返回强类型 DTO */
    public List<CustomerProjection> projectConstructor() {
        TypedQuery<CustomerProjection> query = em.createQuery(
                "select new org.ljh.jpa.projection.CustomerProjection(c.id, c.name) from Customer c",
                CustomerProjection.class);
        return query.getResultList();
    }

    /** 多字段投影：{@code SELECT c.id, c.name} 返回 {@code Object[]} 数组 */
    public List<Object[]> projectObjectArray() {
        TypedQuery<Object[]> query = em.createQuery(
                "select c.id, c.name from Customer c", Object[].class);
        return query.getResultList();
    }

    // ==================== 序号 4：单向一对多（@OneToMany + @JoinColumn） ====================

    /**
     * 保存单向一对多：persist Customer2 后，因其集合上的 {@code @JoinColumn} 由「一」方维护，
     * Hibernate 会额外发出 {@code UPDATE order2 SET customer_id = ?} 维护外键。
     */
    public void saveCustomer2WithItems(Customer2 customer2) {
        em.persist(customer2);
    }

    /** 查询单向一对多的客户（含其子项集合） */
    public Customer2 findCustomer2WithItems(Long id) {
        return em.find(Customer2.class, id);
    }

    // ==================== 序号 5：关联删除行为 ====================

    /**
     * 删除被引用端客户：{@link Order} 以多对一引用 {@link Customer} 且外键列 {@code NOT NULL}，
     * 直接删除该客户会触发数据库外键约束异常（演示「删除被引用端触发外键约束异常」）。
     */
    public void removeCustomer(Customer customer) {
        em.remove(customer);
    }

    /** 按主键查客户（供删除用，须在事务内保证受管状态） */
    public Customer findCustomerEntity(Long id) {
        return em.find(Customer.class, id);
    }

    /** 按主键查订单 */
    public Order findOrderEntity(Long id) {
        return em.find(Order.class, id);
    }

    /**
     * 删除单向一对多的「1」端客户：{@code Customer2.orderItems} 上的 {@code @JoinColumn} 由 1 方
     * 维护且外键列可空、未配 {@code orphanRemoval}，Hibernate 先
     * {@code UPDATE order2 SET customer_id = NULL} 解除引用再 DELETE 客户（演示「删除 1 端时
     * 外键先置 null 再删」）。
     */
    public void removeCustomer2(Customer2 customer2) {
        em.remove(customer2);
    }

    /** 按主键查单向一对多的子项 */
    public Order2 findOrder2(Long id) {
        return em.find(Order2.class, id);
    }

    // ==================== 序号 6：实体二级缓存（@Cacheable） ====================

    /** 按主键读取 Customer3，命中一级/二级缓存时避免查库（跨事务用独立 EntityManager 验证命中） */
    public Customer3 findCustomer3(Long id) {
        return em.find(Customer3.class, id);
    }

    /** 持久化 Customer3 */
    public void saveCustomer3(Customer3 customer3) {
        em.persist(customer3);
    }
}
