package jar.hutool.crypto;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import l.demo.Demo;

import javax.crypto.SecretKey;

/**
 * <a href="https://doc.hutool.cn/pages/HMac/">HMac</a>  消息认证码算法
 * <p>HmacAlgorithm: HmacMD5, HmacSHA1, HmacSHA256, HmacSHA384, HmacSHA512, HmacSM3
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/crypto/digest/HMac.html">HMac api</a>
 *
 * @author ljh
 * @since 2021/7/5 10:47
 */
public class HMacDemo extends Demo {

    public static void main(String[] args) {
        // static SecretKey	    generateKey(String algorithm[, byte[] key/int keySize/KeySpec keySpec])
        // 生成 SecretKey，仅用于对称加密和摘要算法
        SecretKey secretKey = SecureUtil.generateKey(HmacAlgorithm.HmacMD5.getValue());

        HMac hMac = new HMac(HmacAlgorithm.HmacMD5, secretKey);
        p(hMac.digestHex(HELLO_WORLD)); // 5454f8afe497bc8bc050945389d78c63
    }
}
