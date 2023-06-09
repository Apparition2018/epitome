package knowledge.io.stream;

import l.demo.Demo;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * ByteStream 字节流
 * <p>关闭流：
 * <pre>
 * <a href="https://mp.weixin.qq.com/s/MR270MtIIb7B9IkLUbgJAw">IO 操作为什么必须关闭流？</a>
 * <a href="https://mp.weixin.qq.com/s/aEgNbN-Ix83NvcE3rQ8TgA">为什么需要主动关闭文件流？</a>
 * <a href="https://blog.csdn.net/q13315149158/article/details/79277403">Java IO 包装流如何关闭</a>
 * <a href="https://blog.csdn.net/u012426327/article/details/77160400">IO 流详解</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/9/14 11:02
 */
public class ByteStream extends Demo {

    /**
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/io/InputStream.html">InputStream</a>
     * <p>字节输入流的所有类的超类
     * <pre>
     * void             close()                 关闭此输入流并释放与该流关联的所有系统资源
     * void             mark(int readlimit)     在此输入流中标记当前的位置
     * void             reset()                 将此流重新定位到最后一次对此输入流调用 mark 方法时的位置
     * boolean          markSupported()         测试此输入流是否支持 mark 和 reset 方法
     * long             skip(long n)            跳过和丢弃此输入流中数据的 n 个字节
     * int              available()             返回此输入流下一个方法调用可以不受阻塞地从此输入流读取（或跳过）的估计字节数
     * </pre>
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/io/FileInputStream.html">FileInputStream</a>   文件输入流
     * <p>从文件系统中的某个文件中获得输入字节。哪些文件可用取决于主机环境。
     * <pre>
     * FileChannel      getChannel()            返回与此文件输入流有关的唯一 FileChannel 对象
     * FileDescriptor   getFD()                 返回表示到文件系统中实际文件的连接的 FileDescriptor 对象，该文件系统正被此 FileInputStream 使用<p>
     * </pre>
     * 乱码解决方法：使用转换流 InputStreamReader
     * <p>
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/io/BufferedInputStream.html">BufferedInputStream</a>    缓冲输入流
     */
    @Test
    public void testInputStream() {
        // FileInputStream(File file)               通过打开一个到实际文件的连接来创建一个 FileInputStream，该文件通过文件系统中的 File 对象 file 指定
        // FileInputStream(String name)             通过打开一个到实际文件的连接来创建一个 FileInputStream，该文件通过文件系统中的路径名 name 指定
        // FileInputStream(FileDescriptor fdObj)    通过使用文件描述符 fdObj 创建一个 FileInputStream，该文件描述符表示到文件系统中某个实际文件的现有连接
        try (FileInputStream fis = new FileInputStream(DEMO_DIR_PATH + "Input")) {
            byte[] data = new byte[256];
            StringBuilder sb = new StringBuilder();
            int len;
            // abstract  int	read()                              从输入流中读取数据的下一个字节
            // int	            read(byte[] b[, int off, int len])  将输入流中最多 len 个数据字节读入 byte 数组
            while ((len = fis.read(data)) != -1) {
                sb.append(new String(data, 0, len, StandardCharsets.UTF_8));
            }

            p(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/io/OutputStream.html">OutputStream</a>
     * <pre>
     * 输出字节流的所有类的超类
     * void	    close()     关闭此输出流并释放与此流有关的所有系统资源
     * </pre>
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/io/FileOutputStream.html">FileOutputStream</a> 文件输出流
     * <p>用于将数据写入 File 或 FileDescriptor 的输出流
     * <p>
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/io/BufferedOutputStream.html">BufferedOutputStream</a> 缓冲输出流
     */
    @Test
    public void testOutputStream() {
        // FileOutputStream(File file[, boolean append])            创建一个向指定 File 对象表示的文件中写入数据的文件输出流
        // FileOutputStream(String name[, boolean append])          创建一个向具有指定 name 的文件中写入数据的输出文件流
        // FileOutputStream(FileDescriptor fdObj)                   创建一个向指定文件描述符处写入数据的输出文件流，该文件描述符表示一个到文件系统中的某个实际文件的现有连接
        try (OutputStream os = new BufferedOutputStream(Files.newOutputStream(Paths.get(DEMO_DIR_PATH + "Output")))) {
            String outputString = "Output Output Output Output Output ";
            // abstract  void	write(int b)                        将指定的字节写入此输出流
            // void	            write(byte[] b[, int off, int len]) 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此输出流
            os.write(outputString.getBytes(StandardCharsets.UTF_8));
            //  void	        flush()                             刷新此输出流并强制写出所有缓冲的输出字节
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/io/ByteArrayInputStream.html">ByteArrayInputStream</a> 字节数组输入流
     * <p>包含一个内部缓冲区，该缓冲区包含从流中读取的字节
     * <p>
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/io/ByteArrayOutputStream.html">ByteArrayOutputStream</a>   字节数组输出流
     * <pre>
     * 实现了一个输出流，其中的数据被写入一个 byte 数组。缓冲区会随着数据的不断写入而自动增长。
     * 可使用 toByteArray() 和 toString() 获取数据。
     * </pre>
     * <p>主动 close 不再是必须的：因为字节数组流是基于内存的，并没有占用硬盘、网络等资源，GC 可以对其进行回收
     */
    @Test
    public void testByteArrayStream() {
        // ByteArrayInputStream(byte[] buf[, int offset, int length])
        // 创建 ByteArrayInputStream，使用 buf 作为其缓冲区数组
        ByteArrayInputStream bais = new ByteArrayInputStream("Input Input Input Input Input ".getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int data;
        while ((data = bais.read()) != -1) {
            baos.write(data);
        }

        // 第一种获取数据：toString()
        p(baos + StringUtils.CR);

        // 第二种获取数据：toByteArray()
        // byte[]	        toByteArray()                   创建一个新分配的 byte 数组
        p(new String(baos.toByteArray(), StandardCharsets.UTF_8));
    }

    /**
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/io/DataInputStream.html">DataInputStream</a>   数据输入流
     * <p>以与机器无关方式从底层输入流中读取基本 Java 数据类型
     * <p>
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/io/DataOutputStream.html">DataOutputStream</a>
     * <p>以适当方式将基本 Java 数据类型写入输出流中
     */
    @Test
    public void testDataStream() {
        try (DataOutputStream dos = new DataOutputStream(Files.newOutputStream(Paths.get(DEMO_DIR_PATH + "io.dat")));
             DataInputStream dis = new DataInputStream(Files.newInputStream(Paths.get(DEMO_DIR_PATH + "io.dat")))) {
            // void	    writeXXX(XXX v)     将一个 xxx 值以 xxx 值形式写入基础输出流
            dos.writeInt(10);
            dos.writeUTF("io");
            dos.flush();

            // char	    readXXX()           读取两个输入字节并返回一个 xxx 值
            p(dis.readInt());
            p(dis.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/io/ObjectInputStream.html">ObjectInputStream</a>   对象输入流
     * <p>对象输入流读取的内容必须是通过 ObjectOutputStream 序列化的对象
     * <p>
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/io/ObjectOutputStream.html">ObjectOutputStream</a> 对象输出流
     */
    @Test
    public void testObjectStream() {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(DEMO_DIR_PATH + "person.obj")));
             ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(DEMO_DIR_PATH + "person.obj")))) {

            /*
             * 将给定对象转换为一组字节后写出
             *
             *   将对象转换为字节
             *        |
             * 对象 → oos → fos → 文件
             * 				 |
             * 		    将字节转换为文件
             *
             * 将一个对象转换为一组字节码到文件中经理了两个步骤：
             * 	1）对象序列化：将一个对象转换为一组字节
             * 	2）持久化：将这组字节码写入文件（磁盘中）长久保存
             */
            // void	        writeObject(Object obj)     将指定的对象写入 ObjectOutputStream
            oos.writeObject(personList.get(0));
            p("对象写出完毕！");

            // Object	    readObject()                从 ObjectInputStream 读取对象
            p(ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
