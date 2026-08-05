package knowledge.concurrent.lock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/locks/Lock.html">Lock</a>  显式锁<br/>
 * Lock 实现提供了比 synchronized 更广泛的锁操作。此实现允许更灵活的结构，可以具有差别很大的属性，可以支持多个相关的 Condition 对象。
 * <p>vs synchronized：
 * <pre>
 *              synchronized                Lock
 * 层面           JVM                         JDK
 * 实现方式     字节码指令               Java 代码（AQS）
 * 锁获取/释放   自动释放                lock() / unlock()
 * 超时尝试         不支持             tryLock(long, TimeUnit)
 * 可中断性     不可中断                lockInterruptibly()
 * 公平性          非公平                 可选，默认非公平
 * 条件队列     1个 wait/notiy       多个 Condition await/signal
 * 悲观锁          阻塞              lock() 阻塞 / tryLock() 非阻塞
 * </pre>
 * 相对于 synchronized 的优点：
 * <pre>
 * 1 Lock 可以不让等待的线程一直无限期地等待下去 (tryLock(long time, TimeUnit unit)，只等待一定时间，lockInterruptibly() 响应中断)
 * 2 Lock 可以让多个线程进行读操作 (ReentrantReadWriteLock)
 * 3 Lock 可以得知线程有没有获取到锁 (ReentrantLock) ?
 * 4 Lock 可以指定公平策略，默认不公平
 * 5 Lock 可以通过 lock.newCondition() 获得该锁的 Condition 对象，再通过 condition.signal() 或 condition.signalAll() 唤醒该 Condition 对象上的等待的线程
 * </pre>
 * <p>AQS：
 *  ①无竞争：纯 CAS 获取
 *  ②被占用：内核态阻塞，避免 CPU 空转
 *  ③非公平锁会多尝试一次 CAS
 * <p>性能：但随着版本的不断优化，synchronized 效率越来越高，一般情况下优先使用 synchronized
 *
 * @author ljh
 * @see <a href="https://www.cnblogs.com/dolphin0520/p/3923167.html">并发编程 Lock</a>
 * @see <a href="https://blog.csdn.net/ns_code/article/details/17487337">Lock 锁和条件变量</a>
 * @since 2020/11/17 19:09
 */
public class LockDemo {

    /**
     * void	        lockInterruptibly()                     如果当前线程未被中断，则获取锁
     */
    @Test
    public void lockInterruptibly() throws InterruptedException {
        Lock lock = new ReentrantLock();
        // 在使用阻塞等待获取锁的方式中，必须在 try 代码块之外，并且在加锁方法与 try 代码块之间没有任何可能抛出异常的方法调用，避免加锁成功后，在 finally 中无法解锁（阿里编程规约）
        lock.lockInterruptibly();
        try {
            System.out.println("do something");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * boolean	    tryLock([long time, TimeUnit unit])     如果锁在给定的等待时间内空闲，并且当前线程未被中断，则获取锁
     */
    @Test
    public void tryLock() throws InterruptedException {
        Lock lock = new ReentrantLock();
        // 在使用尝试机制来获取锁的方式中，进入业务代码块之前，必须先判断当前线程是否持有锁（阿里编程规约）
        boolean isLocked = lock.tryLock(2, TimeUnit.SECONDS);
        if (isLocked) {
            try {
                System.out.println("do something");
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("do something else");
        }
    }
}
