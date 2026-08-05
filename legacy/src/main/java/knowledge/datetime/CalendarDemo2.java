package knowledge.datetime;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static l.demo.Demo.p;

/**
 * Calendar
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class CalendarDemo2 {

    private final Calendar CALENDAR = Calendar.getInstance();

    public CalendarDemo2() {
        // 2008北京奥运开幕时间
        CALENDAR.set(Calendar.YEAR, 2008);
        CALENDAR.set(Calendar.MONTH, Calendar.AUGUST);
        CALENDAR.set(Calendar.DAY_OF_MONTH, 8);
        CALENDAR.set(Calendar.HOUR_OF_DAY, 20);
        CALENDAR.set(Calendar.MINUTE, 7);
        CALENDAR.set(Calendar.SECOND, 68);  // 超过范围不报错，进位
        p(CALENDAR.getTime());              // Fri Aug 08 20:08:08 CST 2008
    }

    /**
     * 查看3年2个月零5天以后那天所在周的周一？
     */
    @Test
    public void addAndSet() {
        CALENDAR.add(Calendar.YEAR, 3);         // 加3年
        CALENDAR.add(Calendar.MONTH, 2);        // 加2个月
        CALENDAR.add(Calendar.DAY_OF_MONTH, 5); // 加5天
        CALENDAR.set(Calendar.DAY_OF_WEEK, 2);  // CALENDAR.MONDAY 周一
        p(CALENDAR.getTime());                  // Mon Oct 10 20:08:08 CST 2011
    }

    @Test
    public void get() {
        int year = CALENDAR.get(Calendar.YEAR);
        int month = CALENDAR.get(Calendar.MONTH) + 1;       // 月从0开始
        int day = CALENDAR.get(Calendar.DAY_OF_MONTH);
        day = CALENDAR.get(Calendar.DATE);
        p(year + "-" + month + "-" + day);  // 2008-8-8

        int h = CALENDAR.get(Calendar.HOUR);                // 12小时制
        h = CALENDAR.get(Calendar.HOUR_OF_DAY);             // 24小时制
        int m = CALENDAR.get(Calendar.MINUTE);
        int s = CALENDAR.get(Calendar.SECOND);
        p(h + ":" + m + ":" + s);           // 20:8:8

        int doy = CALENDAR.get(Calendar.DAY_OF_YEAR);
        p("当年的第" + doy + "天");          // 当年的第221天

        int dow = CALENDAR.get(Calendar.DAY_OF_WEEK) - 1;   // 从星期日开始，且常量为1
        String[] data = {"日", "一", "二", "三", "四", "五", "六"};
        p("当年的星期" + data[dow]);         // 当年的星期五

        int max = CALENDAR.getActualMaximum(Calendar.DAY_OF_MONTH);
        p("当月共" + max + "天");           // 当月共31天
        max = CALENDAR.getActualMaximum(Calendar.DAY_OF_YEAR);
        p("当年共" + max + "天");           // 当年共366天
    }
}
