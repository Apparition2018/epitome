package knowledge.api.nio.file;

import l.demo.Demo;
import org.junit.Test;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Files
 * 该类包含操作文件、目录或其他类型文件的静态方法
 * https://www.matools.com/file/manual/jdk_api_1.8_google/java/nio/file/Files.html
 * <p>
 * https://www.cnblogs.com/ixenos/p/5851976.html
 * https://www.cnblogs.com/digdeep/p/4478734.html
 */
public class FilesDemo extends Demo {

    // 写
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

    // 读
    @Test
    public void read() throws IOException {
        Path path = Paths.get(DEMO_FILE_PATH);

        // 读取内容
        p(Files.readAllLines(path));

        // 读取内容
        Stream<String> lines = Files.lines(path);
        lines.forEach(FilesDemo::p);

        // 读取文件属性，"*" 表示 读取所有属性
        p(Files.readAttributes(path, "*"));
        // {lastAccessTime=2020-09-22T06:16:01.487644Z, lastModifiedTime=2020-09-22T06:16:01.487644Z, size=91, creationTime=2020-09-14T06:22:45.436145Z, isSymbolicLink=false, isRegularFile=true, fileKey=null, isOther=false, isDirectory=false}
    }

    // 创建
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


    // 删除
    @Test
    public void delete() throws IOException {
        Path path = Paths.get(DEMO_PATH + "a");
        Path path2 = Paths.get(DEMO_PATH + "a/b/demo");

        Files.deleteIfExists(path2);
        Files.deleteIfExists(path); // DirectoryNotEmptyException: src\main\resources\demo\a
    }


    // 移动
    @Test
    public void move() throws IOException {
        Path path1 = Paths.get(DEMO_FILE_PATH);
        Path path2 = Paths.get(DEMO_PATH + "a/b/demo");

        // ATOMIC_MOVE，原子性操作，要么移动成功完成，要么源文件保持在原位置
        Files.move(path1, path2, StandardCopyOption.ATOMIC_MOVE);
        Files.move(path2, path1, StandardCopyOption.ATOMIC_MOVE);
    }

    // 复制
    @Test
    public void copy() throws IOException {
        Path path1 = Paths.get(DEMO_FILE_PATH);
        Path path2 = Paths.get(DEMO_PATH + "a/b/demo");

        // REPLACE_EXISTING，覆盖
        // COPY_ATTRIBUTES，复制文件属性
        Files.copy(path1, path2, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
    }

    // 目录流
    @Test
    public void directoryStream() throws IOException {
        Path path = Paths.get(DEMO_PATH);

        // glob，其过滤作用
        DirectoryStream<Path> entries = Files.newDirectoryStream(path, "demo*");
        for (Path entry : entries) {
            p(entry);
            // src\main\resources\demo\demo
            // src\main\resources\demo\demo_copy
        }

        // Files.newDirectoryStream() 不会遍历子孙目录
        // 要遍历子孙目录需要使用 Files.walkFileTree()
    }

    // 获取信息
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

    // is
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

    // 其它
    @Test
    public void other() throws IOException {
        Path path = Paths.get(DEMO_FILE_PATH);

        // 探测文件类型
        p(Files.probeContentType(path));

        // 新建流
        InputStream is = Files.newInputStream(path);
        OutputStream os = Files.newOutputStream(path);
        BufferedReader br = Files.newBufferedReader(path);
        BufferedWriter bw = Files.newBufferedWriter(path);
    }

}