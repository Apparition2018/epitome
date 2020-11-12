package jar.spring.io;

import l.demo.Demo;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * ClassPathResource
 *
 * @author ljh
 * created on 2020/10/29 17:38
 */
public class ClassPathResourceDemo extends Demo {

    @Test
    public void testClassPathResource() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("demo/demo");

        p(classPathResource.exists());          // true
        p(classPathResource.getDescription());  // class path resource [demo/demo]
        p(classPathResource.getPath());         // demo/demo
        p(classPathResource.getFilename());     // demo
        File file = classPathResource.getFile();
    }
}
