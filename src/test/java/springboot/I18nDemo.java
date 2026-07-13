package springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import springboot.util.MessageUtils;

import java.util.Locale;

import static l.demo.Demo.pe;

/**
 * I18n
 * <p><a href="http://doc.ruoyi.vip/ruoyi/document/htsc.html#国际化支持">RuoYi 国际化支持</a>
 *
 * @author ljh
 * @since 2021/11/21 1:59
 */
@SpringBootTest(classes = EpitomeApplication.class)
public class I18nDemo {

    @Test
    public void testI18n() {
        Locale original = LocaleContextHolder.getLocale();
        try {
            LocaleContextHolder.setLocale(Locale.SIMPLIFIED_CHINESE);
            pe(MessageUtils.message("login.success"));
        } finally {
            LocaleContextHolder.setLocale(original);
        }
    }
}
