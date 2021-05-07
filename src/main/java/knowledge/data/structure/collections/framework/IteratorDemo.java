package knowledge.data.structure.collections.framework;

import l.demo.Demo;

import java.util.Iterator;
import java.util.Map;

/**
 * Iterator
 * Iterator 替代 Enumeration
 * https://www.runoob.com/manual/jdk1.6/java.base/java/util/Iterator.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class IteratorDemo extends Demo {

    public static void main(String[] args) {
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();

        // boolean	    hasNext()       如果仍有元素可以迭代，则返回 true
        while (it.hasNext()) {
            // E	    next()          返回迭代的下一个元素
            p(it.next());
            // void	    remove()        从迭代器指向的 collection 中移除迭代器返回的最后一个元素（可选操作）
            it.remove();
        }
    }

}
