package jar.apache.curator;

import l.demo.Demo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * Curator 分布式锁
 * <p>
 * InterProcessMutex            可重入排它锁      https://curator.apache.org/curator-recipes/shared-reentrant-lock.html
 * InterProcessSemaphoreMutex   不可重入排它锁
 * InterProcessReadWriteLock    可重入读写锁
 * InterProcessMultiLock        将多个锁作为一个实体管理的容器
 * InterProcessSemaphoreV2      信号灯
 *
 * @author ljh
 * created on 2021/9/28 1:10
 */
public class CuratorDistributedLock extends Demo {

    @Test
    public void testInterProcessMutex() throws InterruptedException {
        try (CuratorFramework curatorFramework = CuratorUtils.getCuratorFramework()) {

            final String lockPath = "/lock";
            InterProcessMutex lock = new InterProcessMutex(curatorFramework, lockPath);
            InterProcessMutex lock2 = new InterProcessMutex(curatorFramework, lockPath);
            setCountDownLatch(2);

            new Thread(() -> {
                try {
                    if (lock.acquire(30, TimeUnit.SECONDS)) {
                        p("1 获取锁");
                        TimeUnit.SECONDS.sleep(1);
                    }
                    lock.release();
                    countDownLatch.countDown();
                    p("1 释放锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    if (lock2.acquire(30, TimeUnit.SECONDS)) {
                        p("2 获取锁");
                        TimeUnit.SECONDS.sleep(1);
                    }
                    lock2.release();
                    countDownLatch.countDown();
                    p("2 释放锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            countDownLatch.await();
        }
    }

}
