package jar.apache.commons.codec.binary;

import l.demo.Demo;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * <a href="https://commons.apache.org/proper/commons-codec/userguide.html#Binary_Encoders">Binary Encoders</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class BinaryEncodersDemo extends Demo {

    @Test
    public void testBase64() {
        // 加密
        byte[] encodeBytes = Base64.encodeBase64(HELLO_WORLD.getBytes());
        p(new String(encodeBytes, StandardCharsets.UTF_8)); // SGVsbG8gV29ybGQh
        encodeBytes = Base64.encodeBase64URLSafe(HELLO_WORLD.getBytes());
        p(new String(encodeBytes, StandardCharsets.UTF_8)); // SGVsbG8gV29ybGQh

        String encode = Base64.encodeBase64String(HELLO_WORLD.getBytes());
        p(encode);                                          // SGVsbG8gV29ybGQh
        encode = Base64.encodeBase64URLSafeString(HELLO_WORLD.getBytes());
        p(encode);                                          // SGVsbG8gV29ybGQh

        // 判断是否为 Base64
        p(Base64.isBase64(encodeBytes));                    // true
        p(Base64.isBase64(encode));                         // true

        // 解密
        byte[] decodeBytes = Base64.decodeBase64(encodeBytes);
        p(new String(decodeBytes, StandardCharsets.UTF_8)); // Hello World!

        decodeBytes = Base64.decodeBase64(encode);
        p(new String(decodeBytes, StandardCharsets.UTF_8)); // Hello World!
    }
}
