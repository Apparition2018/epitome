package jar.apache.curator;

import l.demo.Demo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.RetryNTimes;

/**
 * Curator 分布式计数器
 * <p><a href="https://curator.apache.org/curator-recipes/distributed-atomic-long.html">Distributed Atomic Long</a>
 *
 * @author ljh
 * @since 2021/9/28 1:46
 */
public class CuratorDistributedCounter extends Demo {

    private static final String COUNTER_PATH = "/counter";

    public static void main(String[] args) throws InterruptedException {
        try (CuratorFramework client = CuratorUtils.getCuratorFramework()) {

            DistributedAtomicLong distributedAtomicLong = new DistributedAtomicLong(client, COUNTER_PATH, new RetryNTimes(10, 10));
            setCountDownLatch(2);

            increment(distributedAtomicLong);
            increment(distributedAtomicLong);

            countDownLatch.await();
        }
    }

    private static void increment(DistributedAtomicLong distributedAtomicLong) {
        new Thread(() -> {
            try {
                AtomicValue<Long> atomicValue = distributedAtomicLong.increment();
                if (atomicValue.succeeded())
                    p("Increment: from " + atomicValue.preValue() + " to " + atomicValue.postValue());
                countDownLatch.countDown();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
