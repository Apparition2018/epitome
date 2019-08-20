package knowledge.日期和时间.time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * LocalDate
 * ISO-8601日历，没有时区的日期，如2007-12-03
 * <p>
 * boolean	        equals(Object obj)                  检查是否等
 * int	            compareTo(ChronoLocalDate other)    将此 date 与指定 date 进行比较
 * boolean	        isBefore(ChronoLocalDate other)     检查此 date 是否在指定 date 之前
 * boolean	        isAfter(ChronoLocalDate other)      检查此 date 是否在指定 date 之后
 * boolean          isLeapYear()                        根据ISO培训日历系统规则，检查年份是否是闰年
 * <p>
 * https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
 * https://www.yiibai.com/javatime/javatime_localdate.html
 */
public class LocalDateDemo {

    private LocalDate ld;
    private LocalDate ld2;
    private LocalDate ld3;
    private LocalDate ld4;


    /**
     * 获取 LocalDate
     * static LocalDate from(TemporalAccessor temporal)
     * static LocalDate	now()
     * static LocalDate	now(Clock clock)
     * static LocalDate	now(ZoneId zone)
     * static LocalDate	of(int year, int/Month month, int dayOfMonth)
     * static LocalDate of(LocalDate date, LocalTime time)
     * static LocalDate	ofEpochDay(long epochDay)
     * static LocalDate	ofYearDay(int year, int dayOfYear)
     * static LocalDate	parse(CharSequence text[, DateTimeFormatter formatter])
     */
    public LocalDateDemo() {
        ld = LocalDate.of(2008, 8, 8);
        ld2 = LocalDate.now();
        ld3 = LocalDate.ofYearDay(2008, 200);
        ld4 = LocalDate.parse("2008-08-08");
    }

    /**
     * int              getXXX()
     */
    @Test
    public void get() {
        p(ld.getDayOfYear());       // 220
        p(ld.getDayOfMonth());      // 8
        p(ld.getDayOfWeek());       // WEDNESDAY
        p(ld.getYear());            // 2018
        p(ld.getMonth());           // AUGUST
        p(ld.getMonthValue());      // 8
    }

    /**
     * LocalDate	    plusXXX(long xxxToAdd)
     * LocalDate	    minusXXX(long xxxToSubtract)
     */
    @Test
    public void plus_minus() {
        p(ld.plusYears(1));         // 2009-08-08
        p(ld.plusMonths(1));        // 2008-09-08
        p(ld.minusWeeks(1));        // 2008-08-01
        p(ld.minusDays(1));         // 2008-08-07
    }

    /**
     * LocalDate	    witXXX(int xxx)                     返回更改xxx后的本地时间副本
     */
    @Test
    public void with() {
        p(ld.withYear(2018));       // 2018-08-08
        p(ld.withMonth(10));        // 2008-10-08
        p(ld.withDayOfYear(200));   // 2008-07-18
        p(ld.withDayOfMonth(18));   // 2008-08-18
    }

    /**
     * LocalDateTime    atStartOfDay([ZoneId zone])                                     LocalDate + midnight [+ time-zone] → LocalDateTime
     * LocalDateTime	atTime(int hour, int minute[, int second, int nanoOfSecond])    LocalDate + time → LocalDateTime
     * LocalDateTime	atTime(LocalTime time)                                          LocalDate + LocalTime → LocalDateTime
     */
    @Test
    public void at() {
        LocalDateTime ldt = ld.atStartOfDay();
        p(ldt);                     // 2008-08-08T00:00

        ldt = ld.atTime(8, 38);
        p(ldt);                     // 2008-08-08T08:38

        ldt = ld.atTime(LocalTime.parse("20:08:08"));
        p(ldt);                     // 2008-08-08T20:08:08
    }

    /**
     * long	            toEpochDay()                        将此日期转换为纪元日
     */
    @Test
    public void toEpochDay() {
        p(ld.toEpochDay());         // 14099
    }

    public static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}
