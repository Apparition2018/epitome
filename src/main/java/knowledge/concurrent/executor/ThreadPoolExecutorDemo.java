package knowledge.concurrent.executor;

import l.demo.Demo;
import org.apache.commons.lang3.time.DateUtils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * ThreadPoolExecutor
 * https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/ThreadPoolExecutor.html
 * <p>
 * 线程池对待线程的策略:
 * 1.如果池中任务数 < corePoolSize -> 放入立即执行
 * 2.如果池中任务数 > corePoolSize -> 放入队列等待
 * 3.队列满 -> 新建线程立即执行
 * 4.执行中的线程 > maxPoolSize -> 触发handler（RejectedExecutionHandler）异常
 * <p>
 * corePoolSize         池中所保存的线程数，包括空闲线程
 * maximumPoolSize      池中允许的最大线程数
 * keepAliveTime        当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间
 * unit                 keepAliveTime 参数的时间单位
 * workQueue            执行前用于保持任务的队列。此队列仅保持由 execute() 提交的 Runnable 任务
 * threadFactory        执行程序创建新线程时使用的工厂
 * handler              由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序
 * <p>
 * 线程资源必须通过线程池提供，不允许在应用中自行显式创建线程（阿里编程规约）
 *
 * @author ljh
 * created on 2020/11/17 19:09
 */
public class ThreadPoolExecutorDemo extends Demo {

    /**
     * 不推荐使用Executors操作线程池类：https://www.jianshu.com/p/8f9ba86ddf13
     * 线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，规避资源耗尽的风险，阿里编程规约：
     * 1.FixedThreadPool 和 SingleThreadPool：允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM
     * 2.CachedThreadPool：允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM
     */
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3), new MyThreadFactory("test"), new MyRejectHandler());

        IntStream.rangeClosed(1, 10).forEach(i -> pool.execute(new MyTask(i)));
    }

    /**
     * 创建线程或线程池时请指定有意义的线程名称，方便出错时回溯（阿里编程规约）
     */
    private static class MyThreadFactory implements ThreadFactory {

        private final String namePrefix;
        private final AtomicInteger nextId = new AtomicInteger(1);

        private MyThreadFactory(String featureOfGroup) {
            namePrefix = "From LjhThreadFactory's " + featureOfGroup + "-Worker-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(namePrefix + nextId.getAndIncrement());
            p(thread.getName() + " is created");
            return thread;
        }
    }

    private static class MyRejectHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            printLog(r, executor);
        }

        private void printLog(Runnable r, ThreadPoolExecutor executor) {
            System.err.println(r.toString() + " is rejected, " + executor.toString());
        }
    }

    /**
     * ScheduledThreadPoolExecutor
     * 创建一个定长线程池，支持定时及周期性任务执行
     * https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/ScheduledThreadPoolExecutor.html
     */
    private static class ScheduledThreadPoolExecutorDemo extends Demo {

        public static void main(String[] args) {
            // ScheduledThreadPoolExecutor(int corePoolSize)
            // 使用给定核心池大小创建一个新 ScheduledThreadPoolExecutor
            ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

            // ScheduledFuture<?>	scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
            // 创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；
            // 也就是将在 initialDelay 后开始执行，然后在 initialDelay+period 后执行，接着在 initialDelay + 2 * period 后执行，依此类推
            exec.scheduleAtFixedRate(() -> {
                p("================");
            }, DateUtils.MILLIS_PER_SECOND, DateUtils.MILLIS_PER_SECOND * 6, TimeUnit.MILLISECONDS);

            exec.scheduleAtFixedRate(() -> p(System.nanoTime()), DateUtils.MILLIS_PER_SECOND, DateUtils.MILLIS_PER_SECOND * 2, TimeUnit.MILLISECONDS);
        }

    }
}