package jar.apache.commons.math3.util;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * ArithmeticUtils  算术
 * http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/util/ArithmeticUtils.html
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class ArithmeticUtilsDemo {

    @Test
    public void arithmetic() {

        // static int/long      addAndCheck(int/long x, int/long y)
        // 相加，检查溢出
        p(ArithmeticUtils.addAndCheck(15, 15));

        // static int/long	    mulAndCheck(int x/long, int/long y)
        // 相乘，检查溢出
        p(ArithmeticUtils.mulAndCheck(15, 15));

        // static int/long	    subAndCheck(int x/long, int/long y)
        // 想减，检查溢出
        p(ArithmeticUtils.subAndCheck(15, 15));

        // static int/long	    gcd(int/long p, int/long q)
        // 最大公约数，使用 "binary gcd" 方法
        p(ArithmeticUtils.gcd(9, 15));  // 3

        // static int/long	    lcm(int/long a, int/long b)
        // 最小公倍数，lcm(a,b) = (a / gcd(a,b)) * b.
        p(ArithmeticUtils.lcm(4, 7));   // 28

        // static int/long	    pow(int k, int e)
        p(ArithmeticUtils.pow(2, 3));   // 8

        // static boolean	    isPowerOfTwo(long n)
        // 如果是 2 的幂，则返回 true
        p(ArithmeticUtils.isPowerOfTwo(1024)); // true

    }

}
