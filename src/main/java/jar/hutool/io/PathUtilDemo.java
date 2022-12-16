package jar.hutool.io;

import cn.hutool.core.io.file.PathUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * PathUtil
 * <p>
 * static boolean	            isDirectory(Path path, boolean isFollowLinks)   判断是否为目录，如果 file 为 null，则返回 false
 * static boolean	            isDirEmpty(Path dirPath)                        目录是否为空
 * static boolean	            isFile(Path path, boolean isFollowLinks)        判断是否为文件，如果 file 为 null，则返回 false
 * static boolean	            isSymlink(Path path)                            判断是否为符号链接文件
 * static boolean	            equals(Path file1, Path file2)                  检查两个文件是否是同一个文件，指 Path 是否指向同一个文件或文件夹
 * static BufferedInputStream	getInputStream(Path path)                       获得输入流
 * static BufferedOutputStream	getOutputStream(Path path)                      获得输出流
 * static BufferedReader	    getReader(Path path, Charset charset)           获得一个文件读取器
 * static BufferedReader	    getUtf8Reader(Path path)                        获得一个文件读取器
 * <p>
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/io/file/PathUtil.html
 *
 * @author ljh
 * @since 2020/10/30 9:25
 */
public class PathUtilDemo extends Demo {

    @Test
    public void testPathUtil() {
        Path path = Paths.get(DEMO_FILE_PATH);
        Path desPath = Paths.get(DEMO_PATH + "a/b/demo");

        // 获取指定位置的子路径部分，支持负数
        p(PathUtil.subPath(path, 0, 5));    // src\main\resources\demo\demo
        p(PathUtil.getPathEle(path, 0));    // src
        p(PathUtil.getPathEle(path, 1));    // main
        p(PathUtil.getPathEle(path, 2));    // resources
        p(PathUtil.getPathEle(path, -2));   // demo
        p(PathUtil.getLastPathEle(path));   // demo

        // 获取文件属性
        BasicFileAttributes attributes = PathUtil.getAttributes(path, true);
        p("[size]: " + attributes.size() + "; [lastAccess]: " + attributes.lastAccessTime() + ";");

        // 复制文件
        PathUtil.copyFile(path, desPath, StandardCopyOption.REPLACE_EXISTING);

        // 修改文件名
        PathUtil.rename(desPath, "demo2", true);

        // 删除文件
        PathUtil.del(Paths.get(DEMO_PATH + "a/b/demo2"));
    }

    @Test
    public void traversal() {
        Path dir = Paths.get(DEMO_PATH);

        // static List<File>	loopFiles(Path path, int maxDepth, FileFilter fileFilter)
        // 递归遍历目录以及子目录中的所有文件，返回过滤结果
        p(PathUtil.loopFiles(dir, Integer.MAX_VALUE, pathname -> pathname.getName().endsWith(".zip")));

        // static void	        walkFiles(Path start, int maxDepth, FileVisitor<? super Path> visitor)
        // 遍历指定 path 下的文件并做处理
    }
}
