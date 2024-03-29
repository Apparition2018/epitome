package knowledge.data.structure;

import l.demo.Demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Fail
 *
 * @author ljh
 * @since 2020/9/7 16:55
 */
public class Fail extends Demo {

    @BeforeEach
    public void before() {
        p(map);
    }

    /**
     * fail-fast        快速失败
     * <p>在用迭代器遍历一个集合对象时，如果遍历过程中对集合对象的内容进行了修改（增加、删除、修改），则会抛出 ConcurrentModificationException
     */
    @Test
    public void testFailSafe() {
        map.forEach((k, v) -> {
            p(map.get(k));
            map.put(4, "D"); // ConcurrentModificationException
        });
    }

    /**
     * fail-safe        安全失败
     * <pre>
     * 用安全失败机制的集合容器，在遍历时不是直接在集合内容上访问的，而是先复制原有集合内容，在拷贝的集合上进行遍历
     * java.util.concurrent 包下的容器都是安全失败，可以在多线程下并发使用，并发修改
     * </pre>
     */
    @Test
    public void testFailFast() {
        map = new ConcurrentHashMap<>(map);

        map.forEach((k, v) -> {
            p(map.get(k));
            map.put(4, "D");
            // A
            // B
            // C
            // D
        });
    }
}
