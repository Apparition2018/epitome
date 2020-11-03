package knowledge.表达式.正则表达式;

import l.demo.Demo;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Pattern
 * 正则表达式的编译表示形式
 * https://jdk6.net/util-regex/Pattern.html
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
