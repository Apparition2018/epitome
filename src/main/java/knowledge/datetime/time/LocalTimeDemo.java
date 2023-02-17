package knowledge.datetime.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static l.demo.Demo.p;

/**
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html">LocalTime</a>
 * <p>ISO-8601日历，没有时区的时间，如10:15:30
 * <pre>
 * boolean      equals(Object obj)                  检查是否等
 * int          compareTo(LocalTime other)          将此 time 与指定 time 进行比较
 * boolean      isBefore(LocalTime other)           检查此 time 是否在指定 time 之前
 * boolean      isAfter(LocalTime other)            检查此 time 是否在指定 time 之后
 * </pre>
 * <a href="https://www.yiibai.com/javatime/javatime_localtime.html">java.time.LocalTime 类</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class LocalTimeDemo {

    private LocalTime lt;

    /**
     * static LocalTime	                from(TemporalAccessor temporal)
     */
    public LocalTimeDemo() {
        // static LocalTime	            now([Clock clock / ZoneId zone])
        lt = LocalTime.now();
        // static LocalTime	            of(int hour, int minute[, int second, int nanoOfSecond])
        lt = LocalTime.of(20, 8, 8);
        // static LocalTime	            ofSecondOfDay(long secondOfDay)
        // static LocalTime	            ofNanoOfDay(long nanoOfDay)
        lt = LocalTime.ofSecondOfDay(7230);
        // static LocalTime	parse(CharSequence text[, DateTimeFormatter formatter])
        lt = LocalTime.parse("20:08:08");
    }

    @Test
    public void testLocalTime() {
        p(LocalTime.MIN);               // 00:00
        p(LocalTime.MAX);               // 23:59:59.999999999
        p(LocalTime.MIDNIGHT);          // 00:00
        p(LocalTime.NOON);              // 12:00

        // int                          getXXX()
        p(lt.getHour());                // 20
        p(lt.getMinute());              // 8
        p(lt.getSecond());              // 8
        p(lt.getNano());                // 0

        // LocalTime	                plusXXX(long xxxToAdd)
        p(lt.plusHours(1));             // 21:08:08
        p(lt.plusMinutes(1));           // 20:09:08
        // LocalTime	                minusXXX(long xxxToSubtract)
        p(lt.minusSeconds(1));          // 20:08:07
        p(lt.minusNanos(1));            // 20:08:07.999999999

        // LocalTime	                witXXX(int xxx)                     返回更改xxx后的本地时间副本
        p(lt.withHour(20));             // 20:08:08
        p(lt.withMinute(8));            // 20:08:08
        p(lt.withSecond(8));            // 20:08:08
        p(lt.withNano(0));              // 20:08:08

        // LocalDateTime                atDate(LocalDate date)              LocalTime + LocalDate → LocalDateTime
        p(lt.atDate(LocalDate.parse("2008-08-08")));    // 2008-08-08T20:08:08.000000008

        // long	                        toNanoOfDay()                       提取纳秒数[天]，0 ~ 24 * 60 * 60 * 1000,000,000 - 1
        p(lt.toNanoOfDay());            // 72488000000008
        // int	                        toSecondOfDay()                     提取秒数[天]，0 ~ 24 * 60 * 60 - 1
        p(lt.toSecondOfDay());          // 72488

        // Instant	                    truncatedTo(TemporalUnit unit)      返回截断到指定单元的副本
        p(lt.truncatedTo(ChronoUnit.HOURS));   // 20:00
        p(lt.truncatedTo(ChronoUnit.MINUTES)); // 20:08
        p(lt.truncatedTo(ChronoUnit.SECONDS)); // 20:08:08
    }
}
