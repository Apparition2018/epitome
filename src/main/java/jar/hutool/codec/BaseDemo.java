package jar.hutool.codec;

import cn.hutool.core.codec.Base32;
import cn.hutool.core.codec.Base62;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Console;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * Base
 *
 * @author ljh
 * @since 2020/11/5 14:11
 */
public class BaseDemo extends Demo {

    private String encode;
    private String decode;

    /**
     * <a href="https://hutool.cn/docs/#/core/Codec编码/Base64编码解码-Base64">Base64</a>
     * <p>BASE64 使用了 64 个字符，包括 A-Za-z0-9+/
     *
     * @see <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/codec/Base64.html">Base64 api</a>
     */
    @Test
    public void testBase64() {
        encode = Base64.encode(BAIDU_URL);
        decode = Base64.decodeStr(encode);
        Console.log("decode: {}, encode: {}", decode, encode);
        // decode: https://www.baidu.com, encode: aHR0cHM6Ly93d3cuYmFpZHUuY29t
    }

    /**
     * <a href="https://hutool.cn/docs/#/core/Codec编码/Base62编码解码-Base62">Base62</a>
     * <pre>
     * BASE62 使用了 62 个字符，包括 A-Za-z0-9，因为 +/ 在一些场景下代表着特殊含义
     * 多用于安全领域和短 URL 生成
     * </pre>
     *
     * @see <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/codec/Base62.html">Base62 api</a>
     */
    @Test
    public void testBase62() {
        encode = Base62.encode(BAIDU_URL);
        decode = Base62.decodeStr(encode);
        Console.log("decode: {}, encode: {}", decode, encode);
        // decode: https://www.baidu.com, encode: zXR31qQWHJ7mRxsRmpKrBHCC8inV
    }

    /**
     * <a href="https://hutool.cn/docs/#/core/Codec编码/Base32编码解码-Base32">Base32</a>
     * <p>BASE32 使用了 32 个字符，包括 A-Z2-7
     *
     * @see <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/codec/Base32.html">Base32 api</a>
     */
    @Test
    public void testBase32() {
        encode = Base32.encode(BAIDU_URL);
        decode = Base32.decodeStr(encode);
        Console.log("decode: {}, encode: {}", decode, encode);
        // decode: https://www.baidu.com, encode: NB2HI4DTHIXS653XO4XGEYLJMR2S4Y3PNU
    }
}
