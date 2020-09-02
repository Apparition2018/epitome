package jar.apache.commons.codec.digest;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * DigestUtils
 * <p>
 * http://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/digest/DigestUtils.html
 */
public class DigestUtilsDemo {

    @Test
    public void digest() {
        String src = "www.baidu.com";

        // static String	md5Hex(byte[]/InputStream/String data)
        // md5，返回16进制字符串
        p(DigestUtils.md5Hex(src));  // dab19e82e1f9a681ee73346d3e7a575e

        // static String	sha1Hex(byte[]/InputStream/String data)
        // sha1，返回16进制字符串
        p(DigestUtils.sha1Hex(src)); // 31e50a13cdfa1bc2a6a0c2a31b74e3340b7a2dbc

    }

    private static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}
