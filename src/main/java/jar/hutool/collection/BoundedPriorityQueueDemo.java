package jar.hutool.collection;

import cn.hutool.core.collection.BoundedPriorityQueue;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * BoundedPriorityQueue     有界优先队列
 * https://hutool.cn/docs/#/core/%E9%9B%86%E5%90%88%E7%B1%BB/%E6%9C%89%E7%95%8C%E4%BC%98%E5%85%88%E9%98%9F%E5%88%97-BoundedPriorityQueue
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/collection/BoundedPriorityQueue.html
 *
 * @author Arsenal
 * created on 2020/11/1 23:23
 */
public class BoundedPriorityQueueDemo {

    /**
     * 有一个用户表，这个表根据用户名被 Hash 到不同的数据库实例上，找出这些用户中最热门的5个
     * 1.在每个数据库实例上找出最热门的5个
     * 2.将每个数据库实例上的这5条数据按照热门程度排序，最后取出前5条
     */
    @Test
    public void testBoundedPriorityQueue() {
        BoundedPriorityQueue<Integer> queue = new BoundedPriorityQueue<>(5, Comparator.naturalOrder());

        int[] users1 = new int[]{5, 7, 9, 2, 3};
        int[] users2 = new int[]{1, 4, 10, 8, 6};
        Arrays.stream(users1).forEach(queue::offer);
        Arrays.stream(users2).forEach(queue::offer);

        System.out.println(queue.toList());
    }
}
