package knowledge.日期和时间.time;

import l.demo.Demo;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * LocalTime
 * ISO-8601日历，没有时区的时间，如10:15:30
 * <p>
 * boolean	        equals(Object obj)                  检查是否等
 * int	            compareTo(LocalTime other)          将此 time 与指定 time 进行比较
 * boolean	        isBefore(LocalTime other)           检查此 time 是否在指定 time 之前
 * boolean	        isAfter(LocalTime other)            检查此 time 是否在指定 time 之后
 * <p>
 * https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html
 * https://www.yiibai.com/javatime/javatime_localtime.html#
 */
public class LocalTimeDemo extends Demo {

    private LocalTime lt;
    private LocalTime lt2;
    private LocalTime lt3;
    private LocalTime lt4;

    /**
     * 获取 LocalTime
     * static LocalTime	from(TemporalAccessor temporal)
     * static LocalTime	now()
     * static LocalTime	now(Clock clock)
     * static LocalTime	now(ZoneId zone)
     * static LocalTime	of(int hour, int minute[, int second, int nanoOfSecond])
     * static LocalTime	ofNanoOfDay(long nanoOfDay)
     * static LocalTime	ofSecondOfDay(long secondOfDay)
     * static LocalTime	parse(CharSequence text[, DateTimeFormatter formatter])
     */
    public LocalTimeDemo() {
        lt = LocalTime.of(20, 8, 8);
        lt2 = LocalTime.now();
        lt3 = LocalTime.ofSecondOfDay(7230);
        lt4 = LocalTime.parse("20:08:08");
    }

    @Test
    public void constant() {
        p(LocalTime.MIN);           // 00:00
        p(LocalTime.MAX);           // 23:59:59.999999999
        p(LocalTime.MIDNIGHT);      // 00:00
        p(LocalTime.NOON);          // 12:00
    }

    /**
     * int              getXXX()
     */
    @Test
    public void get() {
        p(lt.getHour());            // 20
        p(lt.getMinute());          // 8
        p(lt.getSecond());          // 8
        p(lt.getNano());            // 0
    }

    /**
     * LocalTime	    plusXXX(long xxxToAdd)
     * LocalTime	    minusXXX(long xxxToSubtract)
     */
    @Test
    public void plus_minus() {
        p(lt.plusHours(1));         // 21:08:08
        p(lt.plusMinutes(1));       // 20:09:08
        p(lt.minusSeconds(1));      // 20:08:07
        p(lt.minusNanos(1));        // 20:08:07.999999999
    }

    /**
     * LocalTime	    witXXX(int xxx)                     返回更改xxx后的本地时间副本
     */
    @Test
    public void with() {
        p(lt.withHour(8));          // 08:08:08
        p(lt.withMinute(38));       // 20:38:08
        p(lt.withSecond(38));       // 20:08:38
        p(lt.withNano(38));         // 20:08:08.000000038
    }

    /**
     * LocalDateTime    atDate(LocalDate date)              LocalTime + LocalDate → LocalDateTime
     */
    @Test
    public void atDate() {
        LocalDateTime ldt = lt.atDate(LocalDate.parse("2008-08-08"));
        p(ldt);                     // 2008-08-08T20:08:08.000000008
    }

    /**
     * long	            toNanoOfDay()                       提取纳秒数[天]，0 ~ 24 * 60 * 60 * 1000,000,000 - 1
     * int	            toSecondOfDay()                     提取秒数[天]，0 ~ 24 * 60 * 60 - 1
     */
    @Test
    public void to() {
        p(lt.toNanoOfDay());        // 72488000000008
        p(lt.toSecondOfDay());      // 72488
    }

    /**
     * Instant	        truncatedTo(TemporalUnit unit)      返回截断到指定单元的副本
     */
    @Test
    public void truncatedTo() {
        p(lt.truncatedTo(ChronoUnit.HOURS));   // 20:00
        p(lt.truncatedTo(ChronoUnit.MINUTES)); // 20:08
        p(lt.truncatedTo(ChronoUnit.SECONDS)); // 20:08:08
    }

}