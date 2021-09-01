package springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.dao.master.ScoreMapper;

/**
 * @author ljh
 * created on 2021/8/30 17:14
 */
@SpringBootTest
public class MybatisTest {

    @Autowired
    private ScoreMapper scoreMapper;

    @Test
    public void testMybatis() {
        scoreMapper.selectAll().forEach(System.out::println);
    }
}
