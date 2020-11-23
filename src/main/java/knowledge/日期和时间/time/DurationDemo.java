package knowledge.日期和时间.time;

import l.demo.Demo;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Duration
 * 表示以秒和纳秒为基准的时长
 * <p>
 * boolean	        equals(Object otherDuration)            检查此持续时间是否等于指定的持续时间
 * int	            compareTo(Duration otherDuration)       将此持续时间与指定持续时间进行比较
 * <p>
 * boolean          isNegative()                            检查此持续时间是否为负值，不包括零
 * boolean	        isZero()                                检查此持续时间是否为零长度
 * <p>
 * https://docs.oracle.com/javase/8/docs/api/java/time/Duration.html
 * https://www.yiibai.com/javatime/javatime_duration.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class DurationDemo extends Demo {

    private Duration dur;
    private Duration dur2;
    private Duration dur3;
    private Duration durDay;
    private Duration durHour;
    private Duration durMinute;

    /**
     * 获取 Duration
     * static Duration	between(Temporal startInclusive, Temporal endExclusive)
     * static Duration	from(TemporalAmount amount)
     * static Duration	of(long amount, TemporalUnit unit)
     * static Duration	ofXXX(long xxx) (Days, Hours, Minutes, Seconds, Millis, Nanos)
     * static Duration	parse(CharSequence text)
     */
    public DurationDemo() {
        this.dur = Duration.between(LocalTime.MIN, LocalTime.MAX);
        this.dur2 = Duration.between(LocalTime.MAX, LocalTime.MIN);
        this.dur3 = Duration.from(ChronoUnit.DAYS.getDuration());
        this.durDay = Duration.of(1, ChronoUnit.DAYS);
        this.durHour = Duration.ofHours(1);
        this.durMinute = Duration.parse("PT1M");
    }

    /**
     * long	                get(TemporalUnit unit)      获取请求单元的值
     * long	                getSeconds()                获取此持续时间内的秒数
     * int	                getNano()                   获取此持续时间内每秒内的纳秒数
     * List<TemporalUnit>	getUnits()                  获取此持续时间所支持的单元集
     */
    @Test
    public void get() {
        p(dur.get(ChronoUnit.SECONDS)); // 86399
        p(dur.getSeconds());            // 86399
        p(dur.getNano());               // 999999999
        p(dur.getUnits());              // [Seconds, Nanos]
    }

    /**
     * Duration	        abs()                           返回此持续时间绝对值的副本
     * Duration	        negated()                       返回此持续时间负值的副本
     * Duration	        dividedBy(long divisor)         返回此持续时间除以指定值的副本
     * Duration	        multipliedBy(long multiplicand) 返回此持续时间乘以标量的副本
     * <p>
     * Duration	        withNanos(int nanoOfSecond)     以指定的毫微秒数返回此持续时间的副本
     * Duration	        withSeconds(long seconds)       返回指定秒数的此持续时间的副本
     * <p>
     * Temporal	        addTo(Temporal temporal)        将此持续时间添加到指定的时态对象
     * Temporal	        subtractFrom(Temporal temporal) 从指定的时态对象中减去这个持续时间
     */
    @Test
    public void calculate() {
        p(dur);                     // PT23H59M59.999999999S
        p(dur.negated());           // PT-24H0.000000001S

        p(dur2);                    // PT-24H0.000000001S
        p(dur2.abs());              // PT23H59M59.999999999S

        p(dur3);                    // PT24H
        p(dur3.dividedBy(4));       // PT6H
        p(dur3.multipliedBy(4));    // PT96H

        // ???
        p(dur3.withNanos(888));     // PT24H0.000000888S
        p(dur3.withSeconds(888));   // PT14M48S

        LocalDateTime ldt = LocalDateTime.of(2008, 8, 8, 20, 8, 8);
        p(ldt);                     // 2008-08-08T20:08:08
        p(dur3.addTo(ldt));         // 2008-08-09T20:08:08
        p(dur3.subtractFrom(ldt));  // 2008-08-07T20:08:08
    }

    /**
     * Duration	        plus(Duration duration)
     * Duration	        plus(long amountToAdd, TemporalUnit unit)
     * Duration	        plusXXX(long xxxToAdd)  (Days, Hours, Minutes, Seconds, Millis, Nanos)
     * <p>
     * Duration	        minus(Duration duration)
     * Duration	        minus(long amountToSubtract, TemporalUnit unit)
     * Duration	        minus(long xxxToSubtract)
     */
    @Test
    public void plus_minus() {
        p(durMinute.plus(Duration.ofSeconds(1)).getSeconds());  // 61
        p(durMinute.plus(1, ChronoUnit.SECONDS).getSeconds());  // 61
        p(durMinute.plusSeconds(1).getSeconds());               // 61

        p(durMinute.minus(Duration.ofSeconds(1)).getSeconds()); // 59
        p(durMinute.minus(1, ChronoUnit.SECONDS).getSeconds()); // 59
        p(durMinute.minusSeconds(1).getSeconds());              // 59
    }

    /**
     * long	            toXXX()
     * (Days, Hours, Minutes, Millis, Nanos)
     */
    @Test
    public void to() {
        p(durDay.toHours());    // 24
        p(durHour.toMinutes()); // 60
    }

}
