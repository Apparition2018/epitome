package jar.apache.curator;

import l.demo.Demo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.RetryNTimes;

/**
 * Curator 分布式计数器
 * <p>
 * Distributed Atomic Long：https://curator.apache.org/curator-recipes/distributed-atomic-long.html
 *
 * @author Administrator
 * created on 2021/9/28 1:46
 */
public class CuratorDistributedCounter extends Demo {

    public static void main(String[] args) throws InterruptedException {
        try (CuratorFramework curatorFramework = CuratorUtils.getCuratorFramework()) {

            final String counterPath = "/counter";
            DistributedAtomicLong distributedAtomicLong = new DistributedAtomicLong(curatorFramework, counterPath, new RetryNTimes(10, 10));
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
                e.printStackTrace();
            }
        }).start();
    }
}
