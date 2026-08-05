package jar.hutool.text;

import cn.hutool.core.text.UnicodeUtil;

/**
 * <a href="https://doc.hutool.cn/pages/UnicodeUtil/">UnicodeUtil</a>   Unicode 编码转换工具
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/text/UnicodeUtil.html">UnicodeUtil api</a>
 * <p>针对类似于 \\u4e2d\\u6587 这类 Unicode 字符做一些特殊转换
 *
 * @author ljh
 * @since 2020/11/5 10:16
 */
public class UnicodeUtilDemo {

    public static void main(String[] args) {
        // String → Unicode
        // static String    toUnicode(String str[, boolean isSkipAscii])
        String unicode = UnicodeUtil.toUnicode("aaa123中文", true);
        System.out.println(unicode);    // aaa123\u4e2d\u6587

        // Unicode → String
        String str = UnicodeUtil.toString(unicode);
        System.out.println(str);        // aaa123中文
    }
}
