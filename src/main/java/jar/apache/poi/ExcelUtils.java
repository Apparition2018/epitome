package jar.apache.poi;

import cn.hutool.core.date.DatePattern;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ExcelUtils
 *
 * @author ljh
 * @since 2021/1/8 0:39
 */
public class ExcelUtils<T> {

    public Class<T> clazz;

    public ExcelUtils(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> excel2BeanList(InputStream inputStream, Map<Integer, String> colNumAndFieldNameMap) throws IOException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ParseException {
        List<T> list = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(inputStream);
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (null == row || row.getCell(0).getCellType() == CellType.BLANK) {
                    continue;
                }

                T t = clazz.newInstance();
                for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
                    String fieldName = colNumAndFieldNameMap.get(cellNum);
                    if (null != fieldName) {
                        Class<?> fieldType = clazz.getDeclaredField(fieldName).getType();
                        String setterMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Method method = clazz.getDeclaredMethod(setterMethodName, fieldType);
                        switch (fieldType.getSimpleName()) {
                            case "Integer":
                                method.invoke(t, Integer.parseInt(getValue(row.getCell(cellNum))));
                                break;
                            case "Date":
                                method.invoke(t, DateUtils.parseDate(getValue(row.getCell(cellNum)), DatePattern.NORM_DATETIME_PATTERN));
                                break;
                            default:
                                method.invoke(t, getValue(row.getCell(cellNum)));
                        }
                    }
                }
                list.add(t);
            }
        }
        return list;
    }

    private static String getValue(Cell cell) {
        if (cell != null) {
            if (cell.getCellType() == CellType.BOOLEAN) {
                return String.valueOf(cell.getBooleanCellValue());
            } else if (cell.getCellType() == CellType.NUMERIC) {
//                short format = cell.getCellStyle().getDataFormat();
                if (DateUtil.isCellDateFormatted(cell)) {
                    double cellValue = cell.getNumericCellValue();
                    Date date = DateUtil.getJavaDate(cellValue);
                    return DateFormatUtils.format(date, DatePattern.NORM_DATETIME_PATTERN);
                }
                return String.valueOf((int) cell.getNumericCellValue());
            } else {
                return String.valueOf(cell.getStringCellValue());
            }
        } else {
            return "";
        }
    }
}
