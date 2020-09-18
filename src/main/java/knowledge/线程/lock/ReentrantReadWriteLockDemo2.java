package knowledge.线程.lock;

import l.demo.Demo;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读和写互斥，写和写互斥，读和读不需要互斥
 */
public class ReentrantReadWriteLockDemo2 extends Demo {
    public static void main(String[] args) {
        final Data data = new Data();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    data.write(new Random().nextInt(30));
                }
            }).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    data.read();
                }
            }).start();
        }
    }

    private static class Data {
        // 共享数据
        private int data;
        // 可重入的读写锁
        private final ReadWriteLock rwl = new ReentrantReadWriteLock();
        // 写锁
        private final Lock w = rwl.writeLock();
        // 读锁
        private final Lock r = rwl.readLock();

        // 写操作，同时只允许一个写操作
        public void write(int data) {
            w.lock(); // 取到写锁
            try {
                p(Thread.currentThread().getName() + "准备写入数据");
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.data = data;
                p(Thread.currentThread().getName() + "写入" + this.data);
            } finally {
                w.unlock(); // 释放写锁
            }
        }

        // 多操作，可并发执行
        public void read() {
            r.lock(); // 取到读锁
            try {
                p(Thread.currentThread().getName() + "准备读取数据");
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                p(Thread.currentThread().getName() + "读取" + this.data);
            } finally {
                r.unlock(); // 释放读锁
            }
        }
    }
}
