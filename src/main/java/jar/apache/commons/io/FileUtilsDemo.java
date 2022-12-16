package jar.apache.commons.io;

import l.demo.Demo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * FileUtils
 * https://www.cnblogs.com/my-blogs-for-everone/p/8029846.html
 * https://www.cnblogs.com/xiaozu/p/4555942.html
 * http://commons.apache.org/proper/commons-io/apidocs/org/apache/commons/io/FileUtils.html
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class FileUtilsDemo extends Demo {

    // 写
    @Test
    public void write() throws IOException {
        File file = new File(DEMO_FILE_PATH);

        // write()
        FileUtils.write(file, "静夜思\n", UTF_8, false);

        List<String> lines = new ArrayList<>();
        lines.add("床前明月光，");
        lines.add("疑是地上霜。");
        // writeLines()
        FileUtils.writeLines(file, lines, true);

        // writeStringToFile()
        FileUtils.writeStringToFile(file, "举头望明月，\n低头思故乡。", UTF_8, true);
    }

    // 读
    @Test
    public void read() throws IOException {
        File file = new File(DEMO_FILE_PATH);

        // readFileToString()
        p(FileUtils.readFileToString(file, UTF_8));

        // readLines()
        p(FileUtils.readLines(file, UTF_8));
    }

    // 删除
    @Test
    public void delete() throws IOException {
        File file1 = new File(DEMO_PATH + "a/b/c");
        File file2 = new File(DEMO_PATH + "a/b");

        if (file1.mkdirs()) {
            // deleteDirectory()
            FileUtils.deleteDirectory(file2);
            // deleteQuietly
            FileUtils.deleteQuietly(file2); // 文件夹不是空仍然可以被删除，永远不会抛出异常
        }
    }

    // 移动
    @Test
    public void move() throws IOException {
        File file1 = new File(DEMO_FILE_PATH);
        File file2 = new File(DEMO_PATH + "a/demo");

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
        File file1 = new File(DEMO_FILE_PATH);
        File file2 = new File(DEMO_PATH + "a");

        // copyFileToDirectory()
        FileUtils.copyFileToDirectory(file1, file2);

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
        File file1 = new File(DEMO_PATH);
        File file2 = new File(DEMO_FILE_PATH);

        // directoryContains()
        // 判断是否包含文件或者文件夹
        p(FileUtils.directoryContains(file1, file2)); // true

        // getTempDirectoryPath(), getUserDirectoryPath()
        // 获得临时目录和用户目录
        p(FileUtils.getTempDirectoryPath());   // C:\Users\NL-PC001\AppData\Local\Temp\
        p(FileUtils.getUserDirectoryPath());   // C:\Users\NL-PC001

        // openOutputStream()
        // 打开流
        FileOutputStream fos = FileUtils.openOutputStream(file2, true);
        fos.write("\n诗圣李白".getBytes());
        fos.close();

        // sizeOf(), sizeOfDirectory()
        // 文件/文件夹大小
        p(FileUtils.sizeOf(file2));            // 100
        p(FileUtils.sizeOfDirectory(file1));   // 1484

        // listFiles(File directory, String[] extensions, boolean recursive)
        // 在给定目录(或其子目录)中查找与扩展名数组匹配的文件
        Collection<File> coll = FileUtils.listFiles(file1, null, true);
        p(coll);
        // [src\main\resources\demo\a\b\demo, src\main\resources\demo\demo, src\main\resources\demo\demo.csv, src\main\resources\demo\demo.xml, src\main\resources\demo\EAN-13.png, src\main\resources\demo\hutool\captcha_circle.jpg, src\main\resources\demo\hutool\captcha_line.jpg, src\main\resources\demo\hutool\captcha_math.jpg, src\main\resources\demo\hutool\captcha_random.jpg, src\main\resources\demo\hutool\captcha_shear.jpg, src\main\resources\demo\hutool\capture.jpg, src\main\resources\demo\hutool\capture_compress.jpg, src\main\resources\demo\hutool\capture_convert.jpg, src\main\resources\demo\hutool\capture_createImage.jpg, src\main\resources\demo\hutool\capture_cut.jpg, src\main\resources\demo\hutool\capture_flip.jpg, src\main\resources\demo\hutool\capture_gray.jpg, src\main\resources\demo\hutool\capture_pressImage.jpg, src\main\resources\demo\hutool\capture_pressText.jpg, src\main\resources\demo\hutool\capture_rotate.jpg, src\main\resources\demo\hutool\capture_scale.jpg, src\main\resources\demo\hutool\demo.xlsx, src\main\resources\demo\hutool\example.setting, src\main\resources\demo\hutool\example_copy.setting, src\main\resources\demo\Input, src\main\resources\demo\io.dat, src\main\resources\demo\join.txt, src\main\resources\demo\key, src\main\resources\demo\Output, src\main\resources\demo\person.js, src\main\resources\demo\person.obj, src\main\resources\demo\person.xlsx, src\main\resources\demo\person.xml, src\main\resources\demo\QRCode.png, src\main\resources\demo\Serialization.obj, src\main\resources\demo\spring\spring-ann.xml, src\main\resources\demo\spring\spring-bean.xml, src\main\resources\demo\student.xml]

        // listFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter)
        coll = FileUtils.listFiles(file1, DirectoryFileFilter.INSTANCE, null);
        p(coll);
        // [src\main\resources\demo\a, src\main\resources\demo\hutool, src\main\resources\demo\spring]
    }

}
