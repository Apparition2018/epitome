package jar.apache.commons.math3.transform;

import org.apache.commons.math3.transform.TransformUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * TransformUtils
 * http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/transform/TransformUtils.html
 */
public class TransformUtilsDemo {

    @Test
    public void transform() {

        // static Complex[]	    scaleArray(Complex[] f, double d)
        // static double[]	    scaleArray(double[] f, double d)
        // 比例扩/缩数组
        double[] arr = TransformUtils.scaleArray(new double[]{1, 2, 3}, 2);
        System.out.println(Arrays.toString(arr)); // [2.0, 4.0, 6.0]


    }

}
