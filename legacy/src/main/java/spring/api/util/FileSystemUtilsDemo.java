package spring.api.util;

import l.demo.Demo;
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

    public static void main(String[] args) throws IOException {
        // 递归复制
        FileSystemUtils.copyRecursively(new File(DEMO_DIR_ABSOLUTE_PATH + "a"), new File(DESKTOP + "a"));
        // 递归删除
        FileSystemUtils.deleteRecursively(new File(DESKTOP + "a"));
    }
}
