package org.ljh.dependencies.hutool.util;

import cn.hutool.core.util.CharsetUtil;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.ljh.commons.Demo.DEMO_FILE_ABSOLUTE_PATH;
import static com.ljh.commons.Demo.p;

/**
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/util/CharsetUtil.html">CharsetUtil</a>
 *
 * @author ljh
 * @since 2020/11/22 3:16
 */
public class CharsetUtilTests {

    @Test
    public void test() {
        p(CharsetUtil.UTF_8);               // UTF-8
        p(CharsetUtil.CHARSET_UTF_8);       // UTF-8
        p(CharsetUtil.GBK);                 // GBK
        p(CharsetUtil.CHARSET_GBK);         // GBK
        p(CharsetUtil.ISO_8859_1);          // ISO-8859-1
        p(CharsetUtil.CHARSET_ISO_8859_1);  // ISO-8859-1

        p(CharsetUtil.systemCharsetName()); // GBK
        p(CharsetUtil.systemCharset());     // GBK
        p(CharsetUtil.defaultCharsetName());// UTF-8
        p(CharsetUtil.defaultCharset());    // UTF-8

        // 转换文件编码
        CharsetUtil.convert(new File(DEMO_FILE_ABSOLUTE_PATH), CharsetUtil.CHARSET_UTF_8, CharsetUtil.CHARSET_GBK);
        CharsetUtil.convert(new File(DEMO_FILE_ABSOLUTE_PATH), CharsetUtil.CHARSET_GBK, CharsetUtil.CHARSET_UTF_8);
    }
}
