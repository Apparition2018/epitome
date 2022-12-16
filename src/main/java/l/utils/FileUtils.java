package l.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @author ljh
 * @since 2020/9/7 1:28
 */
@Slf4j
public abstract class FileUtils {

    // 删除文件夹和其子文件夹和文件
    private static boolean deleteDir(File file) {
        if (file.isDirectory()) {
            File[] subs = file.listFiles();
            if (null != subs) {
                for (File sub : subs) {
                    deleteDir(sub);
                }
            }
        }
        return file.delete();
    }
}
