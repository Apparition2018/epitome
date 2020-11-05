package jar.hutool;

import cn.hutool.core.codec.Base32;
import cn.hutool.core.codec.Base62;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Console;
import l.demo.Demo;
import org.junit.Test;

/**
 * Base
 *
 * @author ljh
 * created on 2020/11/5 14:11
 */
public class BaseDemo extends Demo {
    
    private String encode;
    private String decode;

    /**
     * Base64
     * BASE64 使用了 64 个字符，包括 A-Za-z0-9+/
     * https://hutool.cn/docs/#/core/Codec%E7%BC%96%E7%A0%81/Base64%E7%BC%96%E7%A0%81%E8%A7%A3%E7%A0%81-Base64
     */
    @Test
    public void testBase64() {
        encode = Base64.encode(BAIDU_URL);
        decode = Base64.decodeStr(encode);
        Console.log("decode: {}, encode: {}", decode, encode);
        // decode: https://www.baidu.com, encode: aHR0cHM6Ly93d3cuYmFpZHUuY29t
    }

    /**
     * Base62
     * BASE62 使用了 62 个字符，包括 A-Za-z0-9，因为 +/ 在一些场景下代表着特殊含义
     * 多用于安全领域和短 URL 生成
     * https://hutool.cn/docs/#/core/Codec%E7%BC%96%E7%A0%81/Base62%E7%BC%96%E7%A0%81%E8%A7%A3%E7%A0%81-Base62
     */
    @Test
    public void testBase62() {
        encode = Base62.encode(BAIDU_URL);
        decode = Base62.decodeStr(encode);
        Console.log("decode: {}, encode: {}", decode, encode);
        // decode: https://www.baidu.com, encode: zXR31qQWHJ7mRxsRmpKrBHCC8inV
    }

    /**
     * Base32
     * BASE32 使用了 32 个字符，包括 A-Z2-7
     * https://hutool.cn/docs/#/core/Codec%E7%BC%96%E7%A0%81/Base32%E7%BC%96%E7%A0%81%E8%A7%A3%E7%A0%81-Base32
     */
    @Test
    public void testBase32() {
        encode = Base32.encode(BAIDU_URL);
        decode = Base32.decodeStr(encode);
        Console.log("decode: {}, encode: {}", decode, encode);
        // decode: https://www.baidu.com, encode: NB2HI4DTHIXS653XO4XGEYLJMR2S4Y3PNU
    }
    
}
