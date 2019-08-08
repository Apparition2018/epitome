package knowledge.线程.threadpool;

/**
 * ThreadPoolExecutor
 * <p>
 * http://tool.oschina.net/uploads/apidocs/jdk-zh/index.html?java/util/concurrent/ThreadPoolExecutor.html
 * <p>
 * <p>
 * ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue[, ThreadFactory threadFactory, RejectedExecutionHandler handler])
 * 用给定的初始参数创建新的 ThreadPoolExecutor
 * <p>
 * corePoolSize     池中所保存的线程数，包括空闲线程
 * maximumPoolSize  池中允许的最大线程数
 * keepAliveTime    当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间
 * unit             keepAliveTime 参数的时间单位
 * workQueue        执行前用于保持任务的队列。此队列仅保持由 execute() 提交的 Runnable 任务
 * threadFactory    执行程序创建新线程时使用的工厂
 * handler          由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序
 */
public class ThreadPoolExecutorDemo {
}