package knowledge.api.lang;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/Object.html">Object</a>
 * <p>protected Object  clone()             创建并返回此对象的一个副本
 * <pre>
 * <a href="https://www.zhihu.com/question/52490586">为什么实现了 Cloneable 接口，就能调用 clone()？</a>
 * 慎用 Object 的 clone 方法来拷贝对象，对象 clone 方法默认是浅拷贝（阿里编程规约）
 * </pre>
 * protected void       finalize()          当垃圾回收器确定不存在对该对象的更多引用时，由对象的垃圾回收器调用此方法
 * <pre>
 * <a href="https://blog.csdn.net/maoyeqiu/article/details/49562093">为什么不使用 finalize()</a>
 * </pre>
 * int                  hashCode()          返回该对象的哈希码值
 * <pre>
 * hashCode() 的主要作用是配合基于散列的集合一起正常运行，如 HashMap, HashSet, HashTable
 * 如果两个对象相等，他们的 hashCode 值一定相等；如果两个对象的 hashCode 值相等，他们不一定相等
 * <a href="https://zhuanlan.zhihu.com/p/26814793">Java 中的 HashCode</a>
 * </pre>
 * boolean              equals(Object obj)  指示其他某个对象是否与此对象“相等”
 * <pre>
 * 对于基本类型，比较的是基本类型的值；对于引用类型，比较的是对象的内存地址
 * </pre>
 * 阿里编程规约：
 * <pre>
 * 1）只要覆写 equals，就必须覆写 hashCode。
 * 2）因为 Set 存储的是不重复的对象，依据 hashCode 和 equals 进行判断，所以 Set 存储的对象必须覆写这两种方法。
 * 3）如果自定义对象作为 Map 的键，那么必须覆写 hashCode 和 equals。
 * </pre>
 * <pre>
 * void                 notify()            唤醒在此对象监视器上等待的单个线程
 * void                 notifyAll()         唤醒在此对象监视器上等待的所有线程
 * 此方法只应由作为此对象监视器的所有者的线程来调用，否则抛出 IllegalMonitorStateException 异常
 * </pre>
 * 通过以下三种方法之一，线程可以成为此对象监视器的所有者：
 * <pre>
 * 1 通过执行此对象的同步实例方法
 * 2 通过执行在此对象上进行同步的 synchronized 语句的正文
 * 3 对于 Class 类型的对象，可以通过执行该类的同步静态方法
 * </pre>
 * void                 wait([long timeout, int nanos])
 * <pre>
 * 在其他线程调用此对象的 notify() 方法或 notifyAll() 方法，或者其他某个线程中断当前线程，或者已超过某个实际时间量前，导致当前线程等待
 * <a href="https://blog.csdn.net/jianiuqi/article/details/53448849">wait 和 notify 的理解与使用</a>
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class ObjectDemo {

    /**
     * Class<?>	        getClass()          返回此 Object 的运行时类
     */
    public static void main(String[] args) {
        // Object.class 和 object.getClass() 的区别：

        Number n = 2;
        // object.getClass()
        System.out.println(n.getClass().getName()); // java.lang.Integer

        // Object.class
        System.out.println(Number.class.getName()); // java.lang.Number
    }
}
