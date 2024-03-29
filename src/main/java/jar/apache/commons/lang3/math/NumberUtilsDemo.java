package jar.apache.commons.lang3.math;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * <a href="http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/math/NumberUtils.html">NumberUtils</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class NumberUtilsDemo {

    /**
     * static int	compare(byte/int/long/short x, byte/int/long/short y)
     */
    @Test
    public void compare() {
        p(NumberUtils.compare(1, 2)); // -1
    }

    /**
     * static XXX	createXX(String str)    XXX: BigDecimal, BigInteger, Double, Float, Integer, Long, Number
     * <p>
     * String → XXX
     */
    @Test
    public void createXXX() {
        if (NumberUtils.isParsable("321.00")) {
            p(NumberUtils.createFloat("321.00")); // 321.0
        }

        if (NumberUtils.isCreatable("321.00")) {
            p(NumberUtils.createNumber("321.00")); // 321.0
        }
    }

    /**
     * static XXX	toXXX(String str[, XXX defaultValue])   XXX: byte, double, float, int, long, short
     * <p>
     * String → XXX
     */
    @Test
    public void toXXX() {
        p(NumberUtils.toFloat(null, -1));               // -1.0
        p(NumberUtils.toFloat(StringUtils.EMPTY, -1));  // -1.0
        p(NumberUtils.toFloat("321.00"));               //  321.0
    }

    /**
     * static boolean	isCreatable(String str)m
     * <p>判断字符串是否为有效的 Java Number
     */
    @Test
    public void isCreatable() {
        createXXX();
    }

    /**
     * static boolean	isDigits(String str)
     * <p>判断字符串是否只包含数字字符
     */
    @Test
    public void isDigits() {
        p(NumberUtils.isDigits("321"));     // true
        p(NumberUtils.isDigits("321.00"));  // false
        p(NumberUtils.isDigits("321.0A"));  // false
    }

    /**
     * static boolean	isParsable(String str)
     * <p>判断字符串是否可以转换成数字
     */
    @Test
    public void isParsable() {
        createXXX();
    }

    /**
     * static XXX	max(XXX... array)
     * <p>
     * static XXX	min(XXX... array)
     */
    @Test
    public void max_min() {
        p(NumberUtils.max(1.111, 11.11, 111.1, 1111));  // 1111.0
        p(NumberUtils.min(1.111, 11.11, 111.1, 1111));  // 1.111
    }
}
