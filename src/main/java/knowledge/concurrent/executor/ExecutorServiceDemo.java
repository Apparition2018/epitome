package knowledge.concurrent.executor;

import l.demo.Demo;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * ExecutorService
 * <p>
 * 线程池的主要功能是：
 * 1：控制线程数量
 * 2：重用线程 当开发任务量大时，或者有频繁创建线程任务执行的操作时，就应当使用线程池来调度线程
 * <p>
 * FixedThreadPool, CachedThreadPool, SingleThreadExecutor, ScheduledThreadPool
 * 四种线程池都是使用 ThreadPoolExecutor 实现的，所以 ThreadPoolExecutor 更灵活
 * <p>
 * 如何在队列排队之前让ThreadPoolExecutor将线程增加到最大数量：https://blog.csdn.net/fy_java1995/article/details/107920983
 * https://jdk6.net/util-concurrent/ExecutorService.html
 */
public class ExecutorServiceDemo extends Demo {

    /**
     * static ExecutorService	        newFixedThreadPool(int nThreads[, ThreadFactory threadFactory])
     * 创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程，在需要时使用提供的 ThreadFactory 创建新线程
     */
    @Test
    public void testFixedThreadPool() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        executeTask(pool);
    }

    /**
     * static ExecutorService	        newCachedThreadPool([ThreadFactory threadFactory])
     * 创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们，并在需要时使用提供的 ThreadFactory 创建新线程
     */
    @Test
    public void testCachedThreadPool() throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        executeTask(pool);
    }

    /**
     * static ExecutorService	        newSingleThreadExecutor([ThreadFactory threadFactory])
     * 创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程，并在需要时使用提供的 ThreadFactory 创建新线程
     * <p>
     * 该方法返回一个只有一个线程的线程池，即每次只能执行一个线程任务，多余的任务会保存到一个任务队列中，
     * 等待这一个线程空闲，当这个线程空闲了再按FIFO方式顺序执行任务队列中的任务
     */
    @Test
    public void testSingleThreadExecutor() throws InterruptedException {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        executeTask(pool);
    }

    /**
     * static ScheduledExecutorService  newScheduledThreadPool(int corePoolSize[, ThreadFactory threadFactory])
     * 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行
     */
    @Test
    public void testScheduledThreadPool() throws InterruptedException {
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);
        executeTask(scheduledPool);
    }

    private final static int NUM_OF_TASK = 5;

    private void executeTask(ExecutorService pool) throws InterruptedException {
        setCountDownLatch(NUM_OF_TASK);

        for (int i = 1; i <= NUM_OF_TASK; i++) {

            if (pool instanceof ScheduledThreadPoolExecutor) {
                // ScheduledFuture<?>	schedule(Runnable command, long delay, TimeUnit unit)
                // 创建并执行在给定延迟后启用的一次性操作
                ((ScheduledThreadPoolExecutor) pool).schedule(new MyTask(i), 1, TimeUnit.SECONDS);
            } else {
                // void	                execute(Runnable command)
                // 在未来某个时间执行给定的命令
                // 将任务指派给线程池
                pool.execute(new MyTask(i));
            }
            p("指派了一个任务 " + i + " 给线程池！");
        }
        countDownLatch.await();

        // void	                        shutdown()
        // 启动一次顺序关闭，执行以前提交的任务，但不接受新任务
        pool.shutdown();
        p("关闭线程池！");

        // Java 线程池的正确关闭方法：https://www.cnblogs.com/slankka/p/11609615.html
        try {
            // boolean	                awaitTermination(long timeout, TimeUnit unit)
            // 请求关闭、发生超时或者当前线程中断，无论哪一个首先发生之后，都将导致阻塞，直到所有任务完成执行
            if (!pool.awaitTermination(30, TimeUnit.SECONDS)) {
                // List<Runnable>	        shutdownNow()
                // 试图停止所有正在执行的活动任务，暂停处理正在等待的任务，并返回等待执行的任务列表
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
        }
    }

    /**
     * Runnable → Callable
     */
    @Test
    public void runnableToCallable() {
        Runnable runnable = () -> {
        };

        // static <T> Callable<T>	callable(Runnable task[, T result])
        // 返回 Callable 对象，调用它时可运行给定的任务并返回给定的结果
        Callable<Object> callable = Executors.callable(runnable);
    }

}
