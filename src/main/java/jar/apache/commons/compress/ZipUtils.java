package jar.apache.commons.compress;

import l.demo.Demo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Zip 压缩/解压工具
 *
 * @author ljh
 * @see <a href="https://commons.apache.org/proper/commons-compress/">Commons Compress</a>
 * @see <a href="https://blog.csdn.net/u012848709/article/details/82263154">Commons Compress ZIP 压缩解压</a>
 * @since 2019/8/8 19:39
 */
@Slf4j
public final class ZipUtils extends Demo {
    private ZipUtils() {
        throw new AssertionError(String.format("No %s instances for you!", this.getClass().getName()));
    }

    @Test
    public void compress() {
        compress(new File(DEMO_DIR_PATH + "a").listFiles(), DEMO_DIR_PATH + "a.zip");
    }

    @Test
    public void compress2() {
        compress2(DEMO_FILE_PATH, DEMO_DIR_PATH + "demo.zip");
    }

    @Test
    public void decompress() {
        decompress(DEMO_DIR_PATH + "a.zip", DEMO_DIR_PATH);
    }

    /**
     * 压缩
     *
     * @param files   文件夹和文件
     * @param zipFile 压缩路径
     */
    public static void compress(File[] files, String zipFile) {

        if (null != files && files.length > 0) {
            if (isEndsWithZip(zipFile)) {
                Map<String, File> map = new HashMap<>();
                for (File file : files) {
                    listFilesToMap(file, FilenameUtils.getBaseName(zipFile), map);
                }

                try (ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(new File(zipFile))) {
                    // Use Zip64 extensions for all entries where they are required
                    zaos.setUseZip64(Zip64Mode.AsNeeded);

                    for (Map.Entry<String, File> entry : map.entrySet()) {
                        File file = entry.getValue();
                        ZipArchiveEntry zae = new ZipArchiveEntry(file, entry.getKey());
                        zaos.putArchiveEntry(zae);
                        try (InputStream is = Files.newInputStream(file.toPath())) {
                            if (file.isFile()) {
                                IOUtils.copy(is, zaos);
                            }
                        }
                        zaos.closeArchiveEntry();
                    }
                    zaos.finish();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 压缩
     *
     * @param srcFile 文件路径
     * @param zipFile 压缩路径
     */
    public static void compress2(String srcFile, String zipFile) {
        Collection<File> filesToArchive;
        File file = Paths.get(srcFile).toFile();
        if (file.isFile()) {
            filesToArchive = Collections.singletonList(file);
        } else {
            filesToArchive = FileUtils.listFiles(file, null, true);
        }

        String rootPath = file.getParent();
        rootPath = rootPath.endsWith(File.separator) ? rootPath : rootPath + File.separator;

        try (ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(new File(zipFile))) {

            for (File f : filesToArchive) {
                ZipArchiveEntry entry = zaos.createArchiveEntry(f, f.getCanonicalPath().substring(rootPath.length()));
                zaos.putArchiveEntry(entry);
                if (f.isFile()) {
                    try (InputStream is = Files.newInputStream(f.toPath())) {
                        IOUtils.copy(is, zaos);
                    }
                }
                zaos.closeArchiveEntry();
            }
            zaos.finish();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

    }

    /**
     * 解压
     *
     * @param zipFile 压缩包路径
     * @param destDir 解压路径
     */
    public static void decompress(String zipFile, String destDir) {

        if (isEndsWithZip(zipFile)) {
            File file = new File(zipFile);

            if (file.exists()) {
                destDir = destDir.endsWith(File.separator) ? destDir : destDir + File.separator;

                try (ZipArchiveInputStream zais = new ZipArchiveInputStream(new BufferedInputStream(Files.newInputStream(file.toPath()), 1024 * 5))) {

                    ArchiveEntry entry;
                    // 把 zip 包中的每个文件读取出来，然后把文件写到指定的文件夹
                    while (null != (entry = zais.getNextEntry())) {
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
                            try (OutputStream os = Files.newOutputStream(file.toPath())) {
                                IOUtils.copy(zais, os);
                            }
                        }
                    }
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
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
        if (null != fileName && StringUtils.isNotEmpty(fileName.trim())) {
            if (fileName.toLowerCase(Locale.ENGLISH).endsWith(".zip")) {
                flag = true;
            }
        }
        return flag;
    }
}
