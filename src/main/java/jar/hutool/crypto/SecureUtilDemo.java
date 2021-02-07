package jar.hutool.crypto;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

/**
 * SecureUtil   加密解密工具
 * https://hutool.cn/docs/#/crypto/%E5%8A%A0%E5%AF%86%E8%A7%A3%E5%AF%86%E5%B7%A5%E5%85%B7-SecureUtil?id=%e5%8a%a0%e5%af%86%e8%a7%a3%e5%af%86%e5%b7%a5%e5%85%b7-secureutil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/crypto/SecureUtil.html
 *
 * @author Arsenal
 * created on 2020/11/9 2:16
 */
public class SecureUtilDemo extends Demo {

    /**
     * HMac
     * https://hutool.cn/docs/#/crypto/%E6%B6%88%E6%81%AF%E8%AE%A4%E8%AF%81%E7%A0%81%E7%AE%97%E6%B3%95-HMac
     * https://apidoc.gitee.com/loolly/hutool/cn/hutool/crypto/digest/HMac.html
     * HmacAlgorithm: HmacMD5, HmacSHA1, HmacSHA256, HmacSHA384, HmacSHA512, HmacSM3
     */
    @Test
    public void testHMac() {
        // static SecretKey	    generateKey(String algorithm[, byte[] key/int keySize/KeySpec keySpec])
        // 生成 SecretKey，仅用于对称加密和摘要算法
        SecretKey secretKey = SecureUtil.generateKey(HmacAlgorithm.HmacMD5.getValue());
        HMac hMac = new HMac(HmacAlgorithm.HmacMD5, secretKey);
        p(hMac.digestHex(HELLO_WORLD)); // 5454f8afe497bc8bc050945389d78c63
    }

    /**
     * Sign 签名和验证
     * https://hutool.cn/docs/#/crypto/%E7%AD%BE%E5%90%8D%E5%92%8C%E9%AA%8C%E8%AF%81-Sign
     * https://apidoc.gitee.com/loolly/hutool/cn/hutool/crypto/asymmetric/Sign.html
     * SignAlgorithm: MD5withRSA, SHA256withRSA, SHA1withDSA, SHA256withECDSA ......
     */
    @Test
    public void testSign() {
        // static Sign	        sign(SignAlgorithm algorithm)
        // 创建签名算法对象，生成新的私钥公钥对
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
        // byte[]	            sign(byte[] data)
        // 用私钥对信息生成数字签名
        byte[] bytes = sign.sign(HELLO_WORLD.getBytes(StandardCharsets.UTF_8));
        // boolean	            verify(byte[] data, byte[] sign)
        // 用公钥检验数字签名的合法性
        p(sign.verify(HELLO_WORLD.getBytes(StandardCharsets.UTF_8), bytes));
    }
}
