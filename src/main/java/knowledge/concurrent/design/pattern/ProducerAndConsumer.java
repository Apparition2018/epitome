package knowledge.concurrent.design.pattern;

import l.demo.Demo;
import org.apache.commons.lang3.BooleanUtils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者-消费者 模式
 * 一个系统中，存在生产者和消费者两种角色，他们通过内存缓冲区进行通信，生产者生产消费者需要的资料，消费者把资料做成产品。
 * <p>
 * 多线程设计模式：https://www.cnblogs.com/agilestyle/p/11494963.html
 * 多线程设计模式：https://www.cnblogs.com/chinaifae/p/10271107.html
 * 生产者消费者模式-Java实现：https://www.cnblogs.com/chentingk/p/6497107.html
 *
 * @author ljh
 * created on 2020/10/9 13:53
 */
public class ProducerAndConsumer extends Demo {
    private static volatile boolean isProducing = true;
    private static volatile boolean[] isAllConsumerStop = new boolean[]{false, false};
    private static final int SLEEP_TIME = 1000;

    /**
     * 本例基于 LinkedBlockingQueue 实现
     */
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);
        Consumer c1 = new Consumer(queue, 0);
        Consumer c2 = new Consumer(queue, 1);

        ExecutorService pool = Executors.newCachedThreadPool(new MyThreadFactory());
        pool.execute(p1);
        pool.execute(p2);
        pool.execute(c1);
        pool.execute(c2);

        TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
        isProducing = false;
        p("********** 生产者停止生产 **********");

        while (!BooleanUtils.and(isAllConsumerStop)) {
            TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
        }
        pool.shutdown();
        p("********** 消费者消费完毕 **********");
    }

    private static class Producer implements Runnable {
        private static AtomicInteger goodsId = new AtomicInteger();
        private BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            p(threadName + " + start");
            ThreadLocalRandom r = ThreadLocalRandom.current();
            try {
                while (isProducing) {
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(SLEEP_TIME / 2));
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

    private static class Consumer implements Runnable {
        private BlockingQueue<Integer> queue;
        private int consumerIndex;

        public Consumer(BlockingQueue<Integer> queue, int consumerIndex) {
            this.queue = queue;
            this.consumerIndex = consumerIndex;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            p(threadName + " - start");
            ThreadLocalRandom r = ThreadLocalRandom.current();
            try {
                while (isProducing || queue.size() != 0) {
                    Integer goodsId = queue.poll(r.nextInt(SLEEP_TIME), TimeUnit.MILLISECONDS);
                    if (null != goodsId) {
                        p(threadName + " - " + goodsId);
                        TimeUnit.MILLISECONDS.sleep(r.nextInt(SLEEP_TIME));
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
