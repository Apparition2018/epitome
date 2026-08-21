package org.ljh.dependencies.hutool.io;

import cn.hutool.core.io.file.PathUtil;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

import static com.ljh.commons.Demo.*;

/**
 * PathUtil
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/io/file/PathUtil.html">PathUtil api</a>
 *
 * @author ljh
 * @since 2020/10/30 9:25
 */
public class PathUtilTests {

    @Test
    public void testPathUtil() {
        Path path = Paths.get(DEMO_FILE_PATH);
        Path desPath = Paths.get(DEMO_DIR_PATH + "a/b/demo");

        // 获取指定位置的子路径部分，支持负数
        p(PathUtil.subPath(path, 0, 5));    // src\main\resources\demo\demo
        p(PathUtil.getPathEle(path, 0));    // src
        p(PathUtil.getPathEle(path, 1));    // main
        p(PathUtil.getPathEle(path, 2));    // resources
        p(PathUtil.getPathEle(path, -2));   // demo
        p(PathUtil.getLastPathEle(path));   // demo

        // 获取文件属性
        BasicFileAttributes attributes = PathUtil.getAttributes(path, true);
        p(String.format("[size]: %s; [lastAccess]: %s;", attributes.size(), attributes.lastAccessTime()));

        // 复制文件
        PathUtil.copyFile(path, desPath, StandardCopyOption.REPLACE_EXISTING);

        // 修改文件名
        PathUtil.rename(desPath, "demo2", true);

        // 删除文件
        PathUtil.del(Paths.get(DEMO_DIR_PATH + "a/b/demo2"));
    }

    @Test
    public void traversal() {
        Path dir = Paths.get(DEMO_DIR_PATH);

        // static List<File>	loopFiles(Path path, int maxDepth, FileFilter fileFilter)
        // 递归遍历目录以及子目录中的所有文件，返回过滤结果
        p(PathUtil.loopFiles(dir, Integer.MAX_VALUE, pathname -> pathname.getName().endsWith(".zip")));

        // static void	        walkFiles(Path start, int maxDepth, FileVisitor<? super Path> visitor)
        // 遍历指定 path 下的文件并做处理
    }
}
