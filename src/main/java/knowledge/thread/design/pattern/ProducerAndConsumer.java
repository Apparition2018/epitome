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

        BlockingQueue<PCData> queue = new LinkedBlockingQueue<>(CAPACITY);
        
        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        
        ExecutorService pool = Executors.newCachedThreadPool();
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
        private BlockingQueue<PCData> queue;
        private static AtomicInteger count = new AtomicInteger();

        public Producer(BlockingQueue<PCData> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            PCData data;
            Random r = new Random();
            p("start producer id: " + Thread.currentThread().getId());
            try {
                while (isRunning) {
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(SLEEP_TIME / 2));
                    data = new PCData(count.incrementAndGet());
                    p("生产：" + data);
                    if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                        System.err.println("生产失败");
                    }
                }
                p("stop producer id: " + Thread.currentThread().getId());
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
        private BlockingQueue<PCData> queue;

        public Consumer(BlockingQueue<PCData> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            p("start consume id: " + Thread.currentThread().getId());
            Random r = new Random();
            try {
                while (isRunning || queue.size() != 0) {
                    PCData data = queue.take();
                    p("消费：" + data.getData());
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(SLEEP_TIME));
                }
                p("stop consume id: " + Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private static class PCData {
        private final int intData;

        public PCData(int d) {
            intData = d;
        }

        public int getData() {
            return intData;
        }

        @Override
        public String toString() {
            return intData + "";
        }
    }
}
