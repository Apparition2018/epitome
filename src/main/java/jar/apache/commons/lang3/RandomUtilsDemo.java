package jar.apache.commons.lang3;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * RandomUtils
 */
public class RandomUtilsDemo {

    @Test
    public void random() {
        System.out.println(RandomUtils.nextBoolean()); // false

        System.out.println(Arrays.toString(RandomUtils.nextBytes(5))); // [-108, 78, 126, -9, -73]

        System.out.println(RandomUtils.nextInt());
        System.out.println(RandomUtils.nextInt(0, 5)); // 包前不包后

        System.out.println(RandomUtils.nextLong());
        System.out.println(RandomUtils.nextLong(0, 5));

        System.out.println(RandomUtils.nextFloat());
        System.out.println(RandomUtils.nextFloat(0, 5));

        System.out.println(RandomUtils.nextDouble());
        System.out.println(RandomUtils.nextDouble(0, 5));
    }

}
