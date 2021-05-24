package knowledge.regex;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static l.demo.Demo.p;

/**
 * Pattern
 * 正则表达式的编译表示形式
 * https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/regex/Pattern.html
 * <p>
 * 字符：
 * \\               反斜线字符（Java \\\\）
 * 字符类：
 * [abc]	        a、b 或 c（简单类）
 * [^abc]	        任何字符，除了 a、b 或 c（否定）
 * [a-zA-Z]	        a 到 z 或 A 到 Z，两头的字母包括在内（范围）
 * [a-d[m-p]]	    a 到 d 或 m 到 p：[a-dm-p]（并集）
 * [a-z&&[def]]	    d、e 或 f（交集）
 * [a-z&&[^bc]]	    a 到 z，除了 b 和 c：[ad-z]（减去）
 * [a-z&&[^m-p]]	a 到 z，而非 m 到 p：[a-lq-z]（减去）
 * 预定义字符类：
 * .	            任何字符（与行结束符可能匹配也可能不匹配）
 * \d	            数字：[0-9]
 * \D	            非数字： [^0-9]
 * \s	            空白字符：[ \t\n\x0B\f\r]
 * \S	            非空白字符：[^\s]
 * \w	            单词字符：[a-zA-Z_0-9]
 * \W	            非单词字符：[^\w]
 * 边界匹配器：
 * ^	            行的开头
 * $	            行的结尾
 * \b	            单词边界
 * \B	            非单词边界
 * \A	            输入的开头
 * \G	            上一个匹配的结尾
 * \Z	            输入的结尾，仅用于最后的结束符（如果有的话）
 * \z	            输入的结尾
 * Greedy 数量词：
 * X?	            X，一次或一次也没有
 * X*	            X，零次或多次
 * X+	            X，一次或多次
 * X{n}	            X，恰好 n 次
 * X{n,}	        X，至少 n 次
 * X{n,m}	        X，至少 n 次，但是不超过 m 次
 * Reluctant 数量词：
 * X??	            X，一次或一次也没有
 * X*?	            X，零次或多次
 * X+?	            X，一次或多次
 * X{n}?	        X，恰好 n 次
 * X{n,}?	        X，至少 n 次
 * X{n,m}?	        X，至少 n 次，但是不超过 m 次
 * Possessive 数量词：
 * X?+	            X，一次或一次也没有
 * X*+	            X，零次或多次
 * X++	            X，一次或多次
 * X{n}+	        X，恰好 n 次
 * X{n,}+	        X，至少 n 次
 * X{n,m}+	        X，至少 n 次，但是不超过 m 次
 * Logical 运算符：
 * XY	            X 后跟 Y
 * X|Y	            X 或 Y
 * (X)	            X，作为捕获组
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class PatternDemo {

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
    public void matcher() {
        // static boolean    matches(String regex, CharSequence input)
        // 编译给定正则表达式并尝试将给定输入与其匹配
        p(Pattern.matches("a*b", "aab")); // true
    }

    /**
     * static String        quote(String s)
     * 返回指定 String 的字面值模式 String
     */
    @Test
    public void quote() {
        String quote = Pattern.quote("a*b");
        p(Pattern.matches("a*b", "aab")); // true
        p(Pattern.matches(quote, "aab")); // false
    }

    /**
     * String[]	            split(CharSequence input, int limit)
     * 围绕此模式的匹配拆分给定输入序列
     */
    @Test
    public void split() {
        Pattern pattern = Pattern.compile(":");
        p(pattern.split("boo:and:foo", 2)); // [boo, and:foo]
    }
}
