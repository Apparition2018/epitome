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
 * created on 2021/9/8 1:32
 */
public class FileCopyUtilsDemo extends Demo {

    @Test
    public void testFileCopyUtils() throws IOException {
        // 复制
        FileCopyUtils.copy(new File(DEMO_ABSOLUTE_PATH + "a/"), new File(DESKTOP + "copy/"));

        // 读取
        p(FileCopyUtils.copyToString(new FileReader(DEMO_FILE_ABSOLUTE_PATH)));
    }
}
