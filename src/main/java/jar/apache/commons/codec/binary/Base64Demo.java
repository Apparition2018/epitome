package jar.apache.commons.codec.binary;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

/**
 * Base64
 * <p>
 * http://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/binary/Base64.html
 */
public class Base64Demo {

    /**
     * static byte[]	    encodeBase64(byte[] binaryData[, boolean isChunked, boolean urlSafe, int maxResultSize])
     * static String	    encodeBase64String(byte[] binaryData)
     * static byte[]	    encodeBase64URLSafe(byte[] binaryData)
     * static String	    encodeBase64URLSafeString(byte[] binaryData)
     * 加密
     * <p>
     * static byte[]	    decodeBase64(byte[] base64Data)
     * static byte[]	    decodeBase64(String base64String)
     * static BigInteger	decodeInteger(byte[] pArray)
     * 解密
     * <p>
     * static boolean	    isBase64(byte octet)
     * static boolean	    isBase64(byte[] arrayOctet)
     * static boolean	    isBase64(String base64)
     * 判断是否为 Base64
     */
    @Test
    public void base64() {
        /* 加密 */
        String src = "www.baidu.com";
        byte[] encodeBytes = Base64.encodeBase64(src.getBytes());
        p(new String(encodeBytes)); // d3d3LmJhaWR1LmNvbQ==
        encodeBytes = Base64.encodeBase64URLSafe(src.getBytes());
        p(new String(encodeBytes)); // d3d3LmJhaWR1LmNvbQ

        String encode = Base64.encodeBase64String(src.getBytes());
        p(encode); // d3d3LmJhaWR1LmNvbQ==
        encode = Base64.encodeBase64URLSafeString(src.getBytes());
        p(encode); // d3d3LmJhaWR1LmNvbQ

        /* 判断是否为 Base64 */
        p(Base64.isBase64(encodeBytes)); // true
        p(Base64.isBase64(encode));

        /* 解密 */
        byte[] decodeBytes = Base64.decodeBase64(encodeBytes);
        p(new String(decodeBytes)); // www.baidu.com

        decodeBytes = Base64.decodeBase64(encode);
        p(new String(decodeBytes)); // www.baidu.com
    }

    public static <T> void p(T obj) {
        if (obj == null) {
            return;
        }
        System.out.println(obj);
    }
}
