package knowledge.regex;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static l.demo.Demo.p;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/regex/Matcher.html">Matcher</a>
 * <p>通过解释 Pattern 对 character sequence 执行匹配操作的引擎。
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class MatcherDemo {

    // 在使用正则表达式时，利用好其预编译功能，可以有效加快正则匹配速度。不要在方法体内定义（阿里编程规约）
    private static final Pattern pattern = Pattern.compile("(a*b)(foo)");

    /**
     * 索引方法
     * <p>索引方法提供了有用的索引值，精确表明输入字符串中在哪能找到匹配
     * <pre>
     * int      start([int group])      返回在以前的匹配操作期间，由给定组所捕获的子序列的初始索引
     * int      end([int group])        返回在以前的匹配操作期间，由给定组所捕获子序列的最后字符之后的偏移量
     * </pre>
     */
    @Test
    public void index() {
        Matcher matcher = pattern.matcher("abfooaabfooaaabfoob");
        while (matcher.find()) {
            // String	    group([int group])
            // 返回在以前匹配操作期间由给定组捕获的输入子序列，group() 相当于 group(0)
            p(matcher.group());
            p(String.format("全分组：start[%s]，end[%s]", matcher.start(), matcher.end()));
            p(String.format("分组1：start[%s]，end[%s]", matcher.start(1), matcher.end(1)));
            p(String.format("分组2：start[%s]，end[%s]", matcher.start(2), matcher.end(2)));
            // abfoo
            // 全分组：start[0]，end[5]
            // 分组1：start[0]，end[2]
            // 分组2：start[2]，end[5]
            // aabfoo
            // 全分组：start[5]，end[11]
            // 分组1：start[5]，end[8]
            // 分组2：start[8]，end[11]
            // aaabfoo
            // 全分组：start[11]，end[18]
            // 分组1：start[11]，end[15]
            // 分组2：start[15]，end[18]
        }
    }

    /**
     * 研究方法
     * <p>研究方法用来检查输入字符串并返回一个布尔值，表示是否找到该模式
     * <pre>
     * boolean  find([int start])       重置此匹配器，然后尝试查找匹配该模式、从指定索引开始的输入序列的下一个子序列
     * boolean  lookingAt()             尝试将从区域开头开始的输入序列与该模式匹配
     * boolean  matches()               尝试将整个区域与模式匹配
     * </pre>
     */
    @Test
    public void research() {
        // find()
        Matcher matcher = pattern.matcher("abfooaabfooaaabfoob");
        if (matcher.find()) {
            p(matcher.group());     // abfoo
            p(matcher.group(0));    // abfoo
            p(matcher.group(1));    // ab
            p(matcher.group(2));    // foo
        }
        if (matcher.find(10)) {
            p(matcher.group());     // aaabfoo
            p(matcher.group(0));    // aaabfoo
            p(matcher.group(1));    // aaab
            p(matcher.group(2));    // foo
        }

        // lookingAt()
        Pattern pattern2 = Pattern.compile("foo");
        Matcher matcher2 = pattern2.matcher("fooooooooooooooooo");
        p(matcher2.lookingAt());    // true
        matcher2 = pattern.matcher("boofoooooooooooooo");
        p(matcher2.lookingAt());    // false

        // matches()
        Pattern pattern3 = Pattern.compile("foo");
        Matcher matcher3 = pattern3.matcher("fooooooooooooooooo");
        p(matcher3.matches());      // false
        matcher3 = pattern3.matcher("foo");
        p(matcher3.matches());      // true
    }

    /**
     * 替换方法
     * <p>替换方法是替换输入字符串里文本的方法
     * <pre>
     * String           replaceFirst(String replacement)    替换模式与给定替换字符串匹配的输入序列的第一个子序列
     * String           replaceAll(String replacement)      替换模式与给定替换字符串相匹配的输入序列的每个子序列
     * Matcher          appendReplacement(StringBuffer sb, String replacement)  实现非终端添加和替换步骤
     * StringBuffer     appendTail(StringBuffer sb)         实现终端添加和替换步骤
     * static String    quoteReplacement(String s)          返回指定 String 的字面值替换 String
     * static String    quoteReplacement(String s)          返回指定 String 的字面值替换 String
     * </pre>
     */
    @Test
    public void replace() {
        // replaceFirst(), replaceAll()
        Pattern pattern = Pattern.compile("a*b");
        Matcher matcher = pattern.matcher("abfooaabfooaaabfoob");
        p(matcher.replaceFirst("-"));   // -fooaabfooaaabfoob
        p(matcher.replaceAll("-"));     // -foo-foo-foo-


        // appendReplacement(), appendTail()
        // https://blog.zzkun.com/archives/89
        Pattern pattern2 = Pattern.compile("a*b");
        Matcher matcher2 = pattern2.matcher("abfooaabfooaaabfoob");
        StringBuffer sb = new StringBuffer();
        while (matcher2.find()) {
            matcher2.appendReplacement(sb, "-");
            p(sb);
            // -
            // -foo-
            // -foo-foo-
            // -foo-foo-foo-
        }
        matcher2.appendTail(sb);
        p(sb); // -foo-foo-foo-


        Pattern pattern3 = Pattern.compile("a*b");
        Matcher mather3 = pattern3.matcher("abfooaabfooaaabfoob");
        try {
            p(mather3.replaceAll("-$"));
        } catch (Exception e) {
            p(e.getMessage()); // Illegal group reference: group index is missing
        }
        p(mather3.replaceAll(Matcher.quoteReplacement("-$"))); // -$foo-$foo-$foo-$
    }

    /**
     * 计算有多少个英文单词
     */
    @Test
    public void countWord() {
        String s1 = "This is a Cat";
        String s2 = "This is  a Cat";
        String s3 = "This is a Cat? No!";
        String s4 = "I'm OK";

        Pattern pattern = Pattern.compile("\\b\\w+\\b");

        countWord(s1, pattern);
        countWord(s2, pattern);
        countWord(s3, pattern);
        countWord(s4, pattern);
    }

    private void countWord(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        int count = 0;
        while (matcher.find()) {
            ++count;
        }
        p(str + " 单词数：" + count);
    }
}
