package knowledge.线程;

import l.demo.Demo;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Thread
 * https://jdk6.net/lang/Thread.html
 * https://blog.csdn.net/u012426327/article/details/77160416
 * <p>
 * 线程：指进程中一个单一顺序的控制流，一个进程中可以并发多个线程，每条线程并行执行不同的任务。
 * 进程：括由操作系统分配的内存空间，包含一个或多个线程。
 * <p>
 * 线程状态：
 * 1.新建状态：使用 new 关键字和 Thread 类或其子类建立一个线程对象后，该线程对象就处于新建状态。它保持这个状态直到程序 start() 这个线程。
 * 2.就绪状态：当线程对象调用了start()方法之后，该线程就进入就绪状态。就绪状态的线程处于就绪队列中，要等待JVM里线程调度器的调度。
 * 3.运行状态：如果就绪状态的线程获取 CPU 资源，就可以执行 run()，此时线程便处于运行状态。处于运行状态的线程最为复杂，它可以变为阻塞状态、就绪状态和死亡状态。
 * 4.阻塞状态：如果一个线程执行了sleep（睡眠）、suspend（挂起）等方法，失去所占用资源之后，该线程就从运行状态进入阻塞状态。在睡眠时间已到或获得设备资源后可以重新进入就绪状态。
 * -    等待阻塞：运行状态中的线程执行 wait() 方法，使线程进入到等待阻塞状态。
 * -    同步阻塞：线程在获取 synchronized 同步锁失败(因为同步锁被其他线程占用)。
 * -    其他阻塞：通过调用线程的 sfleep() 或 join() 或 发出了 I/O 请求时，线程就会进入到阻塞状态。当 sleep() 状态超时，join() 等待线程终止或超时，或者 I/O 处理完毕，线程重新转入就绪状态。
 * 5.死亡状态：一个运行状态的线程完成任务或者其他终止条件发生时，该线程就切换到终止状态。
 * <p>
 * 线程是异步执行代码的
 * 异步运行：多段代码可以同时运行，各干个的
 * 同步运行：运行代码有先后顺序的一句一句执行
 * <p>
 * static void	                setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)  设置该线程由于未捕获到异常而突然终止时调用的处理程序
 * Thread.UncaughtExceptionHandler	        getUncaughtExceptionHandler()           返回该线程由于未捕获到异常而突然终止时调用的处理程序
 * static Thread.UncaughtExceptionHandler	getDefaultUncaughtExceptionHandler()    返回线程由于未捕获到异常而突然终止时调用的默认处理程序
 * static Map<Thread,StackTraceElement[]>	getAllStackTraces()                     返回所有活动线程的堆栈跟踪的一个映射
 * static int	                enumerate(Thread[] tarray)          将当前线程的线程组及其子组中的每一个活动线程复制到指定的数组中
 * static int	                activeCount()                       返回当前线程的线程组中活动线程的数目
 * static boolean	            holdsLock(Object obj)               当且仅当当前线程在指定的对象上保持监视器锁时，才返回 true
 * static void	                sleep(long millis[, int nanos])     在指定的毫秒数加指定的纳秒数内让当前正在执行的线程休眠（暂停执行），此操作受到系统计时器和调度程序精度和准确性的影响
 * static void	                dumpStack()                         将当前线程的堆栈跟踪打印至标准错误流
 * StackTraceElement[]	        getStackTrace()                     返回一个表示该线程堆栈转储的堆栈跟踪元素数组
 * ThreadGroup	                getThreadGroup()                    返回该线程所属的线程组
 * ClassLoader	                getContextClassLoader()             返回该线程的上下文 ClassLoader
 * void	                        checkAccess()                       判定当前运行的线程是否有权修改该线程
 * void	                        run()                               如果该线程是使用独立的 Runnable 运行对象构造的，则调用该 Runnable 对象的 run 方法；否则，该方法不执行任何操作并返回
 * void	                        start()                             使该线程开始执行；Java 虚拟机调用该线程的 run 方法
 * <p>
 * 如何使用 JUnit 测试异步代码：https://zhuanlan.zhihu.com/p/240281836
 */
public class ThreadDemo extends Demo {

    @Test
    public void testThread() {
        // 当前正在执行的线程对象的引用
        Thread t = Thread.currentThread();
        p("Thread = " + t);
        // 线程标识符
        p("id = " + t.getId());
        // 线程名字
        t.setName("test");
        p("name = " + t.getName());
        // 线程优先级 (1 ~ 10，default 5；还有一个级别为 0的，只属于 JVM)
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
     * void	            join([long millis, int nanos])  等待该线程终止的时间最长为 millis 毫秒 + nanos 纳秒
     */
    @Test
    public void join() throws InterruptedException {
        setCountDownLatch(1);
        final Thread download = new Thread(() -> {
            p("down:开始下载图片...");
            for (int i = 0; i < 100; i++) {
                p("down:" + i + "%");
                try {
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
                /*
                 * 当一个方法的局部内部类中想引用这个方法的其它局部变量，那么该变量必须是 final 的，这源自 JVM 的内存分配问题。
                 * JDK8 由于重构了 JVM 内存分配，解决了这个问题，就不再这样要求了
                 */
                download.join(); // void join(): 等待该线程终止
            } catch (InterruptedException e) {
                e.printStackTrace();
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
     * void	            setDaemon(boolean on)           将该线程标记为守护线程或用户线程
     * 守护线程又称为"后台线程"
     * 默认创建出来的线程都是前台线程，后台线程需要进行单独设置
     * 前台与后台线程使用没有区别，区别在于结束时机上，
     * 当一个进程结束时，进程中的所有后台程序会被强制中断，而进程的结束时机是当一个进程中的所有前台线程都结束时
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
                    e.printStackTrace();
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
                    e.printStackTrace();
                }
            }
        });

        rose.start();

        jack.setDaemon(true); // main 线程和 rose 线程结束后，jack 线程结束
        jack.start();
        countDownLatch.await();

        p("main线程结束了");

    }

    /**
     * static void	    yield()                         暂停当前正在执行的线程对象，并执行其他线程
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
     * void	            interrupt()         中断线程，在当前线程中打了一个停止标志，并不是真的停止线程，它不会中断一个正在运行的线程，只会中断阻塞过程中的线程
     * static boolean	interrupted()       测试当前线程（当前线程指 main 线程）是否已经中断，会清除线程的中断状态
     * boolean	        isInterrupted()     测试线程是否已经中断
     * <p>
     * Java终止线程的三种方式：https://www.cnblogs.com/liyutian/p/10196044.html
     * Thread的中断机制(interrupt)，循环线程停止的方法：https://www.cnblogs.com/panchanggui/p/9668284.html
     * interrupt(), interrupted(), isInterrupted() 区别：https://www.cnblogs.com/huangyichun/p/7126851.html
     */
    @Test
    public void interrupt() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 10000; i++) {
                    sleep(3, TimeUnit.MILLISECONDS);
                    p(i);
                    if (Thread.currentThread().isInterrupted()) {
                        p("线程中断");
                        break;
                    }
                }
                countDownLatch.countDown();
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        });

        thread.start();
        TimeUnit.MILLISECONDS.sleep(100);
        p("发送中断请求");
        thread.interrupt();
    }

}
