package jar.apache.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
    private static final String[][] DATA = {
            {"1", "张三", "北京清华大学", "1.785"},
            {"2", "李四", "上海复旦大学", "1.684"},
            {"3", "王五", "广东中山大学", "1.728"}
    };
    private static Integer rowNum = 0;

    public static void main(String[] args) {
        // 创建Excel
        try (Workbook workbook = new XSSFWorkbook()) {
            // 格式化单元格数据
            DataFormat dataFormat = workbook.createDataFormat();
            // 表头单元格样式
            CellStyle headerStyle = headerStyle(workbook);
            // 表体单元格样式
            CellStyle bodyStyle = bodyStyle(workbook, dataFormat);

            // 创建Excel中创建一张工作表，并设置名字
            Sheet sheet = workbook.createSheet("PoiDemo");
            // 设置默认列宽（字符数 * 字节数 * 256 * 字号 / 10）
            sheet.setDefaultColumnWidth(3 * 2 * 256 * 12 / 10);
            // 设置列宽
            sheet.setColumnWidth(2, 7 * 2 * 256 * 12 / 10);

            // 设置标题
            setTitle(sheet, rowNum, headerStyle);
            // 设置表头数据
            setHeader(sheet, ++rowNum, headerStyle);
            // 设置表体数据
            setData(sheet, rowNum, bodyStyle);

            // 冻结窗口
            sheet.createFreezePane(1, 2);

            FileOutputStream fos = new FileOutputStream(DESKTOP + "poi.xlsx");
            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 表头单元格样式
     *
     * @param workbook {@link Workbook}
     * @return {@link CellStyle}
     */
    private static CellStyle headerStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        // 设置字体样式
        style.setFont(headerFont(workbook));
        // 前景色（注：当设置背景色为黄色时，使用 setFillForegroundColor()，而不是 setFillBackgroundColor()
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        // 背景色
        style.setFillBackgroundColor(IndexedColors.AUTOMATIC.getIndex());
        // 图案样式
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        setBorder(style, BorderStyle.THIN);
        return style;
    }

    /**
     * 表体单元格样式
     *
     * @param workbook   {@link Workbook}
     * @param dataFormat {@link DataFormat}
     * @return {@link CellStyle}
     */
    private static CellStyle bodyStyle(Workbook workbook, DataFormat dataFormat) {
        CellStyle style = workbook.createCellStyle();
        // 设置字体样式
        style.setFont(bodyFont(workbook));
        // 自动换行
        style.setWrapText(true);
        // style.setDataFormat(DataFormat.getBuiltinFormat("0.00"));
        style.setDataFormat(dataFormat.getFormat("0.00"));
        setBorder(style, BorderStyle.THIN);
        return style;
    }

    /**
     * 设置标题
     *
     * @param sheet  {@link Sheet}
     * @param rowNum rowNum
     * @param style  标题格式
     */
    private static void setTitle(Sheet sheet, int rowNum, CellStyle style) {
        Row row = sheet.createRow(rowNum);
        Cell cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("Demo");
        // 合并单元格
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, HEADERS.length - 1));
    }

    /**
     * 设置表头
     *
     * @param sheet  {@link Sheet}
     * @param rowNum rowNum
     * @param style  表头格式
     */
    private static void setHeader(Sheet sheet, int rowNum, CellStyle style) {
        Row row = sheet.createRow(rowNum);
        Cell cell;
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
     * @param sheet  {@link Sheet}
     * @param rowNum rowNum
     * @param style  表体格式
     */
    private static void setData(Sheet sheet, int rowNum, CellStyle style) {
        Row row;
        Cell cell;
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
     * @param workbook {@link Workbook}
     * @return {@link Font}
     */
    private static Font headerFont(Workbook workbook) {
        Font font = workbook.createFont();
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
     * @param workbook {@link Workbook}
     * @return {@link Font}
     */
    private static Font bodyFont(Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);
        return font;
    }

    /**
     * 设置 Border
     *
     * @param style       {@link CellStyle}
     * @param borderStyle {@link BorderStyle}
     */
    private static void setBorder(CellStyle style, BorderStyle borderStyle) {
        style.setBorderTop(borderStyle);
        style.setBorderBottom(borderStyle);
        style.setBorderLeft(borderStyle);
        style.setBorderRight(borderStyle);
    }
}
