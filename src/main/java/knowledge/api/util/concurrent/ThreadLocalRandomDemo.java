package knowledge.api.util.concurrent;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocalRandom
 * 线程隔离的随机数生成器，在并发程序中使用 ThreadLocalRandom 比使用 Random，能够减少开销和降低争用。
 * <p>
 * 为什么要使用ThreadLocalRandom代替Random生成随机数：https://www.cnblogs.com/shamo89/p/8052161.html
 * https://docs.oracle.com/javase/8/docs/api/index.html?java/nio/file/attribute/FileAttributeView.html
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
