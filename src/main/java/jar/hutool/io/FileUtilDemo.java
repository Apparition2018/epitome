package jar.hutool.io;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;
import l.demo.Demo;
import org.apache.commons.codec.digest.XXHash32;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * FileUtil
 * FileUtil 继承 PathUtil
 * FileUtil 方法名与 Linux 相一致
 * <p>
 * static boolean	            isAbsolutePath(String path)                                     判断是否位绝对路径
 * static boolean	            isEmpty(File file)                                              文件是否为空
 * static boolean	            isNotEmpty(File file)                                           文件是否不为空
 * static boolean	            isModifed(File file, long lastModifyTime)                       判断文件是否被改动
 * static boolean	            isSub(File parent, File sub)                                    判断给定的目录是否为给定文件或文件夹的子目录
 * static boolean	            isWindows()                                                     是否为 Windows 环境
 * static boolean	            newerThan(File file, long timeMillis/File reference)            给定文件或目录的最后修改时间是否晚于给定时间
 * static boolean	            pathEndsWith(File file, String suffix)                          判断文件路径是否有指定后缀，忽略大小写
 * static boolean	            pathEquals(File file1, File file2)                              文件路径是否相同，Windows 下忽略大小写，Linux 不忽略
 * <p>
 * static File	                createTempFile([String prefix, String suffix, ]
 * -                                File dir[, boolean isReCreat])                              创建临时文件，名为 prefix[Randon].suffix From com.jodd.io.FileUtil
 * <p>
 * static File	                convertCharset(File file, Charset charset, Charset destCharset) 转换文件编码
 * static File	                convertLineSeparator(File file, Charset charset,
 * -                                LineSeparator lineSeparator)                                转换换行符
 * <p>
 * static <T> File	            appendLines(Collection<T>/String, path/file, String charset)    将 XXX 写入文件，追加模式
 * static <T> File	            appendUtf8Lines(Collection<T>/String, path/file)                将 XXX 写入文件，追加模式
 * <p>
 * static boolean	            containsInvalid(String fileName)                                文件名中是否包含在 Windows 下不支持的非法字符，包括： \ / : * ? " < > |
 * static String	            cleanInvalid(String fileName)                                   清除文件名中的在 Windows 下不支持的非法字符，包括： \ / : * ? " < > |
 * <p>
 * static BOMInputStream	    getBOMInputStream(File file)                                    获得 BOM 输入流，用于处理带 BOM 头的文件
 * static BufferedWriter	    getWriter(file/path, Charset/charset, isAppend)                 获得一个带缓存的写入对象
 * static PrintWriter	        getPrintWriter(file/path, Charset/charset, isAppend)            获得一个打印写入对象，可以有 print
 * <p>
 * https://hutool.cn/docs/#/core/IO/%E6%96%87%E4%BB%B6%E5%B7%A5%E5%85%B7%E7%B1%BB-FileUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/io/FileUtil.html
 *
 * @author ljh
 * created on 2020/10/29 17:46
 */
public class FileUtilDemo extends Demo {

    @Test
    public void testFileUtil() {
        // 创建文件夹
        FileUtil.mkParentDirs(new File(DEMO_PATH));
        File dir = FileUtil.mkdir(new File(DEMO_PATH));

        // 修复路径
        String dirPath = FileUtil.normalize(dir.getAbsolutePath());

        // 列出目录和文件
        FileUtil.ls(dirPath);

        // 创建文件及其父目录
        FileUtil.touch(dir, "demo");
        // 会检查 slip 漏洞
        File file = FileUtil.file(dir, "demo");

        // 检查父完整路径是否为自路径的前半部分，如果不是说明不是子路径，可能存在 slip 注入
        p(FileUtil.checkSlip(dir, file));

        // 写入文件
        List<String> list = ListUtil.toList("静夜思", "窗前明月光，", "疑是地上霜，", "举头望明月，", "低头思故乡。");
        FileUtil.writeUtf8Lines(list, file);

        // 读取文件每一行数据
        p(FileUtil.readUtf8Lines(file));
        // 读取文件数据，长度不能超过 Integer.MAX_VALUE
        p(new String(FileUtil.readBytes(file), StandardCharsets.UTF_8));
        // 读取文件数据，按照给定的 readerHandler
        p(FileUtil.loadUtf8(file, bufferedReader -> {
            String line;
            StringBuilder brString = new StringBuilder();
            while (null != (line = bufferedReader.readLine())) {
                brString.append(line);
            }
            return brString.toString();
        }));


        File destFile = new File(dirPath + "/a/demo");
        File dir2 = new File(dirPath + "/a/b/c/");

        // 移动文件
        FileUtil.move(file, destFile, true);

        // 复制文件或目录
        FileUtil.copy(destFile, file, true);
        FileUtil.copyFilesFromDir(dir, dir2, true);

        // 比较两个文件内容是否相同
        p(FileUtil.contentEquals(file, destFile));
        p(FileUtil.contentEqualsIgnoreEOL(file, destFile, StandardCharsets.UTF_8));

        // 删除文件或者文件夹
        if (FileUtil.exist(destFile)) FileUtil.del(destFile);

        // 清理空文件夹，递归删除空的文件夹，不删除文件
        FileUtil.cleanEmpty(dir2);
        // 清空文件夹
        FileUtil.clean(dir2);
    }

    /**
     * 获取相关
     * <p>
     * static String	getLineSeparator()                  获取当前系统的换行分隔符
     * static Date	    lastModifiedTime(file/path)         指定文件最后修改时间
     */
    @Test
    public void testFileUtil2() {
        File file = new File(DEMO_FILE_PATH + ".zip");
        String filePath = file.getPath();

        // 文件名
        p(FileUtil.getName(file));                  // demo.zip
        // 主文件名
        p(FileUtil.mainName(file));                 // demo
        p(FileUtil.getPrefix(file));                // demo
        // 扩展名
        p(FileUtil.extName(file));                  // zip
        p(FileUtil.getSuffix(file));                // zip
        // 文件类型，根据文件流的头部信息
        p(FileUtil.getType(file));                  // zip
        // MimeType，根据文件扩展名
        p(FileUtil.getMimeType(filePath));          // application/zip
        // 绝对路径
        p(FileUtil.getAbsolutePath(file));          // D:\L\git\epitome\src\main\resources\demo\demo.zip
        // 规范的绝对路径
        p(FileUtil.getCanonicalPath(file));         // D:\L\git\epitome\src\main\resources\demo\demo.zip
        // 指定层级的父路径
        p(FileUtil.getParent(file, 2));             // D:\L\git\epitome\src\main\resources
        // 总大小
        p(FileUtil.size(file));                     // 227
        // 可读的文件大小
        p(FileUtil.readableFileSize(file));         // 227 B
        // 计算文件 XXX 校验码
        p(FileUtil.checksum(file, new XXHash32())
                .getValue());                       // 2688023182
        // 计算文件 CRC32 校验码
        // CRC32：Cyclic Redundancy Check，循环冗余校验；在数据存储和数据通讯领域，为了保证数据的正确，最常用的校验
        // 特点：检错能力极强，开销小
        p(FileUtil.checksumCRC32(file));            // 696136591
        // 最后一个文件路径分隔符的位置
        p(FileUtil.lastIndexOfSeparator(filePath)); // 23

        // 用户目录
        p(FileUtil.getUserHomeDir());               // C:\Users\NL-PC001
        // 用户路径（绝对路径）
        p(FileUtil.getUserHomePath());              // C:\Users\NL-PC001
        // 临时文件目录
        p(FileUtil.getTmpDir());                    // C:\Users\NL-PC001\AppData\Local\Temp
        // 获取临时文件路径（绝对路径）
        p(FileUtil.getTmpDirPath());                // C:\Users\NL-PC001\AppData\Local\Temp\
        // Web 项目下的 web root路径
        p(FileUtil.getWebRoot());                   // D:\L\git\epitome
    }

    /**
     * static void	    tail(File file, Charset charset)
     * 文件内容跟随器，实现类似 Linux 下 tail -f 命令功能，此方法会阻塞当前线程
     */
    @Test
    public void tail() {
        FileUtil.tail(new File(DEMO_FILE_PATH), StandardCharsets.UTF_8);
    }
}
