package knowledge.api.incubator;

// import jdk.incubator.vector.DoubleVector;

import l.demo.Demo;

import java.io.IOException;
import java.util.stream.DoubleStream;

/**
 * <a href="https://achang.blog.csdn.net/article/details/126533134">矢量运算</a>
 *
 * @author ljh
 * @since 2023/2/21 8:48
 */
public class VectorDemo extends Demo {

    private static final double[] a = new double[]{0.6F, 0.7F, 0.8F, 0.9F};
    private static final double[] x = new double[]{1.0F, 2.0F, 3.0F, 4.0F};

    /**
     * y = a0x0 + a1x1 + a2x2 + ... + an-1·xn-1
     * 注：数字是下标
     */
    private static Double sumInScalar(double[] a, double[] x) {
        double[] y = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            y[i] = a[i] * x[i];
        }
        return DoubleStream.of(y).sum();
    }

    // /**
    //  * va 是 a 的矢量表达形式
    //  */
    // private static final DoubleVector va = DoubleVector.fromArray(DoubleVector.SPECIES_128, a, 0);
    // /**
    //  * vx 是 x 的矢量表达形式
    //  */
    // private static final DoubleVector vx = DoubleVector.fromArray(DoubleVector.SPECIES_128, x, 0);
    //
    // private static Double sumInVector(DoubleVector va, DoubleVector vx) {
    //     // 矢量运算
    //     double[] y = va.mul(vx).toArray();
    //     return DoubleStream.of(y).sum();
    // }

    public static void main(String[] args) throws IOException {
        stopWatch.start("scalar");
        for (int i = 0; i < THOUSAND; i++) {
            sumInScalar(a, x);
        }
        stopWatch.stop();

        // VM options: --add-modules jdk.incubator.vector
        stopWatch.start("vector");
        for (int i = 0; i < THOUSAND; i++) {
            // sumInVector(va, vx);
        }
        stopWatch.stop();

        p(stopWatch.prettyPrint());
    }
}
