package knowledge.数据结构.集合框架.collection;

/**
 * Deque
 * double ended queue 双端队列
 * <p>
 * Deque 实现了 Queue
 * <p>
 * 操作位    第一个元素（头部）	            最后一个元素（尾部）
 * 操作     抛出异常	    特殊值	            抛出异常	    特殊值
 * 插入	addFirst(e)	    offerFirst(e)	addLast(e)	    offerLast(e)
 * 移除	removeFirst()	pollFirst()	    removeLast()	pollLast()
 * 检查	getFirst()	    peekFirst()	    getLast()	    peekLast()
 * <p>
 * 用作队列时 (FIFO) :
 * Queue 方法	等效 Deque 方法
 * add(e)	    addLast(e)
 * offer(e)	    offerLast(e)
 * remove()	    removeFirst()
 * poll()	    pollFirst()
 * element()	getFirst()
 * peek()	    peekFirst()
 * <p>
 * 用作堆栈时 (LIFO) :
 * 堆栈方法	    等效 Deque 方法
 * push(e)	    addFirst(e)
 * pop()	    removeFirst()
 * peek()	    peekFirst()
 */
public class DequeDemo {
}
