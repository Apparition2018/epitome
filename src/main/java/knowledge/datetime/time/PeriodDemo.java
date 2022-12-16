package knowledge.datetime.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import static l.demo.Demo.p;

/**
 * Period
 * 表示以年、月、日为基准的时长
 * <p>
 * boolean          isNegative()                            检查这个周期的三个单位中是否有一个是负的
 * boolean	        isZero()                                检查这个周期的三个单位是否都为零
 * <p>
 * long	            toTotalMonths()                         获取此期间的月总数
 * <p>
 * https://docs.oracle.com/javase/8/docs/api/java/time/Period.html
 * https://www.yiibai.com/javatime/javatime_period.html
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class PeriodDemo {

    private Period period;
    private Period period2;
    private Period period3;
    private Period pMonth;
    private Period period4;

    /**
     * 获取 Period
     * static Period	between(LocalDate startDateInclusive, LocalDate endDateExclusive)
     * static Period	from(TemporalAmount amount)
     * static Period	of(int years, int months, int days)
     * static Period	ofXXX(long xxx) (Years, Months, Weeks, Days)
     * static Period	parse(CharSequence text)
     */
    public PeriodDemo() {
        this.period = Period.between(
                LocalDate.ofYearDay(2008, 200),
                LocalDate.ofYearDay(2008, 300));
        this.period2 = Period.from(period);
        this.period3 = Period.of(1, 2, 3);
        this.pMonth = Period.ofMonths(2);
        this.period4 = Period.parse("p1Y2M3D");
    }


    /**
     * int	                getYears()                  获取这段时间的年数
     * int	                getMonths()                 获取此期间的月数
     * int	                getDays()                   得到此期间的天数
     * List<TemporalUnit>	getUnits()                  获取此期间支持的单元集
     */
    @Test
    public void get() {
        p(period3.getYears());  // 1
        p(period3.getMonths()); // 2
        p(period3.getDays());   // 3
        p(period3.getUnits());  // [Years, Months, Days]
    }

    /**
     * Period	        negated()                       返回此持续时间负值的副本
     * Period	        multipliedBy(int scalar)        返回此持续时间乘以标量的副本
     * <p>
     * Period	        withYears(int years)            以指定的年数返回此持续时间的副本
     * Period	        withMonths(int months)          以指定的月数返回此持续时间的副本
     * Period	        withDays(int days)              以指定的日数返回此持续时间的副本
     * <p>
     * Temporal	        addTo(Temporal temporal)        将此持续时间添加到指定的时态对象
     * Temporal	        subtractFrom(Temporal temporal) 从指定的时态对象中减去这个持续时间
     */
    @Test
    public void calculate() {
        p(period2);                 // P3M8D
        p(period2.negated());       // P-3M-8D

        p(pMonth);                  // P2M
        p(pMonth.multipliedBy(2));  // P4M

        p(period4);                 // P1Y2M3D
        p(period4.withYears(4));    // P4Y2M3D
        p(period4.withMonths(4));   // P1Y4M3D
        p(period4.withDays(4));     // P1Y2M4D

        LocalDateTime ldt = LocalDateTime.of(2008, 8, 8, 20, 8, 8);
        p(ldt);                     // 2008-08-08T20:08:08
        p(pMonth.addTo(ldt));       // 2008-10-08T20:08:08
        p(pMonth.subtractFrom(ldt));// 2008-06-08T20:08:08
    }

    /**
     * Period	        plus(TemporalAmount amountToAdd)
     * Period	        plusXXX(long xxxToAdd)  (Year, Months, Days)
     * <p>
     * Period	        minus(TemporalAmount amountToSubtract)
     * Period	        minus(long xxxToSubtract)
     */
    @Test
    public void plus_minus() {
        p(pMonth.plus(Period.ofDays(1)));   // P2M1D
        p(pMonth.plusDays(1));              // P2M1D

        p(pMonth.minus(Period.ofDays(1)));  // P2M-1D
        p(pMonth.minusDays(1));             // P2M-1D
    }

}
