package springboot.domain.master.exam;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 考试场次
 *
 * @author ljh
 * @since 2026/4/29 16:32
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "exam_session")
public class ExamSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 场次名称 */
    @Column(name = "name")
    private String name;
    /** 可预约总数 */
    @Column(name = "max_book_num")
    private Integer maxBookNum;
    /** 当前已预约数 */
    @Column(name = "cur_book_num")
    private Integer curBookNum;
    /** 乐观锁版本号 */
    @Version
    @Column(name = "version")
    private Integer version;
}
