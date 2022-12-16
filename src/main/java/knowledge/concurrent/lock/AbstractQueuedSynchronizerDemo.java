package knowledge.concurrent.lock;

/**
 * AbstractQueuedSynchronizer
 * <p>利用先进先出队列实现的底层同步工具类，它是很多上层同步实现类的基础，比如：ReentrantLock、CountDownLatch、Semaphore 等
 * <p>它们通过继承 AQS 实现其模版方法，然后将 AQS 子类作为同步组件的内部类，通常命名为 Sync
 *
 * @author ljh
 * @since 2022/7/26 11:13
 */
public class AbstractQueuedSynchronizerDemo {
}
