package knowledge.数据结构.集合框架.collection;

import l.demo.Demo;

/**
 * Deque 双端队列  (double ended queue)
 * Deque 继承 Queue
 * https://jdk6.net/util/Deque.html
 * <p>
 * 操作位              第一个元素（头部）	                    最后一个元素（尾部）
 * 操作               抛出异常	    特殊值	                抛出异常	        特殊值
 * 插入	            addFirst(e)	    offerFirst(e)	        addLast(e)	    offerLast(e)
 * 移除	            removeFirst()	pollFirst()	            removeLast()	pollLast()
 * 检查	            getFirst()	    peekFirst()	            getLast()	    peekLast()
 * <p>
 * 用作队列时 (FIFO) :
 * Queue 方法	    等效 Deque 方法
 * add(e)	        addLast(e)
 * offer(e)	        offerLast(e)
 * remove()	        removeFirst()
 * poll()	        pollFirst()
 * element()	    getFirst()
 * peek()	        peekFirst()
 * <p>
 * 用作堆栈时 (LIFO) :
 * Deque 方法	    等效 Deque 方法
 * push(e)	        addFirst(e)
 * pop()	        removeFirst()
 * peek()	        peekFirst()
 * <p>
 * Iterator<E>	    iterator()              返回以恰当顺序在此双端队列的元素上进行迭代的迭代器
 * Iterator<E>	    descendingIterator()    返回以逆向顺序在此双端队列的元素上进行迭代的迭代器
 * <p>
 * boolean	        remove(Object o)        从此双端队列中移除第一次出现的指定元素
 * boolean	        removeLastOccurrence(Object o)  从此双端队列移除最后一次出现的指定元素
 */
public class DequeDemo extends Demo {
}
