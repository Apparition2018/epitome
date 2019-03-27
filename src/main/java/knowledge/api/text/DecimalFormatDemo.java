package knowledge.api.text;

import org.junit.Test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;

/**
 * DecimalFormat
 * DecimalFormat 是 NumberFormat 的一个具体子类，用于格式化十进制数字。
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
 * int	        getGroupingSize()                               返回分组大小
 * void	        setGroupingSize(int newValue)                   设置分组大小
 * <p>
 * int	        getMultiplier()                                 获取百分数、千分数和类似格式中使用的乘数
 * void	        setMultiplier(int newValue)                     设置百分数、千分数和类似格式中使用的乘数
 * <p>
 * String	    getNegativePrefix()                             获取负数前缀
 * String	    getNegativeSuffix()                             获取负数后缀
 * String	    getPositivePrefix()                             获取正数前缀
 * String	    getPositiveSuffix()                             获取正数后缀
 * <p>
 * void	        setNegativePrefix(String newValue)              设置负数前缀
 * void	        setNegativeSuffix(String newValue)              设置负数后缀
 * void	        setPositivePrefix(String newValue)              设置正数前缀
 * void	        setPositiveSuffix(String newValue)              设置正数后缀
 * <p>
 * boolean	    isDecimalSeparatorAlwaysShown()                 允许获取整数中小数分隔符的行为
 * void	        setDecimalSeparatorAlwaysShown(boolean newValue)允许设置整数中小数分隔符的行为
 * <p>
 * boolean	    isParseBigDecimal()                             返回 parse(java.lang.String, java.text.ParsePosition) 方法是否返回 BigDecimal
 * void	        setParseBigDecimal(boolean newValue)            设置 parse(java.lang.String, java.text.ParsePosition) 方法是否返回 BigDecimal
 * <p>
 * void	        applyLocalizedPattern(String pattern)           将给定的模式应用于此 Format 对象
 * void	        applyPattern(String pattern)                    将给定的模式应用于此 Format 对象
 * <p>
 * String	    toLocalizedPattern()                            合成一个表示此 Format 对象当前状态的、已本地化的模式字符串
 * String	    toPattern()                                     合成一个表示此 Format 对象当前状态的模式字符串
 */
public class DecimalFormatDemo {

    // DecimalFormat([String pattern, DecimalFormatSymbols symbols])
    // 使用给定的模式和符号创建一个 DecimalFormat
    private DecimalFormat df = new DecimalFormat("#,###");

    /**
     * 格式化：
     * <p>
     * StringBuffer	format(double number, StringBuffer result, FieldPosition fieldPosition)
     * 格式化一个 double 值，以生成一个字符串
     * <p>
     * StringBuffer	format(long number, StringBuffer result, FieldPosition fieldPosition)
     * 格式化一个 long 值，以生成一个字符串
     * <p>
     * StringBuffer	format(Object number, StringBuffer toAppendTo, FieldPosition pos)
     * 格式化一个数，并将所得文本追加到给定的字符串缓冲区
     * <p>
     * AttributedCharacterIterator	formatToCharacterIterator(Object obj)
     * 格式化一个 Object，以生成一个 AttributedCharacterIterator
     */
    @Test
    public void format() {
        p(df.format(1000000)); // 1,000,000
    }

    /**
     * 解析：
     * <p>
     * Number	parse(String text, ParsePosition pos)
     * 解析字符串中的文本，以生成一个 Number
     */
    @Test
    public void parse() throws ParseException {
        p(df.parse("1,000,000")); // 1000000
    }


    public static <T> void p(T obj) {
        if (obj == null) return;
        if (obj.getClass().isArray()) System.out.println(Arrays.toString((Object[]) obj));
        System.out.println(obj);
    }

}
