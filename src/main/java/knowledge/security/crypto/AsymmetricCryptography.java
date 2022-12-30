package knowledge.security.crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 非对称加密 (Asymmetric Cryptography)
 * <pre>
 * 非对称加密算法需要两个密钥：公开密钥和私有密钥。
 * 加密：公开密钥对数据加密，私有密钥对数据解密。
 * 签名：私有密钥对数据加密，公开密钥对数据解密。
 * 一般公钥是公开的，私钥是自己保存。
 *
 * 字符串格式的密钥在未特殊说明的情况下都为 Base64 格式。
 * 由于非对称性加密速度极其慢，一般文件不使用它来加密而使用对称加密，
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就是保证了数据的安全
 * </pre>
 * 常见的非对称加密算法有：RSA, DSA(数字签名), ECC(移动设备), Diffie-Hellman, El Gamal
 * <p>参考：
 * <pre>
 * <a href="https://www.jianshu.com/p/aff5492d64f0">JAVA 加解密16-非对称加密算法- RSA 算法</a>
 * <a href="https://blog.csdn.net/a394268045/article/details/52232120">JAVA RSA 非对称加密详解</a>
 * <a href="https://blog.csdn.net/defonds/article/details/42775183">Java 进行 RSA 加解密时不得不考虑到的那些事儿</a>
 * <a href="https://www.ruanyifeng.com/blog/2011/08/what_is_a_digital_signature.html">数字签名是什么？ - 阮一峰</a>
 * <a href="https://www.cnblogs.com/jtlgb/p/6668691.html">RSA 公钥 私钥</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/11/18 19:37
 */
public class AsymmetricCryptography {
    /**
     * 非对称加密算法
     */
    private static final String KEY_ALGORITHM = "RSA";
    /**
     * 签名算法
     */
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    /**
     * RSA 最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * RSA 最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    /**
     * 密钥长度必须是 64 的倍数，在 512~65536 位之间
     * DH 算法的默认密钥长度是 1024
     */
    private static final int KEY_SIZE = 1024;
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    enum KEY {
        PUBLIC_KEY, PRIVATE_KEY
    }

    enum Crypto {
        ENCRYPT, DECRYPT
    }

    /**
     * 生成密钥对
     */
    public Map<String, Object> genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator 类用于生成公钥和私钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);

        // void	initialize(int keysize)
        // 初始化确定密钥大小的密钥对生成器，使用默认的参数集合，并使用以最高优先级安装的提供者的 SecureRandom 实现作为随机源
        keyPairGenerator.initialize(KEY_SIZE);

        // KeyPair	generateKeyPair()
        // 生成一个密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // PublicKey	getPublic()
        // 返回对此密钥对的公钥组件的引用
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // PrivateKey	getPrivate()
        // 返回对此密钥对的私钥组件的引用
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data             已加密数据
     * @param privateKeyBase64 私钥 (Base64)
     * @return 数字签名
     */
    public String sign(byte[] data, String privateKeyBase64) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        // String → byte[]
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyBase64);

        // 此类表示按照 ASN.1 类型 PrivateKeyInfo 进行编码的专用密钥的 ASN.1 编码
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

        // 密钥工厂用于将密钥（Key 类型的不透明加密密钥）转换成密钥规范（底层密钥材料的透明表示），反之亦然。
        // 密钥工厂是双向的。也就是说，它们允许根据给定的密钥规范（密钥材料）构建不透明的密钥对象，也允许获取以恰当格式表示的密钥对象的底层密钥材料。
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // PrivateKey	generatePrivate(KeySpec keySpec)
        // 根据提供的密钥规范（密钥材料）生成私钥对象
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // Signature 类用来为应用程序提供数字签名算法功能。
        // 数字签名用于确保数字数据的验证和完整性。
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);

        // void	        initSign(PrivateKey privateKey)
        // 初始化这个用于签名的对象
        signature.initSign(privateKey);

        // void	        update(byte[] data)
        // 使用指定的 byte 数组更新要签名或验证的数据
        signature.update(data);

        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /**
     * 校验数字签名
     *
     * @param data            已加密数据
     * @param publicKeyBase64 公钥 (Base64)
     * @param sign            数字签名
     * @return 是否通过验证
     */
    public static boolean verify(byte[] data, String publicKeyBase64, String sign) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        // byte[] → String
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyBase64);

        // 此类表示根据 ASN.1 类型 SubjectPublicKeyInfo 进行编码的公用密钥的 ASN.1 编码。
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance(String.valueOf(keySpec));

        // PublicKey	generatePublic(KeySpec keySpec)
        // 根据提供的密钥规范（密钥材料）生成公钥对象
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(data);

        return signature.verify(Base64.getDecoder().decode(sign));
    }

    /**
     * 加解密
     *
     * @param data    已加密数据/源数据
     * @param key     密钥 (Base64)
     * @param keyEnum 密钥枚举 (KEY.PRIVATE_KEY, KEY.PUBLIC_KEY)
     * @param crypto  加解密枚举 (Crypto.ENCRYPT, Crypto.DECRYPT)
     * @return 解密后数据/加密后数据
     */
    public static byte[] cryptoKey(byte[] data, byte[] key, KEY keyEnum, Crypto crypto) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException {
        Key k = null;

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        switch (keyEnum) {
            case PRIVATE_KEY:
                PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
                k = keyFactory.generatePrivate(pkcs8KeySpec);
                break;
            case PUBLIC_KEY:
                X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
                k = keyFactory.generatePublic(x509KeySpec);
                break;
            default:
                assert false : "Invalid Param";
        }

        // String	    getAlgorithm()
        // 获取与此 KeyFactory 关联的算法的名称
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

        int MAX_BLOCK = 0;

        switch (crypto) {
            case ENCRYPT:
                // void	init(int opmode, Key key)
                // 用密钥初始化此 Cipher
                cipher.init(Cipher.DECRYPT_MODE, k);
                MAX_BLOCK = MAX_ENCRYPT_BLOCK;
                break;
            case DECRYPT:
                cipher.init(Cipher.DECRYPT_MODE, k);
                MAX_BLOCK = MAX_DECRYPT_BLOCK;
                break;
            default:
                assert false : "Invalid Param";
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int inputLen = data.length;
        int offSet = 0;
        byte[] cache;
        int i = 0;

        // 对数据分段加解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_BLOCK) {
                // byte[]	doFinal(byte[] input, int inputOffset, int inputLen)
                // 按单部分操作加密或解密数据，或者结束一个多部分操作
                cache = cipher.doFinal(data, offSet, MAX_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            baos.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_BLOCK;
        }

        return baos.toByteArray();
    }

    /**
     * 获取密钥
     */
    public byte[] getKey(Map<String, Object> keyMap, KEY enumKey) {
        Key key = null;
        switch (enumKey) {
            case PRIVATE_KEY:
                key = (Key) keyMap.get(PRIVATE_KEY);
                break;
            case PUBLIC_KEY:
                key = (Key) keyMap.get(PUBLIC_KEY);
                break;
            default:
                assert false : "Invalid Param";
        }
        return key.getEncoded();
    }
}
