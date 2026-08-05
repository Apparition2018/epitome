package l.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * DateFormatUtils
 *
 * @author ljh
 * @see <a href="https://www.cnblogs.com/zemliu/p/3290585.html">SimpleDateFormat的线程安全问题与解决方案</a>
 * @since 2023/1/9 16:16
 */
public final class DateFormatUtils {
    private DateFormatUtils() {
        throw new AssertionError(String.format("No %s instances for you!", this.getClass().getName()));
    }

    /** 存放不同的日期格式模板的 dateFormat 的 Map */
    private static final Map<String, ThreadLocal<SimpleDateFormat>> DATE_FORMAT_MAP = new HashMap<>();

    /**
     * 获取 SimpleDateFormat
     *
     * @param pattern 日期格式模板
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getDateFormat(final String pattern) {
        ThreadLocal<SimpleDateFormat> threadLocal = DATE_FORMAT_MAP.get(pattern);
        // 双重检测锁：防止多次 put 重复的 dateFormat
        if (threadLocal == null) {
            synchronized (DateFormatUtils.class) {
                threadLocal = DATE_FORMAT_MAP.get(pattern);
                if (threadLocal == null) {
                    threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat(pattern));
                    DATE_FORMAT_MAP.put(pattern, threadLocal);
                }
            }
        }
        return threadLocal.get();
    }

    public static String format(Date date, String pattern) {
        return getDateFormat(pattern).format(date);
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        return getDateFormat(pattern).parse(dateStr);
    }
}
