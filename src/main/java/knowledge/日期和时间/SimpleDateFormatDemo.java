package knowledge.日期和时间;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleDateFormatDemo
 * <p>
 * 字母   日期或时间元素             表示          示例
 * G        Era 标志符             Text          AD
 * y        年                    Year          1996; 96
 * M        年中的月份             Month        July; Jul; 07
 * w        年中的周数             Number        27
 * W        月份中的周数           Number        2
 * D        年中的天数             Number        189
 * d        月份中的天数           Number        10
 * F        月份中的星期           Number        2
 * E        星期中的天数           Text          Tuesday; Tue
 * a        AM/PM                 Text          PM
 * H        一天中的小时数(0-23)    Number        0
 * k        一天中的小时数(1-24)    Number        24
 * K        AM/PM 中的小时数(0-11) Number         0
 * h        AM/PM 中的小时数(1-12) Number        12
 * m        小时中的分钟数          Number        30
 * s        分钟中的秒数            Number        55
 * S        毫秒数                 Number        978
 * z        时区             General time zone  Pacific Standard Time; PST; GMT-08:00
 * Z        时区             RFC 822 time zone  -0800
 */
public class SimpleDateFormatDemo {

    /**
     * String	format(Date date)
     * 将一个 Date 格式化为日期/时间字符串
     * <p>
     * 从类 java.text.DateFormat 继承的方法
     */
    @Test
    public void format() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(date)); // 2018-10-26
    }

    /**
     * Date parse(String text, ParsePosition pos)
     * 解析字符串的文本，生成 Date
     */
    @Test
    public void parse() {
        String s = "2008-08-08";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println(sdf.parse(s)); // Fri Aug 08 00:00:00 CST 2008
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
