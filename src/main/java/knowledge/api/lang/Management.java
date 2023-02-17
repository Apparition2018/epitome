package knowledge.api.lang;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Management
 * <p>management 包提供管理接口，用于监视和管理 Java 虚拟机以及 Java 虚拟机在其上运行的操作系统。
 * MxBean 摘要：
 * <pre>
 * ClassLoadingMXBean       用于 Java 虚拟机的类加载系统的管理接口。
 * CompilationMXBean        用于 Java 虚拟机的编译系统的管理接口。
 * GarbageCollectorMXBean   用于 Java 虚拟机的垃圾回收的管理接口。
 * MemoryManagerMXBean      内存管理器的管理接口。
 * MemoryMXBean             Java 虚拟机内存系统的管理接口。
 * MemoryPoolMXBean         内存池的管理接口。
 * OperatingSystemMXBean    用于操作系统的管理接口，Java 虚拟机在此操作系统上运行。
 * RuntimeMXBean            Java 虚拟机的运行时系统的管理接口。
 * ThreadMXBean             Java 虚拟机线程系统的管理接口。
 * </pre>
 * 类摘要：
 * <pre>
 * LockInfo                 关于锁的信息。
 * ManagementFactory        是一种工厂类，用于获取 Java 平台的管理 Bean。
 * ManagementPermission     使用 SecurityManager 运行的代码调用 Java 平台的管理接口中定义的方法时，SecurityManager 将要检查的权限。
 * MemoryNotificationInfo   关于内存通知的信息。
 * MemoryUsage              表示内存使用量快照的 MemoryUsage 对象。
 * MonitorInfo              关于对象监视器锁的信息。
 * ThreadInfo               线程信息。
 * </pre>
 *
 * @author ljh
 * @since 2020/10/22 22:54
 */
public class Management {

    /**
     * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/management/ThreadMXBean.html">ThreadMXBean</a>
     * <p>Java 虚拟机线程系统的管理接口。
     *
     * @see <a href="https://www.cnblogs.com/jiaoyiping/p/9250668.html">使用Java提供的MXBean来监控jvm创建了哪些线程</a>
     */
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // long[]	            getAllThreadIds()
        // 返回活动线程 ID
        long[] threadIds = threadMXBean.getAllThreadIds();
        // ThreadInfo[]	        getThreadInfo(long[] ids)
        // 返回其 ID 在输出数组 ids 中的每个线程的线程信息，这些线程不具有堆栈跟踪
        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + ": " + threadInfo.getThreadName());
            // 6: Monitor Ctrl-Break
            // 5: Attach Listener
            // 4: Signal Dispatcher
            // 3: Finalizer
            // 2: Reference Handler
            // 1: main
        }
    }
}
