package knowledge.api.util;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Objects;

import static l.demo.Demo.p;

/**
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Objects.html">Objects</a>
 * <p>JDK7 新增的工具类，由一些静态方法组成，这些方法是空指针安全的 (null-safe)，容忍空指针的 (null-tolerant)
 *
 * @author ljh
 * @since 2020/10/23 20:09
 */
public class ObjectsDemo {

    @Test
    public void testNull() {
        Integer x = null;

        // static boolean	    isNull(Object obj)
        p(Objects.isNull(x));   // true
        // static boolean	    nonNull(Object obj)
        p(Objects.nonNull(x));  // false

        // static <T> T	        requireNonNull(T obj[, String message / Supplier<String> messageSupplier])
        // 检查指定的对象引用是否为空，如果为空，则抛出自定义的 NullPointerException
        p(Objects.requireNonNull(x, "参数不能为空"));
    }

    @Test
    public void toString_() {
        p(Objects.toString(null, "参数不能为空"));
    }

    @Test
    public void testCompareAndEquals() {
        Integer a = 2;
        Integer b = 2;
        Integer c = 3;
        Integer d = null;
        Integer e = null;

        // static <T> int	    compare(T a, T b, Comparator<? super T> c)
        // 如果两个参数都为 null，返回整数0，否则返回-1
        // 如果其中一个参数是 null，是否会抛出 NullPointerException 取决于排序策略
        p(Objects.compare(a, b, Comparator.comparingInt(i -> i)));      // 0
        p(Objects.compare(d, e, Comparator.comparingInt(i -> i)));      // 0
        p(Objects.compare(a, c, Comparator.comparingInt(i -> i)));      // -1
        // p(Objects.compare(a, d, Comparator.comparingInt(i -> i)));   // NullPointerException

        // static boolean	    equals(Object a, Object b)
        // 如果两个参数相等，则返回 true，否则返回 false
        // 阿里编程规约：
        // Object 的 equals 方法容易抛空指针异常，应使用常量或确定有值的对象来调用 equals
        // 推荐使用 JDK7 引入的工具类 java.util.Objects#equals(Object a, Object b)
        p(Objects.equals(a, b)); // true
        p(Objects.equals(d, e)); // true
        p(Objects.equals(a, c)); // false
        p(Objects.equals(a, d)); // false


        String[][] name1 = {{"L", "i", "a", "n", "g"}, {"J", "i", "e"}, {"H", "u", "i"}};
        String[][] name2 = {{"L", "i", "a", "n", "g"}, {"J", "i", "e"}, {"H", "u", "i"}};

        // static boolean	    deepEquals(Object a, Object b)
        // 如果两个参数完全相等，则返回 true，否则返回 false
        // 底层调用了 Arrays.deepEquals(a, b)
        p(Objects.equals(name1, name2));        // false
        p(Objects.deepEquals(name1, name2));    // true，注意不能用来判断是否深克隆
    }

    @Test
    public void testHash() {
        p(new Object().hashCode());             // 896681694
        // static int	        hash(Object... values)
        p(Objects.hash(new Object(), null));    // -170335782
        // static int	        hashCode(Object o)
        p(Objects.hashCode(new Object()));      // 627185331
    }
}
