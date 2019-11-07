package knowledge.api.io.stream.char_;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CharStream {

    private final static String dirPath = "src/main/java/knowledge/api/io/stream/char_/";

    // FileReader

    /**
     * FileReader(File file)        在给定从中读取数据的 File 的情况下创建一个新 FileReader
     * FileReader(String fileName)  在给定从中读取数据的文件名的情况下创建一个新 FileReader
     */
    @Test
    public void fileReader() {
        Reader reader = null;
        try {
            reader = new FileReader(dirPath + "Reader.txt");

            char[] data = new char[64];
            StringBuilder sb = new StringBuilder();

            while (reader.read(data) != -1) {
                sb.append(new String(data));
            }

            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    // FileWriter

    /**
     * FileWriter(File file[, boolean append])      根据给定的 File 对象构造一个 FileWriter 对象
     * FileWriter(String fileName[, boolean append])根据给定的文件名以及指示是否附加写入数据的 boolean 值来构造 FileWriter 对象
     */
    @Test
    public void fileWriter() {
        // 1.创建 writer 对象
        Writer writer = null;
        try {
            writer = new FileWriter(dirPath + "FileWriter.txt");

            String s = "用来写入字符文件的便捷类。此类的构造方法假定默认字符编码和默认字节缓冲区大小都是可接受的。要自己指定这些值，可以先在 FileOutputStream 上构造一个 OutputStreamWriter。\n" +
                    "\n" +
                    "文件是否可用或是否可以被创建取决于底层平台。特别是某些平台一次只允许一个 FileWriter（或其他文件写入对象）打开文件进行写入。在这种情况下，如果所涉及的文件已经打开，则此类中的构造方法将失败。\n" +
                    "\n" +
                    "FileWriter 用于写入字符流。要写入原始字节流，请考虑使用 FileOutputStream。";

            // 2.写入操作
            writer.write(s.toCharArray());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    // InputStreamReader

    /**
     * InputStreamReader
     * 转换流
     * <p>
     * InputStreamReader(InputStream in)                        创建一个使用默认字符集的 InputStreamReader
     * InputStreamReader(InputStream in, Charset cs)            创建使用给定字符集的 InputStreamReader
     * InputStreamReader(InputStream in, CharsetDecoder dec)    创建使用给定字符集解码器的 InputStreamReader
     * InputStreamReader(InputStream in, String charsetName)    创建使用指定字符集的 InputStreamReader
     */
    @Test
    public void inputStreamReader() {
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(dirPath + "Reader.txt"), StandardCharsets.UTF_8);

            char[] data = new char[64];
            StringBuilder sb = new StringBuilder();
            while (reader.read(data) != -1) {
                sb.append(new String(data));
            }

            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }

    }

    // BufferReader

    /**
     * BufferedReader
     * 缓冲字符输入流
     * <p>
     * BufferedReader(Reader in[, int sz])
     * 创建一个使用指定大小输入缓冲区的缓冲字符输入流，可以按行读取字符串
     */
    @Test
    public void bufferedReader() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(dirPath + "Reader.txt"));

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(br);
        }
    }

    /**
     * String	readLine()
     * 读取一个文本行
     */
    @Test
    public void readLine() {
        bufferedReader();
    }

    // PrintWriter

    /**
     * PrintWriter
     * 缓冲字符输出流
     * <p>
     * PrintWriter(File file[, String csn])                 创建指定文件和字符集且不带自动刷行新的新 PrintWriter
     * PrintWriter(String fileName[, String csn])           创建指定文件名称和字符集且不带自动行刷新的新 PrintWriter
     * PrintWriter(OutputStream out[, boolean autoFlush])   通过现有的 OutputStream 创建新的 PrintWriter
     * PrintWriter(Writer out[, boolean autoFlush])         创建新 PrintWriter
     * <p>
     * 可以按行写出字符串，并且具有自动行刷新功能
     */
    @Test
    public void printWriter() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(dirPath + "PrintWriter.txt")), true);

            pw.println("向文本输出流打印对象的格式化表示形式。此类实现在 PrintStream 中的所有 print 方法。它不包含用于写入原始字节的方法，对于这些字节，程序应该使用未编码的字节流进行写入。");
            pw.println("与 PrintStream 类不同，如果启用了自动刷新，则只有在调用 println、printf 或 format 的其中一个方法时才可能完成此操作，而不是每当正好输出换行符时才完成。这些方法使用平台自有的行分隔符概念，而不是换行符。");
            pw.println("此类中的方法不会抛出 I/O 异常，尽管其某些构造方法可能抛出异常。客户端可能会查询调用 checkError() 是否出现错误。");

            System.out.println("写出完毕！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(pw);
        }

    }

    /**
     * void	println([XXX xxx])
     * 打印 XXX，然后终止该行
     */
    @Test
    public void println() {
        printWriter();
    }

    // CharArrayReader

    /**
     * CharArrayReader
     * <p>
     * CharArrayReader(char[] buf[, int offset, int length])
     * 根据指定的 char 数组创建一个 CharArrayReader
     */
    @Test
    public void charArrayReader() {
        String str = "abcdefghijklmnopqrstuvwxyz";
        char[] chars = str.toCharArray();

        // 把字符数组写入到 CharArrayReader
        Reader reader = new CharArrayReader(chars);

        int data;
        StringBuilder sb = new StringBuilder();

        try {
            while ((data = reader.read()) != -1) {
                sb.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(sb);
    }

    // CharArrayWriter

    /**
     * CharArrayWriter([int initialSize])
     * 创建一个具有指定初始大小的新 CharArrayWriter
     */
    @Test
    public void charArrayWriter() {
        Reader reader = null;

        // 创建字符数组输出流
        CharArrayWriter writer = new CharArrayWriter();

        try {
            reader = new FileReader(dirPath + "CharArrayWriter.txt");

            // 将文件数据写入到 CharArrayWriter
            char[] buf = new char[256];
            int len;
            while ((len = reader.read(buf)) != -1) {
                writer.write(buf, 0, len);
            }

            // 写入的数据以字符数组的形式返回
            char[] data = writer.toCharArray();

            String str = new String(data);
            System.out.println(str);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    /**
     * char[]	toCharArray()
     * 返回输入数据的副本
     */
    @Test
    public void toCharArray() {
        charArrayWriter();
    }


}
