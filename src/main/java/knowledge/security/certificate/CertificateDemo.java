package knowledge.security.certificate;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.*;
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;

/**
 * 数字证书
 * <p>
 * Java 加密技术（八）——数字证书：https://www.iteye.com/blog/snowolf-391931
 * 数字证书基本知识总结：https://blog.csdn.net/meng564764406/article/details/79156559
 * Keytool 或 Keystore 使用及证书转换：https://blog.csdn.net/meng564764406/article/details/79427687
 * <p>
 * 证书生成：
 * keytool -genkey -validity 1 -alias ljh -keyalg RSA -keystore C:\Users\Administrator\Desktop\ljh.keystore
 * 口令：123456
 * CN=ljh, OU=ljh, O=ljh, L=zs, ST=gd, C=cn
 * <p>
 * 公钥证书导出：
 * keytool -export -keystore C:\Users\Administrator\Desktop\ljh.keystore -alias ljh -file C:\Users\Administrator\Desktop\ljh.cer -rfc
 * <p>
 * 证书转换，jks → PKCS12
 * keytool -importkeystore -srckeystore C:\Users\Administrator\Desktop\ljh.keystore -destkeystore C:\Users\Administrator\Desktop\ljh.pfx -deststoretype pkcs12
 * <p>
 * 证书查看：
 * keytool -list -keystore C:\Users\Administrator\Desktop\ljh.pfx -rfc
 *
 * @author ljh
 * created on 2021/7/7 17:09
 */
public class CertificateDemo extends Demo {

    private static final String KEY_PATH = DESKTOP + "ljh.pfx";
    private static final String KEY_PASSWORD = "123456";
    private static final String KEY_TYPE_JKS = "JKS";
    private static final String KEY_TYPE_PKCS12 = "PKCS12";
    private static final String CERT_PATH = DESKTOP + "ljh.cer";
    private static final String CERT_TYPE_X509 = "X.509";
    private static final String SIGN_ALGO = "SHA1withRSA";


    @Test
    public void test() throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, UnrecoverableKeyException, SignatureException, InvalidKeyException {
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
        p(new String(decryptPublicKey(encryptData, getPublicKey(getCertificate(keyStore, null)))));
    }

    /**
     * 获取 KeyStore
     */
    public static KeyStore getKeyStore(String keyType, String keyPath, String password) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore;
        if (keyType == null) {
            keyStore = KeyStore.getInstance(KEY_TYPE_JKS);
        } else {
            keyStore = KeyStore.getInstance(keyType);
        }
        FileInputStream is = new FileInputStream(keyPath);
        keyStore.load(is, password.toCharArray());
        is.close();
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
        FileInputStream is = new FileInputStream(certPath);
        Certificate certificate = certificateFactory.generateCertificate(is);
        is.close();
        return certificate;
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
        Signature signature = Signature.getInstance(signAlgo);
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
        Signature signature = Signature.getInstance(signAlgo);
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