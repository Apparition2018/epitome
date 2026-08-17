package jar.hutool.crypto;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;

import java.nio.charset.StandardCharsets;

import static com.ljh.commons.Demo.HELLO_WORLD;

/**
 * <a href="https://doc.hutool.cn/pages/Sign/">Sign</a> 签名和验证
 * <p>SignAlgorithm: MD5withRSA, SHA256withRSA, SHA1withDSA, SHA256withECDSA ......
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/crypto/asymmetric/Sign.html">Sign api</a>
 *
 * @author ljh
 * @since 2021/7/5 10:51
 */
public class SignDemo {

    public static void main(String[] args) {
        // static Sign	        sign(SignAlgorithm algorithm)
        // 创建签名算法对象，生成新的私钥公钥对
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
        // byte[]	            sign(byte[] data)
        // 用私钥对信息生成数字签名
        byte[] bytes = sign.sign(HELLO_WORLD.getBytes(StandardCharsets.UTF_8));
        // boolean	            verify(byte[] data, byte[] sign)
        // 用公钥检验数字签名的合法性
        System.out.println(sign.verify(HELLO_WORLD.getBytes(StandardCharsets.UTF_8), bytes));
    }
}
