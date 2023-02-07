package jar.apache.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;

import static l.demo.Demo.DESKTOP;

/**
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class WritePOI {

    /**
     * 表头列名
     */
    private static final String[] HEADERS = {"序号", "姓名", "学校", "身高"};
    /**
     * 表体数据
     */
    private static final String[][] DATA = {{"1", "张三", "北京清华大学", "1.785"},
            {"2", "李四", "上海复旦大学", "1.684"},
            {"3", "王五", "广东中山大学", "1.728"}};
    private Integer rowNum = 0;

    @Test
    public void writePoi() {
        // 创建Excel
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            // 格式化单元格数据
            HSSFDataFormat dataFormat = workbook.createDataFormat();
            // 表头单元格样式
            HSSFCellStyle headerStyle = this.headerStyle(workbook);
            // 表体单元格样式
            HSSFCellStyle bodyStyle = this.bodyStyle(workbook, dataFormat);

            // 创建Excel中创建一张工作表，并设置名字
            HSSFSheet sheet = workbook.createSheet("PoiDemo");
            // 设置默认列宽（字符数 * 字节数 * 256 * 字号 / 10）
            sheet.setDefaultColumnWidth(3 * 2 * 256 * 12 / 10);
            // 设置列宽
            sheet.setColumnWidth(2, 7 * 2 * 256 * 12 / 10);

            // 设置标题
            this.setTitle(sheet, headerStyle, rowNum);
            // 设置表头数据
            this.setHeader(sheet, headerStyle, ++rowNum);
            // 设置表体数据
            this.setData(sheet, rowNum, bodyStyle);

            // 冻结窗口
            sheet.createFreezePane(1, 2);

            FileOutputStream fos = new FileOutputStream(DESKTOP + "PoiDemo.xls");
            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 表头单元格样式
     *
     * @param workbook {@link HSSFWorkbook}
     * @return {@link HSSFCellStyle}
     */
    private HSSFCellStyle headerStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置字体样式
        style.setFont(this.headerFont(workbook));
        // 前景色（注：当设置背景色为黄色时，使用 setFillForegroundColor()，而不是 setFillBackgroundColor()
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        // 背景色
        style.setFillBackgroundColor(IndexedColors.AUTOMATIC.getIndex());
        // 图案样式
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        this.setBorder(style, BorderStyle.THIN);
        return style;
    }

    /**
     * 表体单元格样式
     *
     * @param workbook {@link HSSFWorkbook}
     * @return {@link HSSFCellStyle}
     */
    private HSSFCellStyle bodyStyle(HSSFWorkbook workbook, HSSFDataFormat dataFormat) {
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置字体样式
        style.setFont(this.bodyFont(workbook));
        // 自动换行
        style.setWrapText(true);
        style.setDataFormat(dataFormat.getFormat("0.00"));
        this.setBorder(style, BorderStyle.THIN);
        return style;
    }

    /**
     * 设置标题
     *
     * @param sheet  {@link HSSFSheet}
     * @param style  标题格式
     * @param rowNum rowNum
     */
    private void setTitle(HSSFSheet sheet, HSSFCellStyle style, int rowNum) {
        HSSFRow row = sheet.createRow(rowNum);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("Demo");
        // 合并单元格
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, HEADERS.length - 1));
    }

    /**
     * 设置表头
     *
     * @param sheet  {@link HSSFSheet}
     * @param style  表头格式
     * @param rowNum rowNum
     */
    private void setHeader(HSSFSheet sheet, HSSFCellStyle style, int rowNum) {
        HSSFRow row = sheet.createRow(rowNum);
        HSSFCell cell;
        for (int i = 0; i < HEADERS.length; i++) {
            // 创建单元格
            cell = row.createCell(i);
            // 设置单元格样式
            cell.setCellStyle(style);
            // 设置单元格内容
            cell.setCellValue(HEADERS[i]);
        }
    }

    /**
     * 设置表体
     *
     * @param sheet  {@link HSSFSheet}
     * @param rowNum rowNum
     * @param style  表体格式
     */
    private void setData(HSSFSheet sheet, int rowNum, HSSFCellStyle style) {
        HSSFRow row;
        HSSFCell cell;
        for (String[] aData : DATA) {
            row = sheet.createRow(++rowNum);
            for (int j = 0; j < aData.length; j++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                cell.setCellValue(aData[j]);
            }
        }
    }

    /**
     * 表头字体
     *
     * @param workbook {@link HSSFWorkbook}
     * @return {@link HSSFFont}
     */
    private HSSFFont headerFont(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");                      // 字体
        font.setFontHeightInPoints((short) 14);          // 字号
        font.setColor(IndexedColors.BLACK.getIndex());   // 字体颜色
        font.setBold(true);                              // 加粗
        font.setItalic(false);                           // 斜体
        font.setStrikeout(false);                        // 删除线
        // font.setUnderline((byte) 1);                     // 下划线
        return font;
    }

    /**
     * 表体字体
     *
     * @param workbook {@link HSSFWorkbook}
     * @return {@link HSSFFont}
     */
    private HSSFFont bodyFont(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);
        return font;
    }

    /**
     * 设置 Border
     *
     * @param style       {@link HSSFCellStyle}
     * @param borderStyle {@link BorderStyle}
     */
    private void setBorder(HSSFCellStyle style, BorderStyle borderStyle) {
        style.setBorderTop(borderStyle);
        style.setBorderBottom(borderStyle);
        style.setBorderLeft(borderStyle);
        style.setBorderRight(borderStyle);
    }
}
