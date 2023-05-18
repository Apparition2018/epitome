package jar.javax.activation;

import jakarta.activation.MimetypesFileTypeMap;
import l.demo.Demo;

import java.io.File;

/**
 * MimetypesFileTypeMap
 *
 * @author ljh
 * @since 2021/4/25 17:38
 */
public class MimetypesFileTypeMapDemo extends Demo {

    public static void main(String[] args) {
        p(new MimetypesFileTypeMap().getContentType(new File(XIAO_XIN_PNG)));           // image/png
        p(new MimetypesFileTypeMap().getContentType(new File(JDBC_PROPS_FILENAME)));    // application/octet-stream
    }
}
