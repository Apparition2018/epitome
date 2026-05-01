package knowledge.concurrent.pool;

import l.demo.Demo;
import org.apache.commons.lang3.time.DateUtils;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/ThreadPoolExecutor.html">ThreadPoolExecutor</a>
 * <p>参数：
 * <pre>
 * corePoolSize     最小线程数
 * maximumPoolSize  最大线程数
 * keepAliveTime    当 poolSize > corePoolSize，空闲线程等待新任务的最长时间，超时则回收
 * unit             keepAliveTime 时间单位
 * workQueue        当 poolSize ≥ corePoolSize，execute() 提交的 Runnable 任务进入的队列
 * threadFactory    创建新线程时使用的工厂
 * handler          当 poolSize ≥ corePoolSize + queueCapacity，新提交的任务出发的 {@link MyRejectHandler 拒绝策略}
 * </pre>
 * 线程池对待任务的策略:
 * <pre>
 * 1 池中任务数 <= corePoolSize -> 放入立即执行
 * 2 池中任务数 > corePoolSize -> 放入队列等待
 * 3 池中任务数 > (corePoolSize + workQueue.size()) -> 新建线程立即执行
 * 4 池中任务数 > maxPoolSize -> 触发handler（RejectedExecutionHandler）异常
 * </pre>
 * 线程资源必须通过线程池提供，不允许在应用中自行显式创建线程（阿里编程规约）
 *
 * @author ljh
 * @since 2020/11/17 19:09
 */
public class ThreadPoolExecutorDemo extends Demo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3), new MyThreadFactory("Demo"), new MyRejectHandler());

        IntStream.rangeClosed(1, 10).forEach(i -> threadPool.execute(new MyTask(i)));
    }

    /**
     * ThreadPoolExecutor 内置4种拒绝策略：
     * <pre>
     * 1 {@link ThreadPoolExecutor.AbortPolicy}：丢弃被拒绝任务，抛出 RejectedExecutionException
     * 2 {@link ThreadPoolExecutor.DiscardPolicy}：丢弃被拒绝任务，不抛出异常
     * 3 {@link ThreadPoolExecutor.DiscardOldestPolicy}：丢弃队列头部最早的任务，重新尝试提交被拒绝任务
     * 4 {@link ThreadPoolExecutor.CallerRunsPolicy}：调用者线程运行被拒绝任务
     * </pre>
     */
    public static class MyRejectHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            printLog(r, executor);
        }

        private void printLog(Runnable r, ThreadPoolExecutor executor) {
            System.err.println("Task " + r + " rejected from " + executor);
        }
    }

    /**
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/ScheduledThreadPoolExecutor.html">ScheduledThreadPoolExecutor</a>
     * <p>创建一个定长线程池，支持定时及周期性任务执行
     */
    private static class ScheduledThreadPoolExecutorDemo {
        public static void main(String[] args) {
            // ScheduledThreadPoolExecutor(int corePoolSize)
            // 使用给定核心池大小创建一个新 ScheduledThreadPoolExecutor
            ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

            // ScheduledFuture<?>	scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
            // 创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；
            // 也就是将在 initialDelay 后开始执行，然后在 initialDelay+period 后执行，接着在 initialDelay + 2 * period 后执行，依此类推
            exec.scheduleAtFixedRate(() -> phr(14), DateUtils.MILLIS_PER_SECOND, DateUtils.MILLIS_PER_SECOND * 3, TimeUnit.MILLISECONDS);
            exec.scheduleAtFixedRate(() -> p(System.nanoTime()), DateUtils.MILLIS_PER_SECOND, DateUtils.MILLIS_PER_SECOND, TimeUnit.MILLISECONDS);
        }
    }
}
