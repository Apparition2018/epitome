package knowledge.concurrent;

import knowledge.suggestions.Suggestions;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/Thread.html">Thread</a>
 * <pre>
 * 进程：具有一定独立功能的程序关于某个数据集合上的一次运行活动，是操作系统分配资源的最小单元。
 * 线程：指进程中一个单一顺序的控制流，是操作系统调度的最小单元，有自己程序计数器、一组寄存器和栈。
 *
 * 异步运行：多段代码可以同时运行，各干个的
 * 同步运行：运行代码有先后顺序的一句一句执行
 *
 * 并发；可以处理多个任务；指两个或多个指令在同一时间间隔内执行，是逻辑层面上的同时工作
 * 并行：同时处理多个任务；指两个或多个指令在同一时刻执行，是物理层面上的同时工作
 * 并发与并行的区别是什么？：https://www.zhihu.com/question/33515481/answer/58849148
 *
 * 线程安全：如果有多个线程在同时运行，而这些线程可能会同时运行这段代码。程序每次运行结果和单线程运行的结果是一样的，而且其他的变量的值也和预期的是一样的，就是线程安全的。
 * 线程同步：将操作共享数据的代码行作为一个整体，同一时间只允许一个线程执行，执行过程中其他线程不能参与执行。
 * 线程安全及三种解决方案：https://zhuanlan.zhihu.com/p/143811831
 * </pre>
 * 线程状态：
 * <pre>
 * 1 新建状态：使用 new 关键字和 Thread 类或其子类建立一个线程对象后，该线程对象就处于新建状态。它保持这个状态直到程序 start() 这个线程。
 * 2 就绪状态：当线程对象调用了 start() 之后，该线程就进入就绪状态。就绪状态的线程处于就绪队列中，要等待 JVM 里线程调度器的调度。
 * 3 运行状态：如果就绪状态的线程获取 CPU 资源，就可以执行 run()，此时线程便处于运行状态。处于运行状态的线程最为复杂，它可以变为阻塞状态、就绪状态和死亡状态。
 * 4 阻塞状态：如果一个线程执行了sleep（睡眠）、suspend（挂起）等方法，失去所占用资源之后，该线程就从运行状态进入阻塞状态。在睡眠时间已到或获得设备资源后可以重新进入就绪状态。
 *      等待阻塞：运行状态中的线程执行 wait() 方法，使线程进入到等待阻塞状态。
 *      同步阻塞：线程在获取 synchronized 同步锁失败(因为同步锁被其他线程占用)。
 *      其他阻塞：通过调用线程的 sleep() 或 join() 或 发出了 I/O 请求时，线程就会进入到阻塞状态。当 sleep() 状态超时，join() 等待线程终止或超时，或者 I/O 处理完毕，线程重新转入就绪状态。
 * 5 死亡状态：一个运行状态的线程完成任务或者其他终止条件发生时，该线程就切换到终止状态。
 * </pre>
 * <pre>
 * static void	                setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)  设置该线程由于未捕获到异常而突然终止时调用的处理程序
 * Thread.UncaughtExceptionHandler          getUncaughtExceptionHandler()           返回该线程由于未捕获到异常而突然终止时调用的处理程序
 * static Thread.UncaughtExceptionHandler   getDefaultUncaughtExceptionHandler()    返回线程由于未捕获到异常而突然终止时调用的默认处理程序
 * static Map<Thread,StackTraceElement[]>   getAllStackTraces()                     返回所有活动线程的堆栈跟踪的一个映射
 * static int                   enumerate(Thread[] tarray)          将当前线程的线程组及其子组中的每一个活动线程复制到指定的数组中
 * static int                   activeCount()                       返回当前线程的线程组中活动线程的数目
 * static boolean               holdsLock(Object obj)               当且仅当当前线程在指定的对象上保持监视器锁时，才返回 true
 * static void                  sleep(long millis[, int nanos])     在指定的毫秒数加指定的纳秒数内让当前正在执行的线程休眠（暂停执行），此操作受到系统计时器和调度程序精度和准确性的影响
 * static void                  dumpStack()                         将当前线程的堆栈跟踪打印至标准错误流
 * StackTraceElement[]          getStackTrace()                     返回一个表示该线程堆栈转储的堆栈跟踪元素数组
 * ThreadGroup                  getThreadGroup()                    返回该线程所属的线程组
 * ClassLoader                  getContextClassLoader()             返回该线程的上下文 ClassLoader
 * void                         checkAccess()                       判定当前运行的线程是否有权修改该线程
 * void                         run()                               如果该线程是使用独立的 Runnable 运行对象构造的，则调用该 Runnable 对象的 run 方法；否则，该方法不执行任何操作并返回
 * void                         start()                             使该线程开始执行；Java 虚拟机调用该线程的 run 方法；多次启动一个线程是非法的
 * </pre>
 * 参考：<a href="https://zhuanlan.zhihu.com/p/240281836">如何使用 JUnit 测试异步代码</a>
 *
 * @author ljh
 * @since 2020/11/17 19:09
 */
public class ThreadDemo extends Demo {

    @Test
    public void testThread() {
        // 当前正在执行的线程对象的引用
        Thread t = Thread.currentThread();
        p("Thread = " + t);
        // 线程标识符
        p("id = " + t.getId());
        // 线程名称
        // 创建线程或线程池时请指定有意义的线程名称，方便出错时回溯（阿里编程规约）
        t.setName("test");
        p("name = " + t.getName());
        // 线程优先级 (1 ~ 10，default 5；还有一个级别为 0的，只属于 JVM)
        // 建议只使用 MIN, NORMAL, MAX 三个级别：1.线程并不是严格按照优先级来执行；2.优先级差别越大，运行机会差别越明显
        t.setPriority(Thread.MAX_PRIORITY);
        p("priority = " + t.getPriority());
        // 线程状态
        p("state = " + t.getState());
        // 是否处于活动状态
        p("isAlive = " + t.isAlive());
        // 是否为守护线程
        p("isDaemon = " + t.isDaemon());
        // 是否中断
        p("isInterrupted = " + t.isInterrupted());
    }

    @Test
    public void testThread2() throws InterruptedException {
        setCountDownLatch(1);
        Thread main = Thread.currentThread();
        p("main线程：" + main);
        subThread();

        // Thread(Runnable target, String name)         分配新的 Thread 对象
        Thread t = new Thread(() -> {
            Thread t1 = Thread.currentThread();
            p("自定义线程：" + t1);
            subThread();
            countDownLatch.countDown();
        }, "自定义线程");
        t.start();
        countDownLatch.await();
    }

    public void subThread() {
        Thread t = Thread.currentThread();
        p("子线程：" + t);
    }

    private static boolean isFinish = false; // 图片是否下载完毕

    /**
     * void             join([long millis, int nanos])  等待该线程终止的时间最长为 millis 毫秒 + nanos 纳秒
     */
    @Test
    public void join() throws InterruptedException {
        setCountDownLatch(1);
        Thread download = new Thread(() -> {
            p("down:开始下载图片...");
            for (int i = 1; i <= 100; i++) {
                p("down:" + i + "%");
                try {
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            p("down:图片下载完毕！");
            isFinish = true;
            countDownLatch.countDown();
        });

        Thread show = new Thread(() -> {
            p("show:开始显示图片...");
            /*
             * 等待下载线程先将图片下载完毕
             * 当 show 线程执行到 download.join() 方法时，show 线程进入阻塞状态，
             * 直到 download 线程将任务执行完毕 (download 线程结束) 时才会接触阻塞继续向下运行代码
             */
            try {
                download.join(); // void join(): 等待该线程终止
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!isFinish) {
                throw new RuntimeException("图片没有下载完毕！");
            }
            p("show:显示图片完毕！");
        });

        download.start();
        show.start();
        countDownLatch.await();
    }

    /**
     * void             setDaemon(boolean on)           将该线程标记为守护线程或用户线程
     * <p>注意事项：
     * <pre>
     * 1 不要在守护线程中执行业务逻辑操作
     * 2 setDaemon(true)，必须在 start() 之前执行
     * 3 在守护线程中产生的新线程也是守护线程
     * </pre>
     */
    @Test
    public void setDaemon() throws InterruptedException {
        setCountDownLatch(1);
        Thread rose = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                p("rose: let me go!");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            p("rose: 啊啊啊AAAaaa...");
            p("音效：噗通！");
            countDownLatch.countDown();
        });

        Thread jack = new Thread(() -> {
            while (true) {
                p("jack: you jump! I jump!");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        rose.start();

        // main 线程和 rose 线程结束后，jack 线程结束
        jack.setDaemon(true);
        jack.start();
        countDownLatch.await();

        p("main线程结束了");

    }

    /**
     * static void      yield()                         暂停当前正在执行的线程对象，并执行其他线程
     */
    @Test
    public void yield() throws InterruptedException {
        setCountDownLatch(3);
        yieldThread("A");
        yieldThread("B");
        yieldThread("C");
        countDownLatch.await();
    }

    private void yieldThread(String threadName) {
        new Thread(threadName) {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    if ((i % 2) == 0) {
                        p(Thread.currentThread().getName() + " yielding control...");
                        Thread.yield();
                    }
                }
                p(Thread.currentThread().getName() + " has finished executing.");
                countDownLatch.countDown();
            }
        }.start();
    }

    /**
     * <pre>
     * void             interrupt()         中断线程，在当前线程中打了一个停止标志，并不是真的停止线程，它不会中断一个正在运行的线程，只会中断阻塞过程中的线程
     * static boolean   interrupted()       测试当前线程（当前线程指 main 线程）是否已经中断，会清除线程的中断状态
     * boolean          isInterrupted()     测试线程是否已经中断，不会清除线程的中断状态
     * </pre>
     *
     * @see Suggestions#test120()
     * @see <a href="https://www.cnblogs.com/liyutian/p/10196044.html">Java 终止线程的三种方式</a>
     * @see <a href="https://www.cnblogs.com/panchanggui/p/9668284.html">Thread 的中断机制 (interrupt)，循环线程停止的方法</a>
     * @see <a href="https://blog.csdn.net/zwx900102/article/details/106741458">Thread 生命周期及 interrupted() 作用分析</a>
     * @see <a href="https://zhuanlan.zhihu.com/p/73302931">如何理解 java 中的中断机制</a>
     */
    @Test
    public void interrupt() {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < THOUSAND; i++) {
                    TimeUnit.MILLISECONDS.sleep(3);
                    p(i);
                    if (Thread.currentThread().isInterrupted()) {
                        p("线程中断1");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                // 抛出 InterruptedException 异常后，会清除中断状态
                // 因为线程为了处理异常，必须重新进入就绪状态
                p(Thread.currentThread().isInterrupted());
                // 看情况是否需要重新设置中断标记
                Thread.currentThread().interrupt();
                p("线程中断2");
            }
        });

        thread.start();
        sleep(100, TimeUnit.MILLISECONDS);
        p("发送中断请求");
        thread.interrupt();
    }
}
