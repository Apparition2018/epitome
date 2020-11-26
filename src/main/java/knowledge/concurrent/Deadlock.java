package knowledge.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 死锁
 * 指两个或两个以上的进程在执行过程中，由于竞争资源或者由于彼此通信而造成的一种阻塞的现象，若无外力作用，它们都将无法推进下去
 * 四个必要条件：
 * 1.互斥，即当资源被一个线程使用（占有）时，别的线程不能使用；
 * 2.请求和保持，即当资源请求者在请求其它资源的同事保持对原有资源的占有；
 * 3.不可抢占，资源请求者不能强制从资源占有者手中夺取资源，资源只能由资源占有者主动释放；
 * 4.循环等待，即存在一个等待序列：p1 占有 p2 的资源，p2 占有 p3 的资源，p3 占有 p1的资源，形成一个等待环路
 * 解决死锁：
 * 1.synchronized
 * 2.Lock
 * 防止死锁：
 * 1.避免一个线程同时获取多个锁
 * 2.避免一个线程在锁内同时占用多个资源，尽量保证每个锁只占用一个资源
 * 3.尝试使用定时锁 lock.tryLock
 * 避免死锁的技术：
 * 1.加锁顺序：当多个线程需要相同的一些锁，确保所有的线程都是按照相同的顺序获得锁
 * 2.加锁时限：线程尝试获取锁的时候加上时间限制，超过时限则放弃对该锁的请求，并释放已占有的资源
 * 3.死锁检测
 * 解除死锁：
 * 1.撤销进程：撤销一个或多个死锁，知道打破循环等待
 * 2.进程回退：让一个或多个死锁进程回退到死锁前的状态
 * 3.资源剥夺：挂起某些死锁进程，并抢占它的资源，将这些资源分配给其它的死锁进程
 *
 * @author ljh
 * created on 2020/11/17 19:09
 */
public class Deadlock {

    private static final Object A = new Object();
    private static final Object B = new Object();

    public static void main(String[] args) {
        deadlock();
    }

    private static void deadlock() {
        new Thread(() -> {
            synchronized (A) {
                System.out.println("线程 t1 拿到 A 锁");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("线程 t1 拿到 B 锁");
                }

            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (B) {
                System.out.println("线程 t2 拿到 B 锁");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    System.out.println("线程 t2 拿到 A 锁");
                }

            }
        }, "t2").start();
    }

}
