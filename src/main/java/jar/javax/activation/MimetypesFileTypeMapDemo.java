package jar.javax.activation;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;

/**
 * MimetypesFileTypeMap
 *
 * @author ljh
 * created on 2021/4/25 17:38
 */
public class MimetypesFileTypeMapDemo extends Demo {
    
    @Test
    public void testMimetypesFileTypeMap() {
        // 获取文件 contentType
        p(new MimetypesFileTypeMap().getContentType(new File(ARSENAL_LOGO)));
    }
    
}
