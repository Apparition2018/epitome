package knowledge.concurrent.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * CountDownLatch
 * CountDownLatch 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待
 * https://www.runoob.com/manual/jdk1.6/java.base/java/util/concurrent/CountDownLatch.html
 * <p>
 * void	        await()                                 使当前线程在锁存器倒计数至零之前一直等待，除非线程被中断
 * boolean	    await(long timeout, TimeUnit unit)      使当前线程在锁存器倒计数至零之前一直等待，除非线程被中断或超出了指定的等待时间
 * void	        countDown()                             递减锁存器的计数，如果计数到达零，则释放所有等待的线程
 * long	        getCount()                              返回当前计数
 *
 * @author ljh
 * created on 2020/11/17 19:09
 */
public class CountDownLatchDemo {

    /**
     * 案例：百米赛跑，多个参加赛跑的人员在听到发令枪响后，开始跑步，到达终点后结束计时，然后统计平均成绩
     */
    @Test
    public void testCountDownLatch() throws InterruptedException, ExecutionException {
        class Runner implements Callable<Integer> {

            // 开始信号
            private CountDownLatch begin;
            // 结束信号
            private CountDownLatch end;

            private Runner(CountDownLatch begin, CountDownLatch end) {
                this.begin = begin;
                this.end = end;
            }

            @Override
            public Integer call() throws Exception {
                // 跑步的成绩
                int score = new Random().nextInt(25);
                // 等待发令枪响起
                begin.await();
                // 跑步中 ....
                TimeUnit.MILLISECONDS.sleep(score);
                // 跑步者已经跑完全程
                end.countDown();
                return score;
            }
        }

        // 参加跑步人数
        int num = 10;
        // 发令枪只响一次
        CountDownLatch begin = new CountDownLatch(1);
        // 参与跑步有多个
        CountDownLatch end = new CountDownLatch(num);
        // 每个跑步者一个跑道
        ExecutorService pool = Executors.newFixedThreadPool(num);
        // 记录比赛成绩
        List<Future<Integer>> futures = new ArrayList<>();
        // 跑步者就位，所有线程处于等待状态
        IntStream.rangeClosed(1, 10).forEach((i) -> futures.add(pool.submit(new Runner(begin, end))));
        // 模拟热身时间
        TimeUnit.SECONDS.sleep(1);
        // 发令枪响，跑步者开始跑步
        begin.countDown();
        // 等待所有跑步者跑完全程
        end.await();
        int count = 0;
        // 统计总分
        for (Future<Integer> future : futures) {
            System.out.println(future.get());
            count += future.get();
        }
        System.out.println("平均分数为：" + count / num);
    }

}
