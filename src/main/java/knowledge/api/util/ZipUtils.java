package knowledge.api.util;

import cn.hutool.core.util.StrUtil;
import l.demo.Demo;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * ZipUtils
 * <pre>
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/zip/ZipOutputStream.html">ZipOutputStream api</a>
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/zip/ZipFile.html">ZipFile api</a>
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/zip/ZipEntry.html">ZipEntry api</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/11/8 16:44
 */
public final class ZipUtils extends Demo {
    private ZipUtils() {
        throw new AssertionError(String.format("No %s instances for you!", this.getClass().getName()));
    }

    @Test
    public void testZip() throws IOException {
        zip(DEMO_DIR_PATH + "a3.zip", null, DEMO_DIR_PATH + "a/", DEMO_FILE_PATH);
    }

    @Test
    public void testUnzip() throws IOException {
        unzip(DEMO_DIR_PATH + "a3.zip", DEMO_DIR_PATH);
    }

    /** 压缩 */
    public static void zip(String zipPath, String path, String... srcPaths) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(new File(zipPath).toPath()))) {
            File[] srcFiles = new File[srcPaths.length];
            IntStream.range(0, srcPaths.length).forEach(i -> srcFiles[i] = new File(srcPaths[i]));
            zip(zos, path, srcFiles);
        }
    }

    /** 压缩 */
    public static void zip(File zipFile, String path, File... srcFiles) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipFile.toPath()))) {
            zip(zos, path, srcFiles);
        }
    }

    /**
     * 压缩文件
     *
     * @param outStream 压缩输出流
     * @param path      文件夹，为空则不添加文件夹
     * @param srcFiles  压缩源文件
     */
    public static void zip(ZipOutputStream outStream, String path, File... srcFiles) throws IOException {
        if (path == null) {
            path = StringUtils.EMPTY;
        }
        path = path.replaceAll("\\*", StrUtil.SLASH);
        if (!path.isEmpty() && !path.endsWith(StrUtil.SLASH)) {
            path += StrUtil.SLASH;
        }
        for (File srcFile : srcFiles) {
            if (srcFile.isDirectory()) {
                File[] files = srcFile.listFiles();
                String srcPath = srcFile.getName();
                srcPath = srcPath.replaceAll("\\*", StrUtil.SLASH);
                if (!srcPath.endsWith(StrUtil.SLASH)) {
                    srcPath += StrUtil.SLASH;
                }
                // void	        putNextEntry(ZipEntry e)
                // 开始写入新的 ZIP 文件 entry，并将流定位到 entry 数据的开始位置
                outStream.putNextEntry(new ZipEntry(path + srcPath));
                zip(outStream, path + srcPath, files);
            } else {
                try (FileInputStream fis = new FileInputStream(srcFile)) {
                    outStream.putNextEntry(new ZipEntry(path + srcFile.getName()));
                    fis.transferTo(outStream);
                } finally {
                    // void	            closeEntry()
                    // 关闭当前  ZIP entry 并定位流以便写入下一个 entry
                    outStream.closeEntry();
                }
            }
        }
    }

    /** 解压 */
    public static void unzip(String zipPath, String descDir) throws IOException {
        unzip(new File(zipPath), new File(descDir), null);
    }

    /** 解压 */
    public static void unzip(File zipFile, File destFile) throws IOException {
        unzip(zipFile, destFile, null);
    }

    /**
     * 解压
     *
     * @param zipFile  解压文件
     * @param destFile 目标目录
     * @param zipList  压缩文件内路径集合
     */
    public static void unzip(File zipFile, File destFile, List<String> zipList) throws IOException {
        if (!destFile.exists()) {
            destFile.mkdirs();
        }
        try (ZipFile zip = new ZipFile(zipFile)) {
            // Enumeration<? extends ZipEntry>  entries()
            // 返回 ZIP 文件 entries 的枚举
            for (Enumeration<?> entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                // InputStream	                getInputStream(ZipEntry entry)
                // 返回用于读取指定 zip 文件 entry 内容的输入流
                try (InputStream is = zip.getInputStream(entry)) {
                    String outPath = zipEntryName.replaceAll("\\*", StrUtil.SLASH);
                    int index = outPath.lastIndexOf('/');
                    if (index != -1) {
                        File file = new File(destFile, outPath.substring(0, outPath.lastIndexOf('/')));
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                    }
                    // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                    File realFile = new File(destFile, outPath);
                    Optional.ofNullable(zipList).ifPresent(list -> list.add(outPath));
                    if (realFile.isDirectory()) {
                        continue;
                    }

                    try (OutputStream os = Files.newOutputStream(realFile.toPath())) {
                        is.transferTo(os);
                    }
                }
            }
        }
    }
}
