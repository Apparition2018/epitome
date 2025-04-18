package jar.apache.commons.lang3;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

import static l.demo.Demo.p;

/**
 * <a href="http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/RandomStringUtils.html">RandomStringUtils</a>
 *
 * @author ljh
 * @see <a href="https://www.baeldung.com/java-generate-secure-password">在 Java 中生成安全的随机密码</a>
 * @see jar.apache.commons.text.RandomStringGeneratorDemo
 * @since 2019/8/8 19:39
 */
public class RandomStringUtilsDemo {

    public static void main(String[] args) {
        // random(int count, int start, int end, final boolean letters, final boolean numbers, final char[] chars, final Random random)
        p("随机数字字母\t" + RandomStringUtils.random(10, 48, 122, true, true, null, new SecureRandom()));
        char[] allowedChars = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz".toCharArray();
        p("随机数字字母\t" + RandomStringUtils.random(10, 0, 0, false, false, allowedChars, new SecureRandom()));

        // random(int count)
        p("随机字符\t\t" + RandomStringUtils.random(10));

        // random(int count, char... chars)
        p("随机字符\t\t" + RandomStringUtils.random(10, 'a', 'b', 'c', 'd', 'e', 'f'));

        // random(int count, String chars)
        p("随机字符\t\t" + RandomStringUtils.random(10, "abcdefghijklmnopqrstuvwxyz"));

        // random(int count, boolean letters, boolean numbers[, char... chars])
        p("随机字母\t\t" + RandomStringUtils.random(10, true, false));
        p("随机数字\t\t" + RandomStringUtils.random(10, false, true));

        // randomAlphabetic(int count)
        // randomAlphabetic(int minLengthInclusive, int maxLengthExclusive)
        p("随机字母\t\t" + RandomStringUtils.randomAlphabetic(10));
        p("随机字母\t\t" + RandomStringUtils.randomAlphabetic(4, 11));

        // randomNumeric(int count)
        // randomNumeric(int minLengthInclusive, int maxLengthExclusive)
        p("随机数字\t\t" + RandomStringUtils.randomNumeric(10));
        p("随机数字\t\t" + RandomStringUtils.randomNumeric(4, 11));

        // randomAlphanumeric(int count)
        // randomAlphanumeric(int minLengthInclusive, int maxLengthExclusive)
        p("随机数字字母\t" + RandomStringUtils.randomAlphanumeric(10));
        p("随机数字字母\t" + RandomStringUtils.randomAlphanumeric(4, 11));

        // randomAscii(int count)
        // randomAscii(int minLengthInclusive, int maxLengthExclusive)
        p("随机ASCII\t" + RandomStringUtils.randomAscii(10));
        p("随机ASCII\t" + RandomStringUtils.randomAscii(4, 11));

        // randomGraph(int count)
        // randomGraph(int minLengthInclusive, int maxLengthExclusive)
        p("随机可见ASCII\t" + RandomStringUtils.randomGraph(10));
        p("随机可见ASCII\t" + RandomStringUtils.randomGraph(4, 11));

        // randomPrint(int count)
        // randomPrint(int minLengthInclusive, int maxLengthExclusive)
        p("随机可见ASCII和空格\t" + RandomStringUtils.randomPrint(10));
        p("随机可见ASCII和空格\t" + RandomStringUtils.randomPrint(4, 11));
    }
}
