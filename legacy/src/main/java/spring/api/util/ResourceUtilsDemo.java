package spring.api.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import static com.ljh.commons.Demo.APP_PROPS_FILENAME;
import static com.ljh.commons.Demo.p;

/**
 * ResourceUtils
 *
 * @author ljh
 * @since 2021/9/8 16:24
 */
public class ResourceUtilsDemo {

    public static void main(String[] args) throws FileNotFoundException {
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + APP_PROPS_FILENAME);
        p(file);    // D:\Liang\git\epitome\target\classes\application.properties

        URL url = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + APP_PROPS_FILENAME);
        p(url);     // file:/D:/Liang/git/epitome/target/classes/application.properties
    }
}
