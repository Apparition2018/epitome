package jar.hutool.util;

import cn.hutool.core.util.HashUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * HashUtil     Hash算法
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/Hash%E7%AE%97%E6%B3%95-HashUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/HashUtil.html
 *
 * @author ljh
 * created on 2020/11/19 10:22
 */
public class HashUtilDemo extends Demo {
    
    @Test
    public void testHashUtil() {
        // Java 默认 Hash
        p(HashUtil.javaDefaultHash(HELLO_WORLD)); 
        // 一次一个 Hash
        p(HashUtil.oneByOneHash(HELLO_WORLD));
        // Bernstein's Hash
        p(HashUtil.bernstein(HELLO_WORLD));
        // 改进的32位 FNV Hash
        p(HashUtil.fnvHash(HELLO_WORLD));
        // ......
    }
}
