package jar.hutool.crypto;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

/**
 * SecureUtil   加密解密工具
 * https://hutool.cn/docs/#/crypto/%E5%8A%A0%E5%AF%86%E8%A7%A3%E5%AF%86%E5%B7%A5%E5%85%B7-SecureUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/crypto/SecureUtil.html
 *
 * @author Arsenal
 * created on 2020/11/9 2:16
 */
public class SecureUtilDemo extends Demo {

    private static final String KEY_PATH = DESKTOP + "ljh.pfx";
    private static final String KEY_PASSWORD = "123456";
    private static final String KEY_TYPE_PKCS12 = "PKCS12";
    private static final String KEY_ALIAS = "ljh";
    private static final String CERT_PATH = DESKTOP + "ljh.cer";

    @Test
    public void testCertificate() throws FileNotFoundException, KeyStoreException {
        // 获取 KeyStore
        KeyStore keyStore = SecureUtil.readKeyStore(KEY_TYPE_PKCS12, new FileInputStream(KEY_PATH), KEY_PASSWORD.toCharArray());
        // 获取 Certificate
        Certificate certificate = SecureUtil.readX509Certificate(new FileInputStream(CERT_PATH));

        // 获取私钥
        PrivateKey privateKey = SecureUtil.generatePrivateKey(keyStore, KEY_ALIAS, KEY_PASSWORD.toCharArray());
        // 获取公钥
        PublicKey publicKey = certificate.getPublicKey();

        // 签名
        Sign sign = SecureUtil.sign(SignAlgorithm.SHA1withRSA, privateKey.getEncoded(), publicKey.getEncoded());
        byte[] signBytes = sign.sign(HELLO_WORLD, StandardCharsets.UTF_8);

        // 验签
        p(sign.verify(HELLO_WORLD.getBytes(StandardCharsets.UTF_8), signBytes));
    }

}
