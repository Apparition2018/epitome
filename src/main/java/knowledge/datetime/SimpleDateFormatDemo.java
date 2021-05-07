package knowledge.datetime;

import l.demo.Demo;

import java.util.Date;

/**
 * SimpleDateFormat
 * SimpleDateFormat的线程安全问题与解决方案：https://www.cnblogs.com/zemliu/p/3290585.html   
 * https://www.runoob.com/manual/jdk1.6/java.base/java/text/SimpleDateFormat.html
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
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class SimpleDateFormatDemo extends Demo {

    public static void main(String[] args) {
        // void	        applyPattern(String pattern)        将给定模式字符串应用于此日期格式
        SDF.applyPattern("yyyy-MM-dd HH:mm:ss");
        p(SDF.format(new Date()));  // 2020-09-03 11:29:36

        // String	    toPattern()                         返回描述此日期格式的模式字符串
        p(SDF.toPattern());         // yyyy-MM-dd HH:mm:ss

        // String	    toLocalizedPattern()                返回描述此日期格式的本地化模式字符串
        p(SDF.toLocalizedPattern());// aaaa-nn-jj HH:mm:ss
    }
}
