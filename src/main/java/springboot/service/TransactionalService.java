package springboot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.dao.master.ScoreMapper;
import springboot.domain.master.Score;

import java.sql.SQLException;

/**
 * `@Transactional
 * 1. A 方法无 @Transactional 标签，B 方法有 @Transactional 标签，A 调用 B，事务不生效
 * 2. @Transactional 默认仅对 Uncheck Exception 和 Error 生效；如需对其它异常生效，可设置 rollbackFor
 * 3. 多线程下，@Transactional 不生效
 *
 * @author ljh
 * created on 2021/11/18 9:10
 */
@Service
@AllArgsConstructor
public class TransactionalService {

    private final ScoreMapper scoreMapper;

    /**
     * SQLException 属于 Check Exception
     * 如需对其生效，设置 rollbackFor = Exception.class 或 rollbackFor = SQLException.class
     */
    @Transactional(rollbackFor = SQLException.class)
    public void checkException() throws SQLException {
        Score score = new Score().setName("b").setCourse("chinese").setScore(100);
        scoreMapper.insert(score);
        throw new SQLException("发生 Check Exception");
    }
}
