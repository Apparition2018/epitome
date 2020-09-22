package knowledge.api.nio.file;

import l.demo.Demo;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Path
 * 可用于在文件系统中定位文件的对象。它通常表示与系统相关的文件路径。
 * https://www.matools.com/file/manual/jdk_api_1.8_google/java/nio/file/Path.html
 * <p>
 * int	        compareTo(Path other)           比较两个抽象路径的词法，如果返回值为 0 则相等
 * boolean	    equals(Object other)            判断此路径是否与给定对象相等
 * boolean	    endsWith(Path/String other)     判断此路径是否以给定路径结束
 * boolean	    startsWith(Path/String other)   判断此路径是否以给定路径开始
 * boolean	    isAbsolute()                    判断是否为绝对路径
 * FileSystem	getFileSystem()                 返回此对象的 FileSystem
 * <p>
 * Paths
 * 该类只包含通过转换路径字符串或 URI 返回路径的静态方法。
 * https://www.matools.com/file/manual/jdk_api_1.8_google/java/nio/file/Paths.html
 */
public class PathDemo extends Demo {

    // 使用 FileSystem.getDefault().getPath(...) 创建 Path
    private Path p1 = FileSystems.getDefault().getPath(DEMO_PATH);
    // 使用 Paths.get(String first[, String... more]) 创建 Path
    private Path p2 = Paths.get(RESOURCES_PATH, "knowledge");

    private Path p3 = Paths.get(USER_DIR + File.separator + DEMO_PATH);
    private Path p4 = Paths.get(USER_DIR + File.separator + RESOURCES_PATH + "knowledge");
    
    @Test
    public void testGet() {
        // 返回文件名或目录名
        p(p1.getFileName());    // demo
        // 返回父路径，如果该路径没有父路径，则返回null
        p(p1.getParent());      // src\main\resources
        // 返回根组件，如果该路径没有根组件，则返回null
        p(p1.getRoot());        //
        
        // 返回路径中名称元素的数量
        p(p1.getNameCount());   // 4
        // 返回指定 index 的名称元素的名称
        p(p1.getName(0));       // src
        p(p1.getName(1));       // main
        p(p1.getName(2));       // resources
        p(p1.getName(3));       // demo
        // 返回一个相对路径，它是该路径的名称元素的子序列
        p(p1.subpath(0, 4));    // src\main\resources\demo
        for (Path aP1 : p1) {
            p(aP1);
        }
    }

    /**
     * Path	        normalize()             返回一个路径，该路径消除了冗余的名称元素；冗余名称元素一般指 ".", ".."
     */
    @Test
    public void normalize() {
        Path path = Paths.get("src/main/resources/demo/a/../demo/");
        p(path.normalize());     // src\main\resources\demo\demo
    }

    /**
     * Path	        relativize(Path other)  构造此路径与给定路径之间的相对路径
     */
    @Test
    public void relativize() {
        p(p1); // src\main\resources\demo
        p(p2); // src\main\resources\knowledge
        p(p3); // D:\L\git\epitome\src\main\resources\demo
        p(p4); // D:\L\git\epitome\src\main\resources\knowledge

        p(p1.relativize(p2)); // ..\knowledge
        p(p3.relativize(p4)); // ..\knowledge

        p(p1.relativize(p3)); // IllegalArgumentException: 'other' is different type of Path
    }

    /**
     * Path	        resolve(Path/String other)          当 other 是绝对路径，则返回 other；否则追加 other 至结尾
     * Path	        resolveSibling(Path/String other)   类似上面方法，不过是生成兄弟路径
     */
    @Test
    public void resolve() {
        Path p5 = Paths.get(DEMO_PATH + "demo");
        p(p1);                              // src\main\resources\demo
        p(p5);                              // src\main\resources\demo\demo
        p(p1.resolve("demo"));              // src\main\resources\demo\demo
        p(p1.resolve("/demo"));             // \demo
        p(p1.resolve(p5.toAbsolutePath())); // D:\L\git\epitome\src\main\resources\demo\demo

        Path p6 = p2.resolve("new 1.txt");
        p(p2);                                          // src\main\resources\knowledge                              
        p(p6);                                          // src\main\resources\knowledge\new 1.txt                             
        p(p1.resolveSibling("knowledge/new 1.txt"));    // src\main\resources\knowledge\new 1.txt
        p(p1.resolveSibling("/knowledge/new 1.txt"));   // \knowledge\new 1.txt
        p(p1.resolveSibling(p6.toAbsolutePath()));      // D:\L\git\epitome\src\main\resources\knowledge\new 1.txt
    }

    /**
     * Path	        toAbsolutePath()        返回绝对路径
     * Path	        toRealPath(LinkOption... options)   返回实际路径，如果找不到实际路径，会抛出异常
     * URI          toFile()                Path → File
     * URI	        toUri()                 Path → URI
     */
    @Test
    public void toXXX() throws IOException {
        p(p1.toAbsolutePath());                     // D:\L\git\epitome\src\main\resources\demo
        p(p1.toRealPath(LinkOption.NOFOLLOW_LINKS));// D:\L\git\epitome\src\main\resources\demo
        p(p1.toFile());                             // src\main\resources\demo
        p(p1.toUri());                              // file:///D:/L/git/epitome/src/main/resources/demo/
    }

}
