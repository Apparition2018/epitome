package jar.hutool.util;

import cn.hutool.core.util.CharUtil;
import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * CharUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/CharUtil.html
 *
 * @author ljh
 * @since 2020/11/22 3:27
 */
public class CharUtilDemo {

    @Test
    public void testCharUtil() {
        p(CharUtil.SLASH);      // /      
        p(CharUtil.BACKSLASH);  // \
        p(CharUtil.CR);         // \r
        p(CharUtil.LF);         // \n
        p(CharUtil.COMMA);      // ,
        p(CharUtil.COLON);      // :
        p(CharUtil.DASHED);     // -
        // ......
    }

    @Test
    public void is() {
        // ASCII
        p(CharUtil.isAscii('1'));                               // true
        // 可见 ASCII
        p(CharUtil.isAsciiPrintable('1'));                      // true
        // 不可见 ASCII
        p(CharUtil.isAsciiControl('\n'));                       // true
        // 空白符（空格、制表符、全角空格和不间断空格）
        p(CharUtil.isBlankChar(CharUtil.TAB));                  // true
        // 字母
        p(CharUtil.isLetter('a'));                              // true
        // 小写字母
        p(CharUtil.isLetterLower('a'));                         // true           
        // 大写字母
        p(CharUtil.isLetterUpper('A'));                         // true
        // 数字
        p(CharUtil.isNumber('1'));                              // true
        // 字母或数字
        p(CharUtil.isLetterOrNumber('a'));                      // true
        // 16 进制规范字符
        p(CharUtil.isHexChar('a'));                             // true

        // 文件分隔符
        p(CharUtil.isFileSeparator(CharUtil.SLASH));            // true
        p(CharUtil.isFileSeparator(CharUtil.BACKSLASH));        // true

        // 字符类型：Char.class, Character.class
        char c = 'a';
        p(CharUtil.isChar(c));                                  // true
        p(CharUtil.isChar(CharUtil.getType(c)));                // false ?
    }
}
