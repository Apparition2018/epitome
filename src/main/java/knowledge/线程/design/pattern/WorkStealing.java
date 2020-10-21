package knowledge.线程.design.pattern;

import l.demo.Demo;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 工作密取
 * <p>
 * 在 生产者-消费者 模式中，所有消费者都从一个工作队列中取元素，一般使用阻塞队列实现；
 * 而在 工作密取 模式中，每个消费者有其单独的工作队列，一般使用双端队列实现，
 * 如果一个消费者完成了自己队列中的全部工作，那么它可以从其它消费者队列末尾秘密地获取工作。
 * <p>
 * 工作密取 ，模式 对比 生产者-消费者 模式，更为灵活，因为多个线程不会因为在同一个工作队列中抢占内容发生竞争。
 * 在大多数时候，它们只是访问自己的双端队列。即使需要访问另一个队列时，也是从队列的尾部获取工作，降低了队列上的竞争程度。
 * 工作密取非常适用于即是消费者也是生产者的问题，当执行某个工作时可能导致出现更多的工作。
 * <p>
 * https://img-blog.csdnimg.cn/20190602161352675.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xhdm9yYW5nZQ==,size_16,color_FFFFFF,t_70
 * https://blog.csdn.net/hxpjava1/article/details/44245593
 * https://blog.csdn.net/ryo1060732496/article/details/88889886
 *
 * @author ljh
 * created on 2020/10/19 16:17
 */
public class WorkStealing extends Demo {

    private static class Work implements Runnable {
        private static final Object object = new Object();
        private static int count = 0;
        public final int id;
        private long putThread;

        public Work() {
            synchronized (object) {
                id = count++;
            }
        }

        @Override
        public void run() {
            if (Thread.currentThread().getId() != putThread) {
                p(Thread.currentThread().getId() + ":" + putThread + "// finish job " + id);
            }
        }

        public long getPutThread() {
            return putThread;
        }

        public void setPutThread(long putThread) {
            this.putThread = putThread;
        }
    }

    public static Work generateWork() {
        return new Work();
    }

    private static class ConsumerAndProducer implements Runnable {
        private Random random = new Random();
        private final LinkedBlockingDeque<Work> deque1;
        private final LinkedBlockingDeque<Work> deque2;

        public ConsumerAndProducer(LinkedBlockingDeque<Work> deque1, LinkedBlockingDeque<Work> deque2) {
            this.deque1 = deque1;
            this.deque2 = deque2;
        }

        @Override
        public void run() {
            while(!Thread.interrupted()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                    if (random.nextBoolean()) {
                        int count = random.nextInt(5);
                        for (int i = 0; i < count; i++) {
                            Work work = generateWork();
                            work.setPutThread(Thread.currentThread().getId());
                            deque1.putLast(work);
                        }
                    }
                    if (deque1.isEmpty()) {
                        if (!deque2.isEmpty()) {
                            deque2.takeLast().run();
                        }
                    } else {
                        deque1.takeFirst().run();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        LinkedBlockingDeque<Work> deque1 = new LinkedBlockingDeque<>();
        LinkedBlockingDeque<Work> deque2 = new LinkedBlockingDeque<>();
        
        new Thread(new ConsumerAndProducer(deque1, deque2)).start();
        new Thread(new ConsumerAndProducer(deque1, deque2)).start();
        
        new Thread(new ConsumerAndProducer(deque2, deque1)).start();
        new Thread(new ConsumerAndProducer(deque2, deque1)).start();
        
    }
}
