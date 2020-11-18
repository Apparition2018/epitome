package jar.apache.commons.io;

import l.demo.Demo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * IOUtils
 * <p>
 * static InputStream	    toInputStream(CharSequence/String input, Charset/String encoding)
 * static InputStream       toBufferedInputStream(InputStream input[, int size])
 * static BufferedReader	toBufferedReader(Reader reader[, int size])
 * static byte[]	        toByteArray(InputStream input[, int/long size])
 * static byte[]	        toByteArray(Reader input, Charset/String encoding)
 * static byte[]	        toByteArray(String input)
 * static byte[]	        toByteArray(URI uri)
 * static byte[]	        toByteArray(URL url)
 * static byte[]	        toByteArray(URLConnection urlConn)
 * static char[]	        toCharArray(InputStream is, Charset/String encoding)
 * static String	        toString(byte[] input, String encoding)
 * static String	        toString(InputStream input, Charset encoding)
 * static String	        toString(Reader input)
 * static String	        toString(URI uri, Charset/String encoding)
 * static String	        toString(URL url, Charset/String encoding)
 * <p>
 * static long	            skip(XXX input, long toSkip)                跳过指定长度的流
 * static void	            skipFully(XXX input, long toSkip)           跳过指定长度的流，如果不够跳会抛出异常
 * <p>
 * https://www.cnblogs.com/xing901022/p/5978989.html
 * http://commons.apache.org/proper/commons-io/javadocs/api-release/org/apache/commons/io/IOUtils.html
 */
public class IOUtilsDemo extends Demo {

    @Test
    public void constant() {
        p(IOUtils.DIR_SEPARATOR_UNIX);      // /
        p(IOUtils.DIR_SEPARATOR_WINDOWS);   // \
        p(IOUtils.DIR_SEPARATOR);           // \
        p(IOUtils.LINE_SEPARATOR_UNIX);     // "\n"
        p(IOUtils.LINE_SEPARATOR_WINDOWS);  // "\r\n"
        p(IOUtils.LINE_SEPARATOR);          //
    }

    /**
     * closeQuietly(XXX xxx)    关闭某个流
     */
    @Test
    public void closeQuietly() throws IOException {
        contentEquals();
    }

    /**
     * static boolean	        contentEquals(InputStream input1, InputStream input2)
     * static boolean	        contentEquals(Reader input1, Reader input2)
     * static boolean	        contentEqualsIgnoreEOL(Reader input1, Reader input2)
     * 对比两个流是否相等[，忽略换行符]
     */
    @Test
    public void contentEquals() {
        try (InputStream is = new URL(BAIDU_URL).openStream();
             InputStream is2 = new URL(BAIDU_URL).openStream();) {
            p(IOUtils.contentEquals(is, is2)); // true
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * static long/int          copy(InputStream input, OutputStream output[, int bufferSize])
     * static void              copy(InputStream input, Writer output[, Charset/String inputEncoding])
     * static int               copy(Reader input, Writer output)
     * static void              copy(Reader input, OutputStream output[, Charset/String inputEncoding])
     * <p>
     * static long              copyLarge(InputStream input, OutputStream output[, long inputOffset, long length, byte[] buffer])
     * static long              copyLarge(Reader input, Writer output[, long inputOffset, long length, char[] buffer])
     * <p>
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
     * static LineIterator	    lineIterator(InputStream input, Charset/String encoding)
     * static LineIterator	    lineIterator(Reader reader)
     * 读取流返回迭代器
     */
    @Test
    public void lineIterator() throws IOException {
        InputStream is = new URL(BAIDU_URL).openStream();
        LineIterator lt = IOUtils.lineIterator(is, UTF_8);
        while (lt.hasNext()) {
            String line = lt.nextLine();
            p(line);
        }
    }

    /**
     * static void              write(byte[]/char[] char, XXX output, Charset/String encoding)
     * static void              write(CharSequence/String data, XXX output, Charset/String encoding)
     * 把数据写入到输出流
     * <p>
     */
    @Test
    public void write() throws IOException {
        OutputStream os = new FileOutputStream(DEMO_FILE_PATH);
        IOUtils.write("静夜思", os, UTF_8);
    }

    /**
     * static void              writeLines(Collection<?> lines, String lineEnding, OutputStream output, Charset/String encoding)
     * static void              writeLines(Collection<?> lines, String lineEnding, Writer writer)
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
        OutputStream os = new FileOutputStream(DEMO_FILE_PATH);
        IOUtils.writeLines(lines, IOUtils.LINE_SEPARATOR, os, UTF_8);
    }

    /**
     * static int               read(InputStream input, byte[] buffer[, int offset, int length])
     * static int               read(ReadableByteChannel input, ByteBuffer buffer)
     * static int               read(Reader input, char[] buffer[, int offset, int length])
     * 从输入流中读取字节 （通常返回输入流的字节数组长度）
     * <p>
     * static void      readFully(InputStream input, byte[] buffer[, int offset, int length])
     * static byte[]    readFully(InputStream input, int length)
     * static void      readFully(ReadableByteChannel input, ByteBuffer buffer)
     * static void      readFully(Reader input, char[] buffer[, int offset, int length])
     * 同上，如果不够读会抛出异常
     */
    @Test
    public void read() throws IOException {
        InputStream is = IOUtils.toInputStream(HELLO_WORLD, UTF_8);
        byte[] bytes = new byte[16];
        IOUtils.read(is, bytes);
        // IOUtils.readFully(is, bytes); // EOFException: Length to read: 16 actual: 11
        p(new String(bytes)); // Hello World!
    }

    /**
     * static List<String>      readLines(InputStream input, Charset/String encoding)
     * static List<String>      readLines(Reader input)
     * 获得输入流的内容放入一个 List<String> 类型的容器，每一行为这个容器的一个入口
     */
    @Test
    public void readLines() throws IOException {
        InputStream is = new FileInputStream(DEMO_FILE_PATH);
        List<String> lines = IOUtils.readLines(is, UTF_8);
        for (String line : lines) {
            p(line);
        }
    }

}
