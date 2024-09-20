package jar.apache.poi;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

import static l.demo.Demo.p;

/**
 * ReadPOI
 *
 * @author ljh
 * @see <a href="http://www.cnblogs.com/hongten/p/java_poi_excel_xls_xlsx.html">POI 读取 Excel</a>
 * @see <a href="https://www.cnblogs.com/stono/p/6713158.html">POI 日期格式</a>
 * @see <a href="https://blog.csdn.net/weixin_38084097/article/details/77242508">POI 日期格式</a>
 * @since 2019/8/8 19:39
 */
public class ReadPOI {

    @Test
    public void testReadPOI() throws IOException {
        URL excelUrl = ReadPOI.class.getResource("/demo/person.xlsx");
        try (Workbook workbook = WorkbookFactory.create(new File(Objects.requireNonNull(excelUrl).getFile()))) {
            Sheet sheet = workbook.getSheetAt(0);
            // getLastRowNum()      返回最后一行的索引，即比行数小1
            p(sheet.getLastRowNum());
            // getLastCellNum()     返回最后一列的列数，即等于列数
            p(sheet.getRow(0).getLastCellNum());
        }
    }

    /**
     * @see ExcelUtils#excel2BeanList(InputStream, Map)
     */
    @Test
    public void testExcel2BeanList() {
    }
}
