package knowledge.api.lang.string;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * String
 * <p>
 * int	    length()                            返回此字符序列的长度
 * char     charAt(int index)                   返回指定索引处的 char 值
 * String   concat(String str)                  将指定字符串连接到此字符串的结尾
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
 */
public class StringDemo {

    /**
     * String([byte[] bytes, int offset, int length, Charset charset / String charsetName])
     * 通过使用指定的 charset/字符集 解码指定的 byte 子数组，构造一个新的 String
     */
    @Test
    public void string() throws UnsupportedEncodingException {
        byte[] data = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        String s = new String(data, 0, 26, "UTF-8");
        p(s); // abcdefghijklmnopqrstuvwxyz
    }

    /**
     * int  compareTo(String anotherString)
     * 按字典顺序比较两个字符串
     * <p>
     * int  compareToIgnoreCase(String str)
     * 按字典顺序比较两个字符串，不考虑大小写
     */
    @Test
    public void compareTo() {
        String s1 = "abc";
        String s2 = "abcd";
        String s3 = "abd";

        p(s1.compareTo(s2)); // -1
        p(s1.compareTo(s3)); // -1
        p(s2.compareTo(s3)); // -1
    }

    /**
     * boolean	contains(CharSequence s)
     * 当且仅当此字符串包含指定的 char 值序列时，返回 true
     */
    @Test
    public void contains() {
        String s1 = "water";
        String s2 = "fire";
        String s3 = "watermelon";

        p(s3.contains(s1)); // true
        p(s3.contains(s2)); // false
    }

    /**
     * boolean  contentEquals(CharSequence cs)
     * 将此字符串与指定的 CharSequence 比较
     * <p>
     * boolean	contentEquals(StringBuffer sb)
     * 将此字符串与指定的 StringBuffer 比较
     */
    @Test
    public void contentEquals() {
        String s1 = "String";
        String s2 = "String";
        StringBuffer sb = new StringBuffer("String");

        p(s1.contentEquals(s2)); // true
        p(s1.contentEquals(sb)); // true
    }

    /**
     * static String    copyValueOf(char[] data[, int offset, int count])
     * 返回指定数组中表示该字符序列的 String
     */
    @Test
    public void copyValueOf() {
        char[] cArr = {'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd'};

        p(String.copyValueOf(cArr)); // hello world
        p(String.copyValueOf(cArr, 0, 6)); // hello
    }

    /**
     * static String	format([Locale l, ]String format, Object... args)
     * 使用指定的语言环境、格式字符串和参数返回一个格式化字符串
     * <p>
     * https://www.cnblogs.com/Dhouse/p/7776780.html
     */
    @Test
    public void format() {
        p(String.format("%s来自%s", "我", "中国")); // 我来自中国
    }

    /**
     * byte[]   getBytes()
     * 使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中
     * <p>
     * byte[]	getBytes(Charset charset)
     * 使用给定的 charset 将此 String 编码到 byte 序列，并将结果存储到一个新的 byte 数组中
     * <p>
     * byte[]	getBytes(String charsetName)
     * 使用指定的字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中
     */
    @Test
    public void getBytes() {
        String s = "hello world";

        byte[] bArr1 = s.getBytes();
        byte[] bArr2 = new byte[0];

        try {
            bArr2 = s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            p("不支持的字符集");
        }

        p(new String(bArr1));  // hello world
        p(new String(bArr2));  // hello world
    }

    /**
     * void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
     * 将字符从此字符串复制到目标字符数组
     * <p>
     * dst      -- 目标数组
     * dstBegin -- 目标数组中的起始偏移量
     */
    @Test
    public void getChars() {
        String s = "hello world";
        char[] cArr = new char[5];

        s.getChars(6, 11, cArr, 0);
        p(cArr); // world

    }

    /**
     * boolean	matches(String regex)
     * 告知此字符串是否匹配给定的正则表达式
     * <p>
     * 和 Pattern.matches(regex, str) 返回的结果完全相同
     */
    @Test
    public void matches() {
        String s = "www.baidu.com";

        p(s.matches("(.*)baidu(.*)"));         // true
        p(Pattern.matches("(.*)baidu(.*)", s));// true
    }

    /**
     * boolean	regionMatches([boolean ignoreCase, ]int toffset, String other, int ooffset, int len)
     * 测试两个字符串区域是否相等
     */
    @Test
    public void regionMatches() {
        String s1 = "www.baidu.com";
        String s2 = "baidu";
        String s3 = "BAIDU";

        p(s1.regionMatches(4, s2, 0, 5)); // true
        p(s1.regionMatches(4, s3, 0, 5)); // false
        p(s1.regionMatches(true, 4, s2, 0, 5)); // true
    }

    /**
     * String	replace(char oldChar, char newChar)
     * 返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的
     * <p>
     * String	replace(CharSequence target, CharSequence replacement)
     * 使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串
     * <p>
     * String	replaceAll(String regex, String replacement)
     * 使用给定的 replacement 替换此字符串所有匹配给定的正则表达式的子字符串
     * <p>
     * String	replaceFirst(String regex, String replacement)
     * 使用给定的 replacement 替换此字符串匹配给定的正则表达式的第一个子字符串
     */
    @Test
    public void replace() {
        String s = "abbc123cbba";

        p(s.replace('a', 'd'));               // dbbc123cbbd
        p(s.replace("bb", "dd"));           // addc123cdda
        p(s.replaceAll("[a-z]*", "ABC"));   // ABCABC1ABC2ABC3ABCABC ???
        p(s.replaceFirst("[a-z]*", "ABC")); // ABC123cbba
    }

    /**
     * String[]	split(String regex, int limit)
     * 根据匹配给定的正则表达式来拆分此字符串
     * <p>
     * limit    -- 分割的份数
     */
    @Test
    public void split() {
        String s = "www.baidu.com";
        String[] sArr = s.split("\\.", 2);
        p(Arrays.toString(sArr)); // [www, baidu.com]
    }

    /**
     * CharSequence	subSequence(int beginIndex, int endIndex)
     * 返回一个新的字符序列，它是此序列的一个子序列
     */
    @Test
    public void subSequence() {
        String s = "www.baidu.com";

        p(s.subSequence(4, 9)); // baidu
    }

    /**
     * String	substring(int beginIndex[, int endIndex])
     * 返回一个新字符串，它是此字符串的一个子字符串
     */
    @Test
    public void substring() {
        String s = "www.baidu.com";

        p(s.substring(4, 9)); // baidu
    }

    /**
     * char[]	toCharArray()
     * 将此字符串转换为一个新的字符数组
     */
    @Test
    public void toCharArray() {
        String s = "hello world";
        char[] chars = s.toCharArray();

        p(Arrays.toString(chars)); // [h, e, l, l, o,  , w, o, r, l, d]
    }

    /**
     * static String	valueOf(XXX xxx)
     * 返回 XXX 参数的字符串表示形式
     */
    @Test
    public void valueOf() {
        boolean b = true;
        long l = 123456L;
        char[] cArr = {'h', 'e', 'l', 'l', 'o'};

        p(String.valueOf(b));      // true
        p(String.valueOf(l));      // 123456
        p(String.valueOf(cArr));   // hello
    }


    public static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }
}
