package knowledge.concurrent;

import org.apache.commons.lang3.StringUtils;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static l.demo.Demo.p;

/**
 * VirtualThread
 * 由 JVM 而非操作系统调度的线程
 *
 * <p>与平台线程的区别：
 * <pre>
 * 区别               虚拟线程        平台线程
 * 切换管辖区        JVM用户态          OS内核
 * 切换开销         100纳秒           1~10微妙
 * 挂起1000个线程    几个载体线程      1000个OS线程
 * 内存           几百字节/线程      1MB/线程
 * </pre>
 *
 * <p>注意事项：
 * <pre>
 * 1. 不要使用 synchronized，同步块会 pinned 在平台线程
 * 2. JNI 调用也会 pinned 在平台线程
 * 3. 不要使用 ThreadLocal，虚拟线程无上限，每个线程都使用 ThreadLocal 会 OOM。如果是一次写入后续只读，改用 ScopedValue
 * 4. 不适合 CPU 密集型任务，因为多了一层 JVM 的 carrier thread 调度，这层调度对于纯 CPU 计算来说就是纯纯的额外开销
 * </pre>
 *
 * @author ljh
 * @since 2026/5/4 16:49
 */
public class VirtualThreadDemo {

    public static void main(String[] args) {
        Thread.startVirtualThread(defaultTask());
        // 不启动
        Thread vt = Thread.ofVirtual().name("vt").unstarted(defaultTask());
        vt.start();
        // 线程工厂
        ThreadFactory factory = Thread.ofVirtual().name("vt-worker-", 0).factory();
        factory.newThread(defaultTask()).start();
        factory.newThread(defaultTask()).start();
        // 线程执行器
        try (ExecutorService ve = Executors.newVirtualThreadPerTaskExecutor()) {
            ve.submit(defaultTask());
        }
    }

    private static @NonNull Runnable defaultTask() {
        return () -> {
            String name = Thread.currentThread().getName();
            p((name.isBlank() ? "(unnamed)" : name) + (Thread.currentThread().isVirtual() ? " isVirtual" : StringUtils.EMPTY));
        };
    }
}
