package knowledge.数据结构.集合框架;

import l.demo.Demo;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

/**
 * Iterator
 * Iterator 替代 Enumeration
 * https://jdk6.net/lang/Iterable.html
 */
public class IteratorDemo extends Demo {

    @Test
    public void testIterator() {
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();

        // boolean	    hasNext()       如果仍有元素可以迭代，则返回 true
        while (it.hasNext()) {
            // E	    next()          返回迭代的下一个元素
            System.out.println(it.next());
            // void	    remove()        从迭代器指向的 collection 中移除迭代器返回的最后一个元素（可选操作）
            it.remove();
        }
    }

}
