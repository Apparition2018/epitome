package jar.apache.commons.lang3.time;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * DateUtils
 * DateFormatUtils
 * <p>
 * https://www.cnblogs.com/aston/p/9053201.html
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/time/DateUtils.html
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/time/DateFormatUtils.html
 */
public class DateUtilsDemo {

    private Date date1;
    private Date date2;
    private Calendar cal1 = Calendar.getInstance();
    private Calendar cal2 = Calendar.getInstance();

    public DateUtilsDemo() throws InterruptedException {
        date1 = new Date();
        TimeUnit.MILLISECONDS.sleep(1);
        date2 = new Date();
    }

    /**
     * addXXX(Date date, int amount)
     * <p>
     * Milliseconds, Seconds, Minutes, Hours, Days, Weeks, Months, Years
     */
    @Test
    public void addXXX() {
        dp(date1);                                  // 2019-10-31 16:55:42
        dp(DateUtils.addYears(date1, 1));   // 2020-10-31 16:55:42
        dp(DateUtils.addMonths(date1, 1));  // 2019-11-30 16:55:42
        dp(DateUtils.addWeeks(date1, 1));   // 2019-11-07 16:55:42
    }

    /**
     * static Date  setXXX(Date date, int amount)
     * <p>
     * Milliseconds, Seconds, Minutes, Hours, Days, Months, Years
     */
    @Test
    public void setXXX() {
        Date date = new Date();
        dp(DateUtils.setMonths(date, 0));       // 2019-01-31 16:54:51
        dp(DateUtils.setDays(date, 1));         // 2019-10-01 16:54:51
        dp(DateUtils.setHours(date, 0));        // 2019-10-31 00:54:51
        dp(DateUtils.setMinutes(date, 0));      // 2019-10-31 16:00:51
        dp(DateUtils.setSeconds(date, 0));      // 2019-10-31 16:54:00
        dp(DateUtils.setMilliseconds(date, 0)); // 2019-10-31 16:54:51
    }

    /**
     * static Calendar / Date	ceiling(Calendar / Date / Object date, int field)    向上取整
     * static Calendar / Date	round(Calendar / Date / Object date, int field)      四舍五入
     * static Calendar / Date	truncate(Calendar / Date / Object date, int field)   向下取整
     */
    @Test
    public void ceilRoundTruncate() {
        dp(date1);                                      // 2019-10-31 16:56:44
        p("=============================\n");

        dp(DateUtils.ceiling(date1, Calendar.YEAR));    // 2020-01-01 00:00:00
        dp(DateUtils.ceiling(date1, Calendar.MONTH));   // 2019-11-01 00:00:00
        dp(DateUtils.ceiling(date1, Calendar.DATE));    // 2019-11-01 00:00:00
        dp(DateUtils.ceiling(date1, Calendar.HOUR));    // 2019-10-31 17:00:00
        dp(DateUtils.ceiling(date1, Calendar.MINUTE));  // 2019-10-31 16:57:00
        p("=============================\n");

        dp(DateUtils.round(date1, Calendar.YEAR));      // 2020-01-01 00:00:00
        dp(DateUtils.round(date1, Calendar.MONTH));     // 2019-11-01 00:00:00
        dp(DateUtils.round(date1, Calendar.DATE));      // 2019-11-01 00:00:00
        dp(DateUtils.round(date1, Calendar.HOUR));      // 2019-10-31 17:00:00
        dp(DateUtils.round(date1, Calendar.MINUTE));    // 2019-10-31 16:57:00
        p("=============================\n");

        dp(DateUtils.truncate(date1, Calendar.YEAR));   // 2019-01-01 00:00:00
        dp(DateUtils.truncate(date1, Calendar.MONTH));  // 2019-10-01 00:00:00
        dp(DateUtils.truncate(date1, Calendar.DATE));   // 2019-10-31 00:00:00
        dp(DateUtils.truncate(date1, Calendar.HOUR));   // 2019-10-31 16:00:00
        dp(DateUtils.truncate(date1, Calendar.MINUTE)); // 2019-10-31 16:56:00
    }

    /**
     * static string        format(...)
     * <p>
     * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/time/DateFormatUtils.html
     */
    @Test
    public void format() {
        p(DateFormatUtils.format(date1, "yyyy-MM-dd")); // 2018-11-19
    }

    /**
     * static long          getFragmentInXXX(Calendar calendar, int fragment)
     * static long          getFragmentInXXX(Date date, int fragment)
     * fragment     -- 一般使用 Calendar.YEAR, Calendar.MONTH
     * <p>
     * Milliseconds, Seconds, Minutes, Hours, Days
     */
    @Test
    public void getFragmentInXXX() {
        p(DateFormatUtils.format(date1, "yyyy-MM-dd HH:mm:ss"));   // 2018-11-19 10:41:10
        p("=============================\n");

        p("从 2018-01-01 00:00:00 到 当前时间");
        p(DateUtils.getFragmentInDays(date1, Calendar.YEAR));              // 323
        p(DateUtils.getFragmentInHours(date1, Calendar.YEAR));             // 7738
        p(DateUtils.getFragmentInMinutes(date1, Calendar.YEAR));           // 464322
        p(DateUtils.getFragmentInSeconds(date1, Calendar.YEAR));           // 27859353
        p("=============================\n");

        p("从 2018-11-01 00:00:00 到 当前时间");
        p(DateUtils.getFragmentInDays(date1, Calendar.MONTH));             // 19
        p(DateUtils.getFragmentInHours(date1, Calendar.MONTH));            // 442
        p(DateUtils.getFragmentInMinutes(date1, Calendar.MONTH));          // 26562
        p(DateUtils.getFragmentInSeconds(date1, Calendar.MONTH));          // 1593753
        p("=============================\n");

        p("从 2018-11-01 10:00:00 到 当前时间");
        p(DateUtils.getFragmentInDays(date1, Calendar.DATE));              // 0
        p(DateUtils.getFragmentInHours(date1, Calendar.DATE));             // 10
        p(DateUtils.getFragmentInMinutes(date1, Calendar.DATE));           // 642
        p(DateUtils.getFragmentInSeconds(date1, Calendar.DATE));           // 38553
        p("=============================\n");
    }

    /**
     * static Iterator<Calendar>	iterator(Calendar focus, int rangeStyle)
     * static Iterator<Calendar>	iterator(Date focus, int rangeStyle)
     * static Iterator<?>	        iterator(Object focus, int rangeStyle)
     */
    @Test
    public void iterator() {
        Iterator<Calendar> it = DateUtils.iterator(date1, DateUtils.RANGE_WEEK_MONDAY);
        while (it.hasNext()) {
            dp(new Date(it.next().getTimeInMillis()));
//            2019-10-28 00:00:00
//            2019-10-29 00:00:00
//            2019-10-30 00:00:00
//            2019-10-31 00:00:00
//            2019-11-01 00:00:00
//            2019-11-02 00:00:00
//            2019-11-03 00:00:00
        }
    }

    /**
     * static boolean	    isSameDay(Calendar cal1, Calendar cal2)
     * static boolean	    isSameDay(Date date1, Date date2)
     * 判断 Date 或 Calendar 是否是同一天
     * <p>
     * static boolean	    isSameInstant(Calendar cal1, Calendar cal2)
     * static boolean	    isSameInstant(Date date1, Date date2)
     * 判断 Date 或 Calendar 是否是同一毫秒
     * <p>
     * static boolean	    isSameLocalTime(Calendar cal1, Calendar cal2)
     * 判断 Calendar 是否是同一个本地时间
     */
    @Test
    public void isSameDay() {
        p(DateUtils.isSameDay(date1, date2));      // true
        p(DateUtils.isSameInstant(date1, date2));  // false

        cal1.setTime(date1);
        cal2.setTime(date2);
        p(DateUtils.isSameLocalTime(cal1, cal2));  // false
    }

    /**
     * parseDate(String str[, Locale locale], String... parsePatterns)
     * String → Date
     * 该方法对日期和时间的解释是宽松的
     * 如 2018-01-32 将被视为等同于 2018-01-01 后 31 天
     * <p>
     * parseDateStrictly(String str[, Locale locale], String... parsePatterns)
     * String → Date
     * 该方法对日期和时间的解释是严格的
     */
    @Test
    public void parseDate() throws ParseException {
        Date date = DateUtils.parseDate("2018-01-32", "yyyy-MM-dd");
        p(date); // Thu Feb 01 00:00:00 CST 2018

        date = DateUtils.parseDateStrictly("2018-01-32", "yyyy-MM-dd");
        p(date); // Unable to parse the date: 2018-01-32
    }

    /**
     * static Calendar	    toCalendar(Date date[, TimeZone tz])
     * Date → Calendar
     */
    @Test
    public void toCalendar() {
        cal1 = DateUtils.toCalendar(date1);
        cal2 = DateUtils.toCalendar(date2, TimeZone.getTimeZone("Europe/Paris"));
        p(cal1.getTime()); // Calendar → Date
        p(cal2.getTime()); // Calendar → Date
    }

    /**
     * static int	        truncatedCompareTo(Calendar cal1, Calendar cal2, int field)
     * static int	        truncatedCompareTo(Date date1, Date date2, int field)
     * 比较两个 Date 或 Calendar 在指定字段上 的差值
     * <p>
     * static boolean	    truncatedEquals(Calendar cal1, Calendar cal2, int field)
     * static boolean	    truncatedEquals(Date date1, Date date2, int field)
     * 比较两个 Date 或 Calendar 在指定字段上 的是否相等
     */
    @Test
    public void truncatedXXX() {
        p(DateUtils.truncatedCompareTo(date1, date2, Calendar.SECOND));        // 0
        p(DateUtils.truncatedCompareTo(date1, date2, Calendar.MILLISECOND));   // -1

        p(DateUtils.truncatedEquals(date1, date2, Calendar.SECOND));           // true
        p(DateUtils.truncatedEquals(date1, date2, Calendar.MILLISECOND));      // false
    }

    private static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

    private static <T> void dp(T obj) {
        if (obj == null) return;
        System.out.println(DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(obj).replace("T", " "));
    }

}
