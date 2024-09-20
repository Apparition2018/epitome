package knowledge.data.structure.collections.framework.collection;

import l.demo.Demo;
import l.demo.Person;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Queue
 *
 * @author ljh
 * @see <a href="https://pic4.zhimg.com/v2-c1f2992fb01c501a5dada75d0b27b0b3_r.jpg">Java 常见队列</a>
 * @since 2020/10/9 14:38
 */
public class QueueDemo extends Demo {

    /**
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/Queue.html">Queue</a> 队列
     * <pre>
     * Queue 继承 Collection
     * Queue 实现了一个先进先出 (FIFO) 的数据结构
     * Queue 用来存放 等待处理元素 的集合，这种场景一般用于缓冲、并发访问。
     *
     * 操作       抛出异常        返回特殊值（推荐使用）
     * 插入       add(e)          offer(e)
     * 移除       remove()        poll()
     * 检查       element()       peek()
     * </pre>
     */
    @Test
    public void testQueue() {
        DequeDemo dequeDemo = new DequeDemo();
        dequeDemo.testDequeAsQueue();
    }

    /**
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/BlockingQueue.html">BlockingQueue</a>    阻塞队列
     * <p>BlockingQueue 继承 Queue
     * <p>参考：<a href="https://www.imooc.com/article/257739">Java 中的 BlockingQueue</a>
     * <pre>
     * 操作       抛出异常        返回特殊值       阻塞          超时退出
     * 插入       add(e)          offer(e)    put(e)      offer(e, time, unit)
     * 移除       remove()        poll()      take()      poll(time, unit)
     * 检查       element()       peek()
     * </pre>
     * <pre>
     * ArrayBlockingQueue       有界，数组结构
     * LinkedBlockingQueue      可选有界或无界，链表结构
     * PriorityBlockingQueue    无界，数组结构，优先级
     * DelayQueue               无界，延迟获取元素
     * LinkedTransferQueue      无界，链表结构，CPU 自旋等待消费者取走元素，自旋一定次数后结束
     * SynchronousQueue         无空间，一个添加操作后必须等待一个获取操作才可以继续添加
     * </pre>
     */
    @Test
    public void testBlockingQueue() throws InterruptedException {
        // ArrayBlockingQueue(int capacity[, boolean fair[, Collection<? extends E> c]])
        // 创建一个具有给定的（固定）容量和指定访问策略的 ArrayBlockingQueue，它最初包含给定 collection 的元素，并以 collection 迭代器的遍历顺序添加元素
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(20, true, list);
        p(queue);       // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // int	        remainingCapacity()
        // 返回在无阻塞的理想情况下（不存在内存或资源约束）此队列能接受的附加元素数量；如果没有内部限制，则返回 Integer.MAX_VALUE
        p(queue.remainingCapacity()); // 11

        for (int i = 0; i < 15; i++) {
            queue.offer(i, 1, TimeUnit.MILLISECONDS);
        }
        p(queue);       // [1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        p(list);        // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        // int	        drainTo(Collection<? super E> c[, int maxElements])
        // 最多从此队列中移除给定数量的可用元素，并将这些元素添加到给定 collection 中
        queue.drainTo(list, 5);
        p(list);        // [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5]
    }

    /**
     * DelayQueue   延时队列
     * <p>DelayQueue 继承 AbstractQueue 实现 BlockingQueue
     */
    @Test
    public void testDelayQueue() throws InterruptedException {
        Date now = new Date();
        p(DateFormatUtils.format(now, "HH:mm:ss")); // 15:04:15
        DelayQueue<DelayTask> delayQueue = new DelayQueue<>();
        delayQueue.put(new DelayTask().setTaskName("A").setStartTime(DateUtils.addSeconds(now, 1)));
        delayQueue.put(new DelayTask().setTaskName("B").setStartTime(DateUtils.addSeconds(now, 3)));
        delayQueue.put(new DelayTask().setTaskName("C").setStartTime(DateUtils.addSeconds(now, 6)));
        while (delayQueue.size() > 0) {
            DelayTask delayTask = delayQueue.take();
            p("Task " + delayTask.taskName + " start at " + DateFormatUtils.format(new Date(), "HH:mm:ss"));
            // Task A start at 15:04:16
            // Task B start at 15:04:18
            // Task C start at 15:04:21
        }
    }

    @Data
    @Accessors(chain = true)
    private static class DelayTask implements Delayed {

        private String taskName;
        private Date startTime;

        @Override
        public long getDelay(@NotNull TimeUnit unit) {
            return unit.convert(startTime.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(@NotNull Delayed other) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), other.getDelay(TimeUnit.MILLISECONDS));
        }
    }

    /**
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/PriorityQueue.html">PriorityQueue</a>    优先级队列
     * <pre>
     * PriorityQueue → AbstractQueue → Queue
     * 一个基于优先级堆的无界优先级队列。
     * 优先级队列的元素按照其自然顺序进行排序，或者根据构造队列时提供的 Comparator 进行排序。
     * 优先级队列不允许使用 null 元素。
     * 依靠自然顺序的优先级队列还不允许插入不可比较的对象（这样做可能导致 ClassCastException）。
     * </pre>
     */
    @Test
    public void testPriorityQueue() {
        p("----- 小顶堆 -----");
        PriorityQueue<Person> queue = new PriorityQueue<>();
        this.addData(queue);
        while (!queue.isEmpty()) {
            p(queue.poll());
            // Person{name='张三', age=18}
            // Person{name='李四', age=22}
            // Person{name='王五', age=25}
            // Person{name='赵六', age=30}
        }

        p("----- 大顶堆 -----");
        queue = new PriorityQueue<>((o1, o2) -> o2.getAge() - o1.getAge());
        queue.comparator();
        this.addData(queue);
        while (!queue.isEmpty()) {
            p(queue.poll());
            // Person{name='赵六', age=30}
            // Person{name='王五', age=25}
            // Person{name='李四', age=22}
            // Person{name='张三', age=18}
        }
    }

    public void addData(Queue<Person> queue) {
        queue.add(new Person("张三", 18));
        queue.add(new Person("李四", 22));
        queue.add(new Person("王五", 25));
        queue.add(new Person("赵六", 30));
    }
}
