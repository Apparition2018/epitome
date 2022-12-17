package knowledge.data.structure.collections.framework.map.properties;

import l.demo.Demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * PropertiesUtil
 *
 * @author ljh
 * @since 2019/8/20 10:24
 */
public class PropertiesUtil extends Demo {

    public static Properties loadProps(String fileName) {
        Properties props = new Properties();
        Reader reader = null;
        try (InputStream is = ClassLoader.getSystemResourceAsStream(fileName)) {
            if (null != is) {
                props.load(new InputStreamReader(is, StandardCharsets.UTF_8));
            } else {
                reader = new InputStreamReader(Files.newInputStream(Paths.get(fileName)), StandardCharsets.UTF_8);
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
        p(PropertiesUtil.getProperty(APP_PROPS_FILENAME, "server.port"));
    }
}
