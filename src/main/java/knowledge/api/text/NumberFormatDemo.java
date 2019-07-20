package knowledge.api.text;

import org.junit.Test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

/**
 * NumberFormat
 * NumberFormat 是所有数值格式的抽象基类
 * <p>
 * static Locale[]	getAvailableLocales()                   返回一个数组，它包含所有此类的 get*Instance 方法可以为其返回本地化实例的语言环境
 * <p>
 * int	            getMaximumFractionDigits()              返回数的小数部分所允许的最大位数
 * int	            getMinimumFractionDigits()              返回数的小数部分所允许的最小位数
 * int	            getMaximumIntegerDigits()               返回数的整数部分所允许的最大位数
 * int	            getMinimumIntegerDigits()               返回数的整数部分所允许的最小位数
 * <p>
 * void	            setMaximumFractionDigits(int newValue)  设置数的小数部分所允许的最大位数
 * void	            setMinimumFractionDigits(int newValue)  设置数的小数部分所允许的最小位数
 * void	            setMaximumIntegerDigits(int newValue)   设置数的整数部分所允许的最大位数
 * void	            setMinimumIntegerDigits(int newValue)   设置数的整数部分所允许的最小位数
 * <p>
 * RoundingMode	    getRoundingMode()                       获取在此 NumberFormat 中使用的 RoundingMode
 * void	            setRoundingMode(RoundingMode roundingMode)设置在此 NumberFormat 中使用的 RoundingMode
 * <p>
 * boolean	        isGroupingUsed()                        如果此格式中使用了分组，则返回 true
 * void	            setGroupingUsed(boolean newValue)       设置此格式中是否使用分组
 * <p>
 * boolean	        isParseIntegerOnly()                    如果此格式只将数作为整数解析，则返回 true
 * void	            setParseIntegerOnly(boolean value)      设置数是否应该仅作为整数进行解析
 */
public class NumberFormatDemo {

    // 返回指定语言环境的通用数值格式
    private NumberFormat nf1 = NumberFormat.getInstance();
    // 返回指定语言环境的通用数值格式
    private NumberFormat nf2 = NumberFormat.getNumberInstance();
    // 返回指定语言环境的货币格式
    private NumberFormat nf3 = NumberFormat.getCurrencyInstance();
    // 返回指定语言环境的百分比格式
    private NumberFormat nf4 = NumberFormat.getPercentInstance();

    /**
     * 格式化
     */
    @Test
    public void format() {
        p(nf1.format(200)); // 200
        p(nf2.format(200)); // 200
        p(nf3.format(200)); // ￥200.00
        p(nf4.format(200)); // 20,000%
    }

    /**
     * 解析
     */
    @Test
    public void parse() throws ParseException {
        p(nf3.parse("￥200")); // 200
        p(nf4.parse("20,000%")); // 200
    }

    /**
     * Currency getCurrency()                   获取格式化货币值时此数值格式使用的货币
     * void	    setCurrency(Currency currency)  设置格式化货币值时此数值格式使用的货币
     */
    @Test
    public void currency() {
        p(nf1.getCurrency()); // CNY

        nf1 = NumberFormat.getInstance(Locale.US);
        p(nf1.getCurrency()); // USD

        nf1.setCurrency(Currency.getInstance(Locale.JAPAN));
        p(nf1.getCurrency()); // JPY
    }

    /**
     * int	getMaximumFractionDigits()  返回数的小数部分所允许的最大位数
     * int	getMinimumFractionDigits()  返回数的小数部分所允许的最小位数
     */
    @Test
    public void getFractionDigits() {
        p(nf1.getMaximumFractionDigits()); // 3
        p(nf1.getMinimumFractionDigits()); // 0
    }

    public static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }


}

