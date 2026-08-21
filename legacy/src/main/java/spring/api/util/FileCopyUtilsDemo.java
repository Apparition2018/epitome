package spring.api.util;

import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.ljh.commons.Demo.*;

/**
 * FileCopyUtils
 *
 * @author ljh
 * @since 2021/9/8 1:32
 */
public class FileCopyUtilsDemo {

    public static void main(String[] args) throws IOException {
        // 复制文件
        FileCopyUtils.copy(new File(DEMO_FILE_ABSOLUTE_PATH), new File(DESKTOP + DEMO_FILE_NAME));

        // 读取
        System.out.println(FileCopyUtils.copyToString(new FileReader(DEMO_FILE_ABSOLUTE_PATH)));
    }
}
