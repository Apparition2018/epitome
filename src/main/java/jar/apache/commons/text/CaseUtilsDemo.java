package jar.apache.commons.text;

import org.apache.commons.text.CaseUtils;
import org.junit.Test;

/**
 * CaseUtils
 * https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/CaseUtils.html
 */
public class CaseUtilsDemo {

    /**
     * static String	toCamelCase​(String str, boolean capitalizeFirstLetter, char... delimiters)
     */
    @Test
    public void toCamelCase() {

        System.out.println(CaseUtils.toCamelCase("To.Camel.Case", true, '.'));  // ToCamelCase
        System.out.println(CaseUtils.toCamelCase("To.Camel.Case", false, '.')); // toCamelCase

    }
}
