package knowledge.concurrent.future;

import l.demo.Demo;

import java.util.Map;
import java.util.concurrent.*;

/**
 * CompletionService
 * <pre>
 * CompletionService 为线程池中任务的执行结果服务的，即为 Executor 中任务返回 Future 而服务的。
 * CompletionService 的实现目标是任务先完成可优先获取到，即结果按照完成先后顺序排序。
 * </pre>
 *
 * @author ljh
 * @see <a href="https://www.cnblogs.com/shijiaqi1066/p/10454237.html">CompletionService 使用与原理</a>
 * @since 2020/10/26 10:44
 */
public class CompletionServiceDemo extends Demo {

    private final static int NUM_OF_TASK = 5;

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(5, new MyThreadFactory());

        CompletionService<Map<Integer, String>> completionService = new ExecutorCompletionService<>(threadPool);

        // 添加任务
        for (int i = 1; i <= NUM_OF_TASK; i++) {
            completionService.submit(new MyCallable(i));
            p(String.format("指派了一个任务 %s 给线程池！", i));
        }

        // 获取结果
        try {
            for (int i = 0; i < NUM_OF_TASK; i++) {
                // Future<V>        take()
                // 检索并删除代表下一个已完成任务的Future，如果还没有，则阻塞等待
                Map<Integer, String> callMap = completionService.take().get();
                // Future<V>        poll(long timeout, TimeUnit unit)
                // 检索并删除代表下一个已完成任务的Future，指定最大阻塞时间
                // Map<Integer, String> callMap = completionService.poll(300, TimeUnit.MILLISECONDS).get();
                callMap.forEach((k, v) -> p(String.format("%s：任务 %s 运行完毕！", k, v)));
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }
    }
}
