package jar.hutool.crypto;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

/**
 * <a href="https://hutool.cn/docs/#/crypto/加密解密工具-SecureUtil">SecureUtil</a>   加密解密工具
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/crypto/SecureUtil.html">SecureUtil api</a>
 *
 * @author ljh
 * @since 2020/11/9 2:16
 */
public class SecureUtilDemo extends Demo {

    private static final String KEY_PATH = DESKTOP + "ljh.pfx";
    private static final String KEY_PASSWORD = "123456";
    private static final String KEY_TYPE_PKCS12 = "PKCS12";
    private static final String KEY_ALIAS = MY_NAME;
    private static final String CERT_PATH = DESKTOP + "ljh.cer";

    @Test
    public void testCertificate() throws IOException, KeyStoreException {
        // 获取 KeyStore
        KeyStore keyStore = SecureUtil.readKeyStore(KEY_TYPE_PKCS12, Files.newInputStream(Paths.get(KEY_PATH)), KEY_PASSWORD.toCharArray());
        // 获取 Certificate
        Certificate certificate = SecureUtil.readX509Certificate(Files.newInputStream(Paths.get(CERT_PATH)));

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
