package knowledge.api.io.stream.byte_;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ByteStream {

    private final static String dirPath = "src/main/java/knowledge/api/io/stream/byte_/";

    // InputStream

    /**
     * abstract  int	read()
     * 从输入流中读取数据的下一个字节
     * <p>
     * int	            read(byte[] b)
     * 从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中
     * <p>
     * int	            read(byte[] b, int off, int len)
     * 将输入流中最多 len 个数据字节读入 byte 数组
     */
    @Test
    public void read() {
        fileInputStream();
    }

    /**
     * int	            available()
     * 返回此输入流下一个方法调用可以不受阻塞地从此输入流读取（或跳过）的估计字节数
     */
    @Test
    public void available() {
        InputStream is = null;
        try {
            is = new FileInputStream(dirPath + "InputStream.txt");

            byte[] data = new byte[4];

            while (is.read(data) != -1) {
                int available = is.available();
                System.out.println(available); // 22 18 14 10 6 2 0
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    // OutputStream

    /**
     * void	            write(byte[] b)                     将 b.length 个字节从指定的 byte 数组写入此输出流
     * void	            write(byte[] b, int off, int len)   将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此输出流
     * abstract  void	write(int b)                        将指定的字节写入此输出流
     */
    @Test
    public void write() {
        fileOutputStream();
    }

    /**
     * void	            flush()         刷新此输出流并强制写出所有缓冲的输出字节
     */
    @Test
    public void flush() {
        bufferedOutputStream();
    }

    // FileInputStream

    /**
     * FileInputStream                 文件输入流
     * FileInputStream(File file)      通过打开一个到实际文件的连接来创建一个 FileInputStream，该文件通过文件系统中的 File 对象 file 指定
     * FileInputStream(String name)    通过打开一个到实际文件的连接来创建一个 FileInputStream，该文件通过文件系统中的路径名 name 指定
     */
    @Test
    public void fileInputStream() {
        InputStream is = null;
        try {
            is = new FileInputStream(dirPath + "InputStream.txt");

            byte[] data = new byte[256];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = is.read(data)) != -1) {
                sb.append(new String(data, 0, len, StandardCharsets.UTF_8));
            }

            System.out.println(sb);
            // 会有乱码，解决方法如下：
            // 方法一：使用转换流 InputStreamReader
            // 方法二：扩大缓冲区容量，一次性存储完整个内容 byte[] data = new byte[1024]
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    // FileOutputStream

    /**
     * FileOutputStream                                 文件输出流
     * FileOutputStream(File file[, boolean append])    创建一个向指定 File 对象表示的文件中写入数据的文件输出流
     * FileOutputStream(String name[, boolean append])  创建一个向具有指定 name 的文件中写入数据的输出文件流
     */
    @Test
    public void fileOutputStream() {
        // 1.创建 os 对象
        OutputStream os = null;
        try {
            os = new FileOutputStream(dirPath + "FileOutputStream.txt");

            String s = "FileOutputStream 用于写入诸如图像数据之类的原始字节的流。要写入字符流，请考虑使用 FileWriter。";
            // 2.写入操作
            os.write(s.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }

    }

    // BufferedOutputStream

    /**
     * BufferedOutputStream                                 缓冲输出流
     * BufferedOutputStream(OutputStream out[, int size])   创建一个新的缓冲输出流，以将具有指定缓冲区大小的数据写入指定的底层输出流
     */
    @Test
    public void bufferedOutputStream() {
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(dirPath + "BufferedOutputStream.txt"));

            String s = "该类实现缓冲的输出流。通过设置这种输出流，应用程序就可以将各个字节写入底层输出流中，而不必针对每次字节写入调用底层系统。";
            os.write(s.getBytes(StandardCharsets.UTF_8));

            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

    // ByteArrayInputStream

    /**
     * ByteArrayInputStream                                         字节数组输入流
     * ByteArrayInputStream(byte[] buf[, int offset, int length])   创建一个 ByteArrayInputStream，使用 buf 作为其缓冲区数组
     */
    @Test
    public void ByteArrayInputStream() {

        String str = "abcdefghijklmnopqrstuvwxyz";
        byte[] bytes = str.getBytes();

        InputStream is = new ByteArrayInputStream(bytes);

        int data;
        StringBuilder sb = new StringBuilder();

        try {
            while ((data = is.read()) != -1) {
                sb.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(sb);

    }

    // ByteArrayOutputStream

    /**
     * ByteArrayOutputStream            字节数组输出流
     * ByteArrayOutputStream()          创建一个新的 byte 数组输出流
     * ByteArrayOutputStream(int size)  创建一个新的 byte 数组输出流，它具有指定大小的缓冲区容量（以字节为单位）
     */
    @Test
    public void ByteArrayOutputStream() {
        InputStream is = null;

        // 创建字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            // 创建文件输入流读取文件
            is = new FileInputStream(dirPath + "ByteArrayOutputStream.txt");

            // 将文件数据写入到 ByteArrayOutputStream，转化为输出流
            byte[] buf = new byte[256];
            int len;
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }

            // 第一种获取数据：toString()
            // System.out.println(baos);

            // 第二种获取数据：toByteArray()
            byte[] data = baos.toByteArray();
            String str = new String(data, StandardCharsets.UTF_8);
            System.out.println(str);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    /**
     * byte[]	toByteArray()
     * 创建一个新分配的 byte 数组
     */
    @Test
    public void toByteArray() {
        ByteArrayOutputStream();
    }

    // ObjectInputStream

    /**
     * ObjectInputStream                    对象输入流
     * ObjectInputStream(InputStream in)    创建从指定 InputStream 读取的 ObjectInputStream
     * <p>
     * 对象输入流读取的内容必须是通过 ObjectOutputStream 序列化的对象
     */
    @Test
    public void objectInputStream() {
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(dirPath + "person.obj"));

            Person p = (Person) ois.readObject();

            System.out.println(p.getName());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(ois);
        }
    }

    /**
     * Object	readObject()
     * 从 ObjectInputStream 读取对象
     */
    @Test
    public void readObject() {
        objectInputStream();
    }

    // ObjectOutputStream

    /**
     * ObjectOutputStream                   对象输出流
     * ObjectOutputStream(OutputStream out) 创建写入指定 OutputStream 的 ObjectOutputStream
     */
    @Test
    public void objectOutputStream() {
        Person p = new Person();
        p.setName("松岛枫");
        p.setGender("女");
        p.setAge(18);

        List<String> otherInfo = new ArrayList<>();
        otherInfo.add("是一名演员");
        otherInfo.add("促进中日文化交流");
        otherInfo.add("广大男性同胞的启蒙老师");

        p.setOtherInfo(otherInfo);

        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(dirPath + "person.obj"));

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
            oos.writeObject(p);

            System.out.println("对象写出完毕！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(oos);
        }

    }

    /**
     * void	writeObject(Object obj)
     * 将指定的对象写入 ObjectOutputStream
     */
    @Test
    public void writeObject() {
        objectOutputStream();
    }

}
