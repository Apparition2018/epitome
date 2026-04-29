package springboot.domain.master.exam;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 考试预约记录
 *
 * @author ljh
 * @since 2026/4/29 16:37
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "exam_book")
public class ExamBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 场次ID */
    @Column(name = "session_id")
    private Long sessionId;
    /** 预约人ID */
    @Column(name = "user_id")
    private Long userId;
}
