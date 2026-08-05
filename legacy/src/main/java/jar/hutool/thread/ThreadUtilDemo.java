package jar.hutool.thread;

import cn.hutool.core.thread.ThreadUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * <a href="https://doc.hutool.cn/pages/ThreadUtil/">ThreadUtil</a> 线程工具
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/thread/ThreadUtil.html">ThreadUtil api</a>
 *
 * @author ljh
 * @since 2020/10/26 9:30
 */
public class ThreadUtilDemo extends Demo {

    private final static int NUM_OF_TASK = 5;

    /**
     * 直接在公共线程池中执行线程
     */
    @Test
    public void execute() throws InterruptedException {
        setCountDownLatch(NUM_OF_TASK);
        // 直接在公共线程池中执行线程
        IntStream.rangeClosed(1, NUM_OF_TASK).forEach(i -> ThreadUtil.execute(new MyTask(i)));
        countDownLatch.await();
    }

    /**
     * 执行异步方法
     */
    @Test
    public void execAsync() throws InterruptedException {
        setCountDownLatch(1);
        ThreadUtil.execAsync(() -> {
            Thread t = Thread.currentThread();
            p(t.getName() + "：正在运行任务 ...");
            sleep(1, TimeUnit.SECONDS);
            countDownLatch.countDown();
            p(t.getName() + "：运行任务完毕！");
        });
        countDownLatch.await();
    }

    /**
     * 获得一个新的线程池
     */
    @Test
    public void newExecutor() throws InterruptedException {
        // 创建 核心线程数为2，最大线程数为5，等待工作队列容量为3 的线程池
        ExecutorService threadPool = ThreadUtil.newExecutor(2, 5, 3);

        setCountDownLatch(NUM_OF_TASK);
        for (int i = 1; i <= NUM_OF_TASK; i++) {
            threadPool.execute(new MyTask(i));
            p(String.format("指派了一个任务 %s 给线程池！", i));
        }
        countDownLatch.await();
    }
}
