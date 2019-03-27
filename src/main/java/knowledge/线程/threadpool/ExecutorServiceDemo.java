package knowledge.线程.threadpool;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExecutorService
 * <p>
 * 线程池的主要功能是：
 * 1：控制线程数量
 * 2：重用线程 当开发任务量大时，或者有频繁创建线程任务执行的操作时，就应当使用线程池来调度线程
 * <p>
 * FixedThreadPool          可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程
 * --                       适用于 Web 服务瞬时高峰，但需注意长时间持续高峰情况造成的队列阻塞
 * CachedThreadPool         可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们
 * --                       快速处理大量耗时较短的任务，如 Netty 的 NIO 接受请求时
 * SingleThreadExecutor     一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程
 * ScheduledThreadPool      一个线程池，它可安排在给延迟后运行命令或者定期地执行
 */
public class ExecutorServiceDemo {

    @Test
    public void newFixedThreadPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            Runnable runnable = () -> {
                try {
                    Thread t = Thread.currentThread();
                    System.out.println(t.getName() + "：正在运行任务...");
                    Thread.sleep(1000);
                    System.out.println(t.getName() + "：运行任务完毕！");
                } catch (InterruptedException e) {
                    System.out.println("线程被中断了！");
                }
            };

            // void	execute(Runnable command)
            // 在未来某个时间执行给定的命令
            threadPool.execute(runnable);   // 将任务指派给线程池
            System.out.println("指派了一个任务给线程池！");
        }

        // List<Runnable>	shutdownNow()
        // 试图停止所有正在执行的活动任务，暂停处理正在等待的任务，并返回等待执行的任务列表
        // threadPool.shutdownNow();

        // void	shutdown()
        // 启动一次顺序关闭，执行以前提交的任务，但不接受新任务
        // threadPool.shutdown();

        // System.out.println("线程池停止了");
    }

    /**
     * Runnable 转为 Callable
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
