package jar.apache.commons.csv;

import l.demo.Demo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <a href="https://commons.apache.org/proper/commons-csv/">Commons CSV</a>
 *
 * @author ljh
 * @since 2020/11/15 3:29
 */
public class CSVDemo extends Demo {

    @Test
    public void writeCSV() {
        try (PrintWriter pw = new PrintWriter(DEMO_DIR_PATH + "demo.csv")) {
            try (CSVPrinter csvPrinter = CSVFormat.Builder.create().setHeader("Name", "gender", "focus", "age").build().print(pw)) {
                csvPrinter.printRecord("张三", "男", "无", 33);
                csvPrinter.printRecord("李四", "男", "好对象", 23);
                csvPrinter.printRecord("王妹妹", "女", "特别关注", 33);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readCSV() {
        try (FileReader fr = new FileReader(DEMO_DIR_PATH + "demo.csv")) {
            try (CSVParser csvParser = CSVFormat.Builder.create().setHeader("Name", "gender", "focus", "age").build().parse(fr)) {
                for (CSVRecord csvRecord : csvParser) {
                    p(csvRecord);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
