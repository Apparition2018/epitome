package jar.hutool.util;

import cn.hutool.core.util.StrUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://hutool.cn/docs/#/core/工具类/字符串工具-StrUtil">StrUtil</a>
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/StrUtil.html">StrUtil api</a>
 * <pre>
 * C_COMMA          ','
 * C_SLASH          '/'
 *
 * static boolean   isBlankIfStr(Object obj)        先判断是否为字符串，再判断是否为空白
 * </pre>
 *
 * @author ljh
 * @since 2020/10/27 14:25
 */
public class StrUtilDemo extends Demo {

    @Test
    public void testStrUtil() {
        // builder
        p(StrUtil.builder("Hello ").append("World!"));  // Hello World!

        // removePrefix, removeSuffix
        p(StrUtil.removeSuffixIgnoreCase(BIRD_IMG, ".jpg"));

        // sub
        p(StrUtil.sub(HELLO_WORLD, 0, 5));              // Hello
        p(StrUtil.sub(HELLO_WORLD, 0, -1));             // Hello World，支持负数
        p(StrUtil.sub(HELLO_WORLD, -1, 0));             // Hello World，支持位置写反

        // format
        p(StrUtil.format("{}来自{}", "我", "中国"));     // 我来自中国
    }
}
