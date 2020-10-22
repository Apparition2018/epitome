package knowledge.线程.design.pattern;

import l.demo.Demo;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

    /**
     * 本例基于 LinkedBlockingDeque 实现
     */
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<Work> deque1 = new LinkedBlockingDeque<>();
        LinkedBlockingDeque<Work> deque2 = new LinkedBlockingDeque<>();

        ProducerAndConsumer pc1 = new ProducerAndConsumer(deque1, deque2);
        ProducerAndConsumer pc2 = new ProducerAndConsumer(deque2, deque1);

        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(pc1);
        pool.execute(pc2);

        TimeUnit.MILLISECONDS.sleep(1000);
        pc1.stopProduce();
        pc2.stopProduce();
        p("********** 停止分派工作 **********");

        while (deque1.size() != 0 && deque2.size() != 0) {
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        pool.shutdown();
        p("********** 工作全部完成 **********");

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.getAllThreadIds();
        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId()+": "+threadInfo.getThreadName());
        }
    }

    @Getter
    @Setter
    private static class Work implements Runnable {
        private long assignId;
        private final int jobId;

        public Work(int jobId) {
            this.jobId = jobId;
        }

        @SneakyThrows
        @Override
        public void run() {
            TimeUnit.MILLISECONDS.sleep(100);
            long finishId = Thread.currentThread().getId();
            p("[" + finishId + "] finish job " + jobId + " assigned by " + (finishId == assignId ? "itself" : "others"));
        }
    }

    private static class ProducerAndConsumer implements Runnable {
        public volatile boolean isRunning = true;
        public volatile boolean isProducing = true;
        private static AtomicInteger jobId = new AtomicInteger();
        private Random random = new Random();
        private final LinkedBlockingDeque<Work> deque1;
        private final LinkedBlockingDeque<Work> deque2;

        public ProducerAndConsumer(LinkedBlockingDeque<Work> deque1, LinkedBlockingDeque<Work> deque2) {
            this.deque1 = deque1;
            this.deque2 = deque2;
        }

        @Override
        public void run() {
            while (isRunning) {
                try {
                    if (isProducing && random.nextBoolean()) {
                        for (int i = 0; i < random.nextInt(5); i++) {
                            Work work = new Work(jobId.incrementAndGet());
                            work.setAssignId(Thread.currentThread().getId());
                            deque1.putLast(work);
                            long assignId = Thread.currentThread().getId();
//                            p("[" + assignId + "] assign job " + jobId + ". job deque " + assignId + ": " + deque1.stream().map(Work::getJobId).collect(Collectors.toList()));
                        }
                    }
                    TimeUnit.MILLISECONDS.sleep(100);
                    if (!deque1.isEmpty()) {
                        deque1.takeFirst().run();
                    } else {
                        if (!deque2.isEmpty()) {
                            deque2.takeLast().run();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void stopProduce() {
            isProducing = false;
        }
    }
}
