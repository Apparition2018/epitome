package springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.dao.slaver.SysDeptMapper;
import springboot.dao.slaver.SysUserMapper;
import springboot.domain.slaver.SysUser;

/**
 * @author ljh
 * @since 2021/8/30 17:14
 */
@SpringBootTest
public class MybatisTest {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * PageHelper：https://pagehelper.github.io/docs/howtouse/
     * RuoYi 分页实现：http://doc.ruoyi.vip/ruoyi/document/htsc.html#%E5%88%86%E9%A1%B5%E5%AE%9E%E7%8E%B0
     */
    @Test
    public void testPageHelper() throws JsonProcessingException {
        /* 返回 Page */
        Page<SysDeptMapper> deptPage = PageHelper.startPage(1, 2).doSelectPage(() -> sysDeptMapper.list(null));
        System.out.println(objectMapper.writeValueAsString(deptPage));
        System.out.println("====================");

        /* 返回 PageInfo */
        PageInfo<SysDeptMapper> deptPageInfo = PageHelper.startPage(1, 2).doSelectPageInfo(() -> sysDeptMapper.list(null));
        System.out.println(objectMapper.writeValueAsString(deptPageInfo));
        System.out.println("====================");

        /* 返回 sql 返回数据条数 */
        long count = PageHelper.count(() -> sysDeptMapper.list(null));
        System.out.println(count);
    }

    /**
     * `@MapKey
     */
    @Test
    public void testMapKey() {
        sysUserMapper.map(null).entrySet().forEach(user -> {
            try {
                System.out.println(objectMapper.writeValueAsString(user));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * <association/> 和 <collection/>
     */
    @Test
    public void testAssociationAndCollection() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(sysUserMapper.list(new SysUser())));
    }

    @Test
    public void testAssociation() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(sysUserMapper.list2(new SysUser())));
    }
}
