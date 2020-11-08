package jar.hutool.crypto;

import cn.hutool.crypto.digest.DigestUtil;
import l.demo.Demo;
import org.junit.Test;

/**
 * DigestUtil   消息摘要
 * https://hutool.cn/docs/#/crypto/%E6%91%98%E8%A6%81%E5%8A%A0%E5%AF%86-Digester
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/crypto/digest/DigestUtil.html
 *
 * @author Arsenal
 * created on 2020/11/9 2:07
 */
public class DigestUtilDemo extends Demo {

    @Test
    public void testDigester() {
        // bcrypt
        String hashed = DigestUtil.bcrypt(HELLO_WORLD);
        p(DigestUtil.bcryptCheck(HELLO_WORLD, hashed));
        
        // DigestAlgorithm: MD2, MD5, SHA-1, SHA-256, SHA-384, SHA-512
        p(DigestUtil.md5Hex(HELLO_WORLD));      // ed076287532e86365e841e92bfc50d8c
        p(DigestUtil.sha256Hex(HELLO_WORLD));   // 7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069
        
        // 国密算法：https://zhuanlan.zhihu.com/p/132352160
        // 需要 Bouncy Castle
        p(DigestUtil.digester("sm3").digestHex(HELLO_WORLD)); // 0ac0a9fef0d212aa76a3c431f793853ce145659ca1d14b114e96c1215cf26582
    }
}
