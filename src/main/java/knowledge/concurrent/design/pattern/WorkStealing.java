package knowledge.concurrent.design.pattern;

import l.demo.Demo;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.BooleanUtils;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

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
 * 工作密取示意图：https://img-blog.csdnimg.cn/20190602161352675.png
 * LinkedBlockingDeque工作密取：https://blog.csdn.net/hxpjava1/article/details/44245593
 *
 * @author ljh
 * created on 2020/10/19 16:17
 */
public class WorkStealing extends Demo {

    public static volatile boolean isProducing = true;
    private static volatile boolean[] isAllStop = new boolean[]{false, false, false, false};
    private static final long SLEEP_TIME = TimeUnit.SECONDS.toMillis(1);

    /**
     * 本例基于 LinkedBlockingDeque 实现
     */
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<Work> deque1 = new LinkedBlockingDeque<>();
        LinkedBlockingDeque<Work> deque2 = new LinkedBlockingDeque<>();
        Machine m1 = new Machine(deque1, deque2, 0);
        Machine m2 = new Machine(deque1, deque2, 1);
        Machine m3 = new Machine(deque2, deque1, 2);
        Machine m4 = new Machine(deque2, deque1, 3);

        ExecutorService pool = Executors.newCachedThreadPool(new MyThreadFactory());
        pool.execute(m1);
        pool.execute(m2);
        pool.execute(m3);
        pool.execute(m4);

        TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
        isProducing = false;
        p("********** 生产者停止生产 **********");

        while (!BooleanUtils.and(isAllStop)) {
            TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
        }
        pool.shutdown();
        p("********** 消费者消费完毕 **********");
    }

    @Getter
    @Setter
    private static class Work implements Runnable {
        private String assignName;
        private final int jobId;

        public Work(int jobId, String assignName) {
            this.jobId = jobId;
            this.assignName = assignName;
        }

        @Override
        public void run() {
            ThreadLocalRandom r = ThreadLocalRandom.current();
            sleep(r.nextLong(SLEEP_TIME), TimeUnit.MILLISECONDS);
            String finishName = Thread.currentThread().getName();
            p(finishName + " - " + jobId + (finishName.equals(assignName) ? "" : " + " + assignName));
        }
    }

    private static class Machine implements Runnable {
        private static AtomicInteger jobId = new AtomicInteger();
        private final LinkedBlockingDeque<Work> deque1;
        private final LinkedBlockingDeque<Work> deque2;
        private int machineIndex;

        public Machine(LinkedBlockingDeque<Work> deque1, LinkedBlockingDeque<Work> deque2, int machineIndex) {
            this.deque1 = deque1;
            this.deque2 = deque2;
            this.machineIndex = machineIndex;
        }

        @Override
        public void run() {
            ThreadLocalRandom r = ThreadLocalRandom.current();
            while (isProducing || deque1.size() != 0 || deque2.size() != 0) {
                try {
                    if (isProducing && r.nextBoolean()) {
                        String assignName = Thread.currentThread().getName();
                        TimeUnit.MILLISECONDS.sleep(r.nextLong(SLEEP_TIME));
                        for (int i = 0; i < r.nextInt(4); i++) {
                            Work work = new Work(jobId.incrementAndGet(), assignName);
                            deque1.putLast(work);
                            p(assignName + " + " + jobId);
                        }
                    }
                    if (!deque1.isEmpty()) {
                        Objects.requireNonNull(deque1.pollFirst()).run();
                    } else {
                        if (!deque2.isEmpty()) {
                            Objects.requireNonNull(deque2.pollLast()).run();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            isAllStop[machineIndex] = true;
        }
    }

}
