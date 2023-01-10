package knowledge.api.security;

import l.demo.Demo;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.stream.IntStream;

/**
 * SecureRandom
 *
 * @author ljh
 * @since 2023/1/9 11:41
 */
public class SecureRandomDemo extends Demo {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SecureRandom random;
        if (randomBoolean()) {
            random = SecureRandom.getInstance("SHA1PRNG");
        } else {
            random = new SecureRandom();
        }
        // 随机生成[100000,999999]之间的数字
        if (randomBoolean()) {
            StringBuilder code = new StringBuilder();
            IntStream.rangeClosed(1, 6).forEach(i -> code.append(random.nextInt(10)));
            p(code);
        } else {
            p(random.nextInt(900000) + 1000000);
        }
    }
}
