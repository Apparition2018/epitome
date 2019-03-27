package knowledge.加解密和消息摘要.加解密;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
 * https://www.cnblogs.com/frank-quan/p/7073457.html
 * https://blog.csdn.net/a394268045/article/details/52232120
 *
 * https://www.cnblogs.com/jtlgb/p/6668691.html
 * https://blog.csdn.net/vola9527/article/details/77715473
 * https://zhidao.baidu.com/question/179468914631666084.html
 * https://blog.csdn.net/ddonking/article/details/82492206
 * https://blog.csdn.net/defonds/article/details/42775183
 * https://www.cnblogs.com/zhaoyihao/p/5345784.html
 *
 *
 */
public class RSADemo2 {

    // 非对称加密算法 RSA
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 密钥长度，DH 算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 512;

    // 获取公钥的 key
    private static final String PUBLIC_KEY = "RSAPublicKey";

    // 获取私钥的 key
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 生成密钥对
     */
    private static Map<String, Object> initKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);

        keyPairGenerator.initialize(KEY_SIZE);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 私钥加密
     */
    private static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     */
    private static byte[] encryptByPublicKey(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     */
    private static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     */
    private static byte[] decryptByPublicKey(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pubKey);

        return cipher.doFinal(data);
    }

    /**
     * 取得私钥
     */
    private static byte[] getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 取得公钥
     */
    private static byte[] getPublicKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidKeySpecException, NoSuchPaddingException {
        // 生成密钥对
        Map<String, Object> keyMap = RSADemo2.initKey();

        // 公钥
        byte[] publicKey = RSADemo2.getPublicKey(keyMap);

        // 私钥
        byte[] privateKey = RSADemo2.getPrivateKey(keyMap);

        p("公钥：" + Base64.getEncoder().encodeToString(publicKey));
        p("私钥：" + Base64.getEncoder().encodeToString(privateKey));

        p("\n========== 密钥对构造完毕，甲方将公钥公布给乙方，开始进行加密数据的传输 ==========");
        String str = "RSA密钥交换算法";
        p("原文：" + str);

        p("\n========== 甲方向乙方发送加密数据 ==========");
        // 甲方进行数据的加密
        byte[] code1 = RSADemo2.encryptByPrivateKey(str.getBytes(), privateKey);
        p("甲方加密后的数据：" + Base64.getEncoder().encodeToString(code1));

        p("\n========== 乙方使用公钥对数据进行解密 ==========");
        // 乙方进行数据的解密
        byte[] decode1 = RSADemo2.decryptByPublicKey(code1, publicKey);
        p("乙方解密后的数据：" + new String(decode1));

        p("\n========== 进行反向操作，乙方向甲方发送数据 ==========");
        str = "乙方向甲方发送数据RSA算法";
        p("原文：" + str);

        p("\n========== 乙方向甲方发送加密数据 ==========");
        // 乙方进行数据的加密
        byte[] code2 = RSADemo2.encryptByPublicKey(str.getBytes(), publicKey);
        p("乙方加密后的数据：" + Base64.getEncoder().encodeToString(code2));

        p("\n========== 用甲使用私钥对数据进行解密 ==========");
        // 乙方进行数据的解密
        byte[] decode2 = RSADemo2.decryptByPrivateKey(code2, privateKey);
        p("甲方解密后的数据：" + new String(decode2));
    }

    public static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }
}
