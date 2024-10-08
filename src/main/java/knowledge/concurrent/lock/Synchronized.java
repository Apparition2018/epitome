package knowledge.concurrent.lock;

import l.demo.Demo;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * Synchronized     内置锁/同步锁<br/>
 * 多个不同线程的代码片段的同步监视器对象是同一个的时候，这些代码就只能同步执行
 * <p>释放锁：
 * <pre>
 * 1 代码执行完毕
 * 2 异常自动释放
 * </pre>
 * <a href="https://www.bilibili.com/video/BV1Xi4y1C7ed?p=5">锁升级</a>/锁膨胀：
 * <pre>
 * 1 无锁
 * 2 偏向锁：Mark Word 记录的线程 ID
 * 3 轻量级锁（自旋锁）：CAS 将 Mark Word 指向自己线程栈的 Lock Record
 * 4 重量级锁：通过 monitor 实现，依赖操作系统的 mutex 指令，需要从用户态会切换到内核态
 *
 * <a href="https://www.cnblogs.com/qingshan-tang/p/12698705.html">monitor 机制实现</a>
 * <a href="https://www.csdn.net/tags/Ntjacg1sOTAwOTYtYmxvZwO0O0OO0O0O.html">用户态和内核态</a>
 * </pre>
 * <a href="https://www.zhihu.com/question/63859501/answer/214026841">锁降级</a>：发生在 GC 的 <a href="https://blog.csdn.net/l2470334493/article/details/108168099">STW (Stop The World)</a> 阶段
 *
 * @author ljh
 * @see <a href="https://www.zhihu.com/question/485107493">synchronized 原理是什么</a>
 * @see <a href="https://www.imooc.com/learn/1086">synchronized 深度解析</a>
 * @see <a href="https://www.imooc.com/video/23603">查看 monitor 指令</a>
 * @since 2020/11/17 19:09
 */
public class Synchronized extends Demo {

    @Nested
    class SynchronizedDemo1 {

        private Table table = new Table();

        /** 同步监视器锁的对象都是 this，所以实现了同步 */
        @Test
        public void testThis() throws InterruptedException {
            setCountDownLatch(2);
            table = new Table();
            new Thread(table::getBean1).start();
            new Thread(table::getBean3).start();
            countDownLatch.await();
        }

        /** 同步监视器锁的对象都是 Table.class，所以实现了同步 */
        @Test
        public void testClass() throws InterruptedException {
            setCountDownLatch(2);
            table = new Table();
            new Thread(Table::getBean2).start();
            new Thread(table::getBean4).start();
            countDownLatch.await();
        }

        /** 同步监视器锁的对象各不相同，所以无法实现同步 */
        @Test
        public void testNotSameObj() throws InterruptedException {
            setCountDownLatch(3);
            table = new Table();
            new Thread(table::getBean1).start();
            new Thread(table::getBean4).start();
            new Thread(table::getBean5).start();
            countDownLatch.await();
        }

        private static class Table {

            private final Object lock = new Object();

            /**
             * 非静态同步方法<br/>
             * 同步监视器锁的对象是当前方法所属对象，即 this
             */
            private synchronized void getBean1() {
                getBean();
            }

            /**
             * 静态同步方法<br/>
             * 同步监视器锁的对象是当前类的 class，即 Table.class
             */
            private static synchronized void getBean2() {
                getBean();
            }

            /**
             * 同步块
             * <pre>
             * synchronized(同步监视器) { ... }
             * 有效的缩小同步范围可以在保证安全的前提下提高代码并发执行的效率
             * 同步监视器即上锁的对象，可以是 java 中的任意对象，
             * </pre>
             */
            private void getBean3() {
                // 同步监视器锁的对象是当前方法所属对象，即 this
                synchronized (this) {
                    getBean();
                }
            }

            private void getBean4() {
                // 同步监视器锁的对象是 Table.class
                synchronized (Table.class) {
                    getBean();
                }
            }

            private void getBean5() {
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
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /** <a href="https://blog.csdn.net/piaodinghuo4319/article/details/121137521">锁消除、锁粗化</a> */
    static class SynchronizedDemo2 {

        /** 锁消除；JIT 编译器在编译时进行逃逸分析，分析被锁对象是否不存被其它线程共享和竞争的可能性，如果是则可以消除该锁 */
        @Test
        public void testLockEliminate() {
            StringBuffer sb = new StringBuffer();
            // StringBuffer 的 append() 被 Synchronized 修饰，但这里 sb 不可能被其它线程引用，所以 JVM 会进行锁消除
            p(sb.append("a").append("b"));
        }

        private final StringBuffer sb = new StringBuffer();

        /** 锁粗化：JIT 检测到同一个对象进行了连续的加锁和解锁操作，会将这些锁合并成一个锁 */
        @Test
        public void testLockCoarsening() {
            for (int i = 0; i < 10; i++) {
                // StringBuffer 的 append() 被 Synchronized 修饰，因为这里 sb 进行了多次连续加锁，所以 JVM 会进行锁粗化
                sb.append(i);
            }
            p(sb);
        }
    }

    /**
     * <a href="https://blog.csdn.net/x541211190/article/details/106397437">可重入&不可中断</a>
     * <p>不可中断：当锁被别的线程获得后，如当前线程想获得该锁，只能等待或阻塞，直到其他线程释放这个锁
     */
    static class SynchronizedDemo3 {


        /**
         * 可重入：同一个线程的外层函数获得锁后，内层函数无需等待外层函数释放锁即可使用该锁；<br/>
         * 避免了死锁，避免了频繁加解锁
         */
        @Test
        public void testReentrant() {
            this.test1();
        }

        private synchronized void test1() {
            p("test1 start");
            test2();
            p("test1 end");
        }

        private synchronized void test2() {
            p("test2 start");
            p("test2 end");
        }
    }
}
