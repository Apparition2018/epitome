package jar.hutool.date;

import cn.hutool.core.date.*;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * DateUtil
 * <p>
 * static int       compare(Date date1, Date date2)
 * <p>
 * https://hutool.cn/docs/#/core/%E6%97%A5%E6%9C%9F%E6%97%B6%E9%97%B4/%E6%97%A5%E6%9C%9F%E6%97%B6%E9%97%B4%E5%B7%A5%E5%85%B7-DateUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/date/DateUtil.html
 *
 * @author ljh
 * created on 2020/10/26 22:16
 */
public class DateUtilDemo extends Demo {
    private static final String DATE_STR = "2008-08-08";
    private static final String TIME_STR = "20:08:08";
    private static final String DATE_TIME_STR = DATE_STR + " " + TIME_STR;
    private final Date DATE = DATE_TIME_FORMAT.get().parse(DATE_TIME_STR);
    private final LocalDateTime LDT = DateUtil.toLocalDateTime(DATE);
    private final Instant INSTANT = DateUtil.toInstant(DATE);

    public DateUtilDemo() throws ParseException {
    }

    @Test
    public void testDateUtil() {
        // new DateTime()
        p(DateUtil.date());                             // 2020-10-26 23:56:11
        // new DateTime(DATE)
        p(DateUtil.dateNew(DATE));                      // 2020-10-26 23:56:11

        //********** 获取一部分 **********//
        // year, quarter, yearAndQuarter, quarterEnum, month, monthEnum,
        // weekOfYear, weekOfMonth, dayOfMonth, dayOfYear, dayOfWeek, dayOfWeekEnum,
        // hour, minute, second, millisecond
        p(DateUtil.year(DATE));                         // 2008
        p(DateUtil.yearAndQuarter(DATE));               // 20083
        p(DateUtil.quarterEnum(DATE));                  // Q3
        p(DateUtil.month(DATE));                        // 7
        p(DateUtil.dayOfWeek(DATE));                    // 6
        p(DateUtil.dayOfWeekEnum(DATE));                // FRIDAY
        p(DateUtil.hour(DATE, true));                   // 20
        p(DateUtil.minute(DATE));                       // 8

        //********** 开始和结束 **********//
        // Year, Quarter, Month, Week, Day, Second
        p(DateUtil.beginOfYear(DATE));                  // 2008-01-01 00:00:00
        p(DateUtil.beginOfQuarter(DATE));               // 2008-07-01 00:00:00
        p(DateUtil.beginOfMonth(DATE));                 // 2008-08-01 00:00:00
        p(DateUtil.endOfWeek(DATE));                    // 2008-08-10 23:59:59
        p(DateUtil.endOfDay(DATE));                     // 2008-08-08 23:59:59
        p(DateUtil.endOfSecond(DATE));                  // 2008-08-08 20:08:08

        //********** 特定 **********//
        p(DateUtil.now());                              // 2020-10-27 01:28:19
        p(DateUtil.today());                            // 2020-10-27
        p(DateUtil.yesterday());                        // 2020-10-26 01:28:20
        p(DateUtil.tomorrow());                         // 2020-10-28 01:28:20
        p(DateUtil.lastWeek());                         // 2020-10-20 01:28:20
        p(DateUtil.nextWeek());                         // 2020-11-03 01:28:20
        p(DateUtil.lastMonth());                        // 2020-09-27 01:28:20
        p(DateUtil.nextMonth());                        // 2020-11-27 01:28:20

        //********** 舍入 **********//
        p(DateUtil.truncate(DATE, DateField.MONTH));    // 2008-08-01 00:00:00
        p(DateUtil.round(DATE, DateField.HOUR_OF_DAY)); // 2008-08-08 20:00:00
        p(DateUtil.ceiling(DATE, DateField.MINUTE));    // 2008-08-08 20:08:59

        //********** 偏移 **********//
        // DateField
        p(DateUtil.offset(DATE, DateField.AM_PM, 2));   // 2008-08-09 20:08:08
        // Month, Week, Day, Hour, Minute, Second, Millisecond
        p(DateUtil.offsetHour(DATE, -2));               // 2008-08-08 18:08:08

        //********** 时间差 **********//
        // DateUnit
        p(DateUtil.between(DATE, new Date(),
                DateUnit.DAY));                         // 4462
        // Year, Month, Week, Day, Ms
        p(DateUtil.betweenDay(DATE, new Date(), true)); // 4463
        p(DateUtil.betweenDay(DATE, new Date(), false));// 4462
        p(DateUtil.betweenMs(DATE, new Date()));        // 385537083945
        //********** 到当前时间的时间差 **********//
        // Year
        p(DateUtil.age(DATE, new Date()));              // 12
        // Ms, Nt
        p(DateUtil.spendMs(DATE.getTime()));            // 385537083945
        p(DateUtil.spendNt(DATE.getTime()));            // 22041361515616
        //********** 时间差格式化 **********//
        p(DateUtil.formatBetween(
                DateUtil.betweenMs(DATE, new Date()),
                BetweenFormatter.Level.MINUTE));         // 4462天4小时24分
    }

    @Test
    public void testDateUtil2() {
        //********** xxx 的当前时间 **********//
        p(DateUtil.current());                          // 1603731559103
        p(DateUtil.currentSeconds());                   // 1603731559

        //********** 星座和属相 **********//
        p(DateUtil.getZodiac(
                Month.AUGUST.getValue(), 8));           // 狮子座
        p(DateUtil.getChineseZodiac(2008));             // 鼠

        // 是否相等
        // Month, Day, Time, Instant
        p(DateUtil.isSameMonth(DateUtil.yesterday(),
                DateUtil.tomorrow()));                  // true

        // 是否范围内
        p(DateUtil.isIn(DateUtil.yesterday(),
                DATE, DateUtil.tomorrow()));            // true

        // 上下午
        p(DateUtil.isAM(DATE));                         // false
        p(DateUtil.isPM(DATE));                         // true

        //********** 闰年 **********//
        // 是否闰年
        p(DateUtil.isLeapYear(2008));                   // true
        // 年的天数
        p(DateUtil.lengthOfYear(2008));                 // 366
        // 月的天数
        p(DateUtil.lengthOfMonth(8, true));             // 31

        // 年龄
        p(DateUtil.ageOfNow(DATE_TIME_STR));            // 12

        // 年季度数组
        p(DateUtil.yearAndQuarter(DATE, new Date()));
        // [20083, 20084, 20091, 20092, 20093, 20094, ..., 20203]

        // 日数组
        p(DateUtil.rangeToList(DATE, new Date(), DateField.DAY_OF_YEAR));
        // [2008-08-08 20:08:08, 2008-08-09 20:08:08, ..., 2020-10-26 20:08:08]
    }

    /**
     * 转换
     */
    @Test
    public void convert() {
        //********** xxx → DateTime **********//
        // static DateTime      date(long/Date/Calendar/TemporalAccessor))
        p(DateUtil.date(System.currentTimeMillis()));   // 2020-10-26 23:56:11
        p(DateUtil.date(new Date()));                   // 2020-10-26 23:56:11
        p(DateUtil.date(Calendar.getInstance()));       // 2020-10-26 23:56:11
        p(DateUtil.date(LocalDateTime.now()));          // 2020-10-26 23:56:11

        // Date → LocalDateTime
        p(DateUtil.toLocalDateTime(DATE));              // 2008-08-08T20:08:08
        // Instant → LocalDateTime
        p(DateUtil.toLocalDateTime(INSTANT));           // 2008-08-08T20:08:08

        // Date → Instant
        p(DateUtil.toInstant(DATE));                    // 2008-08-08T12:08:08Z
        // TemporalAccessor → Instant
        p(DateUtil.toInstant(LDT));                     // 2008-08-08T12:08:08Z

        // timeStr <-> Second
        p(DateUtil.timeToSecond(TIME_STR));             // 72488
        p(DateUtil.secondToTime(72488));                // 20:08:08

        // nanos → Millis
        p(DateUtil.nanosToMillis(MILLION));             // 1
        // nanos → Seconds
        p(DateUtil.nanosToSeconds(MILLION));            // 0.001
    }

    /**
     * parse
     */
    @Test
    public void parse() {
        //********** String → DateTime **********//
        // static DateTime      parse(CharSequence dateStr[, String format[, Locale locale]])
        p(DateUtil.parse(DATE_TIME_STR));               // 2008-08-08 20:08:08
        p(DateUtil.parse(DATE_TIME_STR,
                DatePattern.NORM_DATE_PATTERN));        // 2008-08-08 00:00:00
        // static DateTime      parseDateTime(CharSequence dateString)
        p(DateUtil.parseDateTime(DATE_TIME_STR));       // 2008-08-08 20:08:08
        // static DateTime      parseDate(CharSequence dateString)
        p(DateUtil.parseDate(DATE_STR));                // 2008-08-08 00:00:00
        // static DateTime      parseTime(CharSequence timeString)
        p(DateUtil.parseTime(TIME_STR));                // 1970-01-01 20:08:08

        //********** String → LocalDateTime **********//
        // static LocalDateTime parseLocalDateTime(CharSequence dateStr[, String format])
        p(DateUtil.parseLocalDateTime(DATE_TIME_STR));  // 2008-08-08T20:08:08
        p(DateUtil.parseLocalDateTime(DATE_TIME_STR,
                DatePattern.NORM_DATETIME_PATTERN));    // 2008-08-08T20:08:08
    }

    /**
     * format
     */
    @Test
    public void format() {
        // static String        format(Date date, String format)
        p(DateUtil.format(DATE, DatePattern.NORM_DATE_PATTERN));            // 2008-08-08
        // static String        formatDate(Date date)
        p(DateUtil.formatDate(DATE));                                       // 2008-08-08
        // static String        formatTime(Date date)
        p(DateUtil.formatTime(DATE));                                       // 20:08:08
        // static String        formatDateTime(Date date)
        p(DateUtil.formatDateTime(DATE));                                   // 2008-08-08 20:08:08
        // static String        formatHttpDate(Date date)
        p(DateUtil.formatHttpDate(DATE));                                   // Fri, 08 Aug 2008 12:08:08 GMT
        // static String        formatChineseDate(Date date, boolean isUppercase, boolean withTime)
        p(DateUtil.formatChineseDate(DATE, false, true)); // 2008年08月08日20时08分08秒

        LocalDateTime localDateTime = LocalDateTime.now();
        // static String        format(LocalDateTime localDateTime, String format)
        p(DateUtil.format(localDateTime, DatePattern.NORM_DATE_PATTERN));   // 2020-10-26
        // static String        formatLocalDateTime(LocalDateTime localDateTime)
        p(DateUtil.formatLocalDateTime(localDateTime));                     // 2020-10-26 23:26:57
    }

    /**
     * 计时器
     * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/date/TimeInterval.html
     */
    @Test
    public void timer() throws InterruptedException {
        setCountDownLatch(100);
        TimeInterval timeInterval = DateUtil.timer();

        IntStream.rangeClosed(1, 100).forEach(i -> new Thread(new MyTask(i)).start());
        countDownLatch.await();

        p(timeInterval.interval());         // 314
        p(timeInterval.intervalMs());       // 314
        p(timeInterval.intervalSecond());   // 0
        p(timeInterval.intervalHour());     // 0
        p(timeInterval.intervalDay());      // 0
        p(timeInterval.intervalWeek());     // 0
        // XX天XX小时XX分XX秒XX毫秒
        p(timeInterval.intervalPretty());   // 316毫秒
        // 重新开始计时
        p(timeInterval.intervalRestart());  // 319
        p(timeInterval.interval());         // 0
    }

    /**
     * 计时器
     *
     * @see spring.api.util.StopWatchDemo
     * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/date/StopWatch.html
     */
    @Test
    public void createStopWatch() throws InterruptedException {
        setCountDownLatch(100);
        StopWatch stopWatch = DateUtil.createStopWatch("StopWatch");

        stopWatch.start("执行任务");
        IntStream.rangeClosed(1, 100).forEach(i -> new Thread(new MyTask(i)).start());
        countDownLatch.await();
        stopWatch.stop();
    }
}
