package knowledge.api.text;

import l.demo.Demo;
import org.junit.Test;

import java.text.ChoiceFormat;
import java.text.ParsePosition;

/**
 * ChoiceFormatDemo
 * ChoiceFormat 允许将格式应用到某个范围的数。它通常用于在 MessageFormat 中处理复数。使用按升序排列的 double 列表指定 choice，列表中每一项都指定一个到下一项的半开区间：
 * 当且仅当 limit[j] <= X < limit[j+1] 时，X 匹配 j。
 * 如果不匹配，则根据数 (X) 的是太小还是太大，选择使用第一个或最后一个索引。
 * https://jdk6.net/text/ChoiceFormat.html
 *
 * @author ljh
 * created on 2020/9/3 16:22
 */
public class ChoiceFormatDemo extends Demo {

    @Test
    public void testChoiceFormat() {
        double[] limits = {1, 2, 3, 4, 5, 6, 7};
        String[] dayOfWeekNames = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
        ChoiceFormat form = new ChoiceFormat(limits, dayOfWeekNames);
        ParsePosition status = new ParsePosition(0);
        for (double i = 0.0; i <= 8.0; ++i) {
            status.setIndex(0);
            p(i + " -> " + form.format(i) + " -> " + form.parse(form.format(i), status));
            // 0.0 -> Sun -> 1.0
            // 1.0 -> Sun -> 1.0
            // 2.0 -> Mon -> 2.0
            // 3.0 -> Tue -> 3.0
            // 4.0 -> Wed -> 4.0
            // 5.0 -> Thur -> 5.0
            // 6.0 -> Fri -> 6.0
            // 7.0 -> Sat -> 7.0
            // 8.0 -> Sat -> 7.0
        }
    }

    @Test
    public void testChoiceFormat2() {
        ChoiceFormat fmt = new ChoiceFormat("-1#is negative | 0#is zero or fraction | 1#is one | 1.0<is 1+ | 2#is two | 2<is more than 2.");
        p(fmt.format(Double.NEGATIVE_INFINITY));    // is negative 
        p(fmt.format(-1.0));                        // is negative 
        p(fmt.format(0));                           // is zero or fraction 
        p(fmt.format(0.9));                         // is zero or fraction 
        p(fmt.format(1));                           // is one 
        p(fmt.format(1.5));                         // is 1+ 
        p(fmt.format(2));                           // is two 
        p(fmt.format(2.1));                         // is more than 2.
        p(fmt.format(Double.NaN));                  // is negative 
        p(fmt.format(Double.POSITIVE_INFINITY));    // is more than 2.
    }
}
