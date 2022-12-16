package spring.api.util;

import l.demo.Demo;
import org.junit.jupiter.api.Test;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;

/**
 * FileSystemUtils
 *
 * @author ljh
 * @since 2021/9/8 1:33
 */
public class FileSystemUtilsDemo extends Demo {

    @Test
    public void testFileSystemUtils() throws IOException {
        // 递归复制
        FileSystemUtils.copyRecursively(new File(DEMO_ABSOLUTE_PATH + "a"), new File(DESKTOP + "a"));
        // 递归删除
        FileSystemUtils.deleteRecursively(new File(DESKTOP + "a"));
    }
}
