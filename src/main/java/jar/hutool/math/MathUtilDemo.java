package jar.hutool.math;

import cn.hutool.core.math.MathUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * MathUtil
 * NumberUtil 的一个补充，MathUtil 偏向复杂数学计算
 * https://hutool.cn/docs/#/core/%E6%95%B0%E5%AD%A6/%E6%95%B0%E5%AD%A6%E7%9B%B8%E5%85%B3-MathUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/math/MathUtil.html
 * 
 * @author Arsenal
 * created on 2020/11/19 23:37
 */
public class MathUtilDemo extends Demo {

    @Test
    public void testMathUtil() {
        // 排列数，即A(n, n) = n!
        p(MathUtil.arrangementCount(3));
        // 排列数，即A(n, m) = n!/(n-m)!
        p(MathUtil.arrangementCount(4, 3));

        // 组合数，即C(n, m) = n!/((n-m)! * m!)
        p(MathUtil.combinationCount(4, 3));
    }
}
