package knowledge.api.lang.wrapper;

import l.demo.Demo;
import org.junit.Test;

/**
 * 整型缓存
 * <p>
 * 在 Java 5 中，为整型对象引入的一个新特性，有助于节省内存和提高性能
 * 整型对象在内部实现中通过使用相同的对象引用实现了缓存和重用
 * 上面的规则适用于整数区间 -128 ~ +127
 * 这种 Integer 缓存策略仅在自动装箱 (autoboxing) 的时候有用，使用构造器创建的 Integer 对象不能被缓存
 * Integer 可以通过参数改变范围，其它的都不行
 * <p>
 * Byte, Short, Long: -128 ~ +127
 * Character: 0 ~ +127
 * <p>
 * https://www.cnblogs.com/dolphin0520/p/3780005.html
 * https://www.cnblogs.com/wang-yaz/p/8516151.html
 */
public class IntegerCacheDemo extends Demo {

    @Test
    public void integerCache() {
        Integer x1 = 100;
        Integer x2 = 100;
        Integer x3 = 200;
        Integer x4 = 200;

        Integer y1 = new Integer(100);
        Integer y2 = new Integer(100);
        Integer y3 = new Integer(200);

        p(x1 == x2);            // true
        p(x3 == x4);            // false    超过了127
        p(x1 == y1);            // false    用构造器创建的 Integer 对象不能被缓存
        p(y1 == y2);            // false

        // 包装类运算有一个操作数是表达式会自动拆箱，所以下面全是 true
        p(x3 == (x1 + x2));     // true
        p(x3 == (y1 + y2));     // true
        p(y3 == (x1 + x2));     // true
        p(y3 == (y1 + y2));     // true

        Double d1 = 100.0;
        Double d2 = 100.0;
        p(d1 == d2);            // false    浮点数没有实现缓存技术；因为在一个区间内，有无数个浮点数，不能提前创建

        Boolean b1 = false;
        Boolean b2 = false;
        Boolean b3 = true;
        Boolean b4 = true;
        p(b1 == b2);            // true     查看 Boolean valueOf(boolean b) 源码
        p(b3 == b4);            // true

        Long la = 100L;
        Long lb = 200L;
        p(lb.equals(x1 + x2));  // false    查看 equals(Object o) 源码：1)类型相同，2)值相等
        p(lb.equals(x1 + la));  // true
    }

}
