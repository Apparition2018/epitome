package spring.demo.util;

import l.demo.Demo;
import org.springframework.util.DigestUtils;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * DigestUtils
 *
 * @author Arsenal
 * created on 2020/11/8 18:01
 */
public class DigestUtilsDemo extends Demo {
    
    @Test
    public void testDigestUtils() {
        byte[] bytes = HELLO_WORLD.getBytes(StandardCharsets.UTF_8);
        p(DigestUtils.md5DigestAsHex(bytes)); // ed076287532e86365e841e92bfc50d8c
    }
}
