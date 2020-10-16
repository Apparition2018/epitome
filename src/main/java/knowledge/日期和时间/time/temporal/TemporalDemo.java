package knowledge.日期和时间.time.temporal;

import l.demo.Demo;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * Temporal
 * <p>
 * TemporalAccessor
 * ValueRange	    range(TemporalField field)                  获取指定字段的有效值范围
 * <p>
 * TemporalAdjuster
 * Temporal	        adjustInto(Temporal temporal)               调整指定的时间对象以获得此瞬间
 * <p>
 * 实现类：
 * LocalDateTime, LocalDate, LocalTime, ZonedDateTime, OffsetDateTime, offsetTime, Instant, Year, YearMonth
 */
public class TemporalDemo extends Demo {

    private LocalDateTime ldt;
    private LocalDate ld;
    private LocalTime lt;
    private Instant instant;

    public TemporalDemo() {
        ldt = LocalDateTime.parse("2008-08-08T20:08:08");
        ld = LocalDate.parse("2008-08-08");
        lt = LocalTime.parse("20:08:08");
        instant = Instant.parse("2008-08-08T20:08:08.00Z");
    }

    /* Temporal */

    /**
     * boolean	        isSupported(TemporalUnit unit)              检查是否支持指定的单元
     * <p>
     * ChronoUnit       Nanos Micros Millis Seconds Minutes Hours HalfDays Days Weeks Months Years Decades Centuries Millennia Eras Forever
     * <p>
     * LocalDateTime    除了 Forever
     * LocalDate        DateBased           (Days Weeks Months Years Decades Centuries Millennia Eras Forever)
     * LocalTime        TimeBased           (Nanos Micros Millis Seconds Minutes Hours HalfDays)
     * Instant          TimeBased + Days    (Nanos Micros Millis Seconds Minutes Hours HalfDays Days)
     */
    @Test
    public void isSupportedUnit() {
        p(ldt.isSupported(ChronoUnit.FOREVER));     // false

        p(ld.isSupported(ChronoUnit.HALF_DAYS));    // false
        p(ld.isSupported(ChronoUnit.DAYS));         // true

        p(lt.isSupported(ChronoUnit.HALF_DAYS));    // true
        p(lt.isSupported(ChronoUnit.DAYS));         // false

        p(instant.isSupported(ChronoUnit.DAYS));    // true
        p(instant.isSupported(ChronoUnit.WEEKS));   // false
    }

    /**
     * Temporal	        plus(long amountToAdd, TemporalUnit unit)
     * default Temporal	plus(TemporalAmount amount)
     * <p>
     * default Temporal	minus(long amountToSubtract, TemporalUnit unit)
     * default Temporal	minus(TemporalAmount amount)
     */
    @Test
    public void plus_minus() {
        p(ldt.plus(1, ChronoUnit.DAYS));            // 2008-08-09T20:08:08
        p(ld.plus(Period.ofDays(1)));               // 2008-08-09
        p(lt.minus(1, ChronoUnit.HOURS));           // 19:08:08
        p(instant.minus(Duration.ofHours(1)));      // 2008-08-08T19:08:08Z
    }

    /**
     * long	            until(Temporal endExclusive, TemporalUnit unit)
     * 根据指定的单元计算到另一个瞬间的时间量
     */
    @Test
    public void until() {
        p(ldt.until(LocalDateTime.parse("2018-08-08T20:08:08"), ChronoUnit.YEARS)); // 10
        p(instant.until(Instant.parse("2018-08-08T20:08:08Z"), ChronoUnit.DAYS));   // 3652
    }

    /**
     * default Temporal	with(TemporalAdjuster adjuster)             返回即时调整后的副本
     * Temporal	        with(TemporalField field, long newValue)    返回指定字段设置为新值的即时副本
     */
    @Test
    public void with() {
        p(ldt.with(Month.OCTOBER).with(DayOfWeek.MONDAY));  // 2008-10-06T20:08:08
        p(lt.with(LocalTime.NOON));                         // 12:00

        p(ld.with(ChronoField.DAY_OF_WEEK, 7));             // 2008-08-10
        p(instant.with(ChronoField.INSTANT_SECONDS, 59));   // 1970-01-01T00:00:59Z ???
    }

    /* TemporalAccessor */
    //

    /**
     * boolean	        isSupported(TemporalField field)            检查是否支持指定字段
     * <p>
     * ChronoField      NanoOfSecond NanoOfDay MicroOfSecond MicroOfDay MilliOfSecond MilliOfDay SecondOfMinute SecondOfDay MinuteOfHour MinuteOfDay HourOfAmPm ClockHourOfAmPm HourOfDay ClockHourOfDay AmPmOfDay DayOfWeek AlignedDayOfWeekInMonth AlignedDayOfWeekInYear DayOfMonth DayOfYear EpochDay AlignedWeekOfMonth AlignedWeekOfYear MonthOfYear ProlepticMonth YearOfEra Year Era InstantSeconds OffsetSeconds
     * <p>
     * LocalDateTime    DateBased + TimeBased   (NanoOfSecond NanoOfDay MicroOfSecond MicroOfDay MilliOfSecond MilliOfDay SecondOfMinute SecondOfDay MinuteOfHour MinuteOfDay HourOfAmPm ClockHourOfAmPm HourOfDay ClockHourOfDay AmPmOfDay DayOfWeek AlignedDayOfWeekInMonth AlignedDayOfWeekInYear DayOfMonth DayOfYear EpochDay AlignedWeekOfMonth AlignedWeekOfYear MonthOfYear ProlepticMonth YearOfEra Year Era)
     * LocalDate        DateBased               (DayOfWeek AlignedDayOfWeekInMonth AlignedDayOfWeekInYear DayOfMonth DayOfYear EpochDay AlignedWeekOfMonth AlignedWeekOfYear MonthOfYear ProlepticMonth YearOfEra Year Era)
     * LocalTime        TimeBased               (NanoOfSecond NanoOfDay MicroOfSecond MicroOfDay MilliOfSecond MilliOfDay SecondOfMinute SecondOfDay MinuteOfHour MinuteOfDay HourOfAmPm ClockHourOfAmPm HourOfDay ClockHourOfDay AmPmOfDay)
     * Instant          InstantSeconds MicroOfSecond MilliOfSecond NanoOfSecond
     */
    @Test
    public void isSupportedField() {
        p(ldt.isSupported(ChronoField.INSTANT_SECONDS));    // false
        p(ldt.isSupported(ChronoField.OFFSET_SECONDS));     // false

        p(ld.isSupported(ChronoField.AMPM_OF_DAY));         // false
        p(ld.isSupported(ChronoField.DAY_OF_WEEK));         // true

        p(lt.isSupported(ChronoField.AMPM_OF_DAY));         // true
        p(lt.isSupported(ChronoField.DAY_OF_WEEK));         // false

        p(instant.isSupported(ChronoField.INSTANT_SECONDS));// true
    }

    /**
     * int	            get(TemporalField field)                    从现在开始获取指定字段的值
     * long	            getLong(TemporalField field)                从现在开始获取指定字段的值
     */
    @Test
    public void get() {
        p(ldt.get(ChronoField.DAY_OF_YEAR));                // 221
        p(ld.get(ChronoField.DAY_OF_WEEK));                 // 5
        p(lt.get(ChronoField.AMPM_OF_DAY));                 // 1
        p(instant.getLong(ChronoField.INSTANT_SECONDS));    // 1218226088
    }

}
