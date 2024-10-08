package knowledge.concurrent.util;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static l.demo.Demo.p;

/**
 * ThreadLocalRandom
 * <p>线程隔离的随机数生成器，在并发程序中使用 ThreadLocalRandom 比使用 Random，能够减少开销和降低争用。
 * <p>阿里编程规约：
 * <pre>
 * 避免 Random 实例被多线程使用，虽然共享该实例是线程安全的，但会因竞争同一 seed 导致的性能下降
 * Random 实例包括 java.util.Random 的实例或者 Math.random() 的方式
 * 在 JDK7 之后，可以直接使用 API ThreadLocalRandom，而在 JDK7 之前，需要编码保证每个线程持有一个单独的 Random 实例
 * </pre>
 *
 * @author ljh
 * @see <a href="https://www.cnblogs.com/shamo89/p/8052161.html">使用 ThreadLocalRandom 代替 Random</a>
 * @since 2020/11/6 9:52
 */
public class ThreadLocalRandomDemo {

    public static void main(String[] args) throws InterruptedException {
        // 错误用法
        // static ThreadLocalRandom     current()       返回当前线程的 ThreadLocalRandom
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        new Thread(() -> IntStream.rangeClosed(1, 10).forEach(i -> System.out.print(threadLocalRandom.nextInt() + StringUtils.SPACE))).start();
        TimeUnit.SECONDS.sleep(1);
        p();
        new Thread(() -> IntStream.rangeClosed(1, 10).forEach(i -> System.out.print(threadLocalRandom.nextInt() + StringUtils.SPACE))).start();
        TimeUnit.SECONDS.sleep(1);
        p();

        // 正确用法
        new Thread(() -> {
            ThreadLocalRandom threadLocalRandom2 = ThreadLocalRandom.current();
            for (int i = 0; i < 10; i++) {
                System.out.print(threadLocalRandom2.nextInt() + StringUtils.SPACE);
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        p();
        new Thread(() -> {
            ThreadLocalRandom threadLocalRandom2 = ThreadLocalRandom.current();
            IntStream.rangeClosed(1, 10).forEach(i -> System.out.print(threadLocalRandom2.nextInt() + StringUtils.SPACE));
        }).start();
    }
}
