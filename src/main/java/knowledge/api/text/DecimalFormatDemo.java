package knowledge.api.text;

import org.junit.Test;

import java.text.DecimalFormat;
import java.text.ParseException;

import static l.demo.Demo.p;

/**
 * DecimalFormat
 * DecimalFormat 是 NumberFormat 的一个具体子类，用于格式化十进制数字。
 * https://www.runoob.com/manual/jdk1.6/java/text/DecimalFormat.html
 * <p>
 * 特殊模式字符：
 * 符号	        位置	        本地化？	    含义
 * 0	        数字          是       阿拉伯数字
 * #	        数字          是       阿拉伯数字，如果不存在则显示为 0
 * .	        数字          是       小数分隔符或货币小数分隔符
 * -	        数字          是       减号
 * ,	        数字          是       分组分隔符
 * E	        数字          是       分隔科学计数法中的尾数和指数。在前缀或后缀中无需加引号。
 * ;	        子模式边界     是	       分隔正数和负数子模式
 * %	        前缀或后缀     是	       乘以 100 并显示为百分数
 * \u2030	    前缀或后缀     是	       乘以 1000 并显示为千分数
 * ¤ (\u00A4)	前缀或后缀     否	       货币记号，由货币符号替换。如果两个同时出现，则用国际货币符号替换。如果出现在某个模式中，则使用货币小数分隔符，而不使用小数分隔符。
 * '	        前缀或后缀     否	       用于在前缀或或后缀中为特殊字符加引号，例如 "'#'#" 将 123 格式化为 "#123"。要创建单引号本身，请连续使用两个单引号："# o''clock"。
 * <p>
 * void	        setRoundingMode(RoundingMode roundingMode)      设置在此 DecimalFormat 中使用的 RoundingMode
 * <p>
 * boolean	    isParseBigDecimal()                             返回 parse(java.lang.String, java.text.ParsePosition) 方法是否返回 BigDecimal
 * void	        setParseBigDecimal(boolean newValue)            设置 parse(java.lang.String, java.text.ParsePosition) 方法是否返回 BigDecimal
 * <p>
 * String	    toLocalizedPattern()                            合成一个表示此 Format 对象当前状态的、已本地化的模式字符串
 * String	    toPattern()                                     合成一个表示此 Format 对象当前状态的模式字符串
 *
 * @author ljh
 * created on 2020/11/23 0:45
 */
public class DecimalFormatDemo {

    public static final DecimalFormat FORMAT1;
    public static final DecimalFormat FORMAT2;
    public static final DecimalFormat FORMAT3;

    static {
        // DecimalFormat([String pattern[, DecimalFormatSymbols symbols]])
        // 使用给定的模式和符号创建一个 DecimalFormat
        FORMAT1 = new DecimalFormat("00.000");
        FORMAT2 = new DecimalFormat("#0.000");
        FORMAT3 = new DecimalFormat("0.###");
        // void	        applyPattern(String pattern)        将给定的模式应用于此 Format 对象
        FORMAT3.applyPattern("0.###");
    }

    @Test
    public void testDecimalFormat() throws ParseException {
        p(FORMAT1.format(0.12));    // 00.120
        p(FORMAT2.format(0.12));    // 0.120
        p(FORMAT3.format(0.12));    // 0.12

        // Currency	    getCurrency()                       获取格式化货币值时，此十进制格式使用的货币
        p(FORMAT1.getCurrency());   // CNY

        p(FORMAT1.parse("00.120")); // 0.12
        p(FORMAT2.parse("00.120")); // 0.12
        p(FORMAT3.parse("00.120")); // 0.12

        // String	    toPattern()                         合成一个表示此 Format 对象当前状态的模式字符串
        p(FORMAT1.toPattern());     // #00.000
        p(FORMAT2.toPattern());     // #0.000
        p(FORMAT3.toPattern());     // #0.###
    }

    /**
     * int	            getMaximumFractionDigits()          返回数的小数部分所允许的最大位数
     * int	            getMinimumFractionDigits()          返回数的小数部分所允许的最小位数
     */
    @Test
    public void getFractionDigits() {
        p(FORMAT1.getMaximumFractionDigits()); // 3
        p(FORMAT1.getMinimumFractionDigits()); // 3

        p(FORMAT2.getMaximumFractionDigits()); // 3
        p(FORMAT2.getMinimumFractionDigits()); // 3

        p(FORMAT3.getMaximumFractionDigits()); // 3
        p(FORMAT3.getMinimumFractionDigits()); // 0
    }

}