package knowledge.api.lang.string;

import l.demo.Demo;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/**
 * String
 * https://jdk6.net/lang/String.html
 * <p>
 * int	    length()                            返回此字符序列的长度
 * char     charAt(int index)        1           返回指定索引处的 char 值
 * String   concat(String str)                  将指定字符串连接到此字符串的结尾
 * boolean  contains(CharSequence s)            当且仅当此字符串包含指定的 char 值序列时，返回 true
 * boolean	endsWith(String suffix)
 * boolean	startsWith(String prefix[, int toffset])
 * boolean	equals(Object anObject)             将此字符串与指定的对象比较
 * boolean  equalsIgnoreCase(String str)        与另一个 String 比较，不考虑大小写
 * int	    indexOf(int ch, int fromIndex)      返回在此字符串中第一次出现指定字符处的索引，从指定的索引开始搜索
 * int	    indexOf(String str[, int fromIndex])返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始
 * int	    lastIndexOf(int ch, int fromIndex)
 * int	    lastIndexOf(String str[, int fromIndex])
 * String	toLowerCase()
 * String	toUpperCase()
 * String	trim()
 * <p>
 * 字符集合（形状）         编码字符集（数字）           字符编码方案（字节）              字符集 (编码字符集+字符编码方案)
 * character set        coded character set         character-encoding schema           charset
 * -                        Unicode                  UTF-8:  变长                     GBK: 1个汉字2字节，1个英文或数字1字节
 * -                        USC-2: 1个字符2字节       UTF-16: 变长                     GB18030: 兼容 GBK
 * -                        USC-4: 1个字符4字节       UTF-32: 1个字符4字节            iso-88591-1: 兼容ASCII
 */
public class StringDemo extends Demo {

    @Test
    public void testString() {
        // byte[]	    getBytes([Charset charset/String charsetName])
        // 使用指定的 charset/字符集 将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中
        byte[] bytes = "hello world".getBytes(StandardCharsets.UTF_8);          // 编码
        // String([byte[] bytes, int offset, int length, Charset charset / String charsetName])
        // 通过使用指定的 charset/字符集 解码指定的 byte 子数组，构造一个新的 String
        String s = new String(bytes, 0, bytes.length, StandardCharsets.UTF_8);  // 解码
        p(s); // hello world


        // static String	format([Locale l, ]String format, Object... args)
        // 使用指定的语言环境、格式字符串和参数返回一个格式化字符串
        // https://www.cnblogs.com/Dhouse/p/7776780.html
        p(String.format("%s来自%s", "我", "中国")); // 我来自中国
    }

    @Test
    public void testValueOf() {
        // static String    copyValueOf(char[] data[, int offset, int count])
        // 返回指定数组中表示该字符序列的 String
        char[] cArr = {'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd'};
        p(String.copyValueOf(cArr, 0, 11));  // hello world

        // static String    valueOf(XXX xxx)
        // 返回 XXX 参数的字符串表示形式
        p(String.valueOf(true));            // true
        p(String.valueOf(123456L));         // 123456
        p(String.valueOf(cArr));            // hello world
    }

    @Test
    public void testSplitAndJoin() {
        // String[]	        split(String regex, int limit)
        // 根据匹配给定的正则表达式来拆分此字符串
        p("www.baidu.com".split("\\.", 2)); // [www, baidu.com]

        // static String    join(CharSequence delimiter, Iterable<? extends CharSequence> / CharSequence... elements)
        // 返回由 elements 和指定分隔符组成的新字符串。
        p(String.join(",", "A", "B", "C"));

    }


    /**
     * 测试比较相关 API
     */
    @Test
    public void testCompare() {
        // boolean	        contentEquals([CharSequence / StringBuffer cs])
        // 将此字符串与指定的 CharSequence / StringBuffer 比较内容是否相等
        p("abc".equals(new StringBuffer("abc")));               // false
        p("abc".contentEquals(new StringBuffer("abc")));        // true

        // boolean	        regionMatches([boolean ignoreCase, ]int toffset, String other, int ooffset, int len)
        // 测试两个字符串区域是否相等
        p("www.baidu.com".regionMatches(true, 4, "BAIDU", 0, 5));// true

        // boolean	        matches(String regex)
        // 告知此字符串是否匹配给定的正则表达式，和 Pattern.matches(regex, str) 返回的结果完全相同
        p("www.baidu.com".matches("(.*)baidu(.*)"));            // true
        p(Pattern.matches("(.*)baidu(.*)", "www.baidu.com"));   // true


        // int              compareTo(String anotherString)
        // 按字典顺序比较两个字符串
        // int              compareToIgnoreCase(String str)
        // 按字典顺序比较两个字符串，不考虑大小写
        p("a".compareTo("b"));      //  -1
        p("ab".compareTo("abc"));   //  -1
    }

    @Test
    public void testToCharArray() {
        final String S = "hello world";

        char[] cArr = new char[S.length()];
        // void             getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
        // 将字符从此字符串复制到目标字符数组
        S.getChars(0, S.length(), cArr, 0);
        p(cArr);            // [h, e, l, l, o,  , w, o, r, l, d]

        // char[]	        toCharArray()
        // 将此字符串转换为一个新的字符数组
        p(S.toCharArray()); // [h, e, l, l, o,  , w, o, r, l, d]
    }

    @Test
    public void testSubString() {
        // CharSequence	    subSequence(int beginIndex, int endIndex)
        // 返回一个新的字符序列，它是此序列的一个子序列
        p("www.baidu.com".subSequence(4, 10));  // baidu
        // String	        substring(int beginIndex[, int endIndex])
        // 返回一个新字符串，它是此字符串的一个子字符串
        p("www.baidu.com".substring(4, 10));    // baidu
    }

    /**
     * String	    replace(char oldChar, char newChar)
     * 返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的
     * String	    replace(CharSequence target, CharSequence replacement)
     * 使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串
     * String	    replaceAll(String regex, String replacement)
     * 使用给定的 replacement 替换此字符串所有匹配给定的正则表达式的子字符串
     * String	    replaceFirst(String regex, String replacement)
     * 使用给定的 replacement 替换此字符串匹配给定的正则表达式的第一个子字符串
     */
    @Test
    public void replace() {
        p("abc123cba".replace('b', 'B'));               // aBc123cBa
        p("abc123cba".replace("b", "B"));               // aBc123cBa
        p("abc123cba".replaceAll("[a-z]*", "ABC"));     // ABCABC1ABC2ABC3ABCABC
        p("abc123cba".replaceFirst("[a-z]*", "ABC"));   // ABC123cba

        p("13800123456".replaceAll("(.*\\d{3})\\d{4}(\\d{4})", "$1****$2")); // 138****3456，分组替换
    }
}
