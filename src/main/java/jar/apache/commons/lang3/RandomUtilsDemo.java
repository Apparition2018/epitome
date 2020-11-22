package jar.apache.commons.lang3;

import l.demo.Demo;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

/**
 * RandomUtils
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/RandomUtils.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class RandomUtilsDemo extends Demo {

    @Test
    public void random() {
        p(RandomUtils.nextBoolean());   // false

        p(RandomUtils.nextBytes(5));    // [39, -56, 8, 97, -103]

        p(RandomUtils.nextInt());
        p(RandomUtils.nextInt(0, 5));

        p(RandomUtils.nextLong());
        p(RandomUtils.nextLong(0, 5));

        p(RandomUtils.nextFloat());
        p(RandomUtils.nextFloat(0, 5));

        p(RandomUtils.nextDouble());
        p(RandomUtils.nextDouble(0, 5));
    }

}
