package knowledge.加解密和消息摘要;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * imooc 课程：Java 实现 Base64 加密
 * <p>
 * BASE64 严格地说，属于编码格式，而非加密算法。
 * BASE 加密后产生的字节位数是8的倍数，如果不够位数以 = 符号填充。
 * <p>
 * Base64 算法原理：https://www.cnblogs.com/chengmo/archive/2014/05/18/3735917.html
 * 图片 Base64 互转：https://www.cnblogs.com/libra0920/p/5754356.html
 */
public class Base64Demo {

    private static String src = "Hello World!";

    /**
     * 在 Java8 中，Base64 编码已经成为 Java 类库的标准。
     * http://www.runoob.com/java/java8-base64.html
     */
    @Test
    public void javaUtilBase64() {
        // 基本：输出被映射到一组字符 A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持 A-Za-z0-9+/。
        String encode = java.util.Base64.getEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8));
        System.out.println("encode: " + encode + "\n");

        byte[] bytes = java.util.Base64.getDecoder().decode(encode);
        System.out.println("decode: " + new String(bytes, StandardCharsets.UTF_8) + "\n");

        // URL：输出映射到一组字符 A-Za-z0-9+_，输出是 URL 和文件。
        encode = java.util.Base64.getUrlEncoder().encodeToString("TutorialsPoint?java8".getBytes(StandardCharsets.UTF_8));
        System.out.println("encodeUrl: " + encode + "\n");

        // MIME：输出隐射到 MIME 友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            sb.append(UUID.randomUUID().toString());
        }
        bytes = sb.toString().getBytes(StandardCharsets.UTF_8);
        encode = java.util.Base64.getMimeEncoder().encodeToString(bytes);
        System.out.println("encodeMime:\n" + encode + "\n");

    }

    @Test
    public void sunMiscBase64() {
        try {
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(src.getBytes());
            System.out.println("encode:" + encode);

            BASE64Decoder decoder = new BASE64Decoder();
            System.out.println("decode:" + new String(decoder.decodeBuffer(encode)) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commonsCodecBase64() {
        byte[] encodeBytes = Base64.encodeBase64(src.getBytes());
        System.out.println("encode:" + new String(encodeBytes));

        byte[] decodeBytes = Base64.decodeBase64(encodeBytes);
        System.out.println("decode:" + new String(decodeBytes) + "\n");
    }

    @Test
    public void bouncyCastleBase64() {
        byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
        System.out.println("encode:" + new String(encodeBytes));

        byte[] decodeBytes = org.bouncycastle.util.encoders.Base64.decode(encodeBytes);
        System.out.println("decode:" + new String(decodeBytes) + "\n");
    }

}
