package knowledge.api.lang.wrapper;

import l.demo.Demo;
import org.junit.Test;

/**
 * Number
 * 抽象类 Number 是 BigDecimal、BigInteger、Byte、Double、Float、Integer、Long 和 Short 类的超类。
 * https://jdk6.net/lang/Number.html
 * <p>
 * Number 的子类：
 * AtomicInteger, AtomicLong, BigDecimal, BigInteger
 * Byte, Short, Integer, Long, Float, Double
 * <p>
 */
public class NumberDemo extends Demo {

    // ***** 以下为 Number 方法 ****

    /**
     * XXX          xxxValue()          以 XXX 形式返回指定的数值
     */
    @Test
    public void xxxValue() {
        p(new Integer(5).byteValue());  // 5
        p(new Integer(5).doubleValue());// 5.0
        p(new Integer(5).floatValue()); // 5.0
        p(new Integer(5).longValue());  // 5
        p(new Integer(5).shortValue()); // 5
    }

    // ***** 以下为 Number 子类共有常量和方法 ****

    @Test
    public void testMaxAndMin() {
        p(Byte.MAX_VALUE);      // 127                      2^7-1
        p(Double.MAX_VALUE);    // 1.7976931348623157E308   (2-2^-52)*2^1023
        p(Float.MAX_VALUE);     // 3.4028235E38             (2-2^-23)·2^127
        p(Integer.MAX_VALUE);   // 2147483647               2^31-1
        p(Long.MAX_VALUE);      // 9223372036854775807      2^63-1
        p(Short.MAX_VALUE);     // 32767                    2^15-1

        p(Byte.MIN_VALUE);      // -128                     -2^7
        p(Double.MIN_VALUE);    // 4.9E-324                 2^-1074
        p(Float.MIN_VALUE);     // 1.4E-45                  2^-149
        p(Integer.MIN_VALUE);   // -2147483648              -2^31
        p(Long.MIN_VALUE);      // -9223372036854775808     -2^63
        p(Short.MIN_VALUE);     // -32768                   -2^15
    }

    /**
     * static XXX       parseXXX(String s[, int radix])
     * 使用第二个参数指定的基数，将字符串参数解析为有符号的 XXX
     */
    @Test
    public void parseXXX() {
        p(Integer.parseInt("11", 10));  // 11
        p(Integer.parseInt("b", 16));   // 11
    }

    /**
     * static XXX	    valueOf(String s[, int radix])
     * 返回一个 XXX 对象，该对象中保存了用第二个参数提供的基数进行解析时从指定的 String 中提取的值
     */
    @Test
    public void valueOf() {
        p(Integer.valueOf("11", 10));   // 11
        p(Integer.valueOf("b", 16));    // 11
    }

    /**
     * int	            compareTo(xxx anotherXXX)       比较两个 XXX 对象所表示的数值
     */
    @Test
    public void compareTo() {
        Integer x = 5;
        p(x.compareTo(3)); // 1
        p(x.compareTo(5)); // 0
        p(x.compareTo(7)); // -1
    }

    // ***** 以下为局部共有方法 ****

    /**
     * static Byte/Integer/Long/Short       decode(String nm)   将 String 解码为 XXX
     */
    @Test
    public void decode() {
        p(Integer.decode("0x11"));  // 17
        p(Integer.valueOf("0x11")); // NumberFormatException: For input string: "0x11"
    }

    /**
     * static String	    toBinaryString(int/long i)          以二进制（基数 2）无符号整数形式返回一个整数参数的字符串表示形式
     * static String	    toHexString(int/long i)             以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式
     */
    @Test
    public void toXXXString() {
        p(Integer.toBinaryString(11));  // 1011
        p(Integer.toHexString(11));     // b
    }
    
}
