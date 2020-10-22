package knowledge.日期和时间;

import l.demo.Demo;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        Long time1 = date1.getTime();
        Long time2 = date2.getTime();
        p(time1 < time2);           // true

        // 方法三：before(), after(), equals()
        p(date1.before(date2));     // true
    }

    // Date <=> Long
    @Test
    public void testDateToLong() {

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
    public void testDateToString() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Date → String
        String dateStr = sdf.format(date);
        p(dateStr);

        // String → Date
        date = sdf.parse(dateStr);
        p(date);

    }

    // Date <=> Calendar
    @Test
    public void testDateToCalendar() {

        Calendar calendar = Calendar.getInstance();
//        Calendar calendar = GregorianCalendar.getInstance();

        // Date → Calendar
        calendar.setTime(date);
        p(calendar);

        calendar.add(Calendar.DAY_OF_MONTH, 1); // 1天

        // Calendar → Date
        p(calendar.getTime());

    }

}
