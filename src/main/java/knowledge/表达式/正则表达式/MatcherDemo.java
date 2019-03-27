package knowledge.表达式.正则表达式;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Matcher
 */
public class MatcherDemo {

    /**
     * boolean	find()
     * 尝试查找与该模式匹配的输入序列的下一个子序列
     *
     * boolean	find(int start)
     * 重置此匹配器，然后尝试查找匹配该模式、从指定索引开始的输入序列的下一个子序列
     */
    @Test
    public void find() {
        Pattern p = Pattern.compile("(a*b)(foo)");

        Matcher m = p.matcher("aabfooaabfooabfoob");

        if (m.find()) {
            // 捕获组是从 1 开始从左到右的索引。
            // 组零表示整个模式，因此表达式 m.group(0) 等效于 m.group()
            System.out.println(m.group());  // aabfoo
            System.out.println(m.group(0)); // aabfoo
            System.out.println(m.group(1)); // aab
            System.out.println(m.group(2)); // foo
        }

        if (m.find(10)) {
            System.out.println(m.group());  // abfoo
            System.out.println(m.group(0)); // abfoo
            System.out.println(m.group(1)); // ab
            System.out.println(m.group(2)); // foo
        }
    }

    /**
     * String	group()
     * 返回由以前匹配操作所匹配的输入子序列
     *
     * String	group(int group)
     * 返回在以前匹配操作期间由给定组捕获的输入子序列
     */
    @Test
    public void group() {
        find();
    }

    // 索引方法

    /**
     * int	start()
     * 返回上一个匹配的初始索引
     *
     * int	start(int group)
     * 返回上一个匹配操作期间，由给定组所捕获的子序列的初始索引
     */
    @Test
    public void start() {
        Pattern p = Pattern.compile("(a*b)(foo)");

        Matcher m = p.matcher("aabfooaabfooabfoob");

        while(m.find()) {
            System.out.println(m.start());         // 0 6  12
            System.out.println(m.start(1)); // 0 6  12
            System.out.println(m.start(2)); // 3 9  14

            System.out.println(m.end());           // 6 12 17
            System.out.println(m.end(1));   // 3 9  14
            System.out.println(m.end(2));   // 6 12 17
        }
    }

    /**
     * int	end()
     * 返回最后匹配字符之后的偏移量
     *
     * int	end(int group)
     * 返回在以前的匹配操作期间，由给定组所捕获子序列的最后字符之后的偏移量
     */
    @Test
    public void end() {
        start();
    }

    // 研究方法
    /**
     * boolean	lookingAt()
     * 尝试将从区域开头开始的输入序列与该模式匹配
     */
    @Test
    public void lookingAt() {
        Pattern p = Pattern.compile("foo");

        Matcher m = p.matcher("fooooooooooooooooo");
        System.out.println(m.lookingAt()); // true

        m = p.matcher("boofoooooooooooooo");
        System.out.println(m.lookingAt()); // false
    }

    /**
     * boolean	matches()
     * 尝试将整个区域与模式匹配
     */
    @Test
    public void matches() {
        Pattern p = Pattern.compile("foo");

        Matcher m = p.matcher("fooooooooooooooooo");
        System.out.println(m.matches()); // false

        m = p.matcher("foo");
        System.out.println(m.matches()); // true
    }

    // 替换方法
    /**
     * Matcher	appendReplacement(StringBuffer sb, String replacement)
     * 实现非终端添加和替换步骤
     */
    @Test
    public void appendReplacement() {
        Pattern p = Pattern.compile("a*b");

        Matcher m = p.matcher("aabfooaabfooabfoob");

        StringBuffer buffer = new StringBuffer();

        while(m.find()) {
            m.appendReplacement(buffer, "-");
        }

        m.appendTail(buffer);
        System.out.println(buffer); // -foo-foo-foo-
    }

    /**
     * StringBuffer	appendTail(StringBuffer sb)
     * 实现终端添加和替换步骤
     */
    @Test
    public void appendTail() {
        appendReplacement();
    }

    /**
     * String	replaceAll(String replacement)
     * 替换模式与给定替换字符串相匹配的输入序列的每个子序列
     */
    @Test
    public void replaceAll() {
        Pattern p = Pattern.compile("a*b");

        Matcher m = p.matcher("aabfooaabfooabfoob");

        String result = m.replaceAll("-");
        System.out.println(result); // -foo-foo-foo-

        result = m.replaceFirst("-");
        System.out.println(result); // -fooaabfooabfoob
    }

    /**
     * String	replaceFirst(String replacement)
     * 替换模式与给定替换字符串匹配的输入序列的第一个子序列
     */
    @Test
    public void replaceFirst() {
        replaceAll();
    }

    /**
     * static String	quoteReplacement(String s)
     * 返回指定 String 的字面值替换 String
     */
    @Test
    public void quoteReplacement() {
        Pattern p = Pattern.compile("a*b");

        Matcher m = p.matcher("aabfooaabfooabfoob");

        String result;
        try {
            result = m.replaceAll("-$");
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage()); // Exception: Illegal group reference: group index is missing
        }

        result = m.replaceAll(Matcher.quoteReplacement("-$"));
        System.out.println(result); // -$foo-$foo-$foo-$
    }

}
