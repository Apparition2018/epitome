package knowledge.日期和时间.time;

import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Instant
 * 表示时间线上的瞬时点，定义为距离初始时间 (1970-1-1 00:00) 的时间差
 * <p>
 * boolean	        equals(Object otherInstant)         检查是否等
 * int	            compareTo(Instant otherInstant)     将此 instant 与指定 instant 进行比较
 * boolean	        isBefore(Instant otherInstant)      检查此 instant 是否在指定 instant 之前
 * boolean	        isAfter(Instant otherInstant)       检查此 instant 是否在指定 instant 之后
 * <p>
 * https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html
 * https://www.yiibai.com/javatime/javatime_instant.html
 */
public class InstantDemo {

    private Instant instant;
    private Instant instant2;
    private Instant instant3;

    /**
     * 获取 Instant
     * static Instant	from(TemporalAccessor temporal)                             从时态对象获取Instant实例
     * static Instant   now()                                                       从系统时钟获得当前时间
     * static Instant	now(Clock clock)                                            从指定时钟获取当前瞬间
     * static Instant	ofEpochMilli(long epochMilli)                               从1970-01-01T00:00:00Z的纪元获取一个使用毫秒的Instant实例
     * static Instant	ofEpochSecond(long epochMilli[, long nanoAdjustment])       从1970-01-01T00:00:00Z的纪元获取一个使用秒的Instant实例
     * static Instant	parse(CharSequence text)                                    从文本字符串(如2007-12-03T10:15:30.00Z)获取Instant实例
     */
    public InstantDemo() {
        this.instant = Instant.parse("2008-08-08T20:08:08.00Z");
        this.instant2 = Instant.now();
        this.instant3 = Instant.ofEpochSecond(1218226088L);
    }

    /**
     * int	            getNano()                           获取时间轴上从秒开始以后的纳秒数
     */
    @Test
    public void getNano() {
        p(instant.getNano());       // 0

    }

    /**
     * Instant          plusXXX(long xxxToAdd)              返回此瞬间的副本，并添加指定的持续时间
     * Instant          minusXXX(long xxxToAdd)             返回此瞬间的副本，并减去指定的持续时间
     */
    @Test
    public void plus_minus() {
        p(instant.plusSeconds(1));  // 2008-08-08T20:08:09Z
        p(instant.minusMillis(1));  // 2008-08-08T20:08:07.999Z
        p(instant.minusNanos(1));   // 2008-08-08T20:08:07.999999999Z
    }

    /**
     * ZonedDateTime	atZone(ZoneId zone)                 Instant + time-zone → LocalDateTime
     */
    @Test
    public void atZone() {
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        p(zdt);                     // 2008-08-09T04:08:08+08:00[Asia/Shanghai]
    }

    /**
     * long	            toEpochMilli()                      将此instant转换为从1970-01-01T00:00:00Z的纪元开始的毫秒数
     * long	            getEpochSecond()                    获取来从1970-01-01T00:00:00Z的纪元开始的秒数
     */
    @Test
    public void toEpochMilli() {
        p(instant.toEpochMilli());  // 1218226088000
        p(instant.getEpochSecond());// 1218226088
    }

    /**
     * Instant	        truncatedTo(TemporalUnit unit)      返回截断到指定单元的副本
     */
    @Test
    public void truncatedTo() {
        p(instant.truncatedTo(ChronoUnit.DAYS));    // 2008-08-08T00:00:00Z
        p(instant.truncatedTo(ChronoUnit.HOURS));   // 2008-08-08T20:00:00Z
        p(instant.truncatedTo(ChronoUnit.MINUTES)); // 2008-08-08T20:08:00Z
        p(instant.truncatedTo(ChronoUnit.SECONDS)); // 2008-08-08T20:08:08Z
    }


    public static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}