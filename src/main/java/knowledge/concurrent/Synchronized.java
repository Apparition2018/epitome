package knowledge.concurrent;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * Synchronized     内置锁/同步锁
 * <p>
 * 同步监视器对象是同一个的时候，这些代码间就具有了互斥效果，可称之为互斥锁
 * <p>
 * 锁膨胀/锁升级：new → 偏向锁 → 轻量级锁（自旋锁） → 重量级锁
 * https://www.bilibili.com/video/BV1Xi4y1C7ed?p=5
 * <p>
 * 锁降级：发生在 GC 的 STW (stop the world) 阶段
 * https://www.zhihu.com/question/63859501/answer/214026841
 * STW：https://blog.csdn.net/l2470334493/article/details/108168099
 * <p>
 * 锁消除：JIT 编译器在编译时进行逃逸分析，分析被锁对象是否不存被其它线程共享和竞争的可能性，如果是则可以消除该锁
 * 锁粗化：JIT 检测到同一个对象进行了连续的加锁和解锁操作，会将这些锁合并成一个锁
 * https://blog.csdn.net/piaodinghuo4319/article/details/121137521
 * <p>
 * 可重入性：同一个线程的外层函数获得锁后，内层函数可以直接获取该锁
 * 不可中断性：当锁被别的线程获得以后，如当前线程想获得，只能等待或堵塞，直到其他线程释放了这个锁
 * https://blog.csdn.net/x541211190/article/details/106397437
 * <p>
 * synchronized 介绍与用法：
 * https://www.cnblogs.com/jinggod/p/8490651.html
 * https://www.cnblogs.com/jinggod/p/8491029.html
 * https://www.cnblogs.com/jinggod/p/8491074.html
 * monitor 机制实现：https://www.cnblogs.com/qingshan-tang/p/12698705.html
 *
 * @author ljh
 * created on 2020/11/17 19:09
 */
public class Synchronized extends Demo {

    public static class SynchronizedDemo1 {

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

    public static class SynchronizedDemo2 {

        /**
         * 锁消除
         */
        @Test
        public void testLockEliminate() {
            StringBuffer sb = new StringBuffer();
            // StringBuffer 的 append() 被 Synchronized 修饰，但这里 sb 不可能被其它线程引用，所以 JVM 会进行锁消除
            p(sb.append("a").append("b"));
        }

        private final StringBuffer sb = new StringBuffer();
        /**
         * 锁粗化
         */
        @Test
        public void testLockCoarsening() {
            for (int i = 0; i < 10; i++) {
                // StringBuffer 的 append() 被 Synchronized 修饰，因为这里 sb 进行了多次连续加锁，所以 JVM 会进行锁粗化
                sb.append(i);
            }
            p(sb);
        }
    }

}
