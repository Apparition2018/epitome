package knowledge.concurrent;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * ThreadLocal
 * ThreadLocal 中填充的变量属于当前线程，该变量对其他线程而言是隔离的
 * ThreadLocal 对象使用 static 修饰，ThreadLocal 无法解决共享对象的更新问题（阿里编程规约）
 * <p>
 * 使用场景：
 * 1.在进行对象跨层传递的时候，使用 ThreadLocal 可以避免多次传递，打破层次间的约束
 * 2.线程间数据隔离
 * 3.进行事务操作，用于存储线程事务信息
 * 4.数据库连接，Session 会话管理
 * <p>
 * 知道 ThreadLocal 嘛？谈谈你对它的理解？：https://baijiahao.baidu.com/s?id=1653790035315010634
 * 说说线程封闭与 ThreadLocal 的关系：https://blog.csdn.net/qq_33589510/article/details/105071141
 *
 * @author ljh
 * created on 2020/11/6 12:55
 */
public class ThreadLocalDemo {

    private static final ThreadLocal<List<String>> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            List<String> strList = Lists.newArrayList("1", "2", "3");
            try {
                threadLocal.set(strList);
                threadLocal.get().forEach(name -> System.out.println(Thread.currentThread().getName() + " : " + name));
            } finally {
                // 阿里编程规约：
                // 必须回收自定义的 ThreadLocal 变量记录的当前线程的值，尤其在线程池场景下，线程经常会被复用，
                // 如果不清理自定义的 ThreadLocal 变量，可能会影响后续业务逻辑和造成内存泄露等问题。
                // 尽量在代码中使用 try-finally 块进行回收
                threadLocal.remove();
            }
        }, "Thread-A").start();

        new Thread(() -> {
            List<String> strList = Lists.newArrayList("a", "b", "c");
            try {
                threadLocal.set(strList);
                threadLocal.get().forEach(name -> System.out.println(Thread.currentThread().getName() + " : " + name));
            } finally {
                threadLocal.remove();
            }
        }, "Thread-B").start();
    }
}
