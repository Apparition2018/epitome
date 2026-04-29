package springboot.repository.master.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.domain.master.exam.ExamBook;

/**
 * 考试预约记录 JPA Repository
 *
 * @author ljh
 * @since 2026/4/29 16:43
 */
@Repository
public interface ExamBookRepository extends JpaRepository<ExamBook, Long> {
}
