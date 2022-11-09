package jar.apache.commons.lang3;

import l.demo.Demo;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * StringUtils
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringUtils.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class StringUtilsDemo extends Demo {

    @Test
    public void constant() {
        p(StringUtils.SPACE);   // " "
        p(StringUtils.EMPTY);   // ""
        p(StringUtils.LF);      // "\n"，Line Feed，换行
        p(StringUtils.CR);      // "\r"，Carriage Return，回车
    }

    // Abbreviate
    // 显示指定数量字符
    @Test
    public void abbreviate() {
        p(StringUtils.abbreviate("123456789", 8));              // 12345...
        // p(StringUtils.abbreviate("123456789", 3));           // IllegalArgumentException

        p(StringUtils.abbreviate("123456789", 6, 8));           // 12345...

        p(StringUtils.abbreviateMiddle("123456789", "..", 8));  // 123..789
    }

    // AppendIfMissing / PrependIfMissing
    // 如果开头/结尾不是指定的 suffix / prefix，则拼接到开头/结果
    @Test
    public void ifMissing() {
        p(StringUtils.appendIfMissing(null, null));             //
        p(StringUtils.appendIfMissing("abc", null));            // abc
        p(StringUtils.appendIfMissing("", "xyz"));              // xyz
        p(StringUtils.appendIfMissing("abc", "xyz"));           // abcxyz
        p(StringUtils.appendIfMissing("abcxyz", "xyz"));        // abcxyz
        p(StringUtils.appendIfMissing("abcXYZ", "xyz"));        // abcXYZxyz
        p(StringUtils.appendIfMissing("abc", "xyz", "mno"));    // abcxyz
        p(StringUtils.appendIfMissing("abcmno", "xyz", "mno")); // abcmno
        p(StringUtils.appendIfMissing("abcXYZ", "xyz", "mno")); // abcXYZxyz
    }

    /**
     * UpperCase / LowerCase / SwapCase / Capitalize / UnCapitalize
     * 大小写相关
     */
    @Test
    public void case_() {
        p(StringUtils.upperCase("abc"));    // ABC
        p(StringUtils.lowerCase("ABC"));    // abc
        p(StringUtils.swapCase("aBc"));     // AbC
        p(StringUtils.capitalize("abc"));   // Abc
        p(StringUtils.uncapitalize("ABC")); // aBC
    }

    /**
     * Chomp / Chop
     * 删除最后一位
     */
    @Test
    public void chompAndChop() {
        // 最后一位如果是 "\n", "\r", or "\r\n", 删除
        p(StringUtils.chomp("www.baidu.com"));      // www.baidu.com
        p(StringUtils.chomp("www.baidu.com\n"));    // www.baidu.com
        p(StringUtils.chomp("www.baidu.com\r\n"));  // www.baidu.com

        // 删除最后一位，包括 "\n", "\r", or "\r\n"
        p(StringUtils.chop("www.baidu.com"));       // www.baidu.co
        p(StringUtils.chop("www.baidu.com\n"));     // www.baidu.com
        p(StringUtils.chop("www.baidu.com\r\n"));   // www.baidu.com
    }

    // CountMatches
    // 计算匹配数
    @Test
    public void countMatches() {
        p(StringUtils.countMatches("a-bc-def", "-")); // 2
    }

    // DefaultString
    // 如果字符串是 null，转换为 "" 或 指定字符串
    @Test
    public void defaultString() {
        p(StringUtils.defaultString(null));         //
        p(StringUtils.defaultString(""));           //

        p(StringUtils.defaultString(null, "NULL")); // NULL
        p(StringUtils.defaultString("", "NULL"));   //
    }

    // Difference
    // 从头比较两个字符串，返回后者相对于前者的不相同部分
    @Test
    public void difference() {
        p(StringUtils.difference("abc", "ab"));     //
        p(StringUtils.difference("ab", "abxyz"));   // xyz
        p(StringUtils.difference("abcde", "bcd"));  // bcd
    }

    // Equals / Compare
    @Test
    public void equals() {
        p(StringUtils.equalsAny("aaa", "aaa", "bbb", "ccc"));               // true
        p(StringUtils.equalsAny("aaa", new String[]{"aaa", "bbb", "ccc"})); // true
        p(StringUtils.equalsAny("abc", "aaa", "bbb", "ccc"));               // false
        p(StringUtils.equalsAnyIgnoreCase("AAA", "aaa", "bbb", "ccc"));     // true
    }

    @Test
    public void compare() {
        p(StringUtils.compare("aaa", "AAA"));           // 32
        p(StringUtils.compare("aaa", null, true));      // 1
        p(StringUtils.compare("aaa", null, false));     // -1
        p(StringUtils.compareIgnoreCase("aaa", "AAA")); // 0
    }

    // IsEmpty / IsBlank
    @Test
    public void is1() {
        // "" 或 null
        p(StringUtils.isEmpty(""));         // true
        p(StringUtils.isEmpty(null));       // true
        p(StringUtils.isEmpty(" "));        // false
        p(StringUtils.isNotEmpty(" "));     // true
        p(StringUtils.isAnyEmpty("", " ")); // true
        p(StringUtils.isNoneEmpty("", " "));// false

        // "", " " 或 null
        p(StringUtils.isBlank(""));         // true
        p(StringUtils.isBlank(null));       // true
        p(StringUtils.isBlank(" "));        // true
        p(StringUtils.isNotBlank(""));      // false
        p(StringUtils.isAnyBlank("", "1")); // true
        p(StringUtils.isNoneBlank("", "1"));// false
    }

    // IsAlpha / IsNumeric / IsWhitespace / IsAsciiPrintable
    @Test
    public void is2() {

        p(StringUtils.isAllLowerCase("abc"));       // true
        p(StringUtils.isAllUpperCase("ABC"));       // true

        // 字母
        p(StringUtils.isAlpha("ABC D"));            // false
        p(StringUtils.isAlphaSpace("ABC D"));       // true

        // 数字
        p(StringUtils.isNumeric("123 4"));          // false
        p(StringUtils.isNumericSpace("123 4"));     // true

        // 字母数字
        p(StringUtils.isAlphanumeric("1B3 D"));     // false
        p(StringUtils.isAlphanumericSpace("1B3 D"));// true

        // ascii (ch >= ' ' && ch < 127)
        p(StringUtils.isAsciiPrintable("~!@#"));    // true

        // "", " ", "  ", ...
        p(StringUtils.isWhitespace("  "));          // true
        p(StringUtils.isWhitespace(" "));           // true
        p(StringUtils.isWhitespace(""));            // true
        p(StringUtils.isWhitespace(null));          // false

    }

    // IndexOf / LastIndexOf / Contains
    @Test
    public void indexOf() {
        p(StringUtils.indexOf("abcdabcd", "b"));            // 1
        p(StringUtils.indexOf("abcdabcd", "b", 3));         // 5
        p(StringUtils.indexOfIgnoreCase("abcdabcd", "BC")); // 1

        p(StringUtils.indexOfAny("abcdabcd", "b", "c"));    // 1
        p(StringUtils.indexOfAny("abcdabcd", "bc"));        // 1

        p(StringUtils.indexOfAnyBut("abcdabcd", 'b', 'c')); // 0
        p(StringUtils.indexOfAnyBut("abcdabcd", "bc"));     // 0

        // 比较两个字符串，从第几个字符开始不相同
        p(StringUtils.indexOfDifference("abcdabcd", "ab")); // 2
        p(StringUtils.indexOfDifference("abcdabcd", "bcd"));// 0
    }

    @Test
    public void contains() {
        p(StringUtils.contains("abcdabcd", "bc"));                  // true
        p(StringUtils.contains("abcdabcd", 97));                    // true
        p(StringUtils.containsIgnoreCase("abcdabcd", "BC"));        // true

        // 判断是否至少包含其中一个
        p(StringUtils.containsAny("abcdabcd", "b", "e"));           // true
        p(StringUtils.containsAny("abcdabcd", "eb"));               // true

        // 判断是否都不包含
        p(StringUtils.containsNone("abcdabcd", 'b', 'e'));          // false
        p(StringUtils.containsNone("abcdabcd", 'e', 'f'));          // true
        p(StringUtils.containsNone("abcdabcd", "ef"));              // true

        // 判断是否所有字符都出自第二个参数
        p(StringUtils.containsOnly("abcdabcd", 'b', 'b', 'c'));     // false
        p(StringUtils.containsOnly("abcdabcd", 'a', 'b', 'c', 'd'));// true
        p(StringUtils.containsOnly("abcdabcd", "abcd"));            // false

        // 判断是否包含空格
        p(StringUtils.containsWhitespace("abcdabcd"));              // false
        p(StringUtils.containsWhitespace("abcd abcd"));             // true
    }

    // LeftPad / RightPad / Center / Repeat
    @Test
    public void pad() {
        p(StringUtils.leftPad("760", 4));       //  760
        p(StringUtils.leftPad("760", 4, "0"));  // 0760

        p(StringUtils.rightPad("11", 3));       // 11
        p(StringUtils.rightPad("11", 3, "0"));  // 110

        p(StringUtils.center("232", 5));        //  232
        p(StringUtils.center("232", 5, "1"));   // 12321
    }

    @Test
    public void repeat() {
        p(StringUtils.repeat("a", -1));         //
        p(StringUtils.repeat("a", 3));          // aaa
        p(StringUtils.repeat("a", ",", 3));     // a,a,a
    }

    // Remove / Delete
    @Test
    public void remove() {
        p(StringUtils.remove("abcdabcd", "a"));                 // bcdbcd
        p(StringUtils.removeStart("abcdabcd", "a"));            // bcdabcd
        p(StringUtils.removeStartIgnoreCase("abcdabcd", "A"));  // bcdabcd
        p(StringUtils.removeEnd("abcdabcd", "d"));              // abcdabc
        p(StringUtils.removeEndIgnoreCase("abcdabcd", "D"));    // abcdabc
    }

    @Test
    public void deleteWhitespace() {
        p(StringUtils.deleteWhitespace("abcd abcd")); // abcdabcd
    }

    // Replace / Overlay
    @Test
    public void replace() {
        p(StringUtils.replace("AbcdAbcd", "A", "a"));           // abcdabcd
        p(StringUtils.replaceOnce("AbcdAbcd", "A", "a"));       // abcdAbcd

        // A → a, B → b, C → c, D → d
        p(StringUtils.replaceChars("ABcdabCD", "ABCD", "abcd"));// abcdabcd

        // A → a, C → c
        p(StringUtils.replaceEach("AbcdabCd", new String[]{"A", "C"}, new String[]{"a", "c"})); // abcdabcd
    }

    @Test
    public void overlay() {
        p(StringUtils.overlay("0bcd4", "123", 1, 4)); // 01234
    }

    // Reverse / ReverseDelimited
    @Test
    public void reverse() {
        p(StringUtils.reverse("abc"));                  // cba
        p(StringUtils.reverseDelimited("a:bc", ':'));   // bc:a
        p(StringUtils.reverseDelimited("a:b:c", ':'));  // c:b:a
    }

    // Rotate
    @Test
    public void rotate() {
        p(StringUtils.rotate("abcdefg", 0));    // abcdefg
        p(StringUtils.rotate("abcdefg", 2));    // fgabcde
        p(StringUtils.rotate("abcdefg", -2));   // cdefgab
        p(StringUtils.rotate("abcdefg", 7));    // abcdefg
        p(StringUtils.rotate("abcdefg", -7));   // abcdefg
        p(StringUtils.rotate("abcdefg", 9));    // fgabcde
        p(StringUtils.rotate("abcdefg", -9));   // cdefgab
    }

    // Split / Join
    @Test
    public void split() {
        // 用空格分割
        p(StringUtils.split("a b c d"));        // [a, b, c, d]
        p(StringUtils.split("a b c d", ' '));   // [a, b, c, d]
        p(StringUtils.split("a b c d", "b c")); // [a, d]
        // 用空格分割，分成3份
        p(StringUtils.split("a b c d", " ", 3));// [a, b, c d]

        // ???
        p(StringUtils.splitByWholeSeparator("abcd", ""));

        // ???
        p(StringUtils.splitPreserveAllTokens("abcd", ""));

        // 按字符类型分割
        p(StringUtils.splitByCharacterType("11aA啊啊"));          // [11, a, A, 啊啊]
        p(StringUtils.splitByCharacterTypeCamelCase("11aA啊啊")); // [11, a, A, 啊啊]

    }

    @Test
    public void join() {
        String[] arr = new String[]{"a", "b", "c", "d"};
        p(StringUtils.join(arr));                           // abcd
        p(StringUtils.join(arr, "-"));                      // a-b-c-d
        p(StringUtils.join(arr, "-", 1, 3));                // b-c
        p(StringUtils.joinWith("-", "a", "b", "c", "d"));   // a-b-c-d
    }

    // startsWith / endsWith
    @Test
    public void with() {
        p(StringUtils.startsWith("abc", "A"));          // false
        p(StringUtils.startsWithAny("abc", "a", "A"));  // true
        p(StringUtils.startsWithIgnoreCase("abc", "A"));// true

        p(StringUtils.endsWith("abc", "C"));            // false
        p(StringUtils.endsWithAny("abc", "c", "C"));    // true
        p(StringUtils.endsWithIgnoreCase("abc", "C"));  // true
    }

    // SubString / Left / Right / Mid
    @Test
    public void subString() {
        p(StringUtils.substring("abcdabcd", 2));                // cdabcd
        p(StringUtils.substring("abcdabcd", 2, 6));             // cdab

        p(StringUtils.substringAfter("abcdabcd", "b"));         // cdabcd
        p(StringUtils.substringAfterLast("abcdabcd", "b"));     // cd

        p(StringUtils.substringBefore("abcdabcd", "b"));        // a
        p(StringUtils.substringBeforeLast("abcdabcd", "b"));    // abcda

        p(StringUtils.substringBetween("abcdabcd", "ab"));      // cd
        p(StringUtils.substringBetween("abcdabcd", "a", "d"));  // bc
        p(StringUtils.substringsBetween("abcdabcd", "a", "d")); // [bc, bc]
    }

    @Test
    public void leftRightMid() {
        // 从左截取指定长度的字符串
        p(StringUtils.left("abcdabcd", 3));     // abc
        // 从右截取指定长度的字符串
        p(StringUtils.right("abcdabcd", 3));    // bcd
        // 从第几个开始截取几个字符
        p(StringUtils.mid("abcdabcd", 3, 3));   // cdabcd
    }

    // Trim / Strip
    @Test
    public void trim() {
        // null, ""
        p("* " + StringUtils.trim(null));       // * null
        p("* " + StringUtils.trim(""));         // *
        p("* " + StringUtils.trim(" "));        // *

        // null, "" 均转换为 null
        p("* " + StringUtils.trimToNull(null)); // * null
        p("* " + StringUtils.trimToNull(""));   // * null
        p("* " + StringUtils.trimToNull(" "));  // * null

        // null, "" 均转换为 ""
        p("* " + StringUtils.trimToEmpty(null));// *
        p("* " + StringUtils.trimToEmpty(""));  // *
        p("* " + StringUtils.trimToEmpty(" ")); // *
    }

    @Test
    public void strip() {
        // 等同于 trim, trimToNull, trimToEmpty
        p("* " + StringUtils.strip(""));        // *
        p("* " + StringUtils.stripToNull(""));  // * null
        p("* " + StringUtils.stripToEmpty("")); // *

        // 指定 strip 字符
        p(StringUtils.strip("11234321", "1"));      // 23432
        p(StringUtils.stripStart("11234321", "1")); // 234321
        p(StringUtils.stripEnd("11234321", "1"));   // 1123432

        // 批量 strip
        p(StringUtils.stripAll(" 1 ", " 2 "));                          // [1, 2]
        p(StringUtils.stripAll(new String[]{"112321", "112321"}, "1")); // [232, 232]

        // 去音调符号
        p(StringUtils.stripAccents("àbcdé")); // abcde
    }
}
