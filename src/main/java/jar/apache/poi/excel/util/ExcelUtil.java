package jar.apache.poi.excel.util;

import jar.apache.poi.excel.common.Common;
import l.demo.Person.Student;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hongten
 * @since 2014-5-20
 * <p>
 * https://www.cnblogs.com/hongten/p/java_poi_excel_xls_xlsx.html#undefined
 * https://www.cnblogs.com/stono/p/6713158.html
 */
public class ExcelUtil {

    public void writeExcel(List<Student> list, String path) throws Exception {
        if (null != list && null != path && !Common.EMPTY.equals(path)) {
            String suffix = Util.getSuffix(path);
            if (!Common.EMPTY.equals(suffix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(suffix)) {
                    writeXls(list, path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(suffix)) {
                    writeXlsx(list, path);
                }
            } else {
                System.out.println(path + Common.NOT_EXCEL_FILE);
            }
        }
    }

    public List<Student> readExcel(String path) throws IOException {
        if (null == path || Common.EMPTY.equals(path)) {
            return null;
        } else {
            String suffix = Util.getSuffix(path);
            if (!Common.EMPTY.equals(suffix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(suffix)) {
                    return readXls(path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(suffix)) {
                    return readXlsx(path);
                }
            } else {
                System.out.println(path + Common.NOT_EXCEL_FILE);
            }
        }
        return null;
    }

    public List<Student> readXlsx(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        Student student;
        List<Student> list = new ArrayList<>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (null == xssfSheet) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow cell = xssfSheet.getRow(rowNum);
                if (null != cell) {
                    student = new Student();
                    XSSFCell no = cell.getCell(0);
                    XSSFCell name = cell.getCell(1);
                    XSSFCell age = cell.getCell(2);
                    XSSFCell score = cell.getCell(3);
                    student.setNo(getValue(no));
                    student.setName(getValue(name));
                    student.setAge(Integer.parseInt(getValue(age)));
                    student.setScore(Float.valueOf(getValue(score)));
                    list.add(student);
                }
            }
        }
        return list;
    }

    public List<Student> readXls(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Student student;
        List<Student> list = new ArrayList<>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (null == hssfSheet) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (null != hssfRow) {
                    student = new Student();
                    HSSFCell no = hssfRow.getCell(0);
                    HSSFCell name = hssfRow.getCell(1);
                    HSSFCell age = hssfRow.getCell(2);
                    HSSFCell score = hssfRow.getCell(3);
                    student.setNo(getValue(no));
                    student.setName(getValue(name));
                    student.setAge(Integer.parseInt(getValue(age)));
                    student.setScore(Float.valueOf(getValue(score)));
                    list.add(student);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("static-access")
    private String getValue(Cell cell) {
        if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

    public void writeXls(List<Student> list, String path) throws Exception {
        if (null == list) {
            return;
        }
        int countColumnNum = list.size();
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("studentSheet");
        // option at first row.
        HSSFRow firstRow = sheet.createRow(0);
        HSSFCell[] firstCells = new HSSFCell[countColumnNum];
        String[] options = {"no", "name", "age", "score"};
        for (int j = 0; j < options.length; j++) {
            firstCells[j] = firstRow.createCell(j);
            firstCells[j].setCellValue(new HSSFRichTextString(options[j]));
        }
        //
        for (int i = 0; i < countColumnNum; i++) {
            HSSFRow row = sheet.createRow(i + 1);
            Student student = list.get(i);
            for (String ignored : options) {
                HSSFCell no = row.createCell(0);
                HSSFCell name = row.createCell(1);
                HSSFCell age = row.createCell(2);
                HSSFCell score = row.createCell(3);
                no.setCellValue(student.getNo());
                name.setCellValue(student.getName());
                age.setCellValue(student.getAge());
                score.setCellValue(student.getScore());
            }
        }
        File file = new File(path);
        OutputStream os = new FileOutputStream(file);
        System.out.println(Common.WRITE_DATA + path);
        book.write(os);
        os.close();
    }

    public void writeXlsx(List<Student> list, String path) throws Exception {
        if (null == list) {
            return;
        }
        //XSSFWorkbook
        int countColumnNum = list.size();
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("studentSheet");
        // option at first row.
        XSSFRow firstRow = sheet.createRow(0);
        XSSFCell[] firstCells = new XSSFCell[countColumnNum];
        String[] options = {"no", "name", "age", "score"};
        for (int j = 0; j < options.length; j++) {
            firstCells[j] = firstRow.createCell(j);
            firstCells[j].setCellValue(new XSSFRichTextString(options[j]));
        }
        //
        for (int i = 0; i < countColumnNum; i++) {
            XSSFRow row = sheet.createRow(i + 1);
            Student student = list.get(i);
            for (String ignored : options) {
                XSSFCell no = row.createCell(0);
                XSSFCell name = row.createCell(1);
                XSSFCell age = row.createCell(2);
                XSSFCell score = row.createCell(3);
                no.setCellValue(student.getNo());
                name.setCellValue(student.getName());
                age.setCellValue(student.getAge());
                score.setCellValue(student.getScore());
            }
        }
        File file = new File(path);
        OutputStream os = new FileOutputStream(file);
        System.out.println(Common.WRITE_DATA + path);
        book.write(os);
        os.close();
    }
}
