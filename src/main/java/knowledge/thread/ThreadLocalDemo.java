package knowledge.thread;

import com.google.common.collect.Lists;
import l.demo.Demo;

import java.util.List;

/**
 * ThreadLocal
 * ThreadLocal 中填充的变量属于当前线程，该变量对其他线程而言是隔离的。
 * <p>
 * 使用场景：
 * 1.在进行对象跨层传递的时候，使用 ThreadLocal 可以避免多次传递，打破层次间的约束
 * 2.线程间数据隔离
 * 3.进行事务操作，用于存储线程事务信息
 * 4.数据库连接，Session 会话管理
 * <p>
 * 知道ThreadLocal嘛？谈谈你对它的理解？：https://baijiahao.baidu.com/s?id=1653790035315010634&wfr=spider&for=pc
 * ThreadLocal作用、场景、原理 - 简书：https://www.jianshu.com/p/6fc3bba12f38
 *
 * @author ljh
 * created on 2020/11/6 12:55
 */
public class ThreadLocalDemo extends Demo {

    private static ThreadLocal<List<String>> threadLocal = new ThreadLocal<>();

    public void getThreadLocal() {
        threadLocal.get().forEach(name -> p(Thread.currentThread().getName() + " : " + name));
    }

    public void setThreadLocal(List<String> value) {
        threadLocal.set(value);
    }

    public static void main(String[] args) {
        ThreadLocalDemo demo = new ThreadLocalDemo();

        new Thread(() -> {
            List<String> strList = Lists.newArrayList("1", "2", "3");
            demo.setThreadLocal(strList);
            demo.getThreadLocal();
        }).start();

        new Thread(() -> {
            List<String> strList = Lists.newArrayList("a", "b", "c");
            demo.setThreadLocal(strList);
            demo.getThreadLocal();
        }).start();
    }
}
