package jar.apache.commons.collections4;

import org.apache.commons.collections4.EnumerationUtils;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import static l.demo.Demo.p;

/**
 * <a href="http://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/EnumerationUtils.html">EnumerationUtils</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class EnumerationUtilsDemo {

    public static void main(String[] args) {
        Vector<String> v = new Vector<>(List.of("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"));
        Enumeration<String> e = v.elements();

        // static <E> List<E>	toList(Enumeration<? extends E> enumeration)
        // Creates a list based on an enumeration
        List<String> list = EnumerationUtils.toList(e);
        p(list); // [Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday]

        e = v.elements();

        // static <T> T	        get(Enumeration<T> e, int index)
        // Returns the index-th value in the Enumeration
        p(EnumerationUtils.get(e, 0)); // Sunday
        p(EnumerationUtils.get(e, 0)); // Monday
        p(EnumerationUtils.get(e, 0)); // Tuesday
        p(EnumerationUtils.get(e, 0)); // Wednesday
        p(EnumerationUtils.get(e, 0)); // Thursday
        p(EnumerationUtils.get(e, 0)); // Friday
        p(EnumerationUtils.get(e, 0)); // Saturday
        p(EnumerationUtils.get(e, 0)); // IndexOutOfBoundsException: Entry does not exist: 0
    }
}
