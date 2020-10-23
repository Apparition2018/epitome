package knowledge.线程.executor;

import l.demo.Demo;
import lombok.ToString;
import org.apache.commons.lang3.time.DateUtils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadPoolExecutor
 * http://tool.oschina.net/uploads/apidocs/jdk-zh/index.html?java/util/concurrent/ThreadPoolExecutor.html
 * <p>
 * corePoolSize         池中所保存的线程数，包括空闲线程
 * maximumPoolSize      池中允许的最大线程数
 * keepAliveTime        当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间
 * unit                 keepAliveTime 参数的时间单位
 * workQueue            执行前用于保持任务的队列。此队列仅保持由 execute() 提交的 Runnable 任务
 * threadFactory        执行程序创建新线程时使用的工厂
 * handler              由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序
 */
public class ThreadPoolExecutorDemo {

    /**
     * 不推荐使用Executors操作线程池类：https://www.jianshu.com/p/8f9ba86ddf13
     */
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2), new MyThreadFactory(), new MyRejectHandler());

        for (int i = 0; i < 10; i++) {
            pool.execute(new MyTask(i));
        }
    }

    @ToString
    private static class MyTask implements Runnable {

        private int i;

        public MyTask(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(this.toString() + " 正在执行");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MyThreadFactory implements ThreadFactory {

        private final AtomicInteger count = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("ljh-thread-" + count.getAndIncrement());
            System.out.println(thread.getName() + " is created");
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
     * https://jdk6.net/util-concurrent/ScheduledThreadPoolExecutor.html
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