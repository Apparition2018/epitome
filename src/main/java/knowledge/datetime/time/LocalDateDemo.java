package knowledge.datetime.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static l.demo.Demo.p;
import static l.demo.Demo.pe;

/**
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html">LocalDate</a>
 * <p>ISO-8601日历，没有时区的日期，如2007-12-03
 * <pre>
 * boolean      equals(Object obj)                  检查是否等
 * int          compareTo(ChronoLocalDate other)    将此 date 与指定 date 进行比较
 * boolean      isBefore(ChronoLocalDate other)     检查此 date 是否在指定 date 之前
 * boolean      isAfter(ChronoLocalDate other)      检查此 date 是否在指定 date 之后
 * boolean      isLeapYear()                        根据ISO培训日历系统规则，检查年份是否是闰年
 * </pre>
 * <a href="https://www.yiibai.com/javatime/javatime_localdate.html">java.time.LocalDate 类</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class LocalDateDemo {

    private static LocalDate ld;

    /**
     * <pre>
     * static LocalDate                 from(TemporalAccessor temporal)
     * static LocalDate                 ofEpochDay(long epochDay)
     * </pre>
     */
    public LocalDateDemo() {
        // static LocalDate	            now([Clock clock / ZoneId zone])
        ld = LocalDate.now();
        // static LocalDate	            of(int year, int/Month month, int dayOfMonth)
        // static LocalDate             of(LocalDate date, LocalTime time)
        ld = LocalDate.of(2008, 8, 8);
        // static LocalDate	            ofYearDay(int year, int dayOfYear)
        ld = LocalDate.ofYearDay(2008, 200);
        // static LocalDate	            parse(CharSequence text[, DateTimeFormatter formatter])
        ld = LocalDate.parse("2008-08-08");
    }

    public static void main(String[] args) {
        // int                          getXXX()
        p(ld.getDayOfYear());           // 220
        p(ld.getDayOfMonth());          // 8
        p(ld.getDayOfWeek());           // WEDNESDAY
        p(ld.getYear());                // 2018
        p(ld.getMonth());               // AUGUST
        p(ld.getMonthValue());          // 8

        // int                          lengthOfMonth()                     返回某月的天数
        p(ld.lengthOfMonth());          // 31
        // int                          lengthOfYear()                      返回某年的天数
        p(ld.lengthOfYear());           // 366

        // LocalDate	                plusXXX(long xxxToAdd)
        p(ld.plusYears(1));             // 2009-08-08
        p(ld.plusMonths(1));            // 2008-09-08
        // LocalDate	                minusXXX(long xxxToSubtract)
        p(ld.minusWeeks(1));            // 2008-08-01
        p(ld.minusDays(1));             // 2008-08-07

        // LocalDate	                witXXX(int xxx)                     返回更改xxx后的本地时间副本
        p(ld.withYear(2008));           // 2008-08-08
        p(ld.withMonth(8));             // 2008-08-08
        p(ld.withDayOfYear(221));       // 2008-08-08
        p(ld.withDayOfMonth(8));        // 2008-08-18

        // LocalDateTime                atStartOfDay([ZoneId zone])                                     LocalDate + midnight [+ time-zone] → LocalDateTime
        p(ld.atStartOfDay());           // 2008-08-08T00:00
        // LocalDateTime	            atTime(int hour, int minute[, int second, int nanoOfSecond])    LocalDate + time → LocalDateTime
        p(ld.atTime(20, 8));            // 2008-08-08T20:08
        // LocalDateTime	            atTime(LocalTime time)                                          LocalDate + LocalTime → LocalDateTime
        p(ld.atTime(LocalTime.parse("20:08:08")));  // 2008-08-08T20:08:08

        // long	                        toEpochDay()                        将此日期转换为纪元日
        p(ld.toEpochDay());             // 14099
    }

    @Test
    public void getAgeByIdCard() {
        final String idCard = "11010119640101234X";
        String birthDateString = idCard.substring(6, 14);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
        LocalDate now = LocalDate.now();

        long age = Period.between(birthDate, now).getYears();
        pe(age);

        age = ChronoUnit.YEARS.between(birthDate, LocalDate.now());
        if (now.isBefore(birthDate.plusYears(age))) {
            age--;
        }
        pe(age);
    }
}
