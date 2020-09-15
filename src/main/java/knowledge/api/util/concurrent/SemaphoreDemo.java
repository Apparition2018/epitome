package knowledge.api.util.concurrent;

import l.demo.Demo;
import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore 信号灯
 * 一个计数信号量。从概念上讲，信号量维护了一个许可集。
 * 如有必要，在许可可用前会阻塞每一个 acquire()，然后再获取该许可。
 * 每个 release() 添加一个许可，从而可能释放一个正在阻塞的获取者。
 * 但是，不使用实际的许可对象，Semaphore 只对可用许可的号码进行计数，并采取相应的行动。
 * https://jdk6.net/util-concurrent/Semaphore.html
 */
public class SemaphoreDemo extends Demo {

    // Semaphore(int permits[, boolean fair])
    // 创建具有给定的许可数和给定的公平设置的 Semaphore，公平指保证在争用时按先进先出的顺序授予许可
    private final Semaphore semaphore = new Semaphore(10, true);

    /**
     * int	        availablePermits()          返回此信号量中当前可用的许可数
     */
    @Test
    public void availablePermits() {
        p(semaphore.availablePermits()); // 10
    }

    /**
     * void	        acquire([int permits])      从此信号量获取给定数目的许可，在提供这些许可前一直将线程阻塞，或者线程已被中断
     */
    @Test
    public void acquire() throws InterruptedException {
        semaphore.acquire(12);
        p("end");
    }

    /**
     * void	        acquireUninterruptibly([int permits])       从此信号量获取给定数目的许可，在提供这些许可前一直将线程阻塞
     */
    @Test
    public void acquireUninterruptibly() {
        semaphore.acquireUninterruptibly(12);
        p("end");
    }

    /**
     * boolean	    tryAcquire([int permits])
     * 仅在调用时此信号量中有给定数目的许可时，才从此信号量中获取这些许可
     * boolean	    tryAcquire([int permits, ]long timeout, TimeUnit unit)
     * 如果在给定的等待时间内此信号量有可用的所有许可，并且当前线程未被中断，则从此信号量获取给定数目的许可
     */
    @Test
    public void tryAcquire() throws InterruptedException {
        p(semaphore.tryAcquire(12) ? "yes" : "no");
        p(semaphore.tryAcquire(12, 1, TimeUnit.SECONDS) ? "yes" : "no");
    }

}
