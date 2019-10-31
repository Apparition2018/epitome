package knowledge.线程.lock;

import org.apache.commons.lang3.time.DateUtils;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock
 */
public class ReentrantReadWriteLockDemo {

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void get(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            System.out.println("线程 " + thread.getName() + " 开始读操作...");
            int count = 0;
            while (System.currentTimeMillis() - start <= DateUtils.MILLIS_PER_SECOND) {
                if (++count % 100 == 0) {
                    System.out.println("线程 " + thread.getName() + " 正在进行读操作...");
                }
            }
            System.out.println("线程 " + thread.getName() + " 读操作完毕...");
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
