package knowledge.concurrent.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Callable
 * https://www.runoob.com/manual/jdk1.6/java/util/concurrent/Callable.html
 * <p>
 * 和 Runnable 比较
 * 相同点：
 * 1.两者都可用来编写多线程程序；
 * 2.两者都需要调用 Thread.start() 启动线程；
 * 不相点：
 * 1.Callable 是泛型参数化接口
 * 2.两者最大的不同点是：实现 Callable 接口的任务线程能返回执行结果；而实现 Runnable 接口的任务线程不能返回结果；
 * 3.Callable 接口的 call() 方法允许抛出异常；而 Runnable 接口的 run() 方法的异常只能在内部消化，不能继续上抛；
 * 4.Callable 接口支持返回执行结果，此时需要调用 FutureTask.get() 方法实现，此方法会阻塞主线程直到获取'将来'结果；当不调用此方法时，主线程不会阻塞！
 */
public class CallableDemo {

    /**
     * Java 线程之 FutureTask 与 Future 浅析
     * https://blog.csdn.net/zmx729618/article/details/51596414
     */
    public static void main(String[] args) {
        Callable<String> callable = () -> {
            TimeUnit.SECONDS.sleep(1);
            return "Callable";
        };

        FutureTask<String> task = new FutureTask<>(callable);
        new Thread(task).start();

        String result = null;
        try {
            // V	    get([long timeout, TimeUnit unit])
            // 如有必要，最多等待为使计算完成所给定的时间之后，获取其结果（如果结果可用）
            result = task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);

        System.out.println("main线程完毕");
    }

}
