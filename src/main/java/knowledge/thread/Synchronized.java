package knowledge.thread;

import l.demo.Demo;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Synchronized     内置锁
 * 同步监视器对象是同一个的时候，这些代码间就具有了互斥效果，可称之为互斥锁
 * <p>
 * Java内置锁的简单认识：https://www.cnblogs.com/xyhuangjinfu/p/6505329.html
 * 并发编程中实现内存可见的两种方法比较：https://blog.csdn.net/u012426327/article/details/77469733
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

    private static class Table {

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