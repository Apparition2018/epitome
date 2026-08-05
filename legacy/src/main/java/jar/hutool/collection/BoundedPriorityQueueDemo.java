package jar.hutool.collection;

import cn.hutool.core.collection.BoundedPriorityQueue;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href="https://doc.hutool.cn/pages/BoundedPriorityQueue/">BoundedPriorityQueue</a> 有界优先队列
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/collection/BoundedPriorityQueue.html">BoundedPriorityQueue api</a>
 *
 * @author ljh
 * @since 2020/11/1 23:23
 */
public class BoundedPriorityQueueDemo {

    /**
     * 有一个用户表，这个表根据用户名被 Hash 到不同的数据库实例上，找出这些用户中最热门的5个
     * 1.在每个数据库实例上找出最热门的5个
     * 2.将每个数据库实例上的这5条数据按照热门程度排序，最后取出前5条
     */
    public static void main(String[] args) {
        BoundedPriorityQueue<Integer> queue = new BoundedPriorityQueue<>(5, Comparator.naturalOrder());

        int[] users1 = new int[]{5, 7, 9, 2, 3};
        int[] users2 = new int[]{1, 4, 10, 8, 6};
        Arrays.stream(users1).forEach(queue::offer);
        Arrays.stream(users2).forEach(queue::offer);

        System.out.println(queue.toList());
    }
}
