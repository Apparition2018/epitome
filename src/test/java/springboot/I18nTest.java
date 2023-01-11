package springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.util.MessageUtils;

/**
 * I18n
 * <p><a href="http://doc.ruoyi.vip/ruoyi/document/htsc.html#国际化支持">RuoYi 国际化支持</a>
 *
 * @author ljh
 * @since 2021/11/21 1:59
 */
@SpringBootTest
public class I18nTest {

    @Test
    public void testI18n() {
        System.err.println(MessageUtils.message("login.success"));
    }
}
