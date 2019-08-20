package knowledge.api.util.properties;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        InputStream is = null;
        try {
            is = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
            props.load(new InputStreamReader(is, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
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