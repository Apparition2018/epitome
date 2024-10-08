package knowledge.design.pattern.other.concurrency;

import l.demo.Demo;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 工作密取
 * <pre>
 * 在生产者-消费者模式中，所有消费者都从一个工作队列中取元素，一般使用阻塞队列实现；
 * 而在 工作密取 模式中，每个消费者有其单独的工作队列，一般使用双端队列实现，
 * 如果一个消费者完成了自己队列中的全部工作，那么它可以从其它消费者队列末尾秘密地获取工作。
 *
 * 工作密取模式 对比 生产者-消费者模式更为灵活，因为多个线程不会因为在同一个工作队列中抢占内容发生竞争。
 * 在大多数时候，它们只是访问自己的双端队列。即使需要访问另一个队列时，也是从队列的尾部获取工作，降低了队列上的竞争程度。
 * 工作密取非常适用于即是消费者也是生产者的问题，当执行某个工作时可能导致出现更多的工作。
 * </pre>
 *
 * @author ljh
 * @see <a href="https://img-blog.csdnimg.cn/20190602161352675.png">工作密取示意图</a>
 * @see <a href="https://blog.csdn.net/hxpjava1/article/details/44245593">LinkedBlockingDeque 工作密取</a>
 * @since 2020/10/19 16:17
 */
public class WorkStealingDemo extends Demo {

    public static volatile boolean isProducing = true;
    private static final boolean[] isAllStop = new boolean[]{false, false, false, false};
    private static final long SLEEP_TIME = TimeUnit.SECONDS.toMillis(1);

    /** 本例基于 LinkedBlockingDeque 实现 */
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<Work> deque1 = new LinkedBlockingDeque<>();
        LinkedBlockingDeque<Work> deque2 = new LinkedBlockingDeque<>();
        Machine m1 = new Machine(deque1, deque2, 0);
        Machine m2 = new Machine(deque1, deque2, 1);
        Machine m3 = new Machine(deque2, deque1, 2);
        Machine m4 = new Machine(deque2, deque1, 3);

        ExecutorService threadPool = Executors.newCachedThreadPool(new MyThreadFactory());
        threadPool.execute(m1);
        threadPool.execute(m2);
        threadPool.execute(m3);
        threadPool.execute(m4);

        TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
        isProducing = false;
        p("********** 生产者停止生产 **********");

        while (!BooleanUtils.and(isAllStop)) {
            TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
        }
        threadPool.shutdown();
        p("********** 消费者消费完毕 **********");
    }

    private record Work(int jobId, String assignName) implements Runnable {

        @Override
        public void run() {
            ThreadLocalRandom r = ThreadLocalRandom.current();
            sleep(r.nextLong(SLEEP_TIME), TimeUnit.MILLISECONDS);
            String finishName = Thread.currentThread().getName();
            p(finishName + " - " + jobId + (finishName.equals(assignName) ? StringUtils.EMPTY : " + " + assignName));
        }
    }

    private record Machine(LinkedBlockingDeque<Work> deque1, LinkedBlockingDeque<Work> deque2,
                           int machineIndex) implements Runnable {
        private static final AtomicInteger jobId = new AtomicInteger();

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
