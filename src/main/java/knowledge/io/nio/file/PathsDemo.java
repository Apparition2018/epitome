package knowledge.io.nio.file;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/Paths.html">Paths</a>
 *
 * @author ljh
 * @since 2020/11/5 10:06
 */
public class PathsDemo extends Demo {

    @Test
    public void testPaths() {
        // static Path	    get(String first, String... more)       pathStr... → Path
        Path path = Paths.get(USER_DIR, File.separator, DEMO_FILE_PATH);
        p(path);    // D:\L\git\epitome\src\main\resources\demo\demo

        // static Path	    get(URI uri)                            URI → Path
        Path uriPath = Paths.get(URI.create("file:///" + (USER_DIR + File.separator + DEMO_FILE_PATH).replaceAll("\\\\", "/")));
        p(uriPath); // D:\L\git\epitome\src\main\resources\demo\demo
    }
}
