package knowledge.api.lang.wrapper;

import l.demo.Demo;
import org.junit.Test;

/**
 * 整型缓存
 * <p>
 * 在 Java5 中，为整型对象引入的一个新特性，有助于节省内存和提高性能
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

    /**
     * valueOf(int i) 方法引用了 Integer.Cache
     * 如果数值在 [-128,127] 之间，便返回指向 IntegerCache.cache 中已经存在的对象的引用
     */
    @Test
    public void testInteger() {
        Integer x1 = 100;
        Integer x2 = 100;
        Integer x3 = 200;
        Integer x4 = 200;
        
        p(x1 == x2); // true
        p(x3 == x4); // false
    }

    /**
     * valueOf(boolean b) 返回 Boolean 中已定义的两个静态属性 new Boolean(false) 和 new Boolean(true)
     */
    @Test
    public void testBoolean() {
        Boolean x1 = false;
        Boolean x2 = false;
        
        p(x1 == x2); // true
    }

    /**
     * int + long，结果为 long
     */
    @Test
    public void testArithmetic() {
        Integer x1 = 100;
        Integer x2 = 100;
        
        Long y1 = 100L;
        Long y2 = 200L;
        
        p(y2.equals(x1 + x2)); // false 值相等，但类型不相等
        p(y2.equals(x1 + y1)); // true  值相等，类型相等
    }

}
