package knowledge.io.nio.file;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Files
 * 该类包含操作文件、目录或其他类型文件的静态方法
 * https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html
 *
 * @author ljh
 * @since 2020/9/26 2:51
 */
public class FilesDemo extends Demo {

    @Test
    public void write() throws IOException {
        Path path = Paths.get(DEMO_FILE_PATH);

        List<String> lines = new ArrayList<>();
        lines.add("静夜思");
        lines.add("床前明月光，");
        lines.add("疑是地上霜。");
        lines.add("举头望明月，");
        lines.add("低头思故乡。");

        Files.write(path, lines, StandardOpenOption.CREATE);
    }

    @Test
    public void read() throws IOException {
        Path path = Paths.get(DEMO_FILE_PATH);

        // 1.
        Files.readAllLines(path).forEach(Demo::p);

        // 2.
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(Demo::p);
        }

        // 3.
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while (null != (line = br.readLine())) {
                p(line);
            }
        }

        // 读取文件属性，"*" 表示 读取所有属性
        p(Files.readAttributes(path, "*"));
        // {lastAccessTime=2020-09-22T06:16:01.487644Z, lastModifiedTime=2020-09-22T06:16:01.487644Z, size=91, creationTime=2020-09-14T06:22:45.436145Z, isSymbolicLink=false, isRegularFile=true, fileKey=null, isOther=false, isDirectory=false}
    }

    @Test
    public void create() throws IOException {
        Path path = Paths.get(DEMO_PATH + "a/b/c");
        Path path2 = Paths.get(DEMO_PATH + "a/b/demo");

        // 创建文件夹 (多级)
        Files.createDirectories(path);

        // 创建文件
        if (!Files.exists(path2)) {
            Files.createFile(path2);
        }
    }


    @Test
    public void delete() throws IOException {
        Path path = Paths.get(DEMO_PATH + "a");
        Path path2 = Paths.get(DEMO_PATH + "a/b/demo");

        Files.deleteIfExists(path2);
        Files.deleteIfExists(path); // DirectoryNotEmptyException: src\main\resources\demo\a
    }

    @Test
    public void move() throws IOException {
        Path path1 = Paths.get(DEMO_FILE_PATH);
        Path path2 = Paths.get(DEMO_PATH + "a/b/demo");

        // ATOMIC_MOVE：原子性操作，要么移动成功完成，要么源文件保持在原位置
        Files.move(path1, path2, StandardCopyOption.ATOMIC_MOVE);
        Files.move(path2, path1, StandardCopyOption.ATOMIC_MOVE);
    }

    @Test
    public void copy() throws IOException {
        Path path1 = Paths.get(DEMO_FILE_PATH);
        Path path2 = Paths.get(DEMO_PATH + "a/b/demo");

        // static Path      copy(Path source, Path target, CopyOption... options)
        // StandardCopyOption：REPLACE_EXISTING（覆盖），COPY_ATTRIBUTES（复制文件属性）
        Files.copy(path1, path2, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);

        // static long      copy(InputStream in, Path target, CopyOption... options)
    }

    @Test
    public void get() throws IOException {
        Path path = Paths.get(DEMO_PATH);

        // 返回文件存储区
        p(Files.getFileStore(path)); // 开发 (D:)

        // 返回文件最后修改时间
        p(Files.getLastModifiedTime(path));

        // 返回文件所有者
        p(Files.getOwner(path)); // JS3-LJH\NL-PC001 (User)

        // 返回文件指定属性值
        p(Files.getAttribute(path, "size")); // 4096
    }

    @Test
    public void is() throws IOException {
        Path path = Paths.get(DEMO_PATH);
        Path path2 = Paths.get(DEMO_PATH);

        p(Files.isDirectory(path));         // 文件夹，true
        p(Files.isExecutable(path));        // 可执行，true
        p(Files.isHidden(path));            // 隐藏，false
        p(Files.isReadable(path));          // 可读，true
        p(Files.isRegularFile(path));       // 正常文件(不是符号连接，不是目录等)，false
        p(Files.isSameFile(path, path2));   // 相等，true
        p(Files.isSymbolicLink(path));      // 符号链接，false
        p(Files.isWritable(path));          // 可写，true
    }

    @Test
    public void other() throws IOException {
        Path path = Paths.get(DEMO_FILE_PATH);

        // 探测文件类型
        p(Files.probeContentType(path));
    }

    @Test
    public void stream() throws IOException {
        Path path = Paths.get(DEMO_PATH);

        // 返回指定目录下的条目 Stream，不会递归遍历
        Files.newDirectoryStream(path, "demo*").forEach(System.out::println);

        // 返回指定目录下的条目 Stream，不会递归遍历
        Files.list(path).filter(p -> String.valueOf(p).endsWith(".xml")).forEach(System.out::println);

        // 返回指定目录下的条目 Stream，指定递归深度
        Files.find(path, 5, (p, a) -> String.valueOf(p).endsWith(".png")).forEach(System.out::println);

        // 返回指定目录下的条目 Stream，指定递归深度
        Files.walk(path, 5).filter(p -> String.valueOf(p).endsWith(".obj")).forEach(System.out::println);
    }

    /**
     * https://segmentfault.com/a/1190000020778836
     */
    @Test
    public void walkFileTree() throws IOException {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**.obj");
        Files.walkFileTree(Paths.get(DEMO_PATH), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (pathMatcher.matches(file)) {
                    p(file);
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
