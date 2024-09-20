package knowledge.concurrent.future;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * FutureTask
 * <p>FutureTask 是 Future 和 Callable 的结合体
 *
 * @author ljh
 * @see <a href="https://blog.csdn.net/zmx729618/article/details/51596414">FutureTask & Future</a>
 * @since 2020/11/17 19:09
 */
public class FutureTaskDemo extends Demo {

    private final static int NUM_OF_TASK = 5;
    private final static ExecutorService threadPool = Executors.newFixedThreadPool(5, new MyThreadFactory());

    /**
     * Future 是一个抽象的概念，它表示一个值，在某一点会变得可用。
     */
    @Test
    public void testFuture() {
        List<Future<Map<Integer, String>>> futureList = new ArrayList<>();
        for (int i = 1; i <= NUM_OF_TASK; i++) {
            // <T> Future<T>	submit(Callable<T> task)
            // 提交一个 Callable 任务用于执行，并返回一个表示该任务的 Future
            Future<Map<Integer, String>> future = threadPool.submit(new MyCallable(i));
            p(String.format("指派了一个任务 %s 给线程池！", i));
            futureList.add(future);
        }

        boolean flag = true;
        while (flag) {
            for (Iterator<Future<Map<Integer, String>>> it = futureList.iterator(); it.hasNext(); ) {
                Future<Map<Integer, String>> future = it.next();

                // boolean	    isDone()
                // 如果任务已完成，则返回 true
                if (future.isDone()) {
                    try {

                        // V	get([long timeout, TimeUnit unit])
                        // 如有必要，最多等待为使计算完成所给定的时间之后，获取其结果（如果结果可用），会阻塞主线程
                        Map<Integer, String> callMap = future.get();
                        callMap.forEach((k, v) -> p(String.format("%s：任务 %s 运行完毕！", k, v)));
                        it.remove();
                    } catch (ExecutionException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if (futureList.isEmpty()) {
                flag = false;
            }
        }
        threadPool.shutdownNow();
    }

    /**
     * 使用 FutureTask 实现上面例子
     */
    @Test
    public void testFutureTask() throws InterruptedException {
        setCountDownLatch(5);

        for (int i = 1; i <= NUM_OF_TASK; i++) {
            // FutureTask(Callable<V> callable)
            // 创建一个 FutureTask，一旦运行就执行给定的 Callable
            MyFutureTask ft = new MyFutureTask(new MyCallable(i));
            threadPool.submit(ft);
            p(String.format("指派了一个任务 %s 给线程池！", i));
        }
        countDownLatch.await();
        threadPool.shutdown();
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
                callMap.forEach((k, v) -> p(String.format("%s：任务 %s 运行完毕！", k, v)));
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
