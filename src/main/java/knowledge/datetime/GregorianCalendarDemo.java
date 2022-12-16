package knowledge.datetime;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static l.demo.Demo.p;

/**
 * GregorianCalendar
 * https://www.runoob.com/manual/jdk1.6/java.base/java/util/GregorianCalendar.html
 * <p>
 * 字段                                               默认值
 * ERA                                                  AD
 * YEAR                                                 1970
 * MONTH                                                JANUARY
 * DAY_OF_MONTH                                         1
 * DAY_OF_WEEK                                          一个星期的第一天
 * WEEK_OF_MONTH                                        0
 * AM_PM                                                1
 * HOUR, HOUR_OF_DAY, MINUTE, SECOND, MILLISECOND       0
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class GregorianCalendarDemo extends GregorianCalendar {

    private static final long serialVersionUID = -1103808356456277735L;

    /**
     * GregorianCalendar([int year, int month, int dayOfMonth, int hourOfDay, int minute, int second])
     * 为具有默认语言环境的默认时区构造一个具有给定日期和时间设置的 GregorianCalendar
     * <p>
     * GregorianCalendar([TimeZone zone, Locale aLocale])
     * 在具有给定语言环境的给定时区内构造一个基于当前时间的 GregorianCalendar
     */
    @Test
    public void gregorianCalendar() {
        Calendar cal = new GregorianCalendar(2008, 8, 8);

        p(cal.getTime());   // Mon Sep 08 00:00:00 CST 2008
    }

    /**
     * protected  void	computeFields() ???
     * 将时间值（从 Epoch 至现在的毫秒偏移量）转换为日历字段值
     */
    @Test
    public void computeFields_() {
        GregorianCalendarDemo cal = new GregorianCalendarDemo();
        cal.clear();
        p(cal.getTime());   // Thu Jan 01 00:00:00 CST 1970

        cal.computeFields();
        p(cal.getTime());   // Fri Oct 26 10:09:49 CST 2018
    }

    /**
     * protected  void	computeTime()   ???
     * 将日历字段值转换为时间值（从 Epoch 至现在的毫秒偏移量）
     */
    @Test
    public void computeTime_() {
        GregorianCalendarDemo cal = new GregorianCalendarDemo();
        cal.clear();
        p(cal.getTime());   // Thu Jan 01 00:00:00 CST 1970

        cal.computeTime();
        p(cal.getTime());   // Thu Jan 01 00:00:00 CST 1970
    }

    /**
     * boolean	isLeapYear(int year)
     * 确定给定的年份是否为闰年
     */
    @Test
    public void isLeapYear_() {
        GregorianCalendar cal = new GregorianCalendar();

        p(cal.isLeapYear(cal.get(Calendar.YEAR))); // false
    }
}
