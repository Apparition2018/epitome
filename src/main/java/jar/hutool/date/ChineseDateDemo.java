package jar.hutool.date;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateUtil;

import static l.demo.Demo.p;

/**
 * <a href="https://doc.hutool.cn/pages/ChineseDate/">ChineseDate</a>   农历日期
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/date/ChineseDate.html">ChineseDate api</a>
 *
 * @author ljh
 * @since 2020/10/27 11:29
 */
public class ChineseDateDemo {

    public static void main(String[] args) {
        ChineseDate chineseDate;

        chineseDate = new ChineseDate(DateUtil.parse("2008-01-01"));
        p(chineseDate);                         // 丁亥猪年 十一月廿三

        chineseDate = new ChineseDate(2008, 1, 1);
        p(chineseDate);                         // 戊子鼠年 正月初一
        p(chineseDate.toStringNormal());        // 2008-01-01

        p(chineseDate.getChineseMonth());       // 一月
        p(chineseDate.getChineseMonthName());   // 正月
        p(chineseDate.getChineseDay());         // 初一
        p(chineseDate.getCyclical());           // 戊子
        p(chineseDate.getChineseZodiac());      // 鼠
        p(chineseDate.getFestivals());          // 春节

        p(chineseDate.getChineseYear());        // 2008
        p(chineseDate.getMonth());              // 1
        p(chineseDate.getDay());                // 1

        // 天干地支
        p(chineseDate.getCyclicalYMD());        // 戊子年甲寅月丁丑日

        // 闰月
        p(chineseDate.isLeapMonth());           // false
    }
}
