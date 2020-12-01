package knowledge.datetime;

import org.junit.Test;

import java.util.Calendar;

import static l.demo.Demo.p;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class CalendarDemo2 {

    private Calendar calendar = Calendar.getInstance();

    public CalendarDemo2() {
        // 2008北京奥运开幕时间
        calendar.set(Calendar.YEAR, 2008);
        calendar.set(Calendar.MONTH, Calendar.AUGUST);
        calendar.set(Calendar.DAY_OF_MONTH, 8);
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 7);
        calendar.set(Calendar.SECOND, 68);  // 超过范围不报错，进位
        p(calendar.getTime());              // Fri Aug 08 20:08:08 CST 2008
    }

    /**
     * 查看3年2个月零5天以后那天所在周的周一？
     */
    @Test
    public void testAddAndSet() {
        calendar.add(Calendar.YEAR, 3);         // 加3年
        calendar.add(Calendar.MONTH, 2);        // 加2个月
        calendar.add(Calendar.DAY_OF_MONTH, 5); // 加5天
        calendar.set(Calendar.DAY_OF_WEEK, 2);  // Calendar.MONDAY 周一
        p(calendar.getTime());                  // Mon Oct 10 20:08:08 CST 2011
    }

    @Test
    public void testGet() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;       // 月从0开始
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        day = calendar.get(Calendar.DATE);
        p(year + "-" + month + "-" + day);  // 2008-8-8

        int h = calendar.get(Calendar.HOUR);                // 12小时制
        h = calendar.get(Calendar.HOUR_OF_DAY);             // 24小时制
        int m = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);
        p(h + ":" + m + ":" + s);           // 20:8:8

        int doy = calendar.get(Calendar.DAY_OF_YEAR);
        p("当年的第" + doy + "天");          // 当年的第221天

        int dow = calendar.get(Calendar.DAY_OF_WEEK) - 1;   // 从星期日开始，且常量为1
        String[] data = {"日", "一", "二", "三", "四", "五", "六"};
        p("当年的星期" + data[dow]);         // 当年的星期五

        int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        p("当月共" + max + "天");           // 当月共31天
        max = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        p("当年共" + max + "天");           // 当年共366天
    }

}
