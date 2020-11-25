package jar.apache.commons.lang3;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static l.demo.Demo.p;

/**
 * RandomStringUtils
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/RandomStringUtils.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class RandomStringUtilsDemo {

    @Test
    public void random() {

        // random(int count)                随机字符串，中文环境下乱码
        p(RandomStringUtils.random(10));

        // random(int count, char... chars) 随机字符串，指定字符
        p(RandomStringUtils.random(10, 'a', 'b', 'c', 'd', 'e', 'f'));

        // random(int count, String chars)  随机字符串，指定字符
        p(RandomStringUtils.random(10, "abcdefghijklmnopqrstuvwxyz"));

        // random(int count, boolean letters, boolean numbers[, char... chars])
        // 随机字符串，是否包含字母、数字
        p(RandomStringUtils.random(10, true, false));
        p(RandomStringUtils.random(10, false, true));

        // randomAlphabetic(int count)      随机字母
        // randomAlphabetic(int minLengthInclusive, int maxLengthExclusive)
        p(RandomStringUtils.randomAlphabetic(10));
        p(RandomStringUtils.randomAlphabetic(4, 11));   // 4-10 位

        // randomNumeric(int count)         随机数字
        // randomNumeric(int minLengthInclusive, int maxLengthExclusive)
        p(RandomStringUtils.randomNumeric(10));
        p(RandomStringUtils.randomNumeric(4, 11));      // 4-10 位

        // randomAlphanumeric(int count)    随机数字和字母
        // randomAlphanumeric(int minLengthInclusive, int maxLengthExclusive)
        p(RandomStringUtils.randomAlphanumeric(10));
        p(RandomStringUtils.randomAlphanumeric(4, 11)); // 4-10 位

        // randomAscii(int count)           随机 ASCII，32 ~ 126
        // randomAscii(int minLengthInclusive, int maxLengthExclusive)
        p(RandomStringUtils.randomAscii(10));
        p(RandomStringUtils.randomAscii(4, 11));        // 4-10 位

        // randomGraph(int count)           随机字符 POSIX [:graph:]，非空格(non-space)字符
        // randomGraph(int minLengthInclusive, int maxLengthExclusive)
        p(RandomStringUtils.randomGraph(10));
        p(RandomStringUtils.randomGraph(4, 11));        // 4-10 位

        // randomPrint(int count)           随机字符 POSIX [:print:]，可显示的字符
        // randomPrint(int minLengthInclusive, int maxLengthExclusive)
        p(RandomStringUtils.randomPrint(10));
        p(RandomStringUtils.randomPrint(4, 11));        // 4-10 位

    }

}
