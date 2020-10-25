package l.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

@Slf4j
public class FileUtils {

    // 创建文件
    public static boolean createNewFile(File file) throws IOException {
        if (!file.exists()) {
            return file.createNewFile();
        } else {
            log.info("文件已存在！");
            return false;
        }

    }

    // 创建文件夹
    public static boolean mkdirs(File dir) {
        if (!dir.exists()) {
            return dir.mkdirs();
        } else {
            log.info("目录已存在！");
            return false;
        }
    }

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
