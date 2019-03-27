package knowledge.数据结构.集合框架;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IteratorDemo {

    /**
     * boolean	hasNext()
     * 如果仍有元素可以迭代，则返回 true
     */
    @Test
    public void hasNext() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "A");
        map.put("2", "B");
        map.put("3", "C");

        Iterator it = map.entrySet().iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
            it.remove();
        }
    }

    /**
     * E	next()
     * 返回迭代的下一个元素
     */
    @Test
    public void next() {
        hasNext();
    }

    /**
     * void	remove()
     * 从迭代器指向的 collection 中移除迭代器返回的最后一个元素（可选操作）
     */
    @Test
    public void remove() {
        hasNext();
    }

}
