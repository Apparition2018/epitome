package spring.demo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.bean.Score;
import spring.dao.ScoreDao;

import javax.sql.DataSource;

import java.sql.SQLException;

import static l.demo.Demo.p;

/**
 * SpringJdbcDemo
 *
 * @author Arsenal
 * created on 2020/11/27 1:43
 */
public class SpringJdbcDemo {

    ClassPathXmlApplicationContext ac;
    ScoreDao scoreDao;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("demo/spring/spring-jdbc.xml");
        scoreDao = ac.getBean("scoreDao", ScoreDao.class);
    }

    /**
     * 数据源
     */
    @Test
    public void testConnection() throws SQLException {
        DataSource dataSource = ac.getBean("dataSource", DataSource.class);
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
