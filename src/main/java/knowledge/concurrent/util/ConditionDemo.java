package knowledge.concurrent.util;

import l.demo.Demo;
import org.apache.commons.lang3.BooleanUtils;

import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition
 * <pre>
 * <a href="https://www.cnblogs.com/gemine/p/9039012.html">Java 并发之 Condition</a>
 * <a href="https://blog.csdn.net/a718515028/article/details/108224749">Condition 实现生产者-消费者模式</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/11/15 11:30
 */
public class ConditionDemo extends Demo {

    private static volatile boolean isProducing = true;
    private static final boolean[] isAllConsumerStop = new boolean[]{false, false};
    private static final long SLEEP_TIME = TimeUnit.SECONDS.toMillis(1);
    private static final int MAX = 10;

    private static final ReentrantLock ADD_LOCK = new ReentrantLock();
    // Condition	    newCondition()
    // 返回绑定到此 Lock 实例的新 Condition 实例
    private static final Condition ADD_CON = ADD_LOCK.newCondition();
    private static final ReentrantLock POLL_LOCK = new ReentrantLock();
    private static final Condition POLL_CON = POLL_LOCK.newCondition();

    /**
     * 参考 {@link LinkedBlockingQueue} 源码实现 生产者-消费者 模式
     */
    public static void main(String[] args) throws InterruptedException {
        LinkedList<Integer> list = new LinkedList<>();

        Producer p1 = new Producer(list);
        Producer p2 = new Producer(list);
        Consumer c1 = new Consumer(list, 0);
        Consumer c2 = new Consumer(list, 1);

        ExecutorService threadPool = Executors.newCachedThreadPool(new MyThreadFactory());
        threadPool.execute(p1);
        threadPool.execute(p2);
        threadPool.execute(c1);
        threadPool.execute(c2);

        TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
        isProducing = false;
        p("********** 生产者停止生产 **********");

        while (!BooleanUtils.and(isAllConsumerStop)) {
            TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
        }
        threadPool.shutdown();
        p("********** 消费者消费完毕 **********");
    }

    private static class Producer implements Runnable {
        private static final AtomicInteger goodsId = new AtomicInteger();
        private final LinkedList<Integer> list;

        public Producer(LinkedList<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            p(threadName + " + start");
            ThreadLocalRandom r = ThreadLocalRandom.current();
            try {
                while (isProducing) {
                    TimeUnit.MILLISECONDS.sleep(r.nextLong(SLEEP_TIME / 2));
                    ADD_LOCK.lock();
                    try {
                        while (MAX == list.size()) {
                            // void	        await()
                            // 造成当前线程在接到信号或被中断之前一直处于等待状态
                            ADD_CON.await();
                        }
                        list.addFirst(goodsId.incrementAndGet());
                        p(threadName + " + " + goodsId);
                        if (list.size() + 1 < MAX)
                            // void	        signal()
                            // 唤醒所有等待线程
                            ADD_CON.signalAll();
                    } finally {
                        ADD_LOCK.unlock();
                    }
                }
                p(threadName + " + end");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private static class Consumer implements Runnable {
        private final LinkedList<Integer> list;
        private final int consumerIndex;

        public Consumer(LinkedList<Integer> list, int consumerIndex) {
            this.list = list;
            this.consumerIndex = consumerIndex;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            p(threadName + " - start");
            ThreadLocalRandom r = ThreadLocalRandom.current();
            try {
                while (isProducing || list.size() != 0) {
                    POLL_LOCK.lock();
                    try {
                        while (list.size() == 0) {
                            // boolean	    await(long time, TimeUnit unit)
                            // 造成当前线程在接到信号、被中断或到达指定等待时间之前一直处于等待状态
                            POLL_CON.await(r.nextLong(SLEEP_TIME / 4), TimeUnit.MILLISECONDS);
                        }
                        p(threadName + " - " + list.pollLast());
                        if (list.size() > 1) POLL_CON.signalAll();
                    } finally {
                        POLL_LOCK.unlock();
                    }
                    TimeUnit.MILLISECONDS.sleep(r.nextLong(SLEEP_TIME));
                }
                isAllConsumerStop[consumerIndex] = true;
                p(threadName + " - end");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
