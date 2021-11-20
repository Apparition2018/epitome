package springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.util.MessageUtils;

/**
 * I18n
 * RuoYi 国际化支持：http://doc.ruoyi.vip/ruoyi/document/htsc.html#%E5%9B%BD%E9%99%85%E5%8C%96%E6%94%AF%E6%8C%81
 *
 * @author ljh
 * created on 2021/11/21 1:59
 */
@SpringBootTest
public class I18nTest {

    @Test
    public void testI18n() {
        System.err.println(MessageUtils.message("login.success"));
    }
}
