package knowledge.线程;

import l.demo.Demo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * FutureTask
 * FutureTask 是 Future 和 Callable 的结合体
 * https://blog.csdn.net/zmx729618/article/details/51596414
 */
public class FutureTaskDemo extends Demo {

    @Test
    public void testFuture() {

        ExecutorService es = Executors.newFixedThreadPool(5);

        List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            // <T> Future<T>	submit(Callable<T> task)
            // 提交一个 Callable 任务用于执行，并返回一个表示该任务的 Future
            Future<String> f = es.submit(new MyCallable());
            results.add(f);
        }

        boolean flag = true;
        while (flag) {
            for (Iterator<Future<String>> it = results.iterator(); it.hasNext(); ) {
                Future<String> f = it.next();

                // boolean	    isDone()
                // 如果任务已完成，则返回 true
                if (f.isDone()) {
                    try {

                        // V	get([long timeout, TimeUnit unit])
                        // 如有必要，最多等待为使计算完成所给定的时间之后，获取其结果（如果结果可用），会阻塞主线程
                        p(f.get());
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

        p("执行完毕");
        es.shutdownNow();

    }

    /**
     * 使用 futureTask 实现上面例子，解决多任务结果，效果最优
     */
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            // FutureTask(Callable<V> callable)
            // 创建一个 FutureTask，一旦运行就执行给定的 Callable
            MyFutureTask ft = new MyFutureTask(new MyCallable());
            es.submit(ft);
        }
        es.shutdown();
    }

    static class MyFutureTask extends FutureTask<String> {

        MyFutureTask(Callable<String> callable) {
            super(callable);
        }

        @Override
        public void done() {
            try {
                // V	get([long timeout, TimeUnit unit])
                // 如有必要，最多等待为使计算完成所给定的时间之后，获取其结果（如果结果可用）
                p(get() + " 线程执行完毕！");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            Random random = new Random();
            TimeUnit.SECONDS.sleep(random.nextInt(3));
            return Thread.currentThread().getName();
        }
    }

}