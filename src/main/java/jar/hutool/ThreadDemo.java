package jar.hutool;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import l.demo.Demo;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 线程
 *
 * @author ljh
 * created on 2020/10/26 9:30
 */
public class ThreadDemo extends Demo {

    /**
     * ThreadUtil       线程工具
     * https://hutool.cn/docs/#/core/%E7%BA%BF%E7%A8%8B%E5%92%8C%E5%B9%B6%E5%8F%91/%E7%BA%BF%E7%A8%8B%E5%B7%A5%E5%85%B7-ThreadUtil?id=%e7%ba%bf%e7%a8%8b%e5%b7%a5%e5%85%b7-threadutil
     * <p>
     * static ThreadFactory             newNamedThreadFactory(String prefix[, ThreadGroup threadGroup,]
     * -                                    boolean isDaemon[, UncaughtExceptionHandler handler)]]      创建 ThreadFactory
     * static Thread                    newThread(Runnable runnable, String name[, boolean isDaemon])   创建 Thread
     * static ExecutorService           newSingleExecutor()                                     创建单线程线程池
     * static CountDownLatch            newCountDownLatch(int threadCount)                      创建 CountDownLatch
     * static <T> CompletionService<T>  newCompletionService([ExecutorService executor])        创建 CompletionService
     * static <T> ThreadLocal<T>        createThreadLocal(boolean isInheritable)                创建本地线程对象
     * static ConcurrencyTester         concurrencyTest(int threadSize, Runnable runnable)      高并发测试工具类
     * static boolean                   sleep(Number/long timeout[, TimeUnit timeUnit])         挂起当前线程，通过返回boolean值表示是否被打断，而不是抛出异常
     * static boolean                   safeSleep(Number/long millis)                           挂起当前线程，保证挂起足够时间，...
     * static ThreadGroup               currentThreadGroup()                                    获取进程的线程组
     * static Thread[]                  getThreads([ThreadGroup group])                         获取JVM中与当前线程同组的所有线程
     * static Thread                    getMainThread()                                         获取进程的主线程
     * static StackTraceElement[]       getStackTrace()                                         获得堆栈列表
     * static StackTraceElement         getStackTraceElement(int i)                             获得堆栈项
     * static void                      interrupt(Thread thread, boolean isJoin)                结束线程，调用此方法后，线程将抛出InterruptedException异常
     * static void                      waitForDie([Thread thread])                             等待线程结束. 调用 Thread.join() 并忽略 InterruptedException
     * static void                      sync(Object obj)                                        同步对象
     */
    public static class ThreadUtilDemo {

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
            ExecutorService pool = ThreadUtil.newExecutor(2, 7, 3);

            setCountDownLatch(NUM_OF_TASK);
            for (int i = 1; i <= NUM_OF_TASK; i++) {
                pool.execute(new MyTask(i));
                p("指派了一个任务 " + i + " 给线程池！");
            }
            countDownLatch.await();
        }

    }

    /**
     * ExecutorBuilder      自定义线程池
     * https://hutool.cn/docs/#/core/%E7%BA%BF%E7%A8%8B%E5%92%8C%E5%B9%B6%E5%8F%91/%E8%87%AA%E5%AE%9A%E4%B9%89%E7%BA%BF%E7%A8%8B%E6%B1%A0-ExecutorBuilder
     */
    @Test
    public void testExecutorBuilder() {
        ExecutorService pool;
        // 1.默认线程池
        // 初始线程数为corePoolSize指定的大小
        // 没有最大线程数限制
        // 默认使用LinkedBlockingQueue，默认队列大小为1024（最大等待数1024）
        // 当运行线程大于corePoolSize放入队列，队列满后抛出异常
        pool = ExecutorBuilder.create().build();

        // 2.单线程线程池
        // 初始线程数为 1
        // 最大线程数为 1
        // 默认使用LinkedBlockingQueue，默认队列大小为1024
        // 同时只允许一个线程工作，剩余放入队列等待，等待数超过1024报错
        pool = ExecutorBuilder.create().setCorePoolSize(1).setMaxPoolSize(1).setKeepAliveTime(0).build();

        // 3.更多选项的线程池
        // 初始5个线程
        // 最大10个线程
        // 有界等待队列，最大等待数是100
        pool = ExecutorBuilder.create().setCorePoolSize(5).setMaxPoolSize(10)
                .setWorkQueue(new LinkedBlockingQueue<>(100)).build();

        // 4.特殊策略的线程池
        // 初始5个线程
        // 最大10个线程
        // 它将任务直接提交给线程而不保持它们。当运行线程小于maxPoolSize时会创建新线程，否则触发异常策略
        pool = ExecutorBuilder.create().setCorePoolSize(5).setMaxPoolSize(10)
                .useSynchronousQueue().build();
    }

    /**
     * ConcurrencyTester    高并发测试
     * https://hutool.cn/docs/#/core/%E7%BA%BF%E7%A8%8B%E5%92%8C%E5%B9%B6%E5%8F%91/%E9%AB%98%E5%B9%B6%E5%8F%91%E6%B5%8B%E8%AF%95-ConcurrencyTester
     */
    @Test
    public void testConcurrencyTester() {
        ConcurrencyTester tester = ThreadUtil.concurrencyTest(100, () -> {
            // 测试的逻辑内容
            long delay = RandomUtil.randomLong(100, 1000);
            ThreadUtil.sleep(delay);
            Console.log("{} test finished, delay: {}", Thread.currentThread().getName(), delay);
        });

        // 获取总的执行时间，单位毫秒
        Console.log(tester.getInterval());
    }

}
