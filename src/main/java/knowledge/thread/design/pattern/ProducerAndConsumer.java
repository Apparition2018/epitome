package knowledge.thread.design.pattern;

import l.demo.Demo;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者-消费者 模式
 * 一个系统中，存在生产者和消费者两种角色，他们通过内存缓冲区进行通信，生产者生产消费者需要的资料，消费者把资料做成产品。
 * <p>
 * 多线程设计模式：https://www.cnblogs.com/agilestyle/p/11494963.html
 * 多线程设计模式：https://www.cnblogs.com/chinaifae/p/10271107.html
 * 透彻理解Java并发编程：https://segmentfault.com/blog/ressmix_multithread?page=3
 * 生产者消费者模式-Java实现：https://www.cnblogs.com/chentingk/p/6497107.html
 *
 * @author ljh
 * created on 2020/10/9 13:53
 */
public class ProducerAndConsumer extends Demo {
    private static volatile boolean isRunning = true;
    private static final int SLEEP_TIME = 1000;

    /**
     * 本例基于 LinkedBlockingDeque 实现
     */
    public static void main(String[] args) throws InterruptedException {
        final int CAPACITY = 10;

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(CAPACITY);

        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);

        ExecutorService pool = Executors.newCachedThreadPool(new MyThreadFactory());
        pool.execute(p1);
        pool.execute(p2);
        pool.execute(c1);
        pool.execute(c2);

        TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
        p1.stop();
        p2.stop();
        p("********** 生产者停止生产 **********");

        while (queue.remainingCapacity() != CAPACITY) {
            TimeUnit.MILLISECONDS.sleep(SLEEP_TIME + 300);
        }
        pool.shutdown();
        p("********** 消费者消费完毕 **********");
    }

    private static class Producer implements Runnable {
        private BlockingQueue<Integer> queue;
        private static AtomicInteger goodsId = new AtomicInteger();

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            Random r = new Random();
            String threadName = Thread.currentThread().getName();
            p("start produce thread: " + threadName);
            try {
                while (isRunning) {
                    goodsId.incrementAndGet();
                    if (!queue.offer(goodsId.intValue(), 2, TimeUnit.SECONDS)) {
                        System.err.println("produce error");
                    }
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(SLEEP_TIME / 2));
                    p(threadName + "+" + queue);
                }
                p("stop produce thread: " + threadName);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }

        public void stop() {
            isRunning = false;
        }
    }

    private static class Consumer implements Runnable {
        private BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            p("start consume thread: " + threadName);
            Random r = new Random();
            try {
                while (isRunning || queue.size() != 0) {
                    queue.take();
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(SLEEP_TIME));
                    p(threadName + "-" + queue);
                }
                p("stop consume thread: " + threadName);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private static class MyThreadFactory implements ThreadFactory {

        private final AtomicInteger count = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(map2.get(count.getAndIncrement()));
            return thread;
        }
    }
}
