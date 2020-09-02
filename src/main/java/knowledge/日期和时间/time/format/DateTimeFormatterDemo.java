package knowledge.日期和时间.time.format;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * DateTimeFormatter
 * <p>
 * Symbol  Meaning                     Presentation      Examples
 * ------  -------                     ------------      -------
 * G       era                         text              AD; Anno Domini; A
 * u       year                        year              2004; 04
 * y       year-of-era                 year              2004; 04
 * D       day-of-year                 number            189
 * M/L     month-of-year               number/text       7; 07; Jul; July; J
 * d       day-of-month                number            10
 * <p>
 * Q/q     quarter-of-year             number/text       3; 03; Q3; 3rd quarter
 * Y       week-based-year             year              1996; 96
 * w       week-of-week-based-year     number            27
 * W       week-of-month               number            4
 * E       day-of-week                 text              Tue; Tuesday; T
 * e/c     localized day-of-week       number/text       2; 02; Tue; Tuesday; T
 * F       week-of-month               number            3
 * <p>
 * a       am-pm-of-day                text              PM
 * h       clock-hour-of-am-pm (1-12)  number            12
 * K       hour-of-am-pm (0-11)        number            0
 * k       clock-hour-of-am-pm (1-24)  number            0
 * <p>
 * H       hour-of-day (0-23)          number            0
 * m       minute-of-hour              number            30
 * s       second-of-minute            number            55
 * S       fraction-of-second          fraction          978
 * A       milli-of-day                number            1234
 * n       nano-of-second              number            987654321
 * N       nano-of-day                 number            1234000000
 * <p>
 * V       time-zone ID                zone-id           America/Los_Angeles; Z; -08:30
 * z       time-zone name              zone-name         Pacific Standard Time; PST
 * O       localized zone-offset       offset-O          GMT+8; GMT+08:00; UTC-08:00;
 * X       zone-offset 'Z' for zero    offset-X          Z; -08; -0830; -08:30; -083015; -08:30:15;
 * x       zone-offset                 offset-x          +0000; -08; -0830; -08:30; -083015; -08:30:15;
 * Z       zone-offset                 offset-Z          +0000; -0800; -08:00;
 * <p>
 * p       pad next                    pad modifier      1
 * <p>
 * '       escape for text             delimiter
 * ''      single quote                literal           '
 * [       optional section start
 * ]       optional section end
 * #       reserved for future use
 * {       reserved for future use
 * }       reserved for future use
 * <p>
 * Formatter	                Example
 * BASIC_ISO_DATE	            '20111203'
 * ISO_LOCAL_DATE	            '2011-12-03'
 * ISO_OFFSET_DATE	            '2011-12-03+01:00'
 * ISO_DATE	                    '2011-12-03+01:00'; '2011-12-03'
 * ISO_LOCAL_TIME	            '10:15:30'
 * ISO_OFFSET_TIME	            '10:15:30+01:00'
 * ISO_TIME	                    '10:15:30+01:00'; '10:15:30'
 * ISO_LOCAL_DATE_TIME	        '2011-12-03T10:15:30'
 * ISO_OFFSET_DATE_TIME	        '2011-12-03T10:15:30+01:00'
 * ISO_ZONED_DATE_TIME	        '2011-12-03T10:15:30+01:00[Europe/Paris]'
 * ISO_DATE_TIME	            '2011-12-03T10:15:30+01:00[Europe/Paris]'
 * ISO_ORDINAL_DATE	            '2012-337'
 * ISO_WEEK_DATE	            '2012-W48-6'
 * ISO_INSTANT	                '2011-12-03T10:15:30Z'
 * RFC_1123_DATE_TIME	        'Tue, 3 Jun 2008 11:05:30 GMT'
 * <p>
 * https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
 */
public class DateTimeFormatterDemo {

    @Test
    public void test() {
        DateTimeFormatter[] formatters = new DateTimeFormatter[]{
                // 直接使用常量创建DateTimeFormatter格式器
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ISO_LOCAL_TIME,
                DateTimeFormatter.ISO_LOCAL_DATE_TIME,
                // 使用本地化的不同风格来创建DateTimeFormatter格式器
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM),
                DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG),
                // 根据模式字符串来创建DateTimeFormatter格式器
                DateTimeFormatter.ofPattern("Gyyyy%%MMM%%dd HH:mm:ss")
        };
        LocalDateTime date = LocalDateTime.now();
        for (DateTimeFormatter formatter : formatters) {
            System.out.println(formatter.format(date));
        }
    }

    /**
     * 各种格式器
     *
     * static DateTimeFormatter	    ofLocalizedDate(FormatStyle dateStyle)
     * static DateTimeFormatter	    ofLocalizedTime(FormatStyle timeStyle)
     * static DateTimeFormatter	    ofLocalizedDateTime(FormatStyle dateTimeStyle)
     * static DateTimeFormatter	    ofLocalizedDateTime(FormatStyle dateStyle, FormatStyle timeStyle)
     * static DateTimeFormatter	    ofPattern(String pattern[, Locale locale])
     */
    @Test
    public void dateTimeFormatter() {
        DateTimeFormatter[] formatters = new DateTimeFormatter[]{
                // 常量
                DateTimeFormatter.ISO_LOCAL_DATE,                                               // 2019-01-04
                DateTimeFormatter.ISO_LOCAL_TIME,                                               // 08:54:03.457
                DateTimeFormatter.ISO_LOCAL_DATE_TIME,                                          // 2019-01-04T08:54:03.457
                // ISO chronology
                DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL),                            // 2019年1月4日 星期五
                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG),                            // 2019年1月4日
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM),                          // 2019-1-4
                DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT),                           // 19-1-4
                DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG),                            // 上午08时54分03秒
                DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM),                          // 8:54:03
                DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT),                           // 上午8:54
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.LONG),      // 2019年1月4日 星期五 上午08时54分03秒
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT),   // 2019-1-4 上午8:54
                // 指定模式
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")                              // 2019-01-04 08:54:03
        };

        LocalDateTime ldt = LocalDateTime.now();

        for (DateTimeFormatter formatter : formatters) {
            // String       format(TemporalAccessor temporal)
            // 使用此格式化程序格式化日期时间对象
            p(formatter.format(ldt));
        }

    }


    private static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}
