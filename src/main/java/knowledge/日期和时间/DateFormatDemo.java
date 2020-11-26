package knowledge.日期和时间;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * DateFormat
 * https://www.runoob.com/manual/jdk1.6/java/text/DateFormat.html
 * <p>
 * DateFormat <-> Calendar
 * Calendar     getCalendar()                       获取与此日期/时间格式器关联的日历
 * void         setCalendar(Calendar newCalendar)   设置此日期格式所使用的日历
 * <p>
 * DateFormat <-> NumberFormat
 * NumberFormat getNumberFormat()                   获取此日期/时间格式器用于格式化和解析时间的数字格式器
 * void         setNumberFormat(NumberFormat newNumberFormat) 允许用户设置数字格式器
 * <p>
 * DateFormat <-> TimeZone
 * TimeZone     getTimeZone()                       获取时区
 * void         setTimeZone(TimeZone zone)          为此 DateFormat 对象的日历设置时区
 *
 * @author ljh
 * created on 2020/9/3 10:39
 */
public class DateFormatDemo {

    private static final DateFormat FORMAT;

    static {
        // static DateFormat	getInstance()
        // 获取为日期和时间使用 SHORT 风格的默认日期/时间格式器
        // static DateFormat	getDateInstance([int style[, Locale aLocale]])
        // 获取日期格式器，该格式器具有给定语言环境的给定格式化风格
        // static DateFormat	getDateTimeInstance([int dateStyle, int timeStyle[, Locale aLocale]])
        // 获取日期/时间格式器，该格式器具有给定语言环境的给定格式化风格
        // static DateFormat	getTimeInstance([int style[, Locale aLocale]])
        // 获取时间格式器，该格式器具有给定语言环境的给定格式化风格
        FORMAT = DateFormat.getDateInstance();
    }

    @Test
    public void testDateFormat() throws ParseException {
        // String	            format(Date date)           将一个 Date 格式化为日期/时间字符串
        String dateString = FORMAT.format(new Date());
        System.out.println(dateString); // 2020-9-3

        // Date	                parse(String source)        从给定字符串的开始解析文本，以生成一个日期
        Date date = FORMAT.parse(dateString);
        System.out.println(date);       // Thu Sep 03 00:00:00 CST 2020
    }
}