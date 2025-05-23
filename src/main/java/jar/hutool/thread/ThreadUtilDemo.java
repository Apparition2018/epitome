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
 * <pre>{@code
 * static ThreadFactory             newNamedThreadFactory(String prefix[, ThreadGroup threadGroup], boolean isDaemon[, UncaughtExceptionHandler handler)]]  创建 ThreadFactory
 * static Thread                    newThread(Runnable runnable, String name[, boolean isDaemon])   创建 Thread
 * static ExecutorService           newSingleExecutor()                                 创建单线程线程池
 * static CountDownLatch            newCountDownLatch(int threadCount)                  创建 CountDownLatch
 * static <T> CompletionService<T>  newCompletionService([ExecutorService executor])    创建 CompletionService
 * static <T> ThreadLocal<T>        createThreadLocal(boolean isInheritable)            创建本地线程对象
 * static ConcurrencyTester         concurrencyTest(int threadSize, Runnable runnable)  高并发测试工具类
 * static boolean                   sleep(Number/long timeout[, TimeUnit timeUnit])     挂起当前线程，通过返回boolean值表示是否被打断，而不是抛出异常
 * static boolean                   safeSleep(Number/long millis)                       挂起当前线程，保证挂起足够时间，...
 * static ThreadGroup               currentThreadGroup()                                获取进程的线程组
 * static Thread[]                  getThreads([ThreadGroup group])                     获取JVM中与当前线程同组的所有线程
 * static Thread                    getMainThread()                                     获取进程的主线程
 * static StackTraceElement[]       getStackTrace()                                     获得堆栈列表
 * static StackTraceElement         getStackTraceElement(int i)                         获得堆栈项
 * static void                      interrupt(Thread thread, boolean isJoin)            结束线程，调用此方法后，线程将抛出InterruptedException异常
 * static void                      waitForDie([Thread thread])                         等待线程结束. 调用 Thread.join() 并忽略 InterruptedException
 * static void                      sync(Object obj)                                    同步对象}
 * </pre>
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
