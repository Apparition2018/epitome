package knowledge.concurrent;

import l.demo.Demo;

import java.util.concurrent.TimeUnit;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/Runnable.html">Runnable</a>
 * <pre>
 * void	    run()       使用实现接口 Runnable 的对象创建一个线程时，启动该线程将导致在独立执行的线程中调用对象的 run 方法。
 * </pre>
 * <a href="https://www.jianshu.com/p/333ce4b3d5b8">implements Runnable vs extends Thread</a>
 * <pre>
 * 1 通过 implements Runnable 创建线程，可以避免单继承的局限性
 * 2 通过 new Thread(Runnable) 实现资源共享，由于 Thread 本身也 implements Runnable，所以两者均可实现资源共享
 * </pre>
 *
 * @author ljh
 * @since 2020/11/17 19:09
 */
public class RunnableDemo extends Demo {

    public static void main(String[] args) throws InterruptedException {
        setCountDownLatch(3);
        TicketRunnable ticketRunnable = new TicketRunnable();
        new Thread(ticketRunnable).start();
        new Thread(ticketRunnable).start();
        new Thread(ticketRunnable).start();
        countDownLatch.await();
    }

    private static class TicketRunnable implements Runnable {
        private int ticket = 5;

        @Override
        public synchronized void run() {
            while (ticket > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                p(Thread.currentThread().getName() + "正在卖票" + ticket--);
            }
            countDownLatch.countDown();
        }
    }
}
