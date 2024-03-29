package jar.apache.commons.math3.transform;

import org.apache.commons.math3.transform.TransformUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * <a href="https://commons.apache.org/proper/commons-math/userguide/transform.html">Transforms</a>
 * <a href="http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/transform/TransformUtils.html">...</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class TransformDemo {

    @Test
    public void testTransformUtils() {
        // static Complex[]	    scaleArray(Complex[] f, double d)
        // static double[]	    scaleArray(double[] f, double d)
        // 比例扩/缩数组
        double[] arr = TransformUtils.scaleArray(new double[]{1, 2, 3}, 2);
        System.out.println(Arrays.toString(arr)); // [2.0, 4.0, 6.0]
    }
}
