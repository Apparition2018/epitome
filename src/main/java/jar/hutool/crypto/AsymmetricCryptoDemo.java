package jar.hutool.crypto;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import l.demo.Demo;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;

/**
 * <a href="https://doc.hutool.cn/pages/AsymmetricCrypto/">AsymmetricCrypto</a> 非对称加密
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/crypto/asymmetric/AsymmetricCrypto.html">AsymmetricCrypto api</a>
 *
 * @author ljh
 * @since 2020/11/9 9:43
 */
public class AsymmetricCryptoDemo extends Demo {

    /**
     * AsymmetricAlgorithm：
     * <pre>
     * RSA
     * RSA_ECB_PKCS1（RSA/ECB/PKCS1Padding）
     * RSA_None（RSA/None/NoPadding）
     * ECIES（需要 Bouncy Castle）
     * </pre>
     */
    public static void main(String[] args) {
        AsymmetricCrypto rsa;

        // 自助生成密钥对
        KeyPair keyPair = SecureUtil.generateKeyPair(AsymmetricAlgorithm.RSA.getValue());

        switch (randomInt(1, 4)) {
            case 1: // 随机生成密钥对
                if (randomBoolean()) {
                    rsa = new RSA();
                } else {
                    rsa = SecureUtil.rsa();
                }
                break;
            case 2:
                if (randomBoolean()) {
                    rsa = new RSA(keyPair.getPrivate(), keyPair.getPublic());
                } else {
                    rsa = SecureUtil.rsa(keyPair.getPrivate().getEncoded(), keyPair.getPublic().getEncoded());
                }
                break;
            default:
                rsa = new AsymmetricCrypto(AsymmetricAlgorithm.RSA, keyPair.getPrivate(), keyPair.getPublic());
        }

        // 公钥加密，私钥解密
        String encryptHex = rsa.encryptHex(HELLO_WORLD.getBytes(StandardCharsets.UTF_8), KeyType.PublicKey);
        p(rsa.decryptStr(encryptHex, KeyType.PrivateKey, StandardCharsets.UTF_8));

        // 私钥加密，公钥解密
        String encryptHex2 = rsa.encryptHex(HELLO_WORLD.getBytes(StandardCharsets.UTF_8), KeyType.PrivateKey);
        p(rsa.decryptStr(encryptHex2, KeyType.PublicKey, StandardCharsets.UTF_8));
    }
}
