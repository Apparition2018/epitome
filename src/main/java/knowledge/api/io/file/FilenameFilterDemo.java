package knowledge.api.io.file;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;

/**
 * FilenameFilter
 */
public class FilenameFilterDemo {

    @Test
    public void filenameFilter() {
        File file = new File("src/main/java/knowledge/api/io/file");

        // boolean	accept(File dir, String name)
        // 测试指定文件是否应该包含在某一文件列表中
        FilenameFilter filter = (dir, name) -> name.startsWith("File");

        String[] subs = file.list(filter);
        if (subs != null) {
            for (String sub : subs) {
                System.out.println(sub);
            }
        } else {
            System.out.println("目录不存在或它不是一个目录");
        }
    }

}
