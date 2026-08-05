package spring.api.core;

import l.demo.Demo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * PropertiesLoaderUtils
 *
 * @author ljh
 * @since 2022/12/16 16:10
 */
public class PropertiesLoaderUtilsDemo extends Demo {

    public static void main(String[] args) throws IOException {
        FileSystemResource fileSystemResource = new FileSystemResource(RESOURCES_ABSOLUTE_PATH + APP_PROPS_FILENAME);
        Properties properties = PropertiesLoaderUtils.loadProperties(fileSystemResource);
        p(properties.getProperty("server.port"));

        ClassPathResource classPathResource = new ClassPathResource(APP_PROPS_FILENAME);
        Properties properties2 = PropertiesLoaderUtils.loadProperties(classPathResource);
        p(properties2.getProperty("server.port"));
    }
}
