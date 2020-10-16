package knowledge.日期和时间.time;

import l.demo.Demo;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Time 包下的类都是不可变的、线程安全的
 * <p>
 * 枚举：
 * DayOfWeek    MONDAY TUESDAY WEDNESDAY THURSDAY FRIDAY SATURDAY SUNDAY
 * Month        JANUARY FEBRUARY MARCH APRIL MAY JUNE JULY AUGUST SEPTEMBER OCTOBER NOVEMBER DECEMBER
 * <p>
 * https://www.cnblogs.com/wt20/p/8179118.html
 * https://baijiahao.baidu.com/s?id=1580329434941516154&wfr=spider&for=pc
 * https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html
 * https://www.yiibai.com/javatime
 */
public class TimeDemo extends Demo {

    /**
     * LocalDateTime <=> String
     */
    @Test
    public void test1() {
        /* String → LocalDateTime */
        LocalDateTime ldt = LocalDateTime.parse("2008-08-08T20:08:08");
        p(ldt); // 2008-08-08T20:08:08

        /* LocalDateTime → String */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str = dtf.format(ldt);
        p(str); // 2008-08-08 20:08:08
    }

    /**
     * LocalDateTime <=> Date
     */
    @Test
    public void test2() {
        /* LocalDateTime → Date */
        LocalDateTime ldt = LocalDateTime.parse("2008-08-08T20:08:08");
        Instant instant = ldt.toInstant(ZoneOffset.ofHours(8)); // 东8区
        Date date = Date.from(instant);
        p(date);// Fri Aug 08 20:08:08 CST 2008

        /* Date → LocalDateTime */
        instant = date.toInstant();
        ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        p(ldt); // 2008-08-08T20:08:08
    }

}
