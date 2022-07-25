package knowledge.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * volatile
 * <p>
 * 两大特性：
 * 1.禁止重排序：在程序运行时，为了提高执行性能，编译器和处理器会对指令进行重排序，
 * -  JMM(Java memory model) 为了保证在不同的编译器和 CPU 上有相同的结果，通过插入特定类型的内存屏障来禁止特定类型的编译器重排序和处理器重排序，
 * -  插入一条内存屏障（内存栅栏，一个 CPU 指令）告诉编译器和 CPU：不管什么指令都不能给这条 Memory Barrier 指令重排序
 * 2.内存可见性：基于内存屏障禁止重排序实现
 * -  2.1 普通变量：读操作会优先读取工作内存的数据，如果工作内存中不存在，则从主内存（所有线程共享）中拷贝一份数据到工作内存中；写操作只会修改工作内存的副本数据，其它线程无法读取变量的最新值
 * -  2.2 volatile 变量：操作时 JMM 把工作内存中对应的值设为无效，要求线程从主内存中读取数据；写操作时 JMM 把工作内存中对应的数据刷新到主内存中，其它线程可以读取变量的最新值
 * 注：volatile 不具备原子性
 * <p>
 * 阿里编程规约：
 * volatile 解决多线程内存不可见问题。对于一写多读，是可以解决变量同步问题，但是如果多写，同样无法解决线程安全问题
 * 如果是 count++操作，使用如下类实现：AtomicInteger count = new AtomicInteger(); count.addAndGet(1);
 * 如果是 JDK8，推荐使用 LongAdder 对象，比 AtomicLong 性能更好（减少乐观锁的重试次数）
 * Volatile 不能保证数据同步：https://mouselearnjava.iteye.com/blog/1920154
 * <p>
 * 使用条件：
 * 1.对变量的写入操作不依赖变量的当前值，如多线程下 a++，a=a+1 等
 * 2.该变量没有包含在具有其它变量的不变式中
 * <p>
 * 使用场景：
 * 1.状态标记 volatile boolean
 * 2.双重检查 double check，如双重检查锁单例
 * <p>
 * 深入 Java 内存模型—happen-before 规则及其对 DCL 的分析：https://blog.csdn.net/dhfzhishi/article/details/74279091
 * 深入 Java 内存模型—内存操作规则总结：https://blog.csdn.net/ns_code/article/details/17377197
 * volatile 关键字解惑：https://www.jianshu.com/p/195ae7c77afe
 * volatile 的理解与正确使用：https://www.jb51.net/article/115374.htm
 * volatile 的适用场景：https://www.cnblogs.com/ouyxy/p/7242563.html
 *
 * @author ljh
 * created on 2020/11/18 19:37
 */
public class Volatile {

    public static void main(String[] args) throws InterruptedException {
        Volatile v = new Volatile();
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
