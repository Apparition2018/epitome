package jar.hutool.io;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;
import l.demo.Demo;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * FileUtil
 * FileUtil 方法名与 Linux 相一致
 * https://hutool.cn/docs/#/core/IO/%E6%96%87%E4%BB%B6%E5%B7%A5%E5%85%B7%E7%B1%BB-FileUtil
 *
 * @author ljh
 * created on 2020/10/29 17:46
 */
public class FileUtilDemo extends Demo {

    @Test
    public void testFileUtil() {
        // 创建文件夹
        File dir = FileUtil.mkdir(new File(DEMO_PATH));
        
        // 列出目录和文件
        FileUtil.ls(dir.getAbsolutePath());

        // 创建文件
        File file = FileUtil.touch(dir, "demo");

        // 写入文件
        List<String> list = ListUtil.toList("静夜思", "窗前明月光，", "疑是地上霜，", "举头望明月，", "低头思故乡。");
        FileUtil.writeUtf8Lines(list, file);
        
        // 读取文件
        p(FileUtil.readLines(file, StandardCharsets.UTF_8));
        
        // 删除文件
        FileUtil.del(dir.getAbsolutePath() + "demo2");
        
        // 复制文件
        String destPath = dir.getAbsolutePath() + "a/demo";

    }
}
