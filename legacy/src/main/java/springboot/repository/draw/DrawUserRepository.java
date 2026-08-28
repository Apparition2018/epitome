package springboot.repository.draw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springboot.domain.draw.DrawUser;

/**
 * 抽奖用户 JPA Repository
 *
 * @author ljh
 * @since 2026/8/24 17:30
 */
@Repository
public interface DrawUserRepository extends JpaRepository<DrawUser, Long> {

    /**
     * 扣减积分
     *
     * @param id    用户ID
     * @param score 扣减积分
     * @return 影响条数
     */
    @Modifying
    @Query("UPDATE DrawUser d SET d.score = d.score - :score WHERE d.id = :id AND d.score >= :score")
    int deductScore(@Param("id") Integer id, @Param("score") Integer score);
}
