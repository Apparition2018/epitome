package jar.hutool.util;

import cn.hutool.core.util.CharsetUtil;
import l.demo.Demo;

import java.io.File;

/**
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/CharsetUtil.html">CharsetUtil</a>
 *
 * @author ljh
 * @since 2020/11/22 3:16
 */
public class CharsetUtilDemo extends Demo {

    public static void main(String[] args) {
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
