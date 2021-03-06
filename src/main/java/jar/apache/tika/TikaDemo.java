package jar.apache.tika;

import l.demo.Demo;
import org.apache.tika.Tika;
import org.apache.tika.detect.AutoDetectReader;
import org.apache.tika.exception.TikaException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Tika
 * <p>
 * Apache Tika容抽取工具集合：https://www.oschina.net/p/tika
 * apache Tika介绍及使用：https://blog.csdn.net/weixin_42184707/article/details/91045592
 *
 * @author Arsenal
 * created on 2020/10/25 1:06
 */
public class TikaDemo extends Demo {

    public static void main(String[] args) throws IOException, TikaException {
        Tika tika = new Tika();

        // Content-Type (Mime-Type)
        p(tika.detect(DEMO_FILE_PATH)); // application/octet-stream

        // 文件内容
        p(tika.parseToString(new File(DEMO_FILE_PATH)));

        // 文件字符编码方案 (character-encoding schema)
        AutoDetectReader autoDetectReader = new AutoDetectReader(new FileInputStream(DEMO_FILE_PATH));
        p(autoDetectReader.getCharset().name()); // UTF-8
    }

}
