package spring.api.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

import static com.ljh.commons.Demo.HELLO_WORLD;

/**
 * DigestUtils
 *
 * @author ljh
 * @since 2020/11/8 18:01
 */
public class DigestUtilsDemo {

    public static void main(String[] args) {
        byte[] bytes = HELLO_WORLD.getBytes(StandardCharsets.UTF_8);
        System.out.println(DigestUtils.md5DigestAsHex(bytes)); // ed076287532e86365e841e92bfc50d8c
    }
}
