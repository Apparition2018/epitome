package knowledge.thread.lock;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition
 * Java 并发之 Condition：https://www.cnblogs.com/gemine/p/9039012.html
 *
 * @author Arsenal
 * created on 2020/11/15 11:30
 */
public class ConditionDemo {

    /**
     * Condition 实现 生产者-消费者 模式
     * https://blog.csdn.net/a718515028/article/details/108224749
     */
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Condition putCondition = lock.newCondition();
        Condition takeCondition = lock.newCondition();

        AtomicInteger integer = new AtomicInteger(0);

        ConditionConsumer2 conditionConsumer2 = new ConditionConsumer2(lock, putCondition, takeCondition, integer);
        ConditionProducer2 conditionProducer2 = new ConditionProducer2(lock, putCondition, takeCondition, integer);

        new Thread(conditionConsumer2).start();
//        new Thread(conditionConsumer2).start();
//
//        new Thread(conditionProducer2).start();
        new Thread(conditionProducer2).start();


    }

    private static class ConditionProducer2 implements Runnable {

        private Lock lock;
        // 生产者condition
        private Condition putCondition;
        // 消费者condition
        private Condition takeCondition;

        // 假设一个池子
        private AtomicInteger count;

        public ConditionProducer2(Lock lock, Condition putCondition, Condition takeCondition, AtomicInteger integer) {
            this.lock = lock;
            this.putCondition = putCondition;
            this.takeCondition = takeCondition;
            this.count = integer;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    while (count.intValue() >= 10) { // 池子小于最大值（这里设置10，自定义）
                        // 池子满了阻塞
                        System.out.println("池子满了阻塞，等待消费。。。。。。");
                        putCondition.await();
//                    Thread.sleep(500);
                    }
                    count.incrementAndGet();
                    System.out.println("池子生产了 count=" + count);
                    takeCondition.signal(); // 唤醒消费者线程
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private static class ConditionConsumer2 implements Runnable {

        private Lock lock;
        // 生产者condition
        private Condition putCondition;
        // 消费者condition
        private Condition takeCondition;

        // 假设一个池子
        private AtomicInteger count;

        public ConditionConsumer2(Lock lock, Condition putCondition, Condition takeCondition, AtomicInteger integer) {
            this.lock = lock;
            this.putCondition = putCondition;
            this.takeCondition = takeCondition;
            this.count = integer;
        }

        @Override
        public void run() {
            while (true) {
                try {

                    lock.lock();
                    while (count.intValue() <= 0) { // 池子不为空
                        // 池子为空 阻塞
                        System.out.println("池子空了,等待生产count=" + count);
                        takeCondition.await();
//                    Thread.sleep(500);
                    }
                    System.out.println("开始消费 count=" + count);
                    count.decrementAndGet();
                    putCondition.signal();// 唤醒生产者可以生产
//                Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }
    }

}
