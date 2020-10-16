package jar.apache.commons.collections4;

import l.demo.Demo;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.map.LinkedMap;
import org.junit.Test;

/**
 * OrderedMap
 * <p>
 * OrderedMap 是一个接口，该 Map 是有序的，并允许向前和向后的迭代。
 * LinkedMap 和 ListOrderedMap 实现该接口。
 * <p>
 * http://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/OrderedMap.html
 */
public class OrderedMapDemo extends Demo {

    @Test
    public void orderedMap() {
        OrderedMap<String, Integer> om = new LinkedMap<>();
        om.put("A", 1);
        om.put("B", 2);
        om.put("C", 3);

        // K	firstKey()
        // 第一个键
        p(om.firstKey());       // A
        // K	lastKey()
        // 最后一个键
        p(om.lastKey());        // C

        // K	nextKey(K key)
        // 指定键的下一个键
        p(om.nextKey("A"));     // B
        // K	previousKey(K key)
        // 指定键的上一个键
        p(om.previousKey("C")); // B
    }

}
