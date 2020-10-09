package knowledge.线程;

import l.demo.Demo;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者-消费者 模式
 * <p>
 * https://www.cnblogs.com/chentingk/p/6497107.html
 *
 * @author ljh
 * created on 2020/10/9 13:53
 */
public class ProducerAndConsumerPattern extends Demo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<PCData> queue = new LinkedBlockingDeque<>(10);
        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(p1);
        pool.execute(p2);
        pool.execute(c1);
        pool.execute(c2);
        TimeUnit.SECONDS.sleep(10);
        p1.stop();
        p2.stop();
        TimeUnit.SECONDS.sleep(3);
        pool.shutdown();
        
    }

    private static class Producer implements Runnable {

        private volatile boolean isRunning = true;
        private BlockingQueue<PCData> queue;
        private static AtomicInteger count = new AtomicInteger();
        private static final int SLEEP_TIME = 1000;

        public Producer(BlockingQueue<PCData> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            PCData data;
            Random r = new Random();
            p("start produce id:" + Thread.currentThread().getId());
            try {
                while (isRunning) {
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(SLEEP_TIME));
                    data = new PCData(count.incrementAndGet());
                    p(data + "加入队列");
                    if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                        System.err.println("加入队列失败");
                    }
                }
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
        private static final int SLEEP_TIME = 1000;

        public Consumer(BlockingQueue<PCData> queue) {
            this.queue = queue;
        }

        @Override
        @SuppressWarnings("InfiniteLoopStatement")
        public void run() {
            p("start Consume id:" + Thread.currentThread().getId());
            Random r = new Random();
            try {
                while (true) {
                    PCData data = queue.take();
                    int re = data.getData() * data.getData();
                    p(MessageFormat.format("{0}*{1}={2}", data.getData(), data.getData(), re));
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(SLEEP_TIME));
                }
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
