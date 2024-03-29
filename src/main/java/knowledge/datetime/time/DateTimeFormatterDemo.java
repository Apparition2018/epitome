package knowledge.datetime.time;

import cn.hutool.core.date.DatePattern;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html">DateTimeFormatter</a>
 * <pre>
 * Symbol  Meaning                     Presentation      Examples
 * ------  -------                     ------------      -------
 * G       era                         text              AD; Anno Domini; A
 * u       year                        year              2004; 04
 * y       year-of-era                 year              2004; 04
 * D       day-of-year                 number            189
 * M/L     month-of-year               number/text       7; 07; Jul; July; J
 * d       day-of-month                number            10
 *
 * Q/q     quarter-of-year             number/text       3; 03; Q3; 3rd quarter
 * Y       week-based-year             year              1996; 96
 * w       week-of-week-based-year     number            27
 * W       week-of-month               number            4
 * E       day-of-week                 text              Tue; Tuesday; T
 * e/c     localized day-of-week       number/text       2; 02; Tue; Tuesday; T
 * F       week-of-month               number            3
 *
 * a       am-pm-of-day                text              PM
 * h       clock-hour-of-am-pm (1-12)  number            12
 * K       hour-of-am-pm (0-11)        number            0
 * k       clock-hour-of-am-pm (1-24)  number            0
 *
 * H       hour-of-day (0-23)          number            0
 * m       minute-of-hour              number            30
 * s       second-of-minute            number            55
 * S       fraction-of-second          fraction          978
 * A       milli-of-day                number            1234
 * n       nano-of-second              number            987654321
 * N       nano-of-day                 number            1234000000
 *
 * V       time-zone ID                zone-id           America/Los_Angeles; Z; -08:30
 * z       time-zone name              zone-name         Pacific Standard Time; PST
 * O       localized zone-offset       offset-O          GMT+8; GMT+08:00; UTC-08:00;
 * X       zone-offset 'Z' for zero    offset-X          Z; -08; -0830; -08:30; -083015; -08:30:15;
 * x       zone-offset                 offset-x          +0000; -08; -0830; -08:30; -083015; -08:30:15;
 * Z       zone-offset                 offset-Z          +0000; -0800; -08:00;
 *
 * p       pad next                    pad modifier      1
 *
 * '       escape for text             delimiter
 * ''      single quote                literal           '
 * [       optional section start
 * ]       optional section end
 * #       reserved for future use
 * {       reserved for future use
 * }       reserved for future use
 *
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
 * </pre>
 *
 * @author ljh
 * @since 2020/9/26 2:51
 */
public class DateTimeFormatterDemo {

    /**
     * 各种格式器
     * <pre>
     * static DateTimeFormatter	    ofLocalizedDate(FormatStyle dateStyle)
     * static DateTimeFormatter	    ofLocalizedTime(FormatStyle timeStyle)
     * static DateTimeFormatter	    ofLocalizedDateTime(FormatStyle dateTimeStyle[, FormatStyle timeStyle])
     * static DateTimeFormatter	    ofPattern(String pattern[, Locale locale])
     * </pre>
     */
    public static void main(String[] args) {
        DateTimeFormatter[] formatters = new DateTimeFormatter[]{
                // 常量
                DateTimeFormatter.BASIC_ISO_DATE,                                               // 20080808
                DateTimeFormatter.ISO_LOCAL_DATE,                                               // 2008-08-08
                DateTimeFormatter.ISO_LOCAL_TIME,                                               // 20:08:08
                DateTimeFormatter.ISO_LOCAL_DATE_TIME,                                          // 2008-08-08T20:08:08
                DateTimeFormatter.ISO_OFFSET_DATE_TIME,                                         // 2008-08-08T20:08:08+08:00
                DateTimeFormatter.ISO_ZONED_DATE_TIME,                                          // 2008-08-08T20:08:08+08:00
                DateTimeFormatter.ISO_INSTANT,                                                  // 2008-08-08T12:08:08Z
                // ISO chronology
                DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL),                            // 2008年8月8日 星期五
                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG),                            // 2008年8月8日
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM),                          // 2008-8-8
                DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT),                           // 08-8-8
                DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG),                            // 下午08时08分08秒
                DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM),                          // 20:08:08
                DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT),                           // 下午8:08
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.LONG),      // 2008年8月8日 星期五 下午08时08分08秒
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT),   // 2008-8-8 下午8:08
                // 指定模式
                DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)                  // 2008-08-08 20:08:08
        };
        OffsetDateTime odt = OffsetDateTime.parse("2008-08-08T20:08:08+08:00");
        for (DateTimeFormatter formatter : formatters) {
            System.out.println(formatter.format(odt));
        }
    }
}
