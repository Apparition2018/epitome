package jar.apache.commons.text;

import org.apache.commons.text.CaseUtils;
import org.junit.jupiter.api.Test;

/**
 * CaseUtils
 * https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/CaseUtils.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class CaseUtilsDemo {

    /**
     * static String	toCamelCase(String str, boolean capitalizeFirstLetter, char... delimiters)
     */
    @Test
    public void toCamelCase() {

        System.out.println(CaseUtils.toCamelCase("To.Camel.Case", true, '.'));  // ToCamelCase
        System.out.println(CaseUtils.toCamelCase("To.Camel.Case", false, '.')); // toCamelCase

    }
}
