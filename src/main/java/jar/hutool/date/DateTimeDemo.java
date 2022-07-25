package jar.hutool.date;

import cn.hutool.core.date.*;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Objects;

import static l.demo.Demo.p;

/**
 * DateTime
 * DateTime 继承 java.util.Date，使用 DateTime 可以完全替代开发中 Date 的使用
 * <p>
 * https://hutool.cn/docs/#/core/%E6%97%A5%E6%9C%9F%E6%97%B6%E9%97%B4/%E6%97%A5%E6%9C%9F%E6%97%B6%E9%97%B4%E5%AF%B9%E8%B1%A1-DateTime
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/date/DateTime.html
 *
 * @author Arsenal
 * created on 2020/10/27 2:31
 */
public class DateTimeDemo {

    private static final DateTime DATETIME = new DateTime("2008-08-08 20:08:08", DatePattern.NORM_DATETIME_PATTERN);
    private static final DateTime NOW = DateTime.now();

    @Test
    public void testDateTime() {
        DateTime dateTime;

        //********** 创建对象 **********//
        // 1.new DateTime()
        // TimeZone | Date[, TimeZone] | Calendar | Instant[, ZoneId] |
        // TemporalAccessor | ZoneDateTime | long[, TimeZone] |
        // CharSequence, formatStr/DateFormat/DateTimeFormatter/DateParser
        dateTime = new DateTime("2008-08-08 20:08:08", DatePattern.NORM_DATETIME_FORMAT);
        p(dateTime);
        // 2.Date.of()
        // long | Date | Calendar | dateStr, formatStr
        dateTime = DateTime.of("2008-08-08 20:08:08", DatePattern.NORM_DATETIME_PATTERN);
        p(dateTime);

        //********** 获取一部分 **********//
        // year, quarter, yearAndQuarter, quarterEnum, month, monthBaseOne, monthStartFromOne, monthEnum,
        // weekOfYear, weekOfMonth, dayOfMonth, dayOfYear, dayOfWeek, dayOfWeekInMonth, dayOfWeekEnum,
        // hour, minute, second, millisecond
        p(dateTime.quarterEnum());
        p(dateTime.monthStartFromOne());

        //********** 偏移 **********//
        // 修改自身
        p(dateTime.offset(DateField.YEAR, 1));
        p(dateTime.offset(DateField.MONTH, -12));
        // 返回新对象
        p(dateTime.offsetNew(DateField.YEAR, 1));

        //********** 时间差 **********//
        // DateUnit
        p(DATETIME.between(new Date(), DateUnit.DAY));  // 4462
        p(DATETIME.between(new Date(), DateUnit.DAY,
                BetweenFormatter.Level.SECOND));         // 4462天15小时12分38秒
    }

    /**
     * 获取和设置
     * <p>
     * TimeZone         getTimeZone()
     * ZoneId           getZoneId()
     * DateTime         setTimeZone(TimeZone timeZone)
     */
    @Test
    public void testGetAndSet() {
        p(DATETIME.setField(DateField.YEAR, 2020));
        p(DATETIME.getField(DateField.YEAR));

        // 设置一周的第一天 ?
        p(DATETIME.setFirstDayOfWeek(Week.SUNDAY));     // 2020-08-08 20:08:08
        p(DATETIME.getFirstDayOfWeek());                // SUNDAY

        DATETIME.setTime(DateUtil.toInstant(DATETIME).toEpochMilli());
    }

    /**
     * 转换
     */
    @Test
    public void convert() {
        // DateTime → Calendar
        // toCalendar([[Locale][, TimeZone]])
        p(DATETIME.toCalendar());               // java.util.GregorianCalendar[...]
        // DateTime → Date
        p(DATETIME.toJdkDate());                // Fri Aug 08 20:08:08 CST 2008
        // DateTime → java.sql.Date
        p(DATETIME.toSqlDate());                // 2008-08-08 20:08:08
        // DateTime → String
        // toString([formatStr/TimeZone/DatePrinter/DateFormat)
        p(DATETIME.toString("yyyy/MM/dd"));     // 2008/08/08
        // DateTime → String
        p(DATETIME.toStringDefaultTimeZone());  // 2008-08-08 20:08:08
        // DateTime → String
        p(DATETIME.toDateStr());                // 2008-08-08
        // DateTime → String
        p(DATETIME.toTimeStr());                // 20:08:08
        // DateTime → String
        p(DATETIME.toMsStr());                  // 2008-08-08 20:08:08.000
        // DateTime → Timestamp
        p(DATETIME.toTimestamp());              // 2008-08-08 20:08:08
    }

    /**
     * 判断
     */
    @Test
    public void testIs() {
        p(DATETIME.isAfter(NOW));           // false
        p(DATETIME.isAfterOrEquals(NOW));   // false
        p(DATETIME.isBefore(NOW));          // true
        p(DATETIME.isBeforeOrEquals(NOW));  // true

        // 范围内
        p(DATETIME.isIn(DATETIME, NOW));    // true
        // 上下午
        p(DATETIME.isAM());                 // false
        p(DATETIME.isPM());                 // true
        // 闰年
        p(DATETIME.isLeapYear());           // true
        // 周末
        p(DATETIME.isWeekend());            // false
        // 可变的 (可被修改的)
        p(DATETIME.isMutable());            // true
    }

    /**
     * setMutable(false) 使其变为不可变对象
     * 在可变模式下，offset(), setField(), setTime() 默认修改自身
     * 在不可变模式下，offset(), setField() 返回一个新对象，setTime() 抛出异常
     */
    @Test
    public void mutable() {
        DateTime offset;
        offset = DATETIME.offset(DateField.YEAR, 1);
        p(Objects.equals(offset, DATETIME));// true

        // 修改为不可变对象
        DATETIME.setMutable(false);
        offset = DATETIME.offset(DateField.YEAR, 1);
        p(Objects.equals(offset, DATETIME));// false
    }
}
