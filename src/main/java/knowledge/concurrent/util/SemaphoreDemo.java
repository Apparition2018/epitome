package knowledge.concurrent.util;

import l.demo.Demo;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore 信号灯
 * 一个计数信号量。从概念上讲，信号量维护了一个许可集。
 * 如有必要，在许可可用前会阻塞每一个 acquire()，然后再获取该许可。
 * 每个 release() 添加一个许可，从而可能释放一个正在阻塞的获取者。
 * 但是，不使用实际的许可对象，Semaphore 只对可用许可的号码进行计数，并采取相应的行动。
 * https://www.runoob.com/manual/jdk1.6/java.base/java/util/concurrent/Semaphore.html
 *
 * @author ljh
 * @since 2020/11/17 19:09
 */
public class SemaphoreDemo extends Demo {

    /**
     * Semaphore 可以用于做流量控制，特别公用资源有限的应用场景，比如数据库连接。假如有一个需求，要读取几万个文件的数据，
     * 因为都是 IO 密集型任务，我们可以启动几十个线程并发的读取，但是如果读到内存后，还需要存储到数据库中，而数据库的连接数只有10个，
     * 这时我们必须控制只有十个线程同时获取数据库连接保存数据，否则会报错无法获取数据库连接。
     * http://ifeve.com/concurrency-semaphore/#more-14753
     */
    public static void main(String[] args) {
        final int THREAD_COUNT = 30;
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
        // Semaphore(int permits[, boolean fair])
        // 创建具有给定的许可数和给定的公平设置的 Semaphore
        Semaphore semaphore = new Semaphore(10, true);

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int num = i + 1;
            threadPool.submit(() -> {
                try {
                    // void	    acquire([int permits])
                    // 从此信号量获取给定数目的许可，在提供这些许可前一直将线程阻塞，或者线程已被中断
                    semaphore.acquire();
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    // int	    availablePermits()
                    // 返回此信号量中当前可用的许可数
                    // int	    getQueueLength()
                    // 返回正在等待获取的线程的估计数目
                    p("save data " + num + ", available permits " + semaphore.availablePermits() + ", await " + semaphore.getQueueLength());
                    // void	    release([int permits])
                    // 释放给定数目的许可，将其返回到信号量
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        threadPool.shutdown();
    }

}
