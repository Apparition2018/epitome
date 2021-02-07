package jar.apache.commons.math3.stat;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static l.demo.Demo.p;

/**
 * StatUtils
 * http://www.cnblogs.com/xiao02fang/p/9883909.html
 * http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/stat/StatUtils.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class StatDemo {

    private double[] values = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private double[] values2 = new double[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    @Test
    public void testStatUtils() {

        // static double	max(double[] values[, int begin, int length])
        // 最大值
        p("max = " + StatUtils.max(values));        // 10.0

        // static double	min(double[] values[, int begin, int length])
        // 最小值
        p("min = " + StatUtils.min(values));        // 1.0

        // static double	sum(double[] values[, int begin, int length])
        // 总和
        p("sum = " + StatUtils.sum(values));        // 55.0

        // static double	sumSq(double[] values[, int begin, int length])
        // 平方和
        p("sum sq = " + StatUtils.sumSq(values));   // 385.0

        // static double	sumLog(double[] values[, int begin, int length])
        // 对数和
        p("sum log = " + StatUtils.sumLog(values)); // 385.0

        // static double	product(double[] values[, int begin, int length])
        // 返回乘积
        p("product = " + StatUtils.product(values));// 3628800.0

        // static double	mean(double[] values[, int begin, int length])
        // 算数平均数
        p("mean = " + StatUtils.mean(values));      // 5.5

        // static double	geometricMean(double[] values[, int begin, int length])
        // 几何平均数
        p("geometric mean = " + StatUtils.geometricMean(values));   // 4.528728688116766

        // static double[]	mode(double[] sample[, int begin, int length])
        // 众数
        double[] values3 = new double[]{1, 1, 1, 2, 2, 3, 3, 3};
        p("mode = " + Arrays.toString(StatUtils.mode(values3)));    // [1.0, 3.0]

        // static double	percentile(double[] values[, int begin, int length], double p)
        // 百分位数
        // https://www.sohu.com/a/236882664_660796
        p("percentile = " + StatUtils.percentile(values, 1));       // 1.0
        p("percentile = " + StatUtils.percentile(values, 50));      // 5.5，中位数
        p("percentile = " + StatUtils.percentile(values, 99));      // 10.0

        // static double	variance(double[] values[, double mean, int begin, int length])
        // 方差
        double variance = StatUtils.populationVariance(values);
        p("variance = " + variance);  // 9.166666666666666
        // 标准差
        p("standard deviation = " + Math.sqrt(variance));

        // static double	populationVariance(double[] values[, double mean, int begin, int length])
        // 总体方差
        p("population variance = " + StatUtils.populationVariance(values)); // 8.25


        // static double	meanDifference(double[] sample1, double[] sample2)
        // 返回两数组对应位置差的算数平均数 （平均差/平均概率偏差）
        double meanDifference = StatUtils.meanDifference(values2, values);
        p("mean difference = " + meanDifference);// 1.0

        // static double	sumDifference(double[] sample1, double[] sample2)
        // 返回两数组对应位置差的总和
        p("sum difference = " + StatUtils.sumDifference(values2, values));  // 10.0

        // static double	varianceDifference(double[] sample1, double[] sample2, double meanDifference)
        // 返回两数组对应位置差的方差 （方差差异性）
        p("variance difference = " + StatUtils.varianceDifference(values2, values, meanDifference));  // 0.0

    }

    // 中位数
    @Test
    public void testMedian() {
        Median median = new Median();
        p("median = " + median.evaluate(values)); // 5.5
    }

    // 标准差
    @Test
    public void testStandardDeviation() {
        StandardDeviation sd = new StandardDeviation();
        p("standard deviation = " + sd.evaluate(values)); // 3.0276503540974917
    }
}
