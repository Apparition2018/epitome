package knowledge.regex;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.ljh.commons.Demo.MY_EMAIL;
import static com.ljh.commons.Demo.p;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/regex/Pattern.html">Pattern</a>
 * <p>正则表达式的编译表示形式
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class PatternDemo {

    /**
     * 用法一：编译 → 匹配
     */
    @Test
    public void compileThenMatcher() {
        // compile()：将给定的正则表达式编译到具有给定标志的模式中
        Pattern pattern = Pattern.compile("a*b");
        // matcher()：创建匹配给定输入与此模式的匹配器
        Matcher matcher = pattern.matcher("aab");
        assertThat(matcher.find()).isTrue();
    }

    /**
     * 用法二：直接匹配
     */
    @Test
    public void matches() {
        // 编译给定正则表达式并尝试将给定输入与其匹配
        assertThat(Pattern.matches("a*b", "aab")).isTrue();
    }

    /** 返回指定 String 的字面值模式 String */
    @Test
    public void quote() {
        String quote = Pattern.quote("a*b");
        assertThat(Pattern.matches("a*b", "aab")).isTrue();
        assertThat(Pattern.matches(quote, "aab")).isFalse();
    }

    /** 围绕此模式的匹配拆分给定输入序列 */
    @Test
    public void split() {
        Pattern pattern = Pattern.compile("@");
        p(pattern.split(MY_EMAIL));                     // [88850180, 163.com]
        p(pattern.splitAsStream(MY_EMAIL).toArray());   // [88850180, 163.com]
    }

    /** 创建一个谓词，用于测试给定的字符串是否匹配模式 */
    @Test
    public void asPredicate() {
        Pattern pattern = Pattern.compile(".*@163.com");
        Stream.of(MY_EMAIL, "123456@qq.com").filter(pattern.asPredicate()).forEach(System.out::println);
    }

    /**
     * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html">正则表达式结构</a>
     */
    @Test
    public void regularExpressionConstruct() {
        // 1. Back references 反向引用
        // \n 引用第n个捕获组
        assertThat(Pattern.matches("<(\\w+)>\\w*</\\1>", "<h1>Title</h1>")).isTrue();
        // (?<name>X) 命名捕获组
        // \k<name> 引用<name>捕获组
        assertThat(Pattern.matches("<(?<tag>\\w+)>\\w*</\\k<tag>>", "<h1>Title</h1>")).isTrue();
    }

    /**
     * 默认贪婪模式，尽可能多地匹配字符。在量词后加 ?，可变为非贪婪模式。
     * 非贪婪：三种英文术语，reluctant（Java 专有）、non-greedy、Lazy
     */
    @Test
    public void nonGreedy() {
        String input = "<a>One</a><a>Two</a>";

        // 1. 贪婪模式
        Pattern greedyPattern = Pattern.compile("<a>.*</a>");
        Matcher greedyMatcher = greedyPattern.matcher(input);
        if (greedyMatcher.find()) {
            assertThat(greedyMatcher.group()).isEqualTo(input);
        }

        // 2. 非贪婪模式
        Pattern reluctantPattern = Pattern.compile("<a>.*?</a>");
        Matcher reluctantMatcher = reluctantPattern.matcher(input);
        if (reluctantMatcher.find()) {
            assertThat(reluctantMatcher.group()).isEqualTo("<a>One</a>");
        }
    }
}
