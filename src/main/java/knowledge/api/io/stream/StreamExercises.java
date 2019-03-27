package knowledge.api.io.stream;

import org.apache.commons.io.IOUtils;
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
 * 1.缓冲流：   BufferedInputStream BufferedOutputStream BufferedReader BufferedWriter  (flush)
 * 2.转换流：   InputStreamReader OutputStreamWriter
 * 3.数据流：   DataInputStream DataOutputStream
 */
public class StreamExercises {

    private final static String dirPath = "src/main/java/knowledge/api/io/";

    /**
     * 复制
     */
    @Test
    public void copy() {
        // 1.提供读入、写出的文件
        File f1 = new File(dirPath + "io.png");
        File f2 = new File(dirPath + "io_copy.png");

        // 2.提供相应的流
        InputStream is = null;
        OutputStream os = null;

        try {
            is = new BufferedInputStream(new FileInputStream(f1));
            os = new BufferedOutputStream(new FileOutputStream(f2));

            // 3.实现文件的复制
            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                os.write(b, 0, len);
            }

            System.out.println("复制完毕！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }


    }

    /**
     * 复制2
     * <p>
     * 文件 → BufferedImage → 流 → byte[] → 流 → BufferedImage → 文件
     */
    @Test
    public void copy2() {
        File f1 = new File(dirPath + "io.png");
        File f2 = new File(dirPath + "io_copy.png");
        BufferedImage bi;

        try {
            // 文件 → BufferedImage
            bi = ImageIO.read(f1);

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
            ImageIO.write(bi, "png", f2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
