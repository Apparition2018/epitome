package knowledge.日期和时间;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDemo2 {

    private Date date = new Date();

    // 日期比较
    @Test
    public void comparison() {
        Date date1 = new Date(1000000000000L);
        Date date2 = new Date(2000000000000L);

        // 方法一：compareTo()
        System.out.println(date1.compareTo(date2)); // -1

        // 方法二：getTime()
        Long time1 = date1.getTime();
        Long time2 = date2.getTime();
        System.out.println(time1 < time2);          // true

        // 方法三：before(), after(), equals()
        System.out.println(date1.before(date2));    // true
    }

    // Date <=> Long
    @Test
    public void testDateToLong() {

        // Date → long
        long time = date.getTime();
        System.out.println(time);

        time += 1000 * 60 * 60 * 24; // 1天

        // long → Date
        date.setTime(time);
        System.out.println(date);

        date = new Date(time);
        System.out.println(date);

    }

    // Date <=> String
    @Test
    public void testDateToString() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Date → String
        String dateStr = sdf.format(date);
        System.out.println(dateStr);

        // String → Date
        date = sdf.parse(dateStr);
        System.out.println(date);

    }

    // Date <=> Calendar
    @Test
    public void testDateToCalendar() {

        Calendar calendar = Calendar.getInstance();
//        Calendar calendar = GregorianCalendar.getInstance();

        // Date → Calendar
        calendar.setTime(date);
        System.out.println(calendar);

        calendar.add(Calendar.DAY_OF_MONTH, 1); // 1天

        // Calendar → Date
        System.out.println(calendar.getTime());

    }

}
