package knowledge.datetime.time;

import org.junit.Test;

import java.time.*;

/**
 * OffsetDateTime
 * 与ISO-8601日历系统中的UTC|格林威治时间(例如2007-12-03T10:15:30+01:00)有偏移的日期-时间
 * 用于在数据库中存储日期或通过网络进行通信
 * <p>
 * ZonedDateTime 和 OffsetDateTime 之间的区别：https://www.php.cn/java-article-415981.html
 * https://docs.oracle.com/javase/8/docs/api/java/time/OffsetDateTime.html
 *
 * @author Administrator
 * created on 2021/1/16 9:51
 */
public class OffsetDateTimeDemo {

    private OffsetDateTime odt;

    public OffsetDateTimeDemo() {
        // static OffsetDateTime	        now([Clock clock / ZoneId zone])
        odt = OffsetDateTime.now();
        // static OffsetDateTime	        of(LocalDate date, LocalTime time, ZoneOffset offset)
        // static OffsetDateTime	        of(LocalDateTime dateTime, ZoneOffset offset)
        odt = OffsetDateTime.of(LocalDateTime.of(2008, 8, 8, 20, 8, 8), ZoneOffset.ofHours(8));
        // static OffsetDateTime	        of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond, ZoneOffset offset)
        odt = OffsetDateTime.of(2008, 8, 8, 20, 8, 8, 0, ZoneOffset.ofHours(8));
        // static OffsetDateTime	        ofInstant(Instant instant, ZoneId zone)
        odt = OffsetDateTime.ofInstant(Instant.parse("2008-08-08T12:08:08.00Z"), ZoneId.systemDefault());
        // static OffsetDateTime	        parse(CharSequence text[, DateTimeFormatter formatter])
        odt = OffsetDateTime.parse("2008-08-08T20:08:08+08:00");
    }

    /**
     * LocalDate	    toLocalDate()               OffsetDateTime → LocalDate
     * LocalTime	    toLocalTime()               OffsetDateTime → LocalTime
     * LocalDateTime	toLocalDateTime()           OffsetDateTime → LocalDateTime
     * ZonedDateTime	toZonedDateTime()           OffsetDateTime → ZonedDateTime
     * <p>
     * ChronoZonedDateTime
     * default Instant	toInstant()                 OffsetDateTime → Instant
     * default long	    toEpochSecond()             OffsetDateTime → Second (纪元)
     */
    @Test
    public void to() {
        System.out.println(odt.toLocalDate());      // 2008-08-08
        System.out.println(odt.toLocalTime());      // 20:08:08
        System.out.println(odt.toLocalDateTime());  // 2008-08-08T20:08:08
        System.out.println(odt.toZonedDateTime());  // 2008-08-08T20:08:08+08:00
        System.out.println(odt.toInstant());        // 2008-08-08T12:08:08Z
    }
}
