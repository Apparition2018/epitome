package knowledge.types.wrapper;

import org.junit.jupiter.api.Test;

/**
 * 整型缓存
 * <pre>
 * 在 JDK5 引入的新特性，有助于节省内存和提高性能
 * 整型对象在内部实现中通过使用相同的对象引用实现了缓存和重用
 * </pre>
 * <pre>
 * 类型          最小值          最大值
 * Byte         -128            127
 * Short        -128            127
 * Long         -128            127
 * Character    0               127
 * </pre>
 * 参考：<a href="https://www.cnblogs.com/dolphin0520/p/3780005.html">深入剖析装箱和拆箱</a>
 *
 * @author ljh
 * @since 2020/9/7 1:28
 */
public class IntegerCacheDemo {

    @Test
    public void testXxxCache() {
        Long l1 = 100L;
        Long l2 = 100L;
        Long l3 = new Long(100);
        Long l5 = 200L;
        Long l6 = 200L;

        System.out.println(l1 == l2);           // true；自动装箱 (autoboxing) 调用了 valueOf(Xxx x)，指向 XxxCache.cache 中已经存在的对象的引用
        System.out.println(l1 == l3);           // false；没有自动装箱
        System.out.println(l5 == l6);           // false；超出缓存范围
        System.out.println(l1 == 50L + 50);     // true；算术运算，自动拆箱 (unboxing)，调用了 intValue()，比较值是否相等
        System.out.println(l1.equals(50L + 50));// true；先自动拆箱，再自动装箱
    }
}
