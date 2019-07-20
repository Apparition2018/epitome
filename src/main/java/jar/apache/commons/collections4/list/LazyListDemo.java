package jar.apache.commons.collections4.list;

import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.list.LazyList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * LazyList
 *
 * http://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/list/LazyList.html
 */
public class LazyListDemo {

    @Test
    @SuppressWarnings("unchecked")
    public void lazyList() {
        // 把一个 List 包装成一个 lazy 类型
        List<String> lazy = LazyList.lazyList(
                new ArrayList(),
                (Factory) () -> "A");
        // 访问了第4个元素，此时0，1，2元素为 null
        String obj = lazy.get(3);
        System.out.println(obj); // "A"
        // 追加一个元素
        lazy.add("第五个元素");
        // 元素个数
        System.out.println(lazy.size()); // 5


    }
}
