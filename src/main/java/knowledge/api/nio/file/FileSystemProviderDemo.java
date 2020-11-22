package knowledge.api.nio.file;

import com.alibaba.fastjson.JSON;
import l.demo.Demo;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.concurrent.ThreadLocalRandom;

/**
 * FileSystemProvider
 * 文件系统的服务提供者类
 * 文件的相关操作最终通过 FileSystemProvider 来提供，其不同的底层系统又不同的实现。
 * 此类主要定义了如何在其对应的文件系统定位和加载文件，以及文件中常见的删除、拷贝等操作。
 * 四个子类：AbstractFileSystemProvider, JarFileSystemProvider, WindowFilesSystemProvider, ZipFilesSystemProvider
 * <p>
 * https://docs.oracle.com/javase/8/docs/api/java/nio/file/spi/FileSystemProvider.html
 * https://blog.csdn.net/huitoukest/article/details/106983050
 *
 * @author ljh
 * created on 2020/11/6 9:43
 */
public class FileSystemProviderDemo extends Demo {

    /**
     * 此处仅作示例，一般通过 Files 来实现相关操作
     */
    @Test
    public void testFileSystemProvider() throws IOException {
        FileSystemProvider fileSystemProvider = FileSystems.getDefault().provider();
        Path path = Paths.get(DEMO_PATH, "demo" + ThreadLocalRandom.current().nextInt(999));
        
        // abstract String	getScheme()                 返回标识此提供程序的 URI 方案
        p(fileSystemProvider.getScheme());

        // abstract void	createDirectory(Path dir, FileAttribute<?>... attrs)
        // 创建文件夹
        fileSystemProvider.createDirectory(path);

        // abstract <A extends BasicFileAttributes> A	
        // readAttributes(Path path, Class<A> type, LinkOption... options)
        // 以批量操作的形式读取文件的属性
        BasicFileAttributes basicFileAttributes = fileSystemProvider.readAttributes(path, BasicFileAttributes.class);
        p(JSON.toJSON(basicFileAttributes));
        // {"other":false,"system":false,"hidden":false,"symbolicLink":false,"archive":false,"readOnly":false,"regularFile":false,"directory":true}
        
        // boolean	        deleteIfExists(Path path)   如果 path 存在则删除
        fileSystemProvider.deleteIfExists(path);
    }
}
