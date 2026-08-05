package knowledge.security.digest;

import l.demo.Demo;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

/**
 * <pre>
 * MAC (Message Authentication Code)，消息认证码，基于密钥的认证协议 (密钥 + 算法 → digest)
 * HMAC (keyed-Hash Message Authentication Code)，散列消息鉴别码，基于密钥的 Hash 算法的认证协议 (密钥 + Hash算法 → digest)
 * MAC 算法结合了 MD5 和 SHA 算法的优势，并加入密钥的支持，是一种更为安全的消息摘要算法。
 * </pre>
 * 原理：
 * <pre>
 * 用公开函数和密钥产生一个固定长度的值作为认证标识，用这个标识鉴别消息的完整性。
 * 使用一个密钥生成一个固定大小的小数据块，即 MAC，并将其加入到消息中，然后传输。
 * 接收方利用与发送方共享的密钥进行鉴别认证等。
 * </pre>
 * 应用场景一：
 * <pre>
 * 1 客户端向服务器发起请求，访问登录页面，这时服务器生成一个密钥，把这个密钥存储在 session 之中，然后将密码返回给客户端
 * 2 客户端填写登录表单，点击提交后，运行 HMAC 算法，根据密钥将用户信息加密后 post 到服务器
 * 3 服务器读取数据库中的密钥，用 HMAC 将密码和 session 中的密钥进行加密产生密码的密文，将密文与用户提交的进行比较
 * </pre>
 * 应用场景二：<a href="https://www.cnblogs.com/fishou/p/4206980.html">HMAC 结合"挑战/响应"保障数据传输安全</a>
 * <p>
 *
 * @author ljh
 * @see <a href="https://www.cnblogs.com/fishou/p/4159092.html">HMAC 的 JAVA 实现和应用</a>
 * @since 2020/11/18 19:37
 */
public class HMacDemo extends Demo {

    public static void main(String[] args) {
        String inputStr = HELLO_WORLD;
        p(Objects.equals(getResult1(inputStr), getResult2(inputStr)));
    }

    /**
     * MAC 算法：
     * <pre>
     * HmacMD2
     * HmacMD4
     * HmacMD5
     * HmacSHA1
     * HmacSHA224
     * HmacSHA256
     * HmacSHA384
     * HmacSHA512
     * </pre>
     */
    private static final String KEY_MAC = "HmacMD5";

    /**
     * 初始化 HMAC 密钥
     */
    private static String initMacKey() throws NoSuchAlgorithmException {
        // 得到一个 指定算法密钥的密钥生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

        // 生成一个密钥
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    /** HMAC 加密 */
    private static String encryptHMAC(byte[] data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        // 根据给定的字节数组构造一个密钥，第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), KEY_MAC);

        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());

        // 用密钥初始化 Mac 对象
        mac.init(secretKey);

        // 完成 Mac 操作
        return new String(mac.doFinal(data), StandardCharsets.UTF_8);
    }

    private static String getResult1(String inputStr) {
        Path keyFilePath = Paths.get(DEMO_DIR_PATH + "key");
        String result = null;
        try {
            byte[] inputData = inputStr.getBytes();
            String key = initMacKey(); // 生成密钥
            List<String> keyList = List.of(key);
            Files.write(keyFilePath, keyList, StandardOpenOption.CREATE); // 将密钥写入文件
            result = encryptHMAC(inputData, key); // HMAC 加密
        } catch (NoSuchAlgorithmException | IOException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static String getResult2(String inputStr) {
        Path keyFilePath = Paths.get(DEMO_DIR_PATH + "key");
        List<String> keyList;
        String result = null;
        try {
            // 从文件读取密钥
            keyList = Files.readAllLines(keyFilePath);
            byte[] inputData = inputStr.getBytes();
            result = encryptHMAC(inputData, keyList.get(0)); // HMAC 加密
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
