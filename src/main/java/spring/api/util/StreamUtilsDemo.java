package spring.api.util;

import l.demo.Demo;
import org.junit.jupiter.api.Test;
import org.springframework.util.StreamUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * StreamUtils
 *
 * @author ljh
 * created on 2021/9/8 1:33
 */
public class StreamUtilsDemo extends Demo {

    @Test
    public void testStreamUtils() throws IOException {
        // 读取
        p(StreamUtils.copyToString(new FileInputStream(DEMO_FILE_ABSOLUTE_PATH), StandardCharsets.UTF_8));
    }
}
