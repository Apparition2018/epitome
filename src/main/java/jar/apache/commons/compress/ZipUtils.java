package jar.apache.commons.compress;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Zip 压缩/解压工具
 * <p>
 * https://blog.csdn.net/u012848709/article/details/82263154
 * http://www.cnblogs.com/luxh/archive/2012/06/28/2568758.html
 */
@Slf4j
public class ZipUtils {

    private static final String dirPath = "C:/Users/234607/git/mavenTest/src/main/java/jar/apache/commons/compress/";

    @Test
    public void compress() {
        File srcFile = new File(dirPath + "a");
        String zipPath = dirPath + "a.zip";

        if (srcFile.exists()) {
            File[] files = srcFile.listFiles();
            compress(files, zipPath);
        }
    }

    @Test
    public void compress2() {
        String srcFile = dirPath + "a";
        String zipPath = dirPath + "a.zip";

        compress2(srcFile, zipPath);
    }

    @Test
    public void decompress() {
        String zipPath = dirPath + "a.zip";

        decompress(zipPath, dirPath);
    }

    /**
     * 压缩
     * @param files     文件
     * @param zipFile   压缩路径
     */
    public static void compress(File[] files, String zipFile) {

        if (files != null && files.length > 0) {

            if (isEndsWithZip(zipFile)) {
                Map<String, File> map = new HashMap<>();
                for (File file : files) {
                    listFilesToMap(file, FilenameUtils.getBaseName(zipFile), map);
                }

                ZipArchiveOutputStream zaos = null;
                InputStream is = null;
                try {
                    zaos = new ZipArchiveOutputStream(new File(zipFile));
                    // Use Zip64 extensions for all entries where they are required
                    zaos.setUseZip64(Zip64Mode.AsNeeded);

                    for (Map.Entry<String, File> entry : map.entrySet()) {
                        File file = entry.getValue();
                        ZipArchiveEntry zae = new ZipArchiveEntry(file, entry.getKey());
                        zaos.putArchiveEntry(zae);
                        is = new FileInputStream(file);
                        if (file.isFile()) {
                            IOUtils.copy(is, zaos);
                        }
                        zaos.closeArchiveEntry();
                    }
                    zaos.finish();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                } finally {
                    IOUtils.closeQuietly(is);
                    IOUtils.closeQuietly(zaos);
                }
            }
        }
    }

    /**
     * 解压
     * @param zipFile   压缩包路径
     * @param destDir   解压路径
     */
    public static void decompress(String zipFile, String destDir) {

        if (isEndsWithZip(zipFile)) {
            File file = new File(zipFile);

            if (file.exists()) {
                destDir = destDir.endsWith(File.separator) ? destDir : destDir + File.separator;

                OutputStream os = null;
                ZipArchiveInputStream zais = null;

                try {
                    zais = new ZipArchiveInputStream(new BufferedInputStream(new FileInputStream(file), 1024 * 5));

                    ArchiveEntry entry;
                    // 把 zip 包中的每个文件读取出来，然后把文件写到指定的文件夹
                    while ((entry = zais.getNextZipEntry()) != null) {
                        if (!zais.canReadEntryData(entry)) {
                            continue;
                        }
                        file = new File(destDir, entry.getName());
                        if (entry.isDirectory()) {
                            if (!file.isDirectory() && !file.mkdirs()) {
                                throw new IOException("failed to create directory " + file);
                            }
                        } else {
                            File parent = file.getParentFile();
                            if (!parent.isDirectory() && !parent.mkdirs()) {
                                throw new IOException("failed to create directory " + parent);
                            }
                            os = Files.newOutputStream(file.toPath());
                            IOUtils.copy(zais, os);
                        }
                    }
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                } finally {
                    IOUtils.closeQuietly(os);
                    IOUtils.closeQuietly(zais);
                }
            } else {
                log.error("the file doesn't exist " + zipFile);
            }
        } else {
            log.error(zipFile + " isn't a zip file");
        }
    }

    private static void listFilesToMap(File file, String parent, Map<String, File> map) {
        String name = parent + File.separator + file.getName();
        if (file.isFile()) {
            map.put(name, file);
        } else if (file.isDirectory()) {
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                listFilesToMap(subFile, name, map);
            }
        }
    }

    public static boolean isEndsWithZip(String fileName) {
        boolean flag = false;
        if (fileName != null && !"".equals(fileName.trim())) {
            if (fileName.toLowerCase().endsWith(".zip")) {
                flag = true;
            }
        }
        return flag;
    }



    /**
     * 压缩
     * @param srcFile   文件路径
     * @param zipFile   压缩路径
     */
    public static void compress2(String srcFile, String zipFile) {
        Collection<File> filesToArchive;
        File file = Paths.get(srcFile).toFile();
        if (file.isFile()) {
            filesToArchive = Collections.singletonList(file);
        } else {
            filesToArchive = FileUtils.listFiles(file, null, true);
        }

        ZipArchiveOutputStream zaos = null;
        InputStream is = null;

        String rootPath = file.getParent();
        rootPath = rootPath.endsWith(File.separator) ? rootPath : rootPath + File.separator;

        try {
            zaos = new ZipArchiveOutputStream(new File(zipFile));

            for (File f : filesToArchive) {
                ArchiveEntry entry = zaos.createArchiveEntry(f, f.getCanonicalPath().substring(rootPath.length()));
                zaos.putArchiveEntry(entry);
                if(f.isFile()) {
                    is = Files.newInputStream(f.toPath());
                    IOUtils.copy(is, zaos);
                }
                zaos.closeArchiveEntry();
            }
            zaos.finish();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(zaos);
            IOUtils.closeQuietly(is);
        }

    }


}
