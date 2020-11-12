package knowledge.thread.lock;

import l.demo.Demo;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * ReentrantReadWriteLock
 * 实现与 ReentrantLock 类似语义的 ReadWriteLock
 * 读和写互斥，写和写互斥，读和读不需要互斥
 * https://jdk6.net/util-concurrent-locks/ReentrantReadWriteLock.html
 */
public class ReentrantReadWriteLockDemo extends Demo {
    public static void main(String[] args) {
        final Data data = new Data();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                IntStream.rangeClosed(1, 3).forEach(j -> data.write(new Random().nextInt(30)));
            }, "写" + i + "线程").start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                IntStream.rangeClosed(1, 3).forEach(j -> data.read());
            }, "读" + i + "线程").start();
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
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.data = data;
                p(Thread.currentThread().getName() + " 写入" + this.data);
            } finally {
                w.unlock(); // 释放写锁
            }
        }

        // 读操作，可并发执行
        public void read() {
            r.lock(); // 取到读锁
            try {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                p(Thread.currentThread().getName() + " 读取" + this.data);
            } finally {
                r.unlock(); // 释放读锁
            }
        }
    }

}
