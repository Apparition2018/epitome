package org.ljh.dependencies.hutool.crypto;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static com.ljh.commons.Demo.HELLO_WORLD;

/**
 * <a href="https://doc.hutool.cn/pages/SymmetricCrypto/">SymmetricCrypto</a>  对称加密
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/crypto/symmetric/SymmetricCrypto.html">SymmetricCrypto api</a>
 *
 * @author ljh
 * @since 2020/11/9 9:30
 */
public class SymmetricCryptoTests {

    /**
     * SymmetricAlgorithm：
     * <pre>
     * AES (默认AES/ECB/PKCS5Padding)
     * ARCFOUR
     * Blowfish
     * DES (默认DES/ECB/PKCS5Padding)
     * DESede
     * RC2
     * PBEWithMD5AndDES
     * PBEWithSHA1AndDESede
     * PBEWithSHA1AndRC2_40
     * </pre>
     */
    @Test
    public void test() {
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        // 构建 SymmetricCrypto
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        // 加密
        String encryptHex = aes.encryptHex(HELLO_WORLD);
        // 解密
        System.out.println(aes.decryptStr(encryptHex, StandardCharsets.UTF_8));
    }
}
