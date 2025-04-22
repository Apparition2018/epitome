package jar.hutool.crypto;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://doc.hutool.cn/pages/Digester/">Digester</a> 摘要加密
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/crypto/digest/Digester.html">Digester api</a>
 * <p>
 * DigestUtil   摘要加密工具类
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/crypto/digest/DigestUtil.html">DigestUtil api</a>
 *
 * @author ljh
 * @since 2020/11/9 2:07
 */
public class DigesterDemo extends Demo {

    @Test
    public void testDigester() {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        p(md5.digestHex(HELLO_WORLD));          // ed076287532e86365e841e92bfc50d8c

        Digester sha256 = new Digester(DigestAlgorithm.SHA256);
        p(sha256.digestHex(HELLO_WORLD));       // 7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069
    }

    @Test
    public void testDigestUtil() {
        // bcrypt
        String bcrypt = DigestUtil.bcrypt(HELLO_WORLD);
        p(DigestUtil.bcryptCheck(HELLO_WORLD, bcrypt));

        // DigestAlgorithm: MD2, MD5, SHA-1, SHA-256, SHA-384, SHA-512
        p(DigestUtil.md5Hex(HELLO_WORLD));      // ed076287532e86365e841e92bfc50d8c
        p(DigestUtil.sha256Hex(HELLO_WORLD));   // 7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069

        // 国密算法：https://zhuanlan.zhihu.com/p/132352160
        // 需要 Bouncy Castle
        p(DigestUtil.digester("sm3").digestHex(HELLO_WORLD)); // 0ac0a9fef0d212aa76a3c431f793853ce145659ca1d14b114e96c1215cf26582
    }
}
