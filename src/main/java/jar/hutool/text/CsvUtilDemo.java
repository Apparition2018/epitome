package jar.hutool.text;

import cn.hutool.core.annotation.Alias;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.text.csv.*;
import l.demo.Demo;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <a href="https://hutool.cn/docs/#/core/文本操作/CSV文件处理工具-CsvUtil">CsvUtil</a>
 * <p>CSV Comma-Separated Values，逗号分隔值，也称为字符分隔值，因为分隔字符也可以不是逗号，其文件以纯文本形式存储表格数据（数字和文本）。
 * api：
 * <pre>
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/text/csv/CsvUtil.html">CsvUtil api</a>
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/text/csv/CsvWriter.html">CsvWriter api</a>
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/text/csv/CsvBaseReader.html">CsvBaseReader api</a>
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/text/csv/CsvRow.html">CsvRow api</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/11/5 9:08
 */
public class CsvUtilDemo extends Demo {

    private static final String CSV_FILE_ABSOLUTE_PATH = DEMO_DIR_ABSOLUTE_PATH + "demo.csv";

    @Test
    public void writeCSV() {
        // static CsvWriter                 getWriter(file/filePath, Charset charset[, boolean isAppend])
        // static CsvWriter                 getWriter(Writer writer[, CsvWriteConfig config])
        // 获取 CSV 写出器
        CsvWriter csvWriter = CsvUtil.getWriter(CSV_FILE_ABSOLUTE_PATH, StandardCharsets.UTF_8);
        // CsvWriter	                    write(String[]... lines)
        csvWriter.write(
                // CsvWriter                write(String[].../Collection<?> lines)
                // 将多行写出到 Writer
                new String[]{"Name", "gender", "focus", "age"},
                new String[]{"张三", "男", "无", "33"},
                new String[]{"李四", "男", "好对象", "23"},
                new String[]{"王妹妹", "女", "特别关注", "22"}
        );
    }

    @Test
    public void readCSV() {
        CsvReader csvReader = CsvUtil.getReader();
        {
            // 1.第二种读取方法，读取为 CsvData
            // static CsvReader                 getReader([CsvReadConfig config])
            // 获取 CSV 读取器
            // CsvData	                        read(Path/File[, Charset charset])
            // 读取 CSV 文件
            CsvData csvData = csvReader.read(new File(CSV_FILE_ABSOLUTE_PATH).toPath(), StandardCharsets.UTF_8);
            for (CsvRow csvRow : csvData) {
                // List<String>	    getRawList()
                // 获取本行所有字段值列表
                p(csvRow.getRawList());
            }
        }
        {
            // 2.第一种读取方法，读取为 Bean
            // <T> List<T>	                    read(Reader reader, Class<T> clazz)
            // 从 Reader 中读取 CSV 数据并转换为 Bean 列表，读取后关闭 Reader。此方法默认识别首行为标题行。
            List<CsvBean> csvBeanList = csvReader.read(ResourceUtil.getUtf8Reader("demo/demo.csv"), CsvBean.class);
            for (CsvBean csvBean : csvBeanList) {
                p(csvBean);
            }
        }
    }

    @Data
    private static class CsvBean {
        // 如果 CSV 中标题与字段不对应，可以使用 @Alias 设置别名
        @Alias("Name")
        private String name;
        private String gender;
        private String focus;
        private Integer age;
    }
}
