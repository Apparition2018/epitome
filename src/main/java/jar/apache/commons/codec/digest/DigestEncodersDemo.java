package jar.apache.commons.codec.digest;

import l.demo.Demo;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * <a href="https://commons.apache.org/proper/commons-codec/userguide.html#Digest_Encoders">Digest Encoders</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class DigestEncodersDemo extends Demo {

    @Test
    public void testDigestUtils() {
        // md5
        // 1.
        byte[] md5bytes = DigestUtils.digest(DigestUtils.getMd5Digest(), HELLO_WORLD.getBytes(StandardCharsets.UTF_8));
        p(new BigInteger(1, md5bytes).toString(16));
        // 2.
        p(DigestUtils.md5Hex(HELLO_WORLD));     // ed076287532e86365e841e92bfc50d8c

        // sha
        // 1.
        byte[] sha256bytes = DigestUtils.digest(DigestUtils.getSha256Digest(), HELLO_WORLD.getBytes(StandardCharsets.UTF_8));
        p(new BigInteger(1, sha256bytes).toString(16));
        // 2.
        p(DigestUtils.sha256Hex(HELLO_WORLD));  // 7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069
    }
}
