package jar.hutool.date;

import cn.hutool.core.date.LocalDateTimeUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * LocalDateTimeUtil
 * https://hutool.cn/docs/#/core/%E6%97%A5%E6%9C%9F%E6%97%B6%E9%97%B4/LocalDateTime%E5%B7%A5%E5%85%B7-LocalDateTimeUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/date/LocalDateTimeUtil.html
 *
 * @author ljh
 * created on 2020/10/27 11:45
 */
public class LocalDateTimeUtilDemo extends Demo {

    private final Date DATE = DATE_TIME_SDF.parse("2008-08-08 20:08:08");
    private final LocalDateTime NOW_LDT = LocalDateTime.now();

    public LocalDateTimeUtilDemo() throws ParseException {
    }

    @Test
    public void testLocalDateTimeUtil() {
        LocalDateTime localDateTime;
        LocalDate localDate;
        String ldtStr;

        //********** → localDateTime  **********//
        // 1.ofUTC(Instant/long)
        localDateTime = LocalDateTimeUtil.ofUTC(DATE.getTime());
        p(localDateTime);                                                       // 2008-08-08T12:08:08
        // 2.of()
        // Date | Instant[, ZoneId/TimeZone] | long[, ZoneId/TimeZone] | ZonedDateTime | TemporalAccessor
        localDateTime = LocalDateTimeUtil.of(DATE);
        p(localDateTime);                                                       // 2008-08-08T20:08:08
        // 3.parse(CharSequence[, DateTimeFormatter/formatStr])
        localDateTime = LocalDateTimeUtil.parse("2008-08-08T20:08:08");

        //********** → LocalDate  **********//
        // 1.ofDate(TemporalAccessor)
        localDate = LocalDateTimeUtil.ofDate(localDateTime);
        p(localDate);                                                           // 2008-08-08
        // 2.parseDate(CharSequence[, DateTimeFormatter/formatStr])
        localDate = LocalDateTimeUtil.parseDate("2008-08-08");
        p(localDate);                                                           // 2008-08-08

        //********** → String  **********//
        // format(LocalDateTime/LocalDate, formatStr/DateTimeFormatter)
        ldtStr = LocalDateTimeUtil.format(localDateTime, "yyyy/MM/dd HH:mm:ss");
        p(ldtStr);                                                              // 2008/08/08 20:08:08
        // formatFormat(LocalDateTime/LocalDate)
        ldtStr= LocalDateTimeUtil.formatNormal(localDateTime);
        p(ldtStr);                                                              // 2008-08-08 20:08:08

        //********** 一天的开始和结束 **********//
        p(LocalDateTimeUtil.beginOfDay(localDateTime));                         // 2008-08-08T00:00
        p(LocalDateTimeUtil.endOfDay(localDateTime));                           // 2008-08-08T23:59:59.999999999

        //********** 偏移 **********//
        // 返回新对象
        p(LocalDateTimeUtil.offset(localDateTime, 1, ChronoUnit.YEARS));        // 2009-08-08T20:08:08
        p(LocalDateTimeUtil.offset(localDateTime, -12, ChronoUnit.MONTHS));     // 2007-08-08T20:08:08

        //********** 时间差 **********//
        p(LocalDateTimeUtil.between(localDateTime, NOW_LDT));                   // PT107104H34M3.533S
        p(LocalDateTimeUtil.between(localDateTime, NOW_LDT, ChronoUnit.DAYS));  // 4462
        p(LocalDateTimeUtil.betweenPeriod(localDate,
                LocalDateTimeUtil.ofDate(NOW_LDT)));                            // P12Y2M19D
    }

}
