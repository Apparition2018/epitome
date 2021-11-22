package springboot;

import com.github.pagehelper.PageHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.dao.master.SalesMapper;
import springboot.dao.master.ScoreMapper;

/**
 * @author ljh
 * created on 2021/8/30 17:14
 */
@SpringBootTest
public class MybatisTest {

    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private SalesMapper salesMapper;

    @Test
    public void testMybatis() {
        scoreMapper.selectAll().forEach(System.out::println);
    }

    /**
     * RuoYi 分页实现：http://doc.ruoyi.vip/ruoyi/document/htsc.html#%E5%88%86%E9%A1%B5%E5%AE%9E%E7%8E%B0
     */
    @Test
    public void testPage() {
        PageHelper.startPage(1, 10, null).setReasonable(true);
        salesMapper.selectAll().forEach(System.out::println);
    }
}
