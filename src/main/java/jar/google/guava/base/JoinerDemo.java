package jar.google.guava.base;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import l.demo.Demo;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JoinerDemo
 * <p>
 * http://www.ibloger.net/article/3334.html
 * https://guava.dev/releases/snapshot-jre/api/docs/index.html?com/google/common/base/Joiner.html
 */
public class JoinerDemo extends Demo {

    private final List<String> list = Lists.newArrayList("A", "B", "C");
    private final List<String> listWithNull = Lists.newArrayList("A", "B", "C", null);

    /**
     * null 值处理
     */
    @Test
    public void null_() {
        String join = Joiner.on("-")
                // Joiner	skipNulls()
                // 用于过滤集合中为null的元素，然后返回一个新的Joiner对象实例
                .skipNulls()
                .join(listWithNull);
        p(join); // A-B-C

        join = Joiner.on("-")
                // Joiner	useForNull(String nullText)
                // 连接器做join连接操作时用nullText替换null元素值
                .useForNull("D")
                .join(listWithNull);
        p(join); // A-B-C-D
    }

    /**
     * appendTo
     */
    @Test
    public void appendTo() {
        try (FileWriter writer = new FileWriter(new File("src/main/java/jar/google/guava/base/join.txt"))) {
            // <A extendsAppendable> A	appendTo(A appendable, Iterable<?> parts)
            // 将parts通过连接器的连接符连接成字符串，并拼接到appendable后
            Joiner.on("-").appendTo(writer, list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder("SSS-SS-S-");
        // StringBuilder	appendTo(StringBuilder builder, Iterable<?> parts)
        // 将parts通过连接器的连接符连接成字符串，并拼接到builder后，返回StringBuilder
        StringBuilder join = Joiner.on("-").appendTo(builder, list);
        p(join); // SSS-SS-S-A-B-C
    }

    /**
     * map  →  String
     */
    @Test
    public void withKeyValueSeparator() {
        ImmutableMap<String, String> map = ImmutableMap.of("A", "1", "B", "2");
        // Joiner.MapJoiner	withKeyValueSeparator(char|String keyValueSeparator)
        // 初始化一个Map连接器，连接器连接Map对象时，keyValueSeparator为key和value之间的分隔符
        String join = Joiner.on("; ").withKeyValueSeparator("=").join(map);
        p(join); // A=1; B=2
    }

    /**
     * java8 joining
     */
    @Test
    public void joining() {
        String collect = listWithNull.stream().filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining("-"));
        p(collect); // A-B-C
    }


}
