package knowledge.表达式.正则表达式;

import l.demo.Demo;
import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Pattern
 */
public class PatternDemo extends Demo {

    /**
     * static Pattern	compile(String regex[, int flags])
     * 将给定的正则表达式编译到具有给定标志的模式中
     *
     * flags    -- 匹配标志，可能包括 CASE_INSENSITIVE、 MULTILINE、 DOTALL、 UNICODE_CASE、 CANON_EQ、 UNIX_LINES、 LITERAL 和 COMMENTS 的位掩码
     */
    @Test
    public void compile() {
        Pattern p = Pattern.compile("a*b");

        Matcher m = p.matcher("aaaaab");

        p(m.matches());
        p(p.pattern()); // a*b
    }

    /**
     * Matcher	matcher(CharSequence input)
     * 创建匹配给定输入与此模式的匹配器
     */
    @Test
    public void matcher() {
        compile();
    }

    /**
     * static boolean	matches(String regex, CharSequence input)
     * 编译给定正则表达式并尝试将给定输入与其匹配
     */
    @Test
    public void matches() {
        quote();
    }

    /**
     * String	pattern()
     * 返回在其中编译过此模式的正则表达式
     */
    @Test
    public void pattern() {
        compile();
    }

    /**
     * static String	quote(String s)
     * 返回指定 String 的字面值模式 String
     */
    @Test
    public void quote() {
        String regex = "a*b";

        p(Pattern.matches(regex, "aaaaab"));// true

        regex = Pattern.quote(regex);
        p(regex); // \Qa*b\E

        p(Pattern.matches(regex, "aaaaab"));// false
        p(Pattern.matches(regex, "a*b"));   // true，只能匹配 "a*b" 了
    }

    /**
     * String[]	split(CharSequence input, int limit)
     * 围绕此模式的匹配拆分给定输入序列
     */
    @Test
    public void split() {
        Pattern p = Pattern.compile(":");

        String[] result = p.split("boo:and:foo", 2);
        p(Arrays.toString(result)); // [boo, and:foo]
    }
}
