package knowledge.数据结构.集合框架.collection.concurrent;

/**
 * BlockingQueue
 * <p>
 * BlockingQueue 是支持两个附加操作的 Queue
 * 1.获取元素时等待队列变为非空
 * 2.存储元素时等待空间变得可用
 * <p>
 * BlockingQueue 容量是固定的，可以在声明的时候指定队列容量，若不指定，队列容量为 Integer.MAX_VALUE
 * BlockingQueue 是线程安全的
 * <p>
 * 操作	抛出异常	    特殊值	    阻塞	    超时
 * 插入	add(e)	    offer(e)	put(e)	offer(e, time, unit)
 * 移除	remove()	poll()	    take()	poll(time, unit)
 * 检查	element()	peek()	    不可用	不可用
 */
public class BlockingQueueDemo {

}
