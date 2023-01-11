package knowledge.api.lang.string;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/String.html">String</a>
 * <pre>
 * int          length()                                    返回此字符序列的长度
 * char         charAt(int index)                           返回指定索引处的 char 值
 * String       concat(String str)                          将指定字符串连接到此字符串的结尾
 * boolean      contains(CharSequence s)                    当且仅当此字符串包含指定的 char 值序列时，返回 true
 * boolean      endsWith(String suffix)
 * boolean      startsWith(String prefix[, int toffset])
 * boolean      equals(Object anObject)                     将此字符串与指定的对象比较
 * boolean      equalsIgnoreCase(String str)                与另一个 String 比较，不考虑大小写
 * int          indexOf(int ch, int fromIndex)              返回在此字符串中第一次出现指定字符处的索引，从指定的索引开始搜索
 * int          indexOf(String str[, int fromIndex])        返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始
 * int          lastIndexOf(int ch, int fromIndex)
 * int          lastIndexOf(String str[, int fromIndex])
 * String       toLowerCase()
 * String       toUpperCase()
 * String       trim()
 * </pre>
 * <pre>
 * 字符集合（形状）     编码字符集（数字）           字符编码方案（字节）          字符集 (编码字符集+字符编码方案)
 * character set    coded character set     character-encoding schema           charset
 *                      Unicode                     UTF-8: 变长       GBK: 1个汉字2字节，1个英文或数字1字节
 *                  USC-2: 1个字符2字节              UTF-16: 变长              GB18030: 兼容 GBK
 *                  USC-4: 1个字符4字节          UTF-32: 1个字符4字节         iso-88591-1: 兼容ASCII
 * </pre>
 * <a href="http://www.ruanyifeng.com/blog/2007/10/ascii_unicode_and_utf-8.html">字符编字符编码笔记 ASCII，Unicode 和 UTF-8</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class StringDemo extends Demo {

    @Test
    public void testString() {
        // byte[]	        getBytes([Charset charset/String charsetName])
        // 使用指定的 charset/字符集 将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中
        byte[] bytes = HELLO_WORLD.getBytes(StandardCharsets.UTF_8);            // 编码
        // String([byte[] bytes, int offset, int length, Charset charset / String charsetName])
        // 通过使用指定的 charset/字符集 解码指定的 byte 子数组，构造一个新的 String
        String s = new String(bytes, 0, bytes.length, StandardCharsets.UTF_8);  // 解码
        p(s); // Hello World

        s.chars().forEach(System.out::println);

        // static String	format([Locale l, ]String format, Object... args)
        // 使用指定的语言环境、格式字符串和参数返回一个格式化字符串
        // format 转换符：https://www.cnblogs.com/Dhouse/p/7776780.html
        p(String.format("%s来自%s", "我", "中国"));  // 我来自中国
        p(String.format("%.2f", 0.345));            // 0.35
    }

    /**
     * <a href="https://zhuanlan.zhihu.com/p/357872204">intern()</a>
     * <pre>
     * 如果字符串常量池中已经包含一个等于此 String 对象的字符串，则返回代表池中这个字符串的 String 对象的引用；
     * 否则，会将此 String 对象包含的字符串添加到常量池中，并且返回此 String 对象的引用
     * </pre>
     *
     * @see <a href="https://pic2.zhimg.com/80/v2-16be2d34799123632909e415d956e111_720w.jpg">JDK1.8 JVM 内存模型</a>
     */
    @Test
    public void intern() {
        String s1 = new StringBuilder("计算机").append("软件").toString();
        p(s1.intern() == s1); // true
        String s2 = new StringBuilder("ja").append("va").toString();
        p(s2.intern() == s2); // false；Java 启动阶段会自动在常量池中创建 java
    }

    @Test
    public void valueOf() {
        // static String    copyValueOf(char[] data[, int offset, int count])
        // 返回指定数组中表示该字符序列的 String
        char[] cArr = {'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd'};
        p(String.copyValueOf(cArr, 0, 11)); // Hello World

        // static String    valueOf(XXX xxx)
        // 返回 XXX 参数的字符串表示形式
        p(String.valueOf(true));            // true
        p(String.valueOf(123456L));         // 123456
        p(String.valueOf(cArr));            // Hello World
    }

    @Test
    public void splitAndJoin() {
        // String[]	        split(String regex, int limit)
        // 根据匹配给定的正则表达式来拆分此字符串
        p("www.baidu.com".split("\\.", 2)); // [www, baidu.com]
        // 使用索引访问用 String 的 split 方法得到的数组时，需做最后一个分隔符后有无内容的检查，否则会有抛 IndexOutOfBoundsException 的风险（阿里编程规约）
        p("a,b,c,,".split(","));            // [a,b,c]
        p("a,b,c,,".split(",").length);     // 3

        // static String    join(CharSequence delimiter, Iterable<? extends CharSequence> / CharSequence... elements)
        // 返回由 elements 和指定分隔符组成的新字符串。
        p(String.join(",", "A", "B", "C"));

    }

    @Test
    public void compare() {
        // boolean	        contentEquals([CharSequence / StringBuffer cs])
        // 将此字符串与指定的 CharSequence / StringBuffer 比较内容是否相等
        p("abc".equals(new StringBuilder("abc")));              // false
        p("abc".contentEquals(new StringBuilder("abc")));       // true

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
    public void toCharArray() {
        char[] cArr = new char[HELLO_WORLD.length()];
        // void             getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
        // 将字符从此字符串复制到目标字符数组
        HELLO_WORLD.getChars(0, HELLO_WORLD.length(), cArr, 0);
        p(cArr); // [H, e, l, l, o,  , W, o, r, l, d]

        // char[]	        toCharArray()
        // 将此字符串转换为一个新的字符数组
        p(HELLO_WORLD.toCharArray()); // [H, e, l, l, o,  , W, o, r, l, d]
    }

    @Test
    public void subString() {
        // CharSequence	    subSequence(int beginIndex, int endIndex)
        // 返回一个新的字符序列，它是此序列的一个子序列
        p("www.baidu.com".subSequence(4, 10));  // baidu
        // String	        substring(int beginIndex[, int endIndex])
        // 返回一个新字符串，它是此字符串的一个子字符串
        p("www.baidu.com".substring(4, 10));    // baidu
    }

    /**
     * <pre>
     * String       replace(char oldChar, char newChar)
     * 返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的
     * String       replace(CharSequence target, CharSequence replacement)
     * 使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串
     * String       replaceAll(String regex, String replacement)
     * 使用给定的 replacement 替换此字符串所有匹配给定的正则表达式的子字符串
     * String       replaceFirst(String regex, String replacement)
     * 使用给定的 replacement 替换此字符串匹配给定的正则表达式的第一个子字符串
     * </pre>
     */
    @Test
    public void replace() {
        p("abc123cba".replace('b', 'B'));               // aBc123cBa
        p("abc123cba".replace("b", "B"));               // aBc123cBa
        p("abc123cba".replaceAll("[a-z]+", "ABC"));     // ABC123ABC
        p("abc123cba".replaceAll("[a-z]*", "ABC"));     // ABCABC1ABC2ABC3ABCABC
        p("abc123cba".replaceFirst("[a-z]*", "ABC"));   // ABC123cba

        p("13800123456".replaceAll("(.*\\d{3})\\d{4}(\\d{4})", "$1****$2")); // 138****3456，分组替换
    }
}
