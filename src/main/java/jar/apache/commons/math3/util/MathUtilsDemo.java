package jar.apache.commons.math3.util;

import org.junit.Test;

/**
 * MathUtils
 * <p>
 * http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/util/MathUtils.html
 */
public class MathUtilsDemo {

    @Test
    public void math() {

        // static void	    checkFinite(double/double[] x)
        // 检测有限
        // MathUtils.checkFinite(Double.POSITIVE_INFINITY); // NotFiniteNumberException: ∞ is not a finite number

        // static void	    checkNotNull(Object o)
        // 检测 null
        // MathUtils.checkNotNull(null); // NullArgumentException: null is not allowed

    }

    private static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}
