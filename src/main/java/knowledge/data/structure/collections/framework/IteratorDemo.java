package knowledge.data.structure.collections.framework;

import l.demo.Demo;

import java.util.Iterator;
import java.util.Map;

/**
 * Iterator
 * Iterator 替代 Enumeration
 * https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/Iterator.html
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
            // 不要在 foreach 循环里进行元素的 remove / add 操作。remove 元素请使用 iterator 方式，如果并发操作，需要对 Iterator 对象加锁（阿里编程规约）
            it.remove();
        }
    }

}
