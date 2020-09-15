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
 * close(URLConnection conn)    关闭连接
 * <p>
 * https://www.cnblogs.com/xing901022/p/5978989.html
 * http://commons.apache.org/proper/commons-io/javadocs/api-release/org/apache/commons/io/IOUtils.html
 */
public class IOUtilsDemo extends Demo {

    private final static String dirPath = "src/main/java/jar/apache/commons/io/";

    /**
     * 常量
     */
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
     * static boolean	contentEquals(InputStream input1, InputStream input2)
     * static boolean	contentEquals(Reader input1, Reader input2)
     * static boolean	contentEqualsIgnoreEOL(Reader input1, Reader input2)
     * 对比两个流是否相等[，忽略换行符]
     */
    @Test
    public void contentEquals() throws IOException {
        InputStream is = new URL("http://www.apache.org").openStream();
        InputStream is2 = new URL("http://www.apache.org").openStream();
        p(IOUtils.contentEquals(is, is2)); // true
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(is2);
    }

    /**
     * copy(InputStream input, OutputStream output[, int bufferSize])
     * copy(InputStream input, Writer output[, Charset/String inputEncoding])
     * copy(Reader input, OutputStream output[, Charset/String inputEncoding])
     * copy(Reader input, Writer output)
     * <p>
     * copyLarge(InputStream input, OutputStream output[, long inputOffset, long length, byte[] buffer])
     * copyLarge(Reader input, Writer output[, long inputOffset, long length, char[] buffer])
     * <p>
     * 将字节从输入流，复制到输出流
     */
    @Test
    public void copy() throws IOException {
        InputStream is = new URL("http://www.apache.org").openStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IOUtils.copy(is, baos); // 默认 bufferSize 就是 1024 * 4
        p(baos.size()); // 62019
    }

    /**
     * static LineIterator	lineIterator(InputStream input, Charset/String encoding)
     * static LineIterator	lineIterator(Reader reader)
     * 读取流返回迭代器
     */
    @Test
    public void lineIterator() throws IOException {
        InputStream is = new URL("http://www.apache.org").openStream();
        LineIterator lt = IOUtils.lineIterator(is, UTF_8);
        while (lt.hasNext()) {
            String line = lt.nextLine();
            p(line);
        }
    }

    /**
     * static int   read(InputStream input, byte[] buffer[, int offset, int length])
     * static int   read(ReadableByteChannel input, ByteBuffer buffer)
     * static int   read(Reader input, char[] buffer[, int offset, int length])
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
        InputStream is = IOUtils.toInputStream("Hello World!", UTF_8);
        byte[] bytes = new byte[16];
        IOUtils.read(is, bytes);
//        IOUtils.readFully(is, bytes); // EOFException: Length to read: 16 actual: 11
        p(new String(bytes)); // Hello world
    }

    /**
     * static List<String>  readLines(InputStream input, Charset/String encoding)
     * static List<String>  readLines(Reader input)
     * 获得输入流的内容放入一个 List<String> 类型的容器，每一行为这个容器的一个入口
     */
    @Test
    public void readLines() throws IOException {
        InputStream is = new FileInputStream(dirPath + "IOUtils.txt");
        List<String> lines = IOUtils.readLines(is, UTF_8);
        for (String line : lines) {
            p(line);
        }
    }

    /**
     * static long	skip(InputStream input, long toSkip)
     * static long	skip(ReadableByteChannel input, long toSkip)
     * static long	skip(Reader input, long toSkip)
     * 跳过指定长度的流
     * <p>
     * static void	skipFully(InputStream input, long toSkip)
     * static void	skipFully(ReadableByteChannel input, long toSkip)
     * static void	skipFully(Reader input, long toSkip)
     * 跳过指定长度的流，如果不够跳会抛出异常
     */
    @Test
    public void skip() throws IOException {
        InputStream is = IOUtils.toInputStream("Hello World!", UTF_8);
        IOUtils.skipFully(is, 20); // EOFException: Bytes to skip: 20 actual: 11
        p(IOUtils.toString(is, UTF_8));
    }

    /**
     * static byte[]	        toByteArray(InputStream input[, int/long size])
     * static byte[]	        toByteArray(Reader input, Charset/String encoding)
     * static byte[]	        toByteArray(String input)
     * static byte[]	        toByteArray(URI uri)
     * static byte[]	        toByteArray(URL url)
     * static byte[]	        toByteArray(URLConnection urlConn)
     * static char[]	        toCharArray(InputStream is, Charset/String encoding)
     * <p>
     * static InputStream	    toInputStream(CharSequence/String input, Charset/String encoding)
     * static InputStream       toBufferedInputStream(InputStream input[, int size])
     * static BufferedReader	toBufferedReader(Reader reader[, int size])
     * <p>
     * static String	        toString(byte[] input, String encoding)
     * static String	        toString(InputStream input, Charset encoding)
     * static String	        toString(Reader input)
     * static String	        toString(URI uri, Charset/String encoding)
     * static String	        toString(URL url, Charset/String encoding)
     */
    @Test
    public void toXXX() throws IOException {
        skip();
    }

    /**
     * static void  write(byte[]/char[] char, XXX output, Charset/String encoding)
     * static void  write(CharSequence/String data, XXX output, Charset/String encoding)
     * 把数据写入到输出流
     */
    @Test
    public void write() throws IOException {
        OutputStream os = new FileOutputStream(dirPath + "write.txt");
        IOUtils.write("Hello World!", os, UTF_8);
    }

    /**
     * writeLines(Collection<?> lines, String lineEnding, OutputStream output, Charset/String encoding)
     * writeLines(Collection<?> lines, String lineEnding, Writer writer)
     * 把 List<String> 数据写入到输出流
     */
    @Test
    public void writeLines() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("A");
        lines.add("B");
        lines.add("C");
        OutputStream os = new FileOutputStream(dirPath + "writeLines.txt");
        IOUtils.writeLines(lines, IOUtils.LINE_SEPARATOR, os, UTF_8);
    }


}
