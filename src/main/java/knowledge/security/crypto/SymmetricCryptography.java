package knowledge.security.crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import static l.demo.Demo.HELLO_WORLD;

/**
 * 对称加密 (Symmetric Cryptography)
 * <pre>
 * 加密和解密使用相同密钥的算法
 * 密钥越大，越难被黑客破解，但加解密效率越慢。
 * </pre>
 * 常见的对称加密算法有：
 * <pre>
 * AES, ARCFOUR, Blowfish, DES, DESX, 3DES, DESede, IDEA,
 * PBEWithMD5AndDES, PBEWithSHA1AndDESede, PBEWithSHA1AndRC2_40, PBE,
 * RC2, RC4, RC5, RC6，ChaCha20-Poly1305
 * </pre>
 * 优点：速度快，常在消息发送方需要加密大量数据时使用，算法公开，计算量小，加密速度快，加密效率高<br/>
 * 缺点：密钥的分配与管理
 * <pre>
 * 1 密钥在保管和传送时被泄露，那么加密信息也就不安全了。一般将对称加密的密钥进行非对称加密，然后传送给需要它的人。
 * 2 如果密钥被多个用户共享，不能提供身份验证（抗抵赖性）
 * 3 N 个用户需要 N(N-1)/2 个共享密钥，当用户量增大，密钥空间急剧增大
 * </pre>
 *
 * @author ljh
 * @see <a href="https://blog.csdn.net/weixin_43853965/article/details/103870110">通俗解释对称加密、非对称加密、散列算法与 PKI</a>
 * @see <a href="https://blog.csdn.net/weixin_43219644/article/details/141385976">对称加密AES详解</a>
 * @see <a href="https://www.cnblogs.com/sunxuchu/p/5483956.html">各种加密算法比较</a>
 * @see <a href="https://zhuanlan.zhihu.com/p/20064358">加解密（Encryption）& 哈希（Hash）算法</a>
 * @since 2020/11/18 19:37
 */
public class SymmetricCryptography {

    private static final String SECURE_ALGORITHM = "SHA1PRNG";
    private static final String KEY_ALGORITHM = "AES";
    // AES 支持 128、192、256 位的密钥长度
    private static final int KEY_SIZE = 16 * 8;
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    private static SecretKeySpec getSecretKey(String seed) {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance(SECURE_ALGORITHM);
            secureRandom.setSeed(seed.getBytes(StandardCharsets.UTF_8));
            // https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#KeyGenerator
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
            keyGenerator.init(KEY_SIZE, secureRandom);
            return new SecretKeySpec(keyGenerator.generateKey().getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String encrypt(String content, String seed) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(seed));
            byte[] encryptedBytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String decrypt(String content, String seed) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(seed));
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(content));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws NoSuchPaddingException {
        String content = HELLO_WORLD;
        String seed = System.currentTimeMillis() + "";
        String encryptContent = encrypt(content, seed);
        System.err.printf("加密前：%s%n", content);
        System.err.printf("加密后：%s%n", encryptContent);
        System.err.printf("解密后：%s%n", decrypt(encryptContent, seed));
    }
}
