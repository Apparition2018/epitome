package spring.api.util;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.ljh.commons.Demo.DEMO_FILE_ABSOLUTE_PATH;

/**
 * StreamUtils
 *
 * @author ljh
 * @since 2021/9/8 1:33
 */
public class StreamUtilsDemo {

    public static void main(String[] args) throws IOException {
        // 读取
        System.out.println(StreamUtils.copyToString(Files.newInputStream(Paths.get(DEMO_FILE_ABSOLUTE_PATH)), StandardCharsets.UTF_8));
    }
}
