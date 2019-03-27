package knowledge.数据结构.集合框架.collection;

import org.junit.Test;

import java.util.TreeSet;

/**
 * TreeSet
 * 使用元素的自然顺序对元素进行排序，或者根据创建 set 时提供的 Comparator 进行排序，具体取决于使用的构造方法。
 * <p>
 * http://www.cnblogs.com/bravolove/p/5810267.html
 */
public class TreeSetDemo {

    @Test
    public void comparator() {
        TreeSet<Integer> nums = new TreeSet<>();
        nums.add(5);
        nums.add(2);
        nums.add(6);
        nums.add(-4);
        System.out.println(nums); // [-4, 2, 5, 6]

        nums = new TreeSet<>((o1, o2) -> o2 - o1);
        nums.add(5);
        nums.add(2);
        nums.add(6);
        nums.add(-4);
        System.out.println(nums); // [6, 5, 2, -4]
    }

}
