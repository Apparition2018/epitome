package jar.apache.commons.collections4.list;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.list.LazyList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/list/package-summary.html
 */
public class Collections4ListDemo {

    /**
     * LazyList
     * LazyList 修饰另一个列表，当调用 get 方法时，如果索引超出列表长度，列表会自动增长，我们可以通过一个工厂获得超出索引位置的值。
     * LazyList 和 GrowthList 都可以实现对修饰的列表进行增长，但是 LazyList 发生在 get 时候，而 GrowthList 发生在 set 和 add 时候。
     */
    @Test
    public void lazyList() {
        List<Integer> list = Lists.newArrayList();
        list.add(0);
        list.add(1);
        // 把一个 List 包装成一个 lazy 类型
        LazyList<Integer> lazy = LazyList.lazyList(list, () -> -1);
        System.out.println(lazy.get(3)); // -1
    }
}
