package knowledge.design.pattern.gof.structural.decorator;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.xml.BeanDefinitionDecorator;
import org.springframework.cache.transaction.TransactionAwareCacheDecorator;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;

import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Collections;
import java.util.Objects;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * 装饰器模式：通过将对象放入特殊的封装对象，动态地给对象添加一些新的功能
 * <p>使用场景：功能组合
 * <pre>
 * 1 业务逻辑可用一个基本组件及多个额外可选层表示
 * 2 使用继承来扩展对象行为的方案难以实现或者根本不可行
 * </pre>
 * 使用实例：
 * <pre>
 * 1 Java IO：
 *      Component:          {@link InputStream} {@link OutputStream} {@link Reader} {@link Writer}
 *      ConcreteComponent:  {@link FileInputStream} {@link ByteArrayInputStream} {@link PipedInputStream}
 *      Decorator:          {@link FilterInputStream}
 *      ConcreteDecorator:  {@link BufferedInputStream} {@link DataInputStream}
 * 2 {@link Collections} 的 checkedXXX()、 synchronizedXXX() 和 unmodifiableXXX()
 * 3 {@link HttpServletRequestWrapper} 和 {@link HttpServletResponseWrapper}
 * 4 {@link BeanDefinitionDecorator}
 * 5 {@link ServerHttpRequestDecorator} 和 {@link ServerHttpResponseDecorator}
 * 6 {@link TransactionAwareCacheDecorator}
 * </pre>
 * 角色：
 * <pre>
 * 抽象部件 Component：所有角色的顶级接口，定义通用方法
 * 具体部件 ConcreteComponent：定义基础行为
 * 抽象装饰 Decorator：实现 Component，接收 Component 的引用（构造器接收）
 * 具体装饰 ConcreteDecorator：定义添加到 Component 的额外行为，在调用父类方法之前或之后执行自身的行为
 * </pre>
 * <p>
 * 优点：符合开闭原则
 * <p>简化：如果只有一个 ConcreteComponent，可考虑去掉 Component；如果只有一个 ConcreteDecorator，可考虑去掉 Decorator
 * 半透性：
 * <pre>
 * 1 透明装饰模式：Client 应该将对象全部声明为 Component，而不是 ConcreteComponent 或 ConcreteDecorator
 * 2 半透明装饰模式：为了调用 ConcreteDecorator 新增的方法而不得不把对象声明为 ConcreteDecorator，缺点无法再次装饰
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://refactoringguru.cn/design-patterns/decorator">Decorator</a>
 * <a href="http://c.biancheng.net/view/1366.html">Java设计模式</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/9/26 2:51
 */
public class DecoratorDemo {

    /**
     * <a href="https://refactoringguru.cn/design-patterns/decorator/java/example">编码和压缩装饰</a>
     * <pre>
     * 1 FileDataSource 仅支持简单读写
     * 2 增加加密解密，压缩解压功能
     * </pre>
     */
    @Test
    public void testDecorator() {
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        DataSource dataSource = new CompressionDecorator(
                new EncryptionDecorator(
                        new FileDataSource("OutputDemo.txt")));
        dataSource.writeData(salaryRecords);

        DataSource plain = new FileDataSource("OutputDemo.txt");
        System.out.println("\n- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("\n- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("\n- Decoded --------------");
        System.out.println(dataSource.readData());
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
            try (OutputStream fos = Files.newOutputStream(file.toPath())) {
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
            return new String(result, StandardCharsets.UTF_8);
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
