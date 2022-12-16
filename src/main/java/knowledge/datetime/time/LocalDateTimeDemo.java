package knowledge.datetime.time;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import static l.demo.Demo.p;

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
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class LocalDateTimeDemo {

    private LocalDateTime ldt;

    /**
     * static LocalDateTime             from(TemporalAccessor temporal)
     * static LocalDateTime	            ofEpochSecond(long epochSecond, int nanoOfSecond, ZoneOffset offset)
     */
    public LocalDateTimeDemo() {
        // static LocalDateTime	        now([Clock clock / ZoneId zone])
        ldt = LocalDateTime.now();
        // static LocalDateTime	        of(int year, int/Month month, int dayOfMonth, int hour, int minute[, int second, int nanoOfSecond])
        // static LocalDateTime         of(LocalDate date, LocalTime time)
        ldt = LocalDateTime.of(2008, 8, 8, 20, 8, 8);
        // static LocalDateTime	ofInstant(Instant instant, ZoneId zone)
        ldt = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        // static LocalDateTime	        parse(CharSequence text[, DateTimeFormatter formatter])
        ldt = LocalDateTime.parse("2008-08-08T20:08:08");
    }

    @Test
    public void testLocalDateTime() {
        // int	                                getXXX()            
        p(ldt.getDayOfYear());                  // 221
        p(ldt.getDayOfMonth());                 // 8
        p(ldt.getDayOfWeek());                  // FRIDAY
        p(ldt.getYear());                       // 2008
        p(ldt.getMonth());                      // AUGUST
        p(ldt.getMonthValue());                 // 8
        p(ldt.getHour());                       // 20
        p(ldt.getMinute());                     // 8
        p(ldt.getSecond());                     // 8
        p(ldt.getNano());                       // 0

        // LocalDateTime                        plusXXX(long xxxToAdd)
        p(ldt.plusWeeks(1));                    // 2008-08-15T20:08:08
        // LocalDateTime                        minusXXX(long xxxToSubtract)
        p(ldt.minusMinutes(1));                 // 2008-08-08T20:07:08

        // LocalDateTim                         witXXX(int xxx)                     返回更改xxx后的本地时间副本
        p(ldt.withYear(2018));                  // 2018-08-08T20:08:08
        p(ldt.withMonth(10));                   // 2008-10-08T20:08:08
        p(ldt.withDayOfYear(200));              // 2008-07-18T20:08:08
        p(ldt.withDayOfMonth(18));              // 2008-08-18T20:08:08
        p(ldt.withHour(8));                     // 2008-08-08T08:08:08
        p(ldt.withMinute(38));                  // 2008-08-08T20:38:08
        p(ldt.withSecond(38));                  // 2008-08-08T20:08:38
        p(ldt.withNano(38));                    // 2008-08-08T20:08:08.000000038

        // LocalDateTime                        truncatedTo(TemporalUnit unit)      返回截断到指定单元的副本
        p(ldt.truncatedTo(ChronoUnit.DAYS));    // 2008-08-08T00:00
        p(ldt.truncatedTo(ChronoUnit.HOURS));   // 2008-08-08T20:00
        p(ldt.truncatedTo(ChronoUnit.MINUTES)); // 2008-08-08T20:08
        p(ldt.truncatedTo(ChronoUnit.SECONDS)); // 2008-08-08T20:08:08
    }

    @Test
    public void convert() {
        // ZonedDateTime	                        atZone(ZoneId zone)                 LocalDateTime + time-zone → ZonedDateTime
        p(ldt.atZone(ZoneId.systemDefault()));      // 2008-08-08T20:08:08+08:00[Asia/Shanghai]
        // OffsetDateTime	                        atOffset(ZoneOffset offset)         LocalDateTime + offset → ZonedDateTime
        p(ldt.atOffset(ZoneOffset.ofHours(8)));     // 2008-08-08T20:08:08+08:00

        // LocalDate	                            toLocalDate()                       LocalDateTime → LocalDate
        p(ldt.toLocalDate());                       // 2008-08-08
        // LocalTime	                            toLocalTime()                       LocalDateTime → LocalTime
        p(ldt.toLocalTime());                       // 20:08:08
        // default Instant	                        toInstant(ZoneOffset offset)        LocalDateTime → Instant
        p(ldt.toInstant(ZoneOffset.ofHours(8)));    // 2008-08-08T12:08:08Z
        // default long	                            toEpochSecond(ZoneOffset offset)    LocalDateTime → Second (纪元)
        p(ldt.toEpochSecond(ZoneOffset.ofHours(8)));// 1218197288
    }
}
