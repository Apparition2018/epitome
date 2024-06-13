package knowledge.io.stream;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 节点流：
 * <pre>
 * 文件       FileInputStream FileOutputStream FileReader FileWriter
 * 数组       ByteArrayInputStream ByteArrayOutputStream CharArrayReader CharArrayWriter
 * 字符串     StringRead StringWriter
 * 管道       PipedInputStream PipedOutputStream PipedReader PipedWriter
 * </pre>
 * 处理流：
 * <pre>
 * 缓冲流      BufferedInputStream BufferedOutputStream BufferedReader BufferedWriter
 * 转换流      InputStreamReader OutputStreamWriter
 * 数据流      DataInputStream DataOutputStream
 * </pre>
 *
 * @author ljh
 * @since 2020/9/7 1:28
 */
public class IoStreamDemo extends Demo {

    /** 使用缓冲流复制文件 */
    @Test
    public void copyByBufferedStream() {
        try (InputStream is = new BufferedInputStream(Files.newInputStream(Paths.get(DEMO_FILE_PATH)));
             OutputStream os = new BufferedOutputStream(Files.newOutputStream(Paths.get(DEMO_DIR_PATH + "demo_copy")))) {
            is.transferTo(os);
        } catch (IOException e) {
            p("copy failed!");
            throw new RuntimeException(e);
        }
        p("copy completed!");
    }

    /** 通过字节数组流实现 InputStream 重复使用 */
    @Test
    public void repeatableUse() {
        try (FileInputStream is = new FileInputStream(DEMO_FILE_PATH)) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            is.transferTo(baos);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

            p(bais.available());
            p(bais.available());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
