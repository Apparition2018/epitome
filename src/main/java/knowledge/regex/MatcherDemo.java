package knowledge.regex;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static l.demo.Demo.ae;
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
    private static final Pattern pattern = Pattern.compile("(\\d+)([a-z]+)");

    private static Matcher matcher = pattern.matcher("123abc234bcd");

    /** 研究方法：研究方法用来检查输入字符串并返回一个布尔值，表示是否找到该模式 */
    @Test
    public void research() {
        // 1. find()：重置此匹配器，然后尝试查找匹配该模式、从指定索引开始的输入序列的下一个子序列
        if (matcher.find(4)) {
            // 整个正则匹配的内容，相当于 group()
            ae(matcher.group(0), "234bcd");
            // 第1个捕获组匹配的内容
            ae(matcher.group(1), "234");
            // 第2个捕获组匹配的内容
            ae(matcher.group(2), "bcd");
        }

        // 2. lookingAt()：尝试将从区域开头开始的输入序列与该模式匹配
        ae(matcher.lookingAt(), true);
        matcher = pattern.matcher("abc123");
        ae(matcher.lookingAt(), false);

        // 3. matches()：尝试将整个区域与模式匹配
        ae(matcher.matches(), false);
        matcher = pattern.matcher("123abc");
        ae(matcher.matches(), true);
    }

    /** 索引方法：索引方法提供了有用的索引值，精确表明输入字符串中在哪能找到匹配 */
    @Test
    public void index() {
        while (matcher.find()) {
            // group([int group])：返回在以前匹配操作期间由给定组捕获的输入子序列，group() 相当于 group(0)
            p(matcher.group());
            // 1. start([int group])：返回在以前的匹配操作期间，由给定组所捕获的子序列的初始索引
            // 2. end([int group])：返回在以前的匹配操作期间，由给定组所捕获子序列的最后字符之后的偏移量
            p(String.format("全分组：start[%s]，end[%s]", matcher.start(), matcher.end()));
            p(String.format("分组1：start[%s]，end[%s]", matcher.start(1), matcher.end(1)));
            p(String.format("分组2：start[%s]，end[%s]", matcher.start(2), matcher.end(2)));
            // 123abc
            // 全分组：start[0]，end[6]
            // 分组1：start[0]，end[3]
            // 分组2：start[3]，end[6]
            // 234bcd
            // 全分组：start[6]，end[12]
            // 分组1：start[6]，end[9]
            // 分组2：start[9]，end[12]
        }
    }

    /** 替换方法：替换方法是替换输入字符串里文本的方法 */
    @Test
    public void replace() {
        Pattern pattern = Pattern.compile("(ha+)+");
        Matcher matcher = pattern.matcher("Laugh: hahaha, haa!");

        // 1. replaceFirst()：替换模式与给定替换字符串匹配的输入序列的第一个子序列
        //    replaceAll()：替换模式与给定替换字符串相匹配的输入序列的每个子序列
        ae(matcher.replaceFirst("LOL"), "Laugh: LOL, haa!");
        ae(matcher.replaceAll("LOL"), "Laugh: LOL, LOL!");

        // 2. appendReplacement()：实现非终端添加和替换步骤
        //    appendTail()：实现终端添加和替换步骤
        matcher.reset();
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "LOL");
            p(sb);
            // Laugh: LOL
            // Laugh: LOL, LOL
        }
        matcher.appendTail(sb);
        ae(sb.toString(), "Laugh: LOL, LOL!");

        // 3. quoteReplacement()：返回指定 String 的字面值替换 String
        pattern = Pattern.compile("(the\\s)?USD");
        matcher = pattern.matcher("I love the USD");
        try {
            p(matcher.replaceAll("$"));
        } catch (Exception e) {
            ae(e.getMessage(), "Illegal group reference: group index is missing");
        }
        ae(matcher.replaceAll(Matcher.quoteReplacement("$")), "I love $");
    }

    /** 计算有多少个英文单词 */
    @Test
    public void results() {
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        // results()：返回与模式匹配的输入序列的每个子序列的匹配结果流
        ae(pattern.matcher("This is a Cat").results().count(), 4L);
        ae(pattern.matcher("This is a Cat? No!").results().count(), 5L);
        ae(pattern.matcher("I'm OK").results().count(), 3L);
    }
}
