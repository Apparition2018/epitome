package jar.google.guava.base;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Splitter
 * http://www.ibloger.net/article/3335.html
 * https://github.com/google/guava/wiki/StringsExplained#splitter
 * https://guava.dev/releases/snapshot-jre/api/docs/com/google/common/base/Splitter.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class SplitterDemo {

    @Test
    public void test1() {
        String s = "~?~this, is~~ , . random , text,";
        List<String> split = Splitter
                // static Splitter	    onPattern(String separatorPattern)
                // 返回一个拆分器，它将与给定模式（正则表达式）匹配的任何子序列视为分隔符
                .onPattern("[.|,]")
                // Splitter	            omitEmptyStrings()
                // 从结果中自动忽略空字符串
                .omitEmptyStrings()
                // Splitter	            trimResults(CharMatcher trimmer)
                // 修饰拆分器，拆分器做拆分操作时，会删除元素头尾charMatcher匹配到的字符
                .trimResults(CharMatcher.anyOf("~? "))
                // List<String>	        splitToList(CharSequence sequence)
                // 将序列拆分为字符串组件并将其作为不可变列表返回
                .splitToList(s);
        System.out.println(split); // [this, is, random, text]
    }

    @Test
    public void test2() {
        List<String> list = Splitter
                // static Splitter      fixedLength(int length)
                // 初始化拆分器，拆分器会将字符串分割为元素长度固定的List，最后一个元素长度不足可以直接返回
                .fixedLength(3)
                // Splitter	            limit(int limit)
                // 限制拆分出的字符串数量
                .limit(3)
                .splitToList("aaabbbcccddd");
        System.out.println(list); // [aaa, bbb, cccddd]
    }

    @Test
    public void test3() {
        Map<String, String> map = Splitter.on(Pattern.compile("\\|"))
                .trimResults()
                .omitEmptyStrings()
                // Splitter.MapSplitter withKeyValueSeparator(char separator | Splitter keyValueSplitter | String separator)
                // 初始化一个Map拆分器，拆分器对String拆分时，separator为key和value之间的分隔符
                .withKeyValueSeparator("=")
                .split("hello=HELLO| world=WORLD|||");
        System.out.println(map); // {hello=HELLO, world=WORLD}
    }

}
