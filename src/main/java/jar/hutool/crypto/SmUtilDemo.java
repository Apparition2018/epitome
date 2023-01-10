package jar.hutool.crypto;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.symmetric.SM4;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;

/**
 * <a href="https://hutool.cn/docs/#/crypto/国密算法工具-SmUtil">SmUtil</a>   国密算法工具
 * <p>需要 Bouncy Castle
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/crypto/SmUtil.html">SmUtil api</a>
 *
 * @author ljh
 * @since 2020/11/9 3:06
 */
public class SmUtilDemo extends Demo {

    /**
     * SM3: 摘要签名
     */
    @Test
    public void testSM3() {
        p(SmUtil.sm3(HELLO_WORLD));
    }

    /**
     * SM4: 对称加密
     *
     * @see <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/crypto/symmetric/SM4.html">SM4 api</a>
     */
    @Test
    public void testSM4() {
        SM4 sm4 = SmUtil.sm4();
        String encryptHex = sm4.encryptHex(HELLO_WORLD);
        p(encryptHex); // f9b21c6edb40962c3f8eda7c0cb8b20a
        String decryptStr = sm4.decryptStr(encryptHex, StandardCharsets.UTF_8);
        p(decryptStr); // Hello World!
    }


    /**
     * SM2: 非对称加密和签名
     *
     * @see <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/crypto/asymmetric/AbstractAsymmetricCrypto.html">AbstractAsymmetricCrypto api</a>
     * @see <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/crypto/asymmetric/SM2.html">SM2 api</a>
     */
    static class SM2Demo {

        /**
         * 随机生成密钥对
         */
        @Test
        public void testRandom() {
            SM2 sm2 = SmUtil.sm2();
            // 公钥加密
            String encryptStr = sm2.encryptBcd(HELLO_WORLD, KeyType.PublicKey);
            // 私钥解密
            String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        }

        /**
         * 自助生成密钥对
         */
        @Test
        public void testGenerateKeyPair() {
            // static KeyPair	    generateKeyPair(String algorithm)
            // 生成用于非对称加密的公钥和私钥，仅用于非对称加密
            KeyPair keyPair = SecureUtil.generateKeyPair("SM2");
            byte[] privateKey = keyPair.getPrivate().getEncoded();
            byte[] publicKey = keyPair.getPublic().getEncoded();

            SM2 sm2 = SmUtil.sm2(privateKey, publicKey);
            String encryptStr = sm2.encryptBcd(HELLO_WORLD, KeyType.PublicKey);
            String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        }

        /**
         * 签名和验签
         */
        @Test
        public void testSign() {
            if (randomBoolean()) {
                // 随机生成密钥对
                SM2 sm2 = SmUtil.sm2();
                byte[] bytes = sm2.sign(HELLO_WORLD.getBytes(StandardCharsets.UTF_8));
                p(sm2.verify(HELLO_WORLD.getBytes(StandardCharsets.UTF_8), bytes));
            } else {
                // 自助生成密钥对
                KeyPair keyPair = SecureUtil.generateKeyPair("SM2");
                SM2 sm2 = new SM2(keyPair.getPrivate(), keyPair.getPublic());
                String sign = sm2.signHex(HexUtil.encodeHexStr(HELLO_WORLD));
                p(sm2.verifyHex(HELLO_WORLD, HexUtil.encodeHexStr(sign)));
            }
        }
    }
}
