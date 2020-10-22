package knowledge.数据结构.集合框架.collection;

import l.demo.Demo;
import l.demo.Person;
import org.junit.Test;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Queue
 * <p>
 * Java 常见队列：https://pic4.zhimg.com/v2-c1f2992fb01c501a5dada75d0b27b0b3_r.jpg
 *
 * @author ljh
 * created on 2020/10/9 14:38
 */
public class QueueDemo extends Demo {

    /**
     * Queue 队列
     * Queue 继承 Collection
     * Queue 实现了一个先进先出 (FIFO) 的数据结构
     * Queue 用来存放 等待处理元素 的集合，这种场景一般用于缓冲、并发访问。
     * https://jdk6.net/util/Queue.html
     * <p>
     * 操作       抛出异常	    返回特殊值（推荐使用）
     * 插入	    add(e)	        offer(e)
     * 移除	    remove()	    poll()
     * 检查	    element()	    peek()
     */
    @Test
    public void testQueue() {
        DequeDemo dequeDemo = new DequeDemo();
        dequeDemo.testDequeAsQueue();
    }

    /**
     * BlockingQueue    阻塞队列
     * BlockingQueue 继承 Queue
     * https://www.imooc.com/article/257739
     * <p>
     * 操作       抛出异常	    返回特殊值       阻塞          超时退出
     * 插入	    add(e)	        offer(e)        put(e)      offer(e, time, unit)
     * 移除	    remove()	    poll()          take()      poll(e, time, unit)
     * 检查	    element()	    peek()
     * <p>
     * ArrayBlockingQueue       有界，数组结构
     * LinkedBlockingQueue      可选有界或无界，链表结构
     * LinkedTransferQueue      无界，链表结构 ???
     * PriorityBlockingQueue    无界，优先级
     * DelayQueue               无界，延迟
     * SynchronousQueue         无空间
     */
    @Test
    public void testBlockingQueue() throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(20);
        for (int i = 0; i < 25; i++) {
            queue.offer(i, 1, TimeUnit.SECONDS);
        }
        p(queue.size());
    }

    /**
     * PriorityQueue
     * PriorityQueue → AbstractQueue → Queue
     * 一个基于优先级堆的无界优先级队列。
     * 优先级队列的元素按照其自然顺序进行排序，或者根据构造队列时提供的 Comparator 进行排序。
     * 优先级队列不允许使用 null 元素。
     * 依靠自然顺序的优先级队列还不允许插入不可比较的对象（这样做可能导致 ClassCastException）。
     * https://jdk6.net/util/AbstractQueue.html
     */
    @Test
    public void testPriorityQueue() {
        p("----- 小顶堆 -----");
        PriorityQueue<Person> queue = new PriorityQueue<>();
        addData(queue);
        while (!queue.isEmpty()) {
            p(queue.poll());
            // Person{name='张三', age=18}
            // Person{name='李四', age=22}
            // Person{name='王五', age=25}
            // Person{name='赵六', age=30}
        }

        p("----- 大顶堆 -----");
        queue = new PriorityQueue<>((o1, o2) -> o2.getAge() - o1.getAge());
        addData(queue);
        while (!queue.isEmpty()) {
            p(queue.poll());
            // Person{name='赵六', age=30}
            // Person{name='王五', age=25}
            // Person{name='李四', age=22}
            // Person{name='张三', age=18}
        }
    }

    public void addData(PriorityQueue<Person> queue) {
        queue.add(new Person("张三", 18));
        queue.add(new Person("李四", 22));
        queue.add(new Person("王五", 25));
        queue.add(new Person("赵六", 30));
    }
}
