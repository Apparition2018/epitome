package org.ljh.dependencies.google.guava.base;

import com.google.common.base.Joiner;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ljh.commons.Demo.DEMO_DIR_PATH;
import static com.ljh.commons.Demo.p;

/**
 * <a href="https://github.com/google/guava/wiki/StringsExplained#joiner">Joiner</a>
 *
 * @author ljh
 * @see <a href="http://www.ibloger.net/article/3334.html">Guava Joiner</a>
 * @see <a href="https://guava.dev/releases/snapshot-jre/api/docs/com/google/common/base/Joiner.html">Joiner api</a>
 * @since 2019/8/8 19:39
 */
public class JoinerTests {

    private final List<String> list = List.of("A", "B", "C");
    private final List<String> listWithNull = new ArrayList<>(Arrays.asList("A", "B", "C", null));

    /** null 值处理 */
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

    /** appendTo */
    @Test
    public void appendTo() {
        try (FileWriter writer = new FileWriter(DEMO_DIR_PATH + "join.txt")) {
            // <A extendsAppendable> A	appendTo(A appendable, Iterable<?> parts)
            // 将parts通过连接器的连接符连接成字符串，并拼接到appendable后
            Joiner.on("-").appendTo(writer, list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb = new StringBuilder("SSS-SS-S-");
        // StringBuilder	appendTo(StringBuilder builder, Iterable<?> parts)
        // 将parts通过连接器的连接符连接成字符串，并拼接到builder后，返回StringBuilder
        StringBuilder join = Joiner.on("-").appendTo(sb, list);
        p(join); // SSS-SS-S-A-B-C
    }

    /** map  →  String */
    @Test
    public void withKeyValueSeparator() {
        Map<String, String> map = Map.of("A", "1", "B", "2");
        // Joiner.MapJoiner	withKeyValueSeparator(char|String keyValueSeparator)
        // 初始化一个Map连接器，连接器连接Map对象时，keyValueSeparator为key和value之间的分隔符
        String join = Joiner.on(File.pathSeparator).withKeyValueSeparator("=").join(map);
        p(join); // A=1;B=2
    }

    @Test
    public void joining() {
        String collect = listWithNull.stream().filter(s -> null != s && !s.isEmpty()).collect(Collectors.joining("-"));
        p(collect); // A-B-C
    }
}
