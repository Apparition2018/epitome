package org.ljh.dependencies.group.apache.commons.rng;

import org.apache.commons.rng.RestorableUniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

import static com.ljh.commons.Demo.p;

/**
 * <a href="https://commons.apache.org/proper/commons-rng/index.html">Random Numbers Generators</a> 随机数生成器
 *
 * @author ljh
 * @since 2023/12/5 10:53
 */
public class RngDemo {

    public static void main(String[] args) {
        RestorableUniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();
        p(rng.nextBoolean());
        p(rng.nextInt(0, 100));
        p(rng.nextLong(0, 100));
        p(rng.nextFloat(0, 100));
        p(rng.nextDouble(0, 100));
    }
}
