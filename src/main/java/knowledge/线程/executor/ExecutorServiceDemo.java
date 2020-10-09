package knowledge.线程.executor;

import l.demo.Demo;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ExecutorService
 * https://jdk6.net/util-concurrent/ExecutorService.html
 * <p>
 * 线程池的主要功能是：
 * 1：控制线程数量
 * 2：重用线程 当开发任务量大时，或者有频繁创建线程任务执行的操作时，就应当使用线程池来调度线程
 * <p>
 * FixedThreadPool          可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程；适用于 Web 服务瞬时高峰，但需注意长时间持续高峰情况造成的队列阻塞
 * CachedThreadPool         可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们；适用于快速处理大量耗时较短的任务，如 Netty 的 NIO 接受请求时
 * SingleThreadExecutor     一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程
 * ScheduledThreadPool      一个线程池，它可安排在给延迟后运行命令或者定期地执行
 * <p>
 * 以上四种线程池都是使用 ThreadPoolExecutor 实现的，所以 ThreadPoolExecutor 更灵活
 */
public class ExecutorServiceDemo extends Demo {

    /**
     * FixedThreadPool
     */
    @Test
    public void testFixedThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            Runnable runnable = () -> {
                try {
                    Thread t = Thread.currentThread();
                    p(t.getName() + "：正在运行任务...");
                    TimeUnit.SECONDS.sleep(1);
                    p(t.getName() + "：运行任务完毕！");
                } catch (InterruptedException e) {
                    p("线程被中断了！");
                }
            };

            // void	        execute(Runnable command)
            // 在未来某个时间执行给定的命令
            pool.execute(runnable);   // 将任务指派给线程池
            p("指派了一个任务给线程池！");
        }

        // List<Runnable>	shutdownNow()
        // 试图停止所有正在执行的活动任务，暂停处理正在等待的任务，并返回等待执行的任务列表
        // pool.shutdownNow();

        // void	            shutdown()
        // 启动一次顺序关闭，执行以前提交的任务，但不接受新任务
        // pool.shutdown();

        // p("线程池停止了");
    }

    /**
     * SingleThreadExecutor
     * 该方法返回一个只有一个线程的线程池，即每次只能执行一个线程任务，多余的任务会保存到一个任务队列中，
     * 等待这一个线程空闲，当这个线程空闲了再按FIFO方式顺序执行任务队列中的任务
     */
    @Test
    public void testSingleThreadExecutor() {
        //创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newSingleThreadExecutor();

        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();

        //将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);

        //关闭线程池
        pool.shutdown();
    }

    private static class MyThread extends Thread {
        private static int[] arr = {1, 2, 3, 4, 5};
        private static int index = 0;

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
                p(Thread.currentThread().getName() + "正在执行。。。" + arr[index++]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
