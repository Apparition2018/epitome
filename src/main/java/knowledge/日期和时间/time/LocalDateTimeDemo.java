package knowledge.日期和时间.time;

import l.demo.Demo;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * LocalDateTime
 * ISO-8601日历，没有时区的日期时间，如2007-12-03T10:15:30
 * <p>
 * boolean	        equals(Object obj)                          检查是否等
 * int	            compareTo(ChronoLocalDateTime<?> other)     将此 date-time 与指定 date-time 进行比较
 * boolean	        isBefore(ChronoLocalDateTime<?> other)      检查此 date-time 是否在指定 date-time 之前
 * boolean	        isAfter(ChronoLocalDateTime<?> other)       检查此 date-time 是否在指定 date-time 之后
 * boolean	        isEqual(ChronoLocalDateTime<?> other)       检查此 date-time 是否等于 date-time
 * <p>
 * https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html
 * https://www.yiibai.com/javatime/javatime_localdatetime.html
 */
public class LocalDateTimeDemo extends Demo {

    private LocalDateTime ldt;
    private LocalDateTime ldt2;
    private LocalDateTime ldt3;
    private LocalDateTime ldt4;

    /**
     * 获取 LocalDateTime
     * static LocalDateTime from(TemporalAccessor temporal)
     * static LocalDateTime	now()
     * static LocalDateTime	now(Clock clock)
     * static LocalDateTime	now(ZoneId zone)
     * static LocalDateTime	of(int year, int/Month month, int dayOfMonth, int hour, int minute[, int second, int nanoOfSecond])
     * static LocalDateTime of(LocalDate date, LocalTime time)
     * static LocalDateTime	ofEpochSecond(long epochSecond, int nanoOfSecond, ZoneOffset offset)
     * static LocalDateTime	ofInstant(Instant instant, ZoneId zone)
     * static LocalDateTime	parse(CharSequence text[, DateTimeFormatter formatter])
     */
    public LocalDateTimeDemo() {
        ldt = LocalDateTime.of(2018, 8, 8, 20, 8, 8);
        ldt2 = LocalDateTime.now();
        ldt3 = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        ldt4 = LocalDateTime.parse("2008-08-08T20:08:08");
    }

    /**
     * int	            getXXX()
     */
    @Test
    public void get() {
        p(ldt.getDayOfYear());      // 220
        p(ldt.getDayOfMonth());     // 8
        p(ldt.getDayOfWeek());      // WEDNESDAY
        p(ldt.getYear());           // 2018
        p(ldt.getMonth());          // AUGUST
        p(ldt.getMonthValue());     // 8
        p(ldt.getHour());           // 20
        p(ldt.getMinute());         // 8
        p(ldt.getSecond());         // 8
        p(ldt.getNano());           // 0
    }

    /**
     * LocalDateTime    plusXXX(long xxxToAdd)
     * LocalDateTime    minusXXX(long xxxToSubtract)
     */
    @Test
    public void plus_minus() {
        p(ldt.plusWeeks(1));        // 2018-08-15T20:08:08
        p(ldt.minusMinutes(1));     // 2018-08-08T20:07:08
    }

    /**
     * LocalDateTim     witXXX(int xxx)                     返回更改xxx后的本地时间副本
     */
    @Test
    public void with() {
        p(ldt.withYear(2018));      // 2018-08-08T20:08:08
        p(ldt.withMonth(10));       // 2018-10-08T20:08:08
        p(ldt.withDayOfYear(200));  // 2018-07-19T20:08:08
        p(ldt.withDayOfMonth(18));  // 2018-08-18T20:08:08
        p(ldt.withHour(8));         // 2018-08-08T08:08:08
        p(ldt.withMinute(38));      // 2018-08-08T20:38:08
        p(ldt.withSecond(38));      // 2018-08-08T20:08:38
        p(ldt.withNano(38));        // 2018-08-08T20:08:08.000000038
    }

    /**
     * ZonedDateTime	atZone(ZoneId zone)                 LocalDateTime + time-zone → ZonedDateTime
     */
    @Test
    public void atZone() {
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        p(zdt);                     // 2018-08-08T20:08:08+08:00[Asia/Shanghai]
    }

    /**
     * LocalDate	    toLocalDate()                       LocalDateTime → LocalDate
     * LocalTime	    toLocalTime()                       LocalDateTime → LocalTime
     * <p>
     * ChronoLocalDateTime
     * default Instant	toInstant(ZoneOffset offset)        LocalDateTime → Instant
     * default long	toEpochSecond(ZoneOffset offset)        LocalDateTime → Second (纪元)
     */
    @Test
    public void to() {
        p(ldt.toLocalDate());                   // 2018-08-08
        p(ldt.toLocalTime());                   // 20:08:08
        p(ldt.toInstant(ZoneOffset.UTC));       // 2018-08-08T20:08:08Z
        p(ldt.toEpochSecond(ZoneOffset.UTC));   // 1533758888
    }

    /**
     * LocalDateTime    truncatedTo(TemporalUnit unit)      返回截断到指定单元的副本
     */
    @Test
    public void truncatedTo() {
        p(ldt.truncatedTo(ChronoUnit.DAYS));    // 2018-08-08T00:00
        p(ldt.truncatedTo(ChronoUnit.HOURS));   // 2018-08-08T20:00
        p(ldt.truncatedTo(ChronoUnit.MINUTES)); // 2018-08-08T20:08
        p(ldt.truncatedTo(ChronoUnit.SECONDS)); // 2018-08-08T20:08:08
    }
    
}
