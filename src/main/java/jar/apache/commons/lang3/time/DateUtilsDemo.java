package jar.apache.commons.lang3.time;

import cn.hutool.core.date.DatePattern;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static l.demo.Demo.p;

/**
 * <a href="http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/time/DateUtils.html">DateUtils</a>
 * <p><a href="http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/time/DateFormatUtils.html">DateFormatUtils</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class DateUtilsDemo {

    private final Date DATE1;
    private final Date DATE2;
    private Calendar cal1 = Calendar.getInstance();
    private Calendar cal2 = Calendar.getInstance();

    public DateUtilsDemo() throws InterruptedException {
        DATE1 = new Date();
        TimeUnit.MILLISECONDS.sleep(1);
        DATE2 = new Date();
    }

    /**
     * addXXX(Date date, int amount)
     */
    @Test
    public void addXXX() {
        p(DATE1);                               // 2019-10-31 17:19:10.372
        p(DateUtils.addYears(DATE1, 1));        // 2020-10-31 17:19:10.372
        p(DateUtils.addMonths(DATE1, 1));       // 2019-11-30 17:19:10.372
        p(DateUtils.addWeeks(DATE1, 1));        // 2019-11-07 17:19:10.372
    }

    /**
     * static Date  setXXX(Date date, int amount)
     */
    @Test
    public void setXXX() {
        Date date = new Date();
        p(DateUtils.setMonths(date, 0));        // 2019-01-31 17:19:56.070
        p(DateUtils.setDays(date, 1));          // 2019-10-01 17:19:56.070
        p(DateUtils.setHours(date, 0));         // 2019-10-31 00:19:56.070
        p(DateUtils.setMinutes(date, 0));       // 2019-10-31 17:00:56.070
        p(DateUtils.setSeconds(date, 0));       // 2019-10-31 17:19:00.070
        p(DateUtils.setMilliseconds(date, 0));  // 2019-10-31 17:19:56.000
    }

    /**
     * <p>static Calendar / Date	ceiling(Calendar / Date / Object date, int field)    向上取整
     * <p>static Calendar / Date	round(Calendar / Date / Object date, int field)      四舍五入
     * <p>static Calendar / Date	truncate(Calendar / Date / Object date, int field)   向下取整
     */
    @Test
    public void ceilRoundTruncate() {
        p(DATE1);                                       // 2019-10-31 17:20:13.450
        p("=============================\n");

        p(DateUtils.ceiling(DATE1, Calendar.YEAR));     // 2020-01-01 00:00:00.000
        p(DateUtils.ceiling(DATE1, Calendar.MONTH));    // 2019-11-01 00:00:00.000
        p(DateUtils.ceiling(DATE1, Calendar.DATE));     // 2019-11-01 00:00:00.000
        p(DateUtils.ceiling(DATE1, Calendar.HOUR));     // 2019-10-31 18:00:00.000
        p(DateUtils.ceiling(DATE1, Calendar.MINUTE));   // 2019-10-31 17:21:00.000
        p("=============================\n");

        p(DateUtils.round(DATE1, Calendar.YEAR));       // 2019-01-01 00:00:00.000
        p(DateUtils.round(DATE1, Calendar.MONTH));      // 2019-11-01 00:00:00.000
        p(DateUtils.round(DATE1, Calendar.DATE));       // 2019-11-01 00:00:00.000
        p(DateUtils.round(DATE1, Calendar.HOUR));       // 2019-10-31 17:00:00.000
        p(DateUtils.round(DATE1, Calendar.MINUTE));     // 2019-10-31 17:20:00.000
        p("=============================\n");

        p(DateUtils.truncate(DATE1, Calendar.YEAR));    // 2019-01-01 00:00:00.000
        p(DateUtils.truncate(DATE1, Calendar.MONTH));   // 2019-10-01 00:00:00.000
        p(DateUtils.truncate(DATE1, Calendar.DATE));    // 2019-10-31 00:00:00.000
        p(DateUtils.truncate(DATE1, Calendar.HOUR));    // 2019-10-31 17:00:00.000
        p(DateUtils.truncate(DATE1, Calendar.MINUTE));  // 2019-10-31 17:20:00.000
    }

    /**
     * static string        format(...)
     */
    @Test
    public void format() {
        p(DateFormatUtils.format(DATE1, DatePattern.NORM_DATE_PATTERN)); // 2018-11-19
    }

    /**
     * static long          getFragmentInXXX(Calendar calendar, int fragment)
     * <p>
     * static long          getFragmentInXXX(Date date, int fragment)
     */
    @Test
    public void getFragmentInXXX() {
        p(DateFormatUtils.format(DATE1, DatePattern.NORM_DATETIME_PATTERN)); // 2018-11-19 10:41:10
        p("=============================\n");

        p("从 2018-01-01 00:00:00 到 当前时间");
        p(DateUtils.getFragmentInDays(DATE1, Calendar.YEAR));       // 323
        p(DateUtils.getFragmentInHours(DATE1, Calendar.YEAR));      // 7738
        p(DateUtils.getFragmentInMinutes(DATE1, Calendar.YEAR));    // 464322
        p(DateUtils.getFragmentInSeconds(DATE1, Calendar.YEAR));    // 27859353
        p("=============================\n");

        p("从 2018-11-01 00:00:00 到 当前时间");
        p(DateUtils.getFragmentInDays(DATE1, Calendar.MONTH));      // 19
        p(DateUtils.getFragmentInHours(DATE1, Calendar.MONTH));     // 442
        p(DateUtils.getFragmentInMinutes(DATE1, Calendar.MONTH));   // 26562
        p(DateUtils.getFragmentInSeconds(DATE1, Calendar.MONTH));   // 1593753
        p("=============================\n");

        p("从 2018-11-01 10:00:00 到 当前时间");
        p(DateUtils.getFragmentInDays(DATE1, Calendar.DATE));       // 0
        p(DateUtils.getFragmentInHours(DATE1, Calendar.DATE));      // 10
        p(DateUtils.getFragmentInMinutes(DATE1, Calendar.DATE));    // 642
        p(DateUtils.getFragmentInSeconds(DATE1, Calendar.DATE));    // 38553
        p("=============================\n");
    }

    /**
     * <p>static Iterator<Calendar>	iterator(Calendar focus, int rangeStyle)
     * <p>static Iterator<Calendar>	iterator(Date focus, int rangeStyle)
     * <p>static Iterator<?>        iterator(Object focus, int rangeStyle)
     */
    @Test
    public void iterator() {
        Iterator<Calendar> it = DateUtils.iterator(DATE1, DateUtils.RANGE_WEEK_MONDAY);
        while (it.hasNext()) {
            p(new Date(it.next().getTimeInMillis()));
            // 2019-10-28 00:00:00.000
            // 2019-10-29 00:00:00.000
            // 2019-10-30 00:00:00.000
            // 2019-10-31 00:00:00.000
            // 2019-11-01 00:00:00.000
            // 2019-11-02 00:00:00.000
            // 2019-11-03 00:00:00.000
        }
    }

    /**
     * <pre>
     * static boolean	    isSameDay(Calendar cal1, Calendar cal2)
     * static boolean	    isSameDay(Date DATE1, Date DATE2)
     * </pre>
     * 判断 Date 或 Calendar 是否是同一天
     * <p>
     * <pre>
     * static boolean	    isSameInstant(Calendar cal1, Calendar cal2)
     * static boolean	    isSameInstant(Date DATE1, Date DATE2)
     * </pre>
     * 判断 Date 或 Calendar 是否是同一毫秒
     * <p>
     * <pre>
     * static boolean	    isSameLocalTime(Calendar cal1, Calendar cal2)
     * </pre>
     * 判断 Calendar 是否是同一个本地时间
     */
    @Test
    public void isSameDay() {
        p(DateUtils.isSameDay(DATE1, DATE2));      // true
        p(DateUtils.isSameInstant(DATE1, DATE2));  // false

        cal1.setTime(DATE1);
        cal2.setTime(DATE2);
        p(DateUtils.isSameLocalTime(cal1, cal2));  // false
    }

    /**
     * parseDate(String str[, Locale locale], String... parsePatterns)
     * <pre>
     * String → Date
     * 该方法对日期和时间的解释是宽松的
     * 如 2018-01-32 将被视为等同于 2018-01-01 后 31 天
     * </pre>
     * <p>
     * parseDateStrictly(String str[, Locale locale], String... parsePatterns)
     * <pre>
     * String → Date
     * 该方法对日期和时间的解释是严格的
     * </pre>
     */
    @Test
    public void parseDate() throws ParseException {
        Date date = DateUtils.parseDate("2018-01-32", DatePattern.NORM_DATE_PATTERN);
        p(date); // Thu Feb 01 00:00:00 CST 2018

        date = DateUtils.parseDateStrictly("2018-01-32", DatePattern.NORM_DATE_PATTERN);
        p(date); // Unable to parse the date: 2018-01-32
    }

    /**
     * static Calendar	    toCalendar(Date date[, TimeZone tz])
     * <p>
     * Date → Calendar
     */
    @Test
    public void toCalendar() {
        cal1 = DateUtils.toCalendar(DATE1);
        cal2 = DateUtils.toCalendar(DATE2, TimeZone.getTimeZone("Europe/Paris"));
        p(cal1.getTime()); // Calendar → Date
        p(cal2.getTime()); // Calendar → Date
    }

    /**
     * <pre>
     * static int	        truncatedCompareTo(Calendar cal1, Calendar cal2, int field)
     * static int	        truncatedCompareTo(Date DATE1, Date DATE2, int field)
     * </pre>
     * 比较两个 Date 或 Calendar 在指定字段上大小
     * <p>
     * <pre>
     * static boolean	    truncatedEquals(Calendar cal1, Calendar cal2, int field)
     * static boolean	    truncatedEquals(Date DATE1, Date DATE2, int field)
     * </pre>
     * 比较两个 Date 或 Calendar 在指定字段上 的是否相等
     */
    @Test
    public void truncatedXXX() {
        p(DateUtils.truncatedCompareTo(DATE1, DATE2, Calendar.SECOND));         // 0
        p(DateUtils.truncatedCompareTo(DATE1, DATE2, Calendar.MILLISECOND));    // -1

        p(DateUtils.truncatedEquals(DATE1, DATE2, Calendar.SECOND));            // true
        p(DateUtils.truncatedEquals(DATE1, DATE2, Calendar.MILLISECOND));       // false
    }
}
