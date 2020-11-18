package knowledge.concurrent.executor;

import l.demo.Demo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * FutureTask
 * FutureTask 是 Future 和 Callable 的结合体
 * https://blog.csdn.net/zmx729618/article/details/51596414
 */
public class FutureTaskDemo extends Demo {

    /**
     * Future 实现
     */
    @Test
    public void testFuture() {

        ExecutorService pool = Executors.newFixedThreadPool(5);

        List<Future<Map<Integer, String>>> results = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {

            // <T> Future<T>	submit(Callable<T> task)
            // 提交一个 Callable 任务用于执行，并返回一个表示该任务的 Future
            Future<Map<Integer, String>> future = pool.submit(new MyCallable(i));
            p("指派了一个任务 " + i + " 给线程池！");
            results.add(future);
        }

        boolean flag = true;
        while (flag) {
            for (Iterator<Future<Map<Integer, String>>> it = results.iterator(); it.hasNext(); ) {
                Future<Map<Integer, String>> future = it.next();

                // boolean	    isDone()
                // 如果任务已完成，则返回 true
                if (future.isDone()) {
                    try {

                        // V	get([long timeout, TimeUnit unit])
                        // 如有必要，最多等待为使计算完成所给定的时间之后，获取其结果（如果结果可用），会阻塞主线程
                        Map<Integer, String> callMap = future.get();
                        for (Map.Entry<Integer, String> entry : callMap.entrySet()) {
                            p(entry.getValue() + "：运行任务 " + entry.getKey() + " 完毕！");
                        }
                        it.remove();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (results.size() == 0) {
                flag = false;
            }
        }

        pool.shutdownNow();

    }

    /**
     * 使用 FutureTask 实现上面例子，解决多任务结果，效果最优
     */
    @Test
    public void testFutureTask() throws InterruptedException {
        setCountDownLatch(5);
        ExecutorService pool = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 5; i++) {
            // FutureTask(Callable<V> callable)
            // 创建一个 FutureTask，一旦运行就执行给定的 Callable
            MyFutureTask ft = new MyFutureTask(new MyCallable(i));
            pool.submit(ft);
            p("指派了一个任务 " + i + " 给线程池！");
        }
        countDownLatch.await();
        pool.shutdown();
    }

    private static class MyFutureTask extends FutureTask<Map<Integer, String>> {

        MyFutureTask(Callable<Map<Integer, String>> callable) {
            super(callable);
        }

        @Override
        public void done() {
            try {
                // V	get([long timeout, TimeUnit unit])
                // 如有必要，最多等待为使计算完成所给定的时间之后，获取其结果（如果结果可用），会阻塞主线程
                Map<Integer, String> callMap = get();
                for (Map.Entry<Integer, String> entry : callMap.entrySet()) {
                    p(entry.getValue() + "：运行任务 " + entry.getKey() + " 完毕！");
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}
