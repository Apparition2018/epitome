package knowledge.线程.lock;

import l.demo.Demo;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock （显式锁）
 * Lock 实现提供了比使用 synchronized 方法和语句可获得的更广泛的锁定操作。此实现允许更灵活的结构，可以具有差别很大的属性，可以支持多个相关的 Condition 对象。
 * https://jdk6.net/util-concurrent-locks/Lock.html
 * <p>
 * 相对于 synchronized 的优点：
 * 1.lock 可以不让等待的线程一直无限期地等待下去 (tryLock(long time, TimeUnit unit) 只等待一定时间，lockInterruptibly() 响应中断)
 * 2.lock 可以让多个线程进行读操作 (ReentrantReadWriteLock)
 * 3.lock 可以得知线程有没有获取到锁 (ReentrantLock)
 * <p>
 * 相对于 synchronized 的其它不同点：
 * 1.synchronized 是一个 Java 关键字，是 Java 的内置特性，是基于 JVM 层面实现的；
 * Lock 是一个 Java 接口，是基于 JDK 层面实现的；
 * 2.synchronized 不需要手动释放锁；Lock 必须手动释放锁，否则可能导致死锁，一般在 finally 中释放锁；
 * 3.在竞争资源非常激烈时，Lock 的性能要远远高于 synchronized
 * <p>
 * ReentrantLock([boolean fair])        创建一个具有给定公平策略的锁
 * void	lock()                          获取锁
 * void	unlock()                        释放锁
 * <p>
 * https://www.cnblogs.com/dolphin0520/p/3923167.html
 */
public class LockDemo extends Demo {

    /**
     * void	        lockInterruptibly()                     如果当前线程未被中断，则获取锁
     */
    @Test
    public void lockInterruptibly() throws InterruptedException {
        Lock lock = new ReentrantLock();
        lock.lockInterruptibly();
        try {
            p("do something");
        } catch (Exception e) {
            e.printStackTrace();
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
        if (lock.tryLock(2, TimeUnit.SECONDS)) {
            try {
                p("do something");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            p("do something else");
        }
    }
}