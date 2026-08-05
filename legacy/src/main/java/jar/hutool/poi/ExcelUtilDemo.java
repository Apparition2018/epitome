package jar.hutool.poi;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import l.demo.Demo;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://doc.hutool.cn/pages/ExcelUtil/">ExcelUtil</a>
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/poi/excel/ExcelUtil.html">ExcelUtil api</a>
 *
 * @author ljh
 * @since 2020/11/21 18:26
 */
public class ExcelUtilDemo extends Demo {

    private static final String EXCEL_PATH = HU_DEMO_DIR_ABSOLUTE_PATH + "demo.xlsx";

    /**
     * <pre>
     * <a href="https://doc.hutool.cn/pages/ExcelWriter/">ExcelWriter</a>
     * <a href="https://plus.hutool.cn/apidocs/cn/hutool/poi/excel/ExcelWriter.html">ExcelWriter api</a>
     * <a href="https://doc.hutool.cn/pages/BigExcelWriter/">BigExcelWriter</a>
     * <a href="https://plus.hutool.cn/apidocs/cn/hutool/poi/excel/BigExcelWriter.html">BigExcelWriter api</a>
     * </pre>
     */
    @Test
    public void writeExcel() {
        ExcelWriter writer = new ExcelWriter(EXCEL_PATH);
        // 自定义 Style
        StyleSet styleSet = writer.getStyleSet();
        styleSet.setBackgroundColor(IndexedColors.PALE_BLUE, false);
        // 自定义 Font
        Font font = writer.createFont();
        font.setColor(IndexedColors.WHITE.index);
        writer.getStyleSet().setFont(font, true);
        // 字段别名
        // Bean 字段不能保证顺序，使用 addHeaderAlias() 设置别名，会按照别名的加入顺序排序
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("gender", "性别");
        writer.addHeaderAlias("otherInfo", "其它信息");
        writer.addHeaderAlias("home", "家庭信息");
        write(writer);
        // 新建或者切换 sheet
        writer.setSheet("sheet2");
        write(writer);
        // 如果设定目标文件，将数据输出到文件，关闭工作簿
        writer.close();
    }

    private void write(ExcelWriter writer) {
        // 跳过当前行
        // writer.passCurrentRow();
        // 合并单元格，使用默认样式
        // 这里减两个 1，是因为有 serialVersionUID 字段
        // writer.merge(Person.class.getDeclaredFields().length - 1 - 1, "Person");
        // 将数据写入 Sheet，强制输出标题
        writer.write(personList, true);
        // 将数据输出到文件
        writer.flush();
    }

    /**
     * <pre>
     * <a href="https://doc.hutool.cn/pages/ExcelReader/">ExcelReader</a>
     * <a href="https://plus.hutool.cn/apidocs/cn/hutool/poi/excel/ExcelReader.html">ExcelReader api</a>
     * <a href="https://doc.hutool.cn/pages/Excel07SaxReader/">Excel07SaxReader</a>
     * <a href="https://plus.hutool.cn/apidocs/cn/hutool/poi/excel/sax/Excel07SaxReader.html">Excel07SaxReader api</a>
     * </pre>
     */
    @Test
    public void readExcel() {
        ExcelReader reader = ExcelUtil.getReader(EXCEL_PATH);
        p(reader.readAll());
    }
}
