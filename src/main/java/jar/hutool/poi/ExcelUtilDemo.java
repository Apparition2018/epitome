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
 * ExcelUtil
 * https://hutool.cn/docs/#/poi/Excel%E5%B7%A5%E5%85%B7-ExcelUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/poi/excel/ExcelUtil.html
 *
 * @author Arsenal
 * created on 2020/11/21 18:26
 */
public class ExcelUtilDemo extends Demo {

    private static final String EXCEL_PATH = HU_DEMO_ABSOLUTE_PATH + "demo.xlsx";

    /**
     * https://hutool.cn/docs/#/poi/Excel%E7%94%9F%E6%88%90-ExcelWriter
     * https://hutool.cn/docs/#/poi/Excel%E5%A4%A7%E6%95%B0%E6%8D%AE%E7%94%9F%E6%88%90-BigExcelWriter
     * https://apidoc.gitee.com/loolly/hutool/cn/hutool/poi/excel/ExcelWriter.html
     * https://apidoc.gitee.com/loolly/hutool/cn/hutool/poi/excel/BigExcelWriter.html
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
     * https://hutool.cn/docs/#/poi/Excel%E8%AF%BB%E5%8F%96-ExcelReader
     * https://hutool.cn/docs/#/poi/%E6%B5%81%E6%96%B9%E5%BC%8F%E8%AF%BB%E5%8F%96Excel2007-Excel07SaxReader
     * https://apidoc.gitee.com/loolly/hutool/cn/hutool/poi/excel/ExcelReader.html
     * https://apidoc.gitee.com/loolly/hutool/cn/hutool/poi/excel/sax/Excel07SaxReader.html
     */
    @Test
    public void readExcel() {
        ExcelReader reader = ExcelUtil.getReader(EXCEL_PATH);
        p(reader.readAll());
    }
}
