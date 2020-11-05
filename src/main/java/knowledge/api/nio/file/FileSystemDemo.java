package knowledge.api.nio.file;

import l.demo.Demo;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * FileSystem
 * 两个实现类：WindowsFileSystem，ZipFileSystem
 * https://www.matools.com/file/manual/jdk_api_1.8_google/java/nio/file/FileSystem.html
 * <p>
 * FileSystems
 * 文件系统的工厂方法。 该类定义了 getDefault() 来获取默认文件系统和工厂方法来构建其他类型的文件系统。
 * https://www.matools.com/file/manual/jdk_api_1.8_google/java/nio/file/FileSystems.html
 */
public class FileSystemDemo extends Demo {
    
    @Test
    public void testFileSystem() {
        FileSystem fileSystem = FileSystems.getDefault();
        Path path = fileSystem.getPath(DEMO_PATH, "demo");
        p(path);
        PathMatcher pathMatcher = fileSystem.getPathMatcher("glob:**demo"); // true
        p(pathMatcher.matches(path));
        Iterable<FileStore> fileStores = fileSystem.getFileStores();
        for (FileStore fileStore : fileStores) {
            p(fileStore);
        }
    }
    
    /**
     * 解压 Zip 文件
     * https://www.cnblogs.com/lyndon-chen/p/3575393.html
     */
    @Test
    public void unzip() throws IOException {
        FileSystem fileSystem = FileSystems.newFileSystem(Paths.get(DEMO_PATH+ "demo.zip"), null);
        Files.walkFileTree(fileSystem.getPath("/"), new SimpleFileVisitor<Path>() {
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
