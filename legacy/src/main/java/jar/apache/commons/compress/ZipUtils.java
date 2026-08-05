package jar.apache.commons.compress;

import l.demo.Demo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.stream.Stream;

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

    @Test
    public void compress() {
        compress(DEMO_DIR_PATH + "a", DEMO_DIR_PATH + "a.zip");
    }

    @Test
    public void decompress() {
        decompress(DEMO_DIR_PATH + "a.zip", DEMO_DIR_PATH);
    }

    /**
     * 压缩
     *
     * @param srcFilePath 文件/目录 路径
     * @param zipFilePath 压缩包路径
     */
    public static void compress(String srcFilePath, String zipFilePath) {
        if (srcFilePath.isBlank() || notEndsWithZip(zipFilePath)) return;

        Path srcPath = Paths.get(srcFilePath).toAbsolutePath().normalize();
        Path zipPath = Paths.get(zipFilePath).toAbsolutePath().normalize();
        try (ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(zipPath.toFile());
             Stream<Path> pathStream = Files.walk(srcPath)) {
            // Use Zip64 extensions for all entries where they are required
            zaos.setUseZip64(Zip64Mode.AsNeeded);

            pathStream.forEach(path -> {
                try {
                    String relativePath = srcPath.relativize(path).toString();
                    String entryName = relativePath.isEmpty() ?
                        srcPath.getFileName().toString() :
                        srcPath.getFileName().toString() + "/" + relativePath;
                    entryName = entryName.replace("\\", "/");
                    if (Files.isDirectory(path) && !entryName.endsWith("/")) entryName += "/";
                    ZipArchiveEntry entry = new ZipArchiveEntry(entryName);
                    zaos.putArchiveEntry(entry);
                    if (Files.isRegularFile(path)) {
                        try (InputStream is = Files.newInputStream(path)) {
                            IOUtils.copy(is, zaos);
                        }
                    }
                    zaos.closeArchiveEntry();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            });
            zaos.finish();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            try {
                Files.deleteIfExists(zipPath);
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }

    /**
     * 解压
     *
     * @param zipFilePath 压缩包路径
     * @param destDirPath 目标目录路径
     */
    public static void decompress(String zipFilePath, String destDirPath) {
        if (notEndsWithZip(zipFilePath) || destDirPath.isBlank()) return;

        Path zipPath = Paths.get(zipFilePath).toAbsolutePath().normalize();
        Path destPath = Paths.get(destDirPath).toAbsolutePath().normalize();
        try (ZipArchiveInputStream zais = new ZipArchiveInputStream(new BufferedInputStream(Files.newInputStream(zipPath), 1024 * 8))) {
            ArchiveEntry entry;
            while ((entry = zais.getNextEntry()) != null) {
                if (!zais.canReadEntryData(entry)) continue;
                Path destEntryPath = destPath.resolve(entry.getName()).normalize();
                // 防止 ZIP Slip 攻击
                if (!destEntryPath.startsWith(destPath)) continue;
                if (entry.isDirectory()) {
                    Files.createDirectories(destEntryPath);
                } else {
                    Path parentPath = destEntryPath.getParent();
                    if (parentPath != null) Files.createDirectories(parentPath);
                    try (OutputStream os = Files.newOutputStream(destEntryPath)) {
                        IOUtils.copy(zais, os);
                    }
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static boolean notEndsWithZip(String path) {
        return path == null
            || path.isBlank()
            || !path.toLowerCase(Locale.ENGLISH).endsWith(".zip");
    }
}
