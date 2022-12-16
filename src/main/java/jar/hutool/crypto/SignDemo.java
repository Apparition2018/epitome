package jar.hutool.crypto;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * Sign 签名和验证
 * https://hutool.cn/docs/#/crypto/%E7%AD%BE%E5%90%8D%E5%92%8C%E9%AA%8C%E8%AF%81-Sign
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/crypto/asymmetric/Sign.html
 * SignAlgorithm: MD5withRSA, SHA256withRSA, SHA1withDSA, SHA256withECDSA ......
 *
 * @author ljh
 * @since 2021/7/5 10:51
 */
public class SignDemo extends Demo {

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
