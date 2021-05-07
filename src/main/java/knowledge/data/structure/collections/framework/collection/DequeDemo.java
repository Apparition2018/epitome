package knowledge.data.structure.collections.framework.collection;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static l.demo.Demo.p;

/**
 * Deque 双端队列  (double ended queue)
 * Deque 继承 Queue
 * https://www.runoob.com/manual/jdk1.6/java.base/java/util/Deque.html
 * <p>
 * 操作位              第一个元素（头部）	                    最后一个元素（尾部）
 * 操作               抛出异常	    特殊值	                抛出异常	        特殊值
 * 插入	            addFirst(e)     offerFirst(e)	        addLast(e)	    offerLast(e)
 * 移除	            removeFirst()	pollFirst()	            removeLast()	pollLast()
 * 检查	            getFirst()	    peekFirst()	            getLast()	    peekLast()
 * <p>
 * Iterator<E>	    iterator()                      返回以恰当顺序在此双端队列的元素上进行迭代的迭代器
 * Iterator<E>	    descendingIterator()            返回以逆向顺序在此双端队列的元素上进行迭代的迭代器
 * boolean	        removeFirstOccurrence(Object o) 从此双端队列中移除第一次出现的指定元素
 * boolean	        removeLastOccurrence(Object o)  从此双端队列移除最后一次出现的指定元素
 * <p>
 * 算法刻意练习之栈、队列、双端队列、优先队列：https://www.pianshen.com/article/27711847617/
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class DequeDemo {

    /**
     * 用作队列时 (FIFO) :
     * 数据结构                     Queue                                   Deque
     * 操作               抛出异常	    返回特殊值（推荐使用）         抛出异常	    返回特殊值（推荐使用）
     * 插入	            add(e)	        offer(e)                addLast(e)      offerLast(e)
     * 移除	            remove()	    poll()                  removeFirst()   pollFirst()
     * 检查	            element()	    peek()                  getFirst()      peekFirst()
     */
    @Test
    public void testDequeAsQueue() {
        Queue<Integer> queue = new LinkedList<>();
        // 从队列尾添加元素
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        p(queue);               // [1, 2, 3]
        // 获取并移除此队列的头；如果此队列为空，则返回 null
        p(queue.poll());        // 1
        // 获取但不移除此队列的头；如果此队列为空，则抛出异常
        p(queue.element());     // 2
        // 获取但不移除此队列的头；如果此队列为空，则返回 null
        p(queue.peek());        // 2

        Deque<Integer> deque = new LinkedList<>();
        deque.offerLast(1);
        deque.offerLast(2);
        deque.offerLast(3);
        p(deque);               // [1, 2, 3]
        p(deque.pollFirst());   // 1
        p(deque.getFirst());    // 2
        p(deque.peekFirst());   // 2
    }

    /**
     * Stack
     * Stack 继承 Vector
     * Stack 类表示后进先出（LIFO）的对象堆栈。它通过五个操作对类 Vector 进行了扩展 ，允许将向量视为堆栈。
     * https://www.runoob.com/manual/jdk1.6/java.base/java/util/Stack.html
     * <p>
     * Deque 接口及其实现提供了 LIFO 堆栈操作的更完整和更一致的集合，应该优先使用 Deque，而非 stack。
     * <p>
     * 用作堆栈时 (LIFO) :
     * 数据结构             Stack           Deque
     * 插入               push(e)         addFirst(e)
     * 移除               pop()           removeFirst()
     * 检查               peek()          peekFirst()
     */
    @Test
    public void testDequeAsStack() {
        Stack<Integer> stack = new Stack<>();
        // 从堆栈顶部添加元素
        stack.push(1);
        stack.push(2);
        stack.push(3);
        p(stack);               // [1, 2, 3]
        // 获取并移除此队列的头
        p(stack.pop());         // 3
        // 获取但不移除此队列的头
        p(stack.peek());        // 2
        p(stack.search(0));     // -1
        p(stack.search(1));     // 2

        Deque<Integer> deque = new LinkedList<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        p(deque);               // [1, 2, 3]
        p(deque.removeFirst()); // 3
        p(deque.peekFirst());   // 2
    }

    /**
     * BlockingDeque    阻塞双端队列
     * https://www.runoob.com/manual/jdk1.6/java.base/java/util/concurrent/BlockingDeque.html
     * <p>
     * 操作位                          第一个元素（头部）
     * 操作       抛出异常            特殊值             阻塞          超时退出
     * 插入       addFirst(e)     offerFirst(e)   putFirst(e)     offerFirst(e, time, unit)
     * 移除       removeFirst()   pollFirst()     takeFirst()    pollFirst(time, unit)
     * 检查       getFirst()      peekFirst()
     * 操作位                          最后一个元素（尾部）
     * 操作       抛出异常            特殊值             阻塞          超时退出
     * 插入       addLast(e)      offerLast(e)   putLast(e)       offerLast(e, time, unit)
     * 移除       removeLast()    pollLast()     takeLast()      pollLast(time, unit)
     * 检查       getLast()       peekLast()
     */
    @Test
    public void testBlockingDeque() {

    }
}
