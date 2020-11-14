package jar.apache.commons.collections4;

import l.demo.Demo;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bag.TreeBag;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

/**
 * Bag
 * Bag 是一个接口，该接口可以计算一个对象出现在集合中的次数
 * <p>
 * Iterator<E>	iterator()
 * int	        size()
 * <p>
 * Commons Collections4 简单使用：https://blog.csdn.net/sinat_34093604/article/details/79551924
 * http://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/Bag.html
 */
public class BagDemo extends Demo {

    @Test
    public void testBag() {
        // boolean	add(E object[, int nCopies])        添加元素
        Bag<String> bag = new HashBag<>();
        bag.add("c", 3);
        bag.add("b");
        bag.add("b");
        bag.add("a");
        p("bag = " + bag);  // [1:a,2:b,3:c]

        Bag<String> bag2 = new HashBag<>();
        bag2.add("b", 2);
        p("bag2 = " + bag2);// [2:b]

        Bag<String> bag3 = new HashBag<>();
        bag3.add("a", 2);
        p("bag3 = " + bag3);// [2:a]

        // boolean	containsAll(Collection<?> coll)     包含
        p("bag contains bag2 = " + bag.containsAll(bag2)); // true
        p("bag contains bag3 = " + bag.containsAll(bag3)); // false

        // int	    getCount(Object object)             返回元素在 Bag 中出现次数
        p("c = " + bag.getCount("c")); // 3

        // Set<E>	uniqueSet()                         返回包含唯一元素的一个 Set
        p("uniqueSet = " + bag.uniqueSet()); // [a, b, c]

        // boolean	remove(Object object)               移除所有指定元素
        bag.remove("b");
        p("bag = " + bag);      // [1:a,3:c]

        // boolean	remove(Object object, int nCopies)  移除指定个数的指定元素
        bag.remove("c", 1);
        p("bag = " + bag);      // [1:a,2:c]

        Bag<String> bag4 = new HashBag<>();
        bag4.add("a");
        bag4.add("c");
        p("bag4 = " + bag4);    // [1:a,1:c]
        // boolean	removeAll(Collection<?> coll)       移除 collection 中的包含的元素
        bag.removeAll(bag4);
        p("bag = " + bag);      // [1:c]

        Bag<String> bag5 = new HashBag<>();
        bag5.add("b", 2);
        bag5.add("c", 1);
        p("bag5 = " + bag5);    // [2:b,1:c]
        // boolean	retainAll(Collection<?> coll)       交集
        bag4.retainAll(bag5);
        p("bag4 = " + bag4);    // [1:c]
    }

}
