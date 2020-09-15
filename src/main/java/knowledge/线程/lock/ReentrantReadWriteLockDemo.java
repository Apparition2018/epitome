package knowledge.线程.lock;

import l.demo.Demo;
import org.apache.commons.lang3.time.DateUtils;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock
 * 支持与 ReentrantLock 类似语义的 ReadWriteLock 实现
 * https://jdk6.net/util-concurrent-locks/ReentrantReadWriteLock.html
 */
public class ReentrantReadWriteLockDemo extends Demo {

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void get(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            p("线程 " + thread.getName() + " 开始读操作...");
            int count = 0;
            while (System.currentTimeMillis() - start <= DateUtils.MILLIS_PER_SECOND) {
                if (++count % 100 == 0) {
                    p("线程 " + thread.getName() + " 正在进行读操作...");
                }
            }
            p("线程 " + thread.getName() + " 读操作完毕...");
        } finally {
            rwl.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockDemo rwlDemo = new ReentrantReadWriteLockDemo();

        new Thread(() -> rwlDemo.get(Thread.currentThread()), "A").start();
        new Thread(() -> rwlDemo.get(Thread.currentThread()), "B").start();
    }

}
