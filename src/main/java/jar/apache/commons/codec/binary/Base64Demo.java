package jar.apache.commons.codec.binary;

import l.demo.Demo;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

/**
 * Base64
 * <p>
 * http://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/binary/Base64.html
 */
public class Base64Demo extends Demo {

    /**
     * 加密：
     * static byte[]	    encodeBase64(byte[] binaryData[, boolean isChunked, boolean urlSafe, int maxResultSize])
     * static String	    encodeBase64String(byte[] binaryData)
     * static byte[]	    encodeBase64URLSafe(byte[] binaryData)
     * static String	    encodeBase64URLSafeString(byte[] binaryData)
     * <p>
     * 解密：
     * static byte[]	    decodeBase64(byte[] base64Data)
     * static byte[]	    decodeBase64(String base64String)
     * static BigInteger	decodeInteger(byte[] pArray)
     * <p>
     * 判断是否为 Base64：
     * static boolean	    isBase64(byte octet)
     * static boolean	    isBase64(byte[] arrayOctet)
     * static boolean	    isBase64(String base64)
     */
    @Test
    public void base64() {
        /* 加密 */
        String src = "epitome";
        byte[] encodeBytes = Base64.encodeBase64(src.getBytes());
        p(new String(encodeBytes)); // ZXBpdG9tZQ==
        encodeBytes = Base64.encodeBase64URLSafe(src.getBytes());
        p(new String(encodeBytes)); // ZXBpdG9tZQ

        String encode = Base64.encodeBase64String(src.getBytes());
        p(encode); // ZXBpdG9tZQ==
        encode = Base64.encodeBase64URLSafeString(src.getBytes());
        p(encode); // ZXBpdG9tZQ

        /* 判断是否为 Base64 */
        p(Base64.isBase64(encodeBytes));// true
        p(Base64.isBase64(encode));     //true

        /* 解密 */
        byte[] decodeBytes = Base64.decodeBase64(encodeBytes);
        p(new String(decodeBytes)); // epitome

        decodeBytes = Base64.decodeBase64(encode);
        p(new String(decodeBytes)); // epitome
    }
}
