package knowledge.io.file;

import l.demo.Demo;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Objects;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/io/FilenameFilter.html">FilenameFilter</a>
 * <p>实现此接口的类实例可用于过滤器文件名。
 *
 * @author ljh
 * @since 2020/9/14 10:51
 */
public class FilenameFilterDemo extends Demo {

    public static void main(String[] args) {
        File file = new File(USER_DIR);

        // boolean	    accept(File dir, String name)
        // 测试指定文件是否应该包含在某一文件列表中
        FilenameFilter filter = (dir, name) -> name.startsWith("p");

        File[] subFiles = file.listFiles(filter);
        for (File subFile : Objects.requireNonNull(subFiles, "目录不存在或它不是一个目录")) {
            p(subFile.getName());
        }
    }
}
