package knowledge.concurrent.executor;

import l.demo.Demo;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * CompletableFuture
 * CompletableFuture 是 JDK1.8 加入的一个实现类，实现了 Future, CompletionStage
 * API 极其丰富，配合流式编程，速度飞快，推荐使用
 * <p>
 * Future、FutureTask、CompletionService、CompletableFuture 解决多线程并发中归集问题的效率对比：https://blog.csdn.net/f641385712/article/details/83580886
 * CompletableFuture 的系统讲解和实例演示：https://blog.csdn.net/f641385712/article/details/83618189
 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html
 *
 * @author ljh
 * @since 2020/12/1 14:08
 */
public class CompletableFutureDemo extends Demo {

    private final static int NUM_OF_TASK = 5;
    private final static ExecutorService threadPool = Executors.newFixedThreadPool(5, new MyThreadFactory());

    @Test
    public void testCompletableFuture() {
        List<CompletableFuture<Void>> completableFutureList = new ArrayList<>();
        for (int i = 1; i <= NUM_OF_TASK; i++) {
            completableFutureList.add(CompletableFuture.runAsync(new MyTask(i), threadPool));
            p(String.format("指派了一个任务 %s 给线程池！", i));
        }

        try {
            CompletableFuture<Void> completableFuture = CompletableFuture.allOf(completableFutureList.stream()
                    .filter(Objects::nonNull).collect(Collectors.toList()).toArray(new CompletableFuture[completableFutureList.size()]));
            completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    @Test
    public void testCompletableFuture2() {
        CompletableFuture<?>[] completableFutures = Lists.list(1, 2, 3, 4, 5).stream().map(i -> {
            p(String.format("指派了一个任务 %s 给线程池！", i));
            return CompletableFuture.runAsync(new MyTask(i), threadPool);
        }).toArray(CompletableFuture[]::new);

        try {
            CompletableFuture<Void> completableFuture = CompletableFuture.allOf(completableFutures);
            completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
