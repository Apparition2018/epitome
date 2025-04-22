package jar.hutool.math;

import cn.hutool.core.math.MathUtil;

import static l.demo.Demo.p;

/**
 * <a href="https://doc.hutool.cn/pages/MathUtil/">MathUtil</a>
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/math/MathUtil.html">MathUtil api</a>
 * <p>NumberUtil 的一个补充，MathUtil 偏向复杂数学计算
 *
 * @author ljh
 * @since 2020/11/19 23:37
 */
public class MathUtilDemo {

    public static void main(String[] args) {
        // 排列数，即A(n, n) = n!
        p(MathUtil.arrangementCount(3));
        // 排列数，即A(n, m) = n!/(n-m)!
        p(MathUtil.arrangementCount(4, 3));

        // 组合数，即C(n, m) = n!/((n-m)! * m!)
        p(MathUtil.combinationCount(4, 3));
    }
}
