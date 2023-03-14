package jar.apache.commons.lang3.time;

import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Date;

/**
 * <a href="http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/time/FastDateFormat.html">FastDateFormat</a>
 *
 * @author ljh
 * @since 2023/3/13 17:20
 */
public class FastDateFormatDemo {

    public static void main(String[] args) throws ParseException {
        FastDateFormat dateFormat = FastDateFormat.getInstance();
        String dateStr = dateFormat.format(new Date());
        System.out.println(dateStr);                    // 2023/3/13 下午5:26
        System.out.println(dateFormat.parse(dateStr));  // Mon Mar 13 17:23:40 CST 2023
    }
}
