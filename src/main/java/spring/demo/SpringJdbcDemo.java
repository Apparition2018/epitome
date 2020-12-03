package spring.demo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.model.Score;
import spring.dao.ScoreDao;

import javax.sql.DataSource;

import java.sql.SQLException;

import static l.demo.Demo.p;

/**
 * spring jdbc
 *
 * @author Arsenal
 * created on 2020/11/27 1:43
 */
public class SpringJdbcDemo {

    ClassPathXmlApplicationContext applicationContext;
    ScoreDao scoreDao;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-dao.xml", " spring/spring-service.xml");
        scoreDao = applicationContext.getBean("scoreDao", ScoreDao.class);
    }

    /**
     * 数据源
     */
    @Test
    public void testConnection() throws SQLException {
        DataSource dataSource = applicationContext.getBean("dbcpDataSource", DataSource.class);
        p(dataSource.getConnection());
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
        p(scoreDao.findById(9));
    }

    @Test
    public void testFindAll() {
        p(scoreDao.findAll());
    }

    @Test
    public void testDelete() {
        scoreDao.delete(9);
    }

}
