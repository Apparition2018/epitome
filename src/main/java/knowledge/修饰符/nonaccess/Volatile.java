package knowledge.修饰符.nonaccess;

import java.util.concurrent.TimeUnit;

/**
 * volatile
 * volatile 修饰的成员变量在每次被线程访问时，都强制从共享内存中重新读取该成员变量的值。
 * 而且，当成员变量发生变化时，会强制线程将变化值回写到内存共享。
 * 这样在任何时刻，两个不同的线程总是看到某个成员变量的同一个值。
 * <p>
 * 两大特性：
 * 1.禁止重排序
 * 2.内存可见性
 * <p>
 * 使用条件：
 * 1.对变量的写入操作不依赖变量的当前值，或者你能确保只有单个线程更新变量的值。
 * 2.该变量没有包含在具有其他变量的不变式中。
 * <p>
 * volatile 关键字解惑：https://www.jianshu.com/p/195ae7c77afe
 */
public class Volatile {

    // 如果不使用 volatile 修饰，run() 不会停止
    private volatile Object o = null;

    public void run() {
        while (true) {
            if (o != null) {
                System.out.println("stop");
                break;
            }
        }
    }

    private void createObj() {
        o = new Object();
    }

    public static void main(String[] args) throws InterruptedException {

        Volatile v = new Volatile();
        new Thread(v::run).start();
        TimeUnit.SECONDS.sleep(1);
        v.createObj();
    }

}
