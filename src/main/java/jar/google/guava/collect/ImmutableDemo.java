package jar.google.guava.collect;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

/**
 * 不可变集合
 * <p>
 * ImmutableList
 * ImmutableMap
 * ImmutableSet
 * ImmutableSortedMap
 * ImmutableSortedSet
 * <p>
 * 效率高，占内存少
 */
public class ImmutableDemo {

    @Test
    public void test() {
        // 不可变 List
        ImmutableList<String> list = ImmutableList.of("A", "B", "C");

        // 不可变 Map
        ImmutableMap<Integer, String> map = ImmutableMap.of(1, "壹", 2, "贰", 3, "叁");

    }

}
