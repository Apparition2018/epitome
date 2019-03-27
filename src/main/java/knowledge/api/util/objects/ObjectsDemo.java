package knowledge.api.util.objects;

import org.junit.Test;

import java.util.Comparator;
import java.util.Objects;

/**
 * Objects
 * <p>
 * Objects 是 jdk1.7 新增的工具类
 * Objects 由一些静态方法组成，这些方法是空指针安全的 (null-safe)，容忍空指针的 (null-tolerant)
 * <p>
 * https://www.cnblogs.com/quiet-snowy-day/p/6387321.html
 * https://blog.csdn.net/xinghuo0007/article/details/78895577
 * https://blog.csdn.net/yamaxifeng_132/article/details/53538903
 */
public class ObjectsDemo {

    /**
     * static <T> int	compare(T a, T b, Comparator<? super T> c)
     * Returns 0 if the arguments are identical and c.compare(a, b) otherwise
     * <p>
     * 如果两个参数都为 null，返回整数0
     * 如果其中一个参数是 null，是否会抛出 NullPointerException 取决于排序策略
     */
    @Test
    public void compare() {
        Integer n1 = null;
        Integer n2 = 2;
        System.out.println(Objects.compare(n1, n2, Comparator.comparingInt(o -> o)));
    }

    /**
     * static boolean	equals(Object a, Object b)
     * Returns true if the arguments are equal to each other and false otherwise
     */
    @Test
    public void equals() {
        Number n1 = null;
        Number n2 = 2;
        Number n3 = null;
        System.out.println(Objects.equals(n1, n2)); // false
        System.out.println(Objects.equals(n1, n3)); // true
    }

    /**
     * static boolean	deepEquals(Object a, Object b)
     * Returns true if the arguments are deeply equal to each other and false otherwise
     * <p>
     * 当参数是数组对象，Arrays.deepEquals()
     */
    @Test
    public void deepEquals() {
        String[][] name1 = {{"L", "i", "a", "n", "g"}, {"J", "i", "e"}, {"H", "u", "i"}};
        String[][] name2 = {{"L", "i", "a", "n", "g"}, {"J", "i", "e"}, {"H", "u", "i"}};

        System.out.println(Objects.equals(name1, name2));       // false
        System.out.println(Objects.deepEquals(name1, name2));   // true
    }

    /**
     * static int	hashCode(Object o)
     * Returns the hash code of a non-null argument and 0 for a null argument
     */
    @Test
    public void hashCode_() {
        Number n = null;
        System.out.println(Objects.hashCode(n)); // 0
    }

    /**
     * static boolean	isNull(Object obj)
     * Returns true if the provided reference is null otherwise returns false
     */
    @Test
    public void isNull() {
        Number n = null;
        System.out.println(Objects.isNull(n));  // true
        System.out.println(Objects.nonNull(n)); // false
    }

    /**
     * static boolean	nonNull(Object obj)
     * Returns true if the provided reference is non-null otherwise returns false
     */
    @Test
    public void nonNull() { isNull(); }

    /**
     * static <T> T	requireNonNull(T obj[, String message])
     * static <T> T	requireNonNull(T obj, Supplier<String> messageSupplier)
     * Checks that the specified object reference is not null and throws a customized NullPointerException if it is
     * <p>
     * 主要在方法，构造函数中做参数校验
     */
    @Test
    public void requireNonNull() {
        Number n = null;
        System.out.println(Objects.requireNonNull(n, "n 不能为空"));
    }

    /**
     * static String	toString(Object o[, String nullDefault])
     * Returns the result of calling toString on the first argument if the first argument is not null and returns the second argument otherwise
     */
    @Test
    public void toString_() {
        Number n = null;
        System.out.println(Objects.toString(n)); // null
    }


}
