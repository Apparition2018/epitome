package knowledge.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import static l.demo.Demo.p;

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
public class DateFormatDemo {

    // static DateFormat	getInstance()
    // 获取为日期和时间使用 SHORT 风格的默认日期/时间格式器
    // static DateFormat	getDateInstance([int style[, Locale aLocale]])
    // 获取日期格式器，该格式器具有给定语言环境的给定格式化风格
    // static DateFormat	getDateTimeInstance([int dateStyle, int timeStyle[, Locale aLocale]])
    // 获取日期/时间格式器，该格式器具有给定语言环境的给定格式化风格
    // static DateFormat	getTimeInstance([int style[, Locale aLocale]])
    // 获取时间格式器，该格式器具有给定语言环境的给定格式化风格
    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();
    private static final DateFormat DATE_TIME_FORMAT = DateFormat.getDateTimeInstance();
    private static final DateFormat SHORT_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ENGLISH);
    private static final DateFormat MEDIUM_FORMAT = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH);
    private static final DateFormat LONG_FORMAT = DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH);
    private static final DateFormat FULL_FORMAT = DateFormat.getDateInstance(DateFormat.FULL, Locale.ENGLISH);

    public static void main(String[] args) throws ParseException {
        // String       format(Date date)           将一个 Date 格式化为日期/时间字符串
        String date = DATE_FORMAT.format(new Date());
        p(date);        // 2023年3月13日
        String dateTime = DATE_TIME_FORMAT.format(new Date());
        p(dateTime);    // 2023年3月13日 下午5:33:27
        String shortDate = SHORT_FORMAT.format(new Date());
        p(shortDate);   // 3/13/23
        String mediumDate = MEDIUM_FORMAT.format(new Date());
        p(mediumDate);  // Mar 13, 2023
        String longDate = LONG_FORMAT.format(new Date());
        p(longDate);    // March 13, 2023
        String fullDate = FULL_FORMAT.format(new Date());
        p(fullDate);    // Monday, March 13, 2023

        // Date	p       parse(String source)       从给定字符串的开始解析文本，以生成一个日期
        System.out.println(SHORT_FORMAT.parse(shortDate));  // Mon Mar 13 00:00:00 CST 2023
    }
}
