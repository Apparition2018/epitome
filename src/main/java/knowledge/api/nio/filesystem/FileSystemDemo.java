package knowledge.api.nio.filesystem;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;

/**
 * FileSystem
 *
 * https://docs.oracle.com/javase/8/docs/api/java/nio/file/FileSystem.html
 * https://docs.oracle.com/javase/8/docs/api/java/nio/file/FileSystems.html
 */
public class FileSystemDemo {

    private final static String dirPath = "src/main/java/knowledge/api/nio/filesystem/";


    /**
     * 解压 Zip 文件
     * https://www.cnblogs.com/lyndon-chen/p/3575393.html
     */
    @Test
    public void test() throws IOException {
        // 新建 zip 文件系统
        FileSystem fs = FileSystems.newFileSystem(Paths.get(dirPath + "a.zip"), null);
    }

}
