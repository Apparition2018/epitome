package spring.api.io;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * ClassPathResource
 * <p><a href="https://www.cnblogs.com/whalesea/p/11677657.html">读取 resources 下文件</a>
 *
 * @author ljh
 * @since 2020/10/29 17:38
 */
public class ClassPathResourceDemo {

    public static void main(String[] args) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("demo/demo");

        System.out.println(classPathResource.exists());          // true
        System.out.println(classPathResource.getDescription());  // class path resource [demo/demo]
        System.out.println(classPathResource.getPath());         // demo/demo
        System.out.println(classPathResource.getFilename());     // demo
        File file = classPathResource.getFile();
    }
}
