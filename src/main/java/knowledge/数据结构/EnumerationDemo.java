package knowledge.数据结构;

import org.junit.Test;

import java.util.Enumeration;
import java.util.Set;
import java.util.Vector;

/**
 * Enumeration
 * <p>
 * Enumeration 的功能与 Iterator 接口的功能是重复的。
 * 此外，Iterator 接口添加了一个可选的移除操作，并使用较短的方法名。
 * 新的实现应该优先考虑使用 Iterator 接口而不是 Enumeration 接口。
 */
public class EnumerationDemo {

    /**
     * boolean	hasMoreElements()
     * 测试此枚举是否包含更多的元素
     */
    @Test
    public void hasMoreElements() {
        Enumeration<String> daysE;

        Vector<String> daysV = new Vector<>();
        daysV.add("Sunday");
        daysV.add("Monday");
        daysV.add("Tuesday");
        daysV.add("Wednesday");
        daysV.add("Thursday");
        daysV.add("Friday");
        daysV.add("Saturday");

        daysE = daysV.elements();
        while (daysE.hasMoreElements()) {
            System.out.println(daysE.nextElement());
        }

    }

    /**
     * E	nextElement()
     * 如果此枚举对象至少还有一个可提供的元素，则返回此枚举的下一个元素
     */
    @Test
    public void nextElement() {
        hasMoreElements();
    }
}
