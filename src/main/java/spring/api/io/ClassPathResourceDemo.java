package spring.api.io;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * ClassPathResource
 * Java/Springboot 读取 resources 下文件：https://www.cnblogs.com/whalesea/p/11677657.html
 *
 * @author ljh
 * created on 2020/10/29 17:38
 */
public class ClassPathResourceDemo {

    @Test
    public void testClassPathResource() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("demo/demo");

        System.out.println(classPathResource.exists());          // true
        System.out.println(classPathResource.getDescription());  // class path resource [demo/demo]
        System.out.println(classPathResource.getPath());         // demo/demo
        System.out.println(classPathResource.getFilename());     // demo
        File file = classPathResource.getFile();
    }
}
