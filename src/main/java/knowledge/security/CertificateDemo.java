package knowledge.security;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Signature;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.*;
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;
import java.util.Objects;

/**
 * 数字证书
 * <p>生成 .keysotre 存储密钥对：
 * <pre>
 * keytool -genkeypair -validity 1 -alias ljh -keyalg RSA -keystore C:\Users\Administrator\Desktop\ljh.keystore
 * 口令：123123
 * CN=ljh, OU=ljh, O=ljh, L=zs, ST=gd, C=cn
 * </pre>
 * 导出证书：
 * <pre>
 * keytool -exportcert -alias ljh -keystore C:\Users\Administrator\Desktop\ljh.keystore -file C:\Users\Administrator\Desktop\ljh.crt -rfc
 * </pre>
 * 导入证书：
 * <pre>
 * keytool -importcert -alias ljh2 -file C:\Users\Administrator\Desktop\ljh.crt -keystore C:\Users\Administrator\Desktop\ljh2.keystore
 * </pre>
 * 转换 (jks → PKCS12)
 * <pre>
 * keytool -importkeystore -srckeystore C:\Users\Administrator\Desktop\ljh.keystore -destkeystore C:\Users\Administrator\Desktop\ljh.pfx -deststoretype pkcs12
 * </pre>
 * 列出 .keystore：
 * <pre>
 * keytool -list -keystore C:\Users\Administrator\Desktop\ljh.pfx -rfc
 * </pre>
 *
 * @author ljh
 * @see <a href="https://www.iteye.com/blog/snowolf-391931">Java 加密技术（八）——数字证书</a>
 * @see <a href="https://blog.csdn.net/meng564764406/article/details/79156559">数字证书基本知识总结</a>
 * @see <a href="https://blog.csdn.net/meng564764406/article/details/79427687">Keytool 或 Keystore 使用及证书转换</a>
 * @since 2021/7/7 17:09
 */
public class CertificateDemo extends Demo {

    private static final String KEY_PATH = DESKTOP + "ljh.pfx";
    private static final String KEY_PASSWORD = "123123";
    private static final String KEY_TYPE_JKS = "JKS";
    private static final String KEY_TYPE_PKCS12 = "PKCS12";
    private static final String CERT_PATH = DESKTOP + "ljh.crt";
    private static final String CERT_TYPE_X509 = "X.509";
    private static final String SIGN_ALGO = "SHA1withRSA";

    @Test
    public void testCertificate() throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, UnrecoverableKeyException, SignatureException, InvalidKeyException {
        KeyStore keyStore = getKeyStore(KEY_TYPE_PKCS12, KEY_PATH, KEY_PASSWORD);
        PrivateKey privateKey = getPrivateKey(keyStore, null, KEY_PASSWORD);
        Certificate certificate = getCertificate(keyStore, null);
        String sign = sign(HELLO_WORLD, certificate, SIGN_ALGO, privateKey);
        p(verify(HELLO_WORLD, sign, certificate, SIGN_ALGO, getPublicKey(certificate)));
    }

    @Test
    public void testCrypto() throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, UnrecoverableKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        KeyStore keyStore = getKeyStore(KEY_TYPE_PKCS12, KEY_PATH, KEY_PASSWORD);

        // 私钥加密
        byte[] encryptData = encryptPrivateKey(HELLO_WORLD.getBytes(StandardCharsets.UTF_8), getPrivateKey(keyStore, null, KEY_PASSWORD));
        // 公钥解密
        p(new String(decryptPublicKey(encryptData, getPublicKey(getCertificate(keyStore, null))), StandardCharsets.UTF_8));
    }

    /**
     * 获取 KeyStore
     */
    public static KeyStore getKeyStore(String keyType, String keyPath, String password) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance(Objects.requireNonNullElse(keyType, KEY_TYPE_JKS));
        try (FileInputStream is = new FileInputStream(keyPath)) {
            keyStore.load(is, password.toCharArray());
        }
        return keyStore;
    }

    /**
     * 获取私钥
     */
    public static PrivateKey getPrivateKey(KeyStore keyStore, String alias, String password) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        if (alias == null) {
            Enumeration<String> aliases = keyStore.aliases();
            if (aliases.hasMoreElements()) {
                alias = aliases.nextElement();
            }
        }
        return (PrivateKey) keyStore.getKey(alias, password.toCharArray());
    }

    public static PublicKey getPublicKey(Certificate certificate) {
        return certificate.getPublicKey();
    }

    /**
     * 获取证书（从 KeyStore）
     */
    public static Certificate getCertificate(KeyStore keyStore, String alias) throws KeyStoreException {
        if (alias == null) {
            Enumeration<String> aliases = keyStore.aliases();
            if (aliases.hasMoreElements()) {
                alias = aliases.nextElement();
            }
        }
        return keyStore.getCertificate(alias);
    }

    /**
     * 获取证书（根据路径）
     */
    public static Certificate getCertificate(String certPath) throws CertificateException, IOException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance(CERT_TYPE_X509);
        try (FileInputStream is = new FileInputStream(certPath)) {
            return certificateFactory.generateCertificate(is);
        }
    }

    /**
     * 验证证书是否过期或无效
     */
    public static boolean verifyCertificate(Date date, Certificate certificate) {
        boolean status = true;
        try {
            X509Certificate x509Certificate = (X509Certificate) certificate;
            x509Certificate.checkValidity(date);
        } catch (CertificateExpiredException | CertificateNotYetValidException e) {
            status = false;
        }
        return status;
    }

    /**
     * 签名
     */
    public static String sign(String data, Certificate certificate, String signAlgo, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        if (certificate != null) {
            signAlgo = getSignAlgo(certificate);
        }
        java.security.Signature signature = java.security.Signature.getInstance(signAlgo);
        signature.initSign(privateKey);
        signature.update(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /**
     * 验签
     */
    public boolean verify(String data, String sign, Certificate certificate, String signAlgo, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        if (certificate != null) {
            signAlgo = getSignAlgo(certificate);
        }
        java.security.Signature signature = Signature.getInstance(signAlgo);
        signature.initVerify(publicKey);
        signature.update(data.getBytes(StandardCharsets.UTF_8));
        return signature.verify(Base64.getDecoder().decode(sign));
    }

    /**
     * 获取签名算法
     */
    public static String getSignAlgo(Certificate certificate) {
        return ((X509Certificate) certificate).getSigAlgName();
    }

    /**
     * 私钥加密
     */
    public static byte[] encryptPrivateKey(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     */
    public static byte[] decryptPrivateKey(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     */
    public static byte[] encryptPublicKey(byte[] data, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     */
    public static byte[] decryptPublicKey(byte[] data, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }
}
