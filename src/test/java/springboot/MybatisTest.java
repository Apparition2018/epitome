package springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.domain.slaver.SysUser;
import springboot.mapper.slaver.SysDeptMapper;
import springboot.mapper.slaver.SysUserMapper;

import static l.demo.Demo.p;
import static l.demo.Demo.phr;

/**
 * Mybatis
 * <p>参考：mybatis 项目的 MybatisTest
 *
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
     * <a href="https://pagehelper.github.io/docs/howtouse/">PageHelper</a>
     *
     * @see <a href="http://doc.ruoyi.vip/ruoyi/document/htsc.html#分页实现">RuoYi 分页实现</a>
     */
    @Test
    public void testPageHelper() throws JsonProcessingException {
        /* 返回 Page */
        Page<SysDeptMapper> deptPage = PageHelper.startPage(1, 2).doSelectPage(() -> sysDeptMapper.list(null));
        p(objectMapper.writeValueAsString(deptPage));
        phr(40);

        /* 返回 PageInfo */
        PageInfo<SysDeptMapper> deptPageInfo = PageHelper.startPage(1, 2).doSelectPageInfo(() -> sysDeptMapper.list(null));
        p(objectMapper.writeValueAsString(deptPageInfo));
        phr(40);

        /* 返回 sql 返回数据条数 */
        long count = PageHelper.count(() -> sysDeptMapper.list(null));
        p(count);
    }

    /** &#064;MapKey */
    @Test
    public void testMapKey() {
        sysUserMapper.map(null).entrySet().forEach(user -> {
            try {
                p(objectMapper.writeValueAsString(user));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /** &lt;association/> 和 &lt;collection/> */
    @Test
    public void testAssociationAndCollection() throws JsonProcessingException {
        p(objectMapper.writeValueAsString(sysUserMapper.list(new SysUser())));
    }

    @Test
    public void testAssociation() throws JsonProcessingException {
        p(objectMapper.writeValueAsString(sysUserMapper.list2(new SysUser())));
    }
}
