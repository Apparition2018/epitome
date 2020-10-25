package knowledge.数据结构.集合框架.map.properties;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * PropertiesUtil
 *
 * @author ljh
 * created on 2019/8/20 10:24
 */
public class PropertiesUtil {

    public static Properties loadProps(String fileName) {
        Properties props = new Properties();
        Reader reader = null;
        try (InputStream is = ClassLoader.getSystemResourceAsStream(fileName)) {
            if (null != is) {
                props.load(new InputStreamReader(is, StandardCharsets.UTF_8));
            } else {
                reader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8);
                props.load(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return props;
    }

    public static String getProperty(String fileName, String key) {
        Properties props = loadProps(fileName);
        return props.getProperty(key);
    }

    public static void main(String[] args) {
        String server = PropertiesUtil.getProperty("application.properties", "server.port");
        System.out.println("server = " + server);
    }

}