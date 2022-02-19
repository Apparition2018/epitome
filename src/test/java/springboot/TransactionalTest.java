package springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.service.TransactionalService;

import java.sql.SQLException;

/**
 * `@Transactional
 * 1. A 方法无 @Transactional 标签，B 方法有 @Transactional 标签，A 调用 B，事务不生效
 * 2. @Transactional 默认仅对 Uncheck Exception 和 Error 生效；如需对其它异常生效，可设置 rollbackFor
 * 3. 多线程下，@Transactional 不生效
 * <p>
 * RuoYi 事务管理：http://doc.ruoyi.vip/ruoyi/document/htsc.html#%E4%BA%8B%E5%8A%A1%E7%AE%A1%E7%90%86
 *
 * @author ljh
 * created on 2021/11/18 11:58
 */
@SpringBootTest
public class TransactionalTest {

    @Autowired
    private TransactionalService transactionalService;

    @Test
    public void testCheckException() throws SQLException {
        transactionalService.checkException();
    }
}
