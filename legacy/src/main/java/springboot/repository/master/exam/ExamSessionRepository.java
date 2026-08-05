package springboot.repository.master.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springboot.domain.master.exam.ExamSession;

/**
 * 考试场次 JPA Repository
 *
 * @author ljh
 * @since 2026/4/29 16:43
 */
@Repository
public interface ExamSessionRepository extends JpaRepository<ExamSession, Long> {

    /**
     * 预约成功时原子递增当前预约数
     * 仅当 maxBookNum > curBookNum 时才更新（防止超卖）
     *
     * @param id 场次ID
     * @return 更新行数
     */
    @Modifying
    @Query("UPDATE ExamSession e SET e.curBookNum = e.curBookNum + 1 WHERE e.id = :id AND e.maxBookNum > e.curBookNum")
    int incrCurBookNum(@Param("id") Long id);

    /**
     * 取消预约时原子递减当前预约数
     *
     * @param id 场次ID
     * @return 更新行数
     */
    @Modifying
    @Query("UPDATE ExamSession e SET e.curBookNum = e.curBookNum - 1 WHERE e.id = :id AND e.curBookNum > 0")
    int decrCurBookNum(@Param("id") Long id);
}
