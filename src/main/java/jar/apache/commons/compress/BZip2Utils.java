package jar.apache.commons.compress;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.*;

import static l.demo.Demo.DEMO_FILE_PATH;

/**
 * BZip2 文件压缩/解压工具
 * <p>参考：<a href="http://snowolf.iteye.com/blog/644591">Bzip2 Commons 实现</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public final class BZip2Utils {
    private BZip2Utils() {
        throw new AssertionError(String.format("No %s instances for you!", this.getClass().getName()));
    }

    private static final int BUFFER = 1024;
    private static final CharSequence EXT = ".bz2";

    @Test
    public void compress() throws Exception {
        BZip2Utils.compress(DEMO_FILE_PATH);
    }

    @Test
    public void decompress() throws Exception {
        BZip2Utils.decompress(DEMO_FILE_PATH + EXT);
    }

    /** 数据压缩 */
    public static byte[] compress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        compress(bais, baos);

        return baos.toByteArray();
    }

    /** 文件压缩 */
    public static void compress(File file) throws Exception {
        compress(file, true);
    }

    /** 文件压缩 */
    private static void compress(File file, boolean delete) throws Exception {
        try (FileInputStream fis = new FileInputStream(file);
             FileOutputStream fos = new FileOutputStream(file.getPath() + EXT)) {
            compress(fis, fos);
            fos.flush();
            if (delete) {
                boolean b = file.delete();
            }
        }
    }

    /** 数据压缩 */
    private static void compress(InputStream is, OutputStream os) throws Exception {
        try (BZip2CompressorOutputStream cos = new BZip2CompressorOutputStream(os)) {
            is.transferTo(cos);
            cos.finish();
            cos.flush();
        }
    }

    /** 文件压缩 */
    private static void compress(String path) throws Exception {
        compress(path, true);
    }

    /** 文件压缩 */
    private static void compress(String path, boolean delete) throws Exception {
        File file = new File(path);
        compress(file, delete);
    }

    /** 数据解压缩 */
    public static byte[] decompress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        decompress(bais, baos);

        data = baos.toByteArray();
        return data;
    }

    /** 文件解压缩 */
    public static void decompress(File file) throws Exception {
        decompress(file, true);
    }

    /** 文件解压缩 */
    private static void decompress(File file, boolean delete) throws Exception {
        try (FileInputStream fis = new FileInputStream(file);
             FileOutputStream fos = new FileOutputStream(file.getPath().replace(EXT, StringUtils.EMPTY))) {
            decompress(fis, fos);
            fos.flush();
            if (delete) {
                boolean b = file.delete();
            }
        }
    }

    /** 数据解压缩 */
    private static void decompress(InputStream is, OutputStream os) throws Exception {
        try (BZip2CompressorInputStream cis = new BZip2CompressorInputStream(is)) {
            cis.transferTo(os);
        }
    }

    /** 文件解压缩 */
    private static void decompress(String path) throws Exception {
        decompress(path, true);
    }

    /** 文件解压缩 */
    private static void decompress(String path, boolean delete) throws Exception {
        File file = new File(path);
        decompress(file, delete);
    }
}
