package knowledge.datetime.time;

import l.demo.Demo;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

/**
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/time/Clock.html">Clock</a>
 * <p>一种时钟，使用一个时区提供对当前时刻、日期和时间的访问
 *
 * @author ljh
 * @since 2021/1/16 10:41
 */
public class ClockDemo extends Demo {

    private static Clock clock;

    public ClockDemo() {
        clock = Clock.tick(Clock.systemUTC(), Duration.ofHours(1)); // TickClock[SystemClock[Z],PT1H]
        clock = Clock.tickMinutes(ZoneId.systemDefault());          // TickClock[SystemClock[Asia/Shanghai],PT1M]
        clock = Clock.tickSeconds(ZoneId.systemDefault());          // TickClock[SystemClock[Asia/Shanghai],PT1S]
        clock = Clock.systemUTC();                                  // SystemClock[Z]
        clock = Clock.systemDefaultZone();                          // SystemClock[Asia/Shanghai]
        clock = Clock.system(ZoneId.systemDefault());               // SystemClock[Asia/Shanghai]
        clock = Clock.offset(clock, Duration.ofHours(-1));          // OffsetClock[SystemClock[Asia/Shanghai],PT-1H]
        clock = Clock.fixed(Instant.parse("2008-08-08T12:08:08.00Z"), ZoneId.systemDefault());
        p(clock);                       // FixedClock[2008-08-08T12:08:08Z,Asia/Shanghai]
    }

    public static void main(String[] args) {
        p(clock.millis());              // 1218197288000
        p(clock.getZone());             // Asia/Shanghai
        p(clock.instant());             // 2008-08-08T12:08:08Z
        p(Date.from(clock.instant()));  // Fri Aug 08 20:08:08 CST 2008
    }
}
