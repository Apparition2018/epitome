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
        String src = "epitome";

        // static String	    md5Hex(byte[]/InputStream/String data)
        // md5，返回16进制字符串
        System.out.println(DigestUtils.md5Hex(src));  // 7180fbd80d7c1ea33c55018a8adae65a

        // static String	    sha1Hex(byte[]/InputStream/String data)
        // sha1，返回16进制字符串
        System.out.println(DigestUtils.sha1Hex(src)); // 492ddc9b1c89154a446288ca644bf726e8bc40d5

    }

}
