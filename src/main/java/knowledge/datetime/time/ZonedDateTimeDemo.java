package knowledge.datetime.time;

import java.time.*;

/**
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html">ZoneDateTime</a>
 * <pre>
 * ISO-8601日历，具有时区的日期时间，如2007-12-03T10:15:30+01:00 Europe/Paris，2007-12-03T10:15:30 为 LocalDateTime，+01:00 Europe/Paris 为 ZoneId
 * 在用户特定的时区显示日期时间字段非常方便
 * </pre>
 * <a href="https://www.yiibai.com/javatime/javatime_zoneddatetime.html">java.time.ZonedDateTime 类</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class ZonedDateTimeDemo {

    private static ZonedDateTime zdt;

    public ZonedDateTimeDemo() {
        // static ZonedDateTime	            now([Clock clock / ZoneId zone])
        zdt = ZonedDateTime.now();
        // static OffsetDateTime	        of(LocalDateTime dateTime, ZoneOffset offset)
        // static OffsetDateTime	        of(LocalDate date, LocalTime time, ZoneOffset offset)
        zdt = ZonedDateTime.of(LocalDateTime.of(2008, 8, 8, 20, 8, 8), ZoneOffset.ofHours(8));
        // static OffsetDateTime	        of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond, ZoneOffset offset)
        zdt = ZonedDateTime.of(2008, 8, 8, 20, 8, 8, 0, ZoneId.systemDefault());
        // static OffsetDateTime	        ofInstant(Instant instant, ZoneId zone)
        zdt = ZonedDateTime.ofInstant(Instant.parse("2008-08-08T12:08:08.00Z"), ZoneId.systemDefault());
        // static ZonedDateTime	            parse(CharSequence text[, DateTimeFormatter formatter])
        zdt = ZonedDateTime.parse("2008-08-08T20:08:08+08:00[Asia/Shanghai]");
    }

    /**
     * <pre>
     * LocalDate        toLocalDate()               ZonedDateTime → LocalDate
     * LocalTime        toLocalTime()               ZonedDateTime → LocalTime
     * LocalDateTime    toLocalDateTime()           ZonedDateTime → LocalDateTime
     * OffsetDateTime   toOffsetDateTime()          ZonedDateTime → OffsetDateTime
     *
     * ChronoZonedDateTime
     * default Instant  toInstant()                 ZonedDateTime → Instant
     * default long     toEpochSecond()             ZonedDateTime → Second (纪元)
     * </pre>
     */
    public static void main(String[] args) {
        System.out.println(zdt.toLocalDate());       // 2008-08-08
        System.out.println(zdt.toLocalTime());       // 20:08:08
        System.out.println(zdt.toLocalDateTime());   // 2008-08-08T20:08:08
        System.out.println(zdt.toOffsetDateTime());  // 2008-08-08T20:08:08+08:00
        System.out.println(zdt.toInstant());         // 2008-08-08T12:08:08Z
    }
}
