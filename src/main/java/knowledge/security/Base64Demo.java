package knowledge.security;

import l.demo.Demo;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Base64.html">Base64</a><br/>
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Base64.Decoder.html">Base64.Decoder</a>
 * <pre>
 * BASE64 使用了 64 个字符，包括 A-Za-z0-9+/，其中 +/ 在一些场景下代表着特殊含义(如 url)，会用 -_ 代替
 * BASE64 严格地说，属于编码格式，而非加密算法。
 * BASE 加密后产生的字节位数是8的倍数，如果不够位数以 = 符号填充。
 * </pre>
 * 应用场景：e-mail、密钥、证书文件
 * <p>参考：
 * <pre>
 * <a href="https://www.cnblogs.com/chengmo/archive/2014/05/18/3735917.html">Base64 算法原理</a>
 * <a href="https://www.imooc.com/learn/285">Java 实现 Base64 加密</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/11/18 19:37
 */
public class Base64Demo extends Demo {

    /**
     * <a href="http://www.runoob.com/java/java8-base64.html">Base64</a>
     * <p>JDK8 引入，效率都比 apache-commons-codec, sun.misc, Bouncy Castle 快
     */
    public static void main(String[] args) {
        // 基本：输出被映射到一组字符 A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持 A-Za-z0-9+/。
        String encode = Base64.getEncoder().encodeToString(BAIDU_URL.getBytes(StandardCharsets.UTF_8));
        p("encode: " + encode);

        byte[] bytes = Base64.getDecoder().decode(encode);
        p("decode: " + new String(bytes, StandardCharsets.UTF_8));

        // URL：输出映射到一组字符 A-Za-z0-9+_，输出是 URL 和文件。
        encode = Base64.getUrlEncoder().encodeToString(BAIDU_URL.getBytes(StandardCharsets.UTF_8));
        p("encodeUrl: " + encode);

        // MIME：输出隐射到 MIME 友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。
        StringBuilder sb = new StringBuilder();
        IntStream.rangeClosed(1, 10).forEach(i -> sb.append(UUID.randomUUID()));
        bytes = sb.toString().getBytes(StandardCharsets.UTF_8);
        encode = Base64.getMimeEncoder().encodeToString(bytes);
        p("encodeMime:\n" + encode);
    }
}
