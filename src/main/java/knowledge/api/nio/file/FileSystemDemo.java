package knowledge.api.nio.file;

import l.demo.Demo;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * FileSystem
 * https://www.matools.com/file/manual/jdk_api_1.8_google/java/nio/file/FileSystem.html
 * <p>
 * FileSystem
 * https://www.matools.com/file/manual/jdk_api_1.8_google/java/nio/file/FileSystems.html
 */
public class FileSystemDemo extends Demo {
    
    /**
     * 解压 Zip 文件
     * https://www.cnblogs.com/lyndon-chen/p/3575393.html
     */
    @Test
    public void unzip() throws IOException {
        FileSystem fs = FileSystems.newFileSystem(Paths.get(DEMO_PATH+ "demo.zip"), null);
        Files.walkFileTree(fs.getPath("/"), new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path destPath = Paths.get(DEMO_PATH + "a/", file.toString());
                Files.deleteIfExists(destPath);
                Files.createDirectories(destPath.getParent());
                Files.move(file, destPath);
                return FileVisitResult.CONTINUE;
            }
        });
    }

}
