package spring.api.util;

import l.demo.Demo;
import org.junit.jupiter.api.Test;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * FileCopyUtils
 *
 * @author ljh
 * @since 2021/9/8 1:32
 */
public class FileCopyUtilsDemo extends Demo {

    @Test
    public void testFileCopyUtils() throws IOException {
        // 复制文件
        FileCopyUtils.copy(new File(DEMO_FILE_ABSOLUTE_PATH), new File(DESKTOP + "demo"));

        // 读取
        p(FileCopyUtils.copyToString(new FileReader(DEMO_FILE_ABSOLUTE_PATH)));
    }
}
