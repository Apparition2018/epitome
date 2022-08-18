package knowledge.types.wrapper;

import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * Number
 * Number 是 AtomicInteger, AtomicLong, BigDecimal, BigInteger, Byte, Double, Float, Integer, Long 和 Short 类的超类。
 * https://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/Number.html
 * <p>
 * 阿里编程规约：
 * 1.任何货币金额，均以最小货币单位且整型类型来进行存储
 * 2.浮点数之间的等值判断，基本数据类型不能用==来比较，包装数据类型不能用 equals 来判断
 * -  2.1 指定一个误差范围，两个浮点数的差值在此范围之内，则认为是相等的
 * -  2.2 使用 BigDecimal 来定义值，再进行浮点数的运算操作
 * 3.所有的 POJO 类属性必须使用包装数据类型
 * 4.RPC 方法的返回值和参数必须使用包装数据类型
 * 5.所有的局部变量使用基本数据类型
 *
 * @author ljh
 * created on 2020/9/7 1:28
 */
public class NumberDemo {

    /**
     * Number 方法
     */
    @Test
    public void testNumberMethods() {
        // XXX          xxxValue()          以 XXX 形式返回指定的数值
        p(new Integer(5).intValue());   // 5
        p(new Integer(5).byteValue());  // 5
        p(new Integer(5).doubleValue());// 5.0
        p(new Integer(5).floatValue()); // 5.0
        p(new Integer(5).longValue());  // 5
        p(new Integer(5).shortValue()); // 5
    }

    /**
     * Number 子类共有常量和方法
     */
    @Test
    public void testSubClassConstantsAndMethods() {
        // 常量
        p(Byte.MAX_VALUE);              // 127                      2^7-1
        p(Double.MAX_VALUE);            // 1.7976931348623157E308   (2-2^-52)*2^1023
        p(Float.MAX_VALUE);             // 3.4028235E38             (2-2^-23)·2^127
        p(Integer.MAX_VALUE);           // 2147483647               2^31-1
        p(Long.MAX_VALUE);              // 9223372036854775807      2^63-1
        p(Short.MAX_VALUE);             // 32767                    2^15-1

        p(Byte.MIN_VALUE);              // -128                     -2^7
        p(Double.MIN_VALUE);            // 4.9E-324                 2^-1074
        p(Float.MIN_VALUE);             // 1.4E-45                  2^-149
        p(Integer.MIN_VALUE);           // -2147483648              -2^31
        p(Long.MIN_VALUE);              // -9223372036854775808     -2^63
        p(Short.MIN_VALUE);             // -32768                   -2^15

        p(Double.POSITIVE_INFINITY);    // Infinity
        p(Float.NEGATIVE_INFINITY);     // -Infinity
        p(Double.NaN);                  // NaN


        // static XXX           parseXXX(String s[, int radix])
        // 使用第二个参数指定的基数，将字符串参数解析为有符号的 XXX
        p(Integer.parseInt("11", 10));  // 11
        p(Integer.parseInt("b", 16));   // 11

        // static XXX	        valueOf(String s[, int radix])
        // 返回一个 XXX 对象，该对象中保存了用第二个参数提供的基数进行解析时从指定的 String 中提取的值
        p(Integer.valueOf("11", 10));   // 11
        p(Integer.valueOf("b", 16));    // 11
        // 返回 Boolean 中已定义的两个静态属性 new Boolean(false) 和 new Boolean(true)
        Boolean x1 = false;
        Boolean x2 = false;
        System.out.println(x1 == x2);   // true

        // int	                compareTo(xxx anotherXXX)
        // 比较两个 XXX 对象所表示的数值
        Integer x = 5;
        p(x.compareTo(3));              // 1
        p(x.compareTo(5));              // 0
        p(x.compareTo(7));              // -1
    }

    /**
     * 某些 Number 子类共有方法
     */
    @Test
    public void testSomeSubClassMethods() {
        p("=== Byte/Integer/Long/Short  ===");
        // static XXX           decode(String nm)
        // 将 String 解码为 XXX
        p(Integer.decode("0x11"));      // 17
        // p(Integer.valueOf("0x11"));  // NumberFormatException: For input string: "0x11"


        p("\n=== Integer/Long ===");
        // static String	    toBinaryString(int/long i)
        // 以二进制（基数 2）无符号整数形式返回一个整数参数的字符串表示形式
        p(Integer.toBinaryString(11));  // 1011
        // static String	    toHexString(int/long i)
        // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式
        p(Integer.toHexString(11));     // b
        // JDK1.8 支持无符号整数
        String unsignedString = String.valueOf((1L << 32) - 1);
        p(unsignedString);  // 4294967295
        int unsignedInt = Integer.parseUnsignedInt(unsignedString, 10);
        p(unsignedInt);     // -1
        unsignedString = Integer.toUnsignedString(unsignedInt, 10);
        p(unsignedString);  // 4294967295


        p("\n=== Double/Float ===");
        p(Double.isFinite(Float.POSITIVE_INFINITY));    // false
        p(Float.isInfinite(Float.POSITIVE_INFINITY));   // true
        p(Double.isNaN(Float.NaN));                     // true
    }
}
