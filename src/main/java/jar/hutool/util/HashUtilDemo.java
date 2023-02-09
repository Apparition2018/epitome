package jar.hutool.util;

import cn.hutool.core.util.HashUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://hutool.cn/docs/#/core/工具类/Hash算法-HashUtil">HashUtil</a>     Hash算法
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/HashUtil.html">HashUtil api</a>
 *
 * @author ljh
 * @since 2020/11/19 10:22
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
