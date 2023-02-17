package knowledge.concurrent.lock;

import l.demo.Demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/locks/ReentrantLock.html">ReentrantLock</a>
 * <p>一个可重入互斥 Lock 具有与使用 synchronized 方法和语句访问的隐式监视锁相同的基本行为和语义，但具有扩展功能。
 *
 * @author ljh
 * @since 2020/11/17 19:09
 */
public class ReentrantLockDemo extends Demo {

    public static void main(String[] args) {
        ReentrantLockDemo rdlDemo = new ReentrantLockDemo();

        MyThread t1 = new MyThread(rdlDemo, "A");
        MyThread t2 = new MyThread(rdlDemo, "B");
        t1.start();
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt(); // 因为 1s 后线程 t2 还在等待中，所以可以中断

    }

    // ReentrantLock(boolean fair)
    // 根据给定的公平政策创建一个 ReentrantLock 的实例
    private final Lock lock = new ReentrantLock(true);

    public void insert(Thread thread) throws InterruptedException {
        if (lock.tryLock(2, TimeUnit.SECONDS)) {
            try {
                p("time=" + System.currentTimeMillis() + ", 线程 " + thread.getName() + " 得到了锁...");
                sleep(3000, TimeUnit.MILLISECONDS);
            } finally {
                lock.unlock();
                p("线程 " + thread.getName() + " 释放了锁...");
            }
        } else {
            p("线程 " + thread.getName() + " 放弃了对锁的获取...");
        }
    }

    private static class MyThread extends Thread {
        private final ReentrantLockDemo rlDemo;

        public MyThread(ReentrantLockDemo rlDemo, String name) {
            super(name);
            this.rlDemo = rlDemo;
        }

        public void run() {
            try {
                rlDemo.insert(Thread.currentThread());
            } catch (InterruptedException e) {
                p("time=" + System.currentTimeMillis() + ", 线程 " + Thread.currentThread().getName() + " 被中断...");
            }
        }
    }
}
