package knowledge.design.structural.decorator;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Base64;
import java.util.Objects;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * 装饰器模式：通过将对象放入特殊的封装对象，动态地给对象添加一些新的功能
 * 主要解决：
 * 1.无需修改代码的情况下，添加额外的功能
 * 2.使用继承来扩展对象行为的方案难以实现或者根本不可行
 * 使用场景：功能组合
 * 使用实例：
 * 1.Java IO：
 * -    Component:          InputStream
 * -    ConcreteComponent:  FileInputStream，ByteArrayInputStream，PipedInputStream，StringBufferInputStream
 * -    Decorator:          FilterInputStream
 * -    ConcreteDecorator:  BufferedInputStream，DataInputStream
 * 2.java.util.Collections 的 checkedXXX()、 synchronizedXXX() 和 unmodifiableXXX()
 * <p>
 * 角色：
 * 抽象部件角色 Component
 * 具体部件角色 ConcreteComponent：实现 Component，定义基础行为
 * 抽象装饰角色 Decorator：实现 Component，持有 Component 的引用（构造器接收）
 * 具体装饰角色 ConcreteDecorator：定义添加的额外行为，在调用父类方法之前或之后执行自身的行为
 * <p>
 * 优点：
 * 1.符合开闭原则
 * 2.通过使用多个 ConcreteDecorator 的不同组合，实现不同的功能
 * 3.扩展新功能只需继承 Decorator
 * <p>
 * Decorator：https://refactoringguru.cn/design-patterns/decorator
 * JAVA与模式：http://www.cnblogs.com/java-my-life/archive/2012/04/20/2455726.html
 * Java3y：https://www.zhihu.com/question/32007641/answer/687582571
 * 菜鸟教程：https://www.runoob.com/design-pattern/decorator-pattern.html
 *
 * @author Arsenal
 * created on 2020/9/26 2:51
 */
public class DecoratorDemo {

    /**
     * 案例：
     * 1.FileDataSource 仅支持简单读写
     * 2.增加加密解密，压缩解压功能
     * https://refactoringguru.cn/design-patterns/decorator/java/example
     */
    @Test
    public void testWriterAndReadFile() {
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        DataSourceDecorator encoded = new CompressionDecorator(
                new EncryptionDecorator(
                        new FileDataSource("OutputDemo.txt")));
        encoded.writeData(salaryRecords);

        DataSource plain = new FileDataSource("OutputDemo.txt");
        System.out.println("\n- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("\n- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("\n- Decoded --------------");
        System.out.println(encoded.readData());
    }

    /**
     * Component
     * 定义了读取和写入操作的通用数据接口
     */
    interface DataSource {
        void writeData(String data);

        String readData();
    }

    /**
     * ConcreteComponent
     * 简单数据读写器
     */
    static class FileDataSource implements DataSource {
        private final String name;

        public FileDataSource(String name) {
            this.name = name;
        }

        @Override
        public void writeData(String data) {
            File file = new File(name);
            try (OutputStream fos = new FileOutputStream(file)) {
                fos.write(data.getBytes(), 0, data.length());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        @Override
        public String readData() {
            char[] buffer = null;
            File file = new File(name);
            try (FileReader reader = new FileReader(file)) {
                buffer = new char[(int) file.length()];
                reader.read(buffer);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            return new String(Objects.requireNonNull(buffer));
        }
    }

    /**
     * Decorator
     * 抽象装饰
     */
    static class DataSourceDecorator implements DataSource {
        private final DataSource wrappee;

        DataSourceDecorator(DataSource source) {
            this.wrappee = source;
        }

        @Override
        public void writeData(String data) {
            wrappee.writeData(data);
        }

        @Override
        public String readData() {
            return wrappee.readData();
        }
    }

    /**
     * ConcreteDecorator
     * 加密装饰
     */
    static class EncryptionDecorator extends DataSourceDecorator {

        public EncryptionDecorator(DataSource source) {
            super(source);
        }

        @Override
        public void writeData(String data) {
            super.writeData(encode(data));
        }

        @Override
        public String readData() {
            return decode(super.readData());
        }

        private String encode(String data) {
            byte[] result = data.getBytes();
            for (int i = 0; i < result.length; i++) {
                result[i] += (byte) 1;
            }
            return Base64.getEncoder().encodeToString(result);
        }

        private String decode(String data) {
            byte[] result = Base64.getDecoder().decode(data);
            for (int i = 0; i < result.length; i++) {
                result[i] -= (byte) 1;
            }
            return new String(result);
        }
    }

    /**
     * ConcreteDecorator
     * 压缩装饰
     */
    @Getter
    @Setter
    static class CompressionDecorator extends DataSourceDecorator {
        private int compLevel = 6;

        public CompressionDecorator(DataSource source) {
            super(source);
        }

        @Override
        public void writeData(String data) {
            super.writeData(compress(data));
        }

        @Override
        public String readData() {
            return decompress(super.readData());
        }

        private String compress(String stringData) {
            byte[] data = stringData.getBytes();
            try {
                ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
                DeflaterOutputStream dos = new DeflaterOutputStream(bout, new Deflater(compLevel));
                dos.write(data);
                dos.close();
                bout.close();
                return Base64.getEncoder().encodeToString(bout.toByteArray());
            } catch (IOException ex) {
                return null;
            }
        }

        private String decompress(String stringData) {
            byte[] data = Base64.getDecoder().decode(stringData);
            try {
                InputStream in = new ByteArrayInputStream(data);
                InflaterInputStream iin = new InflaterInputStream(in);
                ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
                int b;
                while ((b = iin.read()) != -1) {
                    bout.write(b);
                }
                in.close();
                iin.close();
                bout.close();
                return bout.toString();
            } catch (IOException ex) {
                return null;
            }
        }
    }

}
