package knowledge.concurrent.pool;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;

/**
 * Executors
 * <p>阿里编程规约：
 * <pre>
 * <a href="https://blog.csdn.net/u010994966/article/details/103123927">线程池不允许使用 Executors 去创建</a>，而是通过 ThreadPoolExecutor 的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险
 * 1 FixedThreadPool 和 SingleThreadPool：允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM
 * 2 CachedThreadPool：允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM
 * 3 ScheduledThreadPool：允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM
 * </pre>
 * 参考：<a href="https://blog.csdn.net/fy_java1995/article/details/107920983">如何在队列排队之前让 ThreadPoolExecutor 将线程增加到最大数量</a>
 *
 * @author ljh
 * @since 2020/11/17 19:09
 */
public class ExecutorsDemo extends Demo {

    /**
     * static ExecutorService	        newFixedThreadPool(int nThreads[, ThreadFactory threadFactory])
     * <p>创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程，在需要时使用提供的 ThreadFactory 创建新线程
     */
    @Test
    public void testFixedThreadPool() throws InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2, new MyThreadFactory());
        executeTask(fixedThreadPool);
    }

    /**
     * static ExecutorService	        newCachedThreadPool([ThreadFactory threadFactory])
     * <p>创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们，并在需要时使用提供的 ThreadFactory 创建新线程
     */
    @Test
    public void testCachedThreadPool() throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool(new MyThreadFactory());
        executeTask(cachedThreadPool);
    }

    /**
     * static ExecutorService	        newSingleThreadExecutor([ThreadFactory threadFactory])
     * <pre>
     * 创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程，并在需要时使用提供的 ThreadFactory 创建新线程
     * 该方法返回一个只有一个线程的线程池，即每次只能执行一个线程任务，多余的任务会保存到一个任务队列中，等待这一个线程空闲，当这个线程空闲了再按FIFO方式顺序执行任务队列中的任务
     * </pre>
     */
    @Test
    public void testSingleThreadExecutor() throws InterruptedException {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor(new MyThreadFactory());
        executeTask(singleThreadExecutor);
    }

    /**
     * static ScheduledExecutorService  newScheduledThreadPool(int corePoolSize[, ThreadFactory threadFactory])
     * <pre>
     * 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行
     * 多线程并行处理定时任务时，Timer 运行多个 TimeTask 时，只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，使用 ScheduledExecutorService 则没有这个问题（阿里编程规约）
     * </pre>
     */
    @Test
    public void testScheduledThreadPool() throws InterruptedException {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2, new MyThreadFactory());
        executeTask(scheduledThreadPool);
    }

    /**
     * static ExecutorService           newWorkStealingPool([int parallelism])
     * <p>创建工作窃取线程池
     */
    @Test
    public void testWorkStealingPool() throws InterruptedException {
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
        executeTask(workStealingPool);
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
            p(String.format("指派了一个任务 %s 给线程池！", i));
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
                // List<Runnable>	    shutdownNow()
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

        // static <T> Callable<T>	    callable(Runnable task[, T result])
        // 返回 Callable 对象，调用它时可运行给定的任务并返回给定的结果
        Callable<Object> callable = Executors.callable(runnable);
    }

    @Test
    public void invoke() throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newWorkStealingPool();
        List<Callable<String>> callableList = List.of(
            this.callable("task1", 1),
            this.callable("task2", 2),
            this.callable("task3", 3)
        );
        p("--- invokeAll ---");
        // <T> List<Future<T>>  invokeAll(Collection<? extends Callable<T>> tasks[, long timeout, TimeUnit unit])
        // 执行给定任务，当所有任务完成时返回一个包含它们状态和结果的 Future 列表
        threadPool.invokeAll(callableList).stream().map(future -> {
            try {
                return future.get();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).forEach(System.out::println);
        p("--- invokeAny ---");
        // <T> T                invokeAny(Collection<? extends Callable<T>> tasks[, long timeout, TimeUnit unit])
        // 执行给定任务，当有一个任务完成时返回其结果
        p(threadPool.invokeAny(callableList));
    }

    private Callable<String> callable(String result, long seconds) {
        return () -> {
            sleep(seconds, TimeUnit.SECONDS);
            return result;
        };
    }
}
