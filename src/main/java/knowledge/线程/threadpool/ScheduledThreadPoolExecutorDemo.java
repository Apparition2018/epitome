package knowledge.线程.threadpool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolExecutor
 * <p>
 * 创建一个定长线程池，支持定时及周期性任务执行
 */
public class ScheduledThreadPoolExecutorDemo {

    public static void main(String[] args) {
        // ScheduledThreadPoolExecutor(int corePoolSize)
        // 使用给定核心池大小创建一个新 ScheduledThreadPoolExecutor
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

        // ScheduledFuture<?>	scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
        // 创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；
        // 也就是将在 initialDelay 后开始执行，然后在 initialDelay+period 后执行，接着在 initialDelay + 2 * period 后执行，依此类推
        exec.scheduleAtFixedRate(() -> {
            System.out.println("================");
        }, 1000, 6000, TimeUnit.MILLISECONDS);

        exec.scheduleAtFixedRate(() -> System.out.println(System.nanoTime()), 1000, 2000, TimeUnit.MILLISECONDS);
    }

}
