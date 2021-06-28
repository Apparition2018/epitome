package jar.hutool.crypto;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import l.demo.Demo;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.Random;

/**
 * AsymmetricCrypto 非对称加密
 * https://hutool.cn/docs/#/crypto/%E5%AF%B9%E7%A7%B0%E5%8A%A0%E5%AF%86-AsymmetricCrypto
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/crypto/symmetric/AsymmetricCrypto.html
 *
 * @author ljh
 * created on 2020/11/9 9:43
 */
public class AsymmetricCryptoDemo extends Demo {

    /**
     * AsymmetricAlgorithm:
     * RSA
     * RSA_ECB_PKCS1（RSA/ECB/PKCS1Padding）
     * RSA_None（RSA/None/NoPadding）
     * ECIES（需要 Bouncy Castle）
     */
    public static void testAsymmetricCrypto(AsymmetricAlgorithm asymmetricAlgorithm) {
        AsymmetricCrypto asymmetricCrypto;

        if (new Random().nextBoolean()) {
            // 随机生成密钥对
            asymmetricCrypto = new RSA();
        } else {
            // 自助生成密钥对
            KeyPair keyPair = SecureUtil.generateKeyPair(asymmetricAlgorithm.getValue());
            asymmetricCrypto = new AsymmetricCrypto(asymmetricAlgorithm,
                    keyPair.getPrivate(), keyPair.getPublic());
        }

        // 公钥加密，私钥解密
        String encryptHex = asymmetricCrypto.encryptHex(HELLO_WORLD.getBytes(StandardCharsets.UTF_8), KeyType.PublicKey);
        String decryptStr = asymmetricCrypto.decryptStr(encryptHex, KeyType.PrivateKey);

        // 私钥加密，公钥解密
        String encryptHex2 = asymmetricCrypto.encryptHex(HELLO_WORLD.getBytes(StandardCharsets.UTF_8), KeyType.PrivateKey);
        String decryptStr2 = asymmetricCrypto.decryptStr(encryptHex, KeyType.PublicKey);
    }
}
