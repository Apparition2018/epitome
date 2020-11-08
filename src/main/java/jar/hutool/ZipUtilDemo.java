package jar.hutool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import l.demo.Demo;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * ZipUtil      压缩工具
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E5%8E%8B%E7%BC%A9%E5%B7%A5%E5%85%B7-ZipUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/util/ZipUtil.html
 *
 * @author Arsenal
 * created on 2020/11/7 17:56
 */
public class ZipUtilDemo extends Demo {

    /**
     * static byte[]	    gzip(File file)
     * static byte[]	    zlib(File file, int level)
     */
    @Test
    public void zip() {
        // static File      zip([String srcPath, ]String zipPath[, Charset, withSrcDir])
        // 将单个文件或单个目录压缩到 zipPath
        ZipUtil.zip(DEMO_ABSOLUTE_PATH + "a/", DEMO_ABSOLUTE_PATH + "a.zip",
                StandardCharsets.UTF_8, true);

        // static File      zip(File zipFile[, Charset], withSrcDir[, FileFilter], File... srcFiles)
        // 将缩多个文件或多个目录压缩到 zipFile
        ZipUtil.zip(FileUtil.file(DEMO_ABSOLUTE_PATH + "a2.zip"), StandardCharsets.UTF_8, true,
                FileUtil.file(DEMO_ABSOLUTE_PATH + "a/"),
                FileUtil.file(DEMO_FILE_ABSOLUTE_PATH)
        );
    }

    /**
     * static File	        unzip(File zipFile[, Charset charset])
     * static byte[]	    unZlib(InputStream in[, int length])
     */
    @Test
    public void unzip() {
        // static File      unzip(String zipFilePath[, String outFileDir, Charset charset])
        // 解压
        ZipUtil.unzip(DEMO_ABSOLUTE_PATH + "a2.zip", DEMO_ABSOLUTE_PATH, StandardCharsets.UTF_8);
    }
}
