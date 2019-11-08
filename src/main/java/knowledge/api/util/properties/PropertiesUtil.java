package knowledge.api.util.properties;

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
        try (InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is != null) {
                props.load(new InputStreamReader(is, StandardCharsets.UTF_8));
            } else {
                reader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8);
                props.load(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
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