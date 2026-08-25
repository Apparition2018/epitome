package springboot.repository.draw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springboot.domain.draw.DrawPrize;

import java.util.List;

/**
 * 抽奖奖品 JPA Repository
 *
 * @author ljh
 * @since 2026/8/24 17:29
 */
@Repository
public interface DrawPrizeRepository extends JpaRepository<DrawPrize, Long> {

    /**
     * 根据 drawId 查找有库存的奖品列表
     *
     * @param drawId 抽奖活动ID
     * @return 奖品概率列表
     */
    List<DrawPrize> findByDrawIdAndTotalQtyGreaterThanWinQty(Long drawId);

    /**
     * 中奖数 +1
     *
     * @param id 奖品ID
     * @return 影响条数
     */
    @Modifying
    @Query("UPDATE DrawPrize d SET d.winQty = d.winQty + 1 WHERE d.id = :id AND d.totalQty > d.winQty")
    int incrWinQty(@Param("id") Long id);
}
