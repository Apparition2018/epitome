package knowledge.api.io.file;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

public class FileFilterDemo {

    @Test
    public void fileFilter() {
        File file = new File("src/main/java/knowledge/api/io/file");


        FileFilter filter = pathname -> {
            // boolean	accept(File pathname)
            // 测试指定抽象路径名是否应该包含在某个路径名列表中
            String name = pathname.getName();
            return name.startsWith("File");
        };

        File[] subs = file.listFiles(filter);
        if (subs != null) {
            for (File sub : subs) {
                System.out.println(sub.getName());
            }
        } else {
            System.out.println("目录不存在或它不是一个目录");
        }
    }
}
