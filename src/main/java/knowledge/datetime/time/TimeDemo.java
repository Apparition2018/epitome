package knowledge.datetime.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static l.demo.Demo.p;

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
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class TimeDemo {

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
        LocalDateTime ldt = LocalDateTime.parse("2008-08-08T20:08:08");
        Date date;

        /* LocalDateTime → Date */
        date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        p(date);    // 2008-08-08 20:08:08

        date = Date.from(ldt.toInstant(ZoneOffset.ofHours(8)));
        p(date);    // 2008-08-08 20:08:08

        /* Date → LocalDateTime */
        ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        p(ldt);     // 2008-08-08T20:08:08

        ldt = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        p(ldt);     // 2008-08-08T20:08:08
    }

}
