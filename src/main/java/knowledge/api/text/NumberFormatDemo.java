package knowledge.api.text;

import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

import static l.demo.Demo.p;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/text/NumberFormat.html">NumberFormat</a>
 * <pre>
 * boolean  isGroupingUsed()                    如果此格式中使用了分组，则返回 true
 * void     setGroupingUsed(boolean newValue)   设置此格式中是否使用分组
 *
 * boolean  isParseIntegerOnly()                如果此格式只将数作为整数解析，则返回 true
 * void     setParseIntegerOnly(boolean value)  设置数是否应该仅作为整数进行解析
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class NumberFormatDemo {

    // static NumberFormat	getInstance([Locale inLocale])              返回当前默认语言环境的通用数值格式
    private static final NumberFormat FORMAT = NumberFormat.getInstance();
    // static NumberFormat	getIntegerInstance([Locale inLocale])       返回当前默认语言环境的整数格式
    private static final NumberFormat INTEGER_FORMAT = NumberFormat.getIntegerInstance();
    // static NumberFormat	getNumberInstance([Locale inLocale])        返回指定语言环境的通用数值格式
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance();
    // static NumberFormat  getCompactNumberInstance([Locale locale, NumberFormat.Style formatStyle])
    // 返回指定语言环境和 formatStyle 的压缩数值格式，JDK12 引入
    private static final NumberFormat COMPACT_NUMBER_FORMAT = NumberFormat.getCompactNumberInstance(Locale.CHINA, NumberFormat.Style.SHORT);
    // static NumberFormat	getCurrencyInstance([Locale inLocale])      返回指定语言环境的货币格式
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance();
    // static NumberFormat	getPercentInstance([Locale inLocale])       返回指定语言环境的百分比格式
    private static final NumberFormat PERCENT_FORMAT = NumberFormat.getPercentInstance();

    @Test
    public void format() throws ParseException {
        double d = 12345.4321;

        this.digits();
        p(FORMAT.format(d));                    // 12,345.432
        p(INTEGER_FORMAT.format(d));            // 12,345
        p(NUMBER_FORMAT.format(d));             // 12,345.432
        p(COMPACT_NUMBER_FORMAT.format(d));     // 1万
        p(CURRENCY_FORMAT.format(d));           // ¥12,345.43
        p(PERCENT_FORMAT.format(d));            // 1,234,543.21%

        p(CURRENCY_FORMAT.parse("¥12,345.43")); // 12345.43
        p(PERCENT_FORMAT.parse("1,234,543%"));  // 12345.43
    }

    /**
     * <pre>
     * void     setMaximumIntegerDigits(int newValue)   设置数的整数部分所允许的最大位数
     * void     setMinimumIntegerDigits(int newValue)   设置数的整数部分所允许的最小位数
     * int      getMaximumIntegerDigits()               返回数的整数部分所允许的最大位数
     * int      getMinimumIntegerDigits()               返回数的整数部分所允许的最小位数
     * void     setMaximumFractionDigits(int newValue)  设置数的小数部分所允许的最大位数
     * void     setMinimumFractionDigits(int newValue)  设置数的小数部分所允许的最小位数
     * int      getMaximumFractionDigits()              返回数的小数部分所允许的最大位数
     * int      getMinimumFractionDigits()              返回数的小数部分所允许的最小位数
     * </pre>
     */
    public void digits() {
        p(FORMAT.getMaximumIntegerDigits());            // 2147483647
        p(FORMAT.getMinimumIntegerDigits());            // 1
        p(FORMAT.getMaximumFractionDigits());           // 3
        p(FORMAT.getMinimumFractionDigits());           // 0

        PERCENT_FORMAT.setMinimumFractionDigits(2);
        p(PERCENT_FORMAT.getMaximumIntegerDigits());    // 2147483647
        p(PERCENT_FORMAT.getMinimumIntegerDigits());    // 1
        p(PERCENT_FORMAT.getMaximumFractionDigits());   // 2
        p(PERCENT_FORMAT.getMinimumFractionDigits());   // 2
    }

    /**
     * <pre>
     * Currency getCurrency()                   获取格式化货币值时此数值格式使用的货币
     * void     setCurrency(Currency currency)  设置格式化货币值时此数值格式使用的货币
     * </pre>
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
     * <pre>
     * boolean  isGroupingUsed()                如果此格式中使用了分组，则返回 true
     * boolean  isParseIntegerOnly()            如果此格式只将数作为整数解析，则返回 true
     * </pre>
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

