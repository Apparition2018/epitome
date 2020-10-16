package jar.apache.commons.lang3;

import l.demo.Demo;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * RandomUtils
 */
public class RandomUtilsDemo extends Demo {

    @Test
    public void random() {
        p(RandomUtils.nextBoolean()); // false

        p(Arrays.toString(RandomUtils.nextBytes(5))); // [-108, 78, 126, -9, -73]

        p(RandomUtils.nextInt());
        p(RandomUtils.nextInt(0, 5)); // 包前不包后

        p(RandomUtils.nextLong());
        p(RandomUtils.nextLong(0, 5));

        p(RandomUtils.nextFloat());
        p(RandomUtils.nextFloat(0, 5));

        p(RandomUtils.nextDouble());
        p(RandomUtils.nextDouble(0, 5));
    }

}
