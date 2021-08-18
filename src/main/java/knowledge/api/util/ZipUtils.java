package knowledge.api.util;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * ZipUtils
 * <p>
 * https://docs.oracle.com/javase/8/docs/api/java/util/zip/ZipOutputStream.html
 * https://docs.oracle.com/javase/8/docs/api/java/util/zip/ZipFile.html
 * https://docs.oracle.com/javase/8/docs/api/java/util/zip/ZipEntry.html
 *
 * @author Arsenal
 * created on 2020/11/8 16:44
 */
public class ZipUtils extends Demo {

    @Test
    public void testZip() throws IOException {
        zip(DEMO_PATH + "a3.zip", null, DEMO_PATH + "a/", DEMO_FILE_PATH);
    }

    @Test
    public void testUnzip() throws IOException {
        unzip(DEMO_PATH + "a3.zip", DEMO_PATH);
    }

    /**
     * 压缩
     */
    public void zip(String zipPath, String path, String... srcPaths) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(zipPath)));
        File[] srcFiles = new File[srcPaths.length];
        for (int i = 0; i < srcPaths.length; i++) {
            srcFiles[i] = new File(srcPaths[i]);
        }
        zip(zos, path, srcFiles);
        zos.close();
    }

    /**
     * 压缩
     */
    public void zip(File zipFile, String path, File... srcFiles) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
        zip(zos, path, srcFiles);
        zos.close();
    }

    /**
     * 压缩文件
     *
     * @param outStream 压缩输出流
     * @param path      文件夹，为空则不添加文件夹
     * @param srcFiles  压缩源文件
     */
    public void zip(ZipOutputStream outStream, String path, File... srcFiles) throws IOException {
        if (path == null) {
            path = "";
        }
        path = path.replaceAll("\\*", "/");
        if (path.length() > 0 && !path.endsWith("/")) {
            path += "/";
        }
        byte[] buf = new byte[1024];
        for (File srcFile : srcFiles) {
            if (srcFile.isDirectory()) {
                File[] files = srcFile.listFiles();
                String srcPath = srcFile.getName();
                srcPath = srcPath.replaceAll("\\*", "/");
                if (!srcPath.endsWith("/")) {
                    srcPath += "/";
                }
                // void	        putNextEntry(ZipEntry e)
                // 开始写入新的 ZIP 文件 entry，并将流定位到 entry 数据的开始位置
                outStream.putNextEntry(new ZipEntry(path + srcPath));
                zip(outStream, path + srcPath, files);
            } else {
                FileInputStream fis = new FileInputStream(srcFile);
                outStream.putNextEntry(new ZipEntry(path + srcFile.getName()));
                int len;
                while ((len = fis.read(buf)) > 0) {
                    // void	    write(byte[] b, int off, int len)
                    // 将字节数组写入当前 ZIP entry 数据
                    outStream.write(buf, 0, len);
                }
                // void	            closeEntry()
                // 关闭当前  ZIP entry 并定位流以便写入下一个 entry
                outStream.closeEntry();
                fis.close();
            }
        }
    }

    /**
     * 解压
     */
    public void unzip(String zipPath, String descDir) throws IOException {
        unzip(new File(zipPath), new File(descDir), null);
    }

    /**
     * 解压
     */
    public void unzip(File zipFile, File destFile) throws IOException {
        unzip(zipFile, destFile, null);
    }

    /**
     * 解压
     *
     * @param zipFile  解压文件
     * @param destFile 目标目录
     * @param zipList  压缩文件内路径集合
     */
    @SuppressWarnings("rawtypes")
    public void unzip(File zipFile, File destFile, List<String> zipList) throws IOException {
        if (!destFile.exists()) {
            destFile.mkdirs();
        }
        ZipFile zip = new ZipFile(zipFile);
        // Enumeration<? extends ZipEntry>  entries()
        // 返回 ZIP 文件 entries 的枚举
        for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            // InputStream	                getInputStream(ZipEntry entry)
            // 返回用于读取指定 zip 文件 entry 内容的输入流
            InputStream is = zip.getInputStream(entry);
            String outPath = zipEntryName.replaceAll("\\*", "/");
            int index = outPath.lastIndexOf('/');
            if (index != -1) {
                File file = new File(destFile, outPath.substring(0, outPath.lastIndexOf('/')));
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            File realFile = new File(destFile, outPath);
            if (zipList != null) {
                zipList.add(outPath);
            }
            if (realFile.isDirectory()) {
                continue;
            }

            OutputStream os = new FileOutputStream(realFile);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = is.read(buf1)) > 0) {
                os.write(buf1, 0, len);
            }
            is.close();
            os.close();
        }
    }
}
