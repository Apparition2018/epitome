package jar.hutool.text;

import cn.hutool.core.text.StrSpliter;
import cn.hutool.core.util.StrUtil;
import l.demo.Demo;
import org.junit.Test;

/**
 * StrSpliter   字符串切割
 * https://hutool.cn/docs/#/core/%E6%96%87%E6%9C%AC%E6%93%8D%E4%BD%9C/%E5%AD%97%E7%AC%A6%E4%B8%B2%E5%88%87%E5%89%B2-StrSpliter
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/text/StrSpliter.html
 *
 * @author ljh
 * created on 2020/11/5 10:20
 */
public class StrSpliterDemo extends Demo {

    private static final String CSV_COMMA = " A , B ,, C ";
    private static final String CSV_SLASH = " A / B // C ";
    private static final String FIXED_STR = "abc123ABC";

    @Test
    public void testStrSpliter() {
        // String →[任意]→ List
        // static List<String>  split(String str, separator/separatorPattern[int limit, isTrim, ignoreEmpty])
        p(StrSpliter.split(CSV_COMMA, StrUtil.COMMA, 4, true, true));           // [A, B, C]
        p(StrSpliter.split(CSV_COMMA, StrUtil.COMMA, 4, true, false));          // [A, B, , C]
        p(StrSpliter.split(CSV_COMMA, StrUtil.COMMA, 4, false, true));          // [ A ,  B ,  C ]
        p(StrSpliter.split(CSV_COMMA, StrUtil.COMMA, 4, false, false));         // [ A ,  B , ,  C ]

        // String →[任意]→ String[]
        // static String[]      splitToArray(String str, separator/separatorPattern[, int limit, isTrim, ignoreEmpty])
        p(StrSpliter.splitToArray(CSV_COMMA, StrUtil.COMMA, 4, true, true));    // [A, B, C]
        p(StrSpliter.splitToArray(CSV_COMMA, StrUtil.COMMA, 4, true, false));   // [A, B, , C]
        p(StrSpliter.splitToArray(CSV_COMMA, StrUtil.COMMA, 4, false, true));   // [ A ,  B ,  C ]
        p(StrSpliter.splitToArray(CSV_COMMA, StrUtil.COMMA, 4, false, false));  // 

        // String →[任意REG]→ List
        // static List<String>  splitByRegex(String str, separatorRegex[, int limit, isTrim, ignoreEmpty])
        p(StrSpliter.splitByRegex(CSV_COMMA, StrUtil.COMMA, 4, true, true));    // [A, B, C]
        p(StrSpliter.splitByRegex(CSV_COMMA, StrUtil.COMMA, 4, true, false));   // [A, B, , C]
        p(StrSpliter.splitByRegex(CSV_COMMA, StrUtil.COMMA, 4, false, true));   // [ A ,  B ,  C ]
        p(StrSpliter.splitByRegex(CSV_COMMA, StrUtil.COMMA, 4, false, false));  // [ A ,  B , ,  C ]

        // String →[/]→ List
        // String →[/]→ String[]
        // static List<String>  splitPath(String str[, int limit])
        // String[]             splitPathToArray(String str[, int limit])
        p(StrSpliter.splitPath(CSV_SLASH, 4));          // [A, B, C]
        p(StrSpliter.splitPathToArray(CSV_SLASH, 4));   // [A, B, C]

        // String →[定长]→ String[]
        // static String[]      splitByLength(String str, int len)
        p(StrSpliter.splitByLength(FIXED_STR, 3));      // [abc, 123, ABC]

        // splitIgnoreCase()
        // splitTrim()
        // splitTrimIgnoreCase()
    }
}
