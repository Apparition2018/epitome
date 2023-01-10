package jar.google.guava.io;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import l.demo.Demo;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * <a href="https://github.com/google/guava/wiki/IOExplained">IO</a>
 * <pre>
 * ByteSource 和 CharSource 分别表示二进制和字符的可读源
 * ByteSink 和 CharSlink 分别表示二进制数字和字符的可写接收器
 * 使用上面这些类的实现可以完全绕过打开和关闭流的问题
 * </pre>
 * 参考：
 * <pre>
 * <a href="http://www.ibloger.net/article/3339.html">Guava IO</a>
 * <a href="https://github.com/google/guava/wiki/ClosingResourcesExplained">ClosingResourcesExplained</a>
 * </pre>
 *
 * @author ljh
 * @since 2023/1/10 9:48
 */
public class IoDemo extends Demo {

    public static void main(String[] args) throws IOException {
        File file = new File(DEMO_FILE_PATH);

        p("Read the lines of a UTF-8 text file");
        ImmutableList<String> lines = Files.asCharSource(file, Charsets.UTF_8).readLines();
        lines.forEach(Demo::p);

        p("\nCount distinct word occurrences in a file");
        Multiset<String> wordOccurrences = HashMultiset.create(
                Splitter.on(CharMatcher.whitespace())
                        .trimResults()
                        .omitEmptyStrings()
                        .split(Files.asCharSource(file, Charsets.UTF_8).read()));
        wordOccurrences.forEach(Demo::p);

        p("\nSHA-256 a file");
        HashCode hash = Files.asByteSource(file).hash(Hashing.sha256());
        p(hash);

        p("\nCopy the data from a URL to a file");
        Resources.asByteSource(new URL(BAIDU_URL)).copyTo(Files.asByteSink(file));
    }
}
