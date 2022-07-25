package knowledge.concurrent.util;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * CountDownLatch
 * CountDownLatch 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待
 * https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/CountDownLatch.html
 * <p>
 * void	        await()                                 使当前线程在锁存器倒计数至零之前一直等待，除非线程被中断
 * boolean	    await(long timeout, TimeUnit unit)      使当前线程在锁存器倒计数至零之前一直等待，除非线程被中断或超出了指定的等待时间
 * void	        countDown()                             递减锁存器的计数，如果计数到达零，则释放所有等待的线程
 * long	        getCount()                              返回当前计数
 * <p>
 * 阿里编程规约：
 * 使用 CountDownLatch 进行异步转同步操作，每个线程退出前必须调用 countDown 方法，
 * 线程执行代码注意 catch 异常，确保 countDown 方法被执行到，避免主线程无法执行至 await 方法，直到超时才返回结果
 * 注意，子线程抛出异常堆栈，不能在主线程 try-catch 到
 *
 * @author ljh
 * created on 2020/11/17 19:09
 */
public class CountDownLatchDemo extends Demo {

    /**
     * 案例：百米赛跑，多个参加赛跑的人员在听到发令枪响后，开始跑步，到达终点后结束计时，然后统计平均成绩
     */
    @Test
    public void testCountDownLatch() throws InterruptedException, ExecutionException {
        class Runner implements Callable<Integer> {

            // 开始信号
            private final CountDownLatch begin;
            // 结束信号
            private final CountDownLatch end;

            private Runner(CountDownLatch begin, CountDownLatch end) {
                this.begin = begin;
                this.end = end;
            }

            @Override
            public Integer call() {
                int score;
                try {
                    int r = randomInt(1, 9);
                    if (r == 1) {
                        // 模拟事故
                        score = 1 / 0;
                    } else {
                        // 跑步的成绩
                        score = new Random().nextInt(25);
                    }
                    // 等待发令枪响起
                    begin.await();
                    // 跑步中 ....
                    TimeUnit.MILLISECONDS.sleep(score);
                } catch (Exception e) {
                    score = 0;
                }
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
        ExecutorService threadPool = Executors.newFixedThreadPool(num);
        // 记录比赛成绩
        List<Future<Integer>> futures = new ArrayList<>();
        // 跑步者就位，所有线程处于等待状态
        IntStream.rangeClosed(1, 10).forEach((i) -> futures.add(threadPool.submit(new Runner(begin, end))));
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
