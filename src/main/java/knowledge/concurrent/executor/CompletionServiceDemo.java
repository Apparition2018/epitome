package knowledge.concurrent.executor;

import l.demo.Demo;

import java.util.Map;
import java.util.concurrent.*;

/**
 * CompletionService
 * CompletionService 为线程池中任务的执行结果服务的，即为 Executor 中任务返回 Future 而服务的。
 * CompletionService 的实现目标是任务先完成可优先获取到，即结果按照完成先后顺序排序。
 * <p>
 * CompletionService 使用与原理：https://www.cnblogs.com/shijiaqi1066/p/10454237.html
 *
 * @author ljh
 * created on 2020/10/26 10:44
 */
public class CompletionServiceDemo extends Demo {

    private final static int NUM_OF_TASK = 5;

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(5);

        CompletionService<Map<Integer, String>> completionService = new ExecutorCompletionService<>(pool);

        // 添加任务
        for (int i = 0; i < NUM_OF_TASK; i++) {
            completionService.submit(new MyCallable(i + 1));
            p("指派了一个任务 " + i + " 给线程池！");
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
                for (Map.Entry<Integer, String> entry : callMap.entrySet()) {
                    p(entry.getValue() + "：运行任务 " + entry.getKey() + " 完毕！");
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }

    }

}
