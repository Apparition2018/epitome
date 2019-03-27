package jar.apache.commons.math3.util;

import org.apache.commons.math3.util.CombinatoricsUtils;
import org.junit.Test;

/**
 * CombinatoricsUtils
 * 组合学
 * <p>
 * http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/util/CombinatoricsUtils.html
 */
public class CombinatoricsUtilsDemo {

    @Test
    public void combinatorics() {

        // static long	    binomialCoefficient(int n, int k)
        // 二项式系数
        // https://www.baidu.com/s?wd=%E4%BA%8C%E9%A1%B9%E5%BC%8F%E7%B3%BB%E6%95%B0&tn=SE_PcZhidaonwhc_ngpagmjz&rsv_dl=gh_pc_zhidao
        p(CombinatoricsUtils.binomialCoefficient(4, 0));  // 1
        p(CombinatoricsUtils.binomialCoefficient(4, 1));  // 4
        p(CombinatoricsUtils.binomialCoefficient(4, 2));  // 6
        p(CombinatoricsUtils.binomialCoefficient(4, 3));  // 4
        p(CombinatoricsUtils.binomialCoefficient(4, 4));  // 1

        // n!
        p(CombinatoricsUtils.factorial(5)); // 120

        // static long	    stirlingS2(int n, int k)
        // 斯特林数
        // https://baike.baidu.com/item/%E6%96%AF%E7%89%B9%E6%9E%97%E6%95%B0/4938529?fr=aladdin
        p(CombinatoricsUtils.stirlingS2(4, 0)); // 0
        p(CombinatoricsUtils.stirlingS2(4, 1)); // 1
        p(CombinatoricsUtils.stirlingS2(4, 2)); // 7
        p(CombinatoricsUtils.stirlingS2(4, 3)); // 6
        p(CombinatoricsUtils.stirlingS2(4, 4)); // 1

    }

    public static <T> void p(T obj) {
        if (obj == null) {
            return;
        }
        System.out.println(obj);
    }

}
