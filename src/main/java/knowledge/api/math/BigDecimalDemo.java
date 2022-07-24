package knowledge.api.math;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static l.demo.Demo.p;

/**
 * BigDecimal
 * BigDecimal 不可变的、任意精度的有符号十进制数。
 * BigDecimal 由任意精度的整数非标度值 和 32 位的整数标度 (scale) 组成。
 * 如果为零或正数，则标度是小数点后的位数。
 * 如果为负数，则将该数的非标度值乘以 10 的负 scale 次幂。
 * 因此，BigDecimal 表示的数值是 (unscaledValue × 10-scale)。
 * https://tool.oschina.net/uploads/apidocs/jdk-zh/java/math/BigDecimal.html
 * <p>
 * BigDecimal	add(BigDecimal augend)              this + augend
 * BigDecimal	subtract(BigDecimal subtrahend)     this - subtrahend
 * BigDecimal	multiply(BigDecimal multiplicand)   this × multiplicand
 * BigDecimal	divide(BigDecimal divisor, ...)     this / divisor
 * <p>
 * BigInteger	toBigInteger()                      BigDecimal → BigInteger
 * String	    toEngineeringString()               返回此 BigDecimal 的字符串表示形式，需要指数时，则使用工程计数法
 * String	    toPlainString()                     返回不带指数字段的此 BigDecimal 的字符串表示形式
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class BigDecimalDemo {

    /**
     * @see RoundingModeDemo
     */
    @Test
    public void constant() {
        p(BigDecimal.ROUND_UP);             // 舍入远离零的舍入模式
        p(BigDecimal.ROUND_DOWN);           // 接近零的舍入模式
        p(BigDecimal.ROUND_CEILING);        // 接近正无穷大的舍入模式
        p(BigDecimal.ROUND_FLOOR);          // 接近负无穷大的舍入模式
        p(BigDecimal.ROUND_HALF_UP);        // 向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则向上舍入的舍入模式
        p(BigDecimal.ROUND_HALF_DOWN);      // 向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则向下舍入的舍入模式
        p(BigDecimal.ROUND_HALF_EVEN);      // 向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则向相邻的偶数舍入（银行家舍入法）
        p(BigDecimal.ROUND_UNNECESSARY);    // 断言请求的操作具有精确的结果，因此不需要舍入
    }

    @Test
    public void testBigDecimal() {
        // BigDecimal(BigInteger val)               BigInteger → BigDecimal
        // BigDecimal(double/int/long/String val)   XXX → BigDecimal
        // 阿里编程规约：
        // 禁止使用构造方法 BigDecimal(double) 的方式把 double 值转化为 BigDecimal 对象
        // BigDecimal(double) 存在精度损失风险
        // 优先推荐入参为 String 的构造方法，或使用 BigDecimal 的 valueOf 方法
        p(new BigDecimal(12.34));                       // 12.339999999999999857891452847979962825775146484375；unpredictable，
        p(new BigDecimal("12.34"));                     // 12.34
        p(new BigDecimal("12.340"));                    // 12.340
        // BigDecimal	stripTrailingZeros()        返回数值上等于此小数，但从该表示形式移除所有尾部零的 BigDecimal
        p(new BigDecimal("12.340").stripTrailingZeros());// 12.34

        // BigDecimal	movePointLeft(int n)        返回一个 BigDecimal，它等效于将该值的小数点向左移动 n 位
        // BigDecimal	movePointRight(int n)       返回一个 BigDecimal，它等效于将该值的小数点向右移动 n 位
        p(new BigDecimal("12.34").movePointLeft(1));    // 1.234
        p(new BigDecimal("12.34").movePointRight(1));   // 123.4

        // int	        precision()                 返回此 BigDecimal 的精度
        p(new BigDecimal("12.34").precision());         // 4
        p(new BigDecimal("12340").precision());         // 5

        // int	        scale()                     返回此 BigDecimal 的标度
        p(new BigDecimal("12.34").scale());             // 2
        p(new BigDecimal("12340").scale());             // 0

        // BigDecimal	ulp()                       返回此 BigDecimal 的 ulp（最后一位的单位）的大小
        p(new BigDecimal("12.34").ulp());               // 0.01
        p(new BigDecimal("12340").ulp());               // 1
    }

    /**
     * BigDecimal 的等值比较应使用 compareTo() 方法，而不是 equals() 方法（阿里编程规约）
     */
    @Test
    public void compareTo() {
        p(new BigDecimal("1.0").compareTo(new BigDecimal("0.9")));  // 1
        p(new BigDecimal("0.9").compareTo(new BigDecimal("1.0")));  // -1

        // equals() 会比较精度
        p(new BigDecimal("1.0").equals(new BigDecimal("1.00")));    // false
        // compareTo() 会忽略精度
        p(new BigDecimal("1.0").compareTo(new BigDecimal("1.00"))); // 0
    }

}
