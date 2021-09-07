package knowledge.datetime;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Calendar
 * https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/Calendar.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class CalendarDemo extends Demo {

    // static Calendar	    getInstance([TimeZone zone, Locale aLocale])
    // 使用指定时区和语言环境获得一个日历
    private static final Calendar CAL = Calendar.getInstance();

    static {
        // void	set(int year, int month, int date[[, int hourOfDay, int minute], int second])
        // 设置字段中的值 YEAR ， MONTH ， DAY_OF_MONTH ， HOUR_OF_DAY ， MINUTE和 SECOND
        // 使用枚举值来指代月份。如果使用数字，注意 Date，Calendar 等日期相关类的月份 month 取值在 0-11 之间（阿里编程规约）
        CAL.set(2018, Calendar.FEBRUARY, 25);
        // void	            set(int field, int value)       将给定的日历字段设置为给定值
        CAL.set(Calendar.HOUR_OF_DAY, 12);
        CAL.set(Calendar.MINUTE, 30);
        CAL.set(Calendar.SECOND, 0);
    }

    @Test
    public void testCalendar() {
        // Date	            getTime()                       返回一个表示此 Calendar 时间值（从历元至现在的毫秒偏移量）的 Date 对象
        Date dateTime = CAL.getTime();
        p(CAL.getTime());                                   // 2018-02-25 12:30:00
        // void	            setTime(Date date)              使用给定的 Date 设置此 Calendar 的时间
        CAL.setTime(dateTime);

        // long	            getTimeInMillis()               返回此 Calendar 的时间值，以毫秒为单位
        p(CAL.getTimeInMillis());                           // 1519533000871
        // TimeZone	        getTimeZone()                   获得时区
        p(CAL.getTimeZone());                               // sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,dstSavings=0,useDaylight=false,transitions=31,lastRule=null]

        // int	            get(int field)                  返回给定日历字段的值
        p(CAL.get(Calendar.YEAR));                          // 2018
        p(CAL.get(Calendar.MONTH));                         // 1，从 0 开始
        p(CAL.get(Calendar.DAY_OF_MONTH));                  // 25

        // int	            getActualMaximum(int field)     给定此 Calendar 的时间值，返回指定日历字段可能拥有的最大值
        p(CAL.getActualMaximum(Calendar.DAY_OF_MONTH));     // 28
        // abstract  int	getMaximum(int field)           返回此 Calendar 实例给定日历字段的最大值
        p(CAL.getMaximum(Calendar.DAY_OF_MONTH));           // 31
        p(CAL.getActualMinimum(Calendar.DAY_OF_MONTH));     // 1
        p(CAL.getMinimum(Calendar.DAY_OF_MONTH));           // 1

        // abstract  void	add(int field, int amount)      根据日历的规则，将指定的时间量添加或减去给定的日历字段
        CAL.add(Calendar.DAY_OF_MONTH, 10);                 // + 10 天
        p(SDF.format(CAL.getTime()));                       // 2018-03-07
        // void	            roll(int field, int amount)     将指定的（已签名）金额添加到指定的日历字段，而不更改更大的字段
        CAL.roll(Calendar.DAY_OF_MONTH, 10);                // + 10 天
        p(SDF.format(CAL.getTime()));                       // 2018-03-17
        // abstract void	roll(int field, boolean up)     在给定时间字段上添加或减去（向上/向下）单个时间单位而不更改更大的字段
        CAL.roll(Calendar.DAY_OF_MONTH, true);              // + 1 月
        p(SDF.format(CAL.getTime()));                       // 2018-03-18

        // void	clear([int field])                          // 设置此 Calendar未定义的所有日历字段值和时间值（距离 Epoch的毫秒偏移量）
        CAL.clear();
        System.out.println(CAL.getTime());                  // Thu Jan 01 00:00:00 CST 1970
    }

}
