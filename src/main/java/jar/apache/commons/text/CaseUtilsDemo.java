package jar.apache.commons.text;

import org.apache.commons.text.CaseUtils;
import org.junit.Test;

/**
 * CaseUtils
 * <p>
 * https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/CaseUtils.html
 */
public class CaseUtilsDemo {

    /**
     * static String	toCamelCase​(String str, boolean capitalizeFirstLetter, char... delimiters)
     */
    @Test
    public void toCamelCase() {

        p(CaseUtils.toCamelCase("To.Camel.Case", true, '.'));   // ToCamelCase
        p(CaseUtils.toCamelCase("To.Camel.Case", false, '.'));  // toCamelCase

    }

    public static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }
}
