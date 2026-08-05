package jar.apache.commons.rng;

import l.demo.Demo;
import org.apache.commons.rng.RestorableUniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

/**
 * <a href="https://commons.apache.org/proper/commons-rng/index.html">Random Numbers Generators</a> 随机数生成器
 *
 * @author ljh
 * @since 2023/12/5 10:53
 */
public class RngDemo extends Demo {

    public static void main(String[] args) {
        RestorableUniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();
        p(rng.nextBoolean());
        p(rng.nextInt(0, 100));
        p(rng.nextLong(0, 100));
        p(rng.nextFloat(0, 100));
        p(rng.nextDouble(0, 100));
    }
}
