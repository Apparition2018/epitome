package jar.apache.commons.math3.distribution;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://commons.apache.org/proper/commons-math/userguide/distribution.html">Probability Distributions</a>
 * <p><a href="http://yzd.iteye.com/blog/852082">commons-math3 分布</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class DistributionDemo {

    /**
     * 饮料装填量不足与超量的概率
     * <pre>
     * 某饮料公司装瓶流程严谨，每罐饮料装填量符合平均600毫升，标准差3毫升的常态分配法则。随机选取一罐，容量超过605毫升的概率？容量小于590毫升的概率？
     * 容量超过605毫升的概率 = p(X > 605) = p(((X - μ) / σ) > ((605 – 600) / 3)) = p(Z > 5 / 3) = p(Z > 1.67) = 0.0475
     * 容量小于590毫升的概率 = p(X < 590) = p(((X - μ) / σ) < ((590 – 600) / 3)) = p(Z < - 10 / 3) = p(Z < - 3.33) = 0.0004
     * </pre>
     */
    @Test
    public void testNormalDistribution() {
        NormalDistribution normal = new NormalDistribution(600, 3);

        try {
            System.out.println("p(X > 605) = " + normal.cumulativeProbability(605));
            System.out.println("p(X < 590) = " + (1 - normal.cumulativeProbability(590)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
