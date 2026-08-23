package org.ljh.dependencies.group.hutool.io;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.ljh.commons.Demo.DEMO_DIR_PATH;

/**
 * <a href="https://doc.hutool.cn/pages/IoUtil/">IOUtil</a>
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/io/IoUtil.html">IOUtil api</a>
 *
 * @author ljh
 * @since 2020/10/29 17:45
 */
public class IoUtilTests {

    @Test
    public void testIoUtil() {
        File file = new File(DEMO_DIR_PATH + "Input");
        File file2 = FileUtil.touch(new File(DEMO_DIR_PATH), "a/Input");

        // 写入
        BufferedOutputStream bos = FileUtil.getOutputStream(file2);
        IoUtil.writeUtf8(bos, false, "Input Input Input Input Input ");

        // 读取
        FileInputStream fis = IoUtil.toStream(file2);
        System.out.println(IoUtil.readUtf8(fis));

        // 复制
        BufferedInputStream bis = FileUtil.getInputStream(file);
        IoUtil.copy(bis, bos, IoUtil.DEFAULT_BUFFER_SIZE);
        BufferedReader br = FileUtil.getUtf8Reader(file);
        PrintWriter pw = FileUtil.getPrintWriter(file2, StandardCharsets.UTF_8, false);
        IoUtil.copy(br, pw, IoUtil.DEFAULT_BUFFER_SIZE);

        // 关闭
        IoUtil.closeIfPossible(fis);
        IoUtil.closeIfPossible(bis);
        IoUtil.closeIfPossible(bos);
        IoUtil.closeIfPossible(br);
        IoUtil.closeIfPossible(pw);

        file2.deleteOnExit();
    }

    /** 转换 */
    @Test
    public void convert() throws IOException {
        File file = new File(DEMO_DIR_PATH + "Input");
        InputStream is = Files.newInputStream(Paths.get(DEMO_DIR_PATH + "Input"));
        byte[] bytes = FileUtil.readBytes(DEMO_DIR_PATH + "Input");
        OutputStream os = Files.newOutputStream(Paths.get(DEMO_DIR_PATH + "Output"));

        // InputStream  → BufferedInputStream
        BufferedInputStream bis = IoUtil.toBuffered(is);

        // InputStream  → 支持 mark 标记的 InputStream
        InputStream markSupportStream = IoUtil.toMarkSupportStream(is);

        // InputStream  → PushbackInputStream
        PushbackInputStream pushbackInputStream = IoUtil.toPushbackStream(is, 1024);

        // File         → FileInputStream
        FileInputStream fileInputStream = IoUtil.toStream(file);

        // String       → ByteArrayInputStream
        ByteArrayInputStream bais = IoUtil.toUtf8Stream(IoUtil.readUtf8(is));

        // byte[]       → ByteArrayInputStream
        ByteArrayInputStream bais2 = IoUtil.toStream(bytes);

        // OutputStream → BufferedOutputStream
        BufferedOutputStream bos = IoUtil.toBuffered(os);
    }
}
