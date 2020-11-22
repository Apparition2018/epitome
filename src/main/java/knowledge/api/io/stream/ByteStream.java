package knowledge.api.io.stream;

import l.demo.Demo;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * ByteStream
 * 字节流
 * <p>
 * Java IO包装流如何关闭？:https://blog.csdn.net/q13315149158/article/details/79277403
 * https://blog.csdn.net/u012426327/article/details/77160400
 *
 * @author ljh
 * created on 2020/9/14 11:02
 */
public class ByteStream extends Demo {

    /**
     * InputStream
     * 字节输入流的所有类的超类
     * https://www.runoob.com/manual/jdk1.6/java/io/InputStream.html
     * void	            close()                 关闭此输入流并释放与该流关联的所有系统资源
     * void	            mark(int readlimit)     在此输入流中标记当前的位置
     * void	            reset()                 将此流重新定位到最后一次对此输入流调用 mark 方法时的位置
     * boolean	        markSupported()         测试此输入流是否支持 mark 和 reset 方法
     * long	            skip(long n)            跳过和丢弃此输入流中数据的 n 个字节
     * int	            available()             返回此输入流下一个方法调用可以不受阻塞地从此输入流读取（或跳过）的估计字节数
     * <p>
     * FileInputStream          文件输入流
     * 从文件系统中的某个文件中获得输入字节。哪些文件可用取决于主机环境。
     * https://www.runoob.com/manual/jdk1.6/java/io/FileInputStream.html
     * FileChannel	    getChannel()            返回与此文件输入流有关的唯一 FileChannel 对象
     * FileDescriptor	getFD()                 返回表示到文件系统中实际文件的连接的 FileDescriptor 对象，该文件系统正被此 FileInputStream 使用<p>
     * <p>
     * 乱码解决方法：
     * 1.使用转换率 InputStreamReader
     * 2.扩大缓冲区容量，一次性存储完整个内容 byte[] data = new byte[1024]
     */
    @Test
    public void testInputStream() {
        // FileInputStream(File file)               通过打开一个到实际文件的连接来创建一个 FileInputStream，该文件通过文件系统中的 File 对象 file 指定
        // FileInputStream(String name)             通过打开一个到实际文件的连接来创建一个 FileInputStream，该文件通过文件系统中的路径名 name 指定
        // FileInputStream(FileDescriptor fdObj)    通过使用文件描述符 fdObj 创建一个 FileInputStream，该文件描述符表示到文件系统中某个实际文件的现有连接
        try (FileInputStream fis = new FileInputStream(DEMO_PATH + "Input")) {
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
     * OutputStream
     * 输出字节流的所有类的超类
     * https://www.runoob.com/manual/jdk1.6/java/io/OutputStream.html
     * void	    close()     关闭此输出流并释放与此流有关的所有系统资源
     * <p>
     * FileOutputStream         文件输出流
     * 用于将数据写入 File 或 FileDescriptor 的输出流
     * https://www.runoob.com/manual/jdk1.6/java/io/FileOutputStream.html
     * <p>
     * BufferedOutputStream     缓冲输出流
     * https://www.runoob.com/manual/jdk1.6/java/io/BufferedOutputStream.html
     */
    @Test
    public void testOutputStream() {
        // FileOutputStream(File file[, boolean append])            创建一个向指定 File 对象表示的文件中写入数据的文件输出流
        // FileOutputStream(String name[, boolean append])          创建一个向具有指定 name 的文件中写入数据的输出文件流
        // FileOutputStream(FileDescriptor fdObj)                   创建一个向指定文件描述符处写入数据的输出文件流，该文件描述符表示一个到文件系统中的某个实际文件的现有连接
        try (OutputStream os = new BufferedOutputStream(new FileOutputStream(DEMO_PATH + "Output"))) {
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
     * ByteArrayInputStream     字节数组输入流
     * 包含一个内部缓冲区，该缓冲区包含从流中读取的字节
     * https://www.runoob.com/manual/jdk1.6/java/io/ByteArrayInputStream.html
     * https://www.cnblogs.com/zhangj-ymm/p/9842657.html
     * <p>
     * ByteArrayOutputStream    字节数组输出流
     * 实现了一个输出流，其中的数据被写入一个 byte 数组。缓冲区会随着数据的不断写入而自动增长。
     * 可使用 toByteArray() 和 toString() 获取数据。
     * https://www.runoob.com/manual/jdk1.6/java/io/ByteArrayOutputStream.html
     * <p>
     * 字节数组流不需要关闭
     */
    @Test
    public void testByteArrayXXXStream() {
        // ByteArrayInputStream(byte[] buf[, int offset, int length])
        // 创建 ByteArrayInputStream，使用 buf 作为其缓冲区数组
        ByteArrayInputStream bais = new ByteArrayInputStream("Input Input Input Input Input ".getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int data;
        while ((data = bais.read()) != -1) {
            baos.write(data);
        }

        // 第一种获取数据：toString()
        p(baos + "\n");

        // 第二种获取数据：toByteArray()
        // byte[]	        toByteArray()                   创建一个新分配的 byte 数组
        p(new String(baos.toByteArray(), StandardCharsets.UTF_8));
    }

    /**
     * DataInputStream          数据输入流
     * 以与机器无关方式从底层输入流中读取基本 Java 数据类型
     * https://www.runoob.com/manual/jdk1.6/java/io/DataInputStream.html
     * <p>
     * DataOutputStream
     * 以适当方式将基本 Java 数据类型写入输出流中
     * https://www.runoob.com/manual/jdk1.6/java/io/DataOutputStream.html
     */
    @Test
    public void testDataXXXStream() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(DEMO_PATH + "io.dat"));
             DataInputStream dis = new DataInputStream(new FileInputStream(DEMO_PATH + "io.dat"))) {
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
     * ObjectInputStream        对象输入流
     * https://www.runoob.com/manual/jdk1.6/java/io/ObjectInputStream.html
     * 对象输入流读取的内容必须是通过 ObjectOutputStream 序列化的对象
     * <p>
     * ObjectOutputStream       对象输出流
     * https://www.runoob.com/manual/jdk1.6/java/io/ObjectOutputStream.html
     */
    @Test
    public void testObjectXXXStream() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DEMO_PATH + "person.obj"));
             ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DEMO_PATH + "person.obj"))) {

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
