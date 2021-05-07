package knowledge.concurrent.util;

import l.demo.Demo;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier
 * 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。
 * 在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。
 * 因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrie。
 * https://www.runoob.com/manual/jdk1.6/java.base/java/util/concurrent/CyclicBarrier.html
 * <p>
 * CyclicBarrier(int parties[, Runnable barrierAction])
 * 创建一个新的 CyclicBarrier，它将在给定数量的参与者（线程）处于等待状态时启动，并在启动 barrier 时执行给定的屏障操作，该操作由最后一个进入 barrier 的线程执行
 * <p>
 * int	        await()                             在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待
 * int	        await(long timeout, TimeUnit unit)  在所有参与者都已经在此屏障上调用 await 方法之前将一直等待,或者超出了指定的等待时间
 * int	        getNumberWaiting()                  返回当前在屏障处等待的参与者数目
 * int	        getParties()                        返回要求启动此 barrier 的参与者数目
 * boolean	    isBroken()                          查询此屏障是否处于损坏状态
 * void	        reset()                             将屏障重置为其初始状态
 *
 * @author ljh
 * created on 2020/11/17 19:09
 */
public class CyclicBarrierDemo extends Demo {

    /**
     * 案例：两个工人从两端挖掘隧道，各自独立奋战，中间不沟通，如果两个人在汇合点处碰头了，则表明隧道已经挖通。
     * http://www.blogjava.net/polang1002/archive/2015/02/27/423067.html
     */
    public static void main(String[] args) {
        // 设置汇集数量，以及汇集完成后的任务
        // CyclicBarrier(int parties[, Runnable barrierAction])
        // 创建一个新的 CyclicBarrier，它将在给定数量的参与者（线程）处于等待状态时启动，并在启动 barrier 时执行给定的屏障操作，该操作由最后一个进入 barrier 的线程执行
        CyclicBarrier cb = new CyclicBarrier(2, () -> p("隧道已经打通！"));
        // 工人1挖隧道
        new Thread(new Worker(cb), "工人1").start();
        // 工人2挖隧道
        new Thread(new Worker(cb), "工人2").start();
    }

    private static class Worker implements Runnable {
        // 关卡
        private CyclicBarrier cb;

        private Worker(CyclicBarrier cb) {
            this.cb = cb;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                p(Thread.currentThread().getName() + "- 到达汇合点");
                // 到达汇合点
                // int	    await([long timeout, TimeUnit unit])
                // 等待所有 parties已经在此屏障上调用 await ，或指定的等待时间过去
                cb.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
