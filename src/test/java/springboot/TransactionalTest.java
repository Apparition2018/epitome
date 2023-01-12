package springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.service.TransactionalService;

import java.sql.SQLException;

/**
 * &#064;Transactional
 * <pre>
 * <a href="https://mp.weixin.qq.com/s/puPGJKHQNk4Dvs6rqB6Abg">自定义Spring事务回调</a>
 * <a href="https://doc.ruoyi.vip/ruoyi/document/htsc.html#事务管理">RuoYi 事务管理</a>
 * </pre>
 *
 * @author ljh
 * @since 2021/11/18 11:58
 */
@SpringBootTest
public class TransactionalTest {

    @Autowired
    private TransactionalService transactionalService;

    @Test
    public void rollbackCheckedException() throws SQLException {
        transactionalService.rollbackCheckedException();
    }

    @Test
    public void transactionalEventListener() {
        transactionalService.transactionalEventListener();
    }

    @Test
    public void transactionSynchronizationManager() {
        transactionalService.transactionSynchronizationManager();
    }
}
