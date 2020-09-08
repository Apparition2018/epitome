package knowledge.线程;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Thread
 * <p>
 * 线程：指进程中一个单一顺序的控制流，一个进程中可以并发多个线程，每条线程并行执行不同的任务。
 * 进程：括由操作系统分配的内存空间，包含一个或多个线程。
 * <p>
 * 线程状态：
 * 1.新建状态：使用 new 关键字和 Thread 类或其子类建立一个线程对象后，该线程对象就处于新建状态。它保持这个状态直到程序 start() 这个线程。
 * 2.就绪状态：当线程对象调用了start()方法之后，该线程就进入就绪状态。就绪状态的线程处于就绪队列中，要等待JVM里线程调度器的调度。
 * 3.运行状态：如果就绪状态的线程获取 CPU 资源，就可以执行 run()，此时线程便处于运行状态。处于运行状态的线程最为复杂，它可以变为阻塞状态、就绪状态和死亡状态。
 * 4.阻塞状态：如果一个线程执行了sleep（睡眠）、suspend（挂起）等方法，失去所占用资源之后，该线程就从运行状态进入阻塞状态。在睡眠时间已到或获得设备资源后可以重新进入就绪状态。
 * (1)等待阻塞：运行状态中的线程执行 wait() 方法，使线程进入到等待阻塞状态。
 * (2)同步阻塞：线程在获取 synchronized 同步锁失败(因为同步锁被其他线程占用)。
 * (3)其他阻塞：通过调用线程的 sleep() 或 join() 发出了 I/O 请求时，线程就会进入到阻塞状态。当sleep() 状态超时，join() 等待线程终止或超时，或者 I/O 处理完毕，线程重新转入就绪状态。
 * 5.死亡状态：一个运行状态的线程完成任务或者其他终止条件发生时，该线程就切换到终止状态。
 * <p>
 * 线程是异步执行代码的
 * 异步运行：多段代码可以同时运行，各干个的
 * 同步运行：运行代码有先后顺序的一句一句执行
 * <p>
 * 多线程能满足程序员编写高效率的程序来达到充分利用 CPU 的目的。
 * <p>
 * static void	sleep(long millis[, int nanos])     在指定的毫秒数加指定的纳秒数内让当前正在执行的线程休眠（暂停执行），此操作受到系统计时器和调度程序精度和准确性的影响
 * void	        setName(String name)                改变线程名称，使之与参数 name 相同
 */
public class ThreadDemo {


    /**
     * Thread([Runnable target, String name])
     * 分配新的 Thread 对象
     */
    @Test
    public void thread() {
        Thread main = Thread.currentThread();
        System.out.println("main线程：" + main);
        currentThread();

        Thread t = new Thread(() -> {
            Thread t1 = Thread.currentThread();
            System.out.println("自定义线程：" + t1);
            currentThread();
        }, "自定义线程");
        t.start();
    }

    /**
     * static Thread	currentThread()
     * 返回对当前正在执行的线程对象的引用
     */
    @Test
    public void currentThread() {
        Thread t = Thread.currentThread();
        System.out.println("doSome线程：" + t);
    }

    // 获取线程信息的相关方法
    @Test
    public void getInfo() {
        Thread t = Thread.currentThread();

        // 线程名字
        String name = t.getName();
        System.out.println("name = " + name);

        // 线程ID
        long id = t.getId();
        System.out.println("id = " + id);

        // 线程优先级 (1 ~ 10, default 5)
        int priority = t.getPriority();
        System.out.println("priority = " + priority);

        // 线程状态
        Thread.State state = t.getState();
        System.out.println("state = " + state); // RUNNABLE

        // 是否处于活动状态
        boolean isAlive = t.isAlive();
        System.out.println("isAlive = " + isAlive);

        // 是否为守护线程
        boolean isDaemon = t.isDaemon();
        System.out.println("isDaemon = " + isDaemon);

        // 是否中断
        boolean isInterrupted = t.isInterrupted();
        System.out.println("isInterrupted = " + isInterrupted);

        // 线程所属线程组
        ThreadGroup tg = t.getThreadGroup();
        System.out.println("ThreadGroup = " + tg);

    }

    private static boolean isFinish = false; // 图片是否下载完毕

    /**
     * void	join([long millis, int nanos])
     * 等待该线程终止的时间最长为 millis 毫秒 + nanos 纳秒
     */
    @Test
    public void join() {
        final Thread download = new Thread(() -> {
            System.out.println("down:开始下载图片...");
            for (int i = 0; i < 100; i++) {
                System.out.println("down:" + i + "%");
                try {
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("down:图片下载完毕！");
            isFinish = true;
        });

        Thread show = new Thread(() -> {
            System.out.println("show:开始显示图片...");
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
            System.out.println("show:显示图片完毕！");
        });

        download.start();
        show.start();
    }

    /**
     * void	setDaemon(boolean on)
     * 将该线程标记为守护线程或用户线程
     * <p>
     * 守护线程又称为"后台线程"
     * 默认创建出来的线程都是前台线程，后台线程需要进行单独设置
     * 前台与后台线程使用没有区别，区别在于结束时机上，
     * 当一个进程结束时，进程中的所有后台程序会被强制中断，而进程的结束时机是当一个进程中的所有前台线程都结束时
     */
    @Test
    public void setDaemon() {
        Thread rose = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("rose: let me go!");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("rose: 啊啊啊AAAaaa...");
            System.out.println("音效：噗通！");
        });

        Thread jack = new Thread(() -> {
            while (true) {
                System.out.println("jack: you jump! I jump!");
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

        System.out.println("main线程结束了");

    }

    /**
     * void	setPriority(int newPriority)
     * 更改线程的优先级
     */
    @Test
    public void setPriority() {
        Thread max = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("max");
            }
        });
        Thread nor = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("nor");
            }
        });
        Thread min = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("min");
            }
        });

        max.setPriority(Thread.MAX_PRIORITY); // 10
        min.setPriority(Thread.MIN_PRIORITY); // 1；其实还有一个级别为0的，但只属于JVM

        max.start();
        nor.start();
        min.start();
    }

    /**
     * static void	yield()
     * 暂停当前正在执行的线程对象，并执行其他线程
     */
    @Test
    public void yield() {
        yieldThread("A");
        yieldThread("B");
        yieldThread("C");
    }

    private void yieldThread(String threadName) {
        new Thread(threadName) {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    if ((i % 2) == 0) {
                        System.out.println(Thread.currentThread().getName() + " yielding control...");
                        Thread.yield();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " has finished executing.");
            }
        }.start();
    }

}

