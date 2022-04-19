package springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.dao.master.SalesMapper;
import springboot.dao.master.ScoreMapper;
import springboot.dao.slaver.SysUserMapper;
import springboot.domain.master.Sales;
import springboot.domain.slaver.SysUser;

import java.util.List;

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
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * RuoYi 分页实现：http://doc.ruoyi.vip/ruoyi/document/htsc.html#%E5%88%86%E9%A1%B5%E5%AE%9E%E7%8E%B0
     */
    @Test
    public void testPage() throws JsonProcessingException {
        // 方法一
        PageHelper.startPage(1, 5, null).setReasonable(true);
        List<Sales> salesList = salesMapper.selectAll();
        PageInfo<Sales> salesPageInfo = new PageInfo<>(salesList);
        System.out.println(objectMapper.writeValueAsString(salesPageInfo));
        System.out.println("====================");

        // 方法二
        PageInfo<Sales> salesPageInfo2 = PageHelper.startPage(1, 5, null).setReasonable(true).doSelectPageInfo(() -> salesMapper.selectAll());
        System.out.println(objectMapper.writeValueAsString(salesPageInfo2));
    }

    @Test
    public void testMapKey() {
        scoreMapper.selectAllMap().entrySet().forEach(System.out::println);
    }

    @Test
    public void testAssociationAndCollection() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(sysUserMapper.list(new SysUser())));
    }
}
