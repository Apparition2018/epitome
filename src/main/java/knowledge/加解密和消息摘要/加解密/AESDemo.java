package knowledge.加解密和消息摘要.加解密;

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
 * <p>
 * 常见的有 DES, 3DES, AES, PBE 算法，安全性一次递增
 * <p>
 * 优点：
 * 速度快，常在消息发送方需要加密大量数据时使用，算法公开，计算量小，加密速度快，加密效率高
 * <p>
 * 缺点：
 * 在数据传送前，发送方和接收方必须商定好密钥，然后双方都能保存好密码要。
 * 其次如果一方的密钥被泄露，那么加密信息也就不安全了。
 * 另外，每对用户每次使用对称加密算法时，都需要使用其他人不知道的唯一密钥，
 * 这会使得收、发双方所拥有的密钥数量巨大，密钥管理成为双方的负担。
 */
public class AESDemo {

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

            byte[] byteCotent = content.getBytes(StandardCharsets.UTF_8);

            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));

            // 加密
            byte[] result = cipher.doFinal(byteCotent);

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
