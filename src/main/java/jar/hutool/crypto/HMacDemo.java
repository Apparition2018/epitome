package jar.hutool.crypto;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

/**
 * HMac
 * https://hutool.cn/docs/#/crypto/%E6%B6%88%E6%81%AF%E8%AE%A4%E8%AF%81%E7%A0%81%E7%AE%97%E6%B3%95-HMac
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/crypto/digest/HMac.html
 * HmacAlgorithm: HmacMD5, HmacSHA1, HmacSHA256, HmacSHA384, HmacSHA512, HmacSM3
 *
 * @author ljh
 * @since 2021/7/5 10:47
 */
public class HMacDemo extends Demo {

    @Test
    public void testHMac() {
        // static SecretKey	    generateKey(String algorithm[, byte[] key/int keySize/KeySpec keySpec])
        // 生成 SecretKey，仅用于对称加密和摘要算法
        SecretKey secretKey = SecureUtil.generateKey(HmacAlgorithm.HmacMD5.getValue());
        
        HMac hMac = new HMac(HmacAlgorithm.HmacMD5, secretKey);
        p(hMac.digestHex(HELLO_WORLD)); // 5454f8afe497bc8bc050945389d78c63
    }
}
