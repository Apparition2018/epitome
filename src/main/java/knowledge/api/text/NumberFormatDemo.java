package knowledge.api.text;

import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

import static l.demo.Demo.p;

/**
 * NumberFormat
 * https://www.runoob.com/manual/jdk1.6/java.base/java/text/NumberFormat.html
 * <p>
 * boolean	        isGroupingUsed()                        如果此格式中使用了分组，则返回 true
 * void	            setGroupingUsed(boolean newValue)       设置此格式中是否使用分组
 * <p>
 * boolean	        isParseIntegerOnly()                    如果此格式只将数作为整数解析，则返回 true
 * void	            setParseIntegerOnly(boolean value)      设置数是否应该仅作为整数进行解析
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class NumberFormatDemo {

    public static final NumberFormat FORMAT;
    public static final NumberFormat INTEGER_FORMAT;
    public static final NumberFormat NUMBER_FORMAT;
    public static final NumberFormat CURRENCY_FORMAT;
    public static final NumberFormat PERCENT_FORMAT;

    static {
        // static NumberFormat	getInstance([Locale inLocale])              返回当前默认语言环境的通用数值格式
        FORMAT = NumberFormat.getInstance();
        // static NumberFormat	getIntegerInstance([Locale inLocale])       返回当前默认语言环境的整数格式
        INTEGER_FORMAT = NumberFormat.getIntegerInstance();
        // static NumberFormat	getNumberInstance([Locale inLocale])        返回指定语言环境的通用数值格式
        NUMBER_FORMAT = NumberFormat.getNumberInstance();
        // static NumberFormat	getCurrencyInstance([Locale inLocale])      返回指定语言环境的货币格式
        CURRENCY_FORMAT = NumberFormat.getCurrencyInstance();
        // static NumberFormat	getPercentInstance([Locale inLocale])       返回指定语言环境的百分比格式
        PERCENT_FORMAT = NumberFormat.getPercentInstance();
    }

    @Test
    public void testNumberFormat() throws ParseException {
        p(FORMAT.format(0.1234));          // 0.123
        p(INTEGER_FORMAT.format(0.1234));  // 0
        p(NUMBER_FORMAT.format(0.1234));   // 0.123
        p(CURRENCY_FORMAT.format(0.1234)); // ￥0.12
        p(PERCENT_FORMAT.format(0.1234));  // 12%

        p(CURRENCY_FORMAT.parse("￥0.12")); // 0.12
        p(PERCENT_FORMAT.parse("12%")); // 0.12
    }

    /**
     * Currency         getCurrency()                       获取格式化货币值时此数值格式使用的货币
     * void	            setCurrency(Currency currency)      设置格式化货币值时此数值格式使用的货币
     */
    @Test
    public void currency() {
        p(CURRENCY_FORMAT.getCurrency()); // CNY

        CURRENCY_FORMAT.setCurrency(Currency.getInstance(Locale.US));
        p(CURRENCY_FORMAT.getCurrency()); // USD

        CURRENCY_FORMAT.setCurrency(Currency.getInstance(Locale.JAPAN));
        p(CURRENCY_FORMAT.getCurrency()); // JPY
    }

    /**
     * int	            getMaximumFractionDigits()          返回数的小数部分所允许的最大位数
     * int	            getMinimumFractionDigits()          返回数的小数部分所允许的最小位数
     */
    @Test
    public void getFractionDigits() {
        p(FORMAT.getMaximumFractionDigits());           // 3
        p(FORMAT.getMinimumFractionDigits());           // 0

        p(INTEGER_FORMAT.getMaximumFractionDigits());   // 0
        p(INTEGER_FORMAT.getMinimumFractionDigits());   // 0

        p(NUMBER_FORMAT.getMaximumFractionDigits());    // 3
        p(NUMBER_FORMAT.getMinimumFractionDigits());    // 0

        p(CURRENCY_FORMAT.getMaximumFractionDigits());  // 2
        p(CURRENCY_FORMAT.getMinimumFractionDigits());  // 2

        p(PERCENT_FORMAT.getMaximumFractionDigits());   // 0
        p(PERCENT_FORMAT.getMinimumFractionDigits());   // 0
    }

    /**
     * boolean	        isGroupingUsed()                    如果此格式中使用了分组，则返回 true
     * boolean	        isParseIntegerOnly()                如果此格式只将数作为整数解析，则返回 true
     */
    @Test
    public void is() {
        p(FORMAT.isGroupingUsed());             // true
        p(FORMAT.isParseIntegerOnly());         // false

        p(INTEGER_FORMAT.isGroupingUsed());     // true
        p(INTEGER_FORMAT.isParseIntegerOnly()); // true

        p(NUMBER_FORMAT.isGroupingUsed());      // true
        p(NUMBER_FORMAT.isParseIntegerOnly());  // false

        p(CURRENCY_FORMAT.isGroupingUsed());    // true
        p(CURRENCY_FORMAT.isParseIntegerOnly());// false

        p(PERCENT_FORMAT.isGroupingUsed());     // true
        p(PERCENT_FORMAT.isParseIntegerOnly()); // false
    }
}

