package knowledge.数据结构.集合框架.collection;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Queue 队列
 * <p>
 * Queue 实现了一个先进先出 (FIFO) 的数据结构
 * <p>
 * 操作     抛出异常	    返回特殊值（推荐使用）
 * 插入	    add(e)	        offer(e)
 * 移除	    remove()	    poll()
 * 检查	    element()	    peek()
 */
public class QueueDemo {

    @Test
    public void test() {
        Queue<String> queue = new LinkedList<>();

        // boolean	offer(E e)
        // 将指定的元素插入此队列（如果立即可行且不会违反容量限制），当使用有容量限制的队列时，
        // 此方法通常要优于 add(E)，后者可能无法插入元素，而只是抛出一个异常
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");

        // E	poll()
        // 获取并移除此队列的头，如果此队列为空，则返回 null
        System.out.println("poll=" + queue.poll());

        // E	element()
        // 获取，但是不移除此队列的头
        System.out.println("element=" + queue.element());

        // E	peek()
        // 获取但不移除此队列的头；如果此队列为空，则返回 null
        System.out.println("peek=" + queue.peek());

    }

}
