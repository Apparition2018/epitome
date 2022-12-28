package jar.apache.commons.io;

import l.demo.Demo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * <a href="http://commons.apache.org/proper/commons-io/javadocs/api-release/org/apache/commons/io/IOUtils.html">IOUtils</a>
 * <pre>
 * static InputStream       toInputStream(CharSequence/String input, Charset/String encoding)
 * static InputStream       toBufferedInputStream(InputStream input[, int size])
 * static BufferedReader    toBufferedReader(Reader reader[, int size])
 * static byte[]            toByteArray(InputStream input[, int/long size])
 * static byte[]            toByteArray(Reader input, Charset/String encoding)
 * static byte[]            toByteArray(String input)
 * static byte[]            toByteArray(URI uri)
 * static byte[]            toByteArray(URL url)
 * static byte[]            toByteArray(URLConnection urlConn)
 * static char[]            toCharArray(InputStream is, Charset/String encoding)
 * static String            toString(byte[] input, String encoding)
 * static String            toString(InputStream input, Charset encoding)
 * static String            toString(Reader input)
 * static String            toString(URI uri, Charset/String encoding)
 * static String            toString(URL url, Charset/String encoding)
 *
 * static long              skip(XXX input, long toSkip)                跳过指定长度的流
 * static void              skipFully(XXX input, long toSkip)           跳过指定长度的流，如果不够跳会抛出异常
 * </pre>
 * 参考：<a href=" * https://www.cnblogs.com/xing901022/p/5978989.html">IOUtils 总结</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class IOUtilsDemo extends Demo {

    @Test
    public void constant() {
        p(IOUtils.DIR_SEPARATOR_UNIX);      // /
        p(IOUtils.DIR_SEPARATOR_WINDOWS);   // \
        p(IOUtils.DIR_SEPARATOR);           // \
        p(IOUtils.LINE_SEPARATOR_UNIX);     // "\n"
        p(IOUtils.LINE_SEPARATOR_WINDOWS);  // "\r\n"
    }

    /**
     * closeQuietly(XXX xxx)    关闭某个流
     */
    @Test
    public void closeQuietly() {
    }

    /**
     * <pre>
     * static boolean   contentEquals(InputStream input1, InputStream input2)
     * static boolean   contentEquals(Reader input1, Reader input2)
     * static boolean   contentEqualsIgnoreEOL(Reader input1, Reader input2)
     * </pre>
     * 对比两个流是否相等[，忽略换行符]
     */
    @Test
    public void contentEquals() {
        try (InputStream is = new URL(BAIDU_URL).openStream();
             InputStream is2 = new URL(BAIDU_URL).openStream()) {
            p(IOUtils.contentEquals(is, is2)); // true
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <pre>
     * static long/int      copy(InputStream input, OutputStream output[, int bufferSize])
     * static void          copy(InputStream input, Writer output[, Charset/String inputEncoding])
     * static int           copy(Reader input, Writer output)
     * static void          copy(Reader input, OutputStream output[, Charset/String inputEncoding])
     *
     * static long          copyLarge(InputStream input, OutputStream output[, long inputOffset, long length, byte[] buffer])
     * static long          copyLarge(Reader input, Writer output[, long inputOffset, long length, char[] buffer])
     * </pre>
     * 将字节从输入流，复制到输出流
     */
    @Test
    public void copy() throws IOException {
        InputStream is = new URL(BAIDU_URL).openStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IOUtils.copy(is, baos); // 默认 bufferSize 就是 1024 * 4
        p(baos.size()); // 2443
    }

    /**
     * <pre>
     * static LineIterator	    lineIterator(InputStream input, Charset/String encoding)
     * static LineIterator	    lineIterator(Reader reader)
     * </pre>
     * 读取流返回迭代器
     */
    @Test
    public void lineIterator() throws IOException {
        InputStream is = new URL(BAIDU_URL).openStream();
        LineIterator lt = IOUtils.lineIterator(is, StandardCharsets.UTF_8.name());
        while (lt.hasNext()) {
            String line = lt.nextLine();
            p(line);
        }
    }

    /**
     * <pre>
     * static void      write(byte[]/char[] char, XXX output, Charset/String encoding)
     * static void      write(CharSequence/String data, XXX output, Charset/String encoding)
     * </pre>
     * 把数据写入到输出流
     */
    @Test
    public void write() throws IOException {
        OutputStream os = Files.newOutputStream(Paths.get(DEMO_FILE_PATH));
        IOUtils.write("静夜思", os, StandardCharsets.UTF_8.name());
    }

    /**
     * <pre>
     * static void      writeLines(Collection<?> lines, String lineEnding, OutputStream output, Charset/String encoding)
     * static void      writeLines(Collection<?> lines, String lineEnding, Writer writer)
     * </pre>
     * 把 List<String> 数据写入到输出流
     */
    @Test
    public void writeLines() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("静夜思");
        lines.add("床前明月光，");
        lines.add("疑是地上霜。");
        lines.add("举头望明月，");
        lines.add("低头思故乡。");
        OutputStream os = Files.newOutputStream(Paths.get(DEMO_FILE_PATH));
        IOUtils.writeLines(lines, IOUtils.LINE_SEPARATOR_WINDOWS, os, StandardCharsets.UTF_8.name());
    }

    /**
     * <pre>
     * static int       read(InputStream input, byte[] buffer[, int offset, int length])
     * static int       read(ReadableByteChannel input, ByteBuffer buffer)
     * static int       read(Reader input, char[] buffer[, int offset, int length])
     * </pre>
     * 从输入流中读取字节 （通常返回输入流的字节数组长度）
     * <pre>
     * static void      readFully(InputStream input, byte[] buffer[, int offset, int length])
     * static byte[]    readFully(InputStream input, int length)
     * static void      readFully(ReadableByteChannel input, ByteBuffer buffer)
     * static void      readFully(Reader input, char[] buffer[, int offset, int length])
     * </pre>
     * 同上，如果不够读会抛出异常
     */
    @Test
    public void read() throws IOException {
        InputStream is = IOUtils.toInputStream(HELLO_WORLD, StandardCharsets.UTF_8.name());
        byte[] bytes = new byte[16];
        IOUtils.read(is, bytes);
        // IOUtils.readFully(is, bytes); // EOFException: Length to read: 16 actual: 12
        p(new String(bytes, StandardCharsets.UTF_8)); // Hello World!
    }

    /**
     * <pre>
     * static List<String>      readLines(InputStream input, Charset/String encoding)
     * static List<String>      readLines(Reader input)
     * </pre>
     * 获得输入流的内容放入一个 List<String> 类型的容器，每一行为这个容器的一个入口
     */
    @Test
    public void readLines() throws IOException {
        InputStream is = Files.newInputStream(Paths.get(DEMO_FILE_PATH));
        List<String> lines = IOUtils.readLines(is, StandardCharsets.UTF_8.name());
        for (String line : lines) {
            p(line);
        }
    }
}
