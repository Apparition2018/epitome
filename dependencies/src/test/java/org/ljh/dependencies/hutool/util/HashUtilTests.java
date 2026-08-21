package org.ljh.dependencies.hutool.util;

import cn.hutool.core.util.HashUtil;
import org.junit.jupiter.api.Test;

import static com.ljh.commons.Demo.HELLO_WORLD;
import static com.ljh.commons.Demo.p;

/**
 * <a href="https://doc.hutool.cn/pages/HashUtil/">HashUtil</a> Hash算法
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/util/HashUtil.html">HashUtil api</a>
 *
 * @author ljh
 * @since 2020/11/19 10:22
 */
public class HashUtilTests {

    @Test
    public void test() {
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
