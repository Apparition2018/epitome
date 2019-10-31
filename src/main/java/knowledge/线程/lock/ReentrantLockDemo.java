package knowledge.线程.lock;

import org.apache.commons.lang3.time.DateUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 *
 *
 */
public class ReentrantLockDemo {

    private Lock lock = new ReentrantLock();

    public void insert(Thread thread) throws InterruptedException {
        if (lock.tryLock(2, TimeUnit.SECONDS)) {
            try {
                System.out.println("time=" + System.currentTimeMillis() + ", 线程 " + thread.getName() + " 得到了锁...");
                long now = System.currentTimeMillis();
                while (System.currentTimeMillis() - now < 3000); // 为了避免 Thread.sleep() 而需要捕获 InterruptedException 而带来的理解上的困惑
            } finally {
                lock.unlock();
                System.out.println("线程 " + thread.getName() + " 释放了锁...");
            }
        } else {
            System.out.println("线程 " + thread.getName() + " 放弃了对锁的获取...");
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo rdlDemo = new ReentrantLockDemo();

        MyThread t1 = new MyThread(rdlDemo, "A");
        MyThread t2 = new MyThread(rdlDemo, "B");
        t1.start();
        t2.start();

        try {
            Thread.sleep(DateUtils.MILLIS_PER_SECOND);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt(); // 因为 1s 后线程 t2 还在等待中，所以可以中断

    }

}

class MyThread extends Thread {
    private ReentrantLockDemo rlDemo;

    MyThread(ReentrantLockDemo rlDemo, String name) {
        super(name);
        this.rlDemo = rlDemo;
    }

    public void run() {
        try {
            rlDemo.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println("time=" + System.currentTimeMillis() + ", 线程 " + Thread.currentThread().getName() + " 被中断...");
        }
    }
}