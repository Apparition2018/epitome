package knowledge.api.nio.path;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Path
 * 可用于在文件系统中定位文件的对象。它通常表示与系统相关的文件路径。
 * <p>
 * int	        compareTo(Path other)           比较两个抽象路径的词法，如果返回值为 0 则相等
 * boolean	    equals(Object other)            判断此路径是否与给定对象相等
 * <p>
 * boolean	    endsWith(Path/String other)     判断此路径是否以给定路径结束
 * boolean	    startsWith(Path/String other)   判断此路径是否以给定路径开始
 * <p>
 * FileSystem	getFileSystem()                 返回此对象的 FileSystem
 * <p>
 * https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html
 * <p>
 * Paths
 * 该类只包含通过转换路径字符串或 URI 返回路径的静态方法。
 */
public class PathDemo {

    // 使用 FileSystem.getDefault().getPath(...) 创建 Path
    private Path p1 = FileSystems.getDefault().getPath("src/main/java/knowledge/api/nio/path/");
    // 使用 Paths.get(String first[, String... more]) 创建 Path
    private Path p2 = Paths.get("src/main/java/", "knowledge/api/io/file/");

    private Path p3 = Paths.get("src/main/java/knowledge/api/nio/path");
    private Path p4 = Paths.get("src/main/java/knowledge/api/io/file");

    /**
     * Path	getFileName()
     * 返回文件名或目录名
     * <p>
     * Path getParent()
     * 返回父路径，如果该路径没有父路径，则返回null
     * <p>
     * Path getRoot()
     * 返回根组件，如果该路径没有根组件，则返回null
     */
    @Test
    public void getXXX() {
        p(p1.getFileName());    // path
        p(p1.getParent());      // src\main\java\knowledge\api\nio
        p(p1.getRoot());        //
    }

    /**
     * int getNameCount()
     * 返回路径中名称元素的数量
     * <p>
     * Path	getName(int index)
     * 返回指定 index 的名称元素的名称
     */
    @Test
    public void getName() {
        p(p1.getNameCount());   // 6
        p(p1.getName(0));       // src
        p(p1.getName(1));       // main
        p(p1.getName(2));       // java
        p(p1.getName(3));       // knowledge
        p(p1.getName(4));       // api
        p(p1.getName(5));       // nio
        p(p1.getName(6));       // path

        p(p1.subpath(0, 4));    // src\main\java\knowledge

        for (Path aP1 : p1) {
            System.out.println(aP1);
        }

    }

    /**
     * boolean	    isAbsolute()
     * 判断是否为绝对路径
     */
    @Test
    public void isAbsolute() {
        p(p1.isAbsolute()); // false
        p(p3.isAbsolute()); // true
    }

    /**
     * Path	normalize()
     * 返回一个路径，该路径消除了冗余的名称元素
     * 冗余名称元素一般指 ".", ".."
     */
    @Test
    public void normalize() {
        Path path = Paths.get("src/main/java/knowledge/api/nio/files/../path/");
        p(path.normalize());     // src\main\java\knowledge\api\nio\path
    }

    /**
     * Path	relativize(Path other)
     * 构造此路径与给定路径之间的相对路径
     */
    @Test
    public void relativize() {
        p(p1); // src\main\java\knowledge\api\nio\path
        p(p2); // src\main\java\knowledge\api\io\file
        p(p3); // C:\Users\234607\git\mavenTest\src\main\java\knowledge\api\nio\path
        p(p4); // C:\Users\234607\git\mavenTest\src\main\java\knowledge\api\io\file

        p(p1.relativize(p2)); // ..\..\io\file
        p(p3.relativize(p4)); // ..\..\io\file

        p(p1.relativize(p3)); // IllegalArgumentException: 'other' is different type of Path
    }

    /**
     * Path	resolve(Path/String other)
     * 当 other 是绝对路径，则返回 other；否则追加 other 至结尾
     * <p>
     * Path	resolveSibling(Path/String other)
     * 类似上面方法，不过是生成兄弟路径
     */
    @Test
    public void resolve() {
        Path p5 = Paths.get("src/main/java/knowledge/api/nio/path/PathDemo.java");
        p(p1.resolve("PathDemo.java"));     // src\main\java\knowledge\api\nio\path\PathDemo.java
        p(p1.resolve("/PathDemo.java"));    // \PathDemo.java
        p(p1.resolve(p5.toAbsolutePath())); // C:\Users\234607\git\mavenTest\src\main\java\knowledge\api\nio\path\PathDemo.java

        Path p6 = p2.resolve("FilesDemo.java");
        p(p1.resolveSibling("files/FilesDemo.java"));   // src\main\java\knowledge\api\nio\files\FilesDemo.java
        p(p1.resolveSibling("/files/FilesDemo.java"));  // \files\FilesDemo.java
        p(p1.resolveSibling(p6.toAbsolutePath()));      // C:\Users\234607\git\mavenTest\src\main\java\knowledge\api\io\file\FilesDemo.java
    }

    /**
     * Path	subpath(int beginIndex, int endIndex)
     * 返回一个相对路径，它是该路径的名称元素的子序列。
     */
    @Test
    public void subpath() {
        getName();
    }

    /**
     * Path	    toAbsolutePath()
     * 返回绝对路径
     * <p>
     * Path	    toRealPath(LinkOption... options)
     * 返回实际路径，如果找不到实际路径，会抛出异常
     * <p>
     * URI      toFile()
     * Path → File
     * <p>
     * URI	    toUri()
     * Path → URI
     */
    @Test
    public void toXXX() throws IOException {
        p(p1.toAbsolutePath()); // C:\Users\234607\git\mavenTest\src\main\java\knowledge\nio\path

        p(p1.toRealPath(LinkOption.NOFOLLOW_LINKS)); // C:\Users\234607\git\mavenTest\src\main\java\knowledge\api\nio\path
    }

    public static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }


}
