package springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.service.TransactionalService;

import java.sql.SQLException;

/**
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
