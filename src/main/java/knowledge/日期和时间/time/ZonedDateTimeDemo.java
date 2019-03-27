package knowledge.日期和时间.time;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * ZoneDateTime
 * ISO-8601日历，具有时区的日期时间，如2007-12-03T10:15:30+01:00 Europe/Paris
 * <p>
 * https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html
 * https://www.yiibai.com/javatime/javatime_zoneddatetime.html
 */
public class ZonedDateTimeDemo {

    private ZonedDateTime zdt;

    public ZonedDateTimeDemo() {
        zdt = ZonedDateTime.of(2018, 8, 8,
                20, 8, 8, 0,
                ZoneId.systemDefault());
    }

    /**
     * LocalDate	    toLocalDate()                       ZonedDateTime → LocalDate
     * LocalTime	    toLocalTime()                       ZonedDateTime → LocalTime
     * LocalDateTime	toLocalDateTime()                   ZonedDateTime → LocalDateTime
     *
     * ChronoZonedDateTime
     * default Instant	toInstant()                         ZonedDateTime → Instant
     * default long	    toEpochSecond()                     ZonedDateTime → Second (纪元)
     */
    @Test
    public void to() {
        p(zdt.toLocalDate());       // 2018-08-08
        p(zdt.toLocalTime());       // 20:08:08
        p(zdt.toLocalDateTime());   // 2018-08-08T20:08:08
        p(zdt.toInstant());         // 2018-08-08T12:08:08Z
    }

    public static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}
