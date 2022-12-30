package jar.hutool.crypto;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * <a href="https://hutool.cn/docs/#/crypto/签名和验证-Sign">Sign</a> 签名和验证
 * <p>SignAlgorithm: MD5withRSA, SHA256withRSA, SHA1withDSA, SHA256withECDSA ......
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/crypto/asymmetric/Sign.html">Sign api</a>
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
