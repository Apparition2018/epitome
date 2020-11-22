package knowledge.api.io.stream;

import l.demo.Demo;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 节点流
 * 1.文件：    FileInputStream FileOutputStream FileReader FileWriter
 * 2.数组：    ByteArrayInputStream ByteArrayOutputStream CharArrayReader CharArrayWriter
 * 3.字符串：  StringRead StringWriter
 * 4.管道：    PipedInputStream PipedOutputStream PipedReader PipedWriter
 * <p>
 * 处理流
 * 1.缓冲流：   BufferedInputStream BufferedOutputStream BufferedReader BufferedWriter
 * 2.转换流：   InputStreamReader OutputStreamWriter
 * 3.数据流：   DataInputStream DataOutputStream
 *
 * @author ljh
 * created on 2020/9/7 1:28
 */
public class StreamExercise extends Demo {

    @Test
    public void copy() {
        copy(DEMO_FILE_PATH, DEMO_PATH + "demo_copy");
    }

    /**
     * 复制
     */
    public boolean copy(String src, String desc) {
        try (InputStream is = new BufferedInputStream(new FileInputStream(src));
             OutputStream os = new BufferedOutputStream(new FileOutputStream(desc))) {
            int len;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
            p("复制失败！");
            return false;
        }
        p("复制成功！");
        return true;
    }

    /**
     * 复制2
     * <p>
     * 文件 → BufferedImage → 流 → byte[] → 流 → BufferedImage → 文件
     */
    public boolean copy2(String src, String desc) {
        try {
            // 文件 → BufferedImage
            BufferedImage bi = ImageIO.read(new File(src));

            // BufferedImage → 流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);

            // 流 → byte[]
            byte[] bytes = baos.toByteArray();

            // byte[] → 流
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

            // 流 → BufferedImage
            bi = ImageIO.read(bais);

            // BufferedImage → 文件
            ImageIO.write(bi, "png", new File(desc));
        } catch (IOException e) {
            e.printStackTrace();
            p("复制失败！");
            return false;
        }
        p("复制成功！");
        return true;
    }

}
