package knowledge.线程;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * void	            interrupt()         中断线程，在当前线程中打了一个停止标志，并不是真的停止线程，它不会中断一个正在运行的线程，只会中断阻塞过程中的线程
 * static boolean	interrupted()       测试当前线程（当前线程指 main 线程）是否已经中断，会清除线程的中断状态
 * boolean	        isInterrupted()     测试线程是否已经中断
 * <p>
 * https://www.cnblogs.com/huangyichun/p/7126851.html
 * https://blog.csdn.net/lx_Frolf/article/details/82560841
 * https://blog.csdn.net/github_34889651/article/details/52474869
 * http://www.runoob.com/java/thread-interrupt.html
 */
public class ThreadInterrupt {


    // 在 sleep() 时调用 interrupt()，会抛出 InterruptedException 异常
    @Test
    public void interruptWhenSleep() throws IOException, InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        System.out.println("在50秒之内按任意键中断线程!");
        System.in.read();
        thread.interrupt();
        thread.join();
        System.out.println("线程已经退出!");
    }

}
