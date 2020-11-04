package jar.hutool;

import cn.hutool.core.util.StrUtil;
import l.demo.Demo;
import org.junit.Test;

/**
 * StrUtilDemo
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E5%AD%97%E7%AC%A6%E4%B8%B2%E5%B7%A5%E5%85%B7-StrUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/util/StrUtil.html
 *
 * @author ljh
 * created on 2020/10/27 14:25
 */
public class StrUtilDemo extends Demo {

    @Test
    public void testStrUtil() {
        // builder
        p(StrUtil.builder("Hello ").append("World!"));  // Hello World!

        // null, empty, blank
        p(StrUtil.isBlank("  "));                       // true
        p(StrUtil.isEmpty("  "));                       // false
        p(StrUtil.hasBlank("1", "2", " "));             // true
        p(StrUtil.hasBlank("1", "2", " "));             // true

        // removePrefix, removeSuffix
        p(StrUtil.removeSuffixIgnoreCase(ARSENAL_LOGO, ".jpg"));

        // sub
        p(StrUtil.sub("Hello World!", 0, 5));           // Hello
        p(StrUtil.sub("Hello World!", 0, -1));          // Hello World，支持负数
        p(StrUtil.sub("Hello World!", -1, 0));          // Hello World，支持位置写反了

        // format
        p(StrUtil.format("{}来自{}", "我", "中国"));     // 我来自中国
    }
}
