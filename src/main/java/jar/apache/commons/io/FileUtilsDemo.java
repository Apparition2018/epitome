package jar.apache.commons.io;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * FileUtils
 * <p>
 * https://www.cnblogs.com/my-blogs-for-everone/p/8029846.html
 * http://commons.apache.org/proper/commons-io/apidocs/org/apache/commons/io/FileUtils.html
 */
public class FileUtilsDemo {

    private final static String dirPath = "src/main/java/jar/apache/commons/io/";

    // 写
    @Test
    public void write() throws IOException {
        File file = new File(dirPath + "FileUtils.txt");

        // write()
        FileUtils.write(file, "静夜思\n", "UTF-8", false);

        List<String> lines = new ArrayList<>();
        lines.add("床前明月光，");
        lines.add("疑是地上霜。");
        // writeLines()
        FileUtils.writeLines(file, lines, true);

        // writeStringToFile()
        FileUtils.writeStringToFile(file, "举头望明月，\n低头思故乡。", "UTF-8", true);
    }

    // 读
    @Test
    public void read() throws IOException {
        File file = new File(dirPath + "FileUtils.txt");

        // readFileToString()
        p(FileUtils.readFileToString(file, "UTF-8"));

        // readLines()
        p(FileUtils.readLines(file, "UTF-8"));
        // [静夜思, 床前明月光，, 疑是地上霜。, 举头望明月，, 低头思故乡。]
    }

    // 删除
    @Test
    public void delete() throws IOException {
        File file1 = new File(dirPath + "a/b/c");
        File file2 = new File(dirPath + "a");

        boolean b = file1.mkdirs();
        if (b) {
            // deleteDirectory()
            FileUtils.deleteDirectory(file2);
            // deleteQuietly
            FileUtils.deleteQuietly(file2); // 文件夹不是空仍然可以被删除，永远不会抛出异常
        }
    }

    // 移动
    @Test
    public void move() throws IOException {
        File file1 = new File(dirPath + "FileUtils.txt");
        File file2 = new File(dirPath + "a/FileUtils.txt");

        // moveFile()
        FileUtils.moveFile(file1, file2);
        FileUtils.moveFile(file2, file1);

        // 更多方法
        // moveDirectory()
        // moveFileToDirectory()
        // moveToDirectory()
    }

    // 复制
    @Test
    public void copy() throws IOException, InterruptedException {
        File file1 = new File(dirPath + "FileUtils.txt");
        File file2 = new File(dirPath + "a");

        // copyFileToDirectory()
        FileUtils.copyFileToDirectory(file1, file2);

        Thread.sleep(3000);
        FileUtils.deleteQuietly(file2);

        // 更多方法
        // copyDirectoryToDirectory()
        // copyFile()
        // copyInputStreamToFile()
        // copyToDirectory()
        // copyToFile()
        // copyURLToFil()
    }

    // 其它
    @Test
    public void other() throws IOException {
        File file1 = new File(dirPath);
        File file2 = new File(dirPath + "FileUtils.txt");

        // directoryContains()
        // 判断是否包含文件或者文件夹
        p(FileUtils.directoryContains(file1, file2)); // true

        // getTempDirectoryPath(), getUserDirectoryPath()
        // 获得临时目录和用户目录
        p(FileUtils.getTempDirectoryPath());   // C:\Users\234607\AppData\Local\Temp\
        p(FileUtils.getUserDirectoryPath());   // C:\Users\234607

        // openOutputStream()
        // 打开流
        FileOutputStream fos = FileUtils.openOutputStream(file2, true);
        fos.write("\n诗圣李白".getBytes());
        fos.close();

        // sizeOf(), sizeOfDirectory()
        // 文件/文件夹大小
        p(FileUtils.sizeOf(file2));            // 100
        p(FileUtils.sizeOfDirectory(file1));   // 15833

        // listFiles()
        // 在给定目录(或其子目录)中查找与扩展名数组匹配的文件
        Collection<File> coll = FileUtils.listFiles(file1, null, true);
        p(coll);
        // [src\main\java\jar\apache\commons\io\FilenameUtilsDemo.java, src\main\java\jar\apache\commons\io\FileUtils.txt, src\main\java\jar\apache\commons\io\FileUtilsDemo.java, src\main\java\jar\apache\commons\io\IOUtils.txt, src\main\java\jar\apache\commons\io\IOUtilsDemo.java, src\main\java\jar\apache\commons\io\write.txt, src\main\java\jar\apache\commons\io\writeLines.txt]
    }

    public static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}
