package knowledge.regex;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/regex/Pattern.html">Pattern</a>
 * <p>正则表达式的编译表示形式
 * <p>字符：
 * <pre>
 * \\               反斜线字符（Java \\\\）
 * </pre>
 * 字符类：
 * <pre>
 * [abc]            a、b 或 c（简单类）
 * [^abc]           任何字符，除了 a、b 或 c（否定）
 * [a-zA-Z]         a 到 z 或 A 到 Z，两头的字母包括在内（范围）
 * [a-d[m-p]]       a 到 d 或 m 到 p：[a-dm-p]（并集）
 * [a-z&&[def]]     d、e 或 f（交集）
 * [a-z&&[^bc]]     a 到 z，除了 b 和 c：[ad-z]（减去）
 * [a-z&&[^m-p]]    a 到 z，而非 m 到 p：[a-lq-z]（减去）
 * </pre>
 * 预定义字符类：
 * <pre>
 * .                任何字符（与行结束符可能匹配也可能不匹配）
 * \d               数字：[0-9]
 * \D               非数字： [^0-9]
 * \s               空白字符：[ \t\n\x0B\f\r]
 * \S               非空白字符：[^\s]
 * \w               单词字符：[a-zA-Z_0-9]
 * \W               非单词字符：[^\w]
 * </pre>
 * 边界匹配器：
 * <pre>
 * ^                行的开头
 * $                行的结尾
 * \b               单词边界
 * \B               非单词边界
 * \A               输入的开头
 * \G               上一个匹配的结尾
 * \Z               输入的结尾，仅用于最后的结束符（如果有的话）
 * \z               输入的结尾
 * </pre>
 * Greedy 数量词：
 * <pre>
 * X?               X，一次或一次也没有
 * X*               X，零次或多次
 * X+               X，一次或多次
 * X{n}             X，恰好 n 次
 * X{n,}            X，至少 n 次
 * X{n,m}           X，至少 n 次，但是不超过 m 次
 * </pre>
 * Reluctant 数量词：
 * <pre>
 * X??              X，一次或一次也没有
 * X*?              X，零次或多次
 * X+?              X，一次或多次
 * X{n}?            X，恰好 n 次
 * X{n,}?           X，至少 n 次
 * X{n,m}?          X，至少 n 次，但是不超过 m 次
 * </pre>
 * Possessive 数量词：
 * <pre>
 * X?+              X，一次或一次也没有
 * X*+              X，零次或多次
 * X++              X，一次或多次
 * X{n}+            X，恰好 n 次
 * X{n,}+           X，至少 n 次
 * X{n,m}+          X，至少 n 次，但是不超过 m 次
 * </pre>
 * Logical 运算符：
 * <pre>
 * XY               X 后跟 Y
 * X|Y              X 或 Y
 * (X)              X，作为捕获组
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class PatternDemo extends Demo {

    /**
     * 用法一 Pattern.compile()
     */
    @Test
    public void compile() {
        // static Pattern   compile(String regex[, int flags])
        // 将给定的正则表达式编译到具有给定标志的模式中
        Pattern pattern = Pattern.compile("a*b");
        // Matcher	        matcher(CharSequence input)
        // 创建匹配给定输入与此模式的匹配器
        Matcher matcher = pattern.matcher("aab");
    }

    /**
     * 用法二 Pattern.matches()
     */
    @Test
    public void matches() {
        // static boolean    matches(String regex, CharSequence input)
        // 编译给定正则表达式并尝试将给定输入与其匹配
        p(Pattern.matches("a*b", "aab")); // true
    }

    /**
     * static String        quote(String s)
     * <p>返回指定 String 的字面值模式 String
     */
    @Test
    public void quote() {
        String quote = Pattern.quote("a*b");
        p(Pattern.matches("a*b", "aab")); // true
        p(Pattern.matches(quote, "aab")); // false
    }

    /**
     * 围绕此模式的匹配拆分给定输入序列
     */
    @Test
    public void split() {
        Pattern pattern = Pattern.compile("@");

        // 一般写法
        // String[]         split(CharSequence input[, int limit])
        p(pattern.split(MY_EMAIL));                     // [88850180, 163.com]

        // 流写法
        // Stream<String>   splitAsStream(final CharSequence input)
        p(pattern.splitAsStream(MY_EMAIL).toArray());   // [88850180, 163.com]
    }

    @Test
    public void asPredicate() {
        Pattern pattern = Pattern.compile(".*@163.com");
        // Pattern → Predicate
        Stream.of(MY_EMAIL, "123456@qq.com").filter(pattern.asPredicate()).forEach(System.out::println);
    }
}
