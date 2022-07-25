package knowledge.concurrent.util;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocalRandom
 * 线程隔离的随机数生成器，在并发程序中使用 ThreadLocalRandom 比使用 Random，能够减少开销和降低争用。
 * <p>
 * 阿里编程规约：
 * 避免 Random 实例被多线程使用，虽然共享该实例是线程安全的，但会因竞争同一 seed 导致的性能下降
 * Random 实例包括 java.util.Random 的实例或者 Math.random() 的方式
 * 在 JDK7 之后，可以直接使用 API ThreadLocalRandom，而在 JDK7 之前，需要编码保证每个线程持有一个单独的 Random 实例
 * <p>
 * 为什么要使用 ThreadLocalRandom 代替 Random 生成随机数：https://www.cnblogs.com/shamo89/p/8052161.html
 *
 * @author ljh
 * created on 2020/11/6 9:52
 */
public class ThreadLocalRandomDemo {

    @Test
    public void testThreadLocalRandom() throws InterruptedException {
        // 错误用法
        // static ThreadLocalRandom     current()       返回当前线程的 ThreadLocalRandom
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.print(threadLocalRandom.nextInt() + " ");
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.print(threadLocalRandom.nextInt() + " ");
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println();

        // 正确用法
        new Thread(() -> {
            ThreadLocalRandom threadLocalRandom2 = ThreadLocalRandom.current();
            for (int i = 0; i < 10; i++) {
                System.out.print(threadLocalRandom2.nextInt() + " ");
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println();
        new Thread(() -> {
            ThreadLocalRandom threadLocalRandom2 = ThreadLocalRandom.current();
            for (int i = 0; i < 10; i++) {
                System.out.print(threadLocalRandom2.nextInt() + " ");
            }
        }).start();
    }
}
