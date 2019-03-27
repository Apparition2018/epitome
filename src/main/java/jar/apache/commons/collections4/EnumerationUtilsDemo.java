package jar.apache.commons.collections4;

import org.apache.commons.collections4.EnumerationUtils;
import org.junit.Test;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * EnumerationUtils
 * <p>
 * http://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/EnumerationUtils.html
 */
public class EnumerationUtilsDemo {

    @Test
    public void enumeration() {

        Vector<String> v = new Vector<>();
        v.add("Sunday");
        v.add("Monday");
        v.add("Tuesday");
        v.add("Wednesday");
        v.add("Thursday");
        v.add("Friday");
        v.add("Saturday");

        Enumeration<String> e = v.elements();

        // static <E> List<E>	toList(Enumeration<? extends E> enumeration)
        // Creates a list based on an enumeration
        List<String> list = EnumerationUtils.toList(e);
        System.out.println(list); // [Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday]

        e = v.elements();

        // static <T> T	get(Enumeration<T> e, int index)
        // Returns the index-th value in the Enumeration
        System.out.println(EnumerationUtils.get(e, 0)); // Sunday
        System.out.println(EnumerationUtils.get(e, 0)); // Monday
        System.out.println(EnumerationUtils.get(e, 0)); // Tuesday
        System.out.println(EnumerationUtils.get(e, 0)); // Wednesday
        System.out.println(EnumerationUtils.get(e, 0)); // Thursday
        System.out.println(EnumerationUtils.get(e, 0)); // Friday
        System.out.println(EnumerationUtils.get(e, 0)); // Saturday
        // System.out.println(EnumerationUtils.get(e, 0)); // IndexOutOfBoundsException: Entry does not exist: 0

    }

}
