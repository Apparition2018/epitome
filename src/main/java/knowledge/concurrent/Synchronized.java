package knowledge.concurrent;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * Synchronized     内置锁
 * <p>
 * 同步监视器对象是同一个的时候，这些代码间就具有了互斥效果，可称之为互斥锁
 * <p>
 * 锁膨胀（锁升级）机制：new → 偏向锁 → 轻量级锁（自旋锁，无锁） → 重量级锁
 * https://www.bilibili.com/video/BV1Xi4y1C7ed?p=5
 * <p>
 * 可重入性：同一个线程的外层函数获得锁后，内层函数可以直接获取该锁
 * 不可中断性：当锁被别的线程获得以后，如当前线程想获得，只能等待或堵塞，直到其他线程释放了这个锁
 * <p>
 * Java 内置锁的简单认识：https://www.cnblogs.com/xyhuangjinfu/p/6505329.html
 * Synchronized 的可重入性和不可中断性：https://blog.csdn.net/x541211190/article/details/106397437
 * monitor 机制实现：https://www.cnblogs.com/qingshan-tang/p/12698705.html
 *
 * @author ljh
 * created on 2020/11/17 19:09
 */
public class Synchronized extends Demo {

    /**
     * getBean1() 和 getBean3() 同步监视器锁的对象都是 this，所以实现了同步
     */
    @Test
    public void testThis() throws InterruptedException {
        setCountDownLatch(2);
        Table table = new Table();
        new Thread(table::getBean1).start();
        new Thread(table::getBean3).start();
        countDownLatch.await();
    }

    /**
     * getBean2() 和 getBean4() 同步监视器锁的对象都是 Table.class，所以实现了同步
     */
    @Test
    public void testClass() throws InterruptedException {
        setCountDownLatch(2);
        Table table = new Table();
        new Thread(Table::getBean2).start();
        new Thread(table::getBean4).start();
        countDownLatch.await();
    }

    /**
     * 同步监视器锁的对象分别是 this, Table.class, lock，所以无法实现同步
     */
    @Test
    public void testNotSameObj() throws InterruptedException {
        setCountDownLatch(3);
        Table table = new Table();
        new Thread(table::getBean1).start();
        new Thread(table::getBean4).start();
        new Thread(table::getBean5).start();
        countDownLatch.await();
    }

    static class Table {

        private final Object lock = new Object();

        /**
         * 非静态同步方法
         * 同步监视器锁的对象是当前方法所属对象，即 this
         */
        public synchronized void getBean1() {
            getBean();
        }

        /**
         * 静态同步方法
         * 同步监视器锁的对象是当前类的 class，即 Table.class
         */
        public static synchronized void getBean2() {
            getBean();
        }

        /**
         * 同步块
         * synchronized(同步监视器) { ... }
         * 有效的缩小同步范围可以在保证安全的前提下提高代码并发执行的效率
         * 同步监视器即上锁的对象，可以是 java 中的任意对象，
         */
        public void getBean3() {
            // 同步监视器锁的对象是当前方法所属对象，即 this
            synchronized (this) {
                getBean();
            }
        }

        public void getBean4() {
            // 同步监视器锁的对象是 Table.class
            synchronized (Table.class) {
                getBean();
            }
        }

        public void getBean5() {
            // 同步监视器锁的对象是 lock
            synchronized (lock) {
                getBean();
            }
        }

        private static void getBean() {
            try {
                p(Thread.currentThread().getName() + " start");
                TimeUnit.MILLISECONDS.sleep(200);
                p(Thread.currentThread().getName() + " end");
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
