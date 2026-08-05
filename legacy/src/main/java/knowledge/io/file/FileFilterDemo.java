package knowledge.io.file;

import l.demo.Demo;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/io/FileFilter.html">FileFilter</a>
 * <p>用于抽象路径名的过滤器
 *
 * @author ljh
 * @since 2020/9/14 10:40
 */
public class FileFilterDemo extends Demo {

    public static void main(String[] args) {
        File file = new File(USER_DIR);

        // boolean	    accept(File pathname)
        // 测试指定抽象路径名是否应该包含在某个路径名列表中
        FileFilter filter = f -> f.getName().startsWith("p");

        File[] subFiles = file.listFiles(filter);
        for (File subFile : Objects.requireNonNull(subFiles, "目录不存在或它不是一个目录")) {
            p(subFile.getName());
        }
    }
}
