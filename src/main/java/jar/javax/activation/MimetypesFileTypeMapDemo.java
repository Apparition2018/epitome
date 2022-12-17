package jar.javax.activation;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;

/**
 * MimetypesFileTypeMap
 *
 * @author ljh
 * @since 2021/4/25 17:38
 */
public class MimetypesFileTypeMapDemo extends Demo {

    @Test
    public void testMimetypesFileTypeMap() {
        p(new MimetypesFileTypeMap().getContentType(new File(XIAO_XIN)));           // image/png
        p(new MimetypesFileTypeMap().getContentType(new File(JDBC_PROPS_FILENAME))); // application/octet-stream
    }
}
