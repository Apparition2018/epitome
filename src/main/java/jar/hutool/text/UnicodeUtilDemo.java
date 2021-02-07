package jar.hutool.text;

import cn.hutool.core.text.UnicodeUtil;
import org.junit.jupiter.api.Test;

/**
 * UnicodeUtil  Unicode 编码转换工具
 * 针对类似于 \\u4e2d\\u6587 这类 Unicode 字符做一些特殊转换
 * https://hutool.cn/docs/#/core/%E6%96%87%E6%9C%AC%E6%93%8D%E4%BD%9C/Unicode%E7%BC%96%E7%A0%81%E8%BD%AC%E6%8D%A2%E5%B7%A5%E5%85%B7-UnicodeUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/text/UnicodeUtil.html
 *
 * @author ljh
 * created on 2020/11/5 10:16
 */
public class UnicodeUtilDemo {
    
    @Test
    public void testUnicodeUtil() {
        // String → Unicode
        // static String    toUnicode(String str[, boolean isSkipAscii])
        String unicode = UnicodeUtil.toUnicode("aaa123中文", true);
        System.out.println(unicode);    // aaa123\u4e2d\u6587
        
        // Unicode → String
        String str = UnicodeUtil.toString(unicode);
        System.out.println(str);        // aaa123中文
    }
}
