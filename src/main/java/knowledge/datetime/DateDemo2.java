package knowledge.datetime;

import l.demo.Demo;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Date
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class DateDemo2 extends Demo {

    private Date date = new Date();

    // 日期比较
    @Test
    public void comparison() {
        Date date1 = new Date(1000000000000L);
        Date date2 = new Date(2000000000000L);

        // 方法一：compareTo()
        p(date1.compareTo(date2));  // -1

        // 方法二：getTime()
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        p(time1 < time2);           // true

        // 方法三：before(), after(), equals()
        p(date1.before(date2));     // true
    }

    // Date <=> Long
    @Test
    public void dateToLong() {
        // Date → long
        long time = date.getTime();
        p(time);

        time += DateUtils.MILLIS_PER_DAY;

        // long → Date
        date.setTime(time);
        p(date);

        date = new Date(time);
        p(date);
    }

    // Date <=> String
    @Test
    public void dateToString() throws ParseException {
        // Date → String
        String dateStr = DATE_TIME_FORMAT.get().format(date);
        p(dateStr);

        // String → Date
        date = DATE_TIME_FORMAT.get().parse(dateStr);
        p(date);
    }

    // Date <=> Calendar
    @Test
    public void dateToCalendar() {
        Calendar calendar = Calendar.getInstance();
        // Calendar calendar = GregorianCalendar.getInstance();

        // Date → Calendar
        calendar.setTime(date);
        p(calendar);

        calendar.add(Calendar.DAY_OF_MONTH, 1); // 1天

        // Calendar → Date
        p(calendar.getTime());
    }
}
