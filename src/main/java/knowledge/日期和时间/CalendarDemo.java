package knowledge.日期和时间;

import l.demo.Demo;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Calendar
 * https://www.runoob.com/manual/jdk1.6/java/util/Calendar.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class CalendarDemo extends Demo {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * abstract  void	add(int field, int amount)
     * 根据日历的规则，为给定的日历字段添加或减去指定的时间
     * 子类 GregorianCalendar 实现
     */
    @Test
    public void add() {
        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.DECEMBER, 25);   // 2018-12-25

        cal.add(Calendar.DAY_OF_MONTH, 10);     // + 10 天
        p(sdf.format(cal.getTime()));           // 2019-01-04

        cal.set(2018, Calendar.DECEMBER, 25);
        cal.roll(Calendar.DAY_OF_MONTH, 10);    // + 10 天
        p(sdf.format(cal.getTime()));           // 2018-12-04

        cal.set(2018, Calendar.DECEMBER, 25);
        cal.roll(Calendar.DAY_OF_MONTH, true);  // + 1 月
        p(sdf.format(cal.getTime()));           // 2018-12-26
    }

    /**
     * void	clear([int field])
     * 将此 Calendar 的给定日历字段值和时间值（从历元至现在的毫秒偏移量）设置成未定义
     */
    @Test
    public void clear() {
        Calendar cal = Calendar.getInstance();

        cal.clear();
        p(cal.getTime());  // Thu Jan 01 00:00:00 CST 1970
    }

    /**
     * boolean	equals(Object obj)
     * 将此 Calendar 与指定 Object 比较
     */
    @Test
    public void get() {
        Calendar cal = Calendar.getInstance();

        p(cal.get(Calendar.MONTH));     // 11
        p(cal.get(Calendar.SECOND));    // 7
    }

    /**
     * int	getActualMaximum(int field)
     * 给定此 Calendar 的时间值，返回指定日历字段可能拥有的最大值
     */
    @Test
    public void getActualMaximum() {
        Calendar cal = Calendar.getInstance();
        // 2 月有 28天
        cal.set(2018, Calendar.FEBRUARY, 1);

        p(cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // 28
        p(cal.getMaximum(Calendar.DAY_OF_MONTH));       // 31，综合所有月份

        p(cal.getActualMinimum(Calendar.DAY_OF_MONTH)); // 1
        p(cal.getMinimum(Calendar.DAY_OF_MONTH));       // 1
    }

    /**
     * int	getActualMinimum(int field)
     * 给定此 Calendar 的时间值，返回指定日历字段可能拥有的最小值
     */
    @Test
    public void getActualMinimum() {
        getActualMaximum();
    }

    /**
     * abstract  int	getMaximum(int field)
     * 返回此 Calendar 实例给定日历字段的最大值
     * 子类 GregorianCalendar 实现
     */
    @Test
    public void getMaximum() {
        getActualMaximum();
    }

    /**
     * abstract  int	getMinimum(int field)
     * 返回此 Calendar 实例给定日历字段的最小值
     * 子类 GregorianCalendar 实现
     */
    @Test
    public void getMinimum() {
        getActualMinimum();
    }

    /**
     * static Calendar	getInstance([TimeZone zone, Locale aLocale])
     * 使用指定时区和语言环境获得一个日历
     */
    @Test
    public void getInstance() {
        Calendar cal = Calendar.getInstance();
        p(cal);
    }

    /**
     * Date	getTime()
     * 返回一个表示此 Calendar 时间值（从历元至现在的毫秒偏移量）的 Date 对象
     */
    @Test
    public void getTime() {
        Calendar cal = Calendar.getInstance();

        try {
            Date date = sdf.parse("2018-10-18");
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        p(cal.getTime()); // Thu Oct 18 00:00:00 CST 2018
    }

    /**
     * long	getTimeInMillis()
     * 返回此 Calendar 的时间值，以毫秒为单位
     */
    @Test
    public void getTimeInMillis() {
        Calendar cal = Calendar.getInstance();

        p(cal.getTimeInMillis());   // 1540522116619

        cal.setTimeInMillis(1000000000000L);
        p(cal.getTime());           // Sun Sep 09 09:46:40 CST 2001
    }

    /**
     * TimeZone	getTimeZone()
     * 获得时区
     */
    @Test
    public void getTimeZone() {
        Calendar cal = Calendar.getInstance();

        p(cal.getTimeZone().getID()); // Asia/Shanghai

        cal.setTimeZone(TimeZone.getTimeZone("GMT-7:00"));
        p(cal.getTimeZone().getID()); // GMT-07:00
    }

    /**
     * abstract  void	roll(int field, boolean up)
     * 在给定的时间字段上添加或减去（上/下）单个时间单元，不更改更大的字段
     * 子类 GregorianCalendar 实现
     * <p>
     * void	roll(int field, int amount)
     * 向指定日历字段添加指定（有符号的）时间量，不更改更大的字段
     */
    @Test
    public void roll() {
        add();
    }

    /**
     * void	set(int field, int value)
     * 将给定的日历字段设置为给定值
     * <p>
     * void	set(int year, int month, int date[, int hourOfDay, int minute, int second])
     * 设置字段 YEAR、MONTH、DAY_OF_MONTH、HOUR_OF_DAY、MINUTE 和 SECOND 的值
     * <p>
     * PS：HOUR - 12小时，HOUR_OF_DAY - 24小时
     */
    @Test
    public void set() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2008);

        cal.set(2008, Calendar.AUGUST, 8); // 设置 2008-08-08
    }

    /**
     * void	setTime(Date date)
     * 使用给定的 Date 设置此 Calendar 的时间
     */
    @Test
    public void setTime() {
        getTime();
    }

    /**
     * void	setTimeInMillis(long millis)
     * 用给定的 long 值设置此 Calendar 的当前时间值
     */
    @Test
    public void setTimeInMillis() {
        getTimeInMillis();
    }

    /**
     * void	setTimeZone(TimeZone value)
     * 使用给定的时区值来设置时区
     */
    @Test
    public void setTimeZone() {
        getTimeZone();
    }

}
