package jar.apache.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class WritePOI {

    public static void main(String[] args) {

        // 创建Excel
        try (HSSFWorkbook wb = new HSSFWorkbook()) {

            // 创建Excel中创建一张工作表，并设置名字
            HSSFSheet sheet = wb.createSheet("PoiDemo");

            // 设置默认列宽
            sheet.setDefaultColumnWidth(8); // 注意：6个字符长度
            // 设置列宽
            sheet.setColumnWidth(0, 3 * 2 * 256 * 12 / 10);
            sheet.setColumnWidth(2, 7 * 2 * 256 * 12 / 10); // 6个字符 * 2字节 * 256 * 12字号 / 10

            // 用于格式化单元格的数据
            HSSFDataFormat format = wb.createDataFormat();

            // 设置表头字体
            HSSFFont headingFont = wb.createFont();
            headingFont.setFontName("微软雅黑");                    // 字体
            headingFont.setFontHeightInPoints((short) 14);          // 字号
            headingFont.setColor(IndexedColors.BLACK.getIndex());   // 字体颜色
            headingFont.setBold(true);                              // 加粗
//            headingFont.setItalic(true); 						    // 斜体
//            headingFont.setUnderline(FontFormatting.U_SINGLE); 	    // 下划线
//            headingFont.setStrikeout(true); 					    // 删除线

            // 设置数据字体
            HSSFFont dataFont = wb.createFont();
            dataFont.setFontName("宋体");
            dataFont.setFontHeightInPoints((short) 12);

            // 设置表头单元格样式
            HSSFCellStyle headingStyle = wb.createCellStyle();
            headingStyle.setFont(headingFont);                                              // 设置字体样式
            headingStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());  // 前景色
            headingStyle.setFillBackgroundColor(IndexedColors.PALE_BLUE.getIndex());        // 背景色
            headingStyle.setFillPattern(FillPatternType.SPARSE_DOTS);                       // 图案样式
            headingStyle.setAlignment(HorizontalAlignment.CENTER);                          // 水平居中
            headingStyle.setBorderTop(BorderStyle.THIN);                                    // 上边框
            headingStyle.setBorderBottom(BorderStyle.THIN);                                 // 下边框
            headingStyle.setBorderLeft(BorderStyle.THIN);                                   // 左边框
            headingStyle.setBorderRight(BorderStyle.THIN);                                  // 右边框

            // 设置数据单元格样式
            HSSFCellStyle dataStyle = wb.createCellStyle();
            dataStyle.setFont(dataFont); // 设置字体样式
            dataStyle.setWrapText(true); // 自动换行
            headingStyle.setBorderTop(BorderStyle.THIN);
            headingStyle.setBorderBottom(BorderStyle.THIN);
            headingStyle.setBorderLeft(BorderStyle.THIN);
            headingStyle.setBorderRight(BorderStyle.THIN);
            headingStyle.setDataFormat(format.getFormat("0.00"));

            // 创建行
            int rowCount = 0; // 行数
            HSSFRow row = sheet.createRow(rowCount);

            // 创建单元格
            HSSFCell cell;

            // 设置列名
            String[] heading = {"序号", "姓名", "学校", "身高"};

            // 写入表头数据
            for (int i = 0; i < heading.length; i++) {
                cell = row.createCell(i);       // 创建单元格
                cell.setCellStyle(headingStyle);// 设置单元格样式
                cell.setCellValue(heading[i]);  // 单元格内容
            }

            // 写入表体数据
            String[][] data = {{"1", "张三", "北京清华大学", "1.785"},
                    {"2", "李四", "上海复旦大学", "1.684"},
                    {"3", "王五", "广东中山大学", "1.728"}};
            for (String[] aData : data) {
                row = sheet.createRow(++rowCount);
                for (int j = 0; j < aData.length; j++) {
                    cell = row.createCell(j);
                    cell.setCellStyle(dataStyle);
                    cell.setCellValue(aData[j]);
                }
            }

            FileOutputStream fos = new FileOutputStream("T:/PoiDemo.xls");
            wb.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
