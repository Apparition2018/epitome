package knowledge.线程.lock;

import l.demo.Demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 * 一个可重入互斥 Lock 具有与使用 synchronized 方法和语句访问的隐式监视锁相同的基本行为和语义，但具有扩展功能。
 * https://www.matools.com/file/manual/jdk_api_1.8_google/java/util/concurrent/locks/ReentrantLock.html
 */
public class ReentrantLockDemo extends Demo {

    private Lock lock = new ReentrantLock();

    public void insert(Thread thread) throws InterruptedException {
        if (lock.tryLock(2, TimeUnit.SECONDS)) {
            try {
                p("time=" + System.currentTimeMillis() + ", 线程 " + thread.getName() + " 得到了锁...");
                long now = System.currentTimeMillis();
                while (System.currentTimeMillis() - now < 3000)
                    ; // 为了避免 Thread.sleep() 而需要捕获 InterruptedException 而带来的理解上的困惑
            } finally {
                lock.unlock();
                p("线程 " + thread.getName() + " 释放了锁...");
            }
        } else {
            p("线程 " + thread.getName() + " 放弃了对锁的获取...");
        }
    }

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

    private static class MyThread extends Thread {
        private ReentrantLockDemo rlDemo;

        MyThread(ReentrantLockDemo rlDemo, String name) {
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