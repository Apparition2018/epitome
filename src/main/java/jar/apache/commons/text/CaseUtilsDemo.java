package jar.apache.commons.text;

import org.apache.commons.text.CaseUtils;

/**
 * <a href="https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/CaseUtils.html">CaseUtils</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class CaseUtilsDemo {

    /**
     * static String	toCamelCase(String str, boolean capitalizeFirstLetter, char... delimiters)
     */
    public static void main(String[] args) {
        System.out.println(CaseUtils.toCamelCase("To.Camel.Case", true, '.'));  // ToCamelCase
        System.out.println(CaseUtils.toCamelCase("To.Camel.Case", false, '.')); // toCamelCase
    }
}
