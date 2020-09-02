package knowledge.api.math;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * BigDecimal
 * BigDecimal 不可变的、任意精度的有符号十进制数。
 * BigDecimal 由任意精度的整数非标度值 和 32 位的整数标度 (scale) 组成。
 * 如果为零或正数，则标度是小数点后的位数。
 * 如果为负数，则将该数的非标度值乘以 10 的负 scale 次幂。
 * 因此，BigDecimal 表示的数值是 (unscaledValue × 10-scale)。
 * <p>
 * 算术运算结果的首选标度：
 * 加	max(addend.scale(), augend.scale())
 * 减	max(minuend.scale(), subtrahend.scale())
 * 乘	multiplier.scale() + multiplicand.scale()
 * 除	dividend.scale() - divisor.scale()
 * <p>
 * BigInteger	toBigInteger()                  BigDecimal → BigInteger
 * String	    toEngineeringString()           返回此 BigDecimal 的字符串表示形式，需要指数时，则使用工程计数法
 * String	    toPlainString()                 返回不带指数字段的此 BigDecimal 的字符串表示形式
 */
public class BigDecimalDemo {

    /**
     * BigDecimal(BigInteger val)   BigInteger → BigDecimal
     * BigDecimal(double val)       double → BigDecimal，不建议使用
     * BigDecimal(int val)          int → BigDecimal
     * BigDecimal(long val)         long → BigDecimal
     * BigDecimal(String val)       String → BigDecimal
     */
    @Test
    public void BigDecimal() {
        BigDecimal bd1 = new BigDecimal(2.3);
        BigDecimal bd2 = new BigDecimal("2.3");
        p(bd1); // 2.29999999999999982236431605997495353221893310546875
        p(bd2); // 2.3
    }

    /**
     * BigDecimal	divide(BigDecimal divisor[, int scale, RoundingMode roundingMode])
     * 返回一个 BigDecimal，其值为 (this / divisor)，其标度为指定标度
     */
    @Test
    public void divide() {
        BigDecimal bd1 = new BigDecimal("100");
        BigDecimal bd2 = new BigDecimal("3");
        p(bd1.divide(bd2, 2, BigDecimal.ROUND_HALF_UP)); // 33.33
    }

    /**
     * BigDecimal	movePointLeft(int n)    返回一个 BigDecimal，它等效于将该值的小数点向左移动 n 位
     * BigDecimal	movePointRight(int n)   返回一个 BigDecimal，它等效于将该值的小数点向右移动 n 位
     */
    @Test
    public void movePoint() {
        BigDecimal bd1 = new BigDecimal("12.3456");
        p(bd1.movePointLeft(2));    // 0.123456
        p(bd1.movePointRight(2));   // 1234.56
    }

    /**
     * int	        precision()             返回此 BigDecimal 的精度
     */
    @Test
    public void precision() {
        BigDecimal bd1 = new BigDecimal("12.3456");
        BigDecimal bd2 = new BigDecimal("123");
        p(bd1.precision()); // 6
        p(bd2.precision()); // 3
    }

    /**
     * int	        scale()                 返回此 BigDecimal 的标度
     */
    @Test
    public void scale() {
        BigDecimal bd1 = new BigDecimal("12.3456");
        BigDecimal bd2 = new BigDecimal("123");
        p(bd1.scale()); // 4
        p(bd2.scale()); // 0
    }

    /**
     * BigDecimal	setScale(int newScale, RoundingMode roundingMode)
     * 返回 BigDecimal，其标度为指定值，其非标度值通过此 BigDecimal 的非标度值乘以或除以十的适当次幂来确定，以维护其总值
     */
    @Test
    public void setScale() {
        BigDecimal bd = new BigDecimal("12.3456");
        p(bd.setScale(3, BigDecimal.ROUND_HALF_UP)); // 12.346
    }

    /**
     * BigDecimal	stripTrailingZeros()
     * 返回数值上等于此小数，但从该表示形式移除所有尾部零的 BigDecimal
     */
    @Test
    public void stripTrailingZeros() {
        BigDecimal bd = new BigDecimal("12.340000");
        p(bd); // 12.340000
        p(bd.stripTrailingZeros()); // 12.34
    }

    /**
     * BigDecimal	ulp()
     * 返回此 BigDecimal 的 ulp（最后一位的单位）的大小
     */
    @Test
    public void ulp() {
        BigDecimal bd1 = new BigDecimal("12.3456");
        BigDecimal bd2 = new BigDecimal("123");
        p(bd1.ulp()); // 0.0001
        p(bd2.ulp()); // 1
    }

    private static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }
}
