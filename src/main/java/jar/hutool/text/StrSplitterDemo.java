package jar.hutool.text;

import cn.hutool.core.text.StrSplitter;
import cn.hutool.core.util.StrUtil;

import static l.demo.Demo.p;

/**
 * <a href="https://hutool.cn/docs/#/core/文本操作/字符串切割-StrSplitter">StrSplitter</a>   字符串切割
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/text/StrSplitter.html">StrSplitter api</a>
 *
 * @author ljh
 * @since 2020/11/5 10:20
 */
public class StrSplitterDemo {

    private static final String CSV_COMMA = " A , B ,, C ";
    private static final String CSV_SLASH = " A / B // C ";
    private static final String FIXED_STR = "abc123ABC";

    public static void main(String[] args) {
        // String →[任意]→ List
        // static List<String>  split(String str, separator/separatorPattern[int limit, isTrim, ignoreEmpty])
        p(StrSplitter.split(CSV_COMMA, StrUtil.COMMA, 4, true, true));           // [A, B, C]
        p(StrSplitter.split(CSV_COMMA, StrUtil.COMMA, 4, true, false));          // [A, B, , C]
        p(StrSplitter.split(CSV_COMMA, StrUtil.COMMA, 4, false, true));          // [ A ,  B ,  C ]
        p(StrSplitter.split(CSV_COMMA, StrUtil.COMMA, 4, false, false));         // [ A ,  B , ,  C ]

        // String →[任意]→ String[]
        // static String[]      splitToArray(String str, separator/separatorPattern[, int limit, isTrim, ignoreEmpty])
        p(StrSplitter.splitToArray(CSV_COMMA, StrUtil.COMMA, 4, true, true));    // [A, B, C]
        p(StrSplitter.splitToArray(CSV_COMMA, StrUtil.COMMA, 4, true, false));   // [A, B, , C]
        p(StrSplitter.splitToArray(CSV_COMMA, StrUtil.COMMA, 4, false, true));   // [ A ,  B ,  C ]
        p(StrSplitter.splitToArray(CSV_COMMA, StrUtil.COMMA, 4, false, false));  // 

        // String →[任意REG]→ List
        // static List<String>  splitByRegex(String str, separatorRegex[, int limit, isTrim, ignoreEmpty])
        p(StrSplitter.splitByRegex(CSV_COMMA, StrUtil.COMMA, 4, true, true));    // [A, B, C]
        p(StrSplitter.splitByRegex(CSV_COMMA, StrUtil.COMMA, 4, true, false));   // [A, B, , C]
        p(StrSplitter.splitByRegex(CSV_COMMA, StrUtil.COMMA, 4, false, true));   // [ A ,  B ,  C ]
        p(StrSplitter.splitByRegex(CSV_COMMA, StrUtil.COMMA, 4, false, false));  // [ A ,  B , ,  C ]

        // String →[/]→ List
        // String →[/]→ String[]
        // static List<String>  splitPath(String str[, int limit])
        // String[]             splitPathToArray(String str[, int limit])
        p(StrSplitter.splitPath(CSV_SLASH, 4));          // [A, B, C]
        p(StrSplitter.splitPathToArray(CSV_SLASH, 4));   // [A, B, C]

        // String →[定长]→ String[]
        // static String[]      splitByLength(String str, int len)
        p(StrSplitter.splitByLength(FIXED_STR, 3));      // [abc, 123, ABC]

        // splitIgnoreCase()
        // splitTrim()
        // splitTrimIgnoreCase()
    }
}
