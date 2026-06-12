package knowledge.concurrent;

import java.util.List;

/**
 * ThreadLocal
 * 填充的变量属于当前线程，该变量对其他线程而言是隔离的
 * <p>使用场景：
 * <pre>
 * 1 在进行对象跨层传递的时候，使用 ThreadLocal 可以避免多次传递，打破层次间的约束
 * 2 线程间数据隔离
 * 3 进行事务操作，用于存储线程事务信息
 * 4 数据库连接，Session 会话管理
 * </pre>
 * ThreadLocalMap
 * <pre>
 * 1 开放地址法：ThreadLocal 的 entry 数量通常很少（一个线程用几个），线性探测比链地址法更节省内存
 * 2 {@code static class Entry extends WeakReference<ThreadLocal<?>>}
 * </pre>
 *
 * @author ljh
 * @see <a href="https://blog.csdn.net/qq_33589510/article/details/105071141">说说线程封闭与 ThreadLocal 的关系</a>
 * @since 2020/11/6 12:55
 */
public class ThreadLocalDemo {

    private static final ThreadLocal<List<String>> THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) {
        startThread("Thread-A", List.of("1", "2", "3"));
        startThread("Thread-B", List.of("a", "b", "c"));
    }

    private static void startThread(String threadName, List<String> data) {
        new Thread(() -> {
            try {
                // ThreadLocal 对象使用 static 修饰，ThreadLocal 无法解决共享对象的更新问题（阿里编程规约）
                // 这里 data 不是共享对象
                THREAD_LOCAL.set(data);
                THREAD_LOCAL.get().forEach(name -> System.out.println(Thread.currentThread().getName() + " : " + name));
            } finally {
                // 阿里编程规约：
                // 必须回收自定义的 ThreadLocal 变量记录的当前线程的值，尤其在线程池场景下，线程经常会被复用，
                // 如果不清理自定义的 ThreadLocal 变量，可能会影响后续业务逻辑和造成内存泄露等问题。
                // 尽量在代码中使用 try-finally 块进行回收
                THREAD_LOCAL.remove();
            }
        }, threadName).start();
    }
}
