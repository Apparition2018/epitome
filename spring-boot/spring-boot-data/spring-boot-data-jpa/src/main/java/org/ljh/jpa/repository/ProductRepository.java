package org.ljh.jpa.repository;

import jakarta.persistence.LockModeType;
import org.ljh.jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * ProductRepository — 商品仓储
 * <p>用于演示乐观锁 {@code @Version} 与审计字段，方法较为简单。</p>
 *
 * @author ljh
 * @since 2026/8/8
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /** 按名称查询 */
    Optional<Product> findByName(String name);

    /** 按价格区间查询 */
    List<Product> findByPriceGreaterThanEqual(java.math.BigDecimal price);

    // ==================== 悲观锁（@Lock） ====================
    // @Lock 注解是 Spring Data JPA 提供；LockModeType 是 JPA 规范（jakarta.persistence）。
    // PESSIMISTIC_WRITE：查询即对行加写锁（SELECT ... FOR UPDATE），事务提交/回滚后释放。
    // 需在事务中使用（@Transactional），否则会因无活动事务抛异常。

    /** 按 id 查询并加悲观写锁（PESSIMISTIC_WRITE） */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Product p where p.id = :id")
    Optional<Product> findByIdForUpdate(@Param("id") Long id);
}
