package knowledge.concurrent;

import knowledge.design.pattern.gof.creational.singleton.SingletonDemo.DoubleCheckLockSingleton;

import java.util.concurrent.TimeUnit;

/**
 * volatile
 * <p>两大特性：
 * <pre>
 * 1 禁止重排序：
 *   1.1 编译器和 CPU 为了性能会对指令进行重排序
 *   1.2 JMM (Java memory model) 通过内存屏障（CPU 指令）禁止特定重排序，屏障前后的指令不能跨越屏障
 * 2 内存可见性：
 *   2.1 普通变量：读写操作只在线程私有的工作内存（栈）中进行，其他线程看不到最新值
 *   2.2 volatile 变量
 *      2.2.1 读操作：JMM 把工作内存中的值设为无效，要求必须从主内存中读取数据
 *      2.2.2 写操作：JMM 把工作内存中的值刷新到主内存，其它线程可以读取到最新值
 * 注：不具备原子性
 * </pre>
 * 阿里编程规约：
 * <pre>
 * volatile 解决多线程内存不可见问题。对于一写多读，是可以解决变量同步问题，但是如果多写，同样无法解决线程安全问题
 * 如果是 count++ 操作，使用如下类实现：AtomicInteger count = new AtomicInteger(); count.addAndGet(1);
 * 如果是 JDK8，推荐使用 LongAdder 对象，比 AtomicLong 性能更好（减少乐观锁的重试次数）
 * 原因：count++ 其实是一个“读取 -> 修改 -> 写入”的复合操作。volatile 只能保证单次读或写的可见性，无法保证这三步合在一起是原子执行的
 * </pre>
 * 使用场景：
 * <pre>
 * 1 状态标记 volatile boolean
 * 2 双重检查 double check，如{@link DoubleCheckLockSingleton 双重检查锁单例}
 * </pre>
 *
 * @author ljh
 * @see <a href="https://blog.csdn.net/dhfzhishi/article/details/74279091">深入 Java 内存模型—happen-before 规则及其对 DCL 的分析</a>
 * @see <a href="https://blog.csdn.net/ns_code/article/details/17377197">深入 Java 内存模型—内存操作规则总结</a>
 * @see <a href="https://www.jianshu.com/p/195ae7c77afe">volatile 关键字解惑</a>
 * @since 2020/11/18 19:37
 */
public class VolatileDemo {

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo v = new VolatileDemo();
        new Thread(v::run).start();
        TimeUnit.SECONDS.sleep(2);
        v.flag = false;
    }

    private volatile boolean flag = true;

    public void run() {
        System.out.println("start");
        while (true) {
            if (!flag) break;
        }
        System.out.println("stop");
    }
}
