package knowledge.api.io.stream;

import l.demo.Demo;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * CharStream
 * 字符流
 * https://blog.csdn.net/u012426327/article/details/77160400
 *
 * @author ljh
 * created on 2020/9/14 14:15
 */
public class CharStream extends Demo {

    /**
     * Reader
     * https://jdk6.net/io/Reader.html
     * abstract  void	close()                     关闭该流并释放与之关联的所有资源
     * void	            mark(int readAheadLimit)    标记流中的当前位置
     * void	            reset()                     重置该流
     * boolean	        markSupported()             判断此流是否支持 mark() 操作
     * boolean	        ready()                     判断是否准备读取此流
     * long	            skip(long n)                跳过字符
     * <p>
     * InputStreamReader
     * 字节流通向字符流的桥梁：它使用指定的 charset 读取字节并将其解码为字符。
     * https://jdk6.net/io/InputStreamReader.html
     * <p>
     * FileReader
     * 用来读取字符文件的便捷类。此类的构造方法假定默认字符编码和默认字节缓冲区大小都是适当的。
     * 要自己指定这些值，可以先在 FileInputStream 上构造一个 InputStreamReader。
     * https://jdk6.net/io/FileReader.html
     * <p>
     * BufferedReader       缓冲字符输入流
     * 从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
     * https://jdk6.net/io/BufferedReader.html
     */
    @Test
    public void testReader() {
        // InputStreamReader(InputStream in[, Charset cs/CharsetDecoder dec/String charsetName])
        // 创建使用给定字符集/字符集解码器的 InputStreamReader
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(DEMO_PATH + "Input"), StandardCharsets.UTF_8);
             // FileReader(File file)                   在给定从中读取数据的 File 的情况下创建一个新 FileReader
             // FileReader(String fileName)             在给定从中读取数据的文件名的情况下创建一个新 FileReader
             // FileReader(FileDescriptor fd)           在给定从中读取数据的 FileDescriptor 的情况下创建一个新 FileReader
             FileReader fr = new FileReader(DEMO_PATH + "Input");
             // BufferedReader(Reader in[, int sz])     创建一个使用指定大小输入缓冲区的缓冲字符输入流
             BufferedReader br = new BufferedReader(new FileReader(DEMO_PATH + "Input"))) {

            char[] data = new char[64];

            /* InputStreamReader */
            StringBuilder isrString = new StringBuilder();
            // int	            read([char[] cbuf]/CharBuffer target)   将字符读入数组/字符缓冲区
            // abstract  int	read(char[] cbuf, int off, int len)     将字符读入数组的某一部分
            while (isr.read(data) != -1) {
                isrString.append(new String(data));
            }
            p(isrString + "\n");

            /* FileReader */
            StringBuilder frString = new StringBuilder();
            while (fr.read(data) != -1) {
                frString.append(new String(data));
            }
            p(frString + "\n");

            /* BufferedReader */
            String line;
            StringBuilder brString = new StringBuilder();
            // String	        readLine()                              读取一个文本行
            while ((line = br.readLine()) != null) {
                brString.append(line);
            }
            p(brString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writer
     * https://jdk6.net/io/Writer.html
     * Writer	        append(CharSequence csq[, int start, int end])  将指定字符序列的子序列添加到此 writer.Appendable
     * abstract  void	close()                         关闭此流，但要先刷新它
     * <p>
     * OutputStreamWriter
     * 字符流通向字节流的桥梁：可使用指定的 charset 将要写入流中的字符编码成字节。
     * https://jdk6.net/io/OutputStreamWriter.html
     * <p>
     * FileWriter
     * 用来写入字符文件的便捷类。此类的构造方法假定默认字符编码和默认字节缓冲区大小都是可接受的。
     * 要自己指定这些值，可以先在 FileOutputStream 上构造一个 OutputStreamWriter。
     * https://jdk6.net/io/FileWriter.html
     * <p>
     * PrintWriter      缓冲字符输出流
     * 可以按行写出字符串，并且具有自动行刷新功能
     * https://jdk6.net/io/PrintWriter.html
     */
    @Test
    public void testWriter() {
        try (Writer writer = new FileWriter(DEMO_PATH + "Output");
             // PrintWriter(OutputStream out, boolean autoFlush)        通过现有的 OutputStream 创建新的 PrintWriter
             PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(DEMO_PATH + "Output")), true)) {

            /* FileWriter */
            // void	            write([char[] cbuf]/int c)              写入字符数组/单个字符
            // void	            write(String str[, int off, int len])   写入字符串的某一部分
            // abstract  void	write(char[] cbuf, int off, int len)    写入字符数组的某一部分
            writer.write("Output Output Output Output Output ".toCharArray());
            // abstract  void   flush()                                 刷新该流的缓冲
            writer.flush();

            /* PrintWriter */
            // void	            println(XXX x)                          打印 XXX，然后终止该行
            pw.println("Output");
            pw.println("Output");
            pw.println("Output");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * CharArrayReader
     * 用作字符输入流的字符缓冲区
     * https://jdk6.net/io/CharArrayReader.html
     * <p>
     * CharArrayWriter
     * 用作 Writer 的字符缓冲区。缓冲区会随向流中写入数据而自动增长。
     * 可使用 toCharArray() 和 toString() 获取数据。
     * 不需要关闭。
     * https://jdk6.net/io/CharArrayWriter.html
     */
    @Test
    public void testCharArray() {
        // CharArrayReader(char[] buf[, int offset, int length])        根据指定的 char 数组创建一个 CharArrayReader
        // CharArrayWriter([int initialSize])                           创建一个具有指定初始大小的新 CharArrayWriter
        CharArrayReader car = new CharArrayReader("Input Input Input Input Input ".toCharArray());
        CharArrayWriter caw = new CharArrayWriter();

        try {
            int len;
            char[] buffer = new char[256];
            while ((len = car.read(buffer)) != -1) {
                caw.write(buffer, 0, len);
            }

            // 第一种获取数据：toString()
            p(caw + "\n");

            // 第二种获取数据：toByteArray()
            // char[]	    toCharArray()           返回输入数据的副本
            p(new String(caw.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
