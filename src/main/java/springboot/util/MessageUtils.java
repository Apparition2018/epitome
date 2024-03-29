package springboot.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * MessageUtils
 * RuoYi (MessageUtils)
 *
 * @author ljh
 * @since 2021/11/21 1:26
 */
public final class MessageUtils {
    private MessageUtils() {
        throw new AssertionError(String.format("No %s instances for you!", this.getClass().getName()));
    }

    /**
     * 根据消息键和参数 获取消息 委托给spring messageSource
     *
     * @param code 消息键
     * @param args 参数
     * @return 获取国际化翻译值
     */
    public static String message(String code, Object... args) {
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
