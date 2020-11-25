package jar.apache.commons.math3.util;

import org.apache.commons.math3.util.CombinatoricsUtils;
import org.junit.Test;

import static l.demo.Demo.p;

/**
 * CombinatoricsUtils   组合学
 * http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/util/CombinatoricsUtils.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class CombinatoricsUtilsDemo {

    @Test
    public void combinatorics() {

        // static long	    binomialCoefficient(int n, int k)
        // 二项式系数
        p(CombinatoricsUtils.binomialCoefficient(4, 0));  // 1
        p(CombinatoricsUtils.binomialCoefficient(4, 1));  // 4
        p(CombinatoricsUtils.binomialCoefficient(4, 2));  // 6
        p(CombinatoricsUtils.binomialCoefficient(4, 3));  // 4
        p(CombinatoricsUtils.binomialCoefficient(4, 4));  // 1

        // n!
        p(CombinatoricsUtils.factorial(5)); // 120

        // static long	    stirlingS2(int n, int k)
        // 斯特林数
        p(CombinatoricsUtils.stirlingS2(4, 0)); // 0
        p(CombinatoricsUtils.stirlingS2(4, 1)); // 1
        p(CombinatoricsUtils.stirlingS2(4, 2)); // 7
        p(CombinatoricsUtils.stirlingS2(4, 3)); // 6
        p(CombinatoricsUtils.stirlingS2(4, 4)); // 1

    }

}
