package knowledge.数据结构.集合框架.collection.concurrent;

/**
 * ConcurrentDemo
 */
public class ConcurrentDemo {

    /**
     * ************************************************************
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
     * ************************************************************
     * CopyOnWriteArrayList
     * <p>
     * 1.实现了List接口
     * 2.内部持有一个ReentrantLock lock = new ReentrantLock();
     * 3.底层是用volatile transient声明的数组 array
     * 4.读写分离，写时复制出一个新的数组，完成插入、修改或者移除操作后将新数组赋值给array
     * ************************************************************
     * ConcurrentSkipListSet
     * <p>
     * ConcurrentSkipListSet 是线程安全的有序的集合，适用于高并发的场景
     * ************************************************************
     */
    public void test() {

    }
}
