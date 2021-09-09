package spring.api.util;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * ResourceUtils
 *
 * @author ljh
 * created on 2021/9/8 16:24
 */
public class ResourceUtilsDemo {

    @Test
    public void testResourceUtils() throws FileNotFoundException {
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "application.properties");
        System.out.println(file); // D:\Liang\git\epitome\target\classes\application.properties
        URL url = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + "application.properties");
        System.out.println(url); // file:/D:/Liang/git/epitome/target/classes/application.properties
    }
}
