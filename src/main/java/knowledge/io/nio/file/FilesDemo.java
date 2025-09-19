package knowledge.io.nio.file;

import l.demo.Demo;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Stream;

/**
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html">Files</a>
 * <p>该类包含操作文件、目录或其他类型文件的静态方法
 *
 * @author ljh
 * @since 2020/9/26 2:51
 */
public class FilesDemo extends Demo {

    private Path dirPath;
    private Path filePath;

    @BeforeEach
    public void init() {
        dirPath = Paths.get(DEMO_DIR_PATH);
        filePath = Paths.get(DEMO_FILE_PATH);
    }

    @Test
    public void write() throws IOException {
        List<String> lines = List.of("静夜思", "床前明月光，", "疑是地上霜。", "举头望明月，", "低头思故乡。");

        // Path                 write(Path path, Iterable<? extends CharSequence> lines, OpenOption... options)
        Files.write(filePath, lines, StandardOpenOption.CREATE);
        // Path                 writeString(Path path, CharSequence csq, OpenOption... options)
        // JDK11 引入
        Files.writeString(filePath, String.join(IOUtils.LINE_SEPARATOR_WINDOWS, lines) + IOUtils.LINE_SEPARATOR_WINDOWS, StandardOpenOption.CREATE);
    }

    @Test
    public void read() throws IOException {
        // List<String>         readAllLines(Path path)
        Files.readAllLines(filePath).forEach(Demo::p);

        // Stream<String>       lines(Path path)
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.forEach(Demo::p);
        }

        // BufferedReader       newBufferedReader(Path path)
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            while (null != (line = br.readLine())) {
                p(line);
            }
        }

        // String               readString(Path path, Charset cs)
        // JDK11 引入
        p(Files.readString(filePath, StandardCharsets.UTF_8));

        // Map<String,Object>   readAttributes(Path path, String attributes, LinkOption... options)
        // 读取文件属性，"*" 表示 读取所有属性
        p(Files.readAttributes(filePath, "*"));
        // {lastAccessTime=2020-09-22T06:16:01.487644Z, lastModifiedTime=2020-09-22T06:16:01.487644Z, size=91, creationTime=2020-09-14T06:22:45.436145Z, isSymbolicLink=false, isRegularFile=true, fileKey=null, isOther=false, isDirectory=false}
    }

    @Test
    public void create() throws IOException {
        Path path = Paths.get(DEMO_DIR_PATH + "a/b/c");
        Path path2 = Paths.get(DEMO_DIR_PATH + "a/b/demo");

        // 创建文件夹（多级）
        Files.createDirectories(path);

        // 创建文件
        if (!Files.exists(path2)) {
            Files.createFile(path2);
        }
    }


    @Test
    public void delete() throws IOException {
        Path path = Paths.get(DEMO_DIR_PATH + "a");
        Path path2 = Paths.get(DEMO_DIR_PATH + "a/b/demo");

        Files.deleteIfExists(path2);
        Files.deleteIfExists(path); // DirectoryNotEmptyException: src\main\resources\demo\a
    }

    @Test
    public void move() throws IOException {
        Path filePath2 = Paths.get(DEMO_DIR_PATH + "a/b/demo");

        // ATOMIC_MOVE：原子性操作，要么移动成功完成，要么源文件保持在原位置
        Files.move(filePath, filePath2, StandardCopyOption.ATOMIC_MOVE);
        Files.move(filePath2, filePath, StandardCopyOption.ATOMIC_MOVE);
    }

    @Test
    public void copy() throws IOException {
        Path filePath2 = Paths.get(DEMO_DIR_PATH + "a/b/demo");

        // static Path      copy(Path source, Path target, CopyOption... options)
        // StandardCopyOption：REPLACE_EXISTING（覆盖），COPY_ATTRIBUTES（复制文件属性）
        Files.copy(filePath, filePath2, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);

        // static long      copy(InputStream in, Path target, CopyOption... options)
    }

    @Test
    public void get() throws IOException {
        // 返回文件存储区
        p(Files.getFileStore(dirPath)); // 开发 (D:)

        // 返回文件最后修改时间
        p(Files.getLastModifiedTime(dirPath));

        // 返回文件所有者
        p(Files.getOwner(dirPath)); // JS3-LJH\NL-PC001 (User)

        // 返回文件指定属性值
        p(Files.getAttribute(dirPath, "size")); // 4096
    }

    @Test
    public void is() throws IOException {
        p(Files.isDirectory(dirPath));          // 文件夹，true
        p(Files.isExecutable(dirPath));         // 可执行，true
        p(Files.isHidden(dirPath));             // 隐藏，false
        p(Files.isReadable(dirPath));           // 可读，true
        p(Files.isRegularFile(dirPath));        // 正常文件(不是符号连接，不是目录等)，false
        p(Files.isSameFile(dirPath, dirPath));  // 相等，true
        p(Files.isSymbolicLink(dirPath));       // 符号链接，false
        p(Files.isWritable(dirPath));           // 可写，true
    }

    @Test
    public void other() throws IOException {
        // 探测文件类型
        p(Files.probeContentType(filePath));
        // 返回不匹配字节的位置，JDK12 引入
        p(Files.mismatch(filePath, filePath));
    }

    @Test
    public void stream() throws IOException {
        // 单层遍历
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, "demo*")) {
            stream.forEach(System.out::println);
        }
        // 单层遍历
        try (Stream<Path> stream = Files.list(dirPath)) {
            stream.filter(p -> String.valueOf(p).endsWith(".xml")).forEach(System.out::println);
        }
        // 条件查找
        try (Stream<Path> stream = Files.find(dirPath, 5, (p, a) -> String.valueOf(p).endsWith(".png"))) {
            stream.forEach(System.out::println);
        }
        // 递归遍历
        try (Stream<Path> stream = Files.walk(dirPath, 5)) {
            stream.filter(p -> String.valueOf(p).endsWith(".obj")).forEach(System.out::println);
        }
    }

    /** @see <a href="https://segmentfault.com/a/1190000020778836">Files.walkFileTree 遍历文件目录</a> */
    @Test
    public void walkFileTree() throws IOException {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**.obj");
        Files.walkFileTree(dirPath, new SimpleFileVisitor<>() {
            @NotNull
            public FileVisitResult visitFile(@NotNull Path file, @NotNull BasicFileAttributes attrs) {
                if (pathMatcher.matches(file)) {
                    p(file);
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
