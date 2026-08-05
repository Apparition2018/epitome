package jar.apache.commons.lang3.time;

import cn.hutool.core.date.DatePattern;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * <a href="http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/time/DateFormatUtils.html">DateFormatUtils</a>
 *
 * @author ljh
 * @since 2023/3/13 17:14
 */
public class DateFormatUtilsDemo {

    public static void main(String[] args) {
        System.out.println(
                DateFormatUtils.format(
                        new Date(),
                        DatePattern.NORM_DATETIME_PATTERN,
                        TimeZone.getDefault(),
                        Locale.CHINA)); // 2023-03-13 17:25:21
    }
}
