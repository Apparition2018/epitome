package knowledge.modifires.nonaccess;

import java.util.concurrent.TimeUnit;

/**
 * volatile
 * <p>
 * 两大特性：
 * 1.禁止重排序：在程序运行时，为了提高执行性能，编译器和处理器会对指令进行重排序，
 * -    JMM 为了保证在不同的编译器和 CPU 上有相同的结果，通过插入特定类型的内存屏障来禁止特定类型的编译器重排序和处理器重排序，
 * -    插入一条内存屏障（内存栅栏，一个 CPU 指令）告诉编译器和 CPU：不管什么指令都不能给这条 Memory Barrier 指令重排序
 * 2.内存可见性：基于内存屏障禁止重排序实现
 * -    2.1：普通变量：读操作会优先读取工作内存的数据，如果工作内存中不存在，则从主内存（所有线程共享）中拷贝一份数据到工作内存中；写操作只会修改工作内存的副本数据，其它线程无法读取变量的最新值
 * -    2.2：volatile 变量：操作时 JMM 把工作内存中对应的值设为无效，要求线程从主内存中读取数据；写操作时 JMM 把工作内存中对应的数据刷新到主内存中，其它线程可以读取变量的最新值
 * <p>
 * 使用条件：
 * 1.对变量的写入操作不依赖变量的当前值，如多线程下 a++，无法通过 volatile 保证结果的准确。
 * 2.该变量没有包含在具有其他变量的不变式中。
 * <p>
 * 使用场景：
 * 1.状态标记 volatile boolean
 * 2.双重检查 double check，如双重检查锁单例
 * <p>
 * volatile 关键字解惑：https://www.jianshu.com/p/195ae7c77afe
 */
public class Volatile {

    public static void main(String[] args) throws InterruptedException {
        Volatile v = new Volatile();
        new Thread(v::run).start();
        TimeUnit.SECONDS.sleep(1);
        v.createObj();
    }

    // 如果不使用 volatile 修饰，run() 不会停止
    private volatile Object o = null;

    public void run() {
        while (true) {
            if (null != o) {
                System.out.println("stop");
                break;
            }
        }
    }

    private void createObj() {
        o = new Object();
    }
}
