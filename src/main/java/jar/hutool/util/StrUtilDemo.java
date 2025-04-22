package jar.hutool.util;

import cn.hutool.core.util.StrUtil;
import l.demo.Demo;

/**
 * <a href="https://doc.hutool.cn/pages/StrUtil/">StrUtil</a>
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/util/StrUtil.html">StrUtil api</a>
 * <pre>
 * String           COMMA                           ','
 * String           SLASH                           '/'
 * static boolean   isBlankIfStr(Object obj)        先判断是否为字符串，再判断是否为空白
 * </pre>
 *
 * @author ljh
 * @since 2020/10/27 14:25
 */
public class StrUtilDemo extends Demo {

    public static void main(String[] args) {
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
