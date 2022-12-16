package knowledge.security.crypto;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 对称加密 (Symmetric Cryptography)
 * 加密和解密使用相同密钥的算法
 * 密钥越大，越难被黑客破解，但加解密效率越慢。
 * <p>
 * 常见的对称加密算法有：
 * AES, ARCFOUR, Blowfish, DES, DESX, 3DES, DESede, IDEA,
 * PBEWithMD5AndDES, PBEWithSHA1AndDESede, PBEWithSHA1AndRC2_40, PBE,
 * RC2, RC4, RC5, RC6
 * <p>
 * 优点：速度快，常在消息发送方需要加密大量数据时使用，算法公开，计算量小，加密速度快，加密效率高
 * <p>
 * 缺点：密钥的分配与管理
 * 1.密钥在保管和传送时被泄露，那么加密信息也就不安全了。一般将对称加密的密钥进行非对称加密，然后传送给需要它的人。
 * 2.如果密钥被多个用户共享，不能提供身份验证（抗抵赖性）
 * 3.N 个用户需要 N(N-1)/2 个共享密钥，当用户量增大，密钥空间急剧增大
 * <p>
 * 通俗解释对称加密、非对称加密、散列算法与PKI：https://blog.csdn.net/weixin_43853965/article/details/103870110
 * Java 对称加密与非对称加密：https://blog.csdn.net/chengbinbbs/article/details/78640589
 * 各种加密算法比较：https://www.cnblogs.com/sunxuchu/p/5483956.html
 * 加解密（Encryption）& 哈希（Hash）算法----入门指引 - 知乎：https://zhuanlan.zhihu.com/p/20064358
 *
 * @author ljh
 * @since 2020/11/18 19:37
 */
public class SymmetricCryptography {

    private static final String KEY_ALGORITHM = "AES";

    // 默认加密算法
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param key     加密密钥
     * @return Base64转码后的加密数据
     */
    private static String encrypt(String content, String key) {
        try {
            // Cipher 为加密和解密提供密码功能
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);

            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));

            // 加密
            byte[] result = cipher.doFinal(byteContent);

            // 通过 Base64 转码返回
            return Base64.encodeBase64String(result);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String decrypt(String content, String key) throws NoSuchPaddingException {
        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));

            // 执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));

            return new String(result, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 生成密钥
     */
    private static SecretKeySpec getSecretKey(final String key) {
        // 返回生成指定算法密钥生成器的 keyGenerator 对象
        KeyGenerator kg;

        try {
            // https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#KeyGenerator
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            // AES 要求密钥长度为128
            kg.init(128, new SecureRandom(key.getBytes()));

            // 生成一个密钥
            SecretKey secretKey = kg.generateKey();

            // 转换为 AES 专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) throws NoSuchPaddingException {
        String content = "Hello";
        String key = "sde@5f98H*^hsff%dfs$r344&df8543*er";
        String h = encrypt(content, key);
        System.out.println(h);
        System.out.println(decrypt(h, key));
    }

}
