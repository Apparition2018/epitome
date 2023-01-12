package knowledge.datetime;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/text/DateFormat.html">DateFormat</a>
 * <p>DateFormat <-> Calendar
 * <pre>
 * Calendar     getCalendar()                       获取与此日期/时间格式器关联的日历
 * void         setCalendar(Calendar newCalendar)   设置此日期格式所使用的日历
 * </pre>
 * DateFormat <-> NumberFormat
 * <pre>
 * NumberFormat getNumberFormat()                   获取此日期/时间格式器用于格式化和解析时间的数字格式器
 * void         setNumberFormat(NumberFormat newNumberFormat) 允许用户设置数字格式器
 * </pre>
 * DateFormat <-> TimeZone
 * <pre>
 * TimeZone     getTimeZone()                       获取时区
 * void         setTimeZone(TimeZone zone)          为此 DateFormat 对象的日历设置时区
 * </pre>
 *
 * @author ljh
 * @since 2020/9/3 10:39
 */
public class DateFormatDemo extends Demo {

    // static DateFormat	getInstance()
    // 获取为日期和时间使用 SHORT 风格的默认日期/时间格式器
    // static DateFormat	getDateInstance([int style[, Locale aLocale]])
    // 获取日期格式器，该格式器具有给定语言环境的给定格式化风格
    // static DateFormat	getDateTimeInstance([int dateStyle, int timeStyle[, Locale aLocale]])
    // 获取日期/时间格式器，该格式器具有给定语言环境的给定格式化风格
    // static DateFormat	getTimeInstance([int style[, Locale aLocale]])
    // 获取时间格式器，该格式器具有给定语言环境的给定格式化风格
    private final DateFormat FORMAT = DateFormat.getDateInstance();
    private final DateFormat SHORT_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ENGLISH);
    private final DateFormat MEDIUM_FORMAT = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH);
    private final DateFormat LONG_FORMAT = DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH);
    private final DateFormat FULL_FORMAT = DateFormat.getDateInstance(DateFormat.FULL, Locale.ENGLISH);

    @Test
    public void testDateFormat() throws ParseException {
        // String       format(Date date)           将一个 Date 格式化为日期/时间字符串
        String date = FORMAT.format(new Date());
        p(date);        // 2021-5-13
        String shortDate = SHORT_FORMAT.format(new Date());
        p(shortDate);   // 5/13/21
        String mediumDate = MEDIUM_FORMAT.format(new Date());
        p(mediumDate);  // May 13, 2021
        String longDate = LONG_FORMAT.format(new Date());
        p(longDate);    // May 13, 2021
        String fullDate = FULL_FORMAT.format(new Date());
        p(fullDate);    // Thursday, May 13, 2021

        // Date	p       parse(String source)       从给定字符串的开始解析文本，以生成一个日期
        System.out.println(SHORT_FORMAT.parse(shortDate));  // Thu May 13 00:00:00 CST 2021
    }
}
