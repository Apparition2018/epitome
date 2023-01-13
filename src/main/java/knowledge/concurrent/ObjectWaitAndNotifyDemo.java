package knowledge.concurrent;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * void	    wait(long timeout, int nanos)   在其他线程调用此对象的 notify() 方法或 notifyAll() 方法，或者其他某个线程中断当前线程，或者已超过某个实际时间量前，导致当前线程等待
 * void	    notify()                        唤醒在此对象监视器上等待的单个线程
 * void	    notifyAll()                     唤醒在此对象监视器上等待的所有线程
 * </pre>
 * <p>锁池：Monitor，多个线程争夺某个对象的锁的拥有权，没有争夺成功的线程就处于对象的锁池中，锁池中的线程会去争夺锁的拥有权
 * <p>等待池：WaitSet，线程调用了对象的 wait()，那么线程就处于该对象的等待池中，等待池中的线程不会去争夺锁的拥有权
 * <p>参考：<a href="https://blog.csdn.net/u012426327/article/details/77160444">wait & notify</a>
 *
 * @author ljh
 * @since 2020/11/17 19:09
 */
public class ObjectWaitAndNotifyDemo {
    public static void main(String[] args) {
        Target t = new Target();

        Thread t1 = new Increase(t);
        t1.setName("Increase");
        Thread t2 = new Decrease(t);
        t2.setName("Decrease");

        t1.start();
        t2.start();
    }

    static class Target {
        private int count;

        public synchronized void increase() {
            if (count == 2) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count++;
            System.out.println(Thread.currentThread().getName() + ":" + count);
            notify();
        }

        public synchronized void decrease() {
            if (count == 0) {
                try {
                    // 等待，由于 Decrease 线程调用的该方法,
                    // 所以 Decrease 线程进入对象 t (main函数中实例化的)的等待池，并且释放对象 t 的锁
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count--;
            System.out.println(Thread.currentThread().getName() + ":" + count);

            // 唤醒线程 Increase, Increase 线程从等待池到锁池
            notify();
        }
    }

    static class Increase extends Thread {
        private final Target t;

        Increase(Target t) {
            this.t = t;
        }

        @Override
        public void run() {
            for (int i = 0; i < 30; i++) {
                try {
                    // 随机睡眠[0~500)毫秒
                    // sleep() 不会释放对象 t 的锁
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                t.increase();
            }
        }
    }

    static class Decrease extends Thread {
        private final Target t;

        Decrease(Target t) {
            this.t = t;
        }

        @Override
        public void run() {
            for (int i = 0; i < 30; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                t.decrease();
            }
        }
    }
}
