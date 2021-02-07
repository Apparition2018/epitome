package jar.apache.commons.compress;

import l.demo.Demo;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * BZip2 文件压缩/解压工具
 * http://snowolf.iteye.com/blog/644591
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class BZip2Utils extends Demo {

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

    /**
     * 数据压缩
     */
    public static byte[] compress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        compress(bais, baos);

        return baos.toByteArray();
    }

    /**
     * 文件压缩
     */
    public static void compress(File file) throws Exception {
        compress(file, true);
    }

    /**
     * 文件压缩
     */
    private static void compress(File file, boolean delete) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file.getPath() + EXT);

        compress(fis, fos);

        fis.close();
        fos.flush();
        fos.close();

        if (delete) {
            boolean b = file.delete();
        }
    }

    /**
     * 数据压缩
     */
    private static void compress(InputStream is, OutputStream os) throws Exception {

        BZip2CompressorOutputStream cos = new BZip2CompressorOutputStream(os);

        int count;
        byte[] data = new byte[BUFFER];
        while ((count = is.read(data, 0, BUFFER)) != -1) {
            cos.write(data, 0, count);
        }

        cos.finish();

        cos.flush();
        cos.close();
    }

    /**
     * 文件压缩
     */
    private static void compress(String path) throws Exception {
        compress(path, true);
    }

    /**
     * 文件压缩
     */
    private static void compress(String path, boolean delete) throws Exception {
        File file = new File(path);
        compress(file, delete);
    }

    /**
     * 数据解压缩
     */
    public static byte[] decompress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        decompress(bais, baos);
        
        data = baos.toByteArray();
        return data;
    }

    /**
     * 文件解压缩
     */
    public static void decompress(File file) throws Exception {
        decompress(file, true);
    }

    /**
     * 文件解压缩
     */
    private static void decompress(File file, boolean delete) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file.getPath().replace(EXT, ""));
        decompress(fis, fos);
        fis.close();
        fos.flush();
        fos.close();

        if (delete) {
            boolean b = file.delete();
        }
    }

    /**
     * 数据解压缩
     */
    private static void decompress(InputStream is, OutputStream os) throws Exception {
        BZip2CompressorInputStream cis = new BZip2CompressorInputStream(is);

        int count;
        byte[] data = new byte[BUFFER];
        while ((count = cis.read(data, 0, BUFFER)) != -1) {
            os.write(data, 0, count);
        }
        cis.close();
    }

    /**
     * 文件解压缩
     */
    private static void decompress(String path) throws Exception {
        decompress(path, true);
    }

    /**
     * 文件解压缩
     */
    private static void decompress(String path, boolean delete) throws Exception {
        File file = new File(path);
        decompress(file, delete);
    }

}
