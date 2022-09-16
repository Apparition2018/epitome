package spring.api.jdbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.dao.ScoreDao;
import spring.model.Score;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static l.demo.Demo.pe;

/**
 * JdbcTemplate
 *
 * @author Arsenal
 * created on 2020/11/27 1:43
 */
public class JdbcTemplateDemo {

    private ClassPathXmlApplicationContext applicationContext;
    private ScoreDao scoreDao;

    @BeforeEach
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        scoreDao = applicationContext.getBean("scoreDao", ScoreDao.class);
    }

    /**
     * 数据源
     */
    @Test
    public void testConnection() throws SQLException {
        DataSource dataSource = applicationContext.getBean("dbcpDataSource", DataSource.class);
        pe(dataSource.getConnection());
    }

    @Test
    public void testSave() {
        Score score = new Score();
        score.setName("赵六");
        score.setCourse("体育");
        score.setScore(100);
        scoreDao.save(score);
    }

    @Test
    public void testFindById() {
        pe(scoreDao.findById(9));
        pe(scoreDao.findById2(9));
        pe(scoreDao.findById3(9));
        pe(scoreDao.findById4(9));
    }

    @Test
    public void testFindAll() {
        pe(scoreDao.findAll());
    }

    @Test
    public void testBatchUpdate() {
        List<Score> scoreList = new ArrayList<>();
        scoreList.add(new Score().setId(1).setScore(82));
        scoreList.add(new Score().setId(2).setScore(99));
        scoreDao.batchUpdate(scoreList);
    }

    @Test
    public void testDelete() {
        scoreDao.delete(9);
    }
}
