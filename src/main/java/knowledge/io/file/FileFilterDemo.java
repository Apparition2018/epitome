package knowledge.io.file;

import l.demo.Demo;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;

/**
 * FileFilter
 * 用于抽象路径名的过滤器
 * https://www.runoob.com/manual/jdk1.6/java/io/FileFilter.html
 *
 * @author ljh
 * created on 2020/9/14 10:40
 */
public class FileFilterDemo extends Demo {

    @Test
    public void testFileFilter() {
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
