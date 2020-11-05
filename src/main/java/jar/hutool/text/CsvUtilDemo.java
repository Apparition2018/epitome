package jar.hutool.text;

import cn.hutool.core.annotation.Alias;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.text.csv.*;
import l.demo.Demo;
import lombok.Data;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * CsvUtil
 * CSV Comma-Separated Values，逗号分隔值，也称为字符分隔值，因为分隔字符也可以不是逗号，其文件以纯文本形式存储表格数据（数字和文本）。
 * <p>
 * https://hutool.cn/docs/#/core/%E6%96%87%E6%9C%AC%E6%93%8D%E4%BD%9C/CSV%E6%96%87%E4%BB%B6%E5%A4%84%E7%90%86%E5%B7%A5%E5%85%B7-CsvUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/text/csv/CsvUtil.html
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/text/csv/CsvWriter.html
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/text/csv/CsvBaseReader.html
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/text/csv/CsvRow.html
 *
 * @author ljh
 * created on 2020/11/5 9:08
 */
public class CsvUtilDemo extends Demo {

    private static final String CSV_FILE_ABSOLUTE_PATH = USER_DIR + File.separator + DEMO_PATH + "demo.csv";

    @Test
    public void testCsvUtil() throws InterruptedException {
        //********** 生成 CSV 文件 **********
        // static CsvWriter                 getWriter(file/filePath, Charset charset[, boolean isAppend])
        // static CsvWriter                 getWriter(Writer writer[, CsvWriteConfig config])
        // 获取 CSV 写出器
        CsvWriter csvWriter = CsvUtil.getWriter(CSV_FILE_ABSOLUTE_PATH, StandardCharsets.UTF_8);
        // CsvWriter	                    write(String[]... lines)
        csvWriter.write(
                // CsvWriter                write(String[].../Collection<?> lines)
                // 将多行写出到 Writer
                new String[]{"姓名", "gender", "focus", "age"},
                new String[]{"张三", "男", "无", "33"},
                new String[]{"李四", "男", "好对象", "23"},
                new String[]{"王妹妹", "女", "特别关注", "22"}
        );

        //********** 读取 CSV 文件 **********
        // 1.第二种读取方法，读取为 CsvData
        // static CsvReader                 getReader([CsvReadConfig config])
        // 获取 CSV 读取器
        CsvReader csvReader = CsvUtil.getReader();
        // CsvData	                        read(Path/File[, Charset charset])
        // 读取 CSV 文件
        CsvData csvData = csvReader.read(new File(CSV_FILE_ABSOLUTE_PATH).toPath(), StandardCharsets.UTF_8);
        for (CsvRow csvRow : csvData) {
            // List<String>	    getRawList()
            // 获取本行所有字段值列表
            p(csvRow.getRawList());
        }
        // 2.第一种读取方法，读取为 Bean
        // <T> List<T>	                    read(Reader reader, Class<T> clazz)
        // 从 Reader 中读取 CSV 数据并转换为 Bean 列表，读取后关闭 Reader。此方法默认识别首行为标题行。
        List<CsvBean> csvBeanList = csvReader.read(ResourceUtil.getUtf8Reader("demo/demo.csv"), CsvBean.class);
        for (CsvBean csvBean : csvBeanList) {
            p(csvBean);
        }
    }

    @Data
    private static class CsvBean {
        // // 如果 CSV 中标题与字段不对应，可以使用 @Alias 设置别名
        @Alias("姓名")
        private String name;
        private String gender;
        private String focus;
        private Integer age;
    }
}
