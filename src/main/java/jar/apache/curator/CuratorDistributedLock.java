package jar.apache.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Curator 分布式锁
 * <pre>
 * <a href="http://curator.apache.org/curator-recipes/shared-reentrant-lock.html">InterProcessMutex</a>     可重入排它锁
 * <a href="http://curator.apache.org/curator-recipes/shared-lock.html">InterProcessSemaphoreMutex</a>      不可重入排它锁
 * <a href="http://curator.apache.org/curator-recipes/shared-reentrant-read-write-lock.html">InterProcessReadWriteLock</a>  可重入读写锁
 * <a href="http://curator.apache.org/curator-recipes/multi-shared-lock.html">InterProcessMultiLock</a>     将多个锁作为一个实体管理的容器
 * <a href="http://curator.apache.org/curator-recipes/shared-semaphore.html">InterProcessSemaphoreV2</a>    信号灯
 * </pre>
 *
 * @author ljh
 * @since 2021/9/28 1:10
 */
public class CuratorDistributedLock {

    private static final int QTY = 5;
    private static final int REPETITIONS = QTY * 10;
    private static final String LOCK_PATH = "/lock";

    /**
     * <a href="https://github.com/apache/curator/tree/master/curator-examples/src/main/java/locking">Locking Example</a>
     */
    public static void main(String[] args) throws Exception {
        FakeLimitedResource resource = new FakeLimitedResource();
        ExecutorService threadPool = Executors.newFixedThreadPool(QTY);
        TestingServer testingServer = new TestingServer();

        try {
            for (int i = 0; i < QTY; i++) {
                final int index = i;
                Callable<Void> task = () -> {
                    CuratorFramework client = CuratorFrameworkFactory.newClient(testingServer.getConnectString(), new ExponentialBackoffRetry(1000, 3));
                    try {
                        client.start();
                        ExampleClientThatLocks example = new ExampleClientThatLocks(client, LOCK_PATH, resource, "Client #" + index);
                        for (int j = 0; j < REPETITIONS; j++) {
                            example.doWork(10, TimeUnit.SECONDS);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        CloseableUtils.closeQuietly(client);
                    }
                    return null;
                };
                threadPool.submit(task);
            }
            threadPool.shutdown();
            if (!threadPool.awaitTermination(10, TimeUnit.MINUTES)) {
                threadPool.shutdownNow();
            }
        } finally {
            CloseableUtils.closeQuietly(testingServer);
        }
    }

    private static class ExampleClientThatLocks {
        private final InterProcessMutex lock;
        private final FakeLimitedResource resource;
        private final String clientName;

        public ExampleClientThatLocks(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName) {
            this.resource = resource;
            this.clientName = clientName;
            lock = new InterProcessMutex(client, lockPath);
        }

        public void doWork(long time, TimeUnit unit) throws Exception {
            if (!lock.acquire(time, unit)) {
                throw new IllegalStateException(clientName + " could not acquire the lock");
            }
            try {
                System.out.println(clientName + " has the lock");
                resource.use();
            } finally {
                System.out.println(clientName + " releasing the lock");
                lock.release();
            }
        }
    }

    /**
     * 模拟只能由一个进程访问的外部资源
     */
    private static class FakeLimitedResource {
        private final AtomicBoolean inUse = new AtomicBoolean(false);

        public void use() throws InterruptedException {
            // 在实际应用程序中，这将是访问/操作共享资源
            if (!inUse.compareAndSet(false, true)) {
                throw new IllegalStateException("Needs to be used by one client at a time");
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } finally {
                inUse.set(false);
            }
        }
    }
}
