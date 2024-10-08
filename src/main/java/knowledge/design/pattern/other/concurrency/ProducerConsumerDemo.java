package knowledge.design.pattern.other.concurrency;

import l.demo.Demo;
import org.apache.commons.lang3.BooleanUtils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者-消费者 模式
 * <p>一个系统中，存在生产者和消费者两种角色，他们通过内存缓冲区进行通信，生产者生产消费者需要的资料，消费者把资料做成产品。
 *
 * @author ljh
 * @see <a href="https://www.cnblogs.com/agilestyle/p/11494963.html">多线程设计模式</a>
 * @see <a href="https://www.cnblogs.com/chinaifae/p/10271107.html">多线程设计模式</a>
 * @see <a href="https://www.cnblogs.com/chentingk/p/6497107.html">生产者消费者模式</a>
 * @since 2020/10/9 13:53
 */
public class ProducerConsumerDemo extends Demo {
    private static volatile boolean isProducing = true;
    private static final boolean[] isAllConsumerStop = new boolean[]{false, false};
    private static final long SLEEP_TIME = TimeUnit.SECONDS.toMillis(1);

    /**
     * 本例基于 LinkedBlockingQueue 实现
     */
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);
        Consumer c1 = new Consumer(queue, 0);
        Consumer c2 = new Consumer(queue, 1);

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

    private record Producer(BlockingQueue<Integer> queue) implements Runnable {
        private static final AtomicInteger goodsId = new AtomicInteger();

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            p(threadName + " + start");
            ThreadLocalRandom r = ThreadLocalRandom.current();
            try {
                while (isProducing) {
                    TimeUnit.MILLISECONDS.sleep(r.nextLong(SLEEP_TIME / 2));
                    queue.put(goodsId.incrementAndGet());
                    p(threadName + " + " + goodsId);
                }
                p(threadName + " + end");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private record Consumer(BlockingQueue<Integer> queue, int consumerIndex) implements Runnable {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            p(threadName + " - start");
            ThreadLocalRandom r = ThreadLocalRandom.current();
            try {
                while (isProducing || queue.size() != 0) {
                    Integer goodsId = queue.poll(r.nextLong(SLEEP_TIME), TimeUnit.MILLISECONDS);
                    if (null != goodsId) {
                        p(threadName + " - " + goodsId);
                        TimeUnit.MILLISECONDS.sleep(r.nextLong(SLEEP_TIME));
                    }
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
