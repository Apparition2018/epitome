package knowledge.datetime.time;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static l.demo.Demo.p;

/**
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html">Instant</a>
 * <p>表示时间线上的瞬时点，定义为距离初始时间 (1970-1-1 00:00) 的时间差
 * <pre>
 * boolean      isBefore(Instant otherInstant)  检查此 instant 是否在指定 instant 之前
 * booleanq     isAfter(Instant otherInstant)   检查此 instant 是否在指定 instant 之后
 * </pre>
 * <a href="https://www.yiibai.com/javatime/javatime_instant.html">java.time.Instant 类</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class InstantDemo {

    private static Instant instant;

    /**
     * 获取 Instant
     * static Instant	from(TemporalAccessor temporal)                             从时态对象获取Instant实例
     * static Instant	ofEpochMilli(long epochMilli)                               从1970-01-01T00:00:00Z的纪元获取一个使用毫秒的Instant实例
     * static Instant	ofEpochSecond(long epochMilli[, long nanoAdjustment])       从1970-01-01T00:00:00Z的纪元获取一个使用秒的Instant实例
     * static Instant	parse(CharSequence text)                                    从文本字符串(如2007-12-03T10:15:30.00Z)获取Instant实例
     */
    public InstantDemo() {
        // static Instant	    now([Clock clock])
        instant = Instant.now(Clock.systemDefaultZone());
        // static Instant	    ofEpochMilli(long epochMilli)                           从1970-01-01T00:00:00Z的纪元获取一个使用毫秒的Instant实例
        // static Instant	    ofEpochSecond(long epochMilli[, long nanoAdjustment])   从1970-01-01T00:00:00Z的纪元获取一个使用秒的Instant实例
        instant = Instant.ofEpochSecond(1218226088L);
        // static Instant	    parse(CharSequence text)                                从文本字符串(如2007-12-03T10:15:30.00Z)获取Instant实例
        instant = Instant.parse("2008-08-08T12:08:08.00Z");
    }

    public static void main(String[] args) {
        // int	            getNano()                       获取时间轴上从秒开始以后的纳秒数
        p(instant.getNano());                               // 0

        // ZonedDateTime	atZone(ZoneId zone)             Instant + time-zone → LocalDateTime
        p(instant.atZone(ZoneId.systemDefault()));          // 2008-08-08T20:08:08+08:00[Asia/Shanghai]

        // long	            toEpochMilli()                  将此instant转换为从1970-01-01T00:00:00Z的纪元开始的毫秒数
        p(instant.toEpochMilli());                          // 1218197288000
        // long	            getEpochSecond()                获取来从1970-01-01T00:00:00Z的纪元开始的秒数
        p(instant.getEpochSecond());                        // 1218197288

        // Instant	        truncatedTo(TemporalUnit unit)  返回截断到指定单元的副本
        p(instant.truncatedTo(ChronoUnit.DAYS));            // 2008-08-08T00:00:00Z
        p(instant.truncatedTo(ChronoUnit.HOURS));           // 2008-08-08T12:00:00Z
        p(instant.truncatedTo(ChronoUnit.MINUTES));         // 2008-08-08T12:08:00Z
        p(instant.truncatedTo(ChronoUnit.SECONDS));         // 2008-08-08T12:08:08Z

        // Instant          plusXXX(long xxxToAdd)          返回此瞬间的副本，并添加指定的持续时间
        p(instant.plusSeconds(1));                          // 2008-08-08T12:08:09Z
        p(instant.minusMillis(1));                          // 2008-08-08T12:08:07.999Z
        // Instant          minusXXX(long xxxToAdd)         返回此瞬间的副本，并减去指定的持续时间
        p(instant.minusNanos(1));                           // 2008-08-08T12:08:07.999999999Z
    }
}
