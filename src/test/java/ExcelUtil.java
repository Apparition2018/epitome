/**
 * @Author: Administrator
 * @Create: 2020/11/27/16:14
 */

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author qiuzk
 * @ClassName: ExcelUtil
 * @Description: excel文档读取类
 * @date 2020-11-27 16:14
 **/
public class ExcelUtil<T> {

    /**
     * Excel sheet最大行数，默认65536
     */
    public static final int sheetSize = 65536;

    /**
     * 工作表名称
     */
    private String sheetName;

    /**
     * 工作薄对象
     */
    private Workbook wb;

    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 实体对象
     */
    public Class<T> clazz;

    public ExcelUtil(Class<T> clazz) {
        this.clazz = clazz;
    }


    public List<T> importExcel(InputStream is) throws IOException, InvalidFormatException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        this.wb = WorkbookFactory.create(is);
        List<T> list = new ArrayList<T>();

        Sheet sheet = wb.getSheetAt(0);

        if (sheet == null) {
            throw new IOException("文件Sheet不存在");
        }

        // excel表格的总行数
        int rows = sheet.getPhysicalNumberOfRows();
        if (rows > 0) {

            // 获取表头
            Row heard = sheet.getRow(0);
            // 总列数 取自表头的列数
            int columns = heard.getPhysicalNumberOfCells();

            Field[] allFields = clazz.getDeclaredFields();

            // 定义一个用来装实体类属性的属性键值对的List
            List<Map<Integer, Object>> keyValueList = new ArrayList<>();
            for (int i = 1; i < rows; i++) {
                // 定义一个map用于存放excel列的序号和field.
                Map<Integer, Object> cellMap = new HashMap<>();
                Row row = sheet.getRow(i);
                String code = "";
                String orderTime = "";
                String orderNumber = "";
                for (int j = 0; j < columns; j++) {
                    // 每次自增 已迭代数自增
                    // 添加行数据键值对 进 MAP
                    String cellValue = this.getCellValue(row, j).toString();
                    if (!"".equals(cellValue)) {
                        if (j == 2) {
                            
                        }
                        cellMap.put(j, cellValue);
                    }
                }
                keyValueList.add(cellMap);
            }

            System.out.println(keyValueList);

            // 迭代实体类键值对的List
            for (Map<Integer, Object> cellMap : keyValueList) {
                // 生成要调用的set方法
                Set<Integer> indexs = cellMap.keySet();
                //ExcelModel excelModel=new ExcelModel();

                T t = clazz.newInstance();
                System.out.println("t:" + t);
                for (Integer index : indexs) {
                    String fieldName = allFields[index].getName();
                    String setterMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                    // Method method = clazz.getClass().getMethod("print", new Class[]{});
                    Method method = clazz.getDeclaredMethod(setterMethodName, Integer.class);
                    Object o = cellMap.get(index);
                    method.invoke(t, o);
                }
                list.add(t);
            }
        }

        return list;
    }

    /**
     * 获取单元格值
     *
     * @param row    获取的行
     * @param column 获取单元格列号
     * @return 单元格值
     */
    public Object getCellValue(Row row, int column) {
        if (row == null) {
            return row;
        }
        Object val = "";
        try {
            Cell cell = row.getCell(column);
            if (cell != null) {
                if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {
                    val = cell.getNumericCellValue();
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        val = DateUtil.getJavaDate((Double) val);
                        val = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(val);
                    } else {
                        if ((Double) val % 1 > 0) {
                            val = new BigDecimal(val.toString());
                        } else {
                            val = new DecimalFormat("0").format(val);
                        }
                    }
                } else if (cell.getCellTypeEnum() == CellType.STRING) {
                    val = cell.getStringCellValue();
                } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
                    val = cell.getBooleanCellValue();
                } else if (cell.getCellTypeEnum() == CellType.ERROR) {
                    val = cell.getErrorCellValue();
                }
            }


        } catch (Exception e) {
            return val;
        }
        return val;
    }


}
