package knowledge.concurrent.util;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/Semaphore.html">Semaphore</a>  信号灯
 * <p>控制同时访问的线程数上限，超出上限的线程阻塞等待许可释放。
 * <p>场景：限流、资源池（数据库连接池）
 *
 * @author ljh
 * @since 2020/11/17 19:09
 */
public class SemaphoreDemo {

    private static final int THREAD_COUNT = 30;
    private static final int MAX_CONCURRENT = 10;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
        Semaphore semaphore = new Semaphore(MAX_CONCURRENT, true);

        for (int i = 0; i < THREAD_COUNT; i++) {
            int num = i + 1;
            threadPool.submit(() -> {
                try {
                    if (semaphore.tryAcquire(3, TimeUnit.SECONDS)) {
                        try {
                            TimeUnit.SECONDS.sleep(new Random().nextInt(2));
                            System.out.println("save data " + num + ", available permits " + semaphore.availablePermits() + ", await " + semaphore.getQueueLength());
                        } finally {
                            semaphore.release();
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.MINUTES);
    }
}
